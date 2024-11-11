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
    <link rel="stylesheet" href="../css/common.css" />
</head>

<body>
<h1>북마크 그룹</h1>
<%@include file="../menu.jsp"%>

<button onclick="location.href='bookmark-group-add.jsp'">
    북마크 그룹 이름 추가
</button>
<br/><br/>


<%
    BookmarkGroupService service = new BookmarkGroupServiceImpl();
    List<BookmarkGroup> groupList = service.getGroupList();
%>

<table>
    <thead>
    <th>ID</th>
    <th>북마크 이름</th>
    <th>순서</th>
    <th>등록일자</th>
    <th>수정일자</th>
    <th>비고</th>
    </thead>

    <tbody>
    <% if(groupList.size() <= 0) { %>
        <tr>
            <td colspan="5">
                <p style="text-align:center; font-weight:bold">
                    조회된 데이터가 없습니다.
                </p>
            </td>
        </tr>
    <% } %>

    <% for(int i = 0; i < groupList.size(); i++) { %>
    <tr>
        <td id="bookmarkGroupCode<%=i%>"><%=groupList.get(i).getCode()%></td>
        <td><%=groupList.get(i).getName()%></td>
        <td><%=groupList.get(i).getSeq()%></td>
        <td><%=groupList.get(i).getRegDt()%></td>
        <td><%=groupList.get(i).getUpdateDt() == null ? "" : groupList.get(i).getUpdateDt()%></td>
        <td>
            <div class="center-align-div">
                <a href="" class="inline-block-btn">수정</a>
                <a href="" class="inline-block-btn">삭제</a>
            </div>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>


</body>
</html>