-- wifi정보
CREATE TABLE `WIFI` (
	`MNGR_NO`         VARCHAR(20)  NOT NULL , -- 관리번호 (SQLite는 자리수를 제한하지는 않음.)
	`WRDOFC`          VARCHAR(20)  NULL     , -- 자치구
	`WIFI_NM`         VARCHAR(500) NULL     , -- 와이파이명
	`ADDR1`           VARCHAR(300) NULL     , -- 도로명주소
	`ADDR2`           VARCHAR(300) NULL     , -- 상세주소
	`INSTALL_FLOOR`    VARCHAR(20)  NULL     , -- 설치위치(층)
	`INSTALL_TY`       VARCHAR(50)  NULL     , -- 설치유형
	`INSTALL_MBY`      VARCHAR(50)  NULL     , -- 설치기관
	`SVC_SE`          VARCHAR(50)  NULL     , -- 서비스구분
	`CMCRWR`          VARCHAR(50)  NULL     , -- 망종류
	`CNSTC_YEAR`      YEAR         NULL     , -- 설치년도
	`INOUT_DOOR`      VARCHAR(2)   NULL     , -- 실내외구분
	`CON_ENVIRONMENT` VARCHAR(50)  NULL     , -- wifi접속환경
	`LAT`             DOUBLE       NULL     , -- Y좌표
	`LNT`             DOUBLE       NULL     , -- X좌표
	`WORK_DT`         DATETIME     NULL     , -- 작업일자
    PRIMARY KEY (MNGR_NO)
);

-- 위치 히스토리
CREATE TABLE `LOCATION_HISTORY` (
	`ID`        INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL, -- 아이디
	`LAT`       DOUBLE   NULL     , -- X좌표
	`LNT`       DOUBLE   NULL     , -- Y좌표
	`SEARCH_DT` DATETIME NULL      -- 조회일자
);
