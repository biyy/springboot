package com.spring.springboot.controller;


import com.spring.springboot.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//这个类的所有方法返回的数据直接写给浏览器，（如果是对象转为json数据）
/*@ResponseBody
@Controller*/
@RestController
public class HelloController {

    @Autowired
    HelloService helloService;
    @RequestMapping("/test")
    public String testTrans(){
        String s = helloService.testTrans();
        System.out.println("controller: "+Thread.currentThread().getName());
        return null;
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello world quick!";
    }

    // RESTAPI的方式
}
