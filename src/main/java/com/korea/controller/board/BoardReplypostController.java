package com.korea.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.korea.controller.SubController;
import com.korea.dto.BoardDTO;
import com.korea.dto.ReplyDTO;
import com.korea.service.BoardService;

public class BoardReplypostController implements SubController{
	BoardService service = BoardService.getInstance();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String comment = req.getParameter("comment"); // 이 정보는 read.jsp에서 postreply() 함수로 부터 넘어오는정보이다.
		String nowPage = req.getParameter("nowPage");
		
		HttpSession session = req.getSession(); 
		BoardDTO dto = (BoardDTO) session.getAttribute("dto"); // 읽고 있는 게시물의 정보 BoardReadController에서 setAttribute한 dto를 get해주는것이다.
		String email = (String) session.getAttribute("email"); // 현재 접속중인 게정 명
		
		ReplyDTO rdto = new ReplyDTO();
		rdto.setBno(dto.getNo());
		rdto.setWriter(email);
		rdto.setContent(comment);
		
		// 서비스 실행
		service.replypost(rdto); 
	}
}
