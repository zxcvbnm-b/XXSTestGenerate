package xxs.gen.gentest.velocity.utils;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;


/**
 * Created by ZhangTieFeng on 2020/3/21.
 */

public class Commons {
    public static List<String> getClassNameFromPackage(String packageName){
        List<String> resultList = new ArrayList();
        List<Class<?>> classList = getClasssFromPackage(packageName);
        for (Class<?> clazz: classList) {
            resultList.add(clazz.getSimpleName());
        }
        return  resultList;
    }
    public static HashMap<String,String> getClassNameMapFromPackage(String packageName){
        List<HashMap<String,String>> resultList = new ArrayList();
        List<Class<?>> classList = getClasssFromPackage(packageName);
        HashMap<String, String> map = new HashMap<String, String>();
        for (Class<?> clazz: classList) {
            map.put(clazz.getSimpleName(),clazz.getName());
            resultList.add(map);
        }
        return map;
    }
    /**
     * 获得包下面的所有的class
     *
     * @param
     *
     * @return List包含所有class的实例
     */

    public static List<Class<?>> getClasssFromPackage(String packageName) {
        List<Class<?>> clazzs = new ArrayList();
        // 是否循环搜索子包
        boolean recursive = true;
        // 包名对应的路径名称
        String packageDirName = packageName.replace('.', '/');
        Enumeration<URL> dirs;

        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            while (dirs.hasMoreElements()) {

                URL url = dirs.nextElement();
                String protocol = url.getProtocol();

                if ("file".equals(protocol)) {
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    findClassInPackageByFile(packageName, filePath, recursive, clazzs);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return clazzs;
    }

    /**
     * 在package对应的路径下找到所有的class
     */
    private static void findClassInPackageByFile(String packageName, String filePath, final boolean recursive,
                                                 List<Class<?>> clazzs) {
        File dir = new File(filePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        // 在给定的目录下找到所有的文件，并且进行条件过滤
        File[] dirFiles = dir.listFiles(new FileFilter() {

            public boolean accept(File file) {
                boolean acceptDir = recursive && file.isDirectory();// 接受dir目录
                boolean acceptClass = file.getName().endsWith("class");// 接受class文件
                return acceptDir || acceptClass;
            }
        });

        for (File file : dirFiles) {
            if (file.isDirectory()) {
                findClassInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, clazzs);
            } else {
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    clazzs.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + className));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static String toLowerStartString(String str){
        //如果字符串str为null和""则返回原数据
        if (str==null||"".equals(str)){
            return str ;
        }

        if(str.length()==1){
            //如果字符串str的长度为1，则调用专门把字符串转换为小写的string方法toLowerCase()
            return str.toLowerCase() ;
        }
        //用字符串截取方法subString()截取第一个字符并调用toLowerCase()方法把它转换为小写字母
        //再与从str第二个下标截取的字符串拼接
        return str.substring(0,1).toLowerCase()+str.substring(1) ;
    }
    public static String toUpperStartString(String str){
        //如果字符串str为null和""则返回原数据
        if (str==null||"".equals(str)){
            return str ;
        }

        if(str.length()==1){
            //如果字符串str的长度为1，则调用专门把字符串转换为大写的string方法tuUpperCase()
            return str.toUpperCase() ;
        }
        //用字符串截取方法subString()截取第一个字符并调用toUpperCase()方法把它转换为大写字母
        //再与从str第二个下标截取的字符串拼接
        return str.substring(0,1).toUpperCase()+str.substring(1) ;
    }
    public static Map<String,String> getMapValue(Configuration properties, String baseKey){
        Iterator<String> keys = properties.getKeys();
        Map<String,String> result=new HashMap<>();
        if(properties==null){
           return  result;
        }
        if(keys!=null){
            while(keys.hasNext()){
                String nextKey = keys.next();
                if(nextKey.contains(baseKey)){
                    result.put(nextKey.replace(baseKey,""),properties.getString(nextKey));
                }
            }
        }
        return result;
    }
    private  static Configuration getConfig() {
        try {
            PropertiesConfiguration propertiesConfiguration;
            return new PropertiesConfiguration("generatorTest.properties");
        } catch (ConfigurationException e) {
            throw new RuntimeException("获取配置文件失败，", e);
        }
    }
    public static void main(String[] args) {
        Configuration config = getConfig();
        Map<String, String> mapValue = getMapValue(config, "request.header.");
        System.out.println(mapValue);
    }
}

