package com.ex.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ex.data.BoardDTO;


@Repository
@Mapper
public interface BoardMapper {
	//글 작성
	public int boardInsert(BoardDTO BoardDTO);
	//글 목록
	public List<BoardDTO> boardList();
	//해당 글번호로 내용 조회
	public BoardDTO boardNum(int num);
}
