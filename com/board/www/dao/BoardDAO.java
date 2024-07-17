package com.board.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.board.www.dto.BoardDTO;

public class BoardDAO {
	// 데이터베이스 처리용 CRUD
	public void list(Connection connection) {
		//BoardDTO boardDTO = null;
		try {
			//boardDTO = new BoardDTO();
			String sql = "select bno, btitle, bcontent, bwriter, bdate from boardTBL order by bno asc";
			// board table의 데이터를 가져온다.
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			// 3단계
			ResultSet resultSet = preparedStatement.executeQuery();
			// 4단계

			while (resultSet.next()) {// 표형식으로 리턴된 값 유무 판단

				/*
				 * boardDTO.setBno(resultSet.getInt("bno"));
				 * boardDTO.setBtitle(resultSet.getString("btitle"));
				 * boardDTO.setBcontents(resultSet.getString("bcontent"));
				 * boardDTO.setBwriter(resultSet.getString("bwriter"));
				 * boardDTO.setBdate(resultSet.getDate("bdate"));
				 */
							
				  System.out.print(resultSet.getInt("bno")+"\t");
				  System.out.print(resultSet.getString("btitle")+"\t");
				  //System.out.println(resultSet.getString("bcontents"));
				  System.out.print(resultSet.getString("bwriter")+"\t\t");
				  System.out.println(resultSet.getDate("bdate")+"\t");
				
				
			}
			//5단계
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.println("BoardDAO.list() sql문 오류");
			//e.printStackTrace();
		}
	}// list() close

}
