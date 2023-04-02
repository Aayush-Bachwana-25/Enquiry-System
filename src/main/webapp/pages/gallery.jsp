<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<%@include file="./base.jsp" %>
</head>
<body>
	<%@include file="./navbar.jsp" %>
	
	
	<h1 class="h1">Albums</h1>
	
	<!-- 	error msg if no videos are available to display -->
		<c:if test="${not empty sessionScope.message}">
		  <div class="alert alert-${sessionScope.message.type} container text-center" role="alert">
		    ${sessionScope.message.messageContent}
		  </div>
		  <c:remove var="sessionScope.message" scope="session"/>
		</c:if>
		
	<c:forEach  var="album" items="${albums}" >
		<div class="container d-inline-flex">
			<div class="card" style="width: 18rem;">
				  <img src="..." class="card-img-top" alt="...">
				  <div class="card-body">
				    <h5 class="card-title">Card title</h5>
				    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
				    <a href="#" class="btn btn-success">Go somewhere</a>
				  </div>
			</div>
		</div>
	</c:forEach>
	

</body>
</html>