package com.spring.amqp.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author biyuyang
 */
@Configuration
public class MyAMQPConfig {

  /**
   * 自定义消息转换器类型
   *  使用Jackson2JsonMessageConverter代替默认的SimpleMessageConverter
   *  将任意对象类型转换为json格式存储到消息队列Queue中
   * @return MessageConverter
   */
  @Bean
  public MessageConverter messageConverter(){
    return new Jackson2JsonMessageConverter();
  }
}
