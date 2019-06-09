package com.spring.amqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author byy
 * 1.RabbitAutoConfiguration
 *    1.是一个配置类
 *    2.容器中必须有RabbitTemplate和Channel实例
 *    3.配置属性在RabbitProperties类中,可以通过spring.rabbitmq前缀进行修改
 *    4.向容器中导入了RabbitAnnotationDrivenConfiguration类,其必须在有注解@EnableRabbit注解才可以使用
 *    5.自动配置了连接工厂ConnectionFactory
 *    6.RabbitTemplate:给RabbitMQ发送和接收消息(自动注入了;可以手动配置替代自动配置的RabbitTemplate)
 *    7.AmqpAdmin:RabbitMQ系统管理组件
 *      创建和删除Exchange,Binding,Queue
 * 2.@EnableRabbit+@RabbitListener 监听消息队列中的内容
 */
@EnableRabbit //开启基于注解的Rabbit功能
@SpringBootApplication
public class Springboot02AmqpApplication {

  public static void main(String[] args) {
    SpringApplication.run(Springboot02AmqpApplication.class, args);
  }

}
