package com.korea.controller.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.korea.controller.SubController;
import com.korea.filter.authfilter;

public class LogoutController implements SubController{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		session.invalidate();
		try {
			authfilter.filterflag = false;
			req.setAttribute("MSG", "로그아웃 완료!");
			req.getRequestDispatcher("/").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
