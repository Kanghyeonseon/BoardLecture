package com.korea.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.korea.controller.member.MemberJoinController;


public class FrontController extends HttpServlet{
	// URL : SubController 객체
	// 상속관계에 있기 때문에 어떤 SubController라도 연결할 수 있다. 업캐스팅임
	// url주소와 SubController 객체를 list에 저장한다.
	HashMap<String, SubController> list = null;
	
	@Override
	public void init() throws ServletException {
		list = new HashMap();
		list.put("/MemberJoin.do", new MemberJoinController());
		// /MemberJoin.do라는 url과 MemberJoinController 위치정보가 리스트에 들어간다.
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String url = req.getRequestURI();
		System.out.println("URL" + url); 
		
		SubController sub = list.get(url);
		if(sub!=null) {
			list.get(url).execute(req, resp); 
		}
	}
}
