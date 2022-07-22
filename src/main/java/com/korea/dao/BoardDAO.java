package com.korea.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.korea.dto.BoardDTO;
import com.korea.dto.ReplyDTO;

public class BoardDAO {
	// DB 객체 생성
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "book_ex";
	private String pw = "1234";
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// 싱글톤 패턴
	private static BoardDAO instance;
	public static BoardDAO getInstance() {
		if (instance == null)
			instance = new BoardDAO();
		return instance;
	}
	
	// DB연결
	private BoardDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("DB Connected...");
		} catch(Exception e) { e.printStackTrace(); }
	}
	
	// 시작페이지, 끝페이지 번호를 받아서 조회
	public List<BoardDTO> Select(int start, int end){
		ArrayList<BoardDTO> list = new ArrayList();
		BoardDTO dto = null;
		try {
			String sql
				="select rn, no, title, content, writer, regdate, pwd, count, ip, filename, filesize from ( select /*+ INDEX_DESC (tbl_board PK_NO) */ rownum as rn, no, title, content, writer, regdate, pwd, count, ip, filename, filesize from tbl_board where rownum<=?) where rn>=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new BoardDTO();
				dto.setNo(rs.getInt("no"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setPwd(rs.getString("pwd"));
				dto.setIp(rs.getString("ip"));
				dto.setFilename(rs.getString("filename"));
				dto.setFilesize(rs.getString("filesize"));
				dto.setCount(rs.getInt("count"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
			try { rs.close(); } catch(Exception e) { e.printStackTrace(); }
		} return list;
	}
	
	// 모든 게시물 개수 조회
	public int getTotalCount() {
		int result = 0;
		try {
			pstmt = conn.prepareStatement("select count(*) from tbl_board");
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstmt.close(); } catch(Exception e) { e.printStackTrace(); }
			try { rs.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		return result;
	}
	
	
	// 글 작성 메서드
	public boolean Insert(BoardDTO dto) {
		try {
			pstmt = conn.prepareStatement("insert into tbl_board values(TBL_BOARD_SEQ.nextval, ?, ?, ?, sysdate, ?, 0, ?, ?, ?)");
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getWriter());
			pstmt.setString(4, dto.getPwd());
			pstmt.setString(5, dto.getIp());
			pstmt.setString(6, dto.getFilename());
			pstmt.setString(7, dto.getFilesize());
			int result = pstmt.executeUpdate();
			if(result>0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
		}
		return false;
	}
	
	
	public BoardDTO Select(int No) {
		BoardDTO dto = new BoardDTO();
		try {
			pstmt = conn.prepareStatement("select * from tbl_board where no=?");
			pstmt.setInt(1, No);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setWriter(rs.getString("writer"));
				dto.setContent(rs.getString("content"));
				dto.setTitle(rs.getString("title"));
				dto.setPwd(rs.getString("pwd"));
				dto.setNo(rs.getInt("no"));
				dto.setIp(rs.getString("ip"));
				dto.setFilename(rs.getString("filename"));
				dto.setFilesize(rs.getString("filesize"));
				dto.setCount(rs.getInt("count"));
				dto.setRegdate(rs.getString("regdate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
		}
		return dto;
	}
	
	public int getLastNo() {
		try {
			pstmt = conn.prepareStatement("select last_number from user_sequences where sequence_name='TBL_BOARD_SEQ'");
			rs = pstmt.executeQuery();
			rs.next();
			int no = rs.getInt(1);
			return no;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { rs.close(); } catch (Exception e) { e.printStackTrace(); }
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
		}
		return 0;
	}
	
	public void CountUp(int no) {
		try {
			pstmt = conn.prepareStatement("update tbl_board set count=count+1 where no=?");
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
		}
	}
	
	public boolean Update(BoardDTO dto) {
		try {
			pstmt = conn.prepareStatement("update tbl_board set title=?, content=? where no=?");
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNo());
			int result = pstmt.executeUpdate();
			if(result>0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
		}
		return false;
	}
	
	public boolean Delete(BoardDTO dto) {
		try {
			pstmt = conn.prepareStatement("delete from tbl_board where no=?");
			pstmt.setInt(1, dto.getNo());
			int result = pstmt.executeUpdate();
			if(result>0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
		}
		return false;
	}
	
	public boolean replypost(ReplyDTO rdto) {
		try {
			pstmt = conn.prepareStatement("insert into tbl_reply values(REPLY_SEQ.nextval,?,?,?,sysdate)");
			pstmt.setInt(1, rdto.getBno());
			pstmt.setString(2, rdto.getWriter());
			pstmt.setString(3, rdto.getContent());
			int result = pstmt.executeUpdate();
			if(result>0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
		}
		return false;
	}
	
	public ArrayList<ReplyDTO> getReplylist(int bno) {
		ArrayList<ReplyDTO> list = new ArrayList();
		ReplyDTO dto = null;
		try {
			pstmt = conn.prepareStatement("select * from tbl_reply where bno = ? order by rno desc");
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new ReplyDTO();
				dto.setRno(rs.getInt("rno"));
				dto.setBno(rs.getInt("bno"));
				dto.setContent(rs.getString("content"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("writer"));
				dto.setRegdate(rs.getString("regdate"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
			try { rs.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		return list;
	}
	
	
	public int getTotalReplyCnt(int bno) {
		int tcnt = 0;
		try {
			pstmt = conn.prepareStatement("select count(*) from tbl_reply where bno = ?");
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			rs.next();
			tcnt = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
			try { rs.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		return tcnt;
	}
	
}
