package com.spring.amqp;

import com.spring.amqp.bean.Book;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot02AmqpApplicationTests {

  @Autowired
  RabbitTemplate rabbitTemplate;

  /**
   * 1.点播(点对点)
   */
  @Test
  public void contextLoads() {
    //底层方法
    //Message自定义,消息体和消息头信息
    //rabbitTemplate.send(exchange,routingKey,message);

    //封装后调用send()方法
    //object默认当成消息体,只需要传入要发送的对象,自动序列化发送给rabbitmq
    //rabbitTemplate.convertAndSend(exchange,routingKey,object);
//    rabbitTemplate.convertAndSend("exchange.direct","atguig","第一次发送消息");

    Map<String,String> map = new HashMap<>();
    map.put("biyy","今年25了");
    map.put("skk","今年26了");
    rabbitTemplate.convertAndSend("exchange.direct","atguigu",map);
    //对象被默认序列化以后发送出去(使用的消息转换器默认是SimpleMessageConverter)
    //接收到的消息: rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAACdAADc2trdAAL
    //5LuK5bm0MjbkuoZ0AARiaXl5dAAL5LuK5bm0MjXkuoZ4

    //自定义消息转换器Jackson2JsonMessageConverter
    //{"skk":"今年26了","biyy":"今年25了"}
  }

  @Test
  public void testConsumer(){
      //无法转为对象
//    Message message = rabbitTemplate.receive("atguigu");
//    byte[] body = message.getBody();
//    String str = new String(body,0,body.length);
//    System.out.println(str);
    //可以获取到对象(不管是哪种消息转换器,都可以获取到)
    Object object = rabbitTemplate.receiveAndConvert("atguigu");
    if(null != object){
      System.out.println(object.getClass());
      System.out.println(object);
    }
  }

  /**
   * 广播模式发送(一对多)
   *  不需要指定路由key了
   */
  @Test
  public void testSendBook(){
    Book book = new Book();
    book.setId(1L);
    book.setName("解忧杂货店");
    book.setPrice(100L);
    rabbitTemplate.convertAndSend("exchange.fanout","atguigu",book);
  }

  /**
   * 可以从队列中获取消息
   */
  @Test
  public void testConsumeBook(){
    Object atguigu = rabbitTemplate.receiveAndConvert("atguigu");
    if(null != atguigu){
      System.out.println("atguigu------");
      System.out.println(atguigu.getClass());
      System.out.println(atguigu);
    }
    Object atguigu1 = rabbitTemplate.receiveAndConvert("atguigu.news");
    if(null != atguigu1){
      System.out.println("atguigu.news------");
      System.out.println(atguigu1.getClass());
      System.out.println(atguigu1);
    }

    Object atguigu2 = rabbitTemplate.receiveAndConvert("atguigu.emps");
    if(null != atguigu2){
      System.out.println("atguigu.emps------");
      System.out.println(atguigu2.getClass());
      System.out.println(atguigu2);
    }

    Object atguigu3 = rabbitTemplate.receiveAndConvert("gulixueyuan.news");
    if(null != atguigu3){
      System.out.println("gulixueyuan.news------");
      System.out.println(atguigu3.getClass());
      System.out.println(atguigu3);
    }
  }

  @Autowired
  RabbitAdmin rabbitAdmin;

  /**
   * RabbitAdmin RabbitMQ系统管理组件
   *  创建和删除Exchange,Binding,Queue
   *  declare: 创建三者
   *  delete: 删除Exchange,Queue
   *  remove: 移除绑定Binding
   */
  @Test
  public void testDeclare(){
    //DirectExchange FanoutExchange CustomExchange TopicExchange HeadersExchange
    //DirectExchange exchange = new DirectExchange("rabbitAdmin.exchange");
    //rabbitAdmin.declareExchange(exchange);

    //rabbitAdmin.declareQueue(new Queue("rabbitAdmin.queue",true));

    rabbitAdmin.declareBinding(new Binding("rabbitAdmin.queue", DestinationType.QUEUE,"rabbitAdmin.exchange","test",null));
  }

  @Test
  public void testDelete(){
    rabbitAdmin.deleteExchange("rabbitAdmin.exchange");
    rabbitAdmin.deleteQueue("rabbitAdmin.queue");
    rabbitAdmin.removeBinding(new Binding("rabbitAdmin.queue", DestinationType.QUEUE,"rabbitAdmin.exchange","test",null));
  }

  @Test
  public void test1(){
    int i = 111;
    int b = 13;
    System.out.println(i%b);
  }
  @Test
  public void test2(){
    int x = 5;
    int y = 4;
    y = x--;
    System.out.println(y);
    System.out.println(x);
  }
}
