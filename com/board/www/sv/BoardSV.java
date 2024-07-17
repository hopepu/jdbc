package com.board.www.sv;

import java.sql.Connection;

import com.board.www.dao.BoardDAO;

public class BoardSV {
	// board의 부메뉴(c,r,u,d,l)
	
	public void list(Connection connection) {// 게시물 목록 보기
		
		System.out.println("=================");
		System.out.println("===대나무숲게시판===");
		System.out.println("[게시물목록]");
		System.out.println("--------------------------------------------------");
		System.out.println("no	title		writer	    	 date  ");
		System.out.println("--------------------------------------------------");
		
		BoardDAO boardDAO = new BoardDAO();
		boardDAO.list(connection);
	}

}
