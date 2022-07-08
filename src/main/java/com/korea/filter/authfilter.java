package com.korea.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class authfilter implements Filter{
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		//request 전 처리
		System.out.print("Filter 처리! - request전");
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		chain.doFilter(req, resp);
		//response로 외부로 나가기 전 처리
		System.out.println("Filter처리! - response전");
	}
}
