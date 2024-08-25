package com.ex.data;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BoardDTO {
	private int num; 			// 글번호
	private String writer;  	// 작성자
	private String title;   	// 글제목
	private String content; 	// 글내용
	private LocalDateTime reg; // 작성일시
}
