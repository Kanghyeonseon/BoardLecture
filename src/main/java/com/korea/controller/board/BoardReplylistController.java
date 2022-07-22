package com.korea.controller.board;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.korea.controller.SubController;
import com.korea.dto.BoardDTO;
import com.korea.dto.ReplyDTO;
import com.korea.service.BoardService;

public class BoardReplylistController implements SubController{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		BoardService service = BoardService.getInstance();
		HttpSession session = req.getSession();
		BoardDTO dto = (BoardDTO) session.getAttribute("dto");
		// 해당하는 게시물 번호의 댓글들을 가져와야한다.
		ArrayList<ReplyDTO> list = service.getReplylist(dto.getNo());
		try {
			PrintWriter out = resp.getWriter(); // out 내장객체를 만들어준다. 이렇게 해야 result에 담아서 브라우저에 출력할 수 있다.
			for(int i=0; i<list.size(); i++) {
				out.print("<div class=\"form-control mb-3\">");
				out.print("<i class=\"bi bi-chat-dots\"></i>");
				out.print("<span style=\"font-size:0.5rem;\">"+list.get(i).getWriter()+"</span>&nbsp;&nbsp;");
				out.print("<span style=\"font-size:0.5rem;\">"+list.get(i).getRegdate()+"</span><br>");
				out.print("<span class=\"mx-3\">"+list.get(i).getContent() +"</span><br>");
				out.print("</div>");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
