package com.example.spring06.dto;

import org.apache.ibatis.type.Alias;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
/*
 * type 에 별칭을 부여하고 Mapper xml 문서에서 별칭을 이용해서 parameterType 과 resultType 설정을 할 수 잇다.
 */
@Alias("memberDto")
@Getter
@Setter
public class MemberDto {
	private int num;
	private String name;
	private String addr;
}
