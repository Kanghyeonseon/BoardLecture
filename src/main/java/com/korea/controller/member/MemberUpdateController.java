package com.korea.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.korea.controller.SubController;
import com.korea.dto.MemberDTO;
import com.korea.service.MemberService;

public class MemberUpdateController implements SubController{
	MemberService service = MemberService.getInstance();
	// 서비스의 함수를 사용하기 위해 등록
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// 파라미터 받기
		
		// 입력값 확인
		
		// 서비스 실행
		
		// view로 연결
		String flag = req.getParameter("flag");
		
		try {
			if(flag==null) { // myinfo.jsp에서 요청받음
				
				req.getRequestDispatcher("/WEB-INF/member/password.jsp").forward(req, resp);
				// 패스워드 입력 페이지로 이동
			} else { // password.jsp에서 요청받음
				String pwd = req.getParameter("pwd");
				HttpSession session = req.getSession();
				String email = (String) session.getAttribute("email");
				MemberDTO dto = service.MemberSearch(email); // MemberSearch 메서드를 실행시켜서 나온 결과인 dto단위의 데이터를 가져온다.
				
				if(service.passwordEncoder.checkpw(pwd, dto.getPwd())) {
					// 유저가 입력한 pwd와 dto에서 꺼내온 pwd값을 비교하는데 패스워드 인코더를 사용한다. 
					String addr1 = req.getParameter("addr1");
					String addr2 = req.getParameter("addr2");
					
					System.out.println("addr1" + addr1);
					dto.setAddr1(addr1);
					dto.setAddr2(addr2);
					
					
					service.MemberUpdate(dto);
					
					System.out.println();
					req.setAttribute("dto", dto);
					req.getRequestDispatcher("/WEB-INF/member/myinfo.jsp").forward(req, resp);
					return;
				} else {
					req.getRequestDispatcher("/WEB-INF/member/password.jsp").forward(req, resp);
					return;
				}
			}
		} catch (Exception e) { e.printStackTrace(); }
		
	}

}
