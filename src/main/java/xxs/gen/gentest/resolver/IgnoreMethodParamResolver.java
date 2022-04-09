package xxs.gen.gentest.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.io.Reader;
import java.security.Principal;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class IgnoreMethodParamResolver implements MethodParamResolver {
    @Override
    public boolean supports(MethodParameter methodParameter) {
        Class<?> parameterType = methodParameter.getNestedParameterType();
        if(parameterType==null){
           return true;
        }
        /*如果是如下类型的方法参数，那么跳过 不用我们创建*/
        if((WebRequest.class.isAssignableFrom(parameterType) ||
                ServletRequest.class.isAssignableFrom(parameterType) ||
                MultipartRequest.class.isAssignableFrom(parameterType) ||
                HttpSession.class.isAssignableFrom(parameterType) ||
                Principal.class.isAssignableFrom(parameterType) ||
                InputStream.class.isAssignableFrom(parameterType) ||
                Reader.class.isAssignableFrom(parameterType) ||
                HttpMethod.class == parameterType ||
                Locale.class == parameterType ||
                TimeZone.class == parameterType ||
                ZoneId.class == parameterType)||
                HttpServletRequest.class== parameterType||
                HttpServletResponse.class== parameterType|| Map.class==parameterType){
           return true;
        }
        return false;
    }

    @Override
    public void resolver(MethodParameter methodParameter, Object methodRequestEntity) {

    }

    @Override
    public Integer getOrder() {
        return 10;
    }
}
