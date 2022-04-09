package xxs.gen.gentest.resolver;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import xxs.gen.gentest.service.entity.MockControllerMethodRequestEntity;

import javax.servlet.http.Part;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Date;

public class RequestBodyMethodParamResolver  implements MethodParamResolver{

    @Override
    public boolean supports(MethodParameter methodParameter) {
        boolean hasRequestBody = methodParameter.hasParameterAnnotation(RequestBody.class);
        if(hasRequestBody){
          return true;
        }
        return false;
    }

    @Override
    public void resolver(MethodParameter methodParameter, Object methodRequestEntity)  {
       try{
           Class<?> parameterType = methodParameter.getParameterType();
           if(methodRequestEntity instanceof MockControllerMethodRequestEntity){
               MockControllerMethodRequestEntity mockControllerMethodRequestEntity=(MockControllerMethodRequestEntity)methodRequestEntity;
               if(parameterType.isPrimitive()||parameterType.isAssignableFrom(String.class)||parameterType.isAssignableFrom(Date.class)
                       ||parameterType.isAssignableFrom(Part.class)||
                       parameterType.isAssignableFrom(MultipartFile.class)||
                       parameterType.isArray()||parameterType.isAssignableFrom(Collection.class)){
                   return;
               }
               /*获取无参数的构造犯法，*/
               Constructor<?> declaredConstructor = parameterType.getDeclaredConstructor();
               /*创建方法参数实例对象*/
               Object parameterObject = declaredConstructor.newInstance();
               /*不注入值，但是对于null值不要忽略他*/
               String jsonString = com.alibaba.fastjson.JSONObject.toJSONString(parameterObject,false);

               /*TODO 给json字符串处理掉双引号的问题*/
               if(jsonString!=null){
                   jsonString=JSONObject.quote(jsonString);
               }
               mockControllerMethodRequestEntity.setContent(jsonString);
           }

       }catch (Exception e){
           e.printStackTrace();
       }

    }

    @Override
    public Integer getOrder() {
        return 30;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        /*获取无参数的构造犯法，*/
        Constructor<?> declaredConstructor = MockControllerMethodRequestEntity.class.getDeclaredConstructor();
        /*创建方法参数实例对象*/
        Object parameterObject = declaredConstructor.newInstance();
        /*不注入值，但是对于null值不要忽略他*/
        String jsonString = com.alibaba.fastjson.JSONObject.toJSONString(parameterObject,true);
        System.out.println(jsonString);
    }

}
