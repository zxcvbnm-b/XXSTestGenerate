package xxs.gen.gentest.valid;

import org.springframework.context.ApplicationContext;

/*验证类上下文 所有验证类都在这创建维护*/
public class ValidContext {
   private ApplicationContext applicationContext;

   private  ControllerValid controllerValid;

    public ValidContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        controllerValid =new ControllerValid(applicationContext);
    }

    public ControllerValid getControllerValid() {

        return controllerValid;
    }
}
