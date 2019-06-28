# 72-Java如何使用全局异常处理?.md

```
1.配置依赖：编辑pom.xml文件
springMVC的依赖   日志包的依赖   校验包的依赖 注意不要少包：aop context tx aspect expression  core web webMVC

2.编写Controller：商品管理模块的控制器


3.定义商品实体类，并在ProductController中定义修改商品信息的方法

public String updateProduct(Product product)
{
	if(product.getId()==100)//把这种情况当做这个商品已经不存在了
	{
		throw new CustomException("商品已经不存在了");
	}else
	{
		//修改商品信息
		System.out.println("修改商品信息");
		return "";
	}
}

4.自定义异常类型

5.定义统一的异常处理器ExceptionResolver
注意给类加注解：@ControllerAdvice  
给方法加注解：@ExceptionHandler

修改：springMVC.xml文件，把全包扫描的包名改成：net.neuedu.demo,这样handler包也会扫描到

6.配置并测试
先请求：http://localhost:8080/pay/product/udpateProduct.do?productId=10 正常
再请求：http://localhost:8080/pay/product/udpateProduct.do?productId=100 会抛出异常并处理异常

```
1. 新建一个ProductController类

2. 配置pom.xml 导包

```
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.alibaba</groupId>
  <artifactId>pay</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>war</packaging>
  
  <!-- 怎么导包？ -->
	<dependencies>
		<!-- https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api -->
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>javax.servlet-api</artifactId>
			<version>3.1.0</version>
			<scope>provided</scope>
		</dependency>
		<!-- <dependency> <groupId>com.oracle</groupId> <artifactId>ojdbc6</artifactId> 
			<version>11.1.0.7.0</version> </dependency> -->
		<!-- https://mvnrepository.com/artifact/org.codehaus.jackson/jackson-core-asl -->
		<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations -->
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-annotations</artifactId>
			<version>2.9.8</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-databind</artifactId>
			<version>2.9.8</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core -->
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-core</artifactId>
			<version>2.9.8</version>
		</dependency>


		<dependency>
			<groupId>jstl</groupId>
			<artifactId>jstl</artifactId>
			<version>1.2</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-beans</artifactId>
			<version>4.3.10.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>4.3.10.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-aop</artifactId>
			<version>4.3.10.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-aspects</artifactId>
			<version>4.3.10.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-tx</artifactId>
			<version>4.3.10.RELEASE</version>
		</dependency>

		<dependency>
			<groupId>commons-io</groupId>
			<artifactId>commons-io</artifactId>
			<version>1.3.2</version>
		</dependency>
		<dependency>
			<groupId>commons-fileupload</groupId>
			<artifactId>commons-fileupload</artifactId>
			<version>1.3.1</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jdbc</artifactId>
			<version>4.3.10.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>4.3.10.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-web</artifactId>
			<version>4.3.10.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-expression</artifactId>
			<version>4.3.10.RELEASE</version>
		</dependency>
		
		<dependency>
			<groupId>com.oracle</groupId>
			<artifactId>ojdbc6</artifactId>
			<version>11.2.0.1.0</version>
		</dependency>
	</dependencies>
	
	<!-- 配置build环境 -->
   <build>
       <plugins>
          <plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.2</version>
				<configuration>
					<source>1.7</source>
					<target>1.7</target>
					<encoding>utf-8</encoding>
				</configuration>
			</plugin>
			<plugin>
				<groupId>org.apache.tomcat.maven</groupId>
				<artifactId>tomcat7-maven-plugin</artifactId>
				<version>2.2</version>
				<configuration>
					<port>8080</port>
					<path>/pay</path>
				</configuration>
			</plugin>      
       </plugins>
  </build> 
</project>
```


