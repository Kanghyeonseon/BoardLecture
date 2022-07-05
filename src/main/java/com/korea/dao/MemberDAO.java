package com.korea.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.korea.dto.MemberDTO;

public class MemberDAO {
	// DB 객체 생성
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "book_ex";
	private String pw = "1234";
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// 싱글톤 패턴
	private static MemberDAO instance;
	public static MemberDAO getInstance() {
		if (instance == null)
			instance = new MemberDAO();
		return instance;
	}
	
	// DB연결
	private MemberDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("DB Connected...");
		} catch(Exception e) { e.printStackTrace(); }
	}
	
	// INSERT 함수
	public boolean insert(MemberDTO dto) {
		try {
			pstmt=conn.prepareStatement("insert into tbl_member values(?,?,?,?,?)");
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getAddr1());
			pstmt.setString(4, dto.getAddr2());
			pstmt.setInt(5, dto.getGrade());
			int result = pstmt.executeUpdate();
			if(result>0) { return true; }
		} catch(Exception e) { e.printStackTrace(); 
		} finally { 
			try { pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		return false;
	} 
}
