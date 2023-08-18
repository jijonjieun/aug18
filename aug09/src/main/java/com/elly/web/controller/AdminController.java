package com.elly.web.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.elly.web.service.AdminService;
import com.elly.web.util.Util;

@Controller
@RequestMapping("/admin") // 어드민폴더아래 있는 애들은 이쪽으로 옴
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private Util util;

	// adminservice /admindao / adminMapper

	// 어드민 중복으로 사용하지 않아도됨.
	@GetMapping("/admin")
	public String adminIndex() {

		return "admin/index";
		// 폴더안에 abmin폴더안에 index.jsp (jsp파일은 영향안받아서 폴더이름작아줘ㅏ야함)
	}

	@PostMapping("/login")
	public String adminLogin(@RequestParam Map<String, Object> map, HttpSession session) {
		System.out.println(map);

		Map<String, Object> result = adminService.adminLogin(map);

		System.out.println(result);

		if (util.obj2Int(result.get("count")) == 1 && util.obj2Int(result.get("m_grade")) > 5) {
			// 세션올리기
			session.setAttribute("mid", map.get("id"));
			session.setAttribute("mname", result.get("m_name"));
			session.setAttribute("mgrade", result.get("m_grade"));
			// 메인으로 이동하기
			return "redirect:/admin/main";
		} else {
			return "redirect:/admin/admin?error=login";
		}


	}
}
