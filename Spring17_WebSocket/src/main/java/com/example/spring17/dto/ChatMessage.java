package com.example.spring17.dto;

import lombok.Data;

@Data
public class ChatMessage {
	// React 에서 보낸 text 이름의 필드가 있어야 함.
	public String text;
	public String userName;
	public String toUserName;
}
