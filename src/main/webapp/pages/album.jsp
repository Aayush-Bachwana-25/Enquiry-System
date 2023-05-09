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
	
	 <div class="container my-5">
    	
    	<%@include file="./message.jsp" %>
	
		
		<c:if test="${sessionScope.admin eq true}">
			<a class="nav-link text-white float-right my-3" href='<c:url value="/admin/addImageToAlbum/${album.albumId}"/>' style="background-color: #1a7f42;">Add new image</a>
		</c:if>
		<h1 class="h1">Album: ${album.albumName}</h1>
		<hr/>
		<div class="container d-flex flex-wrap">
			<c:forEach  var="image" items="${images}" >
				
				<div class="card" style="width: 18rem;  margin: 20px;">
				  <img alt="Image" src='<c:url value="/resources/images/${image}"></c:url>'  class="card-img-top" style="width=300px; height: 200px;">
				  <c:if test="${sessionScope.admin eq true}">
					  <div class="card-body d-flex justify-center align-iems center">
					 	  <a href='<c:url value="deleteImage/${album.albumId}/${image}"></c:url>' class="btn btn-danger m-1">Delete image</a>
					 	  <a href='<c:url value="setImageAsCover/${album.albumId}/${image}"></c:url>' class="btn btn-success m-1">Set as cover</a> 
					   </div>
				   </c:if>
				</div>
			</c:forEach>
    	</div>
    	
    	
    </div>

</body>
</html>