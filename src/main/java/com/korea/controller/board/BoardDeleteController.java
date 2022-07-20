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
			service.BoardRemove(dto);
		} else {
		}
	}

}
