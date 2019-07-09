package com.spring.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 一.环境搭建
 *  1.导入数据库文件(file中)
 *  2.创建实体类
 *  3.整合Mybatis操作数据库
 *    1.在docker容器中部署mysql数据库
 *    2.配置数据源信息,并使用默认的hikari数据源
 *    3.使用注解版的mybatis
 *      1.@MapperScan指定需要扫描的mapper接口的包
 *      2.也可以在mapper接口上添加@Mapper注解(不推荐,mapper接口过多的话,不方便)
 * 二.快速体验缓存
 *  1.开启基于注解的缓存
 *    @EnableCaching
 *  2.在方法上标注缓存注解即可
 *    @Cacheable: 主要针对方法进行配置,能够根据方法的参数进行结果缓存
 *    @CacheEvict: 清空缓存
 *    @CachePut: 保证方法被调用,又希望结果被缓存
 *
 */
@MapperScan("com.spring.cache.mapper")
@EnableCaching
@SpringBootApplication
public class Springboot01CacheApplication {

  public static void main(String[] args) {
    SpringApplication.run(Springboot01CacheApplication.class, args);
  }

}
