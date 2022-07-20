package com.korea.controller.board;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.korea.controller.SubController;
import com.korea.dto.BoardDTO;
import com.korea.service.BoardService;

public class BoardListController implements SubController{

	BoardService service = BoardService.getInstance();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		 
		try {
			
			//파라미터
			String tmpstart = req.getParameter("start");
			String tmpend = req.getParameter("end");
			String nowPage = req.getParameter("nowPage");
			int start=0;
			int end=0;
			if(tmpstart==null || tmpend==null) {
				start=1;
				end=10;
			} else {
				start = Integer.parseInt(tmpstart);
				end = Integer.parseInt(tmpend);
			}

			//입력값
			List<BoardDTO> list = service.getBoardList(start, end);
			int tcnt = service.getTotalCnt();
			
			req.setAttribute("list", list);
			req.setAttribute("tcnt", tcnt);
			
			//게시글 읽기 새로고침 시 중복 Count증가 방지
			Cookie init = new Cookie("init", "true");
			resp.addCookie(init);
			
			req.getRequestDispatcher("/WEB-INF/board/list.jsp?nowPage="+nowPage).forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
	}

}