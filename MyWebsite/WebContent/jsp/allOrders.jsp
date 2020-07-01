<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
    <link href="https://fonts.googleapis.com/css2?family=Lobster&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/css/basket.css" type="text/css" rel="stylesheet">
    
    
    
    
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
    
 <style> 

 body{
font-family: 'Lobster', cursive;
 }

  </style>   
    
    
<title>${orders_text}</title>
</head>
<body>
<c:import url="header.jsp"/>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="text.orders" var="orders_text" />
<fmt:message bundle="${loc}" key="no_orders_in_system" var="no_orders" />
<fmt:message bundle="${loc}" key="order_done" var="order_done" />
<fmt:message bundle="${loc}" key="order_closed" var="order_closed" />


	<c:if test="${not empty requestScope.orderDone}">
		<div class="alert alert-success" role="alert">
			<strong><c:out value="${order_done}" /></strong>
		</div>
	</c:if>
	<c:if test="${not empty requestScope.orderClosed}">
		<div class="alert alert-success" role="alert">
			<strong><c:out value="${order_closed}" /></strong>
		</div>
	</c:if>



	<div class="container-fluid">	
	<c:choose>
			<c:when test="${empty requestScope.allOrders}">
				<h1 style="text-align: center;">${no_orders}</h1>
			</c:when>
			<c:otherwise>
	
	
					<table class="table table-hover">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Номер заказа</th>
							<th scope="col">Покупатель</th>
							<th scope="col">Содержимое</th>
							<th scope="col">Общая стоимость</th>
							<th scope="col">Время заказа</th>
							<th scope="col">Время доставки</th>
							<th scope="col">Способ оплаты</th>
							<th scope="col">Способ доставки</th>
							<th scope="col">Состояние</th>
							<th scope="col"></th>
							
						</tr>
					</thead>
					<tbody>
				<c:forEach items="${requestScope.allOrders}" var="order">
					<tr>
						<td>
							<p>${order.id}</p>

						</td>
						
						<td>
							<p>${order.userLogin}</p>

						</td>
						
						<td><c:forEach items="${order.basket.dishes}" var="dish">
										<div class="dish-title">

											<p>${dish.name}*${order.basket.countDishById.get(dish.id)}
												<br>
												<c:if test="${not empty dish.refusalOfIngredients}">
													<span style="color: red; font-size: 14px;"> Отказ: <c:forEach
															items="${dish.refusalOfIngredients}"
															var="refusedIngredient">
										${refusedIngredient},									
									</c:forEach>
													</span>
												</c:if>
											</p>
										</div>
									</c:forEach></td>



						<td>
							<div class="dish-price">
								<p>${order.basket.totalPrice}</p>
							</div>

						</td>


						<td>
							<div class="time">
							
								<p>${order.orderTime}</p>
							</div>

						</td>

						<td>
							<div class="time">
							
								<p>${order.deliveryTime}</p>
							</div>

						</td>

						<td>
							<div class="">
								<p>${order.paymentType}</p>
							</div>

						</td>

						<td>
							<div class="">
							
								<p>${order.deliveryType}</p>
							</div>

						</td>

						<td>
							<div class="state">
								<p>${order.state}</p>
							</div>
						</td>

						<c:if test="${order.state == 'IN_PROCESS'}">
							<td>
								<form action="${pageContext.request.contextPath}/Controller"
									method="post">
									<input type="hidden" name="command" value="do_order">
									<input type="hidden" name="order_id" value="${order.id}">
									<input type="hidden" name="orderTime" value="${order.orderTime}">
									<input type="hidden" name="deliveryTime" value="${order.deliveryTime}">
									<input type="hidden" name="deliveryType" value="${order.deliveryType}">
									<input type="hidden" name="login" value="${order.userLogin}">
									
									<button class="btn btn-light" type="submit" style="width:150px;">Приготовить</button>

								</form>

							</td>
						</c:if>
						
						<c:if test="${order.state == 'DONE'}">
							<td>
								<form action="${pageContext.request.contextPath}/Controller"
									method="post">
									<input type="hidden" name="command" value="close_order">
									<input type="hidden" name="order_id" value="${order.id}">
									<button class="btn btn-light" type="submit" style="width:50px;">Закрыть</button>

								</form>
							</td>
						</c:if>

					</tr>
				</c:forEach>



			</tbody>
				</table>

</c:otherwise>
</c:choose>
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
<script src="${pageContext.request.contextPath}/js/myJS.js"></script>
	
<script>

</script>
 
</body>
</html>