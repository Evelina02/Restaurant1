<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">

<%@ page buffer="812kb" autoFlush="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="restaurant" uri="/WEB-INF/tld/taglib.tld" %>
    
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/css/menu.css" type="text/css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/style.css" type="text/css" rel="stylesheet">



<title>${menu_text}</title>

<style type="text/css">

.serachInput:: -ms - clear{
display:none;
}

body{
 background: #745;
}

.content{
 margin-top: 50px;
}
        
 .products {
	margin: 0 -20px 0 0;
	padding: 0;
}
        
.dish {
    display: block;
 background: #fff none repeat scroll 0 0;
 border: 1px solid #c0c0c0;
  padding: 0px 5px;
 height: 620px;
    width: 100%;
	float: left;
 overflow: hidden;
 position: relative;
 text-align: center;
/* transition: all 0.5s ease 0s;*/
 margin-bottom: 20px;

	transition: width .2s;
}
.dish:hover {
 box-shadow: 0 0 16px rgba(0, 0, 0, 0.5);
}
        
        
        
        
.dish-img{
    position: relative;
	padding-bottom: 100%;
	margin: 0px 5px;
	padding-top:0px;
	
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
  .dish-title{
  padding-top:0;  
  
  }
  
        
.dish-title p {
 color: #704520;
 font-weight: 500;
 text-transform: uppercase;
 
}
    
.dish-ingred {
padding-top:0;
 height: 90px;
 text-transform: uppercase;

}     
 .dish-ingred span{
	position: relative;
	margin: 0;
	font-size: 10px;
	line-height: 1.4em;
	height: 5.6em;
	overflow: hidden;
} 
 .dish-amount{
   padding-top:0;  
   height:20px;
   margin-bottom:20px;
 
 }
  .dish-amount span{
	font-size: 10px;
}       
        
.dish-price{
   padding-top:0;  
height:40px;
    color: rgb(217, 0, 5);
    font-size: 22px;
    margin-bottom:10px;
   }

.amount {
   padding-top:0;  
	width:180px;
	margin-left:35px;
	margin-bottom:10px;
}
.amount span {
	display:inline-block;
	width:20px;
	height:20px;
	background:#ededed;
	cursor:pointer;
	text-align:center;
}
.amount span:hover {
	background:#dfdfdf;
}
.amount input {
	width:30px;
	text-align:center;
}
.addButton{
margin-right:7px;
text-decoration: none;
  outline: none;
  display: inline-block;
  width: 230px;
  height: 45px;
  line-height: 45px;
  border-radius: 45px;
 /* margin: 10px 20px;*/
  font-family: 'Montserrat', sans-serif;
  font-size: 11px;
  text-transform: uppercase;
  text-align: center;
  letter-spacing: 2.5px;
  font-weight: 600;
  color: #524f4e;
  background: rgb(245,255,250);
  box-shadow: 0 8px 15px rgba(0,0,0,.1);
  transition: .3s;
}
.addButton:hover {
  background: #2EE59D;
  box-shadow: 0 15px 20px rgba(46,229,157,.4);
  color: white;
  transform: translateY(-7px);
}
    
@media only screen and (min-width: 450px) {
	.dish {
		width: 50%;
	}
}

@media only screen and (min-width: 768px) {
	.dish {
		width: 33.333%;
	}
}

@media only screen and (min-width: 1000px) {
	.dish {
		width: 25%;
	}
}






#blur {
        background: rgba(102, 102, 102, 0.5);
        width: 100%;
        height: 100%;
        position: absolute;
        top: 0;
        left: 0;
        /*display: none;*/
      }
      #window {
        width: 800px;
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
      #blur:target {display: block;}
      .close {
        display: inline-block;
        border: 1px solid #0000cc;
        color: #0000cc;
        padding: 0 12px;
        margin: 10px;
        text-decoration: none;
        background: #f2f2f2;
        font-size: 14pt;
        cursor:pointer;
      }
      .close:hover {background: #e6e6ff;}
 
 
 
 
 
 
 
 
 * {box-sizing: border-box;}
body{margin: 0;}
div {padding: 10px 0}
form {
  position: relative;
  width: 300px;
  height:40px;
  margin: 0 auto;
}
.d7 {background: #7BA7AB;}
.d7:after {content:""; clear:both; display:table}
.d7 form {
  width: auto;
  float: right;
  margin-right: 30px;
}
.d7 input {
  width: 250px;
  height: 40px;
  padding-left: 15px;
  border-radius: 42px;
  border: 2px solid #324b4e;
  background: #F9F0DA;
  outline: none;
  position: relative;
  transition: .3s linear;
}
.d7 input:focus {
  width: 300px;
}
.d7 button {
  width: 42px;
  height: 40px;
  background: none;
  border: none;
  position: absolute;
  top: 0px;
  right: 0;
  
  border-radius:0 10px 10px 0;
}
.d7 button:before{

  font-family: FontAwesome;
  color: #324b4e;
}













#bg_popup{
background-color: rgba(0, 0, 0, 0.8);
displaynn: none;
position: absolute;
z-index: 99999;
top: 0;
right: 0;
bottom: 0;
left: 0;
}
  
#popup {
background: #fff;
width: 1020px;
height: 920px;
margin: auto;
padding: 5px 20px 13px 20px;
border: 2px solid #1BA600;
position: relative;
-webkit-box-shadow: 0px 0px 20px #000;
-moz-box-shadow: 0px 0px 20px #000;
box-shadow: 0px 0px 20px #000;
-webkit-border-radius: 15px;
-moz-border-radius: 15px;
border-radius: 15px;
}


    </style>

