<%@page import="com.jsp.project.publicwifiinfo.model.Wifi"%>
<%@page import="com.jsp.project.publicwifiinfo.service.WifiService"%>
<%@page import="com.jsp.project.publicwifiinfo.service.WifiServiceImpl"%>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="com.jsp.project.publicwifiinfo.model.Location" %>
<%@ page import="com.jsp.project.publicwifiinfo.service.BookmarkGroupService" %>
<%@ page import="com.jsp.project.publicwifiinfo.service.BookmarkGroupServiceImpl" %>
<%@ page import="com.jsp.project.publicwifiinfo.model.BookmarkGroup" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="../js/wifi-detail.js"></script>
    <link rel="stylesheet" href="../css/common.css" />
</head>

<body>
<h1>와이파이 정보 구하기</h1>
<%@include file="../menu.jsp"%>

<%
    //select박스 조회
    BookmarkGroupService bookmarkGroupService = new BookmarkGroupServiceImpl();
    List<BookmarkGroup> groupList = bookmarkGroupService.getGroupList();
%>

<div>
    <!-- 북마크 그룹 이름 선택 select박스, 북마크 추가하기버튼 -->
    <select id="bookmarkGroup">
        <option value="">북마크 그룹 이름 선택</option>
        <% for(int i = 0; i < groupList.size(); i++) { %>
        <option value="<%=groupList.get(i).getCode()%>">
            <%=groupList.get(i).getName()%>
        </option>
        <% } %>
    </select>
    <button id="bntAddBookmarkGroup">북마크 추가하기</button>
</div>
<br/><br/>

<%
    String mngrNo = request.getParameter("mngrNo");
    Double lat = Double.valueOf(request.getParameter("lat"));
    Double lnt = Double.valueOf(request.getParameter("lnt"));

    Location location = new Location();
    location.setLat(lat);
    location.setLnt(lnt);

    WifiService service = new WifiServiceImpl();
    Wifi wifiInfo = service.getWifiByMngrNo(mngrNo, location);
%>

<table>
    <colgroup>
        <col style="width: 20%"; />
        <col style="width: 80%"; />
    </colgroup>
    <tbody>
        <tr>
            <th>거리(km)</th>
            <td><%=wifiInfo.getDistance()%></td>
        </tr>
        <tr>
            <th>관리번호</th>
            <td id="mngrNo"><%=wifiInfo.getMngrNo()%></td>
        </tr>
        <tr>
            <th>자치구</th>
            <td><%=wifiInfo.getWrdofc()%></td>
        </tr>
        <tr>
            <th>와이파이명</th>
            <td><%=wifiInfo.getWifiNm()%></td>
        </tr>
        <tr>
            <th>도로명주소</th>
            <td><%=wifiInfo.getAddr1()%></td>
        </tr>
        <tr>
            <th>상세주소</th>
            <td><%=wifiInfo.getAddr2()%></td>
        </tr>
        <tr>
            <th>설치위치(층)</th>
            <td><%=wifiInfo.getInstallFloor()%></td>
        </tr>
        <tr>
            <th>설치유형</th>
            <td><%=wifiInfo.getInstallTy()%></td>
        </tr>
        <tr>
            <th>설치기관</th>
            <td><%=wifiInfo.getInstallMby()%></td>
        </tr>
        <tr>
            <th>서비스구분</th>
            <td><%=wifiInfo.getSvcSe()%></td>
        </tr>
        <tr>
            <th>망종류</th>
            <td><%=wifiInfo.getCmcrwr()%></td>
        </tr>
        <tr>
            <th>설치년도</th>
            <td><%=wifiInfo.getCnstcYear()%></td>
        </tr>
        <tr>
            <th>실내외구분</th>
            <td><%=wifiInfo.getInoutDoor()%></td>
        </tr>
        <tr>
            <th>WIFI접속환경</th>
            <td><%=wifiInfo.getConEnvironment()%></td>
        </tr>
        <tr>
            <th>X좌표</th>
            <td><%=wifiInfo.getLat()%></td>
        </tr>
        <tr>
            <th>Y좌표</th>
            <td><%=wifiInfo.getLnt()%></td>
        </tr>
        <tr>
            <th>작업일자</th>
            <td><%=Timestamp.valueOf(wifiInfo.getWorkDt())%></td>
        </tr>
    </tbody>
</table>


</body>
</html>