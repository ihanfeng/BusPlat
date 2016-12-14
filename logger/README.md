
## logback 引入配置文件

http://www.importnew.com/22290.html

http://logback.qos.ch/manual/configuration.html#configFileProperty
http://blog.arganzheng.me/posts/logback-study.html

需要引入Jar

```
        <dependency>
            <groupId>org.codehaus.janino</groupId>
            <artifactId>janino</artifactId>
            <version>2.7.8</version>
        </dependency>
```



## springboot logback Profile配置
http://blog.csdn.net/catoop/article/details/50588851
http://blog.csdn.net/yingxiake/article/details/51276671
## logback文件包含

被导入文件 log-include.xml

```
<included>

  <!--日志存储目录-->
  <property name="LOG_DIR" value="/var/log/incito/" />
  <!--保留多少天的日志-->
  <property name="LOG_HIS_MAX" value="100" />

</included>

```

logback.xml

```
导入外部文件
<include file="/opt/incito/conf/logback-inc.xml"/> 
导入jar内文件
<include resource="org/springframework/boot/logging/logback/base.xml" /> 
```


## 解决src下xml等资源文件无法打包问题

在pom.xml添加如下信息
```
<build>
    <resources>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.xml</include>
            </includes>
        </resource>
    </resources>
</build>
```
http://blog.csdn.net/shifangwannian/article/details/48882201