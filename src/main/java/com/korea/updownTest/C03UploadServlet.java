package com.korea.updownTest;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload3")
@MultipartConfig
(
		fileSizeThreshold = 1024*1024*10,	// 10Mb
		maxFileSize = 1024*1024*50,			// 50Mb
		maxRequestSize = 1024*1024*100		// 100Mb
)
public class C03UploadServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// uuid로 하위 폴더명 생성
		String subdir = UUID.randomUUID().toString();
		
		// 업로드 경로
		File upload = new File("C://Users//ASUS//Documents//upload/" + subdir);
		
		// 업로드 경로가 없으면 디렉토리 생성
		if(!upload.exists()) {
			upload.mkdir();
		}
		
		// Multipart로 전달되는 모든 파트를 받아서 반복처리로 확인
		for(Part part : req.getParts()) {
			String Filename = getFilename(part); // 파일이름 가져오기
			part.write("C://Users//ASUS//Documents//upload/" + subdir + "/" + Filename);  // 파일 업로드
			System.out.println("******************************************************");
		}
	}
	
	private String getFilename(Part part) {
		String[] arr = part.getHeader("content-disposition").split(";");
		return arr[2].substring(11, arr[2].length()-1);
	}
}
