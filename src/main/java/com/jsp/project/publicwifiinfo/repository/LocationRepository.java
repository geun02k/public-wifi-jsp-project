package com.jsp.project.publicwifiinfo.repository;

import com.jsp.project.publicwifiinfo.db.SQLiteManager;
import com.jsp.project.publicwifiinfo.model.Location;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LocationRepository {
    /**
     * 위치히스토리 단건 LOCATION_HISTORY 테이블에서 delete
     * @param location 위치정보
     * @return int 삭제 건 수
     */
    public int deleteLocationHistory(Location location) {
        int deleteCnt = 0;

        String sql = "DELETE FROM LOCATION_HISTORY" +
                    " WHERE ID = ? ";

        SQLiteManager manager = new SQLiteManager();
        Connection conn = manager.createConnection();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, location.getId());
            deleteCnt = pstmt.executeUpdate();

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

        return deleteCnt;
    }


    /**
     * 위치히스토리 단건 LOCATION_HISTORY 테이블에 insert
     * @param location 위치정보
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

    /**
     * 위치히스토리목록 LOCATION_HISTORY 테이블에서 조회
     * @return List<Location> 위치정보목록
     */
    public List<Location> selectLocationHistoryList() {
        List<Location> list = new ArrayList<>();

        String sql = "SELECT ID, LAT, LNT, SEARCH_DT \n" +
                " FROM LOCATION_HISTORY \n" +
                " ORDER BY SEARCH_DT DESC \n";

        SQLiteManager manager = new SQLiteManager();
        Connection conn = manager.createConnection();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Location location = new Location();
                location.setId(rs.getInt("ID"));
                location.setLat(rs.getDouble("LAT"));
                location.setLnt(rs.getDouble("LNT"));
                String sDttm = String.valueOf(rs.getTimestamp("SEARCH_DT")); //2024-11-08 11:13:14.0
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                location.setSearchDt(LocalDateTime.parse(sDttm, formatter));

                list.add(location);
            }

        } catch (SQLException e) {
            e.printStackTrace();

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

        return list;
    }
}
