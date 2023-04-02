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
	
	<h1 class="h1 text-center my-5">Manage Batches for : <u>${course.courseName}</u></h1>
    <div class="container">
    
    <!-- 	error msg -->
		<c:if test="${not empty sessionScope.message}">
		  <div class="alert alert-${sessionScope.message.type} container text-center" role="alert">
		    ${sessionScope.message.messageContent}
		  </div>
		  <c:remove var="sessionScope.message" scope="session"/>
		</c:if>
		
		
		<table class="table">
		  <thead class="thead-dark">
		    <tr>
		      <th scope="col">Batch Name</th>
		      <th scope="col">Timings</th>
		      <th scope="col">Action</th>
		    </tr>
		  </thead>
		  <tbody>
			  <c:forEach var="batch"  items="${batches}">
			    <tr>
			      <td scope="row"><b>${batch.name}</b></td>
			      <td>${batch.timings}</td>
			     
			      <td>
				      <a href="editBatch/${batch.id}"><i class="fa-solid fa-pencil" style="color: #2b63c5;"></i></a>          
				      &nbsp;
				      <a href="deleteBatch/${batch.id}"><i class="fas fa-trash" style="color: #ed0707;"></i></a>
			      </td>
			    </tr>
			    </c:forEach>
		   </tbody>
		</table>
    
    </div>
</body>
</html>