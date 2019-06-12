package com.spring.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class SpringBoot01HelloworldQuickApplication {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName());
		SpringApplication.run(SpringBoot01HelloworldQuickApplication.class, args);
	}
}
