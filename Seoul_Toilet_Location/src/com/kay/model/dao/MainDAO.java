package com.kay.model.dao;

import static com.kay.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.kay.model.vo.Location;
import com.kay.model.vo.Toilet;
import com.kay.common.LocationTemplate;
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

	public Map<String, Toilet> selectAllToilet(Connection conn) throws MainException {
		Map<String, Toilet> map = null;

		Statement stmt = null;
		ResultSet rset = null;

		String sql = prop.getProperty("selectAllToilet");

		try {

			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);

			map = new HashMap<String, Toilet>();

			while (rset.next()) {
				int index = 1;
				String id = rset.getString(index++);
				String useable = rset.getString(index++);
				String locationName = rset.getString(index++);
				String usingTime = rset.getString(index++);
				String guName = rset.getString(index++);
				String roadAddress = rset.getString(index++);
				String numAddress = rset.getString(index++);
				String locX = rset.getString(index++);
				String locY = rset.getString(index++);
				String phone = rset.getString(index++);

				String detailName1 = rset.getString(index++);
				String detailContent1 = rset.getString(index++);
				String detailName2 = rset.getString(index++);
				String detailContent2 = rset.getString(index++);
				String detailName3 = rset.getString(index++);
				String datailContent3 = rset.getString(index++);
				String detailName4 = rset.getString(index++);
				String detailContent4 = rset.getString(index++);
				String detailName5 = rset.getString(index++);
				String detailContent5 = rset.getString(index++);

				map.put(id,
						new Toilet(id, useable, locationName, usingTime, guName, roadAddress, numAddress, locX, locY,
								phone, detailName1, detailContent1, detailName2, detailContent2, detailName3,
								datailContent3, detailName4, detailContent4, detailName5, detailContent5));
			}

		} catch (Exception e) {
			throw new MainException("selectAllToilet 에러: " + e.getMessage());
		} finally {
			close(rset);
			close(stmt);
		}

		return map;
	}

	public Location selectAddressSeoulLocation(Connection conn, String address, String mainNum, String subNum) throws MainException {
		Location location = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectAddressSeoulLocation");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, address);
			pstmt.setString(2, mainNum);
			pstmt.setString(3, subNum);

			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				String siGunGuName = rset.getString("SI_GUN_GU_NAME");
				String buildingName = rset.getString("BUILDING_NAME");
				String hangJungDongName = rset.getString("HANG_JUNG_DONG_NAME");
				String loc_x = rset.getString("LOC_X");
				String loc_y = rset.getString("LOC_Y");
				
				location = new Location(siGunGuName, address, mainNum, subNum, buildingName, hangJungDongName, loc_x, loc_y);
			}
			
		} catch (Exception e) {
			throw new MainException("selectAddressSeoulLocation 에러 : " + e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return location;
	}

	public Location selectXYSeoulLocation(Connection conn, String loc_x, String loc_y) throws MainException {
		Location location = null;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectXYSeoulLocation");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loc_x);
			pstmt.setString(2, loc_y);
			pstmt.setString(3, loc_x);
			pstmt.setString(4, loc_y);

			rset = pstmt.executeQuery();
			
			while (rset.next()) {
				String siGunGuName = rset.getString("SI_GUN_GU_NAME");
				String address = rset.getString("ROAD_ADDRESS");
				String mainNum = rset.getString("BUILDING_MAIN_NUM");
				String subNum = rset.getString("BUILDING_SUB_NUM");
				String buildingName = rset.getString("BUILDING_NAME");
				String hangJungDongName = rset.getString("HANG_JUNG_DONG_NAME");
				
				
				location = new Location(siGunGuName, address, mainNum, subNum, buildingName, hangJungDongName, loc_x, loc_y);
			}
			
		} catch (Exception e) {
			throw new MainException("selectXYSeoulLocation 에러 : " + e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return location;
	}

	public void selectFindToilet(Connection conn, String loc_x, String loc_y) throws MainException {
		Map<String, Toilet> toilet = LocationTemplate.getToilet();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectFindToilet");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loc_y);
			pstmt.setString(2, loc_x);
			
			rset = pstmt.executeQuery();
			
			int i = 0;
			String[] nearToiletIdArr = LocationTemplate.getNearToiletId();
			
			while (rset.next()) {
				String id = rset.getString("TOILET_ID");
				String distance = rset.getString("DISTANCE");
				
				toilet.get(id).setDistance(Float.parseFloat(distance));
				nearToiletIdArr[i++] = id;
			}
			
		} catch (Exception e) {
			throw new MainException("selectFindToilet 에러 : " + e.getMessage());
		}

	}
}
