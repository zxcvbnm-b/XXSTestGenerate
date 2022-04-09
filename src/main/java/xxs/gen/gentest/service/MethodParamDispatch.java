package xxs.gen.gentest.service;

import org.springframework.core.MethodParameter;
import xxs.gen.gentest.resolver.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/*控制器方法参数处理*/
public class MethodParamDispatch {
  private List<MethodParamResolver> resolvers=new ArrayList<>();
  public MethodParamDispatch(){
      initDefaultResolver();
  }
    private void initDefaultResolver() {
        resolvers.add(new IgnoreMethodParamResolver());
        resolvers.add(new PathVariableMethodParamResolver());
        resolvers.add(new RequestBodyMethodParamResolver());
        resolvers.add(new RequestParamMethodParamResolver());
        Collections.sort(resolvers, new Comparator<MethodParamResolver>() {
            @Override
            public int compare(MethodParamResolver o1, MethodParamResolver o2) {
                return o1.getOrder().compareTo(o2.getOrder());
            }
        });

    }
    /*解析方法参数并设置到 methodRequestEntity中*/
    public void   methodParamResolver(MethodParameter methodParameter, Object methodRequestEntity){
        resolvers:for (MethodParamResolver resolver : resolvers) {
            if(resolver.supports(methodParameter)){
                 resolver.resolver(methodParameter,methodRequestEntity);
                 break resolvers;
            }
        }
    }
}