</head>
<body>
<c:import url="header.jsp"/>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="text.menu" var="menu_text" />
<fmt:message bundle="${loc}" key="dish_added" var="dish_added" />
<fmt:message bundle="${loc}" key="nothing_found" var="nothing_found" />
<fmt:message bundle="${loc}" key="button.addToBasket" var="add_to_basket" />
<fmt:message bundle="${loc}" key="dishCategory.snacks" var="snacks_text" />
<fmt:message bundle="${loc}" key="dishCategory.salads" var="salads_text" />
<fmt:message bundle="${loc}" key="dishCategory.hotDishes" var="hot_dishes_text" />
<fmt:message bundle="${loc}" key="dishCategory.pizza" var="pizza_text" />
<fmt:message bundle="${loc}" key="dishCategory.desserts" var="desserts_text" />
<fmt:message bundle="${loc}" key="dishCategory.drinks" var="drinks_text"/>
<fmt:message bundle="${loc}" key="searchHere" var="search_here"/>


      	<c:if test="${not empty requestScope.dishAdded}">
			<div class="alert alert-success" role="alert">
	        	<strong><c:out value="${dish_added}"/></strong>
        	</div>
    	</c:if>

      	<c:if test="${not empty requestScope.nothingFound}">
			<div class="alert alert-success" role="alert">
	        	<strong><c:out value="${nothing_found}"/></strong>
        	</div>
    	</c:if>
    	
