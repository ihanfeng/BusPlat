
引入maven

		<!-- spring fox start -->
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>2.0.2</version>
		</dependency>
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>2.0.2</version>
		</dependency>

		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-annotations</artifactId>
			<version>2.4.4</version>
		</dependency>
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-databind</artifactId>
			<version>2.4.4</version>
		</dependency>
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-core</artifactId>
			<version>2.4.4</version>
		</dependency>
		<!-- spring fox end -->
		

配置spring
	如果自己下载swagger-ui,并放到webapp目录下，添加
	<mvc:resources location="/swagger-ui/" mapping="/swagger-ui/**"/> 
访问界面
http://localhost:8080/webdemo/swagger-ui/index.html
 使用java配置swagger-ui.jar
http://localhost:8080/webdemo/swagger-ui.html
API
http://localhost:8080/webdemo/v2/api-docs
