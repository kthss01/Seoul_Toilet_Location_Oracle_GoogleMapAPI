package com.kay.model.dao;

import static com.kay.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kay.model.vo.Location;
import com.kay.model.exception.MainException;

public class MainDAO {
	private Properties prop = null;
	
	public MainDAO() {
		prop = new Properties();
		try {
			prop.load(new FileReader("resources/query.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Location> selectAllSeoulLocation(Connection conn) throws MainException {
		ArrayList<Location> list = null;

		Statement stmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectAllSeoulLocation");

		try {

			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);

			list = new ArrayList<Location>();

			while (rset.next()) {
				
				String address = rset.getString(1);
				String mainNum = rset.getString(2);
				String subNum = rset.getString(3);
				String buildingName = rset.getString(4);
				String loc_x = rset.getString(5);
				String loc_y = rset.getString(6);
				
				Location loc = new Location(address, mainNum, subNum, buildingName, loc_x, loc_y);
//				System.out.println(loc);
				
				list.add(loc);
			}

		} catch (Exception e) {
			throw new MainException("selectAllSeoulLocation 에러: " + e.getMessage());
		} finally {
			close(rset);
			close(stmt);
		}

		return list;
	}

	public int updateAllSeoulLocation(Connection conn, Location l) throws MainException {
		int result = 0;

		PreparedStatement pstmt = null;

		String sql = prop.getProperty("updateAllSeoulLocation");

		try {
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, l.getLoc_x());
			pstmt.setString(2, l.getLoc_y());
			pstmt.setString(3, l.getAddress());
			pstmt.setString(4, l.getMainNum());
			pstmt.setString(5, l.getSubNum());
			
//			System.out.println(pstmt.toString());
			
			result = pstmt.executeUpdate();

		} catch (Exception e) {
			throw new MainException("updateAllSeoulLocation 에러: " + e.getMessage());
		} finally {
			close(pstmt);
		}

		return result;
	}
}
