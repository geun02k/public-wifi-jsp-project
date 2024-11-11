package com.jsp.project.publicwifiinfo.controller;

import com.jsp.project.publicwifiinfo.model.Bookmark;
import com.jsp.project.publicwifiinfo.service.BookmarkService;
import com.jsp.project.publicwifiinfo.service.BookmarkServiceImpl;
import common.JsonDataParsing;
import org.json.simple.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "bookmarkServlet", value = "/bookmark")
public class BookmarkServlet extends  HttpServlet{
    BookmarkService service = new BookmarkServiceImpl();

    public void init(){
        System.out.println("/bookmark init()");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("/bookmark doPost()");

        // 요청 데이터 파싱
        JsonDataParsing jsonParser = new JsonDataParsing();
        JSONObject json = jsonParser.parseRequestJson(request);

        Bookmark bookmark = new Bookmark();
        bookmark.setGroupCode(Integer.parseInt(String.valueOf(json.get("groupCode"))));
        bookmark.setWifiMngrNo(String.valueOf(json.get("wifiMngrNo")));

        // 비즈니스 로직 실행
        int savedCnt = service.saveBookmark(bookmark);

        // 응답 데이터 셋팅
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(savedCnt > 0) {
            out.write("SUCCESS");
        } else if(savedCnt == -5) {
            out.write("FAIL_5"); // 필수값없음
        } else if(savedCnt == -10) {
            out.write("FAIL_10"); // 북마크 중복
        } else {
            out.write("FAIL");
        }
    }

    public void destroy() {
    }
}
