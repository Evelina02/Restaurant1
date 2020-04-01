<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">

<title>${authorization_text}</title>
</head>
<body>
<c:import url="header.jsp"/>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="text.registration" var="registration_text" />
<fmt:message bundle="${loc}" key="text.authorization" var="authorization_text" />
<fmt:message bundle="${loc}" key="text.sign_in" var="sign_in_text" />
<fmt:message bundle="${loc}" key="text.enter.login" var="enter_login" />
<fmt:message bundle="${loc}" key="text.enter.password1" var="enter_password1" />
<fmt:message bundle="${loc}" key="wrong_password" var="wrong_password" />
<fmt:message bundle="${loc}" key="wrong_login" var="wrong_login" />

	<h1>${authorization_text}</h1>
	<br> 
	<h2><c:if test="${not empty requestScope.noSuchLogin}">
          <c:out value="${wrong_login}"/>
    </c:if></h2>
      
    <h2><c:if test="${not empty requestScope.wrongPassword}">
          <c:out value="${wrong_password}"/>
    </c:if></h2>
      
      
      
	<form action="${pageContext.request.contextPath}/Controller" method="post" onsubmit="return validateForm(this)">
		<input type="hidden" name="command" value="sign_in"><p>
		<input type="text" name="login" placeholder=${enter_login} maxlength="10" required><br><br> 
		<input type="password" name="password" placeholder=${enter_password1} maxlength="10" required></p> 
		<hr><br>
		<input type="submit" name="sign_in" value=${sign_in_text} >
		<br><br> 
		<a href="${pageContext.request.contextPath}/jsp/registration.jsp">${registration_text}</a>
	</form>
	
	<script type="text/javascript">
		function validateForm(form){
			var login = form.login.value
			var password = form.password.value

			if(login == "" || login == " "){
				alert("You have not entered your login!!!");
				return false;
			}else if(password == "" || password == " "){
				alert("You have not entered your password");
				return false;
			}
		}
	</script>
</body>
</html>