<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">

<title>Регистрация</title>
</head>
<body>
	<h1>Регистрация</h1>
	<br> 
	<h2><c:out value="${loginExists}"/></h2>
	<form action="${pageContext.request.contextPath}/Controller" method="post" onsubmit="return validateForm(this)">
		<input type="hidden" name="command" value="sign_up"><br><br> 
		Введите логин <input type="text" name="login" placeholder="Введите логин" maxlength="10" required><br><br> 
		Введите пароль<input type="password" name="password1" placeholder="Введите пароль" maxlength="10" required><br><br> 
		Введите пароль ещё раз<input type="password" name="password2" placeholder="Введите пароль" maxlength="10" required><br><br> 
		Введите email<input type="email" name="email" placeholder="Введите email" maxlength="20" required><br><br> 
		Введите адрес<input type="text" name="address" placeholder="Введите адрес" maxlength="200"> <br><br>
		
		<hr><br>
		<input type="submit" name="sign_up" value="Зарегистрироваться">
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
			}else if(password1 == "" || password2 == " "){
				alert("You have not entered your password!!!");
				return false;
			}else if(password1 != password2){
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