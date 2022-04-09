package xxs.gen.gentest.service.entity;


import java.util.HashMap;
import java.util.Map;
/* generatorTest.properties 配置文件的配置信息*/
public class GenTestConfig {
    private String packageName;/*需要生成的类的包名*/
    private String  absoluteDir = System.getProperty("user.dir");/*获取到项目的绝对路径*/
    private Class controllerClass= null;/*需要生成的控制器的类*/
    private boolean isOpenTransaction;/*是否使用事务注解*/
    private Map<String,String> healers=new HashMap<>();/*配置的请求头参数*/
    private Map<String,String> params=new HashMap<>();/*配置的请求参数*/
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

    public boolean isOpenTransaction() {
        return isOpenTransaction;
    }

    public void setOpenTransaction(boolean openTransaction) {
        isOpenTransaction = openTransaction;
    }

}
