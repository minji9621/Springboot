package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter //set메서드
@Getter //get메서드
@AllArgsConstructor //모든 매개변수가 있는 생성자
@NoArgsConstructor //기본생성자
public class User {
	private String name;
	private int age;
	
}