<div class="container">

				<div class="d7">
					<form action="${pageContext.request.contextPath}/Controller" method="post">
						<input type="hidden" name="command"
							value="search_dishes_by_part_of_name"> 
							<input class="searchInput" type="text" placeholder="${search_here}..." name="partOfName">
						<button type="submit">
							<img name="cross"
								src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/c2/Blue_magnifying_glass_icon.svg/834px-Blue_magnifying_glass_icon.svg.png"
								style="wigth: 20px; height: 20px;">
						</button>
					</form>
				</div>



				<nav>
					<div class="nav nav-tabs nav-justified" id="nav-tab" role="tablist">
						<a class="nav-item nav-link active" id="nav-snacks-tab"
							data-toggle="tab" href="#nav-snacks" role="tab"
							aria-controls="nav-snacks" aria-selected="true">${snacks_text}</a> <a
							class="nav-item nav-link" id="nav-hot-dishes-tab"
							data-toggle="tab" href="#nav-hot-dishes" role="tab"
							aria-controls="nav-hot-dishes" aria-selected="false">${hot_dishes_text}</a> <a class="nav-item nav-link" id="nav-salads-tab"
							data-toggle="tab" href="#nav-salads" role="tab"
							aria-controls="nav-salads" aria-selected="false">${salads_text}</a> <a
							class="nav-item nav-link" id="nav-pizza-tab" data-toggle="tab"
							href="#nav-pizza" role="tab" aria-controls="nav-pizza"
							aria-selected="false">${pizza_text}</a> <a class="nav-item nav-link"
							id="nav-desserts-tab" data-toggle="tab" href="#nav-desserts"
							role="tab" aria-controls="nav-desserts" aria-selected="false">${desserts_text}</a>

						<a class="nav-item nav-link" id="nav-drinks-tab" data-toggle="tab"
							href="#nav-drinks" role="tab" aria-controls="nav-drinks"
							aria-selected="false">${drinks_text}</a>

					</div>
				</nav>

				<div class="tab-content" id="nav-tabContent">

					<div class="tab-pane fade show active" id="nav-snacks"
						role="tabpanel" aria-labelledby="nav-snacks-tab">
						<div class="products">

							<c:forEach items="${snacks}" var="snack">

								<div class="dish" id="${snack.id}">
									<div class="dish-img">
										<img src="${snack.picture}" alt="">
									</div>

									<div class="dish-title">
										<p>
											<c:out value="${snack.name}" />
										</p>
									</div>

									<div class="dish-ingred">
										<c:forEach items="${snack.ingredients}" var="ingredient">
											<span><c:out value="${ingredient}, " /></span>
										</c:forEach>
									</div>

									<div class="dish-amount">
										<p>
											<c:out value="${snack.amount}" />
										</p>
									</div>

									<div class="dish-price">
										<p>
											<c:out value="${snack.price} р." />
										</p>
									</div>

							<!-- 	<form action="${pageContext.request.contextPath}/Controller"
										method="post">
