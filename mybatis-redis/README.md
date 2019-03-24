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

## SpringBoot集成Redis
### 配置文件中关于Redis的配置：
```xml
## Redis 配置
## Redis数据库索引（默认为0）
spring.redis.database=0
## Redis服务器地址
spring.redis.host=192.168.140.132
## Redis服务器连接端口
spring.redis.port=6379
## Redis服务器连接密码（默认为空）
spring.redis.password=
## 连接池最大连接数（使用负值表示没有限制）
spring.redis.jedis.pool.max-active=8
## 连接池最大阻塞等待时间（使用负值表示没有限制）
spring.redis.jedis.pool.max-wait=-1
## 连接池中的最大空闲连接
spring.redis.jedis.pool.max-idle=8
## 连接池中的最小空闲连接
spring.redis.jedis.pool.min-idle=0
## 连接超时时间（毫秒）
spring.redis.timeout=5000ms
```
### pom.xml中引入Redis依赖
```xml
<!-- Spring Boot Redis 依赖 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-redis</artifactId>
    <version>${spring-boot-starter-redis-version}</version>
</dependency>
```
项目中需要对redisTemplate进行序列化配置，否则存进redis的数据不好识别且容易获取不到，具体的配置类：
```java
package com.superj.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Auther: SuperJ
 * @Date: 2019/3/24 16:08
 * @Description:
 */
@Configuration
public class RedisConfig {

    /**
     * RedisTemplate配置
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        // 设置值（value）的序列化采用FastJsonRedisSerializer。
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        // 设置键（key）的序列化采用StringRedisSerializer。
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
```


