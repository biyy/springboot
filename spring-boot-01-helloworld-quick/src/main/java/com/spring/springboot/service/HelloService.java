package com.spring.springboot.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author biyuyang
 */
@Service
public class HelloService {

  @Transactional
  public String testTrans(){
    System.out.println("service: "+Thread.currentThread().getName());
    System.out.println(Transactional.class);
    return null;
  }
}
