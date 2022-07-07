package com.korea.test;

import org.junit.Test;

import com.korea.dao.MemberDAO;
import com.korea.dto.MemberDTO;

public class UpdateTest {
	@Test
	public void test() {
		MemberDTO dto = new MemberDTO();
		dto.setEmail("abcd@abcd.com");
		dto.setAddr1("수정된addr1!");
		dto.setAddr2("수정된addr2");
		
		MemberDAO dao = MemberDAO.getInstance();
		boolean result = dao.Update(dto);
		if(result) {
			System.out.println("수정 성공");
		}
	}
}
