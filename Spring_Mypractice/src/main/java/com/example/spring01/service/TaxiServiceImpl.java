package com.example.spring01.service;

import org.springframework.stereotype.Component;

@Component
public class TaxiServiceImpl implements TaxiService {
	
	@Override
	public void call(String name) {
		System.out.println(name+" 이 콜을 잡았습니다.");
		
	}

	@Override
	public void go(String name) {
		System.out.println(name+" 이 주행을 합니다.");
		
	}

	@Override
	public void cash(String name) {
		System.out.println(name+" 이 현금을 받았습니다.");
		
	}

	@Override
	public void card(String name) {
		// TODO Auto-generated method stub
		
	}

}
