<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">

<title>Авторизация</title>
</head>
<body>
	<h1>Авторизация</h1>
	<br> 
	<h2><c:out value="${noSuchLogin}"/></h2>
	<h2><c:out value="${wrongPassword}"/></h2>
	
	<form action="${pageContext.request.contextPath}/Controller" method="post" onsubmit="return validateForm(this)">
		<input type="hidden" name="command" value="sign_in"><p>
		Введите логин<input type="text" name="login" placeholder="Введите" maxlength="10" required><br><br> 
		Введите пароль<input type="password" name="password" placeholder="Введите" maxlength="10" required></p> 
		<hr><br>
		<input type="submit" name="sign_in" value="Войти" >
		<br><br> 
		<a href="${pageContext.request.contextPath}/jsp/registration.jsp">Регистрация</a>
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