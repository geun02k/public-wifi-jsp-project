package com.jsp.project.publicwifiinfo.service;

import com.jsp.project.publicwifiinfo.model.Location;

import java.util.List;


public interface LocationService {

    // 내 위치 가져오기 (이건 클라이언트 단에서 해결되어야함.)
    //Location getCurrentLocationInfo();

    // 위치히스토리 삭제
    int deleteLocationHistory(Location location);

    // 위치히스토리 저장
    int saveLocationHistory(Location location);

    // 위치히스토리 목록조회
    List<Location> getLocationHistoryList();

}
