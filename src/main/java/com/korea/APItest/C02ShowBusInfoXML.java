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

@WebServlet("/ShowBus.Info")
public class C02ShowBusInfoXML extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/xml; charset=utf-8"); //xml로 바꿨음
		String addr = "http://apis.data.go.kr/1613000/ExpBusInfoService/getExpBusTrminlList?serviceKey=zqrTLXprDh1OiWtWKnl8n42o82x0HbxEwwFwccjuddzWmFJuLx5puGR4aHoysM2KS0N788d3g625pT7hPJfCaw%3D%3D&pageNo=1&numOfRows=230&_type=xml";
		
		URL url = new URL(addr); // addr에 있는 내용을 자바프로그램으로 가져올 수 있게 하는 기본 스트림이 만들어진다. URL클래스
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8")); //스트림을 열어서 인코딩을 설정
		// 보조스트림. 보조스트림을 두 번 끼운것이다. Inputstreamreader : 1바이트단위의 데이터를 2바이트 단위의 문자 데이터로 가져온다.
		// BufferedReader는 버퍼공간을 추가해서 더 빠르게 가져올 수 있게 한다.
		// 캐릭터형으로 받아온다음에
		
		
		StringBuffer sb = new StringBuffer();
		String str = null;
		
		while(true) {
			str = br.readLine(); // 라인단위로 받아온다.
			if(str==null) break; // 읽어올 데이터가 없다면 더이상 데이터를 받아오지 않고 while문을 벗어난다.
			sb.append(str); //읽어올 데이터가 있다면 라인단위로 데이터를 적용하도록 한다.
		}
		
		br.close();
		// System.out.println(sb.toString());
		resp.getWriter().write(sb.toString()); // 받아온 스트림을 화면에 출력하게 해 준다.
	}

}
