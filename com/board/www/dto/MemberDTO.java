package com.board.www.dto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import com.board.www.dao.MemberDAO;

public class MemberDTO {
	MemberDAO mDAO = new MemberDAO();
	//필드
	private int mno;
	private String mid;
	private String mpw;
	private Date mdate;
	private boolean loginStatus;
	
	//생성자
	
	public MemberDTO(){};
		
	public MemberDTO(int mno, String mid, String mpw, Date mdate) {
		super();
		this.mno = mno;
		this.mid = mid;
		this.mpw = mpw;
		this.mdate = mdate;
	}

	//메서드
	
	public int getMno() {
		return mno;
	}
	public String getMId() {
		return mid;
	}
	public String getMPw() {
		return mpw;
	}
	public Date getMDate() {
		return mdate;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public void setMId(String mid, Connection connection) throws SQLException {
		
		int result = mDAO.searchMid(mid, connection);
		if(result==0) {
			this.mid = mid;
		} else {
			System.out.println("사용할수 없는 id입니다.");
		}
		
	}
	public void setMPw(String mpw) {
		this.mpw = mpw;
	}
	public void setMDate(Date mdate) {
		this.mdate = mdate;
	}

	public void setMId(String mid) {
		this.mid = mid;
		
	}

	public boolean isLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

}
