package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/*
 * 요청 경로를 작성할 때 주의점!
 * 
 * 1. context path 는 생략한다.
 * 2. 반드시  /로 시작한다.
 */
@WebServlet("/fortune") 
public class FortuneServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//오늘의 운서를 어떤 로직에 의해서 얻어왔다고 가정하자
		String fortuneToday="동쪽으로 가면 귀인을 만나요!";
		
		//응답 인코딩 설정
		resp.setCharacterEncoding("utf-8");
		//응답 컨텐트 설정
		//응답할 내용을 미리 알려줘야 함 "text.html 형식을 응답할거고,,,,"
		resp.setContentType("text/html; charset=utf-8");
		// 요청을 한 클라이언트에게 문자열을 출력할 수 잇는 객체 
		PrintWriter pw = resp.getWriter();
		pw.println("<!doctype html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset='utf-8'>");
		pw.println("<title></title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<p>오늘의 운세: <strong>"+fortuneToday+"</strong></p>");	
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
	
		
	}
	
	
	
}
