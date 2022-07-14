package com.korea.updownTest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload1")
@MultipartConfig
(
		fileSizeThreshold = 1024*1024*10,	// 10Mb
		maxFileSize = 1024*1024*50,			// 50Mb
		maxRequestSize = 1024*1024*100		// 100Mb
)
public class C01UploadServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Part part = req.getPart("test");
		/* 파일이름 추출작업 테스트
		System.out.println("Part name :" + part.getName());
		System.out.println("File Size :" + part.getSize() + " byte");
		System.out.println("Header : " + part.getHeaderNames());
		System.out.println("content-disposition : " + part.getHeader("content-disposition"));
		String contentDisp = part.getHeader("content-disposition");
		String[] arr = contentDisp.split(";"); // 세미콜론을 기준으로 contentDisp에있는 데이터를 잘라서 arr에 넣음.
		System.out.println("arr[2] : " + arr[2]); // 2번 인덱스의 데이터를 추출한다.
		System.out.println("파일이름 : " + arr[2].substring(11, arr[2].length()-1));
		파일이름 추출작업 테스트 */ 
		System.out.println("Filename : " + getFilename(part));
	}
	
	private String getFilename(Part part) {
		String[] arr = part.getHeader("content-disposition").split(";");
		return arr[2].substring(11, arr[2].length()-1);
	}
}
