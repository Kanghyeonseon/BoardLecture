package com.korea.APItest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ReqBus.Info")
public class C01RequestBusInfo extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=utf-8");
		
		// 파라미터 받기
		String serviceKey = "zqrTLXprDh1OiWtWKnl8n42o82x0HbxEwwFwccjuddzWmFJuLx5puGR4aHoysM2KS0N788d3g625pT7hPJfCaw%3D%3D"; // 공공데이터포털에서 받아온 인증 키
		String numOfRows = "100";
		String pageNo = "1"; 	// 페이징처리 안 함
		String _Type = "json"; 	// json타입으로 처리함
		String depTerminalId = req.getParameter("depTerminalId"); 	// 출발 터미널 아이디
		String arrTerminalId = req.getParameter("arrTerminalId"); 	// 도착 터미널 아이디
		String depPlandTime = req.getParameter("depPlandTime");
		depPlandTime = depPlandTime.replaceAll("-", "");
		String busGradeId = req.getParameter("busGradeId");			// 버스등급
		
		// 경로 설정
		String addr = "http://apis.data.go.kr/1613000/ExpBusInfoService/getStrtpntAlocFndExpbusInfo?"
				+ "serviceKey=" + serviceKey
				+ "&depTerminalId=" + depTerminalId
				+ "&arrTerminalId=" + arrTerminalId
				+ "&depPlandTime=" + depPlandTime
				+ "&busGradeId=" + busGradeId
				+ "&numOfRows=" + numOfRows
				+ "&_type=json";
		// 확인용
		// System.out.println(addr);
		
		// 01.jsp 테이블로 보내기.
		URL url = new URL(addr); 
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8")); 
		StringBuffer sb = new StringBuffer();
		String str = null;
		
		while(true) {
			str = br.readLine();
			if(str==null) break; 
			sb.append(str);
		}
		System.out.println(sb.toString());

		br.close();
		resp.getWriter().write(sb.toString());
	}
}
