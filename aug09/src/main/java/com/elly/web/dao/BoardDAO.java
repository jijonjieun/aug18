package com.elly.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.elly.web.dto.BoardDTO;

@Repository
@Mapper
public interface BoardDAO {

	 List<BoardDTO> boardList();

	String detail(int bno);
	

}
