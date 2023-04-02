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
			<h1 class="h1">Add new album</h1><hr/>
			<p>
			<form:form method="post" modelAttribute="album">			  
			  <div class="form-group row">
			    <label for="albumName" class="col-sm-2 col-form-label"><b>Album Name</b></label>
			    <div class="col-sm-10">
			      <form:input path="albumName" id="albumName" class="form-control" /><span style="color:red">*</span>
			    </div>
			  </div>
			  
			  <div class="form-group row">
			    <label for="albumDescription" class="col-sm-2 col-form-label"><b>Description</b><span style="color:red"> *</span></label>
			    <div class="col-sm-10">
			      <form:textarea path="albumDescription" class="form-control"  rows="3"></form:textarea>
			    </div>
			  </div>
				  
			  
		      <div class="form-group row">
		           <div class="col-sm-10 offset-sm-2">
		               <form:button type="submit" class="btn btn-success">Submit</form:button>
		           </div>
		       </div>
		  	
			</form:form>
		</div>
</body>
</html>