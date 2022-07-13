package com.korea.test;

import org.junit.Test;

import com.korea.dao.BoardDAO;
import com.korea.dto.BoardDTO;

public class BoardPostTest {

	@Test
	public void test() {
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO dto = new BoardDTO();
		dto.setTitle("타이틀입니다.");
		dto.setContent("컨텐츠입니다.");
		dto.setWriter("이메일이들어갈부분");
		dto.setPwd("1234");
		dto.setIp("192.168.5.50");
		dao.Insert(dto);
	}

}
