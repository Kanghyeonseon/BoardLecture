package com.korea.controller.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.korea.controller.SubController;
import com.korea.dto.MemberDTO;
import com.korea.service.MemberService;

public class LoginController implements SubController{
MemberService service = MemberService.getInstance();
@Override
public void execute(HttpServletRequest req, HttpServletResponse resp) {
	System.out.println("로그인 컨트롤러 진입!");
	// 1. 파라미터 받기
	String email = req.getParameter("email");
	String pwd = req.getParameter("pwd");
	try {
		// 2. 입력값 검증
		if(email==null||pwd==null) {
			resp.sendRedirect("/index.do");
		} 
		
		// 3. 서비스 실행
		MemberDTO dto = service.MemberSearch(email);
		if(dto!=null) {
			if(service.passwordEncoder.checkpw(pwd, dto.getPwd())) {
				// 아이디, 패스워드 일치
				
				// 세션에 특정 옵션 부여
				HttpSession session = req.getSession(); //세션 객체 만들기
				session.setAttribute("grade", dto.getGrade()); // 세션의 grade에 dto에서 받아온 grade값을 입력한다. 
				session.setAttribute("email", dto.getEmail()); //세션에 현재 접속중인 계정의 이메일 저장
				session.setMaxInactiveInterval(60*5);
				
				// view 이동
				resp.sendRedirect("/main.do");
			} else {
				// 패스워드 불일치 : 로그인페이지로 이동
				req.setAttribute("MSG", "패스워드가 일치하지 않습니다.");
				req.getRequestDispatcher("/index.do").forward(req, resp);
			}
		} else {
			// 아이디 조회 실패
			req.setAttribute("MSG", "일치하는 아이디가 없습니다.");
			req.getRequestDispatcher("/index.do").forward(req, resp);
		}
	} catch (Exception e) { e.printStackTrace(); }
}
}