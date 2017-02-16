<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" align="center" cellpadding="5" cellspacing="0"
		bgcolor="#aabbcc">
		<tr>
			<th>用户id</th>
			<th>用户姓名</th>
			<th>描述</th>
			<th>行数</th>
		</tr>
		<c:forEach items="${users}" var="user" varStatus="status">
			<c:if test="${status.count%2==0 }">
				<tr style="background: red">
			</c:if>
			<c:if test="${status.count%2!=0 }">
				<tr style="background: pink">
			</c:if>
			<td>${user.userId }</td>
			<td>${user.userName }</td>
			<td>${user.description}</td>
			<td>${status.count}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>