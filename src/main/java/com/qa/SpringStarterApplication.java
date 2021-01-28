package com.qa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringStarterApplication {

	public static void main(String[] args) {
		ApplicationContext beanBag = SpringApplication.run(SpringStarterApplication.class, args);
		System.out.println("The current time is: " + beanBag.getBean("getTime", String.class));
	}
}
