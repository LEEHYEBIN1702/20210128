<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
tr.row:hover { background-color:lightyellow }
</style>
<script type="text/javascript">
function formSubmit(str) {
	frm.bId.value=str;
	frm.submit();
}
</script>
</head>
<body>
<div align="center">
		<h1>게시판 목록</h1>
		<div>
		<form id="frm" name="frm" action="boardView.do" method="post">
		<input type="hidden" id="bId" name="bId"> <!-- 화면에는 보이지 않는 숨겨진 히든 폼 -->
		</form>
		</div>
		<div>
			<table border="1">
			    <tr>
			    	<th width="100">글번호</th>
					<th width="250">제 목</th>
					<th width="150">작성자</th>
					<th width="150">작성일자</th>
					<th width="100">조회수</th>
				</tr>
				<c:choose>
				<c:when test="${empty list}">
				<tr>
				<td align="center" colspan="5">게시글이 없습니다.</td></tr>
				</c:when>
				<c:when test="${not empty list }">
				<c:forEach var="vo" items="${list }">
					<tr class="row" onclick="formSubmit(${vo.bId })">
						<td align="center">${vo.bId }</td>
						<td>&nbsp;${vo.bTitle }</td>
						<td align="center">${vo.bName }</td>
						<td align="center">${vo.bDate }</td>
						<td align="center">${vo.bHit }</td>
					</tr>
				</c:forEach>
				</c:when>
				</c:choose>
			</table>
     </div>
	</div><br />
 	<div align="center">
	<button type="button" onclick="location.href='boardForm.do'">새글 작성</button>
	</div>  
</body>
</html>