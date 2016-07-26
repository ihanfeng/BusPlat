import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by wangqinghui on 2016/7/25.
 */
public class Proc {

    public static void main(String[] args) throws IOException {


        String UTF8_BOM = "\uFEFF";
        String base = "C:\\Users\\wangqinghui\\Desktop\\Rank\\src";
        String[] su = {"java"};
        Collection<File> files = FileUtils.listFiles(new File(base), su, true);
        for(File f : files){
            System.out.println( f.getPath());
                    String content = FileUtils.readFileToString(f,"utf-8");
        content = content.trim();
        if(content.startsWith(UTF8_BOM)){
            content = content.replace(UTF8_BOM,"");
//            f.s\\\
            String n = f.getPath().replace("Desktop\\Rank", "Desktop\\Rank3");
            FileUtils.writeStringToFile(new File(n),content);

        }

        }

//        File file = new File();
//        String content = FileUtils.readFileToString(file,"utf-8");
//        content = content.trim();
//        if(content.startsWith(UTF8_BOM))
//            content = content.replace(UTF8_BOM,"");

    }
}
