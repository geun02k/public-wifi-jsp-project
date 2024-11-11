<%@ page import="java.util.List" %>
<%@ page import="com.jsp.project.publicwifiinfo.service.BookmarkService" %>
<%@ page import="com.jsp.project.publicwifiinfo.service.BookmarkServiceImpl" %>
<%@ page import="com.jsp.project.publicwifiinfo.model.Bookmark" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <link rel="stylesheet" href="../css/common.css" />

</head>

<body>
<h1>북마크 목록</h1>
<%@include file="../menu.jsp"%>

<%
    BookmarkService service = new BookmarkServiceImpl();
    List<Bookmark> bookmarkList = service.getBookmarkList();
%>

<table>
    <thead>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>와이파이명</th>
        <th>등록일자</th>
        <th>비고</th>
    </thead>
    <tbody>
    <% for(int i = 0; i < bookmarkList.size(); i++) { %>
    <%  Bookmark bookmark = bookmarkList.get(i); %>
        <tr id="tr<%=i%>">
            <td id="<%=bookmark.getId()%>"><%=bookmark.getId()%></td>
            <td><%=bookmark.getGroupNm()%></td>
            <td><a href=""><%=bookmark.getWifiNm()%></a></td>
            <td><%=bookmark.getRegDt()%></td>
            <td>
                <div class="center-align-div">
                    <a href="/bookmark/bookmark-delete.jsp?id=<%=bookmark.getId()%>">
                        삭제
                    </a>
                </div>
            </td>
        </tr>
    <% } %>
    </tbody>
</table>


</body>
</html>