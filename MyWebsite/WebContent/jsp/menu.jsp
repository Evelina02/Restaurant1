<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="restaurant" uri="/WEB-INF/tld/taglib.tld" %>
    
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/css/menu.css" type="text/css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/style.css" type="text/css" rel="stylesheet">



<title>${menu_text}</title>


<style type="text/css">
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
  padding: 25px 15px;
 height: 700px;
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
    
.dish-ingred {
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
 
  .dish-amount span{
	font-size: 10px;
}       
        
.dish-price{
    color: rgb(217, 0, 5);
    font-size: 20px;
    margin-right: 20px;
}

.amount {
	width:200px;
	margin:100px auto;
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

    </style>



</head>
<body>
<c:import url="header.jsp"/>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="text.menu" var="menu_text" />
<fmt:message bundle="${loc}" key="dish_added" var="dish_added" />


      	<c:if test="${not empty requestScope.dishAdded}">
			<div class="alert alert-success" role="alert">
	        	<strong><c:out value="${dish_added}"/></strong>
        	</div>
    	</c:if>



<div class="container">
	<nav>
		<div class="nav nav-tabs nav-justified" id="nav-tab" role="tablist">
			<a class="nav-item nav-link active" id="nav-snacks-tab" data-toggle="tab" href="#nav-snacks" 
				role="tab" aria-controls="nav-snacks" aria-selected="true">Закуски</a>
			
			<a class="nav-item nav-link" id="nav-hot-dishes-tab" data-toggle="tab" href="#nav-hot-dishes" 
				role="tab" aria-controls="nav-hot-dishes" aria-selected="false">Горячие блюда</a>
				
			<a class="nav-item nav-link" id="nav-salads-tab" data-toggle="tab" href="#nav-salads" 
				role="tab" aria-controls="nav-salads" aria-selected="false">Салаты</a>
			
			<a class="nav-item nav-link" id="nav-pizza-tab" data-toggle="tab" href="#nav-pizza" 
				role="tab" aria-controls="nav-pizza" aria-selected="false">Пицца</a>
				
			<a class="nav-item nav-link" id="nav-desserts-tab" data-toggle="tab" href="#nav-desserts" 
				role="tab" aria-controls="nav-desserts" aria-selected="false">Десерты</a>
			
			<a class="nav-item nav-link" id="nav-drinks-tab" data-toggle="tab" href="#nav-drinks" 
				role="tab" aria-controls="nav-drinks" aria-selected="false">Напитки</a>
					
		</div>
	</nav>		
			
	<div class="tab-content" id="nav-tabContent">
	
		<div class="tab-pane fade show active" id="nav-snacks" role="tabpanel" aria-labelledby="nav-snacks-tab">
        	<div class="products">
                	
                	<c:forEach items="${snacks}" var="snack">
                	
              			 <div class="dish" id="${snack.id}"> 
              				 <div class="dish-img">
               					<img src="${snack.picture}" alt="">
              				 </div>
                              
                             <div class="dish-title">
              					 <p><c:out value="${snack.name}"/></p>
                               </div>                					 
              				
               				<div class="dish-ingred">
               					<c:forEach items="${snack.ingredients}" var="ingredient">
									<span><c:out value="${ingredient}, "/></span>
								</c:forEach>
               				</div>
               				
               				 <div class="dish-amount">
              					 <p><c:out value="${snack.amount}"/></p>
                               </div>
               				
             				<div class="dish-price">
             					<p><c:out value="${snack.price} р."/></p>
                             </div>
                             
                             <form action="${pageContext.request.contextPath}/Controller" method="post">
                             
                             	<input type="hidden" name="command" value="add_dish_to_basket">
                            	
                               	<input type="hidden" name="dishId" value="${snack.id}">
                               	<input type="hidden" name="dishCategory" value="snacks">


	                            <div class="amount">
	                            	<button class="minus btn btn-danger" type="button">-</button>
	                             	<input type="text" name="count" value="1">
	                             	<button class="plus btn btn-success" type="button">+</button>
	                            </div>
	                         		
	                         	<button type="submit" class="addToBasket btn btn-primary" style="border: 1px dashed rgb(66, 133, 244);">Добавить в корзину 
	                         		<img class="img-basket" src="https://images.kz.prom.st/83557882_w640_h640_tv-magazin.jpg" width="20" height="20" alt="">
                                 </button>
	                         		
	                  		</form><!--/dish-buttons -->
                	</div><!--/dish -->
                	</c:forEach>
              </div>
          </div>
          
        <div class="tab-pane fade" id="nav-hot-dishes" role="tabpanel" aria-labelledby="nav-hot-dishes-tab">
        	<div class="products">
                	
                	<c:forEach items="${hotDishes}" var="hotDish">
                	
              			 <div class="dish" id="${hotDish.id}"> 
              				 <div class="dish-img">
               					<img src="${hotDish.picture}" alt="">
              				 </div>
                              
                             <div class="dish-title">
              					 <p><c:out value="${hotDish.name}"/></p>
                               </div>                					 
              				
               				<div class="dish-ingred">
               					<c:forEach items="${hotDish.ingredients}" var="ingredient">
									<span><c:out value="${ingredient}, "/></span>
								</c:forEach>
               				</div>
               				
               				 <div class="dish-amount">
              					 <p><c:out value="${hotDish.amount}"/></p>
                               </div>
               				
             				<div class="dish-price">
             					<p><c:out value="${hotDish.price} р."/></p>
                             </div>
                             
                           <form action="${pageContext.request.contextPath}/Controller" method="post">
                             
                             	<input type="hidden" name="command" value="add_dish_to_basket">
                            	
                               	<input type="hidden" name="dishId" value="${hotDish.id}">
                               	<input type="hidden" name="dishCategory" value="hotDishes">


	                            <div class="amount">
	                            	<button class="minus btn btn-danger" type="button">-</button>
	                             	<input type="text" name="count" value="1">
	                             	<button class="plus btn btn-success" type="button">+</button>
	                            </div>
	                         		
	                         	<button type="submit" class="addToBasket btn btn-primary" style="border: 1px dashed rgb(66, 133, 244);">Добавить в корзину 
	                         		<img class="img-basket" src="https://images.kz.prom.st/83557882_w640_h640_tv-magazin.jpg" width="20" height="20" alt="">
                                 </button>
	                         		
	                  		</form><!--/dish-buttons -->
                	</div><!--/dish -->
                	</c:forEach>
              </div>
          </div>  
          
		<div class="tab-pane fade" id="nav-salads" role="tabpanel" aria-labelledby="nav-salads-tab">
        	<div class="products">
                	
                	<c:forEach items="${salads}" var="salad">
                	
              			 <div class="dish" id="${salad.id}"> 
              				 <div class="dish-img">
               					<img src="${salad.picture}" alt="">
              				 </div>
                              
                             <div class="dish-title">
              					 <p><c:out value="${salad.name}"/></p>
                               </div>                					 
              				
               				<div class="dish-ingred">
               					<c:forEach items="${salad.ingredients}" var="ingredient">
									<span><c:out value="${ingredient}, "/></span>
								</c:forEach>
               				</div>
               				
               				 <div class="dish-amount">
              					 <p><c:out value="${salad.amount}"/></p>
                             </div>
               				
             				 <div class="dish-price">
             					<p><c:out value="${salad.price} р."/></p>
                             </div>
                             
                             <form action="${pageContext.request.contextPath}/Controller" method="post">
                             
                             	<input type="hidden" name="command" value="add_dish_to_basket">
                            	
                               	<input type="hidden" name="dishId" value="${salad.id}">
                               	<input type="hidden" name="dishCategory" value="salads">


	                            <div class="amount">
	                            	<button class="minus btn btn-danger" type="button">-</button>
	                             	<input type="text" name="count" value="1">
	                             	<button class="plus btn btn-success" type="button">+</button>
	                            </div>
	                         		
	                         	<button type="submit" class="addToBasket btn btn-primary" style="border: 1px dashed rgb(66, 133, 244);">Добавить в корзину 
	                         		<img class="img-basket" src="https://images.kz.prom.st/83557882_w640_h640_tv-magazin.jpg" width="20" height="20" alt="">
                                 </button>
                                                    		
	                  		</form><!--/dish-buttons -->
                	</div><!--/dish -->
                	</c:forEach>
              </div>
          </div>

		<div class="tab-pane fade" id="nav-pizza" role="tabpanel" aria-labelledby="nav-pizza-tab">
        	<div class="products">
                	
                	<c:forEach items="${pizza}" var="pizza">
                	
              			 <div class="dish" id="${pizza.id}"> 
              				 <div class="dish-img">
               					<img src="${pizza.picture}" alt="">
              				 </div>
                              
                             <div class="dish-title">
              					 <p><c:out value="${pizza.name}"/></p>
                               </div>                					 
              				
               				<div class="dish-ingred">
               					<c:forEach items="${pizza.ingredients}" var="ingredient">
									<span><c:out value="${ingredient}, "/></span>
								</c:forEach>
               				</div>
               				
               				 <div class="dish-amount">
              					 <p><c:out value="${pizza.amount}"/></p>
                               </div>
               				
             				<div class="dish-price">
             					<p><c:out value="${pizza.price} р."/></p>
                             </div>
                             
                            <form action="${pageContext.request.contextPath}/Controller" method="post">
                             
                             	<input type="hidden" name="command" value="add_dish_to_basket">
                            	
                               	<input type="hidden" name="dishId" value="${pizza.id}">
                               	<input type="hidden" name="dishCategory" value="pizza">


	                            <div class="amount">
	                            	<button class="minus btn btn-danger" type="button">-</button>
	                             	<input type="text" name="count" value="1">
	                             	<button class="plus btn btn-success" type="button">+</button>
	                            </div>
	                         		
	                         	<button type="submit" class="addToBasket btn btn-primary" style="border: 1px dashed rgb(66, 133, 244);">Добавить в корзину 
	                         		<img class="img-basket" src="https://images.kz.prom.st/83557882_w640_h640_tv-magazin.jpg" width="20" height="20" alt="">
                                 </button>
	                         		
	                  		</form><!--/dish-buttons -->
                	</div><!--/dish -->
                	</c:forEach>
              </div>
          </div>

		<div class="tab-pane fade" id="nav-desserts" role="tabpanel" aria-labelledby="nav-desserts-tab">        	
				<div class="products">
                	
                	<c:forEach items="${desserts}" var="dessert">
                	
              			 <div class="dish" id="${dessert.id}"> 
              				 <div class="dish-img">
               					<img src="${dessert.picture}" alt=""  max-width: 100%;>
              				 </div>
                              
                             <div class="dish-title">
              					 <p><c:out value="${dessert.name}"/></p>
                               </div>  
                                          				
               				<div class="dish-ingred">
               					<c:forEach items="${dessert.ingredients}" var="ingredient">
												<span><c:out value="${ingredient}, "/></span>
								</c:forEach>
               				</div>
               				
               				 <div class="dish-amount">
              					 <p><c:out value="${dessert.amount}"/></p>
                               </div>
               				
             				<div class="dish-price">
             					<p><c:out value="${dessert.price} р."/></p>
                             </div>
                             
                             <form action="${pageContext.request.contextPath}/Controller" method="post">
                             
                             	<input type="hidden" name="command" value="add_dish_to_basket">
                            	
                               	<input type="hidden" name="dishId" value="${dessert.id}">
                               	<input type="hidden" name="dishCategory" value="desserts">


	                            <div class="amount">
	                            	<button class="minus btn btn-danger" type="button">-</button>
	                             	<input type="text" name="count" value="1">
	                             	<button class="plus btn btn-success" type="button">+</button>
	                            </div>
	                         		
	                         	<button type="submit" class="addToBasket btn btn-primary" style="border: 1px dashed rgb(66, 133, 244);">Добавить в корзину 
	                         		<img class="img-basket" src="https://images.kz.prom.st/83557882_w640_h640_tv-magazin.jpg" width="20" height="20" alt="">
                                 </button>
	                         		
	                  		</form><!--/dish-buttons -->
                	</div><!--/dish -->
                	</c:forEach>
              </div>
          </div>

		<div class="tab-pane fade" id="nav-drinks" role="tabpanel" aria-labelledby="nav-drinks-tab">
        	<div class="products">
                	
                	<c:forEach items="${drinks}" var="drink">
                	
              			 <div class="dish" id="${drink.id}"> 
              				 <div class="dish-img">
               					<img src="${drink.picture}" alt="">
              				 </div>
                              
                             <div class="dish-title">
              					 <p><c:out value="${drink.name}"/></p>
                               </div>                					 
              				
               				<div class="dish-ingred">
               					<c:forEach items="${drink.ingredients}" var="ingredient">
									<span><c:out value="${ingredient}, "/></span>
								</c:forEach>
               				</div>
               				
               				 <div class="dish-amount">
              					 <p><c:out value="${drink.amount}"/></p>
                               </div>
               				
             				<div class="dish-price">
             					<p><c:out value="${drink.price} р."/></p>
                             </div>
                             
                            <form action="${pageContext.request.contextPath}/Controller" method="post">
                             
                             	<input type="hidden" name="command" value="add_dish_to_basket">
                            	
                               	<input type="hidden" name="dishId" value="${drink.id}">
                               	<input type="hidden" name="dishCategory" value="drinks">


	                            <div class="amount">
	                            	<button class="minus btn btn-danger" type="button">-</button>
	                             	<input type="text" name="count" value="1">
	                             	<button class="plus btn btn-success" type="button">+</button>
	                            </div>
	                         		
	                         	<button type="submit" class="addToBasket btn btn-primary" style="border: 1px dashed rgb(66, 133, 244);">Добавить в корзину 
	                         		<img class="img-basket" src="https://images.kz.prom.st/83557882_w640_h640_tv-magazin.jpg" width="20" height="20" alt="">
                                 </button>
	                         		
	                  		</form><!--/dish-buttons -->
                	</div><!--/dish -->
                	</c:forEach>
              </div>
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