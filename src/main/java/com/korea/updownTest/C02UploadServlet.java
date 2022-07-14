package com.korea.updownTest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload2")
@MultipartConfig
(
		fileSizeThreshold = 1024*1024*10,	// 10Mb
		maxFileSize = 1024*1024*50,			// 50Mb
		maxRequestSize = 1024*1024*100		// 100Mb
)
public class C02UploadServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		for(Part part : req.getParts()) {
			System.out.println("Part name :" + part.getName());
			System.out.println("File Size :" + part.getSize() + " byte");
			System.out.println("Header : " + part.getHeaderNames());
			System.out.println("content-disposition : " + part.getHeader("content-disposition"));
			System.out.println("파일이름 : " + getFilename(part));
			System.out.println("******************************************************");
		}
	}
	
	private String getFilename(Part part) {
		String[] arr = part.getHeader("content-disposition").split(";");
		return arr[2].substring(11, arr[2].length()-1);
	}
}
