


### ����jmx��IP

-Djava.rmi.server.hostname=127.0.0.1"


## server.xml

���ӳ����ã�
Ĭ��ֵ��

```
<!--
<Executor name="tomcatThreadPool" namePrefix="catalina-exec-"
  maxThreads="150" minSpareThreads="4"/>
-->
```

�޸�Ϊ��

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

�ص�������ͣ�
maxThreads����󲢷�����Ĭ������ 200��һ�㽨���� 500 ~ 800������Ӳ����ʩ��ҵ�����ж�
minSpareThreads��Tomcat ��ʼ��ʱ�������߳�����Ĭ������ 25
prestartminSpareThreads���� Tomcat ��ʼ����ʱ��ͳ�ʼ�� minSpareThreads �Ĳ���ֵ����������� true��minSpareThreads ��ֵ��ûɶЧ����
maxQueueSize�����ĵȴ���������������ܾ�����


### ���Ӳ������ã�
Ĭ��ֵ��

```
<Connector 
  port="8080" 
  protocol="HTTP/1.1" 
  connectionTimeout="20000" 
  redirectPort="8443" 
/>
```

�޸�Ϊ��

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

�ص�������ͣ�
protocol��Tomcat 8 ���� nio2 ���ã�org.apache.coyote.http11.Http11Nio2Protocol���������ò��ˣ����������Ǹ���
protocol��Tomcat 6��7 ���� nio ���ã�org.apache.coyote.http11.Http11NioProtocol
enableLookups������DNS��ѯ
acceptCount��ָ�������п���ʹ�õĴ���������߳�������ʹ��ʱ�����Էŵ���������е�����������������������󽫲��账��Ĭ������ 100
maxPostSize���� FORM URL ������ʽ�� POST �ύ��ʽ�������ύ���Ĵ�С��Ĭ���� 2097152(2��)����ʹ�õĵ�λ���ֽڡ�10485760 Ϊ 10M�����Ҫ�������ƣ����������Ϊ -1��
acceptorThreadCount�����ڽ������ӵ��̵߳�������Ĭ��ֵ��1��һ�����ָ��Ҫ�Ķ���ʱ������Ϊ�÷�������һ�����CPU������Ƕ�� CPU һ������Ϊ 2.





### ����AJP�˿�
AJP��Ϊ Tomcat �� HTTP ������֮��ͨ�Ŷ����Ƶ�Э�飬���ṩ�ϸߵ�ͨ���ٶȺ�Ч�ʡ����tomcatǰ�˷ŵ���apache��ʱ�򣬻�ʹ�õ�AJP������������������ǹ�˾ǰ������nginx���ķ��������˲�ʹ�ô��������������Ҫע��������������

```
<!-- <Connector port="8009" protocol="AJP/1.3" redirectPort="8443" /> -->
```

### �Զ�����

Ĭ�� Tomcat �ǿ����˶�war�����Ȳ���ġ�Ϊ�˷�ֹ��ֲ��ľ��ȶ�������������Ҫ�ر��Զ�����

�޸�ʵ����

```
<Host name="localhost"  appBase="" unpackWARs="false" autoDeploy="false">



## Jvm


�����ڴ������ 8G��һ�� PermSize ��������Ҫ��֤ϵͳ���ȶ��������У�
JAVA_OPTS="-Dfile.encoding=UTF-8 -server -Xms6144m -Xmx6144m -XX:NewSize=1024m -XX:MaxNewSize=2048m -XX:PermSize=512m -XX:MaxPermSize=512m -XX:MaxTenuringThreshold=10 -XX:NewRatio=2 -XX:+DisableExplicitGC"
�����ڴ������ 16G��һ�� PermSize ��������Ҫ��֤ϵͳ���ȶ��������У�
JAVA_OPTS="-Dfile.encoding=UTF-8 -server -Xms13312m -Xmx13312m -XX:NewSize=3072m -XX:MaxNewSize=4096m -XX:PermSize=512m -XX:MaxPermSize=512m -XX:MaxTenuringThreshold=10 -XX:NewRatio=2 -XX:+DisableExplicitGC"
�����ڴ������ 32G��һ�� PermSize ��������Ҫ��֤ϵͳ���ȶ��������У�
JAVA_OPTS="-Dfile.encoding=UTF-8 -server -Xms29696m -Xmx29696m -XX:NewSize=6144m -XX:MaxNewSize=9216m -XX:PermSize=1024m -XX:MaxPermSize=1024m -XX:MaxTenuringThreshold=10 -XX:NewRatio=2 -XX:+DisableExplicitGC"

�ģ��Թ�С��㣨�������ߣ�
ԭ�����ӣ�http://www.jianshu.com/p/c8613d17e5fe
����Ȩ���������У�ת������ϵ���߻����Ȩ������ע���������ߡ���

## �ο�
http://www.blogjava.net/Alpha/archive/2016/04/29/430290.

http://www.jianshu.com/p/c8613d17e5fe