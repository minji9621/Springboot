package com.example;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {  	//DTO(Data Transfer Object)
	private String id; 				//아이디
	private String pw; 				//비번
	private String email; 			//이메일
	private String gender; 			//성별
	private String country; 		//도시
	private List<String> hobbies; 	//취미
}
