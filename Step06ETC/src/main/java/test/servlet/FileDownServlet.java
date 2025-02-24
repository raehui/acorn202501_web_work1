package test.servlet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/file/download")
public class FileDownServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//다운로드 작업에 필요한 3가지 정보(원본파일명, 저장된 파일명, 파일의 크기) 얻어오기
		//지금은 파라미터로 전달되지만 실제로는 DB에 저장된 정보를 읽어와서 다운로드 해야한다.
		String orgFileName=req.getParameter("orgFileName");
		String saveFileName=req.getParameter("saveFileName");
		long fileSize=Long.parseLong(req.getParameter("fileSize"));
		
		// 응답 헤더 정보 설정
		//웹브라우저에게 파일을 전송하기 위해 미리 3가지 정보를 알려준다.
		//1. 콘텐트 타입, 파일을 전송할거야
		resp.setHeader("Content-Type", "application/octet-stream; charset=UTF-8");
		// 다운로드 시켜줄 파일명 인코딩
		String encodedName = URLEncoder.encode(orgFileName, "utf-8");
		// 파일명에 공백이있는 경우 처리
		encodedName = encodedName.replaceAll("\\+", " ");
		//헤더에게 파일명을 알려줘서 만들어라!
		//2. 파일명은 이거야 웹브라우저는 여기서 준 파일명으로 다운로드 받는다.
		resp.setHeader("Content-Disposition", "attachment;filename=" + encodedName);
		resp.setHeader("Content-Transfer-Encoding", "binary");
		
		//=> 헤더에게 다운로드할 파일의 정보를 알려주기

		// 다운로드할 파일의 크기
		//3. 파일의 크기는 이거야
		//잘 준비하고 받아!
		resp.setContentLengthLong(fileSize);

		// 다운로드 시켜줄 파일의 실제 경로
		//저장된 파일에 경로를 구성하기
		//업로드된 실제 파일의 경로 + | + saveFileName=uid+orgfileName
		
		//우리가 다운받는게 임시파일인가? 저장된 경로에서의 파일
		String path = getServletContext().getRealPath("/upload") + File.separator + saveFileName;
		
		 System.out.println(path);
		 
		//btey 알갱이를 읽어와서 클라이언트에게 출력 
		FileInputStream fis = null;
		BufferedOutputStream bos = null;
		try {
			// 파일에서 byte 을 읽어들일 객체
			fis = new FileInputStream(path);
			// 클라이언트에게 출력할수 있는 스트림 객체 얻어오기
			bos = new BufferedOutputStream(resp.getOutputStream());
			// 한번에 최대 1M byte 씩 읽어올수 있는 버퍼
			byte[] buffer = new byte[1024 * 1024];
			int readedByte = 0;
			// 반복문 돌면서 출력해주기
			while (true) {
				// byte[] 객체를 이용해서 파일에서 byte 알갱이 읽어오기
				readedByte = fis.read(buffer);
				if (readedByte == -1)
					break; // 더이상 읽을 데이터가 없다면 반복문 빠져 나오기
				// 읽은 만큼 출력하기
				bos.write(buffer, 0, readedByte);
				bos.flush(); // 출력
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null)
				fis.close();
			if (bos != null)
				bos.close();
		}
	}
}
