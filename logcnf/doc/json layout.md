

## log4j json layout



https://github.com/michaeltandy/log4j-json


## logback json layout

logback提供了JSON的相关包
https://github.com/qos-ch/logback-contrib/wiki/JSON

Demo
http://qiita.com/ephemeralsnow/items/10d362286aaa55120822



   <dependency>
            <groupId>ch.qos.logback.contrib</groupId>
            <artifactId>logback-json-classic</artifactId>
            <version>${logback.json}</version>
            <!--<optional>true</optional>-->
        </dependency>
        <dependency>
            <groupId>ch.qos.logback.contrib</groupId>
            <artifactId>logback-jackson</artifactId>
            <version>${logback.json}</version>
            <scope>runtime</scope>
        </dependency>
        
         <dependency>
                    <groupId>com.fasterxml.jackson.core</groupId>
                    <artifactId>jackson-core</artifactId>
                    <version>2.5.2</version>
                    <optional>true</optional>
                </dependency>
                <dependency>
                    <groupId>com.fasterxml.jackson.core</groupId>
                    <artifactId>jackson-databind</artifactId>
                    <version>2.5.2</version>
                    <optional>true</optional>
                </dependency>
                <dependency>
                    <groupId>com.fasterxml.jackson.core</groupId>
                    <artifactId>jackson-annotations</artifactId>
                    <version>2.5.2</version>
                    <optional>true</optional>
                </dependency>