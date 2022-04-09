package xxs.gen.gentest.resolver;


import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import xxs.gen.gentest.service.entity.MockControllerMethodRequestEntity;

import javax.servlet.http.Part;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
/*处理有@RequestParam的方法参数 --注入参数*/
public class RequestParamMethodParamResolver implements MethodParamResolver{
    @Override
    public boolean supports(MethodParameter methodParameter) {
            boolean hasRequestParam = methodParameter.hasParameterAnnotation(RequestParam.class);
            if(hasRequestParam){
                return true;
            }
        return false;
    }

    @Override
    public void resolver(MethodParameter methodParameter, Object methodRequestEntity) {
        try {
            MockControllerMethodRequestEntity mockControllerMethodRequestEntity = null;
            Class<?> parameterType = methodParameter.getParameterType();
            if (methodRequestEntity instanceof MockControllerMethodRequestEntity) {
                mockControllerMethodRequestEntity = (MockControllerMethodRequestEntity) methodRequestEntity;

            }
            Map<String, String> params = mockControllerMethodRequestEntity.getParams();
            /*RequestParam 请求问题*/
            /*如果是基本数据类型，不处理，String，不处理，文件的不处理，集合，数组的不处理，暂时不处理*/
            if (parameterType.isPrimitive() || parameterType.isAssignableFrom(Date.class)
                    || parameterType.isAssignableFrom(Part.class) || parameterType.isAssignableFrom(MultipartFile.class)
                    || parameterType.isArray() || parameterType.isAssignableFrom(Collection.class)) {
                return;
            }
            if (parameterType.isAssignableFrom(String.class)) {
                params.put(methodParameter.getParameterName(), "stringparam");
                return;
            }
            /*构建参数实例*/
            Constructor<?> declaredConstructor = parameterType.getDeclaredConstructor();
            Object param = declaredConstructor.newInstance();
            /*反射设置属性值*/
            reflectObjectParam(param, params);
            mockControllerMethodRequestEntity.setParams(params);
            return;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Integer getOrder() {
        return 40;
    }

    public  void reflectObjectParam(Object obj,Map<String,String> params) throws Exception {
        Field[] field = obj.getClass().getDeclaredFields();        //获取实体类的所有属性，返回Field数组
        for(int j=0 ; j<field.length ; j++){     //遍历所有属性
            String fname = field[j].getName();    //获取属性名称
            fname = fname.substring(0,1).toUpperCase()+fname.substring(1); //将属性的首字符大写，方便构造get，set方法
            String ftype = field[j].getGenericType().toString();    //获取属性的类型
            if(ftype.equals("class java.lang.String")){   //如果ftype是类类型，则前面包含"class "，后面跟类名
                params.put(fname,fname);
            }
            if(ftype.equals("class java.lang.Integer")){
                params.put(fname,"1");
            }
            if(ftype.equals("class java.lang.Short")){
                params.put(fname,"1");
            }
            if(ftype.equals("class java.lang.Double")){
                params.put(fname,"1L");
            }
            if(ftype.equals("class java.lang.Boolean")){
                params.put(fname,"true");
            }
            if(ftype.equals("class java.util.Date")){
                params.put(fname,new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            }
            if(field[j].getType().isAssignableFrom(BigDecimal.class)){
                params.put(fname,"10.0");
            }
        }
    }

}
