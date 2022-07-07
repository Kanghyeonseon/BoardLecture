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
			HttpSession session = req.getSession();
			String email = (String) session.getAttribute("email");
			MemberDTO dto = service.MemberSearch(email);
			req.setAttribute("dto", dto);
			req.getRequestDispatcher("/WEB-INF/member/myinfo.jsp").forward(req, resp);
		} catch (Exception e) { e.printStackTrace(); }
	}
}
