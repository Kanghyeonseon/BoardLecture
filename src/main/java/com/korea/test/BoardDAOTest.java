package com.korea.test;

import java.util.List;

import org.junit.Test;

import com.korea.dao.BoardDAO;
import com.korea.dto.BoardDTO;

public class BoardDAOTest {

	@Test
	public void test() {
		BoardDAO dao = BoardDAO.getInstance();
		List<BoardDTO> list = dao.Select(1,10);
		
		
//		list.forEach(dto -> System.out.println(dto));
		for(int i=0; i<list.size();i++) {
			System.out.println(list.get(i));
		}
		System.out.println("size : " + list.size());
	}

}
