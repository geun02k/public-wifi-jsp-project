package com.jsp.project.publicwifiinfo.controller;

import com.jsp.project.publicwifiinfo.model.BookmarkGroup;
import com.jsp.project.publicwifiinfo.service.BookmarkGroupService;
import com.jsp.project.publicwifiinfo.service.BookmarkGroupServiceImpl;
import common.JsonDataParsing;
import org.json.simple.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@WebServlet(name = "bookmarkGroupServlet", value = "/bookmarkGroup")
public class BookmarkGroupServlet extends  HttpServlet{
    BookmarkGroupService service = new BookmarkGroupServiceImpl();

    public void init(){
        System.out.println("/bookmarkGroup init()");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        System.out.println("/bookmarkGroup doPost()");

        // 요청 데이터 파싱
        JsonDataParsing jsonParser = new JsonDataParsing();
        JSONObject json = jsonParser.parseRequestJson(request);

        BookmarkGroup group = new BookmarkGroup();
        group.setName(String.valueOf(json.get("groupName")));
        group.setSeq(Integer.parseInt(String.valueOf(json.get("groupSeq"))));

        // 비즈니스 로직 실행
        int savedCnt = service.saveGroup(group);

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
            out.write("FAIL_10"); // SEQ 중복
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
