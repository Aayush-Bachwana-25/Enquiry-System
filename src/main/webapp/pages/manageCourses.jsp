<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	
	 <%@include file="./base.jsp" %>
	 
	 <style>
	 	h1{
	 		display: inline-block;
	 		width: auto;
	 	}
	 	
	 	.btn btn-secondary{
	 		align-self: right;
	 	}
	 </style>
</head>
<body>
	<h1>Manage Courses</h1><hr/><p>
	
	<a href="admindashboard">
		<button type="button" class="btn btn-secondary">Dashboard</button>
	</a>
	
	<div class="tablediv">

		<table class="table">
		  <thead class="thead-dark">
		    <tr>
		      <th scope="col">Course Name</th>
		      <th scope="col">Age Group</th>
		      <th scope="col">No. of hrs</th>
		      <th scope="col">Action</th>
		    </tr>
		  </thead>
		  <tbody>
			  <c:forEach var="course"  items="${courses}">
			    <tr>
			      <td scope="row"><a href="#">${course.courseName}</a></td>
			      <td>${course.ageGroup}</td>
			      <td>${course.noOfHrs}</td>
			      <td>
				      <a href="editCourse/${course.courseId}"><i class="fa-solid fa-pencil" style="color: #2b63c5;"></i></a>          
				      &nbsp;
				      <a href="delete/${course.courseId}"><i class="fas fa-trash" style="color: #ed0707;"></i></a>
			      </td>
			    </tr>
			    </c:forEach>
		   </tbody>
		</table>
    
    </div>
</body>
</html>