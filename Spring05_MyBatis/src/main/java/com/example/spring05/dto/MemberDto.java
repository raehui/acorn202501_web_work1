package com.example.spring05.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
	private int num;
	private String name;
	private String addr;
}
