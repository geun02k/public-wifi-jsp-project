<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>와이파이 정보 구하기</title>
  <script src="http://code.jquery.com/jquery-latest.js"></script>
  <script src="../js/bookmark-group-add.js"></script>
  <link rel="stylesheet" href="../css/common.css" />
</head>

<body>
<h1>북마크 그룹 추가</h1>
<%@include file="../menu.jsp"%>

<table>
  <colgroup>
    <col style="width: 20%"; />
    <col style="width: 80%"; />
  </colgroup>
  <tbody>
  <tr>
    <th>북마크 이름</th>
    <td><input id="groupName" maxlength="50"/></td>
  </tr>
  <tr>
    <th>순서</th>
    <td><input id="groupSeq" type="number" /></td>
  </tr>
  </tbody>
</table>
<div class="center-align-div" style="border: 1px solid #DEDEDE; height: 25px; padding: 5px;">
  <button id="addGroupBtn" class="inline-block-btn">추가</button>
</div>

</body>
</html>