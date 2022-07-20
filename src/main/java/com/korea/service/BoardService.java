package com.korea.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.korea.dao.BoardDAO;
import com.korea.dto.BoardDTO;

public class BoardService {
	private BoardDAO dao = BoardDAO.getInstance();
	
	private String UploadPath = "C://Users//ASUS//Documents//upload/";
	
	// 싱글톤 패턴
	private static BoardService instance = null;
	public static BoardService getInstance() {
		if(instance==null) instance = new BoardService();
		return instance;
	}
	
	private BoardService() {}
	
	public List<BoardDTO> getBoardList(int start, int end){
		return dao.Select(start, end);
	}
	
	public int getTotalCnt() {
		return dao.getTotalCount();
	}
	
	// 파일 없이 업로드
	public boolean PostBoard(BoardDTO dto) {
		return dao.Insert(dto);
	}
	
	// 파일 포함해서 업로드
	public boolean PostBoard(BoardDTO dto, ArrayList<Part> parts) {
		// 업로드 처리
		// 1. 하위폴더명 지정
		String no = String.valueOf(dao.getLastNo() + 1);
		String email = dto.getWriter();
		Date now = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = simpleDateFormat.format(now);		
		String subPath = email + "/" + date + "/" + no;
		
		// 2. 기본업로드 Path + 하위폴더명
		File RealPath = new File(UploadPath + subPath);
		
		// 3. 하위폴더 생성
		if(!RealPath.exists()) {
			RealPath.mkdirs();
		}
		
		// 파일명 저장을 위한 StringBuffer 추가 : 파일이름을 세미콜론으로 구분해서 붙일거임
		//StringBuffer를 쓰는 이유 :concat으로 이름을 붙이면 파일 양이 많아지면 속도가 떨어진다.
		StringBuffer totalFilename = new StringBuffer();
		// 파일 사이즈 저장을 위한 StringBuffer 추가 : 파일사이즈도 세미콜론으로 구분해서 붙일거임
		StringBuffer totalFilesize = new StringBuffer();
		
		
		// 4. 반복처리로 part를 write해준다.
		for(Part part : parts) {
			if(part.getName().equals("files")) {
				String Filename = getFilename(part);
				
				int start=Filename.lastIndexOf(".");		//확장자구하기 위한 시작idx
				int end=Filename.length();					//확장자구하기 위한 끝idx
				String ext=Filename.substring(start,end);	//파일명잘라내기(확장자만)
				Filename = Filename.substring(0,start);		//파일명잘라내기(확장자제외)
				
				Filename = Filename + "_" + UUID.randomUUID().toString() + ext; 
				
				totalFilename.append(Filename + ";");
				totalFilesize.append(String.valueOf(part.getSize()) + ";"); //filesize는 Integer형이므로 String으로바꿔서 저장해준다.
				
				try {
					part.write(RealPath + "/" + Filename);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
		
		//dto에 파일이름과 파일사이즈를 저장한다.
		dto.setFilename(totalFilename.toString());
		dto.setFilesize(totalFilesize.toString());
		
		// Dao로 파일명 전달
		return dao.Insert(dto);
	}
	
	// 파일명 추출 함수
	private String getFilename(Part part) {
		String contentDisp=part.getHeader("content-disposition");
		String[] arr = contentDisp.split(";"); // 배열화 		
		String Filename=arr[2].substring(11,arr[2].length()-1);	
		return Filename;
	}
	
	// 게시물 하나 가져오기
	public BoardDTO getBoardDTO(int no) {
		return dao.Select(no);
	}
	
	// 단일파일 다운로드 서비스
	public boolean download(String filename,HttpServletRequest req, HttpServletResponse resp) {
		// 파일명, 이메일 계정, 등록날짜를 알아야한다. 
		HttpSession session = req.getSession();
		BoardDTO dto = (BoardDTO) session.getAttribute("dto");
		
		String email = dto.getWriter();
		String no = String.valueOf(dto.getNo());
		String regdate = dto.getRegdate().substring(0, 10);
		
		// 경로설정
		String downdir = "C://Users//ASUS//Documents//upload";
		String filepath = downdir + File.separator + email + File.separator + regdate + File.separator + no + File.separator+ filename;
		
		
		// 헤더설정
		resp.setContentType("application/octet-stream");
		
		// 문자셋 설정
		try {
			filename = URLEncoder.encode(filepath, "utf-8").replaceAll("\\+", "%20");
			resp.setHeader("Content-Disposition", "attatchment; fileName="+filename);
			
			// 스트림형성(다운로드 처리)
			FileInputStream fin = new FileInputStream(filepath);
			ServletOutputStream bout = resp.getOutputStream();
			int read = 0;
			byte buff[] = new byte[4096];
			while(true) {
				read = fin.read(buff, 0, buff.length); 
				// 파일 -> 서블릿으로 index 0부터 buffer.length까지 담기
				// read에는 읽어들인 byte수가 저장되고 더이상 읽을 것이 없으면 -1이 저장된다.
				if(read==-1) {
					break;
				}
				bout.write(buff, 0, read); // buffer 안의 데이터를 0부터 index까지 읽은 만큼 브라우저로 전송한다.
				// 서버-> 클라이언트(브라우저)방향이다.
			}
			bout.flush(); // 브라우저 방향으로 데이터 전송에 사용 된 버퍼공간 초기화
			bout.close(); // 출력스트림종료
			fin.close(); // 입력스트림 종료
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 파일 압축 다운로드
	public boolean downloadAllZip(BoardDTO dto, HttpServletResponse resp) {
		// 압축파일 경로
		String zipFileName="C://Users/ASUS/Downloads/ALL.zip";
		
		String email = dto.getWriter();
		String no = String.valueOf(dto.getNo());
		String regdate = dto.getRegdate().substring(0, 10);
		
		// 1. 경로설정
		String downdir = "C://Users//ASUS//Documents//upload";
		String subpath = downdir + File.separator + email + File.separator + regdate + File.separator + no + File.separator;
		
		// 2. 파일 목록 리스트 생성
		String filelist[] = dto.getFilename().split(";");
		
		// 헤더설정
		resp.setContentType("application/zip");
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attatchment; fileName=All.zip");
		
		// 문자셋 설정
		try {
			// 프로그램에서 파일 방향으로 ZipStream을 생성한다. java.util이다.
			ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipFileName));
			// FileOutputStream : 기본스트림
			// ZipOutputStream : 보조스트림
			for(int i=0; i<filelist.length; i++) {
				// 파일 -> 프로그램 으로 instream 형성해서 파일을 가져와야한다. subpath + filelist[i] : 파일의 절대경로
				FileInputStream fin = new FileInputStream(subpath + filelist[i]);
				
				// 어떤 파일이 들어갈건지 엔트리 형태로 넣어줘야한다. ZipEntry : 여러개의 파일들을 하나의 Zip 파일로 압축하는 자바 코드
				// 데이터를 엔트리 단위로 먼저 넣고 바이트 단위로 전달한다.
				ZipEntry ent = new ZipEntry(filelist[i]);
				zout.putNextEntry(ent);
				
				int read = 0;
				byte buff[] = new byte[4096];
				
				
				while(true) {
					read = fin.read(buff, 0, buff.length-1); //인덱스라서 -1해준것이다. buff라는 배열의 마지막인덱스까지넣어준다는 뜻이다.
					if(read==-1) break;
					zout.write(buff, 0, read);
				}
				zout.closeEntry(); // 엔트리 단위 종료 : 엔트리란 ? ㄴ묶어주는 파일들의 구분되는 단위이다.
				fin.close();
			}
			zout.close();
			
			/* 인코딩
			filename = URLEncoder.encode(filepath, "utf-8").replaceAll("\\+", "%20");
			resp.setHeader("Content-Disposition", "attatchment; fileName="+filename);
			*/
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void CountUp(int no) {
		dao.CountUp(no);
	}
	
	public boolean UpdateBoard(BoardDTO dto) {
		return dao.Update(dto);
	}
	
	public boolean BoardRemove(BoardDTO dto) {
		String email = dto.getWriter();
		String regdate = dto.getRegdate().substring(0, 10);
		String no =String.valueOf(dto.getNo());
		String dirpath = UploadPath + email + "/" + regdate + "/" + no;
		File dir = new File(dirpath); // 설정한 경로의 문자열을 파일 형식으로 바꿔준다. : 폴더가 된다.
		File[] filelist = dir.listFiles(); // 디렉토리 안에 있는 파일들을 배열에 불러온다. 디렉토리를 그냥 삭제할 수 없고 안에있는 파일을 삭제해줘야한다.
		for(File filename : filelist) {
			filename.delete(); // 파일 삭제
		}
		dir.delete(); // 폴더 삭제
		
		return dao.Delete(dto);
	}
}