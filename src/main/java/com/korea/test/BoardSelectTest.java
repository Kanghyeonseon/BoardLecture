package com.korea.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.korea.dao.BoardDAO;
import com.korea.dto.BoardDTO;

public class BoardSelectTest {

	@Test
	public void test() {
		BoardDAO dao = BoardDAO.getInstance();
		BoardDTO dto = dao.Select(503);
		System.out.println(dto);
	}

}
