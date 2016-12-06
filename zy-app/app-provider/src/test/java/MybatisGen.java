import com.zhiyin.dbs.generator.GenMybatisFlie;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hg on 2016/3/4.
 */
public class MybatisGen {

    public static void main(String[] args) {

        String path = GenMybatisFlie.genGeneratorConfig();

//        path = "generator-config-app-provider.xml";
        GenMybatisFlie.genMybatis(path);

    }
}
