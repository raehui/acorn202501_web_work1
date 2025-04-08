package com.example.spring17.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * @Target(ElementType.METHOD) => 메소드에 붙일 어노테이션이다 라고 선언
 * 
 * String value();
 * 
 * 위의 선언을 하면 아래와 같은 형식으로 @SocketMapping() 을 사용할 수 있다.
 * 
 * @SocketMapping(value = "/chat/send")
 * @SocketMapping("/chat/send")
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SocketMapping {
	String value();

}
