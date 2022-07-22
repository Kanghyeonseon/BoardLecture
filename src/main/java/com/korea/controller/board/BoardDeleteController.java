package com.korea.controller.board;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.korea.controller.SubController;
import com.korea.dto.BoardDTO;
import com.korea.service.BoardService;

public class BoardDeleteController implements SubController{
	BoardService service = BoardService.getInstance();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String pwd = req.getParameter("pwd");
		String nowPage = req.getParameter("nowPage");
		System.out.println("PWD : " + pwd);
		
		HttpSession session = req.getSession();
		BoardDTO dto = (BoardDTO) session.getAttribute("dto");
		
		if(dto.getPwd().equals(pwd)) {
			try {
				service.BoardRemove(dto);
				String MSG = "글 삭제 성공!";
				req.setAttribute("MSG", MSG);
				int numPerPage = 10;
				int start = (Integer.parseInt(nowPage) * numPerPage) - numPerPage + 1;
				int end = (Integer.parseInt(nowPage) * numPerPage);
				req.getRequestDispatcher("/Board/list.do?nowPage=" + nowPage + "&start=" + start + "&end=" + end).forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				String MSG = "패스워드 불일치";
				req.setAttribute("MSG", MSG);
				req.getRequestDispatcher("/Board/read.do?no="+dto.getNo()+"&nowpage").forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
