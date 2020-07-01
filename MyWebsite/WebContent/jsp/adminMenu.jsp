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
td, tr {
	text-align: center;
	width: 45px;
	height: 50px;
	vertical-align: center;
}

#window {
	width: 375px;
	height: 485px;
	text-align: center;
	padding: 0px;
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

#add, #change {
	background: rgba(102, 102, 102, 0.8);
	width: 100%;
	height: 100%;
	position: absolute;
	top: 0;
	left: 0;
	display: none;
}

#add:target, #change:target {
	display: block;
}

*
      
      
      .ch {
	display: inline-block;
	border: 1px solid #0000cc;
	color: #0000cc;
	padding: 0 12px;
	margin: 10px;
	text-decoration: none;
	background: #f2f2f2;
	font-size: 14pt;
	cursor: pointer;
	width: 120px;
}

.change:hover {
	background: #e6e6ff;
}

.close {
	position: relative;
	display: inline-block;
	/* border: 1px solid #0000cc;*/
	color: #0000cc;
	padding: 0 12px;
	/*margin: 10px;*/
	margin-left: 250px; */
	margin-bottom: 10px;
	margin-right: 1px;
	margin-top: 0px;
	text-decoration: none;
	background: #f2f2f2;
	/*0font-size: 14pt;*/
	cursor: pointer;
	font-size: 15px;
}

      .close:hover {background: #e6e6ff;}
      

.checkout {
	width: 330px;
	height: 410px;
	margin: 50px auto;
	padding: 15px;
	background: #f3f6fa;
	border: 1px solid;
	border-color: #c2cadb #bbc5d6 #b7c0cd;
	border-radius: 7px;
	-webkit-box-shadow: 0 1px 5px rgba(0, 0, 0, 0.15);
	box-shadow: 0 1px 5px rgba(0, 0, 0, 0.15);
}

.checkout>p {
	zoom: 1;
}

.checkout>p:before, .checkout>p:after {
	content: '';
	display: table;
}

.checkout>p:after {
	clear: both;
}

.checkout>p+p {
	margin-top: 15px;
}

.checkout-header {
	position: relative;
	margin: -15px -15px 5px;
}

.checkout-title { /*Редактировать заголовок*/
	padding: 0 15px;
	/**margin-bottom:15px;*/
	line-height: 38px;
	font-size: 22px;
	font-weight: bold;
	color: #0000cce;
	text-shadow: 0 1px rgba(255, 255, 255, 0.7);
	background: #eceff5;
	border-bottom: 1px solid #c5ccdb;
	border-radius: 7px 7px 0 0;
	background-image: -webkit-linear-gradient(top, #f5f8fb, #e9edf3);
	background-image: -moz-linear-gradient(top, #f5f8fb, #e9edf3);
	background-image: -o-linear-gradient(top, #f5f8fb, #e9edf3);
	background-image: linear-gradient(to bottom, #f5f8fb, #e9edf3);
	-webkit-box-shadow: inset 0 1px white;
	box-shadow: inset 0 1px white;
	margin-bottom: 30px;
}

.checkout-title, type:before {
	content: '';
	position: absolute;
	bottom: 0;
	left: 0;
	right: 0;
	height: 2px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, 0.08);
	box-shadow: 0 1px 2px rgba(0, 0, 0, 0.08);
}

.type {
	font-size: 16px;
	font-weight: bold;
	color: rgba(47, 52, 238, 0.8);
	text-shadow: 0 1px rgba(255, 255, 255, 0.7);
	background: #eceff5;
	border-bottom: 1px solid #c5ccdb;
	border-radius: 7px 7px 0 0;
	background-image: -webkit-linear-gradient(top, #f5f8fb, #e9edf3);
	background-image: -moz-linear-gradient(top, #f5f8fb, #e9edf3);
	background-image: -o-linear-gradient(top, #f5f8fb, #e9edf3);
	background-image: linear-gradient(to bottom, #f5f8fb, #e9edf3);
	-webkit-box-shadow: inset 0 1px white;
	box-shadow: inset 0 1px white;
}

.custom-control-label {
	font-size: 14px;
	color: rgba(47, 52, 238, 0.8);
}

.checkout-price {
	position: absolute;
	top: -14px;
	right: -14px;
	width: 40px;
	font: 14px/40px Helvetica, Arial, sans-serif;
	color: white;
	text-align: center;
	text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.3);
	text-indent: -1px;
	letter-spacing: -1px;
	background: #e54930;
	border: 1px solid;
	border-color: #b33323 #ab3123 #982b1f;
	border-radius: 21px;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	background-image: -webkit-linear-gradient(top, #f75a3b, #d63b29);
	background-image: -moz-linear-gradient(top, #f75a3b, #d63b29);
	background-image: -o-linear-gradient(top, #f75a3b, #d63b29);
	background-image: linear-gradient(to bottom, #f75a3b, #d63b29);
	-webkit-box-shadow: inset 0 1px 1px rgba(255, 255, 255, 0.3), 0 1px 2px
		rgba(0, 0, 0, 0.2);
	box-shadow: inset 0 1px 1px rgba(255, 255, 255, 0.3), 0 1px 2px
		rgba(0, 0, 0, 0.2);
}

.checkout-price:before {
	content: '';
	position: absolute;
	top: 3px;
	bottom: 3px;
	left: 3px;
	right: 3px;
	border: 2px solid #f5f8fb;
	border-radius: 18px;
	-webkit-box-shadow: inset 0 1px 1px rgba(255, 255, 255, 0.2), inset 0
		-1px 1px rgba(0, 0, 0, 0.25), 0 -1px 1px rgba(0, 0, 0, 0.25);
	box-shadow: inset 0 1px 1px rgba(255, 255, 255, 0.2), inset 0 -1px 1px
		rgba(0, 0, 0, 0.25), 0 -1px 1px rgba(0, 0, 0, 0.25);
}

input {
	line-height: normal;
	font-family: inherit;
	font-size: 100%;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	margin-bottom: 10px;
	margin-top: 10px;
	height: 40px;
	width: 280px;
	font-size: 17px;
	border: 1px solid #0000cc;
}

.checkout-input:focus {
	border-color: #46aefe;
	outline: none;
	-webkit-box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.1), 0 0 5px #46aefe;
	box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.1), 0 0 5px #46aefe;
}

.lt-ie9 .checkout-input {
	line-height: 30px;
}

.checkout-btn {
	width: 100%;
	height: 34px;
	padding: 0;
	font-weight: bold;
	color: white;
	text-align: center;
	text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.2);
	border: 1px solid;
	border-color: #1486f9 #0f7de9 #0d6acf;
	background: #1993fb;
	border-radius: 4px;
	background-image: -webkit-linear-gradient(top, #4cb1fe, #229afc 40%, #138df6);
	background-image: -moz-linear-gradient(top, #4cb1fe, #229afc 40%, #138df6);
	background-image: -o-linear-gradient(top, #4cb1fe, #229afc 40%, #138df6);
	background-image: linear-gradient(to bottom, #4cb1fe, #229afc 40%, #138df6);
	-webkit-box-shadow: inset 0 1px rgba(255, 255, 255, 0.2), 0 1px 2px
		rgba(0, 0, 0, 0.2);
	box-shadow: inset 0 1px rgba(255, 255, 255, 0.2), 0 1px 2px
		rgba(0, 0, 0, 0.2);
}

.checkout-btn:active {
	text-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
	border-color: #075bba #0c69d2 #0f7de9;
	background-image: -webkit-linear-gradient(top, #1281dc, #1593fc);
	background-image: -moz-linear-gradient(top, #1281dc, #1593fc);
	background-image: -o-linear-gradient(top, #1281dc, #1593fc);
	background-image: linear-gradient(to bottom, #1281dc, #1593fc);
	-webkit-box-shadow: inset 0 1px 4px rgba(0, 0, 0, 0.1), 0 1px
		rgba(255, 255, 255, 0.5);
	box-shadow: inset 0 1px 4px rgba(0, 0, 0, 0.1), 0 1px
		rgba(255, 255, 255, 0.5);
}

:-moz-placeholder {
	color: #acb6c8 !important;
}

::-moz-placeholder {
	color: #acb6c8 !important;
	opacity: 1;
}

::-webkit-input-placeholder {
	color: #acb6c8;
}

:-ms-input-placeholder {
	color: #acb6c8;
}

::-moz-focus-inner {
	padding: 0 !important;
	border: 0 !important;
}

.deactive {
	display: none;
}

.dish-img, delete-btn {
	position: relative;
	padding-bottom: 100%;
	overflow: hidden;
}

.dish-img img {
	position: absolute;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	max-width: 100%;
	max-height: 100%;
	margin: auto;
	transition: transform .4s ease-out;
}

.dish-title p {
	color: #704520;
	font-weight: 500;
	text-transform: uppercase;
}

.dish-price {
	color: rgb(217, 0, 5);
	font-size: 20px;
	margin-right: 20px;
}

.dish-amount {
	width: 40px;
	height: 30px;
	text-align: center;
}
</style>


<title>${orders_text}</title>
</head>
<body>
	<c:import url="header.jsp" />

	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="resources.localization.local" var="loc" />
	<fmt:message bundle="${loc}" key="dish_changed" var="dish_changed" />
	<fmt:message bundle="${loc}" key="change_dish_error"
		var="change_dish_error" />

	<c:if test="${not empty requestScope.changeDishError}">
		<div class="alert alert-danger" role="alert">
			<strong><c:out value="${change_dish_error}" /></strong>
		</div>
	</c:if>
	<c:if test="${not empty requestScope.changeDishSuccess}">
		<div class="alert alert-success" role="alert">
			<strong><c:out value="${dish_changed}" /></strong>
		</div>
	</c:if>

	<div class="container-fluid">
	
	<form action="${pageContext.request.contextPath}/Controller" method="post">
		
		<div class="btn-group btn-group-lg" role="group">

			<button name="command" value="export_to_xml" type="submit"
				class="btn btn-dark" style="display: inline;height:40px; width:150px;">Экспорт</button>
				</div>
				</form>
				
				
		<!--		<form action="${pageContext.request.contextPath}/Controller" method="post">
		 
		XML:
		<input type="file" class="form-control-file" name="fileName" style="height:40px; display: inline; width:250px;">
		<div class="btn-group" role="group">

			<button name="command" value="stax_parser" type="submit"
				class="btn btn-dark" style="display: inline; width:150px; height:40px;">Импорт</button>
				</div>
				</form>
		-->	
		<table class="table table-hover">
			<thead class="thead-dark">
				<tr>
					<th scope="col">Номер блюда</th>
					<th scope="col">Изображение</th>
					<th scope="col">Название</th>
					<th scope="col">Категория</th>
					<th scope="col">Вес</th>
					<th scope="col">Цена</th>
					<th scope="col" colspan=2><a href="#add"
						style="padding: 5px; color: #fff; border: 1.5px solid #fff">Добавить
							блюдо</a></th>
							
				
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.allDishes}" var="dish">
					<tr>
						<td>
							<p id="id">${dish.id}</p>

						</td>
						<td>
							<div class="dish-img">
								<img src="${dish.picture}" alt="" id="dishPicture">
							</div>
						</td>




						<td>
							<div class="dish-title">

								<p>${dish.name}</p>
								<div class="dish-ingred">
									<c:forEach items="${dish.ingredients}" var="ingredient">
										<span><c:out value="${ingredient}, " /></span>
									</c:forEach>
								</div>
							</div>
						</td>
						<td>
							<div class="dish-category">

								<p>${dish.category}</p>
							</div>
						</td>
						<td>
							<div class="dish-amount">
								<p id="dishAmount">${dish.amount}</p>
							</div>

						</td>

						<td>
							<div class="dish-price">
								<p id="dishPrice">${dish.price}</p>
							</div>

						</td>
						<td><a href="#change" class="change">Изменить</a></td>
						<td>
							<form action="${pageContext.request.contextPath}/Controller"
								method="post">
								<input type="hidden" name="command" value="delete_dish">
								<input type="hidden" name="dishId" value="${dish.id}">
								<button class="btn btn-danger" type="submit"
									style="width: 90px;">Удалить</button>

							</form>

						</td>

					</tr>
				</c:forEach>



			</tbody>
		</table>

	</div>




	<div id="change">
		<div id="window">
			<a href="#" class="close"> <img name="cross"
				src="http://itsnottrashdesigns.com/wp-content/uploads/2016/05/Remove_Item_icon.png"
				style="wigth: 20px; height: 20px;">
			</a>
			<form class="checkout validate-form"
				action="${pageContext.request.contextPath}/Controller" method="post">
				<input type="hidden" name="dishId" id="dishId"> <input
					type="hidden" name="command" value="change_dish">

				<div class="checkout-header">
					<h1 class="checkout-title">Редактирование блюда</h1>
				</div>

				<div id="login" class="validate-input">
					<!--валидация   локализация -->

					<input type="text" name="picture" id="picture"
						style="margin-bottom: 20px; margin-top: 10px;"> <input
						type="text" name="price" id="price" style="margin-bottom: 20px;">
					<input type="text" name="amount" id="amount"
						style="margin-bottom: 20px;"> <br>
				</div>

				<input class="ch" type="submit" value="Изменить">

			</form>
		</div>
	</div>





	<div id="add">
		<div id="window">
			<a href="#" class="close"> 
			<img name="cross"
				src="http://itsnottrashdesigns.com/wp-content/uploads/2016/05/Remove_Item_icon.png"
				style="wigth: 20px; height: 20px;">
			</a>
			<form class="checkout validate-form" accept-charset="utf-8"
				action="${pageContext.request.contextPath}/Controller" method="post"
				onsubmit="return validateForm(this)">
				<input type="hidden" name="command" value="add_dish">

				<div class="checkout-header">
					<h1 class="checkout-title">Добавление блюда</h1>
				</div>

				<div id="login" class="validate-input">
					<!--валидация   локализация -->

					<input type="text" name="dishName" placeholder="Введите название"
						style="margin-bottom: 20px; margin-top: 10px"> <input
						type="text" name="dishPicture"
						placeholder="Введите ссылку на картинку" id="picture"
						style="margin-bottom: 20px;"> <input type="text"
						name="dishAmount" placeholder="Введите вес" id="amount"
						style="margin-bottom: 20px;"> <br>
					<p>
						<select class="custom-select" name="dishCategory">
							<option disabled>Выберите категорию</option>
							<option value="SNACKS">Закуски</option>
							<option value="SALADS">Салаты</option>
							<option value="HOT_DISHES">Горячие блюда</option>
							<option value="PIZZA">Пицца</option>
							<option value="DRINKS">Напитки</option>
							<option value="DESSERTS">Десерты</option>

						</select>
					</p>
					<input type="text" name="dishPrice" placeholder="Введите цену"
						style="margin-bottom: 20px;"> <input type="text"
						name="dishIngredients"
						placeholder="Введите ингредиенты через запятую"
						style="margin-bottom: 10px;"> <br>
				</div>

				<input class="ch" type="submit" value="Добавить">

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
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
	<!--===============================================================================================-->
	<script src="${pageContext.request.contextPath}/js/myJS.js"></script>

	<script>
		
	</script>

</body>
</html>