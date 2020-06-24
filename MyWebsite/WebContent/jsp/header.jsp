<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<meta charset="utf-8">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/css/style.css"
	type="text/css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">

<style>
body {
	/*font-family: 'Montserrat', sans-serif;*/
	
	margin: 0px -15px;
}

header {
	font-family: 'Lobster', cursive;
	height: 75px;
	margin: 0 -15px;
	margin-right: 0px;
	background: rgba(0, 0, 0, 0.5);
}

header a img { /*эмблема*/
	display: block;
	padding-top: 40px;
	padding-left: 100px;
}

header nav ul {
	list-style-type: none;
	padding-right: 30px;
	padding-left: 80px;
	margin-left: 80px;
}

#ln_btn {
	display: block;
	margin-left: auto;
}

header nav ul li {
	display: inline-block;
	margin-right: 10px;
	margin-left: 50px;
	margin-top: 15px;
}

header nav ul li a {
	color: #fff;
	font-size: 20px;
	text-decoration: none;
}

header nav ul li a:hover {
	color: #39b9ef;
	text-decoration: underline;
}

div.call-to h1 {
	margin-top: 250px;
	margin-bottom: 0px;
	text-align: center;
	color: #fff;
	font-size: 72px;
	font-weight: bold;
}

.alert {
	text-align: center;
}
</style>


<title>Insert title here</title>
</head>
<body>
	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="resources.localization.local" var="loc" />
	<fmt:message bundle="${loc}" key="button.name.ru" var="ru_button" />
	<fmt:message bundle="${loc}" key="button.name.en" var="en_button" />
	<fmt:message bundle="${loc}" key="text.name.restaurant"	var="restaurant_name" />
	<fmt:message bundle="${loc}" key="text.sign_in" var="sign_in_text" />
	<fmt:message bundle="${loc}" key="text.sign_up" var="sign_up_text" />
	<fmt:message bundle="${loc}" key="text.sign_out" var="sign_out_text" />
	<fmt:message bundle="${loc}" key="text.menu" var="menu_text" />
	<fmt:message bundle="${loc}" key="text.basket" var="basket_text" />
	<fmt:message bundle="${loc}" key="text.profile" var="profile_text" />
	<fmt:message bundle="${loc}" key="text.reviews" var="reviews_text" />
	<fmt:message bundle="${loc}" key="text.orders" var="orders_text" />
	<fmt:message bundle="${loc}" key="text.users" var="users_text" />
	<fmt:message bundle="${loc}" key="text.all_user_orders"	var="all_user_orders_text" />



	<div class="container-fluid">
		<header>
			<div class="row no-gutters">
				<nav class="d-flex flex-row">
					<ul class="p-2">
						<c:choose>
							<c:when test="${sessionScope.role == null}">
								<li><a href="${pageContext.request.contextPath}/index.jsp">${restaurant_name}</a></li>
								<li><a
									href="${pageContext.request.contextPath}/Controller?command=show_menu">${menu_text}</a></li>
								<li><a
									href="${pageContext.request.contextPath}/Controller?command=all_reviews">${reviews_text}</a></li>
								<li><a
									href="${pageContext.request.contextPath}/jsp/authorization.jsp">${sign_in_text}</a></li>
								<li><a
									href="${pageContext.request.contextPath}/jsp/registration.jsp">${sign_up_text}</a></li>

							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${sessionScope.role =='CLIENT'}">
										<li><a
											href="${pageContext.request.contextPath}/index.jsp">${restaurant_name}</a></li>
										<li><a
											href="${pageContext.request.contextPath}/Controller?command=show_menu">${menu_text}</a></li>
										<li><a
											href="${pageContext.request.contextPath}/Controller?command=show_basket">${basket_text}</a></li>
										<li><a
											href="${pageContext.request.contextPath}/Controller?command=profile">${profile_text}</a></li>
										<li><a
											href="${pageContext.request.contextPath}/Controller?command=all_user_orders">${all_user_orders_text}</a></li>

									</c:when>
									<c:when test="${sessionScope.role =='ADMIN'}">
										<li><a
											href="${pageContext.request.contextPath}/index.jsp">${restaurant_name}</a></li>
										<li><a
											href="${pageContext.request.contextPath}/Controller?command=profile">${profile_text}</a></li>
										<li><a
											href="${pageContext.request.contextPath}/Controller?command=all_orders">${orders_text}</a></li>
										<li><a
											href="${pageContext.request.contextPath}/Controller?command=all_users">${users_text}</a></li>
										<li><a
											href="${pageContext.request.contextPath}/Controller?command=admin_menu">${menu_text}</a></li>
									</c:when>
								</c:choose>

								<li><a
									href="${pageContext.request.contextPath}/Controller?command=all_reviews">${reviews_text}</a></li>
								<li><a
									href="${pageContext.request.contextPath}/Controller?command=sign_out">${sign_out_text}</a></li>

							</c:otherwise>
						</c:choose>
					</ul>
					<form id="btn"
						action="${pageContext.request.contextPath}/Controller"
						method="get">

						<input type="hidden" name="command" value="change_language">
						<button class="btn btn-light" type="submit" name="language"
							value="en">${en_button}</button>
						<button class="btn btn-light" type="submit" name="language"
							value="ru">${ru_button}</button>

					</form>
				</nav>
			</div>

		</header>
	</div>












</body>
</html>