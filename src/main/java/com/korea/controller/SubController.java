package com.korea.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SubController {
	void execute(HttpServletRequest req, HttpServletResponse resp);
	// 모든 컨트롤러들은 request와 response 객체를 받는다.
}
