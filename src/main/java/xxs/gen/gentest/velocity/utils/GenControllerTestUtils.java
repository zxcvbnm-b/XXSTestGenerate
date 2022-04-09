package xxs.gen.gentest.velocity.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import xxs.gen.gentest.service.entity.VelocityGenOneTestDTO;

import java.io.*;
import java.util.*;

/*单元测试 模板生成类*/
public class GenControllerTestUtils {

    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<String>();
        templates.add("template/WebMvcTest.java.vm");
        return templates;
    }
    /**
     * 生成代码
     * absoluteDir:绝对路径文件夹
     */
    public static void generatorCode(VelocityGenOneTestDTO velocityGenOneTestDTO, String absoluteDir) {

        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
        //封装模板数据
        Map<String, Object> map = new HashMap<>();
        map.put("mockRequestEntities",velocityGenOneTestDTO.getMockControllerMethodRequestEntities());
        map.put("className",velocityGenOneTestDTO.getClassName());
        map.put("packageName",velocityGenOneTestDTO.getPackageName());
        VelocityContext context = new VelocityContext(map);

        //获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);
            try {
                String fileName = getFileName(template, velocityGenOneTestDTO.getClassName(), velocityGenOneTestDTO.getPackageName(), "");
                File file = new File(absoluteDir+fileName);
                /*把模板写入文件*/
                writeFile(file,sw.toString(),"UTF-8");
            } catch (IOException e) {
            }
        }
    }
    private static void writeFile(File file, String content, String fileEncoding) throws IOException {
        String path = file.getPath();
         path = path.substring(0, path.lastIndexOf("\\"));
        File file2=new File(path);/*要有文件夹才能生成文件*/
        file2.mkdirs();
        FileOutputStream fos = new FileOutputStream(file, false);
        OutputStreamWriter osw;
        if (fileEncoding == null) {
            osw = new OutputStreamWriter(fos);
        } else {
            osw = new OutputStreamWriter(fos, fileEncoding);
        }

        try (BufferedWriter bw = new BufferedWriter(osw)) {
            bw.write(content);
        }
    }
    /**
     * 获取文件名
     */
    public static String getFileName(String template, String className, String packageName, String moduleName) {
        String packagePath = File.separator+"src"+File.separator+"test" + File.separator + "java" + File.separator;
        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator + moduleName + File.separator;
        }
        if (template.contains("WebMvcTest.java.vm")) {
            return packagePath + File.separator +splitInnerName(className)+ "Test.java";
        }

        return null;
    }
    private static String splitInnerName(String name){
          name = name.replaceAll("\\.","_");
          return name;
    }

    public static void main(String[] args) {
        String file="F:\\codemain\\test\\io\\renren\\modules\\front\\controller\\CustomerMasterControllerTest.java";
        String substring = file.substring(0, file.lastIndexOf("\\"));
        System.out.println(substring);
    }
}
