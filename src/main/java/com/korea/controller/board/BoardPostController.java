package com.korea.controller.board;

import java.util.ArrayList;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.korea.controller.SubController;
import com.korea.dto.BoardDTO;
import com.korea.service.BoardService;

public class BoardPostController implements SubController {

	BoardService service = BoardService.getInstance();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String flag = req.getParameter("flag");
		try {
			if(flag==null) {	 // list.jsp에서 글쓰기 버튼 클릭 시
				req.getRequestDispatcher("/WEB-INF/board/post.jsp").forward(req, resp);
			} else {		 	 // post.jsp에서 글쓰기 버튼 클릭시
				// 파라미터 가져오기
				String title = req.getParameter("title");
				String content = req.getParameter("content");
				String  pwd = req.getParameter("pwd");
				String ip = req.getRemoteAddr();
				HttpSession session = req.getSession();
				String writer = (String) session.getAttribute("email");
				
				// 입력값 검증
				if(title==null || content==null || pwd==null || ip==null) {
					req.getRequestDispatcher("/WEB-INF/board/post.jsp").forward(req, resp);
					return;
				}
				
				// 서비스 실행
				BoardDTO dto = new BoardDTO();
				dto.setTitle(title);
				dto.setContent(content);
				dto.setPwd(pwd);
				dto.setIp(ip);
				dto.setWriter(writer);
				
				// 추가 파일 전달
				ArrayList<Part> parts = ( ArrayList<Part>)req.getParts();
				
				boolean result = false;
				long size = parts.get(3).getSize();
				
				if(size==0) { // 파일 없이 업로드
					result = service.PostBoard(dto);
				} else {		// 파일과 함께 업로드
					result = service.PostBoard(dto, parts);
				}
				
				
				if(result) {
					resp.sendRedirect("/Board/list.do");
					System.out.println("글 작성 완료!"); 
					return;
				} else {
					req.getRequestDispatcher("/WEB-INF/board/post.jsp").forward(req, resp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
