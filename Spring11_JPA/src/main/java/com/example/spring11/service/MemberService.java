package com.example.spring11.service;

import java.util.List;

import com.example.spring11.dto.MemberDto;

public interface MemberService {
	public List<MemberDto> getAll();
	public void saveMember(MemberDto dto);
	public MemberDto getByNum(Integer num);
	
}
