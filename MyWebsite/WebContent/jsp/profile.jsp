<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/css/basket.css"
	type="text/css" rel="stylesheet">




<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/vendor/animate/animate.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/vendor/css-hamburgers/hamburgers.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/vendor/animsition/css/animsition.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/vendor/select2/select2.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/vendor/daterangepicker/daterangepicker.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/signInUpUtil.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/signInUp.css">

<style>
@
keyframes "login " { 0% {
	-ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
	filter: alpha(opacity = 0);
	opacity: 0;
	margin-top: -50px;
}

100%
{
-ms-filter
:
 
"
progid
:DXImageTransform
.Microsoft
.Alpha
(Opacity=100)";

   	
filter
:
 
alpha
(opacity=100);

   	
opacity
:
 
1;
margin-top
:
 
-75
px
;

 
}
}
@
-moz-keyframes login { 0% {
	filter: alpha(opacity = 0);
	opacity: 0;
	margin-top: -50px;
}

100%
{
filter
:
 
alpha
(opacity=100);

   
opacity
:
 
1;
margin-top
:
 
-75
px
;

 
}
}
@
-webkit-keyframes "login " { 0% {
	filter: alpha(opacity = 0);
	opacity: 0;
	margin-top: -50px;
}

100%
{
filter
:
 
alpha
(opacity=100);

   
opacity
:
 
1;
margin-top
:
 
-75
px
;

 
}
}
@
-ms-keyframes "login " { 0% {
	-ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
	filter: alpha(opacity = 0);
	opacity: 0;
	margin-top: -50px;
}

100%
{
-ms-filter
:
 
"
progid
:DXImageTransform
.Microsoft
.Alpha
(Opacity=100)";

   
filter
:
 
alpha
(opacity=100);

   
opacity
:
 
1;
margin-top
:
 
-75
px
;

 
}
}
@
-o-keyframes "login " { 0% {
	filter: alpha(opacity = 0);
	opacity: 0;
	margin-top: -50px;
}

100%
{
filter
:
 
alpha
(opacity=100);

   
opacity
:
 
1;
margin-top
:
 
-75
px
;

 
}
}

/* Main CSS */
* {
	-moz-box-sizing: border-box;
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
}

#login {
	width: 220px;
	height: 155px;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -110px;
	margin-top: -75px;
	-webkit-animation: login 1s ease-in-out;
	-moz-animation: login 1s ease-in-out;
	-ms-animation: login 1s ease-in-out;
	-o-animation: login 1s ease-in-out;
	animation: login 1s ease-in-out;
}

