package com.example.spring05;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class Spring05MyBatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring05MyBatisApplication.class, args);
	}
	// 서버가 준비 되었을 때 실행할 메소드 설정
	@EventListener(ApplicationReadyEvent.class)
	public void openChrome() {
		String url = "http://localhost:9000/spring05/";
		// 운영체제의 이름을 소문자로 
		String os = System.getProperty("os.name").toLowerCase();
		ProcessBuilder builder = null;
		try {
			if (os.contains("win")) {
				builder = new ProcessBuilder("cmd.exe", "/c", "start chrome " + url);
			} else if (os.contains("mac")) {
				builder = new ProcessBuilder("/usr/bin/open", "-a", "Google chrome" + url);
			} else {
				System.out.println("지원 하지 않는 운영체제 입니다.");
				return;
			}
			builder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
