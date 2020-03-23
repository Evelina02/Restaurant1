<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>WebSite</title>
</head>
<body>
	<h2><c:out value="${noSuchLogin}"/></h2>
	<h2><c:out value="${goodbyeMessage}"/></h2>
	
	<ul>
        <li><a href="jsp/authorization.jsp">Войти</a></li>
        <li><a href="jsp/registration.jsp">Регистрация</a></li>
	</ul>
</body>
</html>