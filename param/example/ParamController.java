package com.example;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/param/*") // http://localhost:8888/param/
@Log4j2
public class ParamController {
	/*
	 * 스프링에서 사용되는 로그 레벨 TRACE - 가장 상세한 로그 레벨 DEBUG - 디버깅 목적으로 상세한 내용 출력 INFO - 일반적인
	 * 정보를 기록(기본값) WARN - 경고 수준의 정보를 기록 ERROR - 오류 발생 기록 FATAL - 치명적인 오류 발생 기록 로그
	 * 레벨의 위험도 순 TRACE < DEBUG < INFO < WARN < ERROR < FATAL
	 */

	@GetMapping("form") // http://localhost:8888/param/form
	public String form() {
		log.trace("[TRACE] url-/param/form");
		log.debug("[DEBUG] url-/param/form");
		log.info("[INFO] url-/param/form");
		log.warn("[WARN] url-/param/form");
		log.error("[ERROR] url-/param/form");
		log.fatal("[FATAL] url-/param/form");
		return "form";
	}

	@PostMapping("ex01")
	public String ex01(Member member) { // DTO 객체
		// DTO를 선언하면 파라미터를 자동 주입
		// 이 때, 뷰까지 전달되며 사용 시 DTO의 첫 글자 소문자로 사용 ${member.id}
		log.info("ex01 -- parameter id : " + member.getId());
		log.info("ex01 -- parameter pw : " + member.getPw());

		return "ex01";
	}

	@PostMapping("ex02")
	public String ex02(@RequestParam("id") String id, @RequestParam("pw") String pw, Model model) {
		// @RequestParam request.getParmeter("id")와 같은 역할
		// 어노테이션으로 파라미터를 받아 변수에 대입한다.
		// DTO로 파라미터를 받을때와는 다르게 View까지 전달 불가하기 때문에 Model 객체를 사용해야 함
		log.info("ex02 -- parameter id : " + id);
		log.info("ex02 -- parameter pw : " + pw);

		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		return "ex02";
	}

	@PostMapping("ex03")
	// 체크박스와 같이 파라미터의 이름이 같은 경우,
	public String ex03(@RequestParam("hobbies") List<String> hobbies, @RequestParam("hobbies") String[] arr,
			Model model) {
		log.info("ex03 -- parameter hobbies : " + hobbies);
		log.info("ex03 -- parameter hobbies : " + Arrays.asList(arr));

		model.addAttribute("hobbies", hobbies);
		model.addAttribute("arr", arr);

		return "ex03";
	}

	@PostMapping("ex04")
	public String ex04(@ModelAttribute("id") String id) {
		// @ModelAttribute는 파라미터를 받아 View까지 전달 가능
		// View에서 사용시 ${id}
		// @RequestParam과 @Model이 합쳐진 기능
		log.info("ex04 -- parameter id : " + id);

		return "ex04";
	}

	@PostMapping("ex05")
	public String ex05(@RequestParam("id") String id, RedirectAttributes rttr) {
		// 일회성으로 데이터 전달 - 화면에 한 번만 보여짐(새로고침 후 id값이 출력되지 않는다.)
		// 리다이렉트 : 파라미터 전달 불가,
		rttr.addFlashAttribute("id", id);
		rttr.addFlashAttribute("pw", "1234");

		// redirect로 이동 시 View로 가는 것이 아니라 Get방식의 해딩 맵핑(ex05)으로 이동한다.
		return "redirect:ex05";
	}

	@GetMapping("ex05")
	public String ex05() {
		return "ex05";
	}

	@GetMapping("ex06")
	public @ResponseBody String ex06() {
		// View로 이동하지 않고 브라우저에 객체를 그대로 보여준다.
		// 뷰에서 Ajax 사용 시 주로 활용된다.
		return "안녕하세요";
	}

	@GetMapping("ex07")
	public @ResponseBody Member ex07() {
		// @ResponseBody를 활용한 DTO 객체도 가능하다.
		Member dto = new Member();
		dto.setId("boot");
		dto.setPw("5555");

		return dto;
	}

	@GetMapping("ex08")
	public ResponseEntity<String> ex08() {
		// ResponseEntity : HTTP의 응답을 표현하는 클래스
		// 200(http의 상태에 문제가 없을 때의 코드),404,500 같은 웹 에러코드들이 해당된다.
		String msg = "{ \"name\" : \"춘식이\" }";
		// json 방식 : 겹따옴표("")로 보낸다.
		// 이스케이프 문자 사용 : " -> \"

		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		return new ResponseEntity<>(msg, header, HttpStatus.OK);
	}

	@PostMapping("ex09")
	public String ex09( 
		@RequestParam("save") MultipartFile mf, @ModelAttribute("id") String id, Model model){
			//파일 업로드시 MultipartFile mf 객체 사용
		    //cos 라이브러리 사용 시 MultipartRequest 객체를 사용하는데 보안에 취약,
		    //스프링 web에 내장된 MultipartFile 객체를 사용하면 보안이 뛰어나다.
			//파일 이름 중복처리 기능이 없기 때문에 직접 처리해야 한다.
		
		model.addAttribute("contentType", mf.getContentType());
		model.addAttribute("orgName", mf.getOriginalFilename());
		model.addAttribute("fileSize", mf.getSize());
		
		File copy = new File("c:/upload/" + mf.getOriginalFilename());
		try {
			mf.transferTo(copy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ex09";
	}

	@PostMapping("ex10")
	public String ex10(@RequestParam("save") List<MultipartFile> files, @ModelAttribute("id") String id, Model model) {

		for (MultipartFile mf : files) {
			File copy = new File("c:/upload/" + mf.getOriginalFilename());
			try {
				// 이미지만 업로드
				if (mf.getContentType().split("/")[0].equals("image")) {
					mf.transferTo(copy);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("fileCount", files.size());

		return "ex10";
	}

	@PostMapping("ex11")
	public String ex11(@RequestParam("save") MultipartFile mf, Model model) {
		// 날짜와 랜덤메서드 이용한 파일이름 중복 방지
		// 생성할 때 날짜 형식을 초단위까지 지정
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		// 랜덤메서드 활용하기 위해 객체 생성
		Random random = new Random();
		// .nextInt : 랜덤값이 int로 나오게 하는 메서드
		// int 최대범위 사위의 랜덤값
		String rn = Integer.toString(random.nextInt(Integer.MAX_VALUE));
		// 현재 시간에 df 포맷 적용
		String ts = df.format(new Date());
		String uploadFileName = ts + rn + mf.getOriginalFilename();

		File copy = new File("c:/upload/" + uploadFileName);
		try {
			mf.transferTo(copy);
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("orgName", mf.getOriginalFilename());
		model.addAttribute("uploadFileName", uploadFileName);

		return "ex11";
	}
}