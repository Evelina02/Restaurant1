<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">

<title>${registration_text}</title>
</head>
<body>
<c:import url="header.jsp"/>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="text.registration" var="registration_text" />
<fmt:message bundle="${loc}" key="text.sign_up" var="sign_up_text" />
<fmt:message bundle="${loc}" key="text.enter.login" var="enter_login" />
<fmt:message bundle="${loc}" key="text.enter.password" var="enter_password" />
<fmt:message bundle="${loc}" key="text.enter.password1" var="enter_password1" />
<fmt:message bundle="${loc}" key="text.enter.email" var="enter_email" />
<fmt:message bundle="${loc}" key="text.enter.address" var="enter_address" />
<fmt:message bundle="${loc}" key="message.login_exists" var="login_exists" />
<fmt:message bundle="${loc}" key="register.error" var="register_error" />

	<h1>${registration_text}</h1>
	<br> 
	<h2><c:if test="${not empty requestScope.loginExists}">
          <c:out value="${login_exists}"/>
       </c:if></h2>
    <h2><c:if test="${not empty requestScope.registerError}">
          <c:out value="${register_error}"/>
      </c:if></h2>
	<form action="${pageContext.request.contextPath}/Controller" method="post" onsubmit="return validateForm(this)">
		<input type="hidden" name="command" value="sign_up"><br><br> 
		<input type="text" name="login" placeholder=${enter_login} maxlength="10" required><br><br> 
		<input type="password" name="password" placeholder=${enter_password} maxlength="10" required><br><br> 
		<input type="password" name="password1" placeholder=${enter_password1} maxlength="10" required><br><br> 
		<input type="email" name="email" placeholder=${enter_email} maxlength="20" required><br><br> 
		<input type="text" name="address" placeholder=${enter_address} maxlength="200"> <br><br>
		
		<hr><br>
		<input type="submit" name="sign_up" value=${sign_up_text}>
		<br><br>
	</form>
	
	
	<script type="text/javascript">

		function validateForm(form){
			var login = form.login.value;
			var password1 = form.password1.value;
			var password2 = form.password2.value;
			var email = form.email.value;
			var address = form.address.value;

			if(login == "" || login == " "){
				alert("You have not entered your login!!!");
				return false;
			}else if(password == "" || password1 == " "){
				alert("You have not entered your password!!!");
				return false;
			}else if(password != password1){
				alert("Passwords don't match!!!");
				return false;
			}else if(email == "" || email == " "){
				alert("You have not entered your email!!!");
				return false;
			}
		}
	
	
	</script>
</body>
</html>