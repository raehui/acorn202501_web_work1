package com.example.spring18;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value="classpath:custom.properties")
@SpringBootApplication
public class Spring18DockerTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring18DockerTestApplication.class, args);	
		/*
		 * System 클래스의 .getenv() 메소드를 이용하면 시스템 환경변수의 값을 얻어낼 수 있다.
		 */
		String javaHome=System.getenv("JAVA_HOME");
		System.out.println("JAVA_HOME:" + javaHome);
	}

}
