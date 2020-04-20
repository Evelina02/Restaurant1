<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="restaurant" uri="/WEB-INF/tld/taglib.tld" %>
    
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
	<meta charset="utf-8">
<title>${menu_text}</title>
</head>
<body>
<c:import url="header.jsp"/>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="text.menu" var="menu_text" />

<!--  <c:forEach items="${salads}" var="salad">
		<c:out value="${salad.name}"/><br>
		<c:out value="${salad.picture}"/><br>
		<c:out value="${salad.amount}"/><br>
		<c:out value="${salad.price}"/><br>
		
		<c:forEach items="${salad.ingredients}" var="ingredient">
			<c:out value="${ingredient}"/><br>
		</c:forEach>
	</c:forEach>
	
<c:forEach items="${desserts}" var="dessert">
		<c:out value="${dessert.name}"/><br>
		<c:out value="${dessert.picture}"/><br>
		<c:out value="${dessert.amount}"/><br>
		<c:out value="${dessert.price}"/><br>
		
		<c:forEach items="${dessert.ingredients}" var="ingredient">
			<c:out value="${dessert}"/><br>
		</c:forEach>
</c:forEach>
-->
<c:forEach items="${drinks}" var="drink">
		<c:out value="${drink.name}"/><br>
		<c:out value="${drink.picture}"/><br>
		<c:out value="${drink.amount}"/><br>
		<c:out value="${drink.price}"/><br>
</c:forEach>

<restaurant:footer local="${sessionScope.local}"/> 
</body>
</html>