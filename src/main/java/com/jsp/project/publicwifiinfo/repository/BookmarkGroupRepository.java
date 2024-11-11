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
}
