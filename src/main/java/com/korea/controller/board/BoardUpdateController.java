package com.korea.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.korea.controller.SubController;
import com.korea.dto.BoardDTO;
import com.korea.service.BoardService;

public class BoardUpdateController implements SubController{
	BoardService service = BoardService.getInstance();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String pwd = req.getParameter("pwd");
		String nowPage = req.getParameter("nowPage");
		
		System.out.println("Title : " + title);
		System.out.println("Content : " + content);
		System.out.println("pwd : " + pwd);
		
		HttpSession session = req.getSession();
		BoardDTO dto = (BoardDTO) session.getAttribute("dto");
		
		if(dto.getPwd().equals(pwd)) {
			// 서비스 함수 호출 -> DB변경
			dto.setTitle(title);	// 변경된 타이틀을 dto에 넣어준다.
			dto.setContent(content); // 변경된 글 내용을 dto에 넣어준다.
			service.UpdateBoard(dto); // 서비스에서 Updateboard메서드를 실행시킨다.
			session.setAttribute("dto", dto); // 세션에 읽고 있는 게시물 변경 저장
			
			// read.jsp파일로 이동. 이동하면서 no, nowPage를 변경
			try {
				String MSG = "업데이트 성공!";
				req.setAttribute("MSG", MSG);
				req.getRequestDispatcher("/Board/read.do?no="+dto.getNo()+"&nowpage").forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			//read.jsp로 이동... msg전달
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
