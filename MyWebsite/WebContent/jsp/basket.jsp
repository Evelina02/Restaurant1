<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/css/style.css" type="text/css" rel="stylesheet">
    
 <style> 

 


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
									<button class="btn btn-dark" type="submit">Delete all</button>

								</form>

							</th>
							<th scope="col">Name</th>
							<th scope="col">Amount</th>
							<th scope="col">Price</th>
							<th scope="col">Ingredients</th>
							<th scope="col">Refusal of ingredients</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${basket.dishes}" var="dish">
							<tr>
								<td>
									<form action="${pageContext.request.contextPath}/Controller"
										method="post">
										<input type="hidden" name="command"
											value="delete_dish_from_basket"> <input type="hidden"
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
										<button class="minus btn btn-danger" type="button">-</button>
										<input type="text" class="form-control"
											value="${basket.countDishById.get(dish.id)}">
										<button class="plus btn btn-success" type="button">+</button>
									</div>
								</td>


								<td>
									<div class="dish-price">
										<span>${dish.price*basket.countDishById.get(dish.id)}</span>
									</div>

								</td>


								<td><c:forEach items="${dish.ingredients}" var="ingredient">
										<ul>
											<li>${ingredient}</li>
										</ul>
									</c:forEach></td>
								<td>

</td>

							</tr>
						</c:forEach>
						
						<tr class="table-success">
							<td colspan="4">
							Total price
							</td>
						<td colspan="2">
						
						</td>
						</tr>
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
	
<script>
            $('.minus').click(function () {
                var $input = $(this).parent().find('input');
                var count = parseInt($input.val()) - 1;
                count = count < 1 ? 1 : count;
                $input.val(count);
                $input.change();
                return false;
            });
            $('.plus').click(function () {
                var $input = $(this).parent().find('input');
                $input.val(parseInt($input.val()) + 1);
                $input.change();
                return false;
            });


</script>  
</body>
</html>