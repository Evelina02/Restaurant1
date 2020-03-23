<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<title>Insert title here</title>
</head>
<body>
	<h1><c:out value="${successMessage}"></c:out></h1>
	<h3>Hi, <c:out value="${sessionScope.login}"/>!!! We welcome you!</h3><br><br>
	<form action="${pageContext.request.contextPath}/Controller" method="post">
		<input type="hidden" name="command" value="sign_out">
		<input type="submit" name="sign_out" value="Выйти">
	</form>
</body>
</html>