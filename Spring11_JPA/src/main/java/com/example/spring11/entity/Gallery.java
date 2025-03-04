package com.example.spring11.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
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
@Entity // 이름을 따로 지정하지 않으면 만들어지는 테이블은 class 명과 동일? 동일!
public class Gallery {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String writer;
	private String title;
	private Date createdAt;
	
	// Entity 를 영속화(insert) 하기 직전에 뭔가 작업할 게 있으면 @PrePersist  어노테이션을 활용하면 된다.
	// @PreUpdate 는 수정(update) 하기 직전에 호출된다.
	@PrePersist
	public void onPersist() {
		// 오라클에서 데이터를 넣을 때 SYSDATE 함수를 이용해서 넣은 효과를 낸다.
		createdAt=new Date();
	}
}
