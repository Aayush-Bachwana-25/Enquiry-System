<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="./base.jsp" %>

</head>
<body>
	<%@include file="./navbar.jsp" %>
	
	<div class="container">
		<h1 class="h1">Add videos</h1>
		
		
		<%@include file="./message.jsp" %>
	
		
		<form:form method="post" modelAttribute="link">
			<div class="form-group row">
				    <label for="courseId" class="col-sm-2 col-form-label"><b>Course Name</b><span style="color:red"> *</span></label>
				    <div class="col-sm-10">
				      		<form:select path="courseId" class="form-control" required="true">
						        <form:option value="0">Select Course</form:option>
						        <c:forEach items="${courses}" var="course">
						            <form:option value="${course.courseId}">${course.courseName}</form:option>
						        </c:forEach>
						    </form:select>		      
				    </div>
			   </div> 
			   
			   <div class="form-group row">
				    <label for="url" class="col-sm-2 col-form-label"><b>Youtube Link</b><span style="color:red"> *</span></label>
				    <div class="col-sm-10">
				      <form:input path="url" id="url" class="form-control" placeholder="Enter URL here"  required="true"/>
				    </div>
			   </div>
			   
			    <div style="text-align: center;">
				 	  <form:button type="submit" class="btn btn-primary">Add Video</form:button>
				 	  <a href='<c:url value="/admindashboard"></c:url>' class="btn btn-secondary ml-1" role="button" aria-disabled="true">Cancel</a>
			 	</div>
			
		</form:form>
	</div>

</body>
</html>