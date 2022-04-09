package xxs.gen.gentest.intercept;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*缓存，应该缓存 GenTestInterceptChain 对象*/
public class GenTestInterceptChain implements GenTestIntercept{
 private List<GenTestIntercept> genTestIntercepts=new ArrayList<>();

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    public void beforeDispatch(Object mvcRequestDto){
        Iterator<GenTestIntercept> iterator = genTestIntercepts.iterator();
        while (iterator.hasNext()){
            GenTestIntercept genTestIntercept = iterator.next();
            genTestIntercept.beforeDispatch(mvcRequestDto);
        }
    }
    public void postDispatch(Object mvcRequestDto){
        Iterator<GenTestIntercept> iterator = genTestIntercepts.iterator();
        while (iterator.hasNext()){
            GenTestIntercept genTestIntercept = iterator.next();
            genTestIntercept.postDispatch(mvcRequestDto);
        }
    }
    public void afterDispatch(Object mvcRequestDto){
        Iterator<GenTestIntercept> iterator = genTestIntercepts.iterator();
        while (iterator.hasNext()){
            GenTestIntercept genTestIntercept = iterator.next();
            genTestIntercept.afterDispatch(mvcRequestDto);
        }
    }
    public void addGenTestIntercept(GenTestIntercept genTestIntercept){
        genTestIntercepts.add(genTestIntercept);
    }

    @Override
    public int compare(Integer o1, Integer o2) {
        return Integer.MAX_VALUE;
    }
}
