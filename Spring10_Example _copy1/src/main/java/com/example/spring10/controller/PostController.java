package com.example.spring10.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring10.dto.CommentDto;
import com.example.spring10.dto.CommentListRequest;
import com.example.spring10.dto.PostDto;
import com.example.spring10.dto.PostListDto;
import com.example.spring10.service.PostService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PostController {
	
	@Autowired private PostService service;
	
	@PostMapping("/post/update-comment")
	@ResponseBody //문자열은 그대로, map 혹은 dto는 json으로 변경해서 자동으로 응답한다. list 는 제너릭에 따라서 알아서 담아줌
	public Map<String, Boolean> updateComment(CommentDto dto){
		service.updateComment(dto);
		return Map.of("isSuccess", true);
	}
	
	@GetMapping("/post/delete-comment")
	@ResponseBody
	public Map<String, Boolean> deleteComment(long num){
		
		service.deleteComment(num);
		// @ResponseBody 어노테이션을 붙여 놓고 아래의 데이터를 리턴하면 {"isSuccess" : true} 형식의 json
		// 문자열이 응답된다.
		return Map.of("isSuccess",true);
	}
	
	//댓글 리스트를 보여주는 요청
	@GetMapping("/post/comment-list")
	@ResponseBody //view.html에서 fetch 요청에 사용되기에 응답받기 원하는 view.html 에서 json으로 받기위해서
	public Map<String, Object> commentList(CommentListRequest clr){
		//CommentListRequest 객체에는 댓글의 pageNum과 글번호 postNum이 들어 있다.
		
		return service.getComments(clr);
	}
	
	
	//댓글 저장 요청처리
	@PostMapping("/post/save-comment")
	@ResponseBody //dto 에 저장된 내용을 json 으로 응답하기 위한 어노테이션 ?
	// dto에는 content, postNum, tatgetWriter, parentNum ?이 담겨있는데 
	//원글의 댓글인지 대댓글인지는 parentNum 이 0인지 아닌지에 따라서 알 수 있음
	public CommentDto saveComment(CommentDto dto) {		
		service.createComment(dto);
		return dto;
	}
	
	//글 삭제 요청 처리
	@GetMapping("/post/delete")
	public String delete(long num) {
		service.deletePost(num);
		return "post/delete";
	}
	
	//글 수정 반영 요청처리
	@PostMapping("/post/update")
	public String update(PostDto dto, RedirectAttributes ra) {
		service.updatePost(dto);
		/*
		 *  RedirectAttributes 객체에 FlashAttribute 로 담은 내용은
		 *  redirect 이동된 요청을 처리하는 곳의 Model 객체에 자동으로 담긴다.
		 */
		ra.addFlashAttribute("updateMessage", dto.getNum()+" 번 글을 수정했습니다.");
		//수정 반영후 글 자세히 보기로 이동 
		return "redirect:/post/view?num="+dto.getNum();
	}
	
	//글 수정 폼 요청처리
	@GetMapping("/post/edit")
	public String edit(long num, Model model) {
		//수정할 글정보를 얻어와서 Model 객체에 담는다.
		PostDto dto=service.getByNum(num);
		model.addAttribute("dto", dto);
		return "post/edit";
	}
	
	@GetMapping("/post/new")
	public String newForm() {
		return "post/new";
	}
	
	@PostMapping("/post/save")
	//알아서 객체를 넣어준다.
	public String save(PostDto dto, RedirectAttributes ra){
		//새글을 저장하고 글번호를 리턴 받는다.
		long num=service.createPost(dto);
		ra.addFlashAttribute("saveMessage", "글을 성공적으로 저장했습니다.");
		//글 자세히 보기로 리다일렉트 이동하라는 응답하기
		return "redirect:/post/view?num="+num;
	}
	/*
	 *  dto 에 글번호만 있는 경우도 있고 
	 *  검색과 관련된 정보도 같이 있을수 있다. 
	 */
	@GetMapping("/post/view")
	public String view(PostDto dto, Model model, HttpSession session) {
		
		PostDto resultDto=service.getDetail(dto);
		model.addAttribute("postDto", resultDto);
		
		//새로 작성한 글이 아닌 경우에만 조회수 관련 처리를 한다. 
		if(model.getAttribute("saveMessage") == null) {
			//조회수 관련 처리를 한다.
			String sessionId=session.getId();
			service.manageViewCount(dto.getNum(), sessionId);
		}
		
		return "post/view";
	}
	
	/*
	 *  pageNum 이 파라미터로 넘어오지 않으면 pageNum 의 default 값은 1 로 설정 
	 *  검색 키워드도 파라미터로 넘어오면 PostDto 의 condition 과 keyword 가 null 이 아니다 
	 *  검색 키워드가 넘어오지않으면 PostDto 의 condition 과 keyword 는 null 이다
	 *   
	 */
	@GetMapping("/post/list")
	//컨디션, 키워드 파라미터가 PostDto 에 담겨셔 search 명으로 사용 가능
	public String list(@RequestParam(defaultValue = "1") int pageNum, PostDto search, Model model) {
		PostListDto dto=service.getPosts(pageNum, search);
		model.addAttribute("dto", dto);
		return "post/list";
	}
}






