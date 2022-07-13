package com.korea.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.korea.dao.BoardDAO;

public class BoardDAO2 {

	@Test
	public void test() {
		BoardDAO dao = BoardDAO.getInstance();
		int result = dao.getTotalCount();
		System.out.println("전체 게시물 수 : " + result);
	}

}
