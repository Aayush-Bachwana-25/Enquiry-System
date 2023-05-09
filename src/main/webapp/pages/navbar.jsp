<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  <a class="navbar-brand" href="#"><b>ComputerSeekho</b></a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <div class="collapse navbar-collapse" id="navbarNavDropdown">
		  <ul class="navbar-nav ml-auto">
		    <li class="nav-item active">
		      <a class="nav-link" href='<c:url value="/"></c:url>'>Home <span class="sr-only">(current)</span></a>
		    </li>
		    
		    <li class="nav-item ml-2">
		      <a class="nav-link" href='<c:url value="/aboutus"></c:url>'>About us</a>
		    </li>
		    
		    <li class="nav-item dropdown  ml-2">
		      <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		        Courses
		      </a>
		      <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
		        <a class="dropdown-item" href='<c:url value="/getPastCourses"></c:url>'>Past Courses</a>
		        <a class="dropdown-item" href='<c:url value="/getUpcomingCourses"></c:url>'>Upcoming Courses</a>
		        <a class="dropdown-item" href='<c:url value="/getAllCourses"></c:url>'>All courses</a>
		      </div>
		    </li>
		    
		    <li class="nav-item ml-2">
		      <a class="nav-link" href='<c:url value="/contactus"></c:url>'>Contact Us</a>
		    </li>
		    
		    <li class="nav-item ml-2">
		      <a class="nav-link" href='<c:url value="/viewGallery"></c:url>'>Gallery</a>
		    </li>
		    
		    <c:choose>
			    <c:when test="${sessionScope.admin eq true }">
			    	<li class="nav-item ml-2">
			      		<a class="nav-link text-white" href='<c:url value="/admindashboard"></c:url>' style="background-color: #1a7f42; margin-left: 30px;">Dashboard</a>
			    	</li>
			    	
			    	<li class="nav-item ml-2">
			      		<a class="nav-link text-white" href='<c:url value="/adminlogin"></c:url>' style="background-color: #cc0000;">Logout</a>
			    	</li>
			    </c:when>
			    <c:otherwise>
			    <li class="nav-item ml-2">
			      <a class="nav-link text-white" href='<c:url value="/adminlogin"></c:url>' style="background-color: #1a7f42;">Admin Panel</a>
			    </li>
			    </c:otherwise>
		    </c:choose>
		  </ul>
		</div>

</nav>


