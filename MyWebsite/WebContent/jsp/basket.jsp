<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

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

.image{
text-align: center;
 margin: 50px auto;
  font-size: 22px;
  font-family: 'Lobster', cursive;
}

div .selectedIngredients
{
	--text: "Отказаться от:";
}
.multiple_select
{
	height: 25px;
	width: 90%;
	overflow: hidden;
	-webkit-appearance: menulist;
	position: relative;
}
div .multiple_select::before
{
	content: var(--text);
	display: block;
  margin-left: 5px;
  margin-bottom: 2px;
}
.multiple_select_active
{
	overflow: visible !important;
}
.multiple_select option
{
	display: none;
    height: 25px;
	background-color: white;
}
.multiple_select_active option
{
	display: block;
}

.multiple_select option::before {
  content: "\2610";
}
.multiple_select option:checked::before {
  content: "\2611";
}

/*         */
 
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

.multiselect-container>li>a>label {
  padding: 4px 20px 3px 20px;
}




  </style>   
        
<title>${basket_text}</title>
</head>
<body>
<c:import url="header.jsp"/>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="text.basket" var="basket_text" />
<fmt:message bundle="${loc}" key="basket_cleared" var="empty_basket" />
<fmt:message bundle="${loc}" key="dish.title" var="title" />
<fmt:message bundle="${loc}" key="dish.count" var="count" />
<fmt:message bundle="${loc}" key="dish.price" var="price" />
<fmt:message bundle="${loc}" key="dish.ingredients" var="ingredients" />
<fmt:message bundle="${loc}" key="dish.refusalOfIngredients" var="refusalOfIngredients" />
<fmt:message bundle="${loc}" key="button.deleteAll" var="deleteAll_button" />
<fmt:message bundle="${loc}" key="makeOrder" var="make_order" />
<fmt:message bundle="${loc}" key="makingOrder" var="making_order" />
<fmt:message bundle="${loc}" key="chooseDeliveryType" var="choose_deliveryType" />
<fmt:message bundle="${loc}" key="selfDelivery" var="self_delivery" />
<fmt:message bundle="${loc}" key="byCourier" var="by_courier" />
<fmt:message bundle="${loc}" key="choosePaymentType" var="choose_paymentType" />
<fmt:message bundle="${loc}" key="paymentByCreditCard" var="by_credit_card" />
<fmt:message bundle="${loc}" key="paymentInCash" var="in_cash" />
<fmt:message bundle="${loc}" key="chooseDeliveryDateTime" var="choose_delivery_date_time" />
<fmt:message bundle="${loc}" key="text.order" var="order" />
<fmt:message bundle="${loc}" key="chooseIngredients" var="choose_ingredients" />
<fmt:message bundle="${loc}" key="text.totalPrice" var="total_price_text" />


	<div class="container-fluid">
		<c:choose>
			<c:when test="${sessionScope.basket == null}">
				<div class="image">
					<h1>${empty_basket}</h1>
					<img src="https://s3.amazonaws.com/cdn.freshdesk.com/data/helpdesk/attachments/production/11001410572/original/shopping109.png?1455021247">
				</div>
			</c:when>
			<c:otherwise>
				<table class="table table-hover">
					<thead class="thead-dark">
						<tr>
							<th scope="col">
								<form action="${pageContext.request.contextPath}/Controller"
									method="post">
									<input type="hidden" name="command" value="clear_basket">
									<button class="btn btn-light" type="submit" style="width:115px">${deleteAll_button}</button>

								</form>

							</th>
							<th scope="col">${title}</th>
							<th scope="col">${count}</th>
							<th scope="col">${price}</th>
							<th scope="col">${ingredients}</th>
							<th scope="col">${refusalOfIngredients}</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${basket.dishes}" var="dish">
							<tr>
								<td>
									<form action="${pageContext.request.contextPath}/Controller"
										method="post">
										<input type="hidden" name="command"	value="delete_dish_from_basket">
										 <input type="hidden"
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
											value="${basket.countDishById.get(dish.id)}" disabled>
										<button class="plus btn btn-success" type="button">+</button>
									
									
									</form>									
									
									</div>
								</td>


								<td>
									<div class="dish-price">
										<span id="price">${dish.price*basket.countDishById.get(dish.id)}</span>
									</div>

								</td>


								<td>
									<c:forEach items="${dish.ingredients}" var="ingredient">
										<ul>
											<li>${ingredient}</li>
										</ul>
									</c:forEach>
								</td>


								<c:if test="${not empty dish.ingredients}">
									<td>
										<div style="padding: 20px" class="selectedIngredients">
											<input type="hidden" id="dishId" value="${dish.id}" /> 
											<select
												name="ingredientsForRefusal" id="myFilter"
												class="multiple_select" multiple>
												<c:forEach items="${dish.ingredients}" var="ingredient">
													<option value="${ingredient}">${ingredient}</option>
												</c:forEach>
											</select>

										</div>
									<td>
								
								</c:if>
								
							</tr>
						</c:forEach>

						<tr class="table-success">
							<td colspan="3">${total_price_text}</td>
							<td colspan="2"><span id="totalPrice">${basket.totalPrice}</span> 
							   руб
							<input type="hidden" id="original_total_price" value="${basket.totalPrice}">
							   
							</td>
							<td>
							<div>
								У вас 
							
								<span id="loyalty_points">${userLoyaltyPoints}</span> баллов
								<input type="hidden" id="original_loyalty_points" value="${userLoyaltyPoints}">
								<br>
								
									<input type="text" id="countOfPoints" pattern="(0|[1-9]\d{0,3})([.,]\d{1,2})$">
									<button class="use_loyalty_points btn-success"  type="button" style="width: 150px;">ИСПОЛЬЗОВАТЬ</button>
									<button class="reset_loyalty_points btn-danger"  type="button" style="width: 150px;">СБРОСИТЬ</button>
					
									
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<a href="#blur">${make_order}</a>
							</td>
						</tr>
					</tbody>
				</table>

			</c:otherwise>
		</c:choose>
		
		
		
		
		
	</div>

	<div id="blur">
		<div class="order" id="window">
			<a href="#" class="close"> <img name="cross"
				src="http://itsnottrashdesigns.com/wp-content/uploads/2016/05/Remove_Item_icon.png"
				style="wigth: 20px; height: 20px;">
			</a>
			<form class="checkout"
				action="${pageContext.request.contextPath}/Controller" method="post">
				<input type="hidden" name="command" value="create_order">

				<div class="checkout-header">
					<h1 class="checkout-title">${making_order}</h1>
				</div>

				<span class="type">${choose_deliveryType}:</span>
				<div class="custom-control custom-radio">
					<input type="radio" id="by_courier" name="delivery" value="courier"
						class="checkout-input custom-control-input"> <label
						class="custom-control-label" for="by_courier">${by_courier}</label>
				</div>
				<div class="custom-control custom-radio">
					<input type="radio" id="self_delivery" name="delivery"
						value="self_delivery" class="checkout-input custom-control-input"
						checked> <label class="custom-control-label"
						for="self_delivery">${self_delivery}</label>
				</div>

				<br> <br> <span class="type">${choose_paymentType}:</span>
				<div class="custom-control custom-radio">
					<input type="radio" id="credit_card" value="card" name="payment"
						class="checkout-input custom-control-input"
						onChange="Selected(this)"> <label
						class="custom-control-label" for="credit_card">${by_credit_card}</label>
				</div>
				<div class="custom-control custom-radio">
					<input type="radio" id="in_cash" name="payment" value="cash"
						class="checkout-input custom-control-input" checked> <label
						class="custom-control-label" for="in_cash">${in_cash}</label>
				</div>
				<br> <br> <span class="type">${choose_delivery_date_time}:</span> 
				
				<input type="datetime-local" id="txtDateTime" class="checkout-input" name="dateTime" required/>
				
				<br> <br>
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
					<input type="submit" value="${order}" class="checkout-btn">
				</p>
			</form>
		</div>
	</div>

	<!--  
