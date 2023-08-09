package com.elly.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.elly.web.dto.BoardDTO;
import com.elly.web.service.BoardService;

@Controller	
public class BoardController {
	
	@Autowired
	private BoardService boardservice;
	
	
	@GetMapping("/board")
	public String board(Model model) {
	List<BoardDTO> list =  boardservice.boardList();
		
	model.addAttribute("list",list);
		return "board";
	}

}
