<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/index.js"></script>
    <link rel="stylesheet" href="css/common.css" />
</head>

<body>
<h1>와이파이 정보 구하기</h1>
<%@include file="menu.jsp"%>

LAT: <input id="lat" name="lat" type="number" value="0.0"/>
, LNT: <input id="lnt" name="lnt" type="number" value="0.0"/>
<button id="find-my-location">내 위치 가져오기</button>
<button id="search-wifi-list">근처 WIFI 정보 보기</button>
<!-- 위치정보 가져오기 테스트코드
    <p id="status"></p>
    <a id="map-link" target="_blank"></a>
-->
<br/><br/>

<table>
    <thead>
        <th>거리(km)</th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </thead>
    <tbody id="wifi-list-tbody">
        <tr>
            <td colspan="17">
                <p style="text-align:center; font-weight:bold">
                    위치 정보를 입력한 후에 조회해 주세요.
                </p>
            </td>
        </tr>
    </tbody>
</table>

<!--
    <a href="hello-servlet">Hello Servlet</a>
-->
</body>
</html>