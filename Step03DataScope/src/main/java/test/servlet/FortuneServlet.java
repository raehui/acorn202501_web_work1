package test.servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/fortune")
public class FortuneServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//오늘의 운세를 얻어오는 비즈니스 로직을 수행(DB에서 읽어왔다고 가정)
		String fortune="동쪽으로 가면 귀인을 만나요";
		//흠,,,여기서 html 형식으로 오늘의 운세를 응답하려면 입력한게 너무..많음
		//webapp/에 어딘가 있는 jsp 페이지에게 대신 응답하라고
		//응답을 위임 할 수가 있다.
		//대신 응답에 필요한 데이터(운세)는 request scope 에 담아서 전달해야 한다.
		
		//복잡한 작업(=)은 servlet가! request의 메소드에 담고! 전달 응답만 jsp!
		
		//오늘의 운세를 
		//setAttribute는 key(문자열) 와 value(object)로 쌍으로 담는다.
		//"fortuneToday"라는 키값으로 String tyep 담기
		
		//requestScope 에 내용을 담아서 jsp페이지로 전달해서 접속의 범위를 넓힌다.
		request.setAttribute("fortuneToday", fortune);
		
		//응답은 jsp 페이지에 위임한다(forward 이동)
		//.getRequestDispatcher("응답을 위임할 jsp 페이지의 위치")
		//사용하기 편하게 변수에 담았음
		RequestDispatcher rd=request.getRequestDispatcher("/test/fortune.jsp"); 
		//그러면 jsp 페이지에서 (String)request.getAttribute("fortuneToday") => ()안의 타입에 따라 (string....변경)
		//매개 객체를 갖고 페이지로 이동해라
		rd.forward(request, response);
		
	}
	
}
