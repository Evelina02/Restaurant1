<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
	<meta charset="utf-8">
<title>Меню</title>
</head>
<body>
	<c:forEach> items=${dishes} var="dish">
		<c:out value="${dish.name}"/>
		<c:out value="${dish.picture}"/>
		<c:out value="${dish.amount}"/>
		<c:out value="${dish.price}"/>
		
		<c:forEach> items=${dish.ingredients} var="ingredient"
			<c:out value="${ingredient}"/>
		</c:forEach>
	</c:forEach>
</body>
</html>