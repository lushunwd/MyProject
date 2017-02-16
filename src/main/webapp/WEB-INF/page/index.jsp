<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form:form commandName="user" action="getUser.html" method="post">
		<fieldset>
			<legend>查询用户</legend>
			<p>
				<form:errors path="userName" />
			</p>
			<label for="userName"> 姓名 </label>
			<form:input name="userName" path="userName" />
			<input type="submit" value="提交" />
		</fieldset>
	</form:form>
</body>
</html>