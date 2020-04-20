<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	
	<link rel="icon" type="image/png" href="img/icons/favicon.ico"/>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link href="${pageContext.request.contextPath}/css/style.css" type="text/css" rel="stylesheet">
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/animate/animate.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/css-hamburgers/hamburgers.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/animsition/css/animsition.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/select2/select2.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/daterangepicker/daterangepicker.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/signInUpUtil.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/signInUp.css">

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
<!-- 
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
		
	</script>
	
	-->

	     <c:if test="${not empty requestScope.loginExists}">
			<div class="alert alert-danger" role="alert">
	        	<strong><c:out value="${login_exists}"/></strong>
        	</div>
    	</c:if>

		<c:if test="${not empty requestScope.registerError}">
			<div class="alert alert-danger" role="alert">
	        	<strong><c:out value="${register_error}"/></strong>
        	</div>
    	</c:if>
	
	
	
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-form-title">
					<span class="login100-form-title-1">
						${registration_text}
					</span>
				</div>

				<form class="login100-form validate-form"  action="${pageContext.request.contextPath}/Controller" method="post">
					<input type="hidden" name="command" value="sign_up">
					<div class="wrap-input100 validate-input m-b-26" data-validate="Username is required">
						<input class="input100" type="text" name="login" placeholder=${enter_login}>
						<span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input m-b-18" data-validate = "Password is required">
						<input class="input100" type="password" name="password" placeholder=${enter_password}>
						<span class="focus-input100"></span>
					</div>
					
					<div class="wrap-input100 validate-input m-b-18" data-validate = "Password is required">
						<input class="input100" type="password" name="password1" placeholder=${enter_password1}>
						<span class="focus-input100"></span>
					</div>
					
					<div class="wrap-input100 validate-input m-b-18" data-validate = "Email is required">
						<input class="input100" type="email" name="email" placeholder=${enter_email}>
						<span class="focus-input100"></span>
					</div>
					
					<div class="wrap-input100">
						<input class="input100" type="text" name="address" placeholder=${enter_address}>
						<span class="focus-input100"></span>
					</div>


					<div class="container-login100-form-btn-reg">
						<button class="login100-form-btn" type="submit" name="sign_up">
							${sign_up_text}
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	
	
	
	
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/animsition/js/animsition.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/popper.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/select2/select2.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/daterangepicker/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/daterangepicker/daterangepicker.js"></script>
<script src="${pageContext.request.contextPath}/vendor/countdowntime/countdowntime.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<!--===============================================================================================-->
	
</body>
</html>