package com.elly.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.elly.web.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("/notice")
	public String notice(Model model) {
		//필요한거? model + map + service + DAO + Mapper
	// 리스트를 jsp에 찍어주기위해 모델을 이용함
		
		
		List<Map<String, Object>> list = noticeService.list();
		model.addAttribute("list", list);
		System.out.println(list);
		
		return "notice";
	
	}
	
	
	
	
	
}
