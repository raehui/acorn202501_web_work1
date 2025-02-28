package com.example.spring11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.spring11.entity.Member;
import com.example.spring11.repository.MemberRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class Spring11JpaApplication {
	
	@Autowired MemberRepository memberRepo;
	
	@PostConstruct
	public void memberTest() {
		//DB에 저장할 Member entity 객체를 생성해서
		Member m1=Member.builder().name("김구라").addr("노량진").build();
		Member m2=Member.builder().name("해골").addr("행신동").build();
		Member m3=Member.builder().name("원숭이").addr("분당").build();
		//DB에 저장하기
		memberRepo.save(m1);
		memberRepo.save(m2);
		memberRepo.save(m3);
		
		
		
	}

	public static void main(String[] args) {
		SpringApplication.run(Spring11JpaApplication.class, args);
	}

}
