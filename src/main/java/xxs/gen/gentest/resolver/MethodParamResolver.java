package xxs.gen.gentest.resolver;

import org.springframework.core.MethodParameter;

public interface MethodParamResolver {
    public boolean supports(MethodParameter methodParameter);
    public void resolver(MethodParameter methodParameter, Object methodRequestEntity);
    public Integer getOrder();
}
