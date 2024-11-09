package com.jsp.project.publicwifiinfo.repository;

import com.jsp.project.publicwifiinfo.db.SQLiteManager;
import com.jsp.project.publicwifiinfo.model.Location;

import java.sql.*;

public class LocationRepository {
    /**
     * 위치정보 단건 LOCATION_HISTORY 테이블에 insert
     * @param location
     * @return int 저장 건 수
     */
    public int insertLocationHistory(Location location) {
        int insertedCnt = 0;

        String sql = "INSERT INTO LOCATION_HISTORY(" +
                " LAT," +
                " LNT," +
                " SEARCH_DT" +
                " ) VALUES (" +
                "   ?," +
                "   ?," +
                "   (SELECT DATETIME('now', 'localtime'))" +
                " )";

        SQLiteManager manager = new SQLiteManager();
        Connection conn = manager.createConnection();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setDouble(1, location.getLat());
            pstmt.setDouble(2, location.getLnt());

            insertedCnt = pstmt.executeUpdate();

            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();

            if(conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    e.printStackTrace();
                }
            }
        } finally {
            if(pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            manager.closeConnection();
        }

        return insertedCnt;
    }
}
