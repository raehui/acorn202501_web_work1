package com.example.spring12.entity;

import com.example.spring12.dto.PostDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/*
 * http://localhost:9000/h2-console 로 접속을 하면
 * 만들어진 DB 테이블을 확인할 수 있다.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Post {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String title;
	private String author;
	
	//dto를 entity 로 변환해서 리턴하는 static 메서드 만들기
	public static Post toEntity(PostDto dto) {
		/*
		 * Entity 로 변환할 때 dto 에 숫자가 0 인 경우에 null 을 넣어주어야 한다.
		 * 
		 * .JpaRepository 의 .save() 메소드는 insert 와 update 가 겸용이다.
		 *  - id 칼럼이 null 이면 insert 를 시도하고
		 *  - id 칼럼이 null 이 아니면 update 를 시도한다.
		 */
		return Post.builder()
				.id(dto.getId()==0 ? null: dto.getId())
				.title(dto.getTitle())
				.author(dto.getAuthor())
				.build();
	}
}