<form id="form1">
<div style="padding:20px">
<select id="chkveg" multiple="multiple">
<option value="cheese">Cheese</option>
<option value="tomatoes">Tomatoes</option>
<option value="mozarella">Mozzarella</option>
<option value="mushrooms">Mushrooms</option>
<option value="pepperoni">Pepperoni</option>
<option value="onions">Onions</option>

</select><br /><br />
<input type="button" id="btnget" value="Get Selected Values" />
</div>
</form>

-->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

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
	<script src="${pageContext.request.contextPath}/vendor/daterangepicker/daterangepicker.js"></script>
<script src="${pageContext.request.contextPath}/vendor/countdowntime/countdowntime.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/js/myJS.js"></script>
	
<script>
/*
$(".multiple_select").mousedown(function(e) {
    if (e.target.tagName == "OPTION") 
    {
      return; //don't close dropdown if i select option
    }
    $(this).toggleClass('multiple_select_active'); //close dropdown if click inside <select> box
});
$(".multiple_select").on('blur', function(e) {
    $(this).removeClass('multiple_select_active'); //close dropdown if click outside <select>
});
	
$('.multiple_select option').mousedown(function(e) { //no ctrl to select multiple
    e.preventDefault(); 
    $(this).prop('selected', $(this).prop('selected') ? false : true); //set selected options on click
    $(this).parent().change(); //trigger change event
});

	
	$("#myFilter").on('change', function() {
      var selected = $("#myFilter").val().toString(); //get all options and convert to string
      /*
      var document_style = document.documentElement.style;
      if(selected !== ""){
        document_style.setProperty('--text', "'Отказаться от: "+selected+"'");
      }else{
        document_style.setProperty('--text', "'Выберите ингредиенты'");
      }
      
      
		var dishId = $(this).parent().find('#dishId').val();
		var command = "refuse_of_ingredients";

      $.ajax({
			type: 'POST',
			data: {command: command, dishId:dishId, selected:selected},
			url:  'http://localhost:8080/MyWebsite/AjaxController',
			success : function(result){
				
				var obj = JSON.parse(result)

				if(obj.status == "yes"){
					alert("Ok!")
				}else{
					alert("No!")
				}
			}	
		});
      
      
	});


	*/
	
	/*
	function Selected(a) {
	  document.getElementById("courier").style.display = 'none';
	  document.getElementById("card").style.display = 'none';

	  document.getElementById(a.value).style.display = 'block';
	}
	
	*/
	
	function zero_first_format(value)
    {
        if (value < 10)
        {
            value='0'+value;
        }
        return value;
    }

$(function(){
    var dtToday = new Date();
    
    var month = zero_first_format(dtToday.getMonth() + 1);
    var nextMonth = zero_first_format(dtToday.getMonth() + 2);
    var day = zero_first_format(dtToday.getDate());
    var year = dtToday.getFullYear();
    
    var minDate = year + '-' + month + '-' + day;
    var maxDate = year + '-' + nextMonth + '-' + day;
    
    var hours = zero_first_format(dtToday.getHours());
    var minutes = zero_first_format(dtToday.getMinutes());
    var minDateTime = minDate + 'T' + hours + ':' + minutes;
    var maxDateTime = maxDate + 'T' + '17' + ':' + '00';

    //alert(minDateTime);

    $('#txtDateTime').attr('min', minDateTime);
    $('#txtDateTime').attr('max', maxDateTime);


});

</script>

<script>


</script>
 

									</body>
</html>