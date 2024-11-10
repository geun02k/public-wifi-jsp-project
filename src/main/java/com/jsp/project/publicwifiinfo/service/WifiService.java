package com.jsp.project.publicwifiinfo.service;

import com.jsp.project.publicwifiinfo.model.Location;
import com.jsp.project.publicwifiinfo.model.Wifi;

import java.util.List;

public interface WifiService {

    // open api 와이파이정보 목록 저장 (api호출 후 저장)
    int saveWifiList();

    // 근처 wifi 목록 조회 (db의 wifi정보 조회)
    List<Wifi> getWifiList(Location location);

    // wifi 상세정보 조회
    Wifi getWifiByMngrNo(String mngrNo, Location location);

}
