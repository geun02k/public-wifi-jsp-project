<%@ page import="com.jsp.project.publicwifiinfo.model.BookmarkGroup" %>
<%@ page import="com.jsp.project.publicwifiinfo.service.BookmarkGroupService" %>
<%@ page import="com.jsp.project.publicwifiinfo.service.BookmarkGroupServiceImpl" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>와이파이 정보 구하기</title>
  <script src="http://code.jquery.com/jquery-latest.js"></script>
  <script src="../js/bookmark-group-edit.js"></script>
  <link rel="stylesheet" href="../css/common.css" />
</head>

<body>
<h1>북마크 그룹 수정</h1>
<%@include file="../menu.jsp"%>


<%
  int groupCode = Integer.parseInt(request.getParameter("code"));

  BookmarkGroup requestGroup = new BookmarkGroup();
  requestGroup.setCode(groupCode);

  BookmarkGroupService service = new BookmarkGroupServiceImpl();
  BookmarkGroup groupInfo = service.getGroup(requestGroup);
%>

<input type="hidden" id="groupCode" value="<%=groupInfo.getCode()%>" />

<input type="hidden" id="preGroupName" value="<%=groupInfo.getName()%>" />
<input type="hidden" id="preGroupSeq" value="<%=groupInfo.getSeq()%>" />

<table>
  <colgroup>
    <col style="width: 20%"; />
    <col style="width: 80%"; />
  </colgroup>
  <tbody>
    <tr>
    <th>북마크 이름</th>
    <td><input id="groupName" maxlength="50" value="<%=groupInfo.getName()%>"/></td>
  </tr>
  <tr>
    <th>순서</th>
    <td><input id="groupSeq" type="number" value="<%=groupInfo.getSeq()%>" /></td>
  </tr>
  </tbody>
</table>
<div class="center-align-div" style="border: 1px solid #DEDEDE; height: 25px; padding: 5px;">
  <a href="javascript:history.back();">돌아가기</a>
  | <button id="updateGroupBtn" class="inline-block-btn">수정</button>
</div>

</body>
</html>