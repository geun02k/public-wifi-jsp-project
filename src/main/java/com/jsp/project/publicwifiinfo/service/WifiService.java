package com.jsp.project.publicwifiinfo.service;

import com.jsp.project.publicwifiinfo.model.Location;
import com.jsp.project.publicwifiinfo.model.Wifi;

import java.util.List;

public interface WifiService {

    // 내 위치 가져오기 (이건 클라이언트 단에서 해결되어야함.)
    //Location getCurrentLocationInfo();

    // open api 와이파이 정보 저장(api호출 후 저장)
    int saveWifiList();

    // 근처 wifi정보보기(db의 wifi정보 조회)
    List<Wifi> getWifiList(Location location);

    // 위치히스토리 저장
    int saveLocationHistory(Location location);

    // 위치히스토리 목록조회
    int getLocationHistory();
}
