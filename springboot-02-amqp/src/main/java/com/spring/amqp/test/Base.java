package com.spring.amqp.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author biyuyang
 */
public abstract class Base {

  abstract public void myfunc();
  public void another(){
    System.out.println("Another method");
  }
}

class Abs extends Base{

  public static void main(String[] args) {
//    Abs abs = new Abs();
//    abs.amethod();
    //排序算法
    List<Integer> list = new ArrayList<>();
    list.add(3);
    list.add(5);
    list.add(2);
    list.add(7);

    int temp = 0;
    List<Integer> list2 = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {

      for (int j = 0; j < list.size() ; j++) {

      }
    }
    Collections.sort(list);
    System.out.println(list);
  }
  @Override
  public void myfunc() {
    System.out.println("My func");
  }
  public void amethod(){
    myfunc();
  }
}

class TestServer {

  public static void main(String[] args) {

    byte[] src,dst ;
//    dst = new String(src,"GBK").getBytes("UTF-8");
//    dst = new String("GBK",src).getBytes();

    int result = 0;
    int i = 2;
    switch (i){
      case 1:
        result = result + i;
      case 2:
        result = result + i*2;
      case 3:
        result = result + i*3;
    }
    //10
    System.out.println(result);
  }

  int users = 0;
  //静态方法中不能引用非静态变量
  static public void login() {
    //users += 1 ;
  }
}