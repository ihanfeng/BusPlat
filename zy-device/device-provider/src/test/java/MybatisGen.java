import com.zhiyin.dbs.generator.GenMybatisFlie;

/**
 * Created by hg on 2016/3/4.
 */
public class MybatisGen {

    public static void main(String[] args) {

        String path = GenMybatisFlie.genGeneratorConfig2();

//        path = "generator-config-app-provider.xml";
        GenMybatisFlie.genMybatis(path);

    }
}
