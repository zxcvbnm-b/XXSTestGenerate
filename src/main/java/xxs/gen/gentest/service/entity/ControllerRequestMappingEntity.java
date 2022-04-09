package xxs.gen.gentest.service.entity;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

public class ControllerRequestMappingEntity {
    private RequestMappingInfo requestMappingInfo;
    private HandlerMethod handlerMethod;

    public RequestMappingInfo getRequestMappingInfo() {
        return requestMappingInfo;
    }

    public void setRequestMappingInfo(RequestMappingInfo requestMappingInfo) {
        this.requestMappingInfo = requestMappingInfo;
    }

    public HandlerMethod getHandlerMethod() {
        return handlerMethod;
    }

    public void setHandlerMethod(HandlerMethod handlerMethod) {
        this.handlerMethod = handlerMethod;
    }
}
