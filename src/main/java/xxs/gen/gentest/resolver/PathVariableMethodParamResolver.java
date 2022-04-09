package xxs.gen.gentest.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.PathVariable;
import xxs.gen.gentest.service.entity.MockControllerMethodRequestEntity;
import xxs.gen.gentest.velocity.utils.StringResolverUtils;
/*处理有PathVariable的方法参数 --注入参数*/
public class PathVariableMethodParamResolver implements MethodParamResolver{
    @Override
    public boolean supports(MethodParameter methodParameter) {
        boolean hasPathVariable = methodParameter.hasParameterAnnotation(PathVariable.class);
        if(hasPathVariable){
            return true;
        }
        return false;
    }

    @Override
    public void resolver(MethodParameter methodParameter, Object methodRequestEntity) {
        try{
            if(methodRequestEntity instanceof MockControllerMethodRequestEntity) {
                MockControllerMethodRequestEntity mockControllerMethodRequestEntity = (MockControllerMethodRequestEntity) methodRequestEntity;
                PathVariable parameterAnnotation = methodParameter.getParameterAnnotation(PathVariable.class);
                String parameterName = methodParameter.getParameterName();
                if (parameterAnnotation != null) {
                    parameterName = parameterAnnotation.name();
                }

                String urlTemplate = mockControllerMethodRequestEntity.getUrlTemplate();
                urlTemplate = StringResolverUtils.replaceKey("{", "}", urlTemplate, parameterName, "1");
                mockControllerMethodRequestEntity.setUrlTemplate(urlTemplate);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Integer getOrder() {
        return 20;
    }

}
