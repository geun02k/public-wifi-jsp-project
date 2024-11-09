package com.jsp.project.publicwifiinfo.service;

import com.jsp.project.publicwifiinfo.api.WifiOpenApi;
import com.jsp.project.publicwifiinfo.model.Location;
import com.jsp.project.publicwifiinfo.model.Wifi;
import com.jsp.project.publicwifiinfo.repository.WifiRepository;

import java.io.IOException;
import java.util.List;

public class WifiServiceImpl implements WifiService {
    WifiRepository wifiRepository = new WifiRepository();

    @Override
    public int saveWifiList() {
        WifiOpenApi api = new WifiOpenApi();

        int saveCnt = 0;
        int startIdx = 0;
        int endIdx = api.MAX_SEARCH_CNT;
        try {
            int totCnt = api.getWifiListTotCnt();

            while(startIdx <= totCnt) {
                // wifi 정보 조회
                List<Wifi> wifiList = api.wifiInfoList(startIdx, endIdx);
                // DB에 데이터 다건 저장
                System.out.println("-- startIdx=" + startIdx + " / endIdx=" + endIdx);
                saveCnt += wifiRepository.insertWifiList(wifiList);

                //startIdx = startIdx + api.MAX_SEARCH_CNT + 1;
                startIdx = startIdx + api.MAX_SEARCH_CNT + 1;
                if(startIdx + api.MAX_SEARCH_CNT > totCnt) {
                    endIdx = totCnt;
                } else {
                    endIdx = startIdx + api.MAX_SEARCH_CNT;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return saveCnt;
    }

    @Override
    public List<Wifi> getWifiList(Location location) {
        List<Wifi> wifiList = wifiRepository.selectWifiList(location);
        return wifiList;
    }
}
