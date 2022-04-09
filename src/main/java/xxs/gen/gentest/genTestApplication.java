package xxs.gen.gentest;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import xxs.gen.gentest.build.MockHttpRequestBuilder;
import xxs.gen.gentest.intercept.GenTestIntercept;
import xxs.gen.gentest.intercept.GenTestInterceptChain;
import xxs.gen.gentest.intercept.GenTestInterceptContainer;
import xxs.gen.gentest.service.ServiceContext;
import xxs.gen.gentest.service.entity.ControllerRequestMappingEntity;
import xxs.gen.gentest.service.entity.GenTestConfig;
import xxs.gen.gentest.service.entity.MockControllerMethodRequestEntity;
import xxs.gen.gentest.service.entity.VelocityGenOneTestDTO;
import xxs.gen.gentest.valid.ValidContext;
import xxs.gen.gentest.velocity.utils.Commons;
import xxs.gen.gentest.velocity.utils.GenControllerTestUtils;

import javax.naming.ConfigurationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
对于一些你需要自己去生成的测试类，那么只能靠你自己了 还有需要调用service层代码的也是，一些when操作都需要你自己去添加了 这里只是生成简单的测试类
* */
/*1.扩展：对于有token的配置处理-添加请求头的配置--1*/
/*2.扩展：根据控制器方法的参数自动生成测试用例*/
/*3.扩展：配置可以一次生成一个包下面的所有控制器--1*/
/*5.扩展：让生成的测试类直接生成在项目中，而不是指定绝对路径，还需要去创建文件夹。--1*/
/*6.添加配置文件，把一些东西弄成可配置项--可以使用spring的配置文件--1*/
/*给json字符串处理掉双引号的问题--1*/
/*7.针对构造请求参数问题，需要解决掉 复杂对象的参数的构造问题 比如基本数据类型，集合等。*/
@Component
public class genTestApplication implements ApplicationContextAware, ApplicationRunner {

    ApplicationContext applicationContext;

    @Autowired
    RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Autowired
    RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    private ValidContext validContext;

    private ServiceContext serviceContext;

    private MockHttpRequestBuilder mockHttpRequestBuilder;

    private GenTestInterceptContainer genTestInterceptContainer=new GenTestInterceptContainer();

    private GenTestConfig genTestConfig=new GenTestConfig();

    public void  before (){
        this.validContext=new ValidContext(applicationContext);
        this.serviceContext=new ServiceContext(applicationContext);
        this.mockHttpRequestBuilder=new MockHttpRequestBuilder(applicationContext,serviceContext,validContext);
        genTestInterceptContainer.setApplicationContext(applicationContext);
    }

    public void application() throws Exception {
        initGenTestConfig(genTestConfig);
        String absoluteDir = genTestConfig.getAbsoluteDir();
        Class controllerClass = genTestConfig.getControllerClass();
        String packageName = genTestConfig.getPackageName();
        if(genTestConfig.getControllerClass()!=null){
            /*加载控制器类*/
             this.genOneWebMvcTestByControllerName(controllerClass,absoluteDir);
        }
        if(StringUtils.isNotBlank(packageName)){
            /*加载所有的class类*/
            List<Class<?>> classNameFromPackage = Commons.getClasssFromPackage(packageName);
            if(classNameFromPackage!=null){
                for (Class<?> clazz : classNameFromPackage) {
                    if(AnnotatedElementUtils.hasAnnotation(clazz, Controller.class)){
                        this.genOneWebMvcTestByControllerName(clazz,absoluteDir);
                    }
                }
            }
        }

    }

