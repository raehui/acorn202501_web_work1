package test.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import test.member.dto.MemberDto;
@WebServlet("/member")
public class MemberServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//회원 한명의 정보를  DB에서 읽어왔다고 가정하자
		MemberDto dto=new MemberDto(1, "김구라","노량진");
		
		//회원 한명의 정보를 담고있는 MemberDto 객체를 request scope 에 "dto"라는 키값으도 담기 1
		request.setAttribute("dto", dto);
		
		//"test/member.jsp" 페이지로 응답을 위임시켜서 회원 한명의 정보를 응답하도록 프로그램이 해보기. 2
		RequestDispatcher rd=request.getRequestDispatcher("/test/member.jsp");
		rd.forward(request, response);
		
		//webapp/test/member.jsp 페이지를 만들고 거기에서 회원 한명의 정보를 응답해보세요!
		
	}
}
