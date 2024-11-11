<%@page import="com.jsp.project.publicwifiinfo.service.WifiService"%>
<%@page import="com.jsp.project.publicwifiinfo.service.WifiServiceImpl"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>와이파이 정보 구하기</title>
    <style>
        body {
            text-align: center;
        }
    </style>
  </head>
  <body>
  <%
    WifiService service = new WifiServiceImpl();
    int cnt = service.saveWifiList();
  %>
    <h1><%=cnt%>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
    <a href="/index.jsp">홈 으로 가기</a>
  </body>
</html>
