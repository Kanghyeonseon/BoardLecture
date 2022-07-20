package com.korea.updownTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download.do")
public class C04DownloadServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 경로설정
		String downdir = "C://Users//ASUS//Documents//upload";
		String filename = req.getParameter("filename");
		String filepath = downdir + File.separator + filename;
		// File.seperator 대신에 "/"를 사용해도 된다.
		
		// 헤더설정
		resp.setContentType("application/octet-stream");
		// 컨텐츠 타입이 text/html로 원래 설정되어있다. 컨텐츠의 타입이 바이너리데이터(= 파일)이다. 라고 알려줘야한다.
		
		// 문자셋 설정
		filename = URLEncoder.encode(filepath, "utf-8").replaceAll("\\+", "%20");
		resp.setHeader("Content-Disposition", "attatchment; fileName="+filename);
		// 파일이름이 공백을 포함한다면 파일이름을 다시 공백으롭 바꿔준다.
		// 파일이름 대로 다운로드 될 파일명과 동일하게 설정이 된다.
		
		// 스트림 설정
		byte[] buffer = new byte[4096];	// 버퍼공간 : 파일에서 서블릿으로 받아 올 임시 단위공간 
		FileInputStream fin = new FileInputStream(filepath); // 입력스트림 : 파일-> 서블릿
		ServletOutputStream sout = resp.getOutputStream(); // 출력 스트림 : 서블릿(서버) -> 브라우저
		
		// 파일에서 서버 방향으로 read라는 변수에 바이트 수를 저장시킬 것이다.
		
		int read; // read에서 받을 것이 있는지 확인하는 용도 
		while(true) {
			read = fin.read(buffer, 0, buffer.length); 
			// 파일 -> 서블릿으로 index 0부터 buffer.length까지 담기
			// read에는 읽어들인 byte수가 저장되고 더이상 읽을 것이 없으면 -1이 저장된다.
			if(read==-1) {
				break;
			}
			sout.write(buffer, 0, read); // buffer 안의 데이터를 0부터 index까지 읽은 만큼 브라우저로 전송한다.
			// 서버-> 클라이언트(브라우저)방향이다.
		}
		sout.flush(); // 브라우저 방향으로 데이터 전송에 사용 된 버퍼공간 초기화
		sout.close(); // 출력스트림종료
		fin.close(); // 입력스트림 종료
		
		// 뷰로 이동은 생략. 위치를 고정해야 다른파일을 클릭하면 다른파일도 같이 다운로드가 된다.
	}

}
