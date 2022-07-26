package com.korea.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.korea.controller.SubController;
import com.korea.dto.MemberDTO;
import com.korea.service.MemberService;

public class MemberJoinController implements SubController{
	
	private MemberService service = MemberService.getInstance();

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("=====MEMBER JOIN CONTROLLER====");
		 
		String flag = req.getParameter("flag");
		try {
			if(flag==null) { // 로그인에서 회원가입 버튼을 누름
				req.getRequestDispatcher("/WEB-INF/member/join.jsp").forward(req, resp);
			} else {		// 회원가입 페이지에서 submit 버튼을 누름
			
			// 1. 파라미터 받기
			String email = req.getParameter("email");
			String pwd = req.getParameter("pwd");
			String addr1 = req.getParameter("addr1");
			String addr2 = req.getParameter("addr2");
			String zipcode = req.getParameter("zipcode");
			System.out.println(email +", "+ pwd + ", "+addr1 + ", "+addr2);
			
			// 2. 입력값 검증
			// 3. 서비스 실행
			MemberDTO dto = new MemberDTO();
			dto.setEmail(email);
			dto.setPwd(pwd);
			dto.setAddr1(addr1);
			dto.setAddr2(addr2);
			dto.setZipcode(zipcode);
			
			boolean result = service.MemberInsert(dto);
			if(result) {
				System.out.println("회원가입 성공!");
			}
			
			// 4. View로 이동
			resp.sendRedirect("/index.do");
		}
		
		} catch (Exception e) { e.printStackTrace(); }
	}
}
