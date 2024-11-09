package com.jsp.project.publicwifiinfo.controller;

import com.jsp.project.publicwifiinfo.model.Location;
import com.jsp.project.publicwifiinfo.model.Wifi;
import com.jsp.project.publicwifiinfo.service.WifiService;
import com.jsp.project.publicwifiinfo.service.WifiServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(name = "wifiListServlet", value = "/wifi/list")
public class WifiListServlet extends  HttpServlet{
    WifiService wifiService = new WifiServiceImpl();

    public void init(){
        //System.out.println("/wifi-list init()");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //System.out.println("/wifi-list doGet()");

        // 요청 데이터 파싱
        Location location = new Location();
        location.setLat(Double.parseDouble(request.getParameter("lat")));
        location.setLnt(Double.parseDouble(request.getParameter("lnt")));

        // 비즈니스 로직 실행
        List<Wifi> list = wifiService.getWifiList(location);

        // 응답 데이터 셋팅
//            request.setAttribute("wifiList", list);
//            request.getRequestDispatcher("/index.jsp").forward(request, response);
//            response.getWriter().write("SUCCESS");

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        for(Wifi wifi : list) {
            out.println("<tr>");
            out.println("   <td>" + wifi.getDistance() + "</td>");
            out.println("   <td>" + wifi.getMngrNo() + "</td>");
            out.println("   <td>" + wifi.getWrdofc() + "</td>");
            out.println("   <td>" + wifi.getWifiNm() + "</td>");
            out.println("   <td>" + wifi.getAddr1() + "</td>");
            out.println("   <td>" + wifi.getAddr2() + "</td>");
            out.println("   <td>" + wifi.getInstallFloor() + "</td>");
            out.println("   <td>" + wifi.getInstallTy() + "</td>");
            out.println("   <td>" + wifi.getInstallMby() + "</td>");
            out.println("   <td>" + wifi.getSvcSe() + "</td>");
            out.println("   <td>" + wifi.getCmcrwr() + "</td>");
            out.println("   <td>" + wifi.getCnstcYear() + "</td>");
            out.println("   <td>" + wifi.getInoutDoor() + "</td>");
            out.println("   <td>" + wifi.getConEnvironment() + "</td>");
            out.println("   <td>" + wifi.getLat() + "</td>");
            out.println("   <td>" + wifi.getLnt() + "</td>");
            out.println("   <td>" + Timestamp.valueOf(wifi.getWorkDt()) + "</td>");
            out.println("</tr>");
        }
        out.close();
    }

    public void destroy() {
    }
}
