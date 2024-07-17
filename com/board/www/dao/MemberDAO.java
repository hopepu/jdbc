package com.board.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.board.www.dto.MemberDTO;

import oracle.sql.DATE;

public class MemberDAO {

	// public MemberDAO() {}

	// public MemberDAO(Connection connection) {}
	// 커스텀 생성자

	public void join(Connection connection, Scanner s, MemberDTO loginST) {
		MemberDTO joinDTO = new MemberDTO();
		System.out.println("사용하실 id를 입력해주세요");
		System.out.print(">>>");
		try {
			joinDTO.setMId(s.next(), connection);

			if (joinDTO.getMId() != null) {
				System.out.println("사용하실 pw를 입력해주세요");
				System.out.print(">>>");
				joinDTO.setMPw(s.next());

				try {
					String sql = "insert into memberTBL(mno, mid, mpw, mdate) values (board_seq.nextval,?,?,sysdate)";
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, joinDTO.getMId());
					preparedStatement.setString(2, joinDTO.getMPw());
					boolean result = preparedStatement.execute();

					PreparedStatement close;

				} catch (Exception e) {
					System.out.println("쿼리문 오류 확인하세요");
				} // try close

			} // if close

		} catch (SQLException e) {
		}

	}

	public int searchMid(String mid, Connection connection) throws SQLException {
		String sql = "select * from memberTBL where mid= ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, mid);
		int result = preparedStatement.executeUpdate();
		preparedStatement.close();

		return result;

	}

	public MemberDTO login(Connection connection, Scanner s, MemberDTO loginST) throws SQLException {
		MemberDTO memberDTO = new MemberDTO();
		System.out.print("ID : ");
		memberDTO.setMId(s.next());
		System.out.print("PW : ");
		memberDTO.setMPw(s.next());
		String sql = "select * from memberTBL where mid = ? and mpw = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, memberDTO.getMId());
		preparedStatement.setString(2, memberDTO.getMPw());

		int result = preparedStatement.executeUpdate();
		if (result == 1) {
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				memberDTO.setMno(resultSet.getInt("mno"));
				memberDTO.setMId(resultSet.getString("mid"));
				memberDTO.setMPw(resultSet.getString("mpw"));
				memberDTO.setMDate(resultSet.getDate("mdate"));
				System.out.println(memberDTO.getMId() + "님 환영합니다!");
				resultSet.close();
				memberDTO.setLoginStatus(true);
			}
		} else {
			System.out.println("ID 혹은 PW가 잘못되었습니다.");
			memberDTO = loginST;
		}

		preparedStatement.close();

		return memberDTO;

	}

	public MemberDTO modify(Connection connection, Scanner s, MemberDTO loginST) throws SQLException {
		MemberDTO modifyDTO = new MemberDTO();

		System.out.println("비밀번호 변경 페이지입니다. 비밀번호를 변경하시겠습니까?");
		System.out.println("1. 예 | 2. 아니오");
		int select = s.next().charAt(0) - 48;
		if (select == 1) {
			System.out.println("본인확인을 위하여 비밀번호를 다시 한번 입력해주세요.");
			System.out.print(">>>");
			modifyDTO.setMPw(s.next());
			boolean run = true;
//			System.out.println(modifyDTO.getMPw());
//			System.out.println(loginST.getMPw());

			if (modifyDTO.getMPw().equals(loginST.getMPw())) {
				while (run) {
					modifyDTO = loginST;
					System.out.println("변경하실 비밀번호를 입력해주세요");
					System.out.print(">>>");
					String pw1 = s.next();
					System.out.println("비밀번호를 한번 더 입력해주세요");
					System.out.print(">>>");
					String pw2 = s.next();

					if (pw1.equals(pw2)) {
						modifyDTO.setMPw(pw2);
						String sql = "update memberTBL set mpw = ? where mid = ?";
						PreparedStatement preparedStatement = connection.prepareStatement(sql);
						preparedStatement.setString(1, modifyDTO.getMPw());
						preparedStatement.setString(2, loginST.getMId());
						int result = preparedStatement.executeUpdate();

						if (result == 1) {
							System.out.println("비밀번호 변경에 성공하였습니다.");
												
							run = false;
						} else {
							System.out.println("알수없는 오류로 비밀번호 변경에 실패하였습니다.");
						}
						preparedStatement.close();

					} else {
						System.out.println("입력하신 비밀번호가 일치하지 않습니다.");
						System.out.println("다시 입력해주십시요.");
					}

				}
			} else {
				System.out.println("비밀번호 오류입니다.");
				System.out.println("회원관리 메뉴로 돌아갑니다.");
				modifyDTO = loginST;

			}
		} else {
			modifyDTO = loginST;
			System.out.println("회원관리 메뉴로 돌아갑니다.");
		}

		return modifyDTO;
	}

	public MemberDTO withdraw(Connection connection, Scanner s, MemberDTO loginST) throws SQLException {
		System.out.println("회원탈퇴 하시겠습니까?");
		System.out.println("1. 예 | 2. 아니오");
		int select = s.next().charAt(0) - 48;
		if (select == 1) {
			System.out.println("본인확인을 위하여 비밀번호를 다시 한번 입력해주세요.");
			System.out.print(">>>");
			if(loginST.getMPw().equals(s.next())) {
				String sql = "delete from memberTBL where mid = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, loginST.getMId());
				int result = preparedStatement.executeUpdate();

				if (result == 1) {
					System.out.println("계정을 삭제하였습니다.");
					loginST = logout(loginST);
					
				
				} else {
					System.out.println("알수없는 오류로 계정삭제에 실패하였습니다.");
				}
				preparedStatement.close();
				
				
			} else {
				System.out.println("비밀번호 오류입니다.");
				System.out.println("회원관리 메뉴로 돌아갑니다.");
			}
		}
		return loginST;
		
		
	}

	public MemberDTO logout(MemberDTO loginST) {
		MemberDTO logoutDTO = new MemberDTO();
		loginST = logoutDTO;
		loginST.setLoginStatus(false);
		return loginST;
	}

}
