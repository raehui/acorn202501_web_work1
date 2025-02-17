package com.example.springp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TodoDto {
	private int num;
	private String title;
	private String content;

}
