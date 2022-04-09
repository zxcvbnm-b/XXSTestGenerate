package xxs.gen.gentest.intercept;


import java.util.Comparator;

/*不可断拦截*/
public interface GenTestIntercept extends Comparator<Integer> {
    public boolean supports(Class<?> clazz);
    public void beforeDispatch(Object mvcRequestDto);
    public void postDispatch(Object mvcRequestDto);
    public void afterDispatch(Object mvcRequestDto);
}