-->
										<input type="hidden" name="command" class="command" value="add_dish_to_basket">
										<input type="hidden" name="dishId" class="dishId" value="${snack.id}">
										<input type="hidden" name="dishCategory" class="dishCategory" value="snacks">

										<div class="amount">
											<button class="minus btn btn-danger" type="button">-</button>
											<input type="text" name="count" class="count" value="1" disabled>
											<button class="plus btn btn-success" type="button">+</button>
										</div>

										<button type="submit" class="addToBasket addButton"
											>
											${add_to_basket} 
										</button>

									<!-- </form>-->
									<!--/dish-buttons -->
								</div>
								<!--/dish -->
							</c:forEach>
						</div>
					</div>

					<div class="tab-pane fade" id="nav-hot-dishes" role="tabpanel"
						aria-labelledby="nav-hot-dishes-tab">
						<div class="products">

							<c:forEach items="${hotDishes}" var="hotDish">

								<div class="dish" id="${hotDish.id}">
									<div class="dish-img">
										<img src="${hotDish.picture}" alt="">
									</div>

									<div class="dish-title">
										<p>
											<c:out value="${hotDish.name}" />
										</p>
									</div>

									<div class="dish-ingred">
										<c:forEach items="${hotDish.ingredients}" var="ingredient">
											<span><c:out value="${ingredient}, " /></span>
										</c:forEach>
									</div>

									<div class="dish-amount">
										<p>
											<c:out value="${hotDish.amount}" />
										</p>
									</div>

									<div class="dish-price">
										<p>
											<c:out value="${hotDish.price} р." />
										</p>
									</div>

					
										<input type="hidden" name="command" class="command" value="add_dish_to_basket">
										<input type="hidden" name="dishId" class="dishId" value="${hotDish.id}" >
										<input type="hidden" name="dishCategory" class="dishCategory" value="hotDishes">


										<div class="amount">
											<button class="minus btn btn-danger" type="button">-</button>
											<input type="text" name="count" class="count" value="1" disabled>
											<button class="plus btn btn-success" type="button">+</button>
										</div>

										<button type="submit" class="addToBasket addButton"
											>
											${add_to_basket}
										</button>

									
									<!--/dish-buttons -->
								</div>
								<!--/dish -->
							</c:forEach>
						</div>
					</div>

					<div class="tab-pane fade" id="nav-salads" role="tabpanel"
						aria-labelledby="nav-salads-tab">
						<div class="products">

							<c:forEach items="${salads}" var="salad">

								<div class="dish" id="${salad.id}">
									<div class="dish-img">
										<img src="${salad.picture}" alt="">
									</div>

									<div class="dish-title">
										<p>
											<c:out value="${salad.name}" />
										</p>
									</div>

									<div class="dish-ingred">
										<c:forEach items="${salad.ingredients}" var="ingredient">
											<span><c:out value="${ingredient}, " /></span>
										</c:forEach>
									</div>

									<div class="dish-amount">
										<p>
											<c:out value="${salad.amount}" />
										</p>
									</div>

									<div class="dish-price">
										<p>
											<c:out value="${salad.price} р." />
										</p>
									</div>

							
																	<input type="hidden" name="command" class="command" value="add_dish_to_basket">
										<input type="hidden" name="dishId" class="dishId" value="${salad.id}">
										<input type="hidden" name="dishCategory" class="dishCategory" value="salads">


										<div class="amount">
											<button class="minus btn btn-danger" type="button">-</button>
											<input type="text" name="count" class="count" value="1" disabled>
											<button class="plus btn btn-success" type="button">+</button>
										</div>

										<button type="submit" class="addToBasket addButton"
											>
											${add_to_basket} 
										</button>

									
									<!--/dish-buttons -->
								</div>
								<!--/dish -->
							</c:forEach>
						</div>
					</div>

					<div class="tab-pane fade" id="nav-pizza" role="tabpanel"
						aria-labelledby="nav-pizza-tab">
						<div class="products">

							<c:forEach items="${pizza}" var="pizza">

								<div class="dish" id="${pizza.id}">
									<div class="dish-img">
										<img src="${pizza.picture}" alt="">
									</div>

									<div class="dish-title">
										<p>
											<c:out value="${pizza.name}" />
										</p>
									</div>

									<div class="dish-ingred">
										<c:forEach items="${pizza.ingredients}" var="ingredient">
											<span><c:out value="${ingredient}, " /></span>
										</c:forEach>
									</div>

									<div class="dish-amount">
										<p>
											<c:out value="${pizza.amount}" />
										</p>
									</div>

									<div class="dish-price">
										<p>
											<c:out value="${pizza.price} р." />
										</p>
									</div>

							
															<input type="hidden" name="command" class="command" value="add_dish_to_basket">
										<input type="hidden" name="dishId" class="dishId" value="${pizza.id}">
										<input type="hidden" name="dishCategory" class="dishCategory" value="pizza">


										<div class="amount">
											<button class="minus btn btn-danger" type="button">-</button>
											<input type="text" name="count" class="count" value="1" disabled>
											<button class="plus btn btn-success" type="button">+</button>
										</div>

										<button type="submit" class="addToBasket addButton"
										>
											${add_to_basket}
										</button>

							
									<!--/dish-buttons -->
								</div>
								<!--/dish -->
							</c:forEach>
						</div>
					</div>

					<div class="tab-pane fade" id="nav-desserts" role="tabpanel"
						aria-labelledby="nav-desserts-tab">
						<div class="products">

							<c:forEach items="${desserts}" var="dessert">

								<div class="dish" id="${dessert.id}">
									<div class="dish-img">
										<img src="${dessert.picture}" alt=""max-width: 100%;>
									</div>

									<div class="dish-title">
										<p>
											<c:out value="${dessert.name}" />
										</p>
									</div>

									<div class="dish-ingred">
										<c:forEach items="${dessert.ingredients}" var="ingredient">
											<span><c:out value="${ingredient}, " /></span>
										</c:forEach>
									</div>

									<div class="dish-amount">
										<p>
											<c:out value="${dessert.amount}" />
										</p>
									</div>

									<div class="dish-price">
										<p>
											<c:out value="${dessert.price} р." />
										</p>
									</div>

								<input type="hidden" name="command" class="command" value="add_dish_to_basket">
										<input type="hidden" name="dishId" class="dishId" value="${dessert.id}">
										<input type="hidden" name="dishCategory" class="dishCategory" value="desserts">


										<div class="amount">
											<button class="minus btn btn-danger" type="button">-</button>
											<input type="text" name="count" class="count" value="1" disabled>
											<button class="plus btn btn-success" type="button">+</button>
										</div>

										<button type="submit" class="addToBasket addButton "
									>
											${add_to_basket} 
										</button>

									<!--/dish-buttons -->
								</div>
								<!--/dish -->
							</c:forEach>
						</div>
					</div>

					<div class="tab-pane fade" id="nav-drinks" role="tabpanel"
						aria-labelledby="nav-drinks-tab">
						<div class="products">

							<c:forEach items="${drinks}" var="drink">

								<div class="dish" id="${drink.id}">
									<div class="dish-img">
										<img src="${drink.picture}" alt="">
									</div>

									<div class="dish-title">
										<p>
											<c:out value="${drink.name}" />
										</p>
									</div>

									<div class="dish-ingred">
										<c:forEach items="${drink.ingredients}" var="ingredient">
											<span><c:out value="${ingredient}, " /></span>
										</c:forEach>
									</div>

									<div class="dish-amount">
										<p>
											<c:out value="${drink.amount}" />
										</p>
									</div>

									<div class="dish-price">
										<p>
											<c:out value="${drink.price} р." />
										</p>
									</div>

								<input type="hidden" name="command" class="command" value="add_dish_to_basket">
										<input type="hidden" name="dishId" class="dishId" value="${drink.id}">
										<input type="hidden" name="dishCategory" class="dishCategory" value="drinks">


										<div class="amount">
											<button class="minus btn btn-danger" type="button">-</button>
											<input type="text" name="count" class="count" value="1" disabled>
											<button class="plus btn btn-success" type="button">+</button>
										</div>

										<button type="submit" class="addToBasket addButton "
											>
											${add_to_basket} 
										</button>

								
									<!--/dish-buttons -->
								</div>
								<!--/dish -->
							</c:forEach>
						</div>
					</div>

				</div>


			<c:if test="${not empty requestScope.allDishes}">
				<div id="bg_popup">
					<div id="popup">
					<form action="${pageContext.request.contextPath}/Controller"
						method="post">

						<input type="hidden" name="command" value="show_menu">
						 <button type="submit"
							>
							<img name="cross"
							src="http://itsnottrashdesigns.com/wp-content/uploads/2016/05/Remove_Item_icon.png"
							style="wigth: 20px; height: 20px;">
						</button>
					</form>
					<div class="products">

							<c:forEach items="${allDishes}" var="dish">

								<div class="dish" id="${dish.id}">
									<div class="dish-img">
										<img src="${dish.picture}" alt="">
									</div>

									<div class="dish-title">
										<p>
											<c:out value="${dish.name}" />
										</p>
									</div>

									<div class="dish-ingred">
										<c:forEach items="${dish.ingredients}" var="ingredient">
											<span><c:out value="${ingredient}, " /></span>
										</c:forEach>
									</div>

									<div class="dish-amount">
										<p>
											<c:out value="${dish.amount}" />
										</p>
									</div>

									<div class="dish-price">
										<p>
											<c:out value="${dish.price} р." />
										</p>
									</div>

								
										<input type="hidden" name="command" class="command" value="add_dish_to_basket">
										<input type="hidden" name="dishId" class="dishId" value="${dish.id}">
										<input type="hidden" name="dishCategory" class="dishCategory" >



										<div class="amount">
											<button class="minus btn btn-danger" type="button">-</button>
											<input type="text" name="count" class="count" value="1" disabled>
											<button class="plus btn btn-success" type="button">+</button>
										</div>

										<button type="submit" class="addToBasket addButton">
											${add_to_basket}</button>

									
									<!--/dish-buttons -->
								</div>
								<!--/dish -->
							</c:forEach>
						</div>
					</div>
				</div>

			</c:if>       
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
	<script charset="utf-8" src="${pageContext.request.contextPath}/js/myJS.js"></script>
	
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