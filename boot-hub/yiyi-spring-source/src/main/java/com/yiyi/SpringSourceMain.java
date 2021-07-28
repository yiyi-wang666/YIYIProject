package com.yiyi;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringSourceMain {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("test.xml");
    System.out.println( ctx.getBean("testbean"));
  }

}
