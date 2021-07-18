package com.kay.service;

import static com.kay.common.JDBCTemplate.close;
import static com.kay.common.JDBCTemplate.getConnection;
import static com.kay.common.JDBCTemplate.commit;
import static com.kay.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kay.model.dao.MainDAO;
import com.kay.model.exception.MainException;
import com.kay.model.vo.Location;

public class MainService {
	public void exitProgram() {
		close(getConnection());
	}

	public ArrayList<Location> selectAllSeoulLocation() throws MainException {
		
		Connection conn = getConnection();
		
		ArrayList<Location> list = new MainDAO().selectAllSeoulLocation(conn);
		
		return list;
	}

	public int updateAllSeoulLocation(ArrayList<Location> list) throws MainException {
		Connection conn = getConnection();

		int result = 0;
		
		for (Location location : list) {
			if (location.getLoc_x() == null || location.getLoc_y() == null)
				continue;
			System.out.println(location);
			result = new MainDAO().updateAllSeoulLocation(conn, location);
			if (result <= 0) {
				rollback(conn);
				break;
			}
		}

		if (result > 0) {
			commit(conn);
		}

		return result;
		
	}
}
