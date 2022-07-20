package com.korea.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.korea.controller.SubController;
import com.korea.service.BoardService;

public class BoardDownloadController implements SubController{
	BoardService service = BoardService.getInstance();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// 파라미터
		String filename = req.getParameter("filename");
		String flag = req.getParameter("flag");
		
		// 입력값검증
		
		// 서비스
		boolean result = false;
			result = service.download(filename, req, resp);
		if(result) { System.out.println("파일 다운로드 완료!"); }
		
		// 뷰
		
		
	}

}
