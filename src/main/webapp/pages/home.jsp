<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    
    <%@include file="./base.jsp" %>

</head>
<body>
    
    <%@include file="./navbar.jsp" %>
    
    <div class="container">

		<table class="table">
		  <thead class="thead-dark">
		    <tr>
		      <th scope="col">Course Name</th>
		      <th scope="col">Age Group</th>
		      <th scope="col">No. of hrs</th>
		      <th scope="col">Fees</th>
		    </tr>
		  </thead>
		  <tbody>
			  <c:forEach var="course"  items="${courses}">
			    <tr>
			      <td scope="row"><a href="#">${course.courseName}</a></td>
			      <td>${course.ageGroup}</td>
			      <td>${course.noOfHrs}</td>
			      <td><b>&#x20B9;</b>&nbsp;${course.fees}</td>
			    </tr>
			    </c:forEach>
		   </tbody>
		</table>
    
    </div>
</body>
</html>