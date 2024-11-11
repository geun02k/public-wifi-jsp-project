package com.jsp.project.publicwifiinfo.repository;

import com.jsp.project.publicwifiinfo.db.SQLiteManager;
import com.jsp.project.publicwifiinfo.model.Bookmark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookmarkRepository {

    /**
     * 동일 북마크그룹, 와이파이관리번호에 일치하는 북마크목록 건 수 BOOKMARK 테이블에서 조회
     * @param bookmark 북마크 그룹, 와이파이관리번호 정보를 가진 Bookmark 객체
     * @return int 동일 북마크 그룹, 관리번호에 대한 데이터 개수
     */
    public int selectDuplBookmarkCnt(Bookmark bookmark) {
        int duplicationCnt = 0;

        String sql = "SELECT COUNT(*) AS BOOKMARK_CNT \n" +
                " FROM BOOKMARK \n" +
                " WHERE WIFI_MNGR_NO = ? \n" +
                " AND GROUP_CODE = ? \n";

        SQLiteManager manager = new SQLiteManager();
        Connection conn = manager.createConnection();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, bookmark.getWifiMngrNo());
            pstmt.setInt(2, bookmark.getGroupCode());

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                duplicationCnt = rs.getInt("BOOKMARK_CNT");
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

        return duplicationCnt;
    }

    /**
     * 북마크정보를 BOOKMARK 테이블에 insert
     *
     * @param bookmark 북마크정보를 가진 Bookmark객체
     * @return int 저장 건 수
     */
    public int insertBookmark(Bookmark bookmark) {
        int insertedCnt = 0;

        String sql = "INSERT INTO BOOKMARK(" +
                " WIFI_MNGR_NO," +
                " GROUP_CODE," +
                " REG_DT " +
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

            pstmt.setString(1, bookmark.getWifiMngrNo());
            pstmt.setInt(2, bookmark.getGroupCode());

            insertedCnt = pstmt.executeUpdate();

            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();

            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    e.printStackTrace();
                }
            }
        } finally {
            if (pstmt != null) {
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
