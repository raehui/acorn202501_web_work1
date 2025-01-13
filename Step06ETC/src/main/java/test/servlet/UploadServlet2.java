package test.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
@WebServlet("/file/upload2")
@MultipartConfig( //어노테이션 기반으로 class 파일이 새로 만들어짐
		fileSizeThreshold = 1024*1024*5, //메모리 임계값 , 메모리를 효율적으로 사용하기 위한 코드
		maxFileSize = 1024*1024*50, //최대 파일 사이즈 50mg byte
		maxRequestSize = 1024*1024*60 //최대 요청 사이즈
		)
public class UploadServlet2 extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//업로드될 실체 경로 얻어내기
		//환경이 달라질 수도 있으니 객체를 이용해서 얻어옴
		//업로드 파일까지의 실제 경로를 메소드를 통해서 얻어올 수 있음
		String uploadPath=this.getServletContext().getRealPath("/upload");
		File uploadDir=new File(uploadPath);
		//만일 upload 폴더가 존재하지 않으면 
		if(!uploadDir.exists()) {
			uploadDir.mkdir(); //실제로 폴더 만들기
		}
		
		String title=req.getParameter("title");
		
		//파일명이 겹치지 않게 저장하기 위한 랜덤한 문자열 얻어내기
		//겹치지 않는 특별한 문자열이 만들어짐
		String uid=UUID.randomUUID().toString();
		
		String orgfileName=null;
		String saveFileName=null;
		
		//파일 데이터 처리
		//input type의 name
		Part filePart=req.getPart("myImage");
		if(filePart !=null) {
			//원본 파일의 이름 얻어내기
			orgfileName=filePart.getSubmittedFileName();
			//저장될 파일의 이름 구성하기
			//파일명이 단 하나만 존재하기 위해 uid 붙임
			saveFileName=uid+orgfileName;
			//저장할 파일의 경로 구성하기
			//File.separator 환경에 맞게 알아서 나옴
			String filePath=uploadPath + File.separator+saveFileName;
			/*
			 * 업로드된 파일은 임시 폴더에 임시 파일로 저장이 된다.
			 * 해당 파일에서 byte 알갱이를 읽어 들일 수 있는 InputStream 객체를 얻어내고
			 */
			InputStream is=filePart.getInputStream();
			//원하는 목적지에 copy를 해야 한다
			Files.copy(is, Paths.get(filePath));		
		}
		
		//파일의 크기 얻어내기(큰 정수이기 때문에 long type 이다.)
		long fileSize=filePart.getSize();
		
		//응답에 필요한 데이터를 request 영역에 담기
		req.setAttribute("title", title);
		req.setAttribute("orgFileName", orgfileName);
		req.setAttribute("saveFileName", saveFileName);
		req.setAttribute("fileSize", fileSize);
		req.setAttribute("uploadPath", uploadPath);
		
		
		//jsp 페이지로 응답을 위임하기
		String cPath=req.getContextPath();
		RequestDispatcher rd=req.getRequestDispatcher("/file/upload2.jsp"); //응답을 위임을 할 때는 컨텍 경로를 붙이지 않음
		rd.forward(req, resp);
		
	}
}
