package com.example.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
@RequiredArgsConstructor   // 스프링 의존성 주입 규칙에 의해 questionRepository 객체가 자동으로 주입된다.
@Controller
public class QuestionController {
	
	private final QuestionService questionService;
	
	@GetMapping("/") //  http://localhost:8888/까지만 쓰고 싶을 때는 /만 붙이면 된다.
	public String root() {
		
		// /question/list 해당 URL 요청 메서드로 가라는 의미
		return "redirect:/question/list";

	}
	
	@GetMapping("/list") //  http://localhost:8888/question/list
	public String list(Model model) {
		List<Question> questionList = this.questionService.getList();
		model.addAttribute("questionList",questionList);
		return "question_list"; // 리턴값으로 templates(템플릿) 안의 question_list 불러온다.
	}
	
	@GetMapping(value="/detail/{id}")
	public String detail( Model model, @PathVariable("id") Integer id) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "question_detail";
	}
	
	@GetMapping("/create")
	public String create(){  //질문 목록에서 질문 등록 버튼을 눌렀을 때 
		return "question_form";
	}
	
	@PostMapping("/create") //질문 폼에서 등록 눌렀을 때
	public String create(@RequestParam("subject") String subject, @RequestParam("content") String content) {
		
		//~질문을 저장하는 코드~
		this.questionService.create(subject, content);
		
		return "redirect:/question/list"; // 질문 저장 후 질문 목록으로 이동
	}
}
