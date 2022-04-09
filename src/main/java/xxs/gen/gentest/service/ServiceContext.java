package xxs.gen.gentest.service;

import org.springframework.context.ApplicationContext;

/*服务类上下文 所有服务类都在这创建维护*/
public class ServiceContext {
    private ApplicationContext applicationContext;

    private ControllerMethodDispatch controllerMethodDispatch;

    private MethodParamDispatch methodParamDispatch;

    public ControllerMethodDispatch getControllerMethodDispatch() {
        return controllerMethodDispatch;
    }

    public MethodParamDispatch getMethodParamDispatch() {
        return methodParamDispatch;
    }

    public ServiceContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;

        controllerMethodDispatch= new ControllerMethodDispatch(applicationContext);
        this.methodParamDispatch=new MethodParamDispatch();
    }
}
