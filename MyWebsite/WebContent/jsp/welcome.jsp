<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="restaurant" uri="/WEB-INF/tld/taglib.tld" %>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<title>Insert title here</title>
</head>
<body>
<c:import url="header.jsp"/>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="register.success_message" var="register_success" />
<fmt:message bundle="${loc}" key="message.hello" var="hello" />
<fmt:message bundle="${loc}" key="message.goodbye" var="goodbye" />

<h2><c:if test="${not empty requestScope.helloMessage}">
          <c:out value="${hello}"/>
    </c:if>
</h2>

<h2><c:if test="${not empty requestScope.successMessage}">
          <c:out value="${register_success}"/>
    </c:if>
</h2>
       
<h2><c:if test="${not empty requestScope.goodbyeMessage}">
          <c:out value="${goodbye}"/>
    </c:if>
</h2>
       

<br><br><br><br><br><br><br>

<restaurant:footer local="${sessionScope.local}"/> 
</body>
</html>