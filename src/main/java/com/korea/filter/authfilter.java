package com.korea.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class authfilter implements Filter{
	Map<String, Integer> pageGradeMap = new HashMap();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		pageGradeMap.put("/Board/list.do", 0);
		pageGradeMap.put("/Board/post.do", 1);
		pageGradeMap.put("/Notice/list.do", 0);
		pageGradeMap.put("/Notice/post.do", 2);
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		//request 전 처리
		System.out.println("-------구분선-------");
		System.out.println("Filter 처리! - request전");
		
		// 한글 문자셋 처리
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		//session으로부터 grade 추출
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		int usergrade=0;
		if(session.getAttribute("grade")!=null) {
			usergrade = (Integer) session.getAttribute("grade");
		}
		
		//url grade확인
		String URL = request.getRequestURI();
		System.out.println("Filter's URL : " + URL);
		System.out.println("user grade : " + usergrade);
		
		int pagegrade=0;
		if(pageGradeMap.get(URL)!=null) {
			pagegrade = pageGradeMap.get(URL);
			System.out.println("page grade : " + pagegrade);
		}
		if(usergrade==0 && pagegrade>=1) {
			throw new ServletException("로그인이 필요한 페이지입니다.");
		}
		if(usergrade<2 && pagegrade==2) {
			throw new ServletException("관리자 권한이 필요합니다.");
		}
		chain.doFilter(req, resp);
		
		//response로 외부로 나가기 전 처리
		System.out.println("Filter처리! - response전");
		System.out.println("-------구분선-------");

	}
}
