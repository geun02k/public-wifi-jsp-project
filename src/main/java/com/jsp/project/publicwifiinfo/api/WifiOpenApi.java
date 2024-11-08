package com.jsp.project.publicwifiinfo.api;

import com.jsp.project.publicwifiinfo.model.Wifi;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WifiOpenApi {
    private final static String KEY = "6c58767953676c793132347247754650"; // 인증 개인키
    private final static String ENCODING_TYPE = "UTF-8";
    public final static int MAX_SEARCH_CNT = 999; // 최대 조회가능 건 수

    /**
     *  공공와이파이정보목록조회 (외부api호출)
     * @param startIdx 페이지 시작위치
     * @param endIdx 페이지 종료위치
     * @return List<Wifi> 공공와이파이정보목록
     * @throws IOException
     */
    public List<Wifi> wifiInfoList(int startIdx, int endIdx) throws IOException {
        if(endIdx - startIdx > MAX_SEARCH_CNT) {
            throw new IOException("공공 와이파이 조회는 " + (MAX_SEARCH_CNT+1) + "건 이상 불가합니다.");
        }

        List<Wifi> wifiList = new ArrayList<>();

        String sStartIdx = String.valueOf(startIdx);
        String sEndIdx = String.valueOf(endIdx);

        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
        /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
        urlBuilder.append("/" + URLEncoder.encode(KEY,ENCODING_TYPE) );
        /*요청파일타입 (xml,xmlf,xls,json) */
        urlBuilder.append("/" + URLEncoder.encode("json",ENCODING_TYPE) );
        /*서비스명 (대소문자 구분 필수입니다.)*/
        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo",ENCODING_TYPE));
        /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
        urlBuilder.append("/" + URLEncoder.encode(sStartIdx,ENCODING_TYPE));
        /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
        urlBuilder.append("/" + URLEncoder.encode(sEndIdx,ENCODING_TYPE));
        // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.


        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/

        BufferedReader rd;
        // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), ENCODING_TYPE));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), ENCODING_TYPE));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        JSONObject wifiInfoJsonObj = null;
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = new JSONObject((JSONObject) parser.parse(sb.toString()));
            wifiInfoJsonObj = (JSONObject) jsonObject.get("TbPublicWifiInfo");

//            if(wifiInfoJsonObj == null) {
//                return null;
//            }

            wifiInfoJsonObj.get("RESULT");
            wifiInfoJsonObj.get("MESSAGE");
            wifiInfoJsonObj.get("list_total_count");
            wifiInfoJsonObj.get("row");

            JSONArray jsonArray = (JSONArray) wifiInfoJsonObj.get("row");
            for(int i = 0; i < jsonArray.size(); i++) {
                JSONObject obj = (JSONObject) jsonArray.get(i);

                Wifi wifi = new Wifi();
                wifi.setMngrNo(obj.get("X_SWIFI_MGR_NO").toString());
                wifi.setWrdofc(obj.get("X_SWIFI_WRDOFC").toString());
                wifi.setWifiNm(obj.get("X_SWIFI_MAIN_NM").toString());
                wifi.setAddr1(obj.get("X_SWIFI_ADRES1").toString());
                wifi.setAddr2(obj.get("X_SWIFI_ADRES2").toString());
                wifi.setInstallFloor(obj.get("X_SWIFI_INSTL_FLOOR").toString());
                wifi.setInstallTy(obj.get("X_SWIFI_INSTL_TY").toString());
                wifi.setInstallMby(obj.get("X_SWIFI_INSTL_MBY").toString());
                wifi.setSvcSe(obj.get("X_SWIFI_SVC_SE").toString());
                wifi.setCmcrwr(obj.get("X_SWIFI_CMCWR").toString());
                wifi.setCnstcYear(obj.get("X_SWIFI_CNSTC_YEAR").toString());
                wifi.setInoutDoor(obj.get("X_SWIFI_INOUT_DOOR").toString());
                wifi.setConEnvironment(obj.get("X_SWIFI_REMARS3").toString());
                wifi.setLat(Double.parseDouble(obj.get("LAT").toString()));
                wifi.setLnt(Double.parseDouble(obj.get("LNT").toString()));

                String sWorkDttm = String.valueOf(obj.get("WORK_DTTM")); //2024-11-08 11:13:14.0
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                wifi.setWorkDt(LocalDateTime.parse(sWorkDttm, formatter));

                wifiList.add(wifi);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println(sb.toString());
        }

        return wifiList;
    }

    /**
     *  공공와이파이정보목록 총 수 조회 (외부api호출)
     * @return int 총 공공와이파이정보목록 수
     * @throws IOException
     */
    public int getWifiListTotCnt() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
        /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
        urlBuilder.append("/" + URLEncoder.encode(KEY,ENCODING_TYPE) );
        /*요청파일타입 (xml,xmlf,xls,json) */
        urlBuilder.append("/" + URLEncoder.encode("json",ENCODING_TYPE) );
        /*서비스명 (대소문자 구분 필수입니다.)*/
        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo",ENCODING_TYPE));
        /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
        urlBuilder.append("/" + URLEncoder.encode("0",ENCODING_TYPE));
        /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
        urlBuilder.append("/" + URLEncoder.encode("1",ENCODING_TYPE));
        // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.


        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/xml");
        System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/

        BufferedReader rd;
        // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        JSONObject wifiInfoJsonObj = null;
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = new JSONObject((JSONObject) parser.parse(sb.toString()));
            wifiInfoJsonObj = (JSONObject) jsonObject.get("TbPublicWifiInfo");

            wifiInfoJsonObj.get("RESULT");
            wifiInfoJsonObj.get("MESSAGE");
            wifiInfoJsonObj.get("list_total_count");
            wifiInfoJsonObj.get("row");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return Integer.parseInt(wifiInfoJsonObj.get("list_total_count").toString());
    }
}

