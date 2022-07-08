package com.korea.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.korea.controller.SubController;

public class BoardListController  implements SubController{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.getRequestDispatcher("/WEB-INF/board/list.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
