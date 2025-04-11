package com.example.spring17.dto;

import lombok.Data;

@Data
public class ChatMessage {
	// React 에서 보낸 text 이름의 필드가 있어야 함.
	private String text;
	private String userName;
	private String toUserName;
	private String saveFileName;
}
