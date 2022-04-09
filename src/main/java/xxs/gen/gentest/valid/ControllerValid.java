package xxs.gen.gentest.valid;

import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;

/*控制器验证器*/
final public class ControllerValid {
    private ApplicationContext applicationContext;

    public ControllerValid(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    public  void isControllerValid(String controllerName){
        Object bean = applicationContext.getBean(controllerName);
        if(bean==null){
            throw new RuntimeException("控制器在系统中不存在");
        }
        Class<?> handlerType = bean.getClass();
        boolean flag = hasControllerAnnotation(handlerType);
        if(!flag){
            throw new RuntimeException("当前bean不是控制器--没有@Controller注解");
        }

    }
    public  void isControllerValid(Class controllerType){
        Object bean = applicationContext.getBean(controllerType);
        if(bean==null){
            throw new RuntimeException("控制器在系统中不存在");
        }
        Class<?> handlerType = bean.getClass();
        boolean flag = hasControllerAnnotation(handlerType);
        if(!flag){
            throw new RuntimeException("当前bean不是控制器--没有@Controller注解");
        }

    }
    private  boolean hasControllerAnnotation(Class<?> clazz){
        Class<?> userType = ClassUtils.getUserClass(clazz);
        boolean flag = AnnotatedElementUtils.hasAnnotation(userType, Controller.class);
        return flag;
    }
}
