package xxs.gen.gentest.service;

import org.springframework.context.ApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import xxs.gen.gentest.service.entity.ControllerRequestMappingEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*控制器方法二次处理*/
public class ControllerMethodDispatch {
    /*key:控制器的名称，*/
 private Map<Class<?>, List<ControllerRequestMappingEntity>> controllerRequestMethodInfo=new HashMap<>();
 private ApplicationContext applicationContext;

    public ControllerMethodDispatch(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
        /*初始化controllerRequestMethodInfo */
        init();
    }
    private void init()   {
        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        /*得到所有的 HandlerMethod和控制类的匹配关系*/
        if(requestMappingHandlerMapping!=null){
            Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
         if(handlerMethods!=null){
             for (Map.Entry<RequestMappingInfo, HandlerMethod> item: handlerMethods.entrySet()) {
                 ControllerRequestMappingEntity controllerRequestMappingEntity =new ControllerRequestMappingEntity();
                 controllerRequestMappingEntity.setHandlerMethod(item.getValue());
                 controllerRequestMappingEntity.setRequestMappingInfo(item.getKey());
                 HandlerMethod value = item.getValue();
                 Class<?> controllerType = value.getBeanType();
                 List<ControllerRequestMappingEntity> controllerRequestMethodEntities = controllerRequestMethodInfo.getOrDefault(controllerType, new ArrayList<ControllerRequestMappingEntity>());
                 controllerRequestMethodEntities.add(controllerRequestMappingEntity);
                 controllerRequestMethodInfo.put(controllerType, controllerRequestMethodEntities);
             }
         }
        }
    }
    public  List<ControllerRequestMappingEntity> getAllControllerRequestMethodInfo(){
        List<ControllerRequestMappingEntity> controllerRequestMethodEntities =new ArrayList<>();
        for (Map.Entry<Class<?>, List<ControllerRequestMappingEntity>> item: controllerRequestMethodInfo.entrySet()) {
            controllerRequestMethodEntities.addAll(item.getValue());
        }
        return controllerRequestMethodEntities;
    }
    public  List<ControllerRequestMappingEntity> getCRequestMethodInfoByClass(Class<?> controllerClass){
        return controllerRequestMethodInfo.get(controllerClass);
    }

    public Map<Class<?>, List<ControllerRequestMappingEntity>> getControllerRequestMethodInfo() {
        return controllerRequestMethodInfo;
    }
}
