package xxs.gen.gentest.service.entity;


import java.util.HashMap;
import java.util.Map;

public class GenTestConfig {
    private String packageName;
    private String  absoluteDir = System.getProperty("user.dir");
    private Class controllerClass= null;
    private Map<String,String> healers=new HashMap<>();
    private Map<String,String> params=new HashMap<>();
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAbsoluteDir() {
        return absoluteDir;
    }

    public void setAbsoluteDir(String absoluteDir) {
        this.absoluteDir = absoluteDir;
    }

    public Class getControllerClass() {
        return controllerClass;
    }

    public void setControllerClass(Class controllerClass) {
        this.controllerClass = controllerClass;
    }

    public Map<String, String> getHealers() {
        return healers;
    }

    public void setHealers(Map<String, String> healers) {
        this.healers = healers;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public void addParam(String paramName,String paramValue){
        this.params.put(paramName,paramValue);
    }
    public void addHealer(String healerName,String healerValue){
        this.healers.put(healerName,healerValue);
    }
}
