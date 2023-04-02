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
	
	 <div class="container">
    	
    	<c:if test="${not empty sessionScope.message}">
			  <div class="alert alert-${sessionScope.message.type} container text-center" role="alert">
			    ${sessionScope.message.messageContent}
			  </div>
			  <c:remove var="message" scope="session"/>
		</c:if>
		
		<a class="nav-link text-white float-right my-3" href='<c:url value="/addImageToAlbum/${albumId}"/>' style="background-color: #1a7f42;">Add new image</a>
		
		<hr/>
		<div class="container d-inline-flex">
			<c:forEach  var="image" items="${images}" >
				<img alt="Image" src='<c:url value="#"></c:url>'>
			</c:forEach>
    	</div>
    </div>

</body>
</html>