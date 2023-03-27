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
    <c:set var="url" value="${pageContext.request.requestURL}" scope="request"/>

    
    <div class="container">
    	
    	<c:if test="${not empty sessionScope.message}">
			  <div class="alert alert-${sessionScope.message.type} container text-center" role="alert">
			    ${sessionScope.message.messageContent}
			  </div>
			  <c:remove var="message" scope="session"/>
		</c:if>

		<table class="table">
		  <thead class="thead-dark">
		    <tr>
		      <th scope="col">Course Name</th>
		      <th scope="col" colspan="2">Action</th>
		    </tr>
		  </thead>
		  <tbody>
			  <c:forEach var="course"  items="${courses}">
			    <tr>
			      <td scope="row">${course.courseName}</td>
			      <td><a href="addBatch/${course.courseId}/${course.courseName}">Add new batch</a></td>
			      <td><a href="manageBatchByCourse/${course.courseId}/${course.courseName}">View/Manage existing batches</a></td>
			    </tr>
			    </c:forEach>
		   </tbody>
		</table>
    
    </div>
</body>
</html>