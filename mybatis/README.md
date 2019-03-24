##  SpringBoot集成Mybatis

#### 遇到的一些问题：
-	IDE无法自动装载mapper：再mapper接口添加@Repository注解
-	启动项目后MySql发出SSL警告：配置Mysql关闭SSL

```java
spring.datasource.url=jdbc:mysql://localhost/chapter5?useUnicode=true&characterEncoding=utf8&useSSL=false
```
### Mybatis公共代码是通过mybatis的生成工具自动生成，具体配置如下
```xml
<?xml version="1.0" encoding="UTF-8"?>  
<!DOCTYPE generatorConfiguration  
  PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"  
  "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">  
<generatorConfiguration>  
<!-- 数据库驱动-->  
    <classPathEntry  location="mysql-connector-java-5.1.25-bin.jar"/>  
    <context id="DB2Tables"  targetRuntime="MyBatis3">  
        <commentGenerator>  
            <property name="suppressDate" value="true"/>  
            <!-- 是否生成注释 -->  
            <property name="suppressAllComments" value="true"/>  
        </commentGenerator>  
        <!--数据库链接地址账号密码 -->  
        <jdbcConnection driverClass="com.mysql.jdbc.Driver" connectionURL="jdbc:mysql://localhost/chapter5" userId="root" password="8781288aa">  
        </jdbcConnection>  
        <javaTypeResolver>  
            <property name="forceBigDecimals" value="false"/>  
        </javaTypeResolver>  
        <!-- 生成pojo类存放位置-->  
        <javaModelGenerator targetPackage="com.superj.mybatis.pojo" targetProject="src">  
            <property name="enableSubPackages" value="true"/>  
            <property name="trimStrings" value="true"/>  
        </javaModelGenerator>  
        <!-- 生成映射文件存放位置-->  
        <sqlMapGenerator targetPackage="mybatis-mappers" targetProject="src">  
            <property name="enableSubPackages" value="true"/>  
        </sqlMapGenerator>  
        <!-- 生成Dao类存放位置-->  
        <javaClientGenerator type="XMLMAPPER" targetPackage="com.superj.mybatis.dao" targetProject="src">  
            <property name="enableSubPackages" value="true"/>  
        </javaClientGenerator>  
        <!-- 生成对应表及类名 -->  
        <table tableName="t_user" domainObjectName="User" enableCountByExample="false" enableUpdateByExample="false" enableDeleteByExample="false" enableSelectByExample="false" selectByExampleQueryId="false"></table>
    </context>  
</generatorConfiguration>  
```
