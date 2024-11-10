<%@page import="com.jsp.project.publicwifiinfo.model.Location"%>
<%@page import="com.jsp.project.publicwifiinfo.service.LocationService"%>
<%@page import="com.jsp.project.publicwifiinfo.service.LocationServiceImpl"%>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <link rel="stylesheet" href="css/common.css" />
    <script>
        function deleteRow(row) {
            // console.log("deleteRow row =" + row);
            // console.log("deleteRow의 ID =" + $("#location_id"+row).text());

            var obj = {
                id: $("#location_id"+row).text()
            };

            $.ajax({
                type: "DELETE",    // HttpMethod
                url: "/location",  // 서버 URL
                contentType: "application/json; charset=utf-8",  // request 데이터타입
                dataType: "text",   // response 데이터타입 (실제 서버에서 전달하는 response 타입과 일치해야함.)
                async: true,        //동기,비동기 설정
                data: JSON.stringify(obj),
                success: function (data, status, xhr) { //통신성공 후 처리로직
                    if(data == "SUCCESS") {
                        alert("삭제되었습니다.");
                        window.location.reload(); // 재조회를 위한 새로고침
                    } else {
                        alert("삭제에 실패했습니다.");
                    }
                },
                error :function (xhr, data) { // 통신실패 후 처리로직
                    alert("통신실패");
                }
            });
        };
    </script>
</head>

<body>
<h1>와이파이 정보 구하기</h1>
<a href="/index.jsp">홈</a>
| <a href="/location_history.jsp">위치 히스토리 목록</a>
| <a href="/load_wifi.jsp">Open API 와이파이 정보 가져오기</a>
<br/><br/>

<%
    LocationService service = new LocationServiceImpl();
    List<Location> locationList = service.getLocationHistoryList();
%>

<table>
    <thead>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </thead>
    <tbody>
    <% for(int i = 0; i < locationList.size(); i++) { %>
        <tr id="tr<%=i%>">
            <td id="location_id<%=i%>"><%=locationList.get(i).getId()%></td>
            <td><%=locationList.get(i).getLat()%></td>
            <td><%=locationList.get(i).getLnt()%></td>
            <td><%=locationList.get(i).getSearchDt()%></td>
            <td>
                <div class="btn_div">
                    <button class="delete_btn" onclick="deleteRow(<%=i%>)">삭제</button>
                </div>
            </td>
        </tr>
    <% } %>
    </tbody>
</table>


</body>
</html>