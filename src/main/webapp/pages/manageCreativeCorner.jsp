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
		<h1 class="h1 text-center mt-4" style="display: inline-block;">Manage Creative Corner</h1>
		<a class="nav-link text-white float-right my-4" href="addvideos" style="background-color: #1a7f42; display: inline-block;">Add new videos</a>
		
		<hr/>
		
		<%@include file="./message.jsp" %>
	
		<table class="table table-bordered my-5" style="text-align: center;">
		  <thead class="thead-dark">
		    <tr>
		      <th scope="col">S.No.</th>
		      <th scope="col">Youtube Link</th>
		      <th scope="col">Action</th>		      
		    </tr>
		  </thead>
		  <tbody>
			
			<c:forEach var="course" items="${courses}" varStatus="courseStatus">
			    <tr style="background-color: white; color: black;">
			        <th colspan="3">Course Name : ${course.courseName}</th>
			    </tr>
			    <c:set var="rowNumber" value="${0}" />
			    <c:forEach var="link" items="${links}" varStatus="linkStatus">
			        <c:if test="${course.courseId eq link.courseId}">
			            <tr>
			                <td>${rowNumber + 1}</td>
			                <td align="left"><a href='<c:url value="${link.url}"></c:url>'>${link.url}</a></td>
			                <td><a href="deleteVideo/${link.linkId}"><i class="fas fa-trash" style="color: #ed0707;"></i></a></td>
			            </tr>
			            <c:set var="rowNumber" value="${rowNumber + 1}" />
			        </c:if>	
			    </c:forEach>
			</c:forEach>
			
			   	
		   </tbody>
		</table>
	</div>
	
</body>
</html>