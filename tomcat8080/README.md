


### 设置jmx绑定IP

-Djava.rmi.server.hostname=127.0.0.1"


## server.xml

连接池配置：
默认值：

```
<!--
<Executor name="tomcatThreadPool" namePrefix="catalina-exec-"
  maxThreads="150" minSpareThreads="4"/>
-->
```

修改为：

```
<Executor 
  name="tomcatThreadPool" 
  namePrefix="catalina-exec-"
  maxThreads="500" 
  minSpareThreads="100" 
  prestartminSpareThreads = "true"
  maxQueueSize = "100"
/>
```

重点参数解释：
maxThreads，最大并发数，默认设置 200，一般建议在 500 ~ 800，根据硬件设施和业务来判断
minSpareThreads，Tomcat 初始化时创建的线程数，默认设置 25
prestartminSpareThreads，在 Tomcat 初始化的时候就初始化 minSpareThreads 的参数值，如果不等于 true，minSpareThreads 的值就没啥效果了
maxQueueSize，最大的等待队列数，超过则拒绝请求


### 链接参数配置：
默认值：

```
<Connector 
  port="8080" 
  protocol="HTTP/1.1" 
  connectionTimeout="20000" 
  redirectPort="8443" 
/>
```

修改为：

```
<Connector 
 executor="tomcatThreadPool"
 port="8080" 
 protocol="org.apache.coyote.http11.Http11Nio2Protocol" 
 connectionTimeout="20000" 
 maxConnections="10000" 
 redirectPort="8443" 
 enableLookups="false" 
 acceptCount="100" 
 maxPostSize="10485760" 
 compression="on" 
 disableUploadTimeout="true" 
 compressionMinSize="2048" 
 acceptorThreadCount="2" 
 compressableMimeType="text/html,text/xml,text/plain,text/css,text/javascript,application/javascript" 
 URIEncoding="utf-8"
/>
```

重点参数解释：
protocol，Tomcat 8 设置 nio2 更好：org.apache.coyote.http11.Http11Nio2Protocol（如果这个用不了，就用下面那个）
protocol，Tomcat 6、7 设置 nio 更好：org.apache.coyote.http11.Http11NioProtocol
enableLookups，禁用DNS查询
acceptCount，指定当所有可以使用的处理请求的线程数都被使用时，可以放到处理队列中的请求数，超过这个数的请求将不予处理，默认设置 100
maxPostSize，以 FORM URL 参数方式的 POST 提交方式，限制提交最大的大小，默认是 2097152(2兆)，它使用的单位是字节。10485760 为 10M。如果要禁用限制，则可以设置为 -1。
acceptorThreadCount，用于接收连接的线程的数量，默认值是1。一般这个指需要改动的时候是因为该服务器是一个多核CPU，如果是多核 CPU 一般配置为 2.





### 管理AJP端口
AJP是为 Tomcat 与 HTTP 服务器之间通信而定制的协议，能提供较高的通信速度和效率。如果tomcat前端放的是apache的时候，会使用到AJP这个连接器。由于我们公司前端是由nginx做的反向代理，因此不使用此连接器，因此需要注销掉该连接器。

```
<!-- <Connector port="8009" protocol="AJP/1.3" redirectPort="8443" /> -->
```

### 自动部署

默认 Tomcat 是开启了对war包的热部署的。为了防止被植入木马等恶意程序，因此我们要关闭自动部署。

修改实例：

```
<Host name="localhost"  appBase="" unpackWARs="false" autoDeploy="false">



## Jvm


机子内存如果是 8G，一般 PermSize 配置是主要保证系统能稳定起来就行：
JAVA_OPTS="-Dfile.encoding=UTF-8 -server -Xms6144m -Xmx6144m -XX:NewSize=1024m -XX:MaxNewSize=2048m -XX:PermSize=512m -XX:MaxPermSize=512m -XX:MaxTenuringThreshold=10 -XX:NewRatio=2 -XX:+DisableExplicitGC"
机子内存如果是 16G，一般 PermSize 配置是主要保证系统能稳定起来就行：
JAVA_OPTS="-Dfile.encoding=UTF-8 -server -Xms13312m -Xmx13312m -XX:NewSize=3072m -XX:MaxNewSize=4096m -XX:PermSize=512m -XX:MaxPermSize=512m -XX:MaxTenuringThreshold=10 -XX:NewRatio=2 -XX:+DisableExplicitGC"
机子内存如果是 32G，一般 PermSize 配置是主要保证系统能稳定起来就行：
JAVA_OPTS="-Dfile.encoding=UTF-8 -server -Xms29696m -Xmx29696m -XX:NewSize=6144m -XX:MaxNewSize=9216m -XX:PermSize=1024m -XX:MaxPermSize=1024m -XX:MaxTenuringThreshold=10 -XX:NewRatio=2 -XX:+DisableExplicitGC"

文／乖乖小恒恒（简书作者）
原文链接：http://www.jianshu.com/p/c8613d17e5fe
著作权归作者所有，转载请联系作者获得授权，并标注“简书作者”。

## 参考
http://www.blogjava.net/Alpha/archive/2016/04/29/430290.

http://www.jianshu.com/p/c8613d17e5fe