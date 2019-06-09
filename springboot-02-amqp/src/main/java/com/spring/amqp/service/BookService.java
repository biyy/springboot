package com.spring.amqp.service;

import com.spring.amqp.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author biyuyang
 */
@Service
public class BookService {

  /**
   * 只要消息队列"atguigu","atguigu.news"接收到消息,该方法都会执行
   * 注意:发送的消息体内容如果不是Book对象,无法完成属性赋值,但是仍然可以接收消息
   * @param book book
   */
  @RabbitListener(queues = {"atguigu","atguigu.news"})
  public void receive(Book book){
    System.out.println(book);
  }

  @RabbitListener(queues = {"atguigu.emps","gulixueyuan.news"})
  public void receive02(Message message){
    System.out.println(message.getBody());
    System.out.println(message.getMessageProperties());
  }
}
