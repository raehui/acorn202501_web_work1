package com.example.spring06.dto;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
@Alias("todoDto")

@Getter
@Setter
public class TodoDto {
	private int id;
	private String content;
	private String regdate;

}
