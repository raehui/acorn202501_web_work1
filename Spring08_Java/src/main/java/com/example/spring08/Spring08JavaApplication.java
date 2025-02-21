package com.example.spring08;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.spring08.util.Messenger;
import com.example.spring08.util.WritingUtil;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class Spring08JavaApplication {
	
	@Autowired
	private WritingUtil util;
	
	@Autowired
	private Messenger messenger;
	
	
	@PostConstruct //spring frameowork 가 준비된 이후에 실행이 된다.
	public void testAop() {
		
		messenger.sendGreeting("안녕하세요!");
		messenger.sendGreeting("안녕 마루야!");
		
		String result=messenger.getMessage();
		System.out.println("result: "+result);
		
		util.writeLetter();
		util.writeReport();
		util.writeDiary();
		
	}

	public static void main(String[] args) {
		SpringApplication.run(Spring08JavaApplication.class, args);
		
		String pwd="1234";
		
		//비밀번호를 암호화 해주는 객체
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		//암호화된 비밀번호 얻어내기
		String encodedPwd= encoder.encode(pwd);
		//디코딩할 수 는 없지만 1234에서 파생된 사실을 알 수 는 있다.
		
		//결과를 콘솔창에
		System.out.println(pwd+" 를 암호화하면 "+encodedPwd);
		
		//날것의 비밀번호와 암호화된 비밀번호가 일치하는지 여부 알아내기
		//
		boolean same=BCrypt.checkpw("123", encodedPwd);
		System.out.println("일치하는지 여부: "+same);
		
		//of() 메소드로 만든 List 는 읽기전용(read only)이다.
		List<String> names=List.of("김구라","원숭이","해골");
		//names.add("주뎅이"); //동작하지 않는다.(예외발생)
		
		//List의 stream()의 메소드를 호출하면 Stream type 이 리턴된다.
		Stream<String> stream=names.stream();
		
		Function<String, String> f= (item) -> {
			return item+"님";
		};
		//위의 Function type 을 줄여서 쓰면 아래와 같다.
		Function<String, String> f2=item -> item+"님";
		
		//stream에서 map 사용은 단 한번만 실행 가능
		List<String> names2=stream.map(f).toList();
		System.out.println(names2);
		
		//단 한줄의 코딩으로 새로운 리스트 생성함
		//stream은 map 메서드를 사용하기 위해서 작성
		//리스트를 stream 타입으로 만들고 map 메서드를 사용함
		List<String> names3=names.stream().map(f2).toList();
		System.out.println(names3);
		
		List<String> names4=names.stream().map(item->item +"님").toList();
		
		List<Integer> nums = List.of(-10, 20, 30, -5, 25, -30);
		//양의 정수만 남긴 리스트
		List<Integer> newNums=nums.stream().filter(item -> item>0).toList();
		System.out.println(newNums);
		//양의 정수 2배
		List<Integer> newNums2=nums.stream().filter(item -> item>0).map(item->item*2).toList();
		System.out.println(newNums2);
		
		//nums 에서 양의 정수만 남기고 2배를 곱한 새로운 List 얻어내서 순서대로 해당 숫자를 콘솔창에 모두 출력하기
		nums.stream().filter(item->item>0).map(item->item*2).forEach(item->{
			System.out.println(item);
		});
		//문자열(String)이 들어 있는 List
		List<String> strNums= List.of("10","20","30","40","50");
		
		//위의 List를 활용해서 List<Integer>로 얻어내 보세요.
		List<Integer> intNums=strNums.stream().map(item->Integer.parseInt(item)).toList();
		System.out.println(intNums);
		// Integer 클래스가 가지고 있는 parseInt 메소드를 참조해서 map()함수에 전달해서 동일한 작업도 가능하다.
		List<Integer> intNums2=strNums.stream().map(Integer::parseInt).toList();
		System.out.println(intNums2);
		
		
		
		

	}
	
	

}
