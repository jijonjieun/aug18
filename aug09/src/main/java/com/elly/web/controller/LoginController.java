package com.elly.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	//0811 프레임워크프로그래밍
	
	@GetMapping("/login.sik")
	public String index() {
		return "login";
	}

}
