package com.spring.cache.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author biyuyang
 */
@Configuration
public class MyBatisConfig {

  //springboot2.0以后,使用的数据源默认是HikariDataSource,不需要配置了
//  @Bean
//  public DataSource dataSource(){
//    return new HikariDataSource();
//  }
//  @Bean
//  public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
//    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//    factoryBean.setDataSource(dataSource());
//    return factoryBean;
//  }
//

  /**
   * 定制化mybatis的属性信息
   *  开启驼峰命名
   *  延迟加载
   *  ...
   * @return
   */
  @Bean
  public ConfigurationCustomizer configurationCustomizer(){
    return configuration -> {
      configuration.setMapUnderscoreToCamelCase(true);
//        configuration.set...
    };
  }
}
