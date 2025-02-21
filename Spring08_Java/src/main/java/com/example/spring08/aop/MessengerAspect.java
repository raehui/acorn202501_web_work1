package com.example.spring08.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MessengerAspect {

	//..은 매개변수의 모양을 상관하지 않겠다.
	@Around("execution(void send*(..))") //적어주는 자체가 연결이네..
	public void checkGreeting(ProceedingJoinPoint joinpoint) throws Throwable {
		//joinpoint로 매개변수에 전달한 데이터를 배열에 담을 수 있다.
		Object[] args=joinpoint.getArgs();
		//반복문을 돌면서 매개변수에 담긴 값을들 하나하나 조사해 볼 수 있다.
		for(Object tmp:args) {
			//찾는 type 을 확인한다.
			if(tmp instanceof String) {
				//찾았다면 원래 type으로 casting 한다.
				String msg=(String)tmp;
				System.out.println("aspect 에서 읽어낸 내용: "+msg);
				if(msg.contains("마루")) {
					System.out.println("마루는 금지된 단어입니다.");
					return; //메소드를 여기서 끝내기
				}
			}
		}
		//이 메소드를 호출하는 시점에 실제로 aspect 가 적용된 메소드가 수행된다.(호출하지 않으면 수행안됨)
		joinpoint.proceed();
	}
}
