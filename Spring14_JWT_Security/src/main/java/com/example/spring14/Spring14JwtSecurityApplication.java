package com.example.spring14;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
@PropertySource(value= "classpath:custom.properties")
@SpringBootApplication
public class Spring14JwtSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring14JwtSecurityApplication.class, args);
	}

}
