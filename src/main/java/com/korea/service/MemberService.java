package com.korea.service;

import org.mindrot.bcrypt.BCrypt;

import com.korea.dao.MemberDAO;
import com.korea.dto.MemberDTO;

public class MemberService {
	MemberDAO dao = MemberDAO.getInstance();
	public BCrypt passwordEncoder = new BCrypt();
	
	
	// 싱글톤 패턴
	private static MemberService instance = null;
	public static MemberService getInstance() {
		if(instance==null) instance = new MemberService();
		return instance;
	}
	private MemberService() {}
	
	public boolean MemberInsert(MemberDTO dto) {
		//패스워드 암호화
		String pwd = passwordEncoder.hashpw(dto.getPwd(), passwordEncoder.gensalt());
		System.out.println("PWD(EN)" + pwd);
		dto.setPwd(pwd);
		
		return dao.insert(dto);
	}
	public MemberDTO MemberSearch(String email) {
		return dao.Select(email);
	}
}
