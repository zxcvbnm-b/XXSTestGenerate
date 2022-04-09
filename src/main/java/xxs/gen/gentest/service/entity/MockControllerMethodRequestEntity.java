package xxs.gen.gentest.service.entity;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public class MockControllerMethodRequestEntity {
    private  String urlTemplate;//需要包括/${xx}的情况 需要动态获取
    private  String contextPath;//容器的xxx 需要动态获取
    private  Map<String,String> httpHeaders =new HashMap<>();//需要拼接字符串
    private  Map<String, String> params=new HashMap<String, String>();//需要拼接字符串
    private  String content="";//如果有@RequestBody的注解，那么有值
    private MediaType contentType= MediaType.APPLICATION_JSON_UTF8;
    private  String contentTypeString="APPLICATION_JSON_UTF8";
    private HttpMethod httpMethod;
    private String methodName;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getUrlTemplate() {
        return urlTemplate;
    }

    public void setUrlTemplate(String urlTemplate) {
        this.urlTemplate = urlTemplate;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public void setHttpHeaders(Map<String, String> httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    public Map<String, String> getHttpHeaders() {
        return httpHeaders;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MediaType getContentType() {
        return contentType;
    }

    public void setContentType(MediaType contentType) {
        this.contentType = contentType;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getContentTypeString() {
        return contentTypeString;
    }

    public void setContentTypeString(String contentTypeString) {
        this.contentTypeString = contentTypeString;
    }
}
