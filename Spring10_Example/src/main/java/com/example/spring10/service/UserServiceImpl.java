package com.example.spring10.service;

import java.io.File;
import java.security.Security;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring10.dto.UserDto;
import com.example.spring10.exception.PasswordException;
import com.example.spring10.repository.UserDao;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired private UserDao dao;
	// SecurityConfig 클래스에 @Bean 설정으로 bean 이된 PasswordEncoder 객체 주입 받기
	@Autowired private PasswordEncoder encoder;
	//업로드된 이미지를 저장할 위치 얻어내기
	@Value("${file.location}") private String fileLocation;
	
	@Override
	public UserDto getByNum(long num) {
		
		
		return dao.getData(num);
	}

	@Override
	public UserDto getByUserName(String userName) {
		
		return dao.getData(userName);
	}

	@Override
	public void createUser(UserDto dto) {
		//저장할 때 비밀번호를 암호화 한 후에 저장 되도록 한다.
		String encodedPwd=encoder.encode(dto.getPassword());
		//인코딩된 비밀번호를 다시 dto 객체에 넣어주고
		dto.setPassword(encodedPwd);
		//DB에 저장한단.
		int rowCount=dao.insert(dto);
		if(rowCount==0) {
			throw new RuntimeException("회원정보 저장 실패");
		}
		
	}

	@Override
	public void updateUserInfo(UserDto dto) {
		
		// MultipartFile 객체
		MultipartFile image = dto.getProfileFile();

		// 만일 파일이 업로드 되지 않았다면
		if (!image.isEmpty()) {

			// 원본파일명
			String orgFileName = image.getOriginalFilename();
			// 이미지의 확장자를 유지하기 위해 뒤에 원본 파일명을 추가한다.
			// 이미지라는 정보를 유지하기 위해서 orfFileName을 뒤에 추가함
			String saveFileName = UUID.randomUUID().toString() + orgFileName;
			// 저장할 파일의 전체 경로 구성하기
			String filePath = fileLocation + File.separator + saveFileName;
			try {
				// 업로드 파일을 저장할 파일 객체 생성
				File savefile = new File(filePath);
				image.transferTo(savefile);

			} catch (Exception e) {
				e.printStackTrace();
			}
			//UserDto 에 저장된 이미지의 이름을 넣어준다.
			dto.setProfileImage(saveFileName);

		}
		//로그인된 userName 도 dto 에 담아준다.
		String userName=SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setUserName(userName);
		
		//dao를 이용해서 수정반영한다.
		int rowCount=dao.update(dto);
		

	}

	@Override
	public void changePassword(UserDto dto) {
		// 비밀번호를 제대로 입력했는지 비교하기 위해서 DB의 암호화 비밀번호랑 입력한 비밀번호 일치를 비교하기
		
		//1. 로그인된 userName 을 얻어온다.
		String userName=SecurityContextHolder.getContext().getAuthentication().getName();
		//2. 기존의 비밀번호를 DB에서 읽어와서(암호화된 비밀번호)
		String encodedPwd=dao.getData(userName).getPassword();
		//3. 입력한 (암호화 되지 않은 구비밀먼호)와 일치하는지 비교 해서
		// .checkpw(암호화 되지 않은 비밀번호, 암호화된 비밀번호)
		boolean isValid=BCrypt.checkpw(dto.getPassword(), encodedPwd);
		//4. 만일 일치하지 않다면 Exception 을 발생시킨다.
		if (!isValid) {
			throw new PasswordException("기존 비밀번호가 일치하지 않습니다!");
		}
		// 5. 일치하면 새비밀번호를 암호화해서 dto에 담은 다음
		dto.setNewPassword(encoder.encode(dto.getNewPassword()));
		// 6. userName 도 dto 에 담고
		dto.setUserName(userName);
		// 7. DB에 비밀번호 수정반영를 한다.
		int rowCount = dao.updatePassword(dto);
	}

}
