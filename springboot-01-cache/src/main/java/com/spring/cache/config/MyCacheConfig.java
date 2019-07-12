package com.spring.cache.config;

import java.util.Arrays;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author biyuyang
 *
 * 缓存的自定义配置
 */
@Configuration
public class MyCacheConfig {

  /**
   * 自定义KeyGenerator的生成策略
   * @return KeyGenerator
   */
  @Bean(value = "myKeyGenerator")
  public KeyGenerator keyGenerator(){
    //使用Lambda表达式的写法
    return (target, method, params) -> method.getName()+"["+ Arrays.asList(params).toString() +"]";
  }

}
