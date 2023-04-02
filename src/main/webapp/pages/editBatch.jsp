<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
 <%@ page isELIgnored="false" %>
 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<%@include file="./base.jsp" %>
</head>
<body>

	<%@include file="./navbar.jsp" %>
	
	<div class="container">
		<h1 class="h1 text-center m-3">Edit Batch </h1>
		 
	
		<form:form method="post" modelAttribute="batch" class="form-horizontal">
			<form:input type="hidden" path="id" class="form-control" />
			<form:input type="hidden" path="courseId" class="form-control" />
			<div class="form-group">
				<label class="col-sm-2 control-label">Batch Name:</label>
				<div class="col-sm-10">
					<form:input path="name" class="form-control"/>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">Batch Timings:</label>
				<div class="col-sm-10">
					<form:select path="timings" class="form-control">
						<form:option value="">Select Timings</form:option>
						<form:option value="10:00 AM - 01:00 PM">10:00 AM - 01:00 PM</form:option>
						<form:option value="01:00 PM - 04:00 PM">01:00 PM - 04:00 PM</form:option>
						<form:option value="04:00 AM - 08:00 PM">04:00 AM - 08:00 PM</form:option>
					</form:select>	
				</div>
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" value="Modify Batch" class="btn btn-success"/>
					<a href="/admindashboard" class="btn btn-secondary ml-1" role="button" aria-disabled="true">Back</a>
				</div>
			</div>
		</form:form>
		
	</div>
</body>
</html>