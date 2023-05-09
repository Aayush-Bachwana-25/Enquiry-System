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
		
		<div class="container my-5">
			<h1 class="h1">Admin Login</h1><hr/>
			
			<%@include file="./message.jsp" %>
	
			<form:form method="post" modelAttribute="admin">
			  <div class="form-group">
			    <form:label path="username">Email address <span style="color: red"> *</span></form:label>
			    <form:input path="username" type="email" class="form-control" aria-describedby="emailHelp" placeholder="Enter email" required="true"/>
			    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
			  </div>
			  <div class="form-group">
			    <form:label path="password">Password <span style="color: red"> *</span></form:label>
			    <form:input path="password" type="password" class="form-control" placeholder="Password" required="true"/>
			  </div>
			  
			  <form:button type="submit" class="btn btn-primary">Submit</form:button>
			</form:form>
		</div>
</body>
</html>