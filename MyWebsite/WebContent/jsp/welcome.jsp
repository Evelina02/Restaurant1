<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="restaurant" uri="/WEB-INF/tld/taglib.tld" %>
	<meta charset="utf-8">
	
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">


<style type="text/css">

#welcome{
	
	height:800px;
	background-image: url(../img/fon.jpg);
	background-repeat: no-repeat;
	background-position: center center;
	background-size:100%; 
}
#btn{
 
	margin-right:10px;
	margin-left:auto;
	margin-top:20px;
}
#btn .btn-light:hover{
	color:#fff;
	background:#000;
}
.alert{
	text-align:  center;
}

</style>
<title>${restaurant_name}</title>
</head>

<body id="welcome">
<c:import url="header.jsp"/>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="resources.localization.local" var="loc" />
<fmt:message bundle="${loc}" key="register.success_message" var="register_success" />
<fmt:message bundle="${loc}" key="message.hello" var="hello" />
<fmt:message bundle="${loc}" key="message.goodbye" var="goodbye" />
<fmt:message bundle="${loc}" key="welcome.text" var="welcome_text" />
<fmt:message bundle="${loc}" key="text.name.restaurant" var="restaurant_name" />



      	<c:if test="${not empty requestScope.helloMessage}">
			<div class="alert alert-success" role="alert">
	        	<strong><c:out value="${hello}"/></strong>
        	</div>
    	</c:if>
    	
    	<c:if test="${not empty requestScope.registerSuccess}">
			<div class="alert alert-success" role="alert">
	        	<strong><c:out value="${register_success}"/></strong>
        	</div>
    	</c:if>

    	<c:if test="${not empty requestScope.goodbyeMessage}">
			<div class="alert alert-success" role="alert">
	        	<strong><c:out value="${goodbye}, ${requestScope.login}!"/></strong>
        	</div>
    	</c:if>


	
        <div class="call-to row no-gutters" >
          <div class="col-12">
            <h1 style="font-family: 'Lobster', cursive;
            	letter-spacing: 6px;">
            	${welcome_text}</h1>
          </div>
        </div>



<restaurant:footer local="${sessionScope.local}"/>  
</body>
</html>