#login input[type="password"] {
	width: 100%;
	height: 40px;
	positon: relative;
	margin-top: 7px;
	font-size: 14px;
	color: #444;
	outline: none;
	border: 1px solid rgba(0, 0, 0, .49);
	padding-left: 20px;
	-webkit-background-clip: padding-box;
	-moz-background-clip: padding-box;
	background-clip: padding-box;
	border-radius: 6px;
	background-image: -webkit-linear-gradient(bottom, #FFFFFF 0%, #F2F2F2 100%);
	background-image: -moz-linear-gradient(bottom, #FFFFFF 0%, #F2F2F2 100%);
	background-image: -o-linear-gradient(bottom, #FFFFFF 0%, #F2F2F2 100%);
	background-image: -ms-linear-gradient(bottom, #FFFFFF 0%, #F2F2F2 100%);
	background-image: linear-gradient(bottom, #FFFFFF 0%, #F2F2F2 100%);
	-webkit-box-shadow: inset 0px 2px 0px #d9d9d9;
	box-shadow: inset 0px 2px 0px #d9d9d9;
	-webkit-transition: all .1s ease-in-out;
	-moz-transition: all .1s ease-in-out;
	-o-transition: all .1s ease-in-out;
	-ms-transition: all .1s ease-in-out;
	transition: all .1s ease-in-out;
}

#login input[type="password"]:focus {
	-webkit-box-shadow: inset 0px 2px 0px #a7a7a7;
	box-shadow: inset 0px 2px 0px #a7a7a7;
}

#login input:first-child {
	margin-top: 0px;
}

#login input[type="submit"] {
	width: 100%;
	height: 50px;
	margin-top: 7px;
	color: #fff;
	font-size: 18px;
	font-weight: bold;
	text-shadow: 0px -1px 0px #5b6ddc;
	outline: none;
	border: 1px solid rgba(0, 0, 0, .49);
	-webkit-background-clip: padding-box;
	-moz-background-clip: padding-box;
	background-clip: padding-box;
	border-radius: 6px;
	background-color: #5466da;
	background-image: -webkit-linear-gradient(bottom, #5466da 0%, #768ee4 100%);
	background-image: -moz-linear-gradient(bottom, #5466da 0%, #768ee4 100%);
	background-image: -o-linear-gradient(bottom, #5466da 0%, #768ee4 100%);
	background-image: -ms-linear-gradient(bottom, #5466da 0%, #768ee4 100%);
	background-image: linear-gradient(bottom, #5466da 0%, #768ee4 100%);
	-webkit-box-shadow: inset 0px 1px 0px #9ab1ec;
	box-shadow: inset 0px 1px 0px #9ab1ec;
	cursor: pointer;
	-webkit-transition: all .1s ease-in-out;
	-moz-transition: all .1s ease-in-out;
	-o-transition: all .1s ease-in-out;
	-ms-transition: all .1s ease-in-out;
	transition: all .1s ease-in-out;
}

#login input[type="submit"]:hover {
	background-color: #5f73e9;
	background-image: -webkit-linear-gradient(bottom, #5f73e9 0%, #859bef 100%);
	background-image: -moz-linear-gradient(bottom, #5f73e9 0%, #859bef 100%);
	background-image: -o-linear-gradient(bottom, #5f73e9 0%, #859bef 100%);
	background-image: -ms-linear-gradient(bottom, #5f73e9 0%, #859bef 100%);
	background-image: linear-gradient(bottom, #5f73e9 0%, #859bef 100%);
	-webkit-box-shadow: inset 0px 1px 0px #aab9f4;
	box-shadow: inset 0px 1px 0px #aab9f4;
	margin-top: 10px;
}

#login input[type="submit"]:active {
	background-color: #7588e1;
	background-image: -webkit-linear-gradient(bottom, #7588e1 0%, #7184df 100%);
	background-image: -moz-linear-gradient(bottom, #7588e1 0%, #7184df 100%);
	background-image: -o-linear-gradient(bottom, #7588e1 0%, #7184df 100%);
	background-image: -ms-linear-gradient(bottom, #7588e1 0%, #7184df 100%);
	background-image: linear-gradient(bottom, #7588e1 0%, #7184df 100%);
	-webkit-box-shadow: inset 0px 1px 0px #93a9e9;
	box-shadow: inset 0px 1px 0px #93a9e9;
}

.placeholder {
	color: #444;
}

#blur {
	background: rgba(102, 102, 102, 0.5);
	width: 100%;
	height: 100%;
	position: absolute;
	top: 0;
	left: 0;
	display: none;
}

#window {
	width: 400px;
	height: 400px;
	text-align: center;
	padding: 15px;
	border: 3px solid #0000cc;
	border-radius: 10px;
	color: #0000cc;
	position: absolute;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	margin: auto;
	background: #fff;
}

#blur:target {
	display: block;
}

.close {
	position: relative;
	display: inline-block;
	/*border: 1px solid #0000cc;*/
	color: #0000cc;
	padding: 0 12px;
	margin-top: 0px;
	margin-bottom: 20px; text-decoration : none;
	background: #f2f2f2;
	font-size: 14pt;
	cursor: pointer;
	text-decoration: none;
}

.close:hover {
	background: #e6e6ff;
}

.checkout-title {
	position: relative;
	font-size: 30px;
	margin-lrft: 50px;
}
</style>


<title>${profile_text}</title>
</head>
<body>
	<c:import url="header.jsp" />

	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="resources.localization.local" var="loc" />
	<fmt:message bundle="${loc}" key="text.profile" var="profile_text" />
	<fmt:message bundle="${loc}" key="changes_saved" var="changes_saved" />
	<fmt:message bundle="${loc}" key="save_change_error"
		var="save_change_error" />
	<fmt:message bundle="${loc}" key="wrong_old_password"
		var="password_change_error" />
	<fmt:message bundle="${loc}" key="password_changed"	var="password_change_success" />

	<fmt:message bundle="${loc}" key="text.enter.login" var="login_text" />
	<fmt:message bundle="${loc}" key="text.enter.password" var="password_text" />	
	<fmt:message bundle="${loc}" key="text.changePassword" var="change_password_text" />
	<fmt:message bundle="${loc}" key="text.enter.email" var="mail_text" />
	<fmt:message bundle="${loc}" key="text.enter.address" var="address_text" />
	<fmt:message bundle="${loc}" key="text.saveChanges" var="save_changes_text" />
	<fmt:message bundle="${loc}" key="changingPassword" var="changing_password" />
	<fmt:message bundle="${loc}" key="text.newPassword" var="new_password" />
	<fmt:message bundle="${loc}" key="text.oldPassword" var="old_password" />
	<fmt:message bundle="${loc}" key="text.newPasswordOnceAgain" var="new_password_once_again" />
	<fmt:message bundle="${loc}" key="text.change" var="change_text" />



<script type="text/javascript">
function validateForm(form){

	var old_password = form.old_password.value;
	var password1 = form.new_password.value;
	var password2 = form.new_password1.value;

	if(old_password == "" || old_password == " "){
		alert("Вы не ввели свой старый пароль!!!");
		return false;
	}else if(password1 == "" || password2 == " "){
		alert("Вы не ввели свой новый пароль!!!");
		return false;
	}else if(password1 != password2){
		alert("Пароли не совпадают!!!");
		return false;
	}
}
</script>


	<c:if test="${not empty requestScope.changesSaved}">
		<div class="alert alert-success" role="alert">
			<strong><c:out value="${changes_saved}" /></strong>
		</div>
	</c:if>

	<c:if test="${not empty requestScope.saveUserChangesError}">
		<div class="alert alert-danger" role="alert">
			<strong><c:out value="${save_change_error}" /></strong>
		</div>
	</c:if>

	<c:if test="${not empty requestScope.passwordChanged}">
		<div class="alert alert-success" role="alert">
			<strong><c:out value="${password_change_success}" /></strong>
		</div>
	</c:if>

	<c:if test="${not empty requestScope.changePasswordError}">
		<div class="alert alert-danger" role="alert">
			<strong><c:out value="${password_change_error}" /></strong>
		</div>
	</c:if>

	<div class="container">


		<form action="${pageContext.request.contextPath}/Controller"
			method="post">
			<input type="hidden" name="command" value="save_user_changes">

			<table class="table">
				<tbody>
					<tr>
						<th>${login_text}</th>
						<td><input type="text" name="login" value="${user.login}"
						pattern="^[a-zA-Z][a-zA-Z0-9-_\\.]{3,20}$"
						style="width:300px"></td>
						
					</tr>
					<tr>
						<th>${password_text}</th>
						<td><a href="#blur">${change_password_text}</a></td>
					</tr>

					<tr>
						<th>${mail_text}</th>
						<td><input type="text" name="email" value="${user.email}"
						pattern="[a-zA-Z0-9.\-_]+@[a-zA-Z]+\.[a-zA-Z]+"
						style="width:300px"></td>
					</tr>
					<tr>
						<th>${address_text}</th>
						<td><input type="text" name="address" value="${user.address}"
						style="width:300px";
						></td>
					</tr>
				</tbody>
			</table>
			<button type="submit" class="btn btn-primary" style="width: 222px;">${save_changes_text}</button>
		</form>

	</div>




	<div id="blur">
		<div id="window">
			<a href="#" class="close"> 
			<img name="cross"
				src="http://itsnottrashdesigns.com/wp-content/uploads/2016/05/Remove_Item_icon.png"
				style="wigth: 20px; height: 20px;">
			</a>
			<form class="checkout validate-form"
				action="${pageContext.request.contextPath}/Controller" method="post"
				onsubmit="return validateForm(this)">
				<input type="hidden" name="command" value="change_password">

				<div class="checkout-header">
					<h1 class="checkout-title">${changing_password}</h1>
				</div>

				<div id="login" class="validate-input">
					<!--валидация   локализация -->
					<input type="password" name="old_password"
						class="placeholder input100" placeholder="${old_password}:"
						data-validate="Старый пароль обязателен!" 
						pattern = "^[a-zA-z]{1}[a-zA-Z1-9]{3,20}$" required
					>
						 <input
						type="password" name="new_password" class="placeholder input100"
						placeholder="${new_password}:" data-validate="Введите пароль!" 
						pattern = "^[a-zA-z]{1}[a-zA-Z1-9]{3,20}$" required
					>
					<input type="password" name="new_password1"
						class="placeholder input100" placeholder="${new_password_once_again}:"
						data-validate="Введите пароль!" 
						pattern = "^[a-zA-z]{1}[a-zA-Z1-9]{3,20}$" required
						> 
						<input type="submit" value="${change_text}">
				</div>
			</form>
		</div>
	</div>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/vendor/jquery/jquery-3.2.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/vendor/animsition/js/animsition.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/vendor/bootstrap/js/popper.js"></script>
	<script
		src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/vendor/select2/select2.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/vendor/daterangepicker/moment.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/vendor/daterangepicker/daterangepicker.js"></script>
	<script
		src="${pageContext.request.contextPath}/vendor/countdowntime/countdowntime.js"></script>
	<!--===============================================================================================-->








	<script>
		$(function() {
			// check placeholder browser support
			if (!Modernizr.input.placeholder) {

				// set placeholder values
				$(this).find('[placeholder]').each(function() {
					if ($(this).val() == '') // if field is empty
					{
						$(this).val($(this).attr('placeholder'));
					}
				});

				// focus and blur of placeholders
				$('[placeholder]').focus(function() {
					if ($(this).val() == $(this).attr('placeholder')) {
						$(this).val('');
						$(this).removeClass('placeholder');
					}
				}).blur(
						function() {
							if ($(this).val() == ''
									|| $(this).val() == $(this).attr(
											'placeholder')) {
								$(this).val($(this).attr('placeholder'));
								$(this).addClass('placeholder');
							}
						});

				// remove placeholders on submit
				$('[placeholder]').closest('form').submit(function() {
					$(this).find('[placeholder]').each(function() {
						if ($(this).val() == $(this).attr('placeholder')) {
							$(this).val('');
						}
					})
				});

			}
		});
		
		
	
		
	
	</script>

</body>
</html>