    public GenTestConfig getGenTestConfig() {
        return genTestConfig;
    }
    /*初始化生成测试配置*/
    private void initGenTestConfig(GenTestConfig genTestConfig) throws ClassNotFoundException {
        Configuration genConfigProperties = getConfig();
        String controllerName = genConfigProperties.getString("config.controller-name");
        if(StringUtils.isNotBlank(controllerName)){
            Class<?> controllerClass = Class.forName(controllerName);
            genTestConfig.setControllerClass(controllerClass);
        }
        String absoluteDir = genConfigProperties.getString("config.absoluteDir");
        if(StringUtils.isNotBlank(absoluteDir)){
            genTestConfig.setAbsoluteDir(absoluteDir);
        }
        String packageName = genConfigProperties.getString("config.package-name");
        if(StringUtils.isNotBlank(packageName)){
            genTestConfig.setPackageName(packageName);
        }
        Map<String, String> headers = Commons.getMapValue(genConfigProperties, "request.header.");
        if(headers!=null){
            for (Map.Entry<String, String> header : headers.entrySet()) {
                genTestConfig.addHealer(header.getKey(),header.getValue());
            }
        }
        Map<String, String> params = Commons.getMapValue(genConfigProperties, "request.param.");
        if(params!=null){
            for (Map.Entry<String, String> param : params.entrySet()) {
                genTestConfig.addHealer(param.getKey(),param.getValue());
            }
        }
    }

    public void genOneWebMvcTestByControllerName(Class controllerClass,String absoluteDir) throws Exception {
        GenTestConfig genTestConfig = this.getGenTestConfig();
        /*根据控制器名称然后根据模板生成测试类和测试方法*/
        /*1.验证控制器类是否存中*/
        validContext.getControllerValid().isControllerValid(controllerClass);
        /*2.获取控制器的所有的方法*/
        List<ControllerRequestMappingEntity> cControllerRequestMappingEntityByClasses = serviceContext.getControllerMethodDispatch().getCRequestMethodInfoByClass(controllerClass);
        List<MockControllerMethodRequestEntity> mockRequestEntities=new ArrayList<>();
        GenTestInterceptChain genTestInterceptChain = getGenTestInterceptChain(MockControllerMethodRequestEntity.class);
        genTestInterceptChain.beforeDispatch(mockRequestEntities);
        /*3.简单构造请求参数*/
        for (ControllerRequestMappingEntity controllerRequestMappingEntityByClass : cControllerRequestMappingEntityByClasses) {
            MockControllerMethodRequestEntity mockControllerMethodRequestEntity = mockHttpRequestBuilder.builderMockRequestEntity(controllerRequestMappingEntityByClass);
            mockRequestEntities.add(mockControllerMethodRequestEntity);
            mockControllerMethodRequestEntity.getHttpHeaders().putAll(genTestConfig.getHealers());
            mockControllerMethodRequestEntity.getParams().putAll(genTestConfig.getParams());
        }
        genTestInterceptChain.afterDispatch(mockRequestEntities);
        /*获取需要生成测试类的控制器的包*/
        Package aPackage = controllerClass.getPackage();
        /*封装velocity模板对象*/
        VelocityGenOneTestDTO velocityGenOneTestDTO = new VelocityGenOneTestDTO();
        velocityGenOneTestDTO.setMockControllerMethodRequestEntities(mockRequestEntities);
        velocityGenOneTestDTO.setClassName(controllerClass.getSimpleName());
        velocityGenOneTestDTO.setPackageName(aPackage.getName());
        GenControllerTestUtils.generatorCode(velocityGenOneTestDTO,absoluteDir);

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
    /*获取生成测试的拦截器链*/
    public GenTestInterceptChain getGenTestInterceptChain(Class<?> clazz){
        GenTestInterceptChain genTestInterceptChain = new GenTestInterceptChain();
        if(clazz==null||genTestInterceptContainer==null){
           return genTestInterceptChain;
        }
        List<GenTestIntercept> allInterceptContainer = genTestInterceptContainer.getAllInterceptContainer();
        for (GenTestIntercept genTestIntercept : allInterceptContainer) {
            if(genTestIntercept.supports(clazz)){
                genTestInterceptChain.addGenTestIntercept(genTestIntercept);
            }
        }
        return genTestInterceptChain;
    }
    /**
     * 获取配置信息
     */
      private  Configuration getConfig() {
        try {
            PropertiesConfiguration propertiesConfiguration;
            return new PropertiesConfiguration("generatorTest.properties");
        } catch (Exception e) {
            throw new RuntimeException("获取配置文件失败，", e);
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.before();
        this.application();
    }
}
