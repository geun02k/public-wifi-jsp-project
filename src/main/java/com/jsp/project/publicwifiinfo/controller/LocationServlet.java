package com.jsp.project.publicwifiinfo.controller;

import com.jsp.project.publicwifiinfo.model.Location;
import com.jsp.project.publicwifiinfo.service.LocationService;
import com.jsp.project.publicwifiinfo.service.LocationServiceImpl;
import common.JsonDataParsing;
import org.json.simple.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "locationServlet", value = "/location")
public class LocationServlet extends  HttpServlet{
    LocationService service = new LocationServiceImpl();

    public void init(){
        System.out.println("/location/delete init()");
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("/location/delete doPost()");

        // 요청 데이터 파싱
        JsonDataParsing jsonParser = new JsonDataParsing();
        JSONObject json = jsonParser.parseRequestJson(request);

        Location location = new Location();
        location.setId(Integer.parseInt(String.valueOf(json.get("id"))));

        // 비즈니스 로직 실행
        int deleteCnt = service.deleteLocationHistory(location);

        // 응답 데이터 셋팅
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(deleteCnt > 0) {
            out.write("SUCCESS");
        } else {
            out.write("FAIL");
        }
    }

    public void destroy() {
    }

//    private void parsingTest() {
//        // 요청 데이터 파싱
//        Location location = new Location();
//
//        BufferedReader rd = null;
//        StringBuilder sb = null;
//        try {
//            rd = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
//            sb = new StringBuilder();
//            String line;
//            while ((line = rd.readLine()) != null) {
//                sb.append(line);
//            }
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            if(rd != null) {
//                try {
//                    rd.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        System.out.println("sb = " + sb.toString());
//
//        try {
//            JSONParser parser = new JSONParser();
//            JSONObject jsonObject = new JSONObject((JSONObject) parser.parse(sb.toString()));
//            location.setId(Integer.parseInt(String.valueOf(jsonObject.get("id"))));
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
}
