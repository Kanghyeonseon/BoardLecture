
package com.korea.controller.board;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.korea.controller.SubController;
import com.korea.dto.BoardDTO;
import com.korea.service.BoardService;

public class BoardReadController implements SubController{
	BoardService service = BoardService.getInstance();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		//파라미터
		String no = req.getParameter("no");
		String nowPage = req.getParameter("nowPage");
		
		//서비스 실행
		int num = Integer.parseInt(no);
		
		//쿠키 꺼내기
		Cookie[] cookies = (req.getCookies());
		for(int i=0; i<cookies.length; i++) {
			if(cookies[i].getName().equals("init")) {
				cookies[i].setMaxAge(0);		// 쿠키 제거
				resp.addCookie(cookies[i]);	// response에 쿠키 제거 적용
				service.CountUp(num);			// 조회수 증가
				break;							// 반복문 제거하기
			}
		}
		
		// 게시물 받기
		BoardDTO dto = service.getBoardDTO(num);
		
		// 세션에 읽고 있는 게시물 저장 (수정, 삭제로 이동 시 현재 읽는 게시물 확인이 쉽다.)
		HttpSession session = req.getSession();
		session.setAttribute("dto", dto);
		
		// 뷰로 이동
		try {
			req.setAttribute("dto", dto);
			req.setAttribute("nowPage", nowPage); // 읽고있던 게시글의 페이지로 이동해야하기 때문에 nowPage도 함께 전달해줘야한다.
			req.getRequestDispatcher("/WEB-INF/board/read.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
