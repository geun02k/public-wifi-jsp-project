<%@ page import="com.jsp.project.publicwifiinfo.model.Bookmark" %>
<%@ page import="com.jsp.project.publicwifiinfo.service.BookmarkService" %>
<%@ page import="com.jsp.project.publicwifiinfo.service.BookmarkServiceImpl" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>와이파이 정보 구하기</title>
  <script src="http://code.jquery.com/jquery-latest.js"></script>
  <script src="../js/bookmark-delete.js"></script>
  <link rel="stylesheet" href="../css/common.css" />
</head>

<body>
<h1>북마크 삭제</h1>
<%@include file="../menu.jsp"%>

<p>북마크를 삭제하시겠습니까?</p>
<br/>

<%
  int bookmarkId = Integer.parseInt(request.getParameter("id"));

  Bookmark requestBookmark = new Bookmark();
  requestBookmark.setId(bookmarkId);

  BookmarkService service = new BookmarkServiceImpl();
  Bookmark bookmarkInfo = service.getBookmark(requestBookmark);
%>

<input type="hidden" id="bookmarkId" value="<%=bookmarkInfo.getId()%>" />
<input type="hidden" id="groupCode" value="<%=bookmarkInfo.getGroupCode()%>" />
<input type="hidden" id="wifiMngrNo" value="<%=bookmarkInfo.getWifiMngrNo()%>" />

<table>
  <colgroup>
    <col style="width: 20%"; />
    <col style="width: 80%"; />
  </colgroup>
  <tbody>
    <tr>
      <th>북마크 이름</th>
      <td><%=bookmarkInfo.getGroupNm()%></td>
    </tr>
    <tr>
      <th>와이파이명</th>
      <td><%=bookmarkInfo.getWifiNm()%></td>
    </tr>
    <tr>
      <th>등록일자</th>
      <td><%=bookmarkInfo.getRegDt()%></td>
    </tr>
    <tr>
      <td colspan="2">
        <div class="center-align-div">
          <a href="javascript:history.back();">돌아가기</a>
          | <button id="deleteBookmarkBtn" class="inline-block-btn">삭제</button>
        </div>
      </td>
    </tr>
  </tbody>
</table>

</body>
</html>