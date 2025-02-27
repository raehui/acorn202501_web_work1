package com.example.spring10.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.spring10.dto.FileDto;
import com.example.spring10.dto.FileListDto;
import com.example.spring10.repository.FileDao;
import com.example.spring10.repository.PostDao;
@Service
public class FileServiceImpl implements FileService {
	
	// 한 페이지에 몇개씩 표시할 것인지
	final int PAGE_ROW_COUNT = 5;
	// 하단 페이지를 몇개씩 표시할 것인지
	final int PAGE_DISPLAY_COUNT = 5;
	
	
	@Autowired private FileDao fileDao;

	@Override
	public long createFile(FileDto dto) {
		// 파일 업로드 번호
		long num=fileDao.getSequence();
		dto.setNum(num);
		// 파일 업로드자 dto
		String uploader=SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setUploader(uploader);
		// 파일 원래 이름, 저장이름, 사이즈는 cotroller 에서 담김
		fileDao.insert(dto);		
		
		
		return num;
	}

	@Override
	public FileListDto getFiles(int pageNum, FileDto search) {
		// 보여줄 페이지의 시작 ROWNUM
		int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_COUNT;
		// 보여줄 페이지의 끝 ROWNUM
		int endRowNum = pageNum * PAGE_ROW_COUNT;

		// 하단 시작 페이지 번호
		int startPageNum = 1 + ((pageNum - 1) / PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
		// 하단 끝 페이지 번호
		int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;
		// 전체 글의 갯수
		int totalRow =fileDao.getCount(search);
		// 전체 페이지의 갯수 구하기
		int totalPageCount = (int) Math.ceil(totalRow / (double) PAGE_ROW_COUNT);
		// 끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
		if (endPageNum > totalPageCount) {
			endPageNum = totalPageCount; // 보정해 준다.
		}

		// startRowNum 과 endRowNum 을 PostDto 객체에 담아서
		search.setStartRowNum(startRowNum);
		search.setEndRowNum(endRowNum);
		
		List<FileDto> list=fileDao.getList(search);
		
		String findQuery="";
		if(search.getKeyword() != null) {
			findQuery="&keyword="+search.getKeyword()+"&condition="+search.getCondition();
		}
		
		//파일 목록 페이지에 필요한 정보를 모두 FileListDto 에 담기
		FileListDto dto=FileListDto.builder()
				.list(list)
				.startPageNum(startPageNum)
				.endPageNum(endPageNum)
				.totalPageCount(totalPageCount)
				.pageNum(pageNum)
				.totalRow(totalRow)
				.findQuery(findQuery)
				.condition(search.getCondition())
				.keyword(search.getKeyword())
				.build();

		
		return dto;
	}

}
