package com.board.www;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

//import com.board.www.dao.BoardDAO;
import com.board.www.dto.MemberDTO;
import com.board.www.sv.BoardSV;
import com.board.www.sv.MemberSV;

public class BoardMain {
	// 필드
	public static Scanner s = new Scanner(System.in);
	//public static BoardDAO boardDAO = new BoardDAO(); // jdbc
	public static Connection connection = null;
	public static MemberDTO loginST = new MemberDTO();

	// 생성자
	public BoardMain() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 1단계
			connection = DriverManager.getConnection
					("jdbc:oracle:thin:@192.168.111.103:1521:orcl", "boardtest",	"boardtest");
			// 2단계
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버명 또는 ojdbc6.jar를 확인해주세요");
			// e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("url, id, pw나 쿼리문이 잘못됨");
			// e.printStackTrace();
			System.exit(0);// 프로세스 강제 종료
		}

	}
	// 메서드

	public static void main(String[] args) throws SQLException {
		// 기본적인 설정 : 스캐너, jdbc 연동, 주메뉴
		// jdbc를 활용하여 게시판 구현
		BoardMain boardMain = new BoardMain(); // 생성자 호출
		boolean run = true;
		loginST.setLoginStatus(false);
		System.out.println("MBC 아카데미 대나무숲 오신걸 환영합니다.");
		while (run) {
			System.out.println("1. 회원 | 2. 게시판 | 3. 종료");
			System.out.print(">>>");
			int select = s.next().charAt(0)-48;
			switch (select) {
			
			case 1:
				System.out.println("회원용 서비스로 진입합니다.");
				MemberSV memberSV = new MemberSV();
				loginST = memberSV.memberMenu(connection, s, loginST);
				//회원 서비스에서 나올 때 로그인 정보가 유지되야 됨.
				break;
			case 2:
				System.out.println("게시판 서비스로 진입합니다.");
				BoardSV boardSV = new BoardSV();
				boardSV.list(connection);
				break;
			case 3:
				System.out.println("게시판을 종료합니다.");
				run = false;
				break;
			default:
				System.out.println("0~2를 입력해주시기 바랍니다.");

			}

		} // while close

	}// main close

}
