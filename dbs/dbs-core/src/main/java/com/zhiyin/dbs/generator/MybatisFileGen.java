package com.zhiyin.dbs.generator;

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
public class MybatisFileGen {

    public static void genByFilePath(String path) {
        try {
            List<String> warnings = new ArrayList<String>();
            boolean overwrite = false;
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(new File(path));
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    public static void main(String[] args) {
//        try {
//            List<String> warnings = new ArrayList<String>();
//            boolean overwrite = false;
//            ConfigurationParser cp = new ConfigurationParser(warnings);
//            Configuration config = cp.parseConfiguration(MybatisFileGen.class.getResourceAsStream( "/mysql-base-generator-config.xml"));
//            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
//            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
//            myBatisGenerator.generate(null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
