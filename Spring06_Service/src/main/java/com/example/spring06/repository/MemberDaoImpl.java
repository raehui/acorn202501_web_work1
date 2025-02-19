package com.example.spring06.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spring06.dto.MemberDto;

//dao를 bean 으로 만들기 위한 어노테이션
//sqlexception을 발생하면 dataaccess exception을 발생시켜서 DB 관련 에러는 따로 ExceptionController에서 관리한다.
@Repository
public class MemberDaoImpl implements MemberDao {
	
	//MyBatis 기반으로 DB 관련 작업을 하기 위한 핵심 의존객체를 DI 받는다.
	@Autowired
	private SqlSession session;
	
	@Override
	public List<MemberDto> getList() {
		/*
		 * SqlSession 객체를 이용해서 회원 목록을 얻어온다.
		 * 따로 작성할 xml문서로 불러와줘.
		 * session.selectList로 받아온 데이터 타입은 list type(selectList)
		 */
		List<MemberDto> list = session.selectList("member.getList");
		
		return list;
	}

	@Override
	public void insert(MemberDto dto) {
		// TODO Auto-generated method stub
		/*
		 * Mapper 의 namespace : member
		 * sql의 id : insert
		 * parameter type : MemberDto 
		 * 
		 * 파라미터의 타입이 여러개면 dto 혹은 Map , 즉 여기서는 name, addr이 담겨있다.
		 */
		session.insert("member.insert",dto);
		
	}

	@Override
	public int update(MemberDto dto) {
		/*
		 * Mapper 의 namespace : member
		 * sql의 id : update
		 * parameter type : MemberDto 
		 */
		//수정 반영하고 수정반영된 row 의 갯수를 리턴한다.(성공이면 1, 실패면 0)
		return session.update("member.update",dto);
	}

	@Override
	public int delete(int num) {
		/*
		 * Mapper 의 namespace : member
		 * sql의 id : delete
		 * parameter type : num
		 */
		
		//수정 반영하고 수정반영된 row 의 갯수를 리턴한다.(성공이면 1, 실패면 0)
		return session.delete("member.delete",num);
	}

	@Override
	public MemberDto getData(int num) {
		/*
		 * 리턴되는 값이 데이터 타입이 된다. (selectOne)
		 */
		//삭제하고 삭제된 row 의 갯수를 리턴한다.(성공이면 1, 실패면 0)
		MemberDto dto=session.selectOne("member.getData",num); 
		return dto;
	}

}
