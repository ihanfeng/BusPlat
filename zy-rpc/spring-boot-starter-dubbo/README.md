#spring-boot-start-dubbo

Dubbo是阿里开发的一套分布式通讯框架,Spring-boot是业界比较火的微服务框架，两者可以进行结合实现分布式微服务
* 对于提供外部的服务，可以使用spring-boot的rest服务,可以结合增强的dubbox,支持只注册dubbo服务,但不发布服务.
同时也支持消费springboot对外提供的rest服务.git地址: https://git.oschina.net/wuyu15255872976/dubbox.git
* 对于内部远程Rpc调用，可以借用Dubbo能力，达到服务治理的目的

##如何发布Dubbo服务
* 在Spring Boot项目的pom.xml中添加以下依赖:
```
 <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-dubbo</artifactId>
         <version>1.3.6.SNAPSHOT</version>
 </dependency>
 
 <!--依赖于容器-->
 <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-web</artifactId>
         <version>1.3.6.RELEASE</version>
 </dependency>
 
 ```
* 在application.properties添加Dubbo的版本信息和客户端超时信息,如下:
```
#dubbo produce
spring.dubbo.application.name=comment-provider
spring.dubbo.registry.protocol=zookeeper
spring.dubbo.registry.address=monkey:2181,127.0.0.1:2181
spring.dubbo.protocol.name=duubo
spring.dubbo.protocol.port=20880
spring.dubbo.scan=com.vcg.comment.service
spring.dubbo.protocol.host=发布的hostname


在Spring Application的application.properties中添加spring.dubbo.scan即可支持Dubbo服务发布,其中scan表示要扫描的package目录
```
* spring boot启动
```

    @SpringBootApplication
    public class Application {

        public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
        }

```
* 编写你的Dubbo服务,只需要添加要发布的服务实现上添加 @Service ,如下:

```
    @Service(version = "1.0.0")
    public class CommentServiceImpl implements CommentService {

        @Override
        public String test() {
            return "hello";
        }
    }

    如果你不喜欢Dubbo的@Service注解,而是喜欢原生的Spring @Service注解,可以采用以下方式对外发布服务
    @Configurable
    public class BeanConfiguration {
        @Bean
        public ServiceBean<CommentService> commentServiceServiceBean(CommentService commentService) {
            ServiceBean<CommentService> serviceBean = new ServiceBean<>();
            serviceBean.setInterface(CommentService.class);
            serviceBean.setRef(commentService);
            return serviceBean;
        }
    }
```

##如何引用Dubbo服务
* 在Spring Boot项目的pom.xml中添加以下依赖:

```

    <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-dubbo</artifactId>
         <version>1.3.5.SNAPSHOT</version>
    </dependency>

 ```

* 在application.properties添加Dubbo的版本信息和客户端超时信息,如下:

```

    #dubbo consumer
    spring.dubbo.application.name=comment-consumer
    spring.dubbo.registry.protocol=zookeeper
    spring.dubbo.registry.address=monkey:2181,127.0.0.1:2181
    spring.dubbo.scan=com.vcg
    在Spring Application的application.properties中添加spring.dubbo.scan即可支持Dubbo服务发布,其中scan表示要扫描的package目录

```

* spring boot启动
```

    @SpringBootApplication
    public class Application {
        public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
        }
    }

```

* 引用Dubbo服务,只需要添加要发布的服务实现上添加 @Reference ,如下:

```

    @Component
    public class UserController {

        @Reference(version = "1.0.0")
        private CommentService commentService;
    }

```

* 如果你不喜欢@Reference注入服务,而是用@Autowired可以采用以下方式.

```
    @Configurable
    public class BeanConfiguration {
        @Bean
        public ReferenceBean<CommentService> commentService(){
            ReferenceBean<CommentService> commentServiceBean=new ReferenceBean<>();
            commentServiceBean.setInterface(CommentService.class);
            return commentServiceBean;
        }
    }

```

* 引用Dubbo服务,引用以上服务:

```
    @Component
    public class UserController {

        @Autowired
        private CommentService commentService;

    }
```


