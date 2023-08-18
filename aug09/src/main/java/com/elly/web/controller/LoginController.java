package com.elly.web.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.elly.web.service.LoginService;

@Controller
public class LoginController {
	
	
	@Autowired
	private LoginService loginService;
	
	
	//0811 프레임워크프로그래밍
	
	@GetMapping("/login")
	public String index() {
		return "login";
	}
	
	
	//2023-08-16
	@PostMapping("/login")
	public String login(@RequestParam Map<String, String> map, HttpSession session) {
		//세션이 있다면 다른곳으로 이동
				//id/pw값이 없다면 다른곳으로 이동
	System.out.println(map);
	
	Map<String, Object> result = loginService.login(map);
	
	// System.out.println(result);
	// {m_name=김도영, count=1}
	
	
	if(String.valueOf(result.get("count")).equals("1")) {
		//(int)result.get("count")==1
		//정상로그인이라면 세션만들고, index로 이동합니다.
		//result.get("count")이게 오브젝트타입이기떄문에 인트를 넣어줌.
		session.setAttribute("mid", map.get("id"));
		session.setAttribute("mname", result.get("m_name"));
		
		return "redirect:/";
	} else {
		//다시로그인가기
		return "login"; 
	}
	
	}
	
	
	@GetMapping("/logout")
	   public String logout(HttpSession session) {
	      if(session.getAttribute("mid") != null) {
	         session.removeAttribute("mid");
	         
	      }if(session.getAttribute("mname") != null) {
	         session.removeAttribute("mname");
	      }
	      //다른 방법
	      session.invalidate();
	      return "redirect:/";
	   }
	
	
	
	//요구사항확인
	//마이인포다음에 아이디가 들어올꺼야 url주소를 그렇게 요청하나봄?
	//@PathVariable사용법
	@GetMapping("/myinfo@{id}")
	public ModelAndView myInfo(@PathVariable("id") String id, HttpSession session) {
		System.out.println("jsp가 보내준 값 : " + id );
		System.out.println(id.equals(session.getAttribute("mid")));
		//주소에 찍히는 아이디와 세션에 들어있는 아이디가 다르다면 인포페이지를
	//보여주지않음.
		
		Map<String, Object> myInfo = loginService.myInfo(id);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("myinfo"); //이동할 jsp파일명
		mv.addObject("myInfo", myInfo); //값 붙이기
	
		return mv;
	}
	
	

}
