package com.jsp.project.publicwifiinfo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor //기본생성자 자동생성
public class Wifi {
    private String mngrNo;      //관리번호
    private String wrdofc;      // 자치구
    private String wifiNm;      // 와이파이명
    private String addr1;       // 도로명주소
    private String addr2;       // 상세주소
    private String installFloor; // 설치위치(층)
    private String installTy;   // 설치유형
    private String installMby;  // 설치기관
    private String svcSe;       // 서비스구분
    private String cmcrwr;      // 망종류
    private String cnstcYear;     // 설치년도
    private String inoutDoor;   // 실내외구분
    private String conEnvironment; // wifi접속환경
    private double lat;         // Y좌표
    private double lnt;         // X좌표
    private LocalDateTime workDt; // 작업일자
}
