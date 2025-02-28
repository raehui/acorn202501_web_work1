package com.example.spring11.dto;

import com.example.spring11.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDto {
	private Integer num; // int로 하면 기본값이 0으로 들어가기에 
	private String name;
	private String addr;
	
	//entity를 dto를 변환하는 static 메소드
	public static MemberDto toDto(Member entity) {
		//매개변수에 전달되는 Member entity 객체에 담긴 내용을 이요해서 MemberDto 객체를 만들어서 리턴해 준다.
		return MemberDto.builder()
				//.num(entity.getNum() == 0 ? null : dto.getName()) 기본값으로 0으로 들어가면 수정으로 시도함,  null이어야만 insert 시도
				.num(entity.getNum())
				.name(entity.getName())
				.addr(entity.getAddr())
				.build();
	}

}
