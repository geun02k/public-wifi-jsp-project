-- 북마크그룹
CREATE TABLE 'BOOKMARK_GROUP' (
    'CODE'      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, --그룹코드
    'NAME'      VARCHAR(50) NULL,       -- 그룹명
    'SEQ'       INT         NULL,       -- 순서
    'REG_DT'    DATETIME    NOT NULL,   -- 등록일자
    'UPDATE_DT' DATETIME    NOT NULL    -- 수정일자
);

-- 북마크목록
CREATE TABLE 'BOOKMARK' (
	'ID'            INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL, -- 북마크아이디
	'WIFI_MNGR_NO'  VARCHAR(20)  NULL, -- 와이파이 관리번호
	'GROUP_CODE'    INT         NULL, -- 북마크 그룹코드
    'REG_DT'        DATETIME    NOT NULL  -- 등록일자
);
