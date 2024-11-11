package com.jsp.project.publicwifiinfo.repository;

import com.jsp.project.publicwifiinfo.db.SQLiteManager;
import com.jsp.project.publicwifiinfo.model.BookmarkGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static common.DataTypeChange.changeSqlDtToJavaDt;

public class BookmarkGroupRepository {
    /**
     * 북마크그룹목록을 BOOKMARK_GROUP 테이블에서 조회
     * @return List<BookmarkGroup> 북마크그룹목록
     */
    public List<BookmarkGroup> selectBookmarkGroupList() {
        List<BookmarkGroup> list = new ArrayList<>();

        String sql = "SELECT CODE, NAME, SEQ" +
                    "       , REG_DT \n" +
                    "       , UPDATE_DT \n" +
                    " FROM BOOKMARK_GROUP \n" +
                    " ORDER BY SEQ \n";

        SQLiteManager manager = new SQLiteManager();
        Connection conn = manager.createConnection();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                BookmarkGroup group = new BookmarkGroup();

                group.setCode(rs.getInt("CODE"));
                group.setName(rs.getString("NAME"));
                group.setSeq(rs.getInt("SEQ"));
                group.setRegDt(changeSqlDtToJavaDt(rs.getTimestamp("REG_DT")));
                group.setUpdateDt(changeSqlDtToJavaDt(rs.getTimestamp("UPDATE_DT")));

                list.add(group);
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


    /**
     * 순서와 일치하는 북마크그룹목록을 BOOKMARK_GROUP 테이블에서 조회
     * @param groupSeq 북마크그룹 순서
     * @return int 일치하는 순서 데이터 개수
     */
    public int selectBookmarkGroupCntBySeq(int groupSeq) {
        List<BookmarkGroup> list = new ArrayList<>();

        String sql = "SELECT COUNT(*) AS BOOKMARK_GROUP_CNT \n" +
                " FROM BOOKMARK_GROUP \n" +
                " WHERE SEQ = ? \n" +
                " GROUP BY SEQ \n";

        SQLiteManager manager = new SQLiteManager();
        Connection conn = manager.createConnection();
        PreparedStatement pstmt = null;

        int resultCnt = 0;
        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, groupSeq);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                resultCnt = rs.getInt("BOOKMARK_GROUP_CNT");
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

        return resultCnt;
    }

    /**
     * 북마크그룹을 BOOKMARK_GROUP 테이블에 insert
     * @param group 북마크그룹정보를 가진 BookmarkGroup객체
     * @return int 저장 건 수
     */
    public int insertBookmarkGroup(BookmarkGroup group) {
        int insertedCnt = 0;

        String sql = "INSERT INTO BOOKMARK_GROUP(" +
                " NAME," +
                " SEQ," +
                " REG_DT" +
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

            pstmt.setString(1, group.getName());
            pstmt.setInt(2, group.getSeq());

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
