package xxs.gen.gentest.intercept;

import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GenTestInterceptContainer {
    private ApplicationContext applicationContext;
    private List<GenTestIntercept> allInterceptContainer=new ArrayList<>();
    public GenTestInterceptContainer() {
        initDefaultGenTestIntercept();
    }

    private void initDefaultGenTestIntercept() {

    }

    public void addGenTestIntercept(GenTestIntercept genTestIntercept){
        allInterceptContainer.add(genTestIntercept);
    }

    public List<GenTestIntercept> getAllInterceptContainer() {
        return Collections.unmodifiableList(allInterceptContainer);
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.addGenTestInterceptByAppContext();
    }
    private void addGenTestInterceptByAppContext(){
        Map<String, GenTestIntercept> beansOfType = applicationContext.getBeansOfType(GenTestIntercept.class);
        if(beansOfType!=null){
             beansOfType.values().forEach(item->{
                 this.addGenTestIntercept(item);
             });

        }
    }
}
