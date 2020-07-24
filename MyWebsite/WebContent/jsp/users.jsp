<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib prefix="restaurant" uri="/WEB-INF/tld/taglib.tld" %>

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
</style>


<title>${orders_text}</title>
</head>
<body>
	<c:import url="header.jsp" />

	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="resources.localization.local" var="loc" />
	<fmt:message bundle="${loc}" key="text.orders" var="orders_text" />
	<fmt:message bundle="${loc}" key="order.added" var="order_added" />
	<fmt:message bundle="${loc}" key="no_users" var="no_users" />

	<!--  
	<c:if test="${not empty requestScope.orderAdded}">
		<div class="alert alert-success" role="alert">
			<strong><c:out value="${order_added}" /></strong>
		</div>
	</c:if>
	<c:if test="${not empty requestScope.orderDeleted}">
		<div class="alert alert-success" role="alert">
			<strong><c:out value="${order_deleted}" /></strong>
		</div>
	</c:if>
-->
	<div class="container-fluid">
		<c:choose>
			<c:when test="${requestScope.allUsers == null}">
				<br>
				<h1 style="text-align: center;">${no_users}</h1>
			</c:when>
			<c:otherwise>


				<table class="table table-hover">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Номер</th>
							<th scope="col">Логин</th>
							<th scope="col">Эл. почта</th>
							<th scope="col">Адрес</th>
							<th scope="col">Состояние</th>
							<th scope="col"></th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.allUsers}" var="user">
							<tr>
								<td>
									<p>${user.id}</p>

								</td>
								<td><p>${user.login}</p></td>



								<td>
									<div class="">
										<p>${user.email}</p>
									</div>

								</td>


								<td>
									<div class="">
										<p>${user.address}</p>
									</div>

								</td>

								<td>
									<div class="state">
										<c:if test="${user.banned == true}">
											<span id=user_state>Заблокирован</span>
										</c:if>

									</div>
								</td>


								<td>


									<form action="${pageContext.request.contextPath}/Controller"
										method="post">
										<input type="hidden" name="user_id" value="${user.id}">
										<input type="hidden" name="command" value="unban_user">

										<c:if test="${user.banned == true}">
											<button class="unban btn btn-light" id="ban" type="submit"
												style="width: 150px;">Разблокировать</button>
										</c:if>
									</form>
									<form action="${pageContext.request.contextPath}/Controller"
										method="post">
										<input type="hidden" name="user_id" value="${user.id}">
										<input type="hidden" name="command" value="ban_user">

										<c:if test="${user.banned == false}">
											<button class="ban btn btn-light" type="submit"
												style="width: 150px;">Заблокировать</button>
										</c:if>
									</form>
								</td>


							</tr>
						</c:forEach>



					</tbody>
				</table>

			</c:otherwise>
		</c:choose>
	</div>


<restaurant:footer local="${sessionScope.local}"/>  

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
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
	<!--===============================================================================================-->
	<script src="${pageContext.request.contextPath}/js/myJS.js"></script>

	<script>

</script>

</body>
</html>