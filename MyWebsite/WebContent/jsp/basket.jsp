<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<%@ page buffer="812kb" autoFlush="true" %>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
    
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

 
body {

  background-color: rgb(175,238,238);
  font-family: 'Roboto', sans-serif;
}

.dish-img, delete-btn{
	
	
    position: relative;
	padding-bottom: 100%;
	overflow: hidden;
}
 
.dish-img img{

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
.dish-price{
    color: rgb(217, 0, 5);
    font-size: 20px;
    margin-right: 20px;
}
.dish-amount{
	width: 40px;
  	height: 30px;
  	text-align:center;
  	
}


button[class*=btn] {
  width: 40px;
  height: 30px;
  background-color: rgb(230,230,250);
  border-radius: 6px;
  border: none;
  cursor: pointer;
}

 
button:focus,
input:focus {
  outline:0;
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
        width: 390px;
        height: 500px;
        text-align: center;
        padding: 5px;
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
      #blur:target {display: block;}
      .close {
        display: inline-block;
        border: 1px solid #0000cc;
        color: #0000cc;
        padding: 0 12px;
        margin-top:0px;
        margin-right: 0px;
        margin-left:180px;
        text-decoration: none;
        background: #f2f2f2;
        font-size: 14pt;
        cursor:pointer;
      }
      .close:hover {background: #e6e6ff;}
      
      
      


.order html, body, div, span, applet, object, iframe,
h1, h2, h3, h4, h5, h6, p, blockquote, pre,
a, abbr, acronym, address, big, cite, code,
del, dfn, em, img, ins, kbd, q, s, samp,
small, strike, strong, sub, sup, tt, var,
b, u, i, center,
dl, dt, dd, ol, ul, li,
fieldset, form, label, legend,
table, caption, tbody, tfoot, thead, tr, th, td,
article, aside, canvas, details, embed,
figure, figcaption, footer, header, hgroup,
menu, nav, output, ruby, section, summary,
time, mark, audio, video {
  margin: 0;
  padding: 0;
  border: 0;
  font-size: 100%;
  font: inherit;
  vertical-align: baseline;
}

.order article, aside, details, figcaption, figure,
footer, header, hgroup, menu, nav, section {
  display: block;
}

.order {
  line-height: 1;
}

.order ol, ul {
  list-style: none;
}

.order blockquote, q {
  quotes: none;
}

.order blockquote:before, blockquote:after,
q:before, q:after {
  content: '';
  content: none;
}

.order table {
  border-collapse: collapse;
  border-spacing: 0;
}

.order {
  font: 13px/20px 'Lucida Grande', Verdana, sans-serif;
  color: #404040;
  background: #dbe0eb;
}

.checkout {
  width: 330px;
  heigth:420px;
  margin: 50px auto;
  padding: 5px;
  background: #f3f6fa;
  border: 1px solid;
  border-color: #c2cadb #bbc5d6 #b7c0cd;
  border-radius: 7px;
  -webkit-box-shadow: 0 1px 5px rgba(0, 0, 0, 0.15);
  box-shadow: 0 1px 5px rgba(0, 0, 0, 0.15);
}
.checkout > p {
  zoom: 1;
}
.checkout > p:before, .checkout > p:after {
  content: '';
  display: table;
}
.checkout > p:after {
  clear: both;
}
.checkout > p + p {
  margin-top: 15px;
}

.checkout-header {
  position: relative;
  margin: -15px -15px 15px;
}

.checkout-title {
  padding: 0 15px;
 /**margin-bottom:15px;*/
  line-height: 38px;
  font-size: 22px;
  font-weight: bold;
  color: #7f889e;
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
  margin-bottom:30px;
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
.type{

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
.custom-control-label{
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
  -webkit-box-shadow: inset 0 1px 1px rgba(255, 255, 255, 0.3), 0 1px 2px rgba(0, 0, 0, 0.2);
  box-shadow: inset 0 1px 1px rgba(255, 255, 255, 0.3), 0 1px 2px rgba(0, 0, 0, 0.2);
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
  -webkit-box-shadow: inset 0 1px 1px rgba(255, 255, 255, 0.2), inset 0 -1px 1px rgba(0, 0, 0, 0.25), 0 -1px 1px rgba(0, 0, 0, 0.25);
  box-shadow: inset 0 1px 1px rgba(255, 255, 255, 0.2), inset 0 -1px 1px rgba(0, 0, 0, 0.25), 0 -1px 1px rgba(0, 0, 0, 0.25);
}

input {
  margin: 0;
  line-height: normal;
  font-family: inherit;
  font-size: 100%;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}

.checkout-input {
  float: center;
  padding: 10px 7px;
  height: 32px;
  color: #525864;
  background: white;
  border: 1px solid;
  border-color: #b3c0e2 #bcc5e2 #c0ccea;
  border-radius: 4px;
  background-image: -webkit-linear-gradient(top, #f6f8fa, white);
  background-image: -moz-linear-gradient(top, #f6f8fa, white);
  background-image: -o-linear-gradient(top, #f6f8fa, white);
  background-image: linear-gradient(to bottom, #f6f8fa, white);
  -webkit-box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.1), 0 1px rgba(255, 255, 255, 0.5);
  box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.1), 0 1px rgba(255, 255, 255, 0.5);
margin:10px 50px;
margin-bottom:5px;	
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

.checkout-name {
  width: 150px;
}

.checkout-card {
  width: 210px;
}

.checkout-exp,
.checkout-cvc {
  margin-left: 15px;
  width: 45px;
}

.checkout-btn {
  width: 200px;
  height: 34px;
  padding: 0;
  margin-top:0px;
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
  -webkit-box-shadow: inset 0 1px rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.2);
  box-shadow: inset 0 1px rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.2);
}
.checkout-btn:active {
  text-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
  border-color: #075bba #0c69d2 #0f7de9;
  background-image: -webkit-linear-gradient(top, #1281dc, #1593fc);
  background-image: -moz-linear-gradient(top, #1281dc, #1593fc);
  background-image: -o-linear-gradient(top, #1281dc, #1593fc);
  background-image: linear-gradient(to bottom, #1281dc, #1593fc);
  -webkit-box-shadow: inset 0 1px 4px rgba(0, 0, 0, 0.1), 0 1px rgba(255, 255, 255, 0.5);
  box-shadow: inset 0 1px 4px rgba(0, 0, 0, 0.1), 0 1px rgba(255, 255, 255, 0.5);
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


/*
.shopping-cart {
  width: 750px;
  height: 423px;
  margin: 80px auto;
  background: #FFFFFF;
  box-shadow: 1px 2px 3px 0px rgba(0,0,0,0.10);
  border-radius: 6px;
 
  display: flex;
  flex-direction: column;
}
.title {
  height: 60px;
  border-bottom: 1px solid #E1E8EE;
  padding: 20px 30px;
  color: #5E6977;
  font-size: 18px;
  font-weight: 400;
}
 
.item {
  padding: 20px 30px;
  height: 120px;
  display: flex;
}
 
.item:nth-child(3) {
  border-top:  1px solid #E1E8EE;
  border-bottom:  1px solid #E1E8EE;
}
.buttons {
  position: relative;
  padding-top: 30px;
  margin-right: 60px;
}
.delete-btn{
  display: inline-block;
  Cursor: pointer;
  width: 20px;
  height: 20px;
  background: url("") no-repeat center;
}
 

.is-active {
  animation-name: animate;
  animation-duration: .8s;
  animation-iteration-count: 1;
  animation-timing-function: steps(28);
  animation-fill-mode: forwards;
}
 
@keyframes animate {
  0%   { background-position: left;  }
  50%  { background-position: right; }
  100% { background-position: right; }
}
.image {
  margin-right: 50px;
    position: relative;
	overflow: hidden;
}
 
.image img{
    position: absolute;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	max-width: 30%;
	max-height: 30%;
	margin: auto;
	transition: transform .4s ease-out;
}
 

.description {
  padding-top: 10px;
  margin-right: 60px;
  width: 115px;
}
 
.description span {
  display: block;
  font-size: 14px;
  color: #43484D;
  font-weight: 400;
}
 
.description span:first-child {
  margin-bottom: 5px;
}
.description span:last-child {
  font-size: 10px;
  font-weight: 300;
  margin-top: 8px;
  color: #86939E;
}
.quantity {
  padding-top: 20px;
  margin-right: 60px;
}
.quantity input {
  -webkit-appearance: none;
  border: none;
  text-align: center;
  width: 32px;
  font-size: 16px;
  color: #43484D;
  font-weight: 300;
}
 
button[class*=btn] {
  width: 30px;
  height: 30px;
  background-color: #E1E8EE;
  border-radius: 6px;
  border: none;
  cursor: pointer;
}
.minus-btn img {
  margin-bottom: 3px;
}
.plus-btn img {
  margin-top: 2px;
}
 
button:focus,
input:focus {
  outline:0;
}
.total-price {
  width: 83px;
  padding-top: 27px;
  text-align: center;
  font-size: 16px;
  color: #43484D;
  font-weight: 300;
}
@media (max-width: 800px) {
  .shopping-cart {
    width: 100%;
    height: auto;
    overflow: hidden;
  }
  .item {
    height: auto;
    flex-wrap: wrap;
    justify-content: center;
  }
  .image img {
    width: 30%;
    height: 30%;
  }

  .quantity,
  .description {
    width: 100%;
    text-align: center;
    margin: 6px 0;
  }

}
*/


  </style>   
    
    
<title>${basket_text}</title>
</head>
<body>
<c:import url="header.jsp"/>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="text.basket" var="basket_text" />
<fmt:message bundle="${loc}" key="basket_cleared" var="empty_basket" />

	<div class="container-fluid">
		<c:choose>
			<c:when test="${sessionScope.basket == null}">
				<h1 style="text-align: center;">${empty_basket}</h1>
			</c:when>
			<c:otherwise>


				<table class="table table-hover">
					<thead class="thead-dark">
						<tr>
							<th scope="col">
								<form action="${pageContext.request.contextPath}/Controller"
									method="post">
									<input type="hidden" name="command" value="clear_basket">
									<button class="btn btn-light" type="submit" style="width:115px">Удалить всё</button>

								</form>

							</th>
							<th scope="col">Название</th>
							<th scope="col">Количество</th>
							<th scope="col">Цена</th>
							<th scope="col">Ингредиенты</th>
			<!-- 				<th scope="col">Refusal of ingredients</th>
-->
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${basket.dishes}" var="dish">
							<tr>
								<td>
									<form action="${pageContext.request.contextPath}/Controller"
										method="post">
										<input type="hidden" name="command"	value="delete_dish_from_basket"> <input type="hidden"
											name="dishId" value="${dish.id}">

										<button class="delete-btn">
											<img
												src="https://avatanplus.com/files/resources/mid/57758834c8299155a31c0e54.png"
												width="20" height="20" alt="" />
										</button>
									</form>
								</td>
								<td>
									<div class="dish-img">

										<img src="${dish.picture}" alt="">
									</div>
									<div class="dish-title">

										<p>${dish.name}</p>
									</div>


								</td>

								<td>
									<div class="dish-amount">
									
									<!-- удали эту форму -->
									<form action="${pageContext.request.contextPath}/Controller"
										method="post">
										<input type="hidden" id="command"	value="change_amount_of_dish">
										 <input type="hidden" name="dishId" id="dishId" value="${dish.id}">
																		
										<button class="minus btn btn-danger"  type="button">-</button>
										<input type="text"  name="count" id="count" class="form-control"
											value="${basket.countDishById.get(dish.id)}">
										<button class="plus btn btn-success" type="button">+</button>
									
									
									</form>									
									
									</div>
								</td>


								<td>
									<div class="dish-price">
										<span id="price">${dish.price*basket.countDishById.get(dish.id)}</span>
									</div>

								</td>


								<td><c:forEach items="${dish.ingredients}" var="ingredient">
										<ul>
											<li>${ingredient}</li>
										</ul>
									</c:forEach></td>
								<td></td>

							</tr>
						</c:forEach>

						<tr class="table-success">
							<td colspan="4">Итого</td>
							<td colspan="2"><span id="totalPrice"> 
							   <fmt:formatNumber value="${basket.totalPrice}" type="number"/>
							</span></td>
						</tr>
						<tr>
							<td>
								<a href="#blur">Оформить заказ</a>
							</td>
						</tr>
					</tbody>
				</table>

			</c:otherwise>
		</c:choose>
	</div>




	<div id="blur">
		<div class="order" id="window">
<a href="#" class="close">
<img name="cross" src="http://itsnottrashdesigns.com/wp-content/uploads/2016/05/Remove_Item_icon.png" style="wigth:20px; height:20px;">
</a>
			<form class="checkout"
				action="${pageContext.request.contextPath}/Controller" method="post">
				<input type="hidden" name="command" value="create_order">

				<div class="checkout-header">
					<h1 class="checkout-title">
						Оформление заказа
						<!-- <span class="checkout-price">$10</span>
      -->
					</h1>
				</div>

				<span class="type">Выберите способ доставки:</span>
				<div class="custom-control custom-radio">
					<input type="radio" id="by_courier" name="delivery" value="courier"
						class="checkout-input custom-control-input">
					<label class="custom-control-label" for="by_courier">Курьером</label>
				</div>
				<div class="custom-control custom-radio">
					<input type="radio"  id="self_delivery" name="delivery" value="self_delivery"
						class="checkout-input custom-control-input" checked> 
						<label class="custom-control-label" for="self_delivery">Самовывоз</label>
				</div>

				<br>
				<br> <span class="type">Выберите способ оплаты:</span>
				<div class="custom-control custom-radio">
					<input type="radio" id="credit_card" value="card" name="payment"
						class="checkout-input custom-control-input"
						onChange="Selected(this)"> <label
						class="custom-control-label" for="credit_card">Картой</label>
				</div>
				<div class="custom-control custom-radio">
					<input type="radio" id="in_cash" name="payment" value="cash"
						class="checkout-input custom-control-input" checked>
					<label class="custom-control-label" for="in_cash">Наличными</label>
				</div>
				<br>
				<br> <span class="type">Выберите ожидаемое дату/время:</span>
				 <input type="date" class="checkout-input" name="date" required> 
				 <input type="time" class="checkout-input" name="time" requireds> <br>
				<br>
				<div id="card" class="deactive">
					<p>
						<input type="text" class="checkout-input checkout-exp"
							placeholder="MM"> <input type="text"
							class="checkout-input checkout-exp" placeholder="YY">
					</p>
					<p>
						<input type="text" class="checkout-input checkout-card"
							placeholder="1111 1111 1111 1111"> <input type="text"
							class="checkout-input checkout-cvc" placeholder="CVC">
					</p>

				</div>
				<p>
					<input type="submit" value="Оформить" class="checkout-btn">
				</p>



				

			</form>
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
<script src="${pageContext.request.contextPath}/js/myJS.js"></script>
	
<script>

	function Selected(a) {
	  document.getElementById("courier").style.display = 'none';
	  document.getElementById("card").style.display = 'none';

	  document.getElementById(a.value).style.display = 'block';
	}
</script>
 
</body>
</html>