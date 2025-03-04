package com.example.spring11.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring11.dto.MemberDto;
import com.example.spring11.entity.Member;
import com.example.spring11.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired private MemberRepository repo;

	@Override
	public List<MemberDto> getAll() {
		// Member entity 의 목록
		// List<Member> entityList = repo.findAll();
		
		//추가한 메소드를 이용해서 num에 대해서 내림차순 정렬된 목록을 얻어낼 수 있음
		List<Member> entityList = repo.findAllByOrderByNumDesc();
		
		/*
		 * //MemberDto의 목록으로 만들어서 리턴해야 한다. 
		 * List<MemberDto> list = new ArrayList<>();
		 * //반복문 돌면서 Member 객체를 순서대로 참조 
		 * for(Member tmp:entityList) {
		 * 		list.add(MemberDto.toDto(tmp)); 
		 * }
		 */
		
		// stream() 을 이용하면 한 줄의 coding 으로 위의 동작을 할수가 있다.
		// List<MemberDto> list = entityList.stream().map(item -> MemberDto.toDto(item).toList());
		List<MemberDto> list = entityList.stream().map(MemberDto::toDto).toList();

		return list;

	}

	@Override
	public void saveMember(MemberDto dto) {
		// dto를 Entity 로 변경해서 저장한다.
		repo.save(Member.toEntity(dto));
		
	}

	/*
	 * @Override public MemberDto getByNum(Integer num) { Member
	 * member=repo.getById(num);
	 * 
	 * // 내가 아이디로 가져온 entity를 dto에 담아야 함. MemberDto dto= MemberDto.toDto(member);
	 * return dto; }
	 */

//	@Override
//	public void update(MemberDto dto) {
//		// 기존의 값 가져오기
//		Member member=repo.getById(dto.getNum());
//		
//		// 수정한 dto를 기존의 entity로 변경
//		member.setName(dto.getName());
//		member.setAddr(dto.getAddr());
//		System.out.println(dto);
//		// DB에 저장하기
//		repo.save(member);
//		
//	}

	/*
	 * @Override public void delete(Integer num) { repo.deleteById(num);
	 * 
	 * }
	 */

	@Override
	public void deleteMember(Integer num) {
		// TODO Auto-generated method stub
		repo.deleteById(num);
		
	}

	@Override
	public MemberDto getMember(Integer num) {
		// id를 이용해서 Member Entity type 을 얻어낸다.
		Member member=repo.findById(num).get();
		
		// Entity 를 dto로 변경해서 리턴한다.
		return MemberDto.toDto(member);
	}

	@Override
	public void updateMember(MemberDto dto) {
		// MemberDto 를 Entity 로 변경해서 save(수정) 한다.
		repo.save(Member.toEntity(dto));
		
	}

}
