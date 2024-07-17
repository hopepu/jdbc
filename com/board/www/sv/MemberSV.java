package com.board.www.sv;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.board.www.dao.MemberDAO;
import com.board.www.dto.MemberDTO;

public class MemberSV {

	MemberDAO mDAO = new MemberDAO();

	public MemberDTO memberMenu(Connection connection, Scanner s, MemberDTO loginST) throws SQLException {
		// 회원에 대한 처리 (C 회원가입 R 로그인 U 정보수정 D 회원탈퇴)
		System.out.println("회원관리용 서비스로 진입");
		boolean memberRun = true;

		while (memberRun) {
//			System.out.println(loginST.getMId());
//			System.out.println(loginST.getMPw());
//			System.out.println(loginST.isLoginStatus());
			System.out.println("1.회원가입 | 2. 로그인 | 3. 회원정보수정 | 4. 회원탈퇴 | 5. 로그아웃 | 6. 메인메뉴");
			System.out.print(">>>");
			int memberSelect = s.next().charAt(0) - 48;
			switch (memberSelect) {
			case 1:
				System.out.println("회원가입용 메서드로 진입");
				join(connection, s, loginST);
				break;

			case 2:
				
				if (!loginST.isLoginStatus()) {
					System.out.println("로그인 메서드로 진입");
					loginST = login(connection, s, loginST);
				} else {
					System.out.println(loginST.getMId()+"님은 이미 로그인중이십니다.");
				}
				break;
			case 3:
				System.out.println("비밀번호 변경 메서드로 진입");
				if (loginST.isLoginStatus()) {
					loginST = modify(connection, s, loginST);
				} else {
					System.out.println("로그인이 필요합니다. 로그인 메뉴로 이동합니다.");
					loginST = login(connection, s, loginST);
				}
				break;
			case 4:
				System.out.println("회원탈퇴 메서드로 진입");
				withdraw(connection, s, loginST);
				break;
				
			case 5:
				System.out.println("로그아웃 메서드로 진입");
				loginST=mDAO.logout(loginST);
				System.out.println("로그아웃 되었습니다.");

			case 6:
				System.out.println("메인메뉴로 돌아갑니다.");
				memberRun = false;
				break;

			default:
				System.out.println("1~5를 입력해주세요");
			}

		}
		return loginST;

	}

	private void withdraw(Connection connection, Scanner s, MemberDTO loginST) throws SQLException {
		loginST = mDAO.withdraw(connection, s, loginST);

	}// withdraw close

	private MemberDTO modify(Connection connection, Scanner s, MemberDTO loginST) throws SQLException {
		loginST = mDAO.modify(connection, s, loginST);
		
		return loginST;
	}// modify close

	private MemberDTO login(Connection connection, Scanner s, MemberDTO loginST) throws SQLException {
		loginST = mDAO.login(connection, s, loginST);
		return loginST;

	}// login close

	private void join(Connection connection, Scanner s, MemberDTO loginST) throws SQLException {
		mDAO.join(connection, s, loginST);

	}// join close

}
