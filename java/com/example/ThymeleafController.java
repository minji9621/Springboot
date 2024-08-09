package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;

@Controller
@RequestMapping("/th/*")  //http://localhost:8888/th/ -> 루트주소
public class ThymeleafController {
	
	//http://localhost:8888/th/ex01
	@GetMapping("ex01") //@RequestMapping("/th/*")
						//여기서 뒤쪽에 /를 붙여줬기 때문에 여기서는 안 붙여도 됨
	public String ex01(Model model) {
		model.addAttribute("message", "Hello Thymeleaf");
		return "ex01"; //ex01이라는 뷰파일로 이동한다는 의미
	}
	
	@GetMapping("ex02")
	public String ex02(Model model) {
		model.addAttribute("message", "<h1>Hello Thymeleaf</h1>");
		model.addAttribute("htmlContent", "<h1>Hello Thymeleaf</h1>");
		return "ex02";
	}
	
	@GetMapping("ex03")
	public String ex03(Model model) {
		return "ex03";
	}
	
	@GetMapping("ex04")
	public String ex04(Model model) {
		model.addAttribute("date", new Date());
		model.addAttribute("num", 100000);
		model.addAttribute("num2",0.12345);
		model.addAttribute("message","Hello Thymeleaf");
		
		List<String> list = new ArrayList<String>();
		list.add("java");
		list.add("html");
		list.add("jsp");
		list.add("spring");
		
		model.addAttribute("list",list);
		return "ex04";
	}
	
	@GetMapping("ex05")
	public String ex05(Model model) {
		model.addAttribute("data", "main");
		return "ex05";
	}
	
	@GetMapping("ex06")
	public String ex06(Model model, HttpSession session) {
		User user = new User(); //User.java 파일에 @NoArgsConstructor를 선언했기 때문에 사용가능함
		user.setAge(20);
		user.setName("타임리프");
		session.setAttribute("user", user);
		return "ex06";
	}
	
	@GetMapping("ex07")
	public String ex07() {
		return "super";
	}
	
	@GetMapping("ex08")
	public String ex08() { //타임리프에서의 param 사용
		return "ex08";
	}
	
	@GetMapping("ex09")
	public String ex09(HttpSession session) { //타임리프에서의 Session 사용
		session.setAttribute("sid", "springboot");
		return "ex09";
	}
	
	@GetMapping("ex10")
	public String ex10(Model model) {
		model.addAttribute("age", "20");
		return "ex10";
	}
	
	@GetMapping("ex11")
	public String ex11(Model model) {
		model.addAttribute("age", "20");
		return "ex11";
	}
	
	@GetMapping("ex12")
	public String ex12(Model model) {
		List<String> list = Arrays.asList("java","jsp","html","spring");
		
		List<User> userList = new ArrayList<User>();
		userList.add(new User("java",20));
		userList.add(new User("jsp",30));
		userList.add(new User("html",40));
		userList.add(new User("spring",50));
		
		model.addAttribute("list",list);
		model.addAttribute("userList",userList);
		
		return "ex12";
	}
	
	@GetMapping("ex13")
	public String ex13(Model model) {
		model.addAttribute("age","50");
		return "ex13";
	}
	
	@GetMapping("ex14")
	public String ex14(Model model) {
		model.addAttribute("image","default.jpg");
		return "ex14";
	}
	
	@GetMapping("ex15")
	public String ex15() {
		return "ex15";
	}
	
	@GetMapping("main")
	public String main() {
		return "main";
	}
}
	









