package xxs.gen.gentest.velocity.utils;

public class StringResolverUtils {
    public static    String replaceKey(String pre,String post,String content,String replaceKey,String replaceValue){
        if(pre==null||pre==""||post==null||post==""||replaceKey==null||replaceValue ==null||content==null){
            return content;
        }
        int preIndex = content.indexOf(pre);

        if(preIndex==-1){
            return content;
        }
        StringBuilder result = new StringBuilder(content);
         while (preIndex!=-1){
             int postIndex=result.indexOf(post,preIndex+pre.length());
             if(postIndex==-1){
                 break;
             }
             String key=result.substring(preIndex+pre.length(),postIndex);

             if(key.equals(replaceKey)){
                 result.replace(preIndex,postIndex+post.length(),replaceValue);
                 /*这里不用 pre+pre.length() 如果不匹配，那么会死循环*/
                 preIndex = result.indexOf(pre, preIndex);
                 continue;
             }
             /*这里不用 pre+pre.length() 如果不匹配，那么会死循环*/
             preIndex = result.indexOf(pre, preIndex+pre.length());
         }
        return result.toString();
    }

    public static void main(String[] args) {
        String id = replaceKey("${", "}", "111{id}${id}{id}{i2d}1111", "id", "1");
        System.out.println(id);

    }
}
