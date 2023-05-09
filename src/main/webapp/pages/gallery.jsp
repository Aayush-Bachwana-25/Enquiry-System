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
		<h1 class="h1 d-inline">Albums</h1>
		
		<c:if test="${sessionScope.admin eq true}">
			<a class="nav-link text-white float-right my-3" href='<c:url value="/admin/addAlbum"></c:url>' style="background-color: #1a7f42; display:inline-block;">Add new album</a>
		</c:if>
		
		<hr/>
		
		<%@include file="./message.jsp" %>
	
		<div class="container my-3">
	
		  <div class="row">
		    <c:forEach var="album" items="${albums}">
		      <div class="col-lg-4 mb-4">
		        <div class="card" style="height: 450px">
		          <img alt="Image" src='<c:url value="/resources/images/${album.coverImage}"></c:url>' class="card-img-top" height="200px">
		          <div class="card-body">
		            <h5 class="card-title">${album.albumName}</h5>
		            <p class="card-text" style="height: 70px;">${album.albumDescription}</p>
		           
		           	<c:choose>
		           		<c:when test="${sessionScope.admin eq true}">
			            	<a href='<c:url value="/admin/manageAlbumImages/${album.albumId}"></c:url>' class="btn btn-success">Open</a>
				            <a href='<c:url value="/admin/deleteAlbum/${album.albumId}"></c:url>' class="btn btn-danger">Delete</a>
		            	</c:when>
			            <c:otherwise>
				            <a href='<c:url value="viewAlbumImages/${album.albumId}"></c:url>' class="btn btn-success">Open</a>
			            </c:otherwise>
		            </c:choose>
		            
		            <p style="margin-top: 10px"><b>Valid till:</b> ${album.validityDate}</p>
		          </div>
		        </div>
		      </div>
		    </c:forEach>
		  </div>
		</div>
	
		
	</div>

</body>
</html>