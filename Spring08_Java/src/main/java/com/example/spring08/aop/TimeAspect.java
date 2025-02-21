package com.example.spring08.aop;

import java.util.Date;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/*
 * 횡단 관심사를 따로 클래스를 만들어서 작성한다.
 */
@Aspect //관심사 @Aspect 어노테이션을 이용해서 만든다 .
@Component //bean 으로 만들기 위해
public class TimeAspect {
	
	//이런 모양의 메서드가 실행되기 직전
	@Before("execution(void write*())")
	public void start() {
			Date start=new Date();
			long startTime=start.getTime();
			System.out.println("시작 시간: " + startTime);
		}
	//이런 모양의 메서드가 실행된 이후
	@After("execution(void write*())")
	public void end() {
			Date end=new Date();
			long endTime=end.getTime();
			System.out.println("종료 시간: " + endTime);
		}
	
	
}
