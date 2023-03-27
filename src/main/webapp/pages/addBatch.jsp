<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isELIgnored="false" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<%@include file="./base.jsp" %>
</head>
<body>

	<%@include file="./navbar.jsp" %>
	<h1 class="h1 text-center m-3">Create Batch for course: <span style="{font-style: italic;}">${requestScope.courseName}</span></h1>
	
	<!-- 	error msg -->
	<%-- <c:if test="${not empty sessionScope.message}">
	  <div class="alert alert-${sessionScope.message.type} container text-center" role="alert">
	    ${sessionScope.message.messageContent}
	  </div>
	  <c:remove var="sessionScope.message" scope="session"/>
	</c:if>
	 --%>
	
	<div class="container">
		<form:form method="post" modelAttribute="ns" class="form-horizontal">
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
					<input type="submit" value="Create Batch" class="btn btn-primary"/>
					<a href="/admindashboard" class="btn btn-secondary ml-1" role="button" aria-disabled="true">Back</a>
				</div>
			</div>
		</form:form>
		
	</div>
</body>
</html>