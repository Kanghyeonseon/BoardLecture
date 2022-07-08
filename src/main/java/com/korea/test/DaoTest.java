package com.korea.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.korea.dao.MemberDAO;
import com.korea.dto.MemberDTO;
import com.korea.service.MemberService;

public class DaoTest {

	@Test
	public void test() {
		MemberDTO dto = new MemberDTO();
		dto.setEmail("admin@admin.com");
		dto.setPwd("1234");
		dto.setAddr1("관리자계정1번주소");
		dto.setAddr2("관리자계정2번주솜");
		dto.setGrade(2);

		MemberService service = MemberService.getInstance();
		service.MemberInsert(dto);
		
		dto.setEmail("guest@guest.com");
		dto.setPwd("1234");
		dto.setAddr1("게스트계정1번주소");
		dto.setAddr2("게스트계정2번주솜");
		dto.setGrade(0);
		
		service.MemberInsert(dto);
	}
	
//	@Test
//	public void test2() {
//		//MemberDAO의 Select 테스트
//		MemberDAO dao = MemberDAO.getInstance();
//		MemberDTO dto = dao.Select("ample@example.com");
//		System.out.println("결과 : " + dto.toString());
//	}

}
