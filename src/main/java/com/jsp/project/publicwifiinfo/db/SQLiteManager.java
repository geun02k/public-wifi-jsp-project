package com.jsp.project.publicwifiinfo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteManager {
    // DB 변수
    private static final String JDBC_DRIVER = "org.sqlite.JDBC";
    private static final String FILE_DB_URL = "jdbc:sqlite:C:\\workspace\\public-wifi-info\\public_wifi";
    //private static final String FILE_DB_URL = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\public_wifi";
    private static final String MEMORY_DB_URL = "jdbc:sqlite::memory";

    // DB 옵션변수
    private static final boolean OPT_AUTO_COMMIT = false;
    private static final int OPT_VALID_TIMEOUT = 500;

    // 변수
    private Connection conn = null;
    private String driver = null;
    private String url = null;

    // 생성자
    public SQLiteManager() {
        this.driver = JDBC_DRIVER;
        this.url = FILE_DB_URL;
//        this(FILE_DB_URL);
    }

    /**
     * DB 연결
     * @return Connection 객체
     */
    public Connection createConnection() {
        try {
            // JDBC Driver Class 로드
            Class.forName(this.driver);

            // DB 연결 객체 생성
            this.conn = DriverManager.getConnection(this.url);

            System.out.println("CONNECTED");

            // 옵션설정 - 자동커밋X
            this.conn.setAutoCommit(OPT_AUTO_COMMIT);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return this.conn;
    }
    /**
     * DB 연결 종료
     */
    public void closeConnection() {
        try {
            if(this.conn != null) {
                this.conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conn = null;
            System.out.println("CLOSED");
        }
    }

    /**
     * DB 연결 객체 가져오기
     * @return Connection 객체
     */
    public Connection getConnection() {
        return this.conn;
    }
}
