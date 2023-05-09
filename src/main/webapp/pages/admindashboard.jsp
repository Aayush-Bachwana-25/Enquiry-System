<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ page isELIgnored="false" %>
      
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="./base.jsp" %>

	<style>
		body{
			background-color: #f2f2f2;
		}
		.card{
			border: 1px solid grey;
			border-radius:5px;
		}
	</style>

</head>
<body>
	<%@include file="./navbar.jsp" %>
	
	<h1 class="h1 text-center my-5">Welcome back, Admin!</h1>
	
	<div class="container d-flex justify-content-center">

		<div class="card mx-3" style="width: 18rem;">
			  <img class="card-img-top" src='<c:url value="/resources/images/books.jpg"></c:url>' alt="Card image cap"  height="220px">
			  
			  <div class="card-body">
			    <h5 class="card-title">Courses</h5>
			  </div>
			  
			  <ul class="list-group list-group-flush">
			    <li class="list-group-item">
			    	<a href='<c:url value="/admin/addcourse"></c:url>' class="card-link">Add Courses</a>
		    	</li>
			    <li class="list-group-item">
			    	<a href='<c:url value="/admin/managecourses"></c:url>' class="card-link">Manage Courses</a>
		    	</li>
		    	<li class="list-group-item">
			    	<a href='<c:url value="/admin/managebatches"></c:url>' class="card-link">Manage Batches</a>
		    	</li>
			  </ul>
		</div>
		
		<div class="card mx-3" style="width: 18rem;">
			  <img class="card-img-top" src='<c:url value="/resources/images/question.jpg"></c:url>' alt="Card image cap" height=220px>
			  
			  <div class="card-body">
			    <h5 class="card-title">Actions</h5>
			  </div>
			  
			  <ul class="list-group list-group-flush">
			    <li class="list-group-item">
			    	<a href='<c:url value="/admin/viewqueries"></c:url>' class="card-link">View/Manage Queries</a>
		    	</li>
			    <li class="list-group-item">
			    	<a href='<c:url value="/admin/addStudent"></c:url>' class="card-link">Student Registration</a>
		    	</li>
		    	<li class="list-group-item">
			    	<a href='<c:url value="/resources/misc/annexure1.pdf"></c:url>' class="card-link">Annexure-I</a>
		    	</li>
			  </ul>
		</div>
		
		<div class="card mx-3" style="width: 18rem; ">
			  <img class="card-img-top" src='<c:url value="/resources/images/camera.jpg"></c:url>' alt="Card image cap" height="220px">
			  
			  <div class="card-body">
			    <h5 class="card-title">Gallery</h5>
			  </div>
			  
			  <ul class="list-group list-group-flush">
			    <li class="list-group-item">
			    	<a href='<c:url value="/admin/manageAlbums"></c:url>' class="card-link">Albums</a>
		    	</li>
			    <li class="list-group-item">
			    	<a href='<c:url value="/admin/creative-corner"></c:url>' class="card-link">Creative Corner</a>
		    	</li>
		    	<li class="list-group-item">
			    	&nbsp;
		    	</li>
			  </ul>
		</div>
	</div>
	
</body>
</html>