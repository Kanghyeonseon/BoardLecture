package com.korea.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.korea.controller.SubController;
import com.korea.dto.MemberDTO;
import com.korea.service.MemberService;

public class MemberInfoController implements SubController{

	MemberService service = MemberService.getInstance();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("회원정보 컨트롤러 진입!");
		try {
			/* 여기서부턴 내가 추가한 코드 */
			HttpSession session = req.getSession();
			String email = (String) session.getAttribute("email");
			System.out.println("내정보 email : " + email);
			
			session.setAttribute("grade", dto.getGrade()); // 세션의 grade에 dto에서 받아온 grade값을 입력한다. 
			session.setAttribute("email", dto.getEmail()); //세션에 현재 접속중인 계정의 이메일 저장
			session.setMaxInactiveInterval(60*5);
			
			MemberDTO dto = service.MemberSearch(email);
			
			/* 여기까지는 내가 추가한 코드 */
			req.getRequestDispatcher("/WEB-INF/member/myinfo.jsp").forward(req, resp);
		} catch (Exception e) { e.printStackTrace(); }
		

	}

}
