package com.jsp.project.publicwifiinfo.repository;

import com.jsp.project.publicwifiinfo.db.SQLiteManager;
import com.jsp.project.publicwifiinfo.model.Location;
import com.jsp.project.publicwifiinfo.model.Wifi;

import java.sql.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WifiRepository {
    /**
     * wifi목록 WIFI 테이블에 insert
     * @param wifiList
     * @return int 저장 건 수
     */
    public int insertWifiList(List<Wifi> wifiList) {
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

    /**
     * wifi목록 WIFI 테이블에서 조회
     * @return List<Wifi> wifi목록
     */
    public List<Wifi> selectWifiList(Location location) {
        final int LIMIT_CNT = 20;

        List<Wifi> wifiList = new ArrayList<>();

        String sql = "select MNGR_NO" +
                    "    , round((6371*acos(cos(radians(?))*cos(radians(LAT))*cos(radians(LNT)\n" +
                    "        - radians(?))+sin(radians(?))*sin(radians(LAT)))), 4) AS DISTANCE\n" +
                    "    , LAT -- 37.486592 (위도)\n" +
                    "    , LNT -- 126.8973568 (경도)\n" +
                    "    , WRDOFC\n" +
                    "    , WIFI_NM\n" +
                    "    , ADDR1\n" +
                    "    , ADDR2\n" +
                    "    , INSTALL_FLOOR\n" +
                    "    , INSTALL_TY\n" +
                    "    , INSTALL_MBY\n" +
                    "    , SVC_SE\n" +
                    "    , CMCRWR\n" +
                    "    , CNSTC_YEAR\n" +
                    "    , INOUT_DOOR\n" +
                    "    , CON_ENVIRONMENT\n" +
                    "    , WORK_DT\n" +
                " from WIFI\n" +
                " ORDER BY DISTANCE ASC\n" +
                " LIMIT ?";

        SQLiteManager manager = new SQLiteManager();
        Connection conn = manager.createConnection();
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setDouble(1, location.getLat());
            pstmt.setDouble(2, location.getLnt());
            pstmt.setDouble(3, location.getLat());
            pstmt.setInt(4, LIMIT_CNT);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Wifi wifi = new Wifi();
                wifi.setMngrNo(rs.getString("MNGR_NO"));
                wifi.setDistance(rs.getDouble("DISTANCE"));
                wifi.setLat(rs.getDouble("LAT"));
                wifi.setLnt(rs.getDouble("LNT"));
                wifi.setWrdofc(rs.getString("WRDOFC"));
                wifi.setWifiNm(rs.getString("WIFI_NM"));
                wifi.setAddr1(rs.getString("ADDR1"));
                wifi.setAddr2(rs.getString("ADDR2"));
                wifi.setInstallFloor(rs.getString("INSTALL_FLOOR"));
                wifi.setInstallTy(rs.getString("INSTALL_TY"));
                wifi.setInstallMby(rs.getString("INSTALL_MBY"));
                wifi.setSvcSe(rs.getString("SVC_SE"));
                wifi.setCmcrwr(rs.getString("CMCRWR"));
                wifi.setCnstcYear(rs.getString("CNSTC_YEAR"));
                wifi.setInoutDoor(rs.getString("INOUT_DOOR"));
                wifi.setConEnvironment(rs.getString("CON_ENVIRONMENT"));

                String sWorkDttm = String.valueOf(rs.getTimestamp("WORK_DT")); //2024-11-08 11:13:14.0
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                wifi.setWorkDt(LocalDateTime.parse(sWorkDttm, formatter));

                wifiList.add(wifi);
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

        return wifiList;
    }

}
