package com.jsp.project.publicwifiinfo.service;

import com.jsp.project.publicwifiinfo.model.Location;
import com.jsp.project.publicwifiinfo.model.Wifi;

import java.util.List;

public interface WifiService {

    // open api 와이파이 정보 저장(api호출 후 저장)
    int saveWifiList();

    // 근처 wifi정보보기(db의 wifi정보 조회)
    List<Wifi> getWifiList(Location location);

}
