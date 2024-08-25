package com.ex.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ex.data.BoardDTO;
import com.ex.repository.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardMapper boardMapper = null;
	
	//글 작성
	public void insert(BoardDTO boardDTO) {
		boardMapper.boardInsert(boardDTO);
	}
	
	//글 목록
	public List<BoardDTO> boardList(){
		return boardMapper.boardList();
	}
	
	//해당 글번호를 눌렀을 때 나올 글 내용
	public BoardDTO boardContent(int num) {
		return boardMapper.boardNum(num);
	}
}
