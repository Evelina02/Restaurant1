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


<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />



<style>
body {
	
}

.panel {
	box-shadow: 0 2px 0 rgba(0, 0, 0, 0.075);
	border-radius: 0;
	border: 0;
	margin-bottom: 15px;
}

.panel .panel-footer, .panel>:last-child {
	border-bottom-left-radius: 0;
	border-bottom-right-radius: 0;
}

.panel .panel-heading, .panel>:first-child {
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}

.panel-body {
	padding: 25px 20px;
}

.media-block .media-left {
	display: block;
	float: left
}

.media-block .media-right {
	float: right
}

.media-block .media-body {
	display: block;
	overflow: hidden;
	width: auto;
}

.middle .media-left, .middle .media-right, .middle .media-body {
	vertical-align: middle
}

.thumbnail {
	border-radius: 0;
	border-color: #e9e9e9
}

.tag.tag-sm, .btn-group-sm>.tag {
	padding: 5px 10px;
}

.tag:not
(
.label
)
{
background-color:#fff;
padding:6px 12px;
border-radius:2px;
border:1px
solid#cdd6e1;
font-size:12px;
line-height:1
.42857;
vertical-align:
middle;
-webkit-transition:
all
.15s;
transition:all .15s;
}
.text-muted, a.text-muted:hover, a.text-muted:focus {
	color: #acacac;
}

.text-sm {
	font-size: 0.9em;
}

.text-5x, .text-4x, .text-5x, .text-2x, .text-lg, .text-sm, .text-xs {
	line-height: 1.25;
}

.btn-trans {
	background-color: transparent;
	border-color: transparent;
	color: #929292;
}

.btn-icon {
	padding-left: 9px;
	padding-right: 9px;
}

.btn-sm, .btn-group-sm>.btn, .btn-icon.btn-sm {
	padding: 5px 10px !important;
}

.mar-top {
	margin-top: 15px;
}

.bDel {
	position: absolute;
	right: 40px;
	topi: 1px;
}

.login-text{
font-size: 20px;
margin-bottom: 0px;
}

.media-block{
/*background:#ffffe0;*/
}
</style>

<title>${reviews_text}</title>
</head>
<body style="background-color: #ffffe0;">
	<c:import url="header.jsp" />

	<fmt:setLocale value="${sessionScope.local}" />
	<fmt:setBundle basename="resources.localization.local" var="loc" />
	<fmt:message bundle="${loc}" key="text.reviews" var="reviews_text" />
	<fmt:message bundle="${loc}" key="review_added" var="review_added" />
	<fmt:message bundle="${loc}" key="add_review_error"
		var="add_review_error" />
	<fmt:message bundle="${loc}" key="review_deleted" var="review_deleted" />
	<fmt:message bundle="${loc}" key="add_button" var="add_button" />
	<fmt:message bundle="${loc}" key="delete_button" var="delete_button" />
	<fmt:message bundle="${loc}" key="add_your_review" var="add_your_review" />
	



	<c:if test="${not empty requestScope.addReviewSuccess}">
		<div class="alert alert-success" role="alert">
			<strong><c:out value="${review_added}" /></strong>
		</div>
	</c:if>
	
	<c:if test="${not empty requestScope.addReviewError}">
		<div class="alert alert-danger" role="alert">
			<strong><c:out value="${add_review_error}" /></strong>
		</div>
	</c:if>

	<c:if test="${not empty requestScope.reviewDeleted}">
		<div class="alert alert-success" role="alert">
			<strong><c:out value="${review_deleted}" /></strong>
		</div>
	</c:if>
	
	
	<section class="container">
		<div class="row">

			<div class="col-md-12">
				<c:if test="${sessionScope.role =='CLIENT'}">

					<div class="panel">
						<div class="panel-body">

							<form action="${pageContext.request.contextPath}/Controller"
								method="post">
								<input type="hidden" name="command" value="add_review">

								<textarea class="form-control" rows="2" name="comment"
									placeholder="${add_your_review}"></textarea>
								<div class="mar-top clearfix">
									<button class="btn btn-sm btn-primary pull-right" type="submit">
										<i class="fa fa-pencil fa-fw"></i> ${add_button}
									</button>

								</div>
							</form>
						</div>
					</div>
				</c:if>
				<div class="panel">
					<div class="panel-body">

						<!--============================-->
						<c:forEach items="${requestScope.allReviews}" var="review">
							<form action="${pageContext.request.contextPath}/Controller"
								method="post">
								<input type="hidden" name="command" value="delete_review">
								<input type="hidden" name="review_id" value="${review.id}">
								<div class="media-block">
									<div class="media-body">
										<p class="login-text text-semibold media-heading box-inline">
											${review.userLogin}
											<c:if test="${sessionScope.role =='ADMIN'}">
												<button class="bDel btn-primary" id="del">${delete_button}</button>
											</c:if>
										</p>
										<p class="text-muted text-sm">${review.time}</p>
									</div>
									<p>${review.body}</p>
									<hr>
								</div>
							</form>
						</c:forEach>
						<!--===================================================-->

					</div>
				</div>
			</div>

		</div>
		<!-- /.row -->
	</section>
	<!-- /.container -->

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