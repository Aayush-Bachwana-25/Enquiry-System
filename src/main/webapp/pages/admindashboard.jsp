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

</head>
<body>
	<%@include file="./navbar.jsp" %>
	
	<h1 class="h1 text-center mb-5">Welcome,Admin</h1>
	
	<div class="container d-flex align-items-center justify-content-center">

		<div class="card mx-3" style="width: 18rem;">
			  <img class="card-img-top" src='<c:url value="/resources/images/courses.png"></c:url>' alt="Card image cap">
			  
			  <div class="card-body">
			    <h5 class="card-title">Courses</h5>
			  </div>
			  
			  <ul class="list-group list-group-flush">
			    <li class="list-group-item">
			    	<a href="addcourse" class="card-link">Add Courses</a>
		    	</li>
			    <li class="list-group-item">
			    	<a href="managecourses" class="card-link">Manage Courses</a>
		    	</li>
		    	<li class="list-group-item">
			    	<a href="managebatches" class="card-link">Manage Batches</a>
		    	</li>
			  </ul>
		</div>
		
		<div class="card mx-3" style="width: 18rem;">
			  <img class="card-img-top" src='<c:url value="/resources/images/courses.png"></c:url>' alt="Card image cap">
			  
			  <div class="card-body">
			    <h5 class="card-title">Actions</h5>
			  </div>
			  
			  <ul class="list-group list-group-flush">
			    <li class="list-group-item">
			    	<a href="viewqueries" class="card-link">View/Manage Queries</a>
		    	</li>
			    <li class="list-group-item">
			    	<a href="addStudent" class="card-link">Register Student</a>
		    	</li>
		    	<li class="list-group-item">
			    	<a href="#" class="card-link">Annexure-I</a>
		    	</li>
			  </ul>
		</div>
		
		<div class="card mx-3" style="width: 18rem;">
			  <img class="card-img-top" src='<c:url value="/resources/images/courses.png"></c:url>' alt="Card image cap">
			  
			  <div class="card-body">
			    <h5 class="card-title">Gallery</h5>
			  </div>
			  
			  <ul class="list-group list-group-flush">
			    <li class="list-group-item">
			    	<a href="manageAlbums" class="card-link">Manage Albums</a>
		    	</li>
			    <li class="list-group-item">
			    	<a href="creative-corner" class="card-link">Creative Corner</a>
		    	</li>
		    	<li class="list-group-item">
			    	<a href="#" class="card-link">Courses Thumbnails</a>
		    	</li>
			  </ul>
		</div>
	</div>
	<!-- 
	<div class="card">
		  <h2 class="card-header">QUERIES</h2>
		  <hr/>
		  <ul class="card-links">
		    <li><a href="addcourse">Manage Queries</a></li>
		    <li><a href="managecourses">Manage Courses</a></li>
		    <li><a href="managebatches">Manage Batches</a></li>
		  </ul>
	</div>
	
	<div class="card">
		  <h2 class="card-header">GALLERY</h2>
		  <hr/>
		  <ul class="card-links">
		    <li><a href="#">Add album</a></li>
		    <li><a href="#">Manage existing albums</a></li>
		    <li><a href="#">Manage Batches</a></li>
		  </ul>
	</div> -->
	
</body>
</html>