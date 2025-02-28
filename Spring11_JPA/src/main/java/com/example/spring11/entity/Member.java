package com.example.spring11.entity;

import com.example.spring11.dto.MemberDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "MEMBER_INFO") // @data를 작성하면 충돌 발생으로 작성 금지
public class Member {
	@Id //프라이머리 키를 자동으로 생성
	@GeneratedValue(strategy = GenerationType.AUTO) //번호를 따로 놓지않아도 자동 삽입
	private Integer num;
	private String name;
	private String addr;
	
	//dto 를 entity 로 변환하는 static 메소드
	public static Member toEntity(MemberDto dto) {
		return Member.builder()
				.num(dto.getNum())
				.name(dto.getName())
				.addr(dto.getAddr())
				.build();
	}
	
	
	
}
