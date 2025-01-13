package test.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/friends")
public class FriendsServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//DB에서 읽어온 친구 목록이라고 가정하자
		List<String>names=new ArrayList<>();
		names.add("김구라");
		names.add("해골");
		names.add("원숭이");
		names.add("주뎅이");
		
		//names 객체에 들어 있는 친구목록을 ul ,li 요소를 이용해서 출력해 보세요.
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
			pw.println("<h1>친구 목록</h1>");
			pw.println("<ul>");
				pw.println("<li>"+names.get(0)+"</li>");
				pw.println("<li>"+names.get(1)+"</li>");
				pw.println("<li>"+names.get(2)+"</li>");
			pw.println("</ul>");	
			
			pw.println("<h1>친구 목록2</h1>");
			pw.println("<ul>");
			for(int i=0;i<names.size();i++) {
				pw.println("<li>"+ names.get(i)+"</li>");
			}
			pw.println("</ul>");	
			
			pw.println("<h1>친구 목록3</h1>");
			pw.println("<ul>");
			for(String tmp:names) { //names 리스트 안에 있는 tmp 임시 변수에 요소를 담음
				pw.println("<li>"+ tmp+"</li>");						
				}
			pw.println("</ul>");
		
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
		
	}
}
