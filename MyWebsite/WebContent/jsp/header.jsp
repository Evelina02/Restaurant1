<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<meta charset="utf-8">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/css/style.css" type="text/css" rel="stylesheet">

<title>Insert title here</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="button.name.ru" var="ru_button" />
<fmt:message bundle="${loc}" key="button.name.en" var="en_button" />
<fmt:message bundle="${loc}" key="text.name.restaurant" var="restaurant_name" />
<fmt:message bundle="${loc}" key="text.sign_in" var="sign_in_text" />
<fmt:message bundle="${loc}" key="text.sign_up" var="sign_up_text" />
<fmt:message bundle="${loc}" key="text.sign_out" var="sign_out_text" />
<fmt:message bundle="${loc}" key="text.menu" var="menu_text" />
<fmt:message bundle="${loc}" key="text.basket" var="basket_text" />
<fmt:message bundle="${loc}" key="text.profile" var="profile_text" />
<fmt:message bundle="${loc}" key="text.reviews" var="reviews_text" />
<fmt:message bundle="${loc}" key="text.orders" var="orders_text" />
<fmt:message bundle="${loc}" key="text.users" var="users_text" />


    <div class="container-fluid">
      <header>
     <div class="row no-gutters">
            <nav class="d-flex flex-row">
              <ul class="p-2">
<c:choose>
  <c:when test="${sessionScope.role == null}">
     <li><a href="${pageContext.request.contextPath}/index.jsp">${restaurant_name}</a></li>
	<li><a href="${pageContext.request.contextPath}/Controller?command=show_menu">${menu_text}</a></li>
    <li><a href="${pageContext.request.contextPath}/Controller?command=show_reviews">${reviews_text}</a></li>
    <li><a href="${pageContext.request.contextPath}/jsp/authorization.jsp">${sign_in_text}</a></li>
    <li><a href="${pageContext.request.contextPath}/jsp/registration.jsp">${sign_up_text}</a></li>
    
   </c:when>
   <c:otherwise>
	   <c:choose>
    	   <c:when test="${sessionScope.role =='CLIENT'}">
    	     <li><a href="${pageContext.request.contextPath}/index.jsp">${restaurant_name}</a></li>
           	 <li><a href="${pageContext.request.contextPath}/Controller?command=show_menu">${menu_text}</a></li>
             <li><a href="${pageContext.request.contextPath}/Controller?command=show_basket">${basket_text}</a></li>
             <li><a href="${pageContext.request.contextPath}/jsp/profileUser.jsp">${profile_text}</a></li>
          
           </c:when>
           <c:when test="${sessionScope.role =='ADMIN'}">
             <li><a href="${pageContext.request.contextPath}/index.jsp">${restaurant_name}</a></li>
             <li><a href="${pageContext.request.contextPath}/jsp/profileUser.jsp">${profile_text}</a></li>
             <li><a href="${pageContext.request.contextPath}/Сontroller?command=show_orders_in_process">${orders_text}</a></li>
             <li><a href="${pageContext.request.contextPath}/Controller?command=show_all_users">${users_text}</a></li>
             <li><a href="${pageContext.request.contextPath}/Controller?command=show_menu">${menu_text}</a></li>
           
           </c:when>
       </c:choose>
       
       	 <li><a href="${pageContext.request.contextPath}/Controller?command=show_reviews">${reviews_text}</a></li>
         <li><a href="${pageContext.request.contextPath}/Controller?command=sign_out">${sign_out_text}</a></li>
   
   </c:otherwise>
        </c:choose>
        </ul>
                    <form id="btn" action="${pageContext.request.contextPath}/Controller" method="get">
                        
                        <input type="hidden" name="command" value="change_language">
                        <button class="btn btn-light" type="submit" name="language"
                                value="en" >${en_button}
                        </button>
                        <button class="btn btn-light" type="submit" name="language"
                                value="ru" >${ru_button}
                        </button>
                   
                    </form>
            </nav>
         </div>
       
      </header>
    </div>
   
   
   
   
   

   
   
   
   
   
                
</body>
</html>