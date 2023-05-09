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
		<h1 class="h1 text-center m-3">Add New Course</h1>
		
		<%@include file="./message.jsp" %>
			
		
		<div class="container mb-5">
			<form:form method="post" modelAttribute="nc" enctype="multipart/form-data">
				  <div class="form-group row">
				    <label for="courseName" class="col-sm-2 col-form-label"><b>Course Name</b><span style="color: red"> *</span></label>
				    <div class="col-sm-10">
				      <form:input path="courseName" id="courseName" class="form-control"  required="true"/>
				    </div>
				  </div>
				
				  <div class="form-group row">
				    <label for="ageGroup" class="col-sm-2 col-form-label"><b>Age Group</b><span style="color: red"> *</span></label>
				    <div class="col-sm-10">
				      <form:select path="ageGroup" id="ageGroup" class="form-control" required="true">
				        <form:option value="">Select Group</form:option>
				        <form:option value="0-10">0-10 years</form:option>
				        <form:option value="10-20">10-20 years</form:option>
				        <form:option value="20-30">20-30 years</form:option>
				      </form:select>
				      
				    </div>
				  </div>
				
				  <div class="form-group row">
				    <label for="noOfHrs" class="col-sm-2 col-form-label"><b>No. of Hrs</b><span style="color: red"> *</span></label>
				    <div class="col-sm-10">
				      <form:input path="noOfHrs" type="number" id="noOfHrs" class="form-control" required="true"/>
				    </div>
				  </div>
				
				  <div class="form-group row">
				    <label for="startDate" class="col-sm-2 col-form-label"><b>Start Date</b><span style="color: red"> *</span></label>
				    <div class="col-sm-10">
				      <form:input path="startDate" type="date" id="startDate" class="form-control" placeholder="DD-MM-YYYY"  required="true"/>
				    </div>
				  </div>
				
				  <div class="form-group row">
				    <label for="endDate" class="col-sm-2 col-form-label"><b>End Date</b><span style="color: red"> *</span></label>
				    <div class="col-sm-10">
				      <form:input path="endDate" type="date" id="endDate" class="form-control" placeholder="DD-MM-YYYY"  required="true"/>
				    </div>
				  </div>
				
				  <div class="form-group row">
				    <label for="fees" class="col-sm-2 col-form-label"><b>Fees</b><span style="color: red"> *</span></label>
				    <div class="col-sm-10">
				      <form:input path="fees" id="fees" class="form-control" placeholder="Value in INR" required="true"/>
				    </div>
				  </div>
				
				  <div class="form-group row">
				    <label for="about" class="col-sm-2 col-form-label"><b>About</b><span style="color: red"> *</span></label>
				    <div class="col-sm-10">
				      <form:textarea path="about" class="form-control"  rows="3" required="true"></form:textarea>
				    </div>
			    </div>	
			    
			    <div class="form-group row">
				     <label for="imageData" class="col-sm-2 col-form-label"><b>Course Image</b><span style="color: red"> *</span></label>
				     <div class="col-sm-10">
			    		<form:input type="file" path="imageData" class="form-control-file"  required="true"/>
		    		</div>
			 	 </div>
			 	 
			 	 <div class="form-group row">
				    <label for="presentationDate" class="col-sm-2 col-form-label"><b>Presentation Date</b><span style="color: red"> *</span></label>
				    <div class="col-sm-10">
				      <form:input path="presentationDate" type="date" id="presentationDate" class="form-control" placeholder="DD-MM-YYYY"  required="true"/>
				    </div>
				  </div>
			 	 
			 	  <form:button type="submit" class="btn btn-primary">Add Course</form:button>
			 	  <a href='<c:url value="/admindashboard"></c:url>' class="btn btn-secondary ml-1" role="button" aria-disabled="true">Back</a>
		    </form:form>
		</div>    
		
		

</body>
</html>