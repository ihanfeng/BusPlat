package com.zhiyin.dbs.generator;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangqinghui on 2016/11/11.
 */
@Slf4j
public class GenConfigFlie {

    public static String tableEntityMap() {
        Parameters params = new Parameters();

        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.properties()
                                .setFileName(GeneratorConstant.GenConfigProperName));
        try {
            Configuration config = builder.getConfiguration();
            List<String> mapperList = config.getList(String.class, "table.entity.mapper");
            mapperList = Optional.fromNullable(mapperList).or(new ArrayList<String>());

            log.info("table entity mapper size:",mapperList.size());

            if(mapperList.size() <= 0){
                return null;
            }


            List<String > appendList = Lists.newArrayList();
            for(String tmp : mapperList){
                String[] mp = tmp.trim().split(",");
                if(mp.length != 2){
                    log.error("table entity not valid,{}",tmp);
                    return null ;
                }
                appendList.add(" <table tableName=\""+mp[0]+"\" domainObjectName=\""+mp[1]+"\"/>");
            }
//            List<String> lines = FileUtils.readLines( new File("/mysql-base-generator-config.xml") );

            List<String> lines = IOUtils.readLines( GenConfigFlie.class.getResourceAsStream("/mysql-base-generator-config.xml") );
            lines.addAll(lines.size()-3,appendList);
            File saveFile = new File("generator-config-" + config.getString("project.name") + ".xml");
            log.info(saveFile.getAbsolutePath());
            FileUtils.writeLines(saveFile,lines);
            return saveFile.getAbsolutePath();
        } catch (ConfigurationException cex) {
            // loading of the configuration file failed
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }



    public static void main(String[] args) throws IOException {




    }
}
