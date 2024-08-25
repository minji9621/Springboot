package com.ex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ex.data.BoardDTO;
import com.ex.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService = new BoardService();
	
	@GetMapping("/writeForm")
	public String writeForm(BoardDTO boardDTO) {
		return "board/writeForm";
	}
	
	@PostMapping("/writePro")
	public String writePro(BoardDTO boardDTO) {
		boardService.insert(boardDTO);
		return "board/writePro";
	}
	
	@GetMapping("list")
	public String list(Model model) {
		List<BoardDTO> list = boardService.boardList();
		model.addAttribute("list", list);
		return "board/list";
	}
	
	@GetMapping("content")
	public String content(Model model, @RequestParam("num") int num) {
		model.addAttribute("boardDTO" , boardService.boardContent(num));
		return "board/content";
	}
}
