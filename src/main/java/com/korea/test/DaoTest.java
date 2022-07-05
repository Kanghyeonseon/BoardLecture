package com.korea.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.korea.dao.MemberDAO;
import com.korea.dto.MemberDTO;

public class DaoTest {

	@Test
	public void test() {
		MemberDTO dto = new MemberDTO();
		dto.setEmail("example@example.com");
		dto.setPwd("1234");
		dto.setAddr1("대구광역시 00구 11아파트");
		dto.setAddr2("111동 1234호");
		
		MemberDAO dao = MemberDAO.getInstance();
		boolean result = dao.insert(dto);
		if(result) {
			System.out.println("INSERT 성공");
		}
		
		
	}

}
