package com.example.spring01;

import java.net.ContentHandler;
import java.security.Provider.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.spring01.service.TaxiService;

import ch.qos.logback.core.Context;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class SpringMypracticeApplication {
	@Autowired
	TaxiService service;
	
	@PostConstruct
	public void exec() {
		service.call("래희님");
	}
	
	public static void main(String[] args) {
		ApplicationContext bean=SpringApplication.run(SpringMypracticeApplication.class, args);
		
		TaxiService service2=bean.getBean(TaxiService.class);
		
		service2.go("이제훈");
		
	}
	
}
