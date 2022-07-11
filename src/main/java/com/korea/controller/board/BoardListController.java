package com.korea.controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.korea.controller.SubController;
import com.korea.dto.BoardDTO;
import com.korea.service.BoardService;

public class BoardListController  implements SubController{
	
	BoardService service = BoardService.getInstance();

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			
			//파라미터 확인
			int start=1; int end=10;
			//입력값 검증
			
			//서비스 실행
			List<BoardDTO> list = service.getBoardList(start, end);
			req.setAttribute("list", list); // list라는 이름으로 start, end값을 보낸다.
			req.getRequestDispatcher("/WEB-INF/board/list.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
