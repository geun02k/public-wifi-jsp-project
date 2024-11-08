package com.jsp.project.publicwifiinfo.repository;

import com.jsp.project.publicwifiinfo.db.SQLiteManager;
import com.jsp.project.publicwifiinfo.model.Wifi;

import java.sql.*;

import java.util.List;

public class WifiRepository {
    /**
     * wifi 목록 WIFI 테이블에 insert
     * @param wifiList
     * @return int 저장 건 수
     */
    public int insertWifiInfoList(List<Wifi> wifiList) {
        String sql = "INSERT INTO WIFI(" +
                " MNGR_NO" +
                " , WRDOFC" +
                " , WIFI_NM" +
                " , ADDR1" +
                " , ADDR2" +

                " , INSTALL_FLOOR" +
                " , INSTALL_TY" +
                " , INSTALL_MBY" +
                " , SVC_SE" +
                " , CMCRWR" +

                " , CNSTC_YEAR" +
                " , INOUT_DOOR " +
                " , CON_ENVIRONMENT" +
                " , LAT" +
                " , LNT" +

                " , WORK_DT" +

                " ) VALUES (" +
                " ?, ?, ?, ?, ?" +
                " , ?, ?, ?, ?, ?" +
                " , ?, ?, ?, ?, ?" +
                " , ?" +
                ")";

        SQLiteManager manager = new SQLiteManager();
        Connection conn = manager.createConnection();
        PreparedStatement pstmt = null;

        int insertedCnt = 0;
        int failCnt = 0;
        String mngrNo = "";
        try {
            for(Wifi wifi : wifiList) {
                pstmt = conn.prepareStatement(sql);

                mngrNo = wifi.getMngrNo();
                pstmt.setString(1, wifi.getMngrNo());
                pstmt.setString(2, wifi.getWrdofc());
                pstmt.setString(3, wifi.getWifiNm());
                pstmt.setString(4, wifi.getAddr1());
                pstmt.setString(5, wifi.getAddr2());

                pstmt.setString(6, wifi.getInstallFloor());
                pstmt.setString(7, wifi.getInstallTy());
                pstmt.setString(8, wifi.getInstallMby());
                pstmt.setString(9, wifi.getSvcSe());
                pstmt.setString(10, wifi.getCmcrwr());

                pstmt.setString(11, wifi.getCnstcYear());
                pstmt.setString(12, wifi.getInoutDoor());
                pstmt.setString(13, wifi.getConEnvironment());
                pstmt.setDouble(14, wifi.getLat());
                pstmt.setDouble(15, wifi.getLnt());

                //2024-11-08 11:13:14.0
                pstmt.setTimestamp(16, Timestamp.valueOf(wifi.getWorkDt()));

                int affected = pstmt.executeUpdate();
                if(affected > 0) {
                    insertedCnt++;
                } else {
                    failCnt++;
                }
            }

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("insertedCnt=" + insertedCnt + " / failCnt=" + failCnt + " / mngrNo => " + mngrNo);

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
