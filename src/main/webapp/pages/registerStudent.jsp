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
	
	<h1 class="h1 text-center my-5">Student Registration</h1>
	
		<div class="container mb-5">
			<form:form method="post" modelAttribute="student" enctype="multipart/form-data">
				  <div class="form-group row">
				    <label for="studentName" class="col-sm-2 col-form-label"><b>Student Name</b><span style="color:red"> *</span></label>
				    <div class="col-sm-10">
				      <form:input path="studentName" id="studentName" class="form-control"  required="true"/>
				    </div>
				  </div>
				  
				  <div class="form-group row">
				    <label for="fatherName" class="col-sm-2 col-form-label"><b>Father Name</b><span style="color:red"> *</span></label>
				    <div class="col-sm-10">
				      <form:input path="fatherName" id="fatherName" class="form-control"  required="true"/>
				    </div>
				  </div>
				  
				  <div class="form-group row">
				    <label for="motherName" class="col-sm-2 col-form-label"><b>Mother Name</b></label>
				    <div class="col-sm-10">
				      <form:input path="motherName" id="motherName" class="form-control" />
				    </div>
				  </div>
				  
				  <div class="form-group row">
				    <label for="dateOfBirth" class="col-sm-2 col-form-label"><b>Date of Birth</b><span style="color:red"> *</span></label>
				    <div class="col-sm-10">
				      <form:input path="dateOfBirth" type="date" id="dateOfBirth" class="form-control" placeholder="DD-MM-YYYY"  required="true"/>
				    </div>
				  </div>
				  
				  				  
				  <div class="form-group row">
					    <label for="gender" class="col-sm-2 col-form-label"><b>Gender</b><span style="color:red"> *</span></label>
					    
						
						<div class="form-check form-check-inline ml-3">
						  <input class="form-check-input" type="radio" name="gender" id="male" value="male"/>
						  <label class="form-check-label ml-2" for="male">Male</label>
						</div>
						
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="gender" id="female" value="female"/>
						  <label class="form-check-label ml-2" for="female">Female</label>
						</div>
						
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="gender" id="other" value="other"/>
						  <label class="form-check-label ml-2" for="other">Other</label>
						</div>
					</div>
					
				  
				  
				  <div class="form-group row">
				    <label for="residentialAddress" class="col-sm-2 col-form-label"><b>Residential Address</b><span style="color:red"> *</span></label>
				    <div class="col-sm-10">
				      <form:textarea path="residentialAddress" id="residentialAddress" class="form-control"  rows="2" required="true"></form:textarea>
				    </div>
				  </div>
				  
				  <div class="form-group row">
				    <label for="officeAddress" class="col-sm-2 col-form-label"><b>Office Address</b><br/></label>
				    <div class="col-sm-10">
				      <form:textarea path="officeAddress" id="officeAddress" class="form-control"  rows="2"></form:textarea>
				    </div>
				  </div>
				  
				  <div class="form-group row">
				    <label for="mobile" class="col-sm-2 col-form-label"><b>Mobile</b><span style="color:red"> *</span></label>
				    <div class="col-sm-10">
				      <form:input path="mobile" id="mobile" class="form-control" value=""  required="true"/>
				    </div>
				  </div>
				  
				  <div class="form-group row">
				    <label for="residentialPhone" class="col-sm-2 col-form-label"><b>Residential Phone</b></label>
				    <div class="col-sm-10">
				      <form:input path="residentialPhone" id="residentialPhone" class="form-control" value=""/>
				    </div>
				  </div>
				  
				  <div class="form-group row">
				    <label for="officePhone" class="col-sm-2 col-form-label"><b>Office Phone</b></label>
				    <div class="col-sm-10">
				      <form:input path="officePhone" id="officePhone" class="form-control" value="" />
				    </div>
				  </div>
				  
				  
				  
				  <div class="form-group row">
				    <label for="email" class="col-sm-2 col-form-label"><b>Email</b><span style="color:red"> *</span></label>
				    <div class="col-sm-10">
				      <form:input path="email" type="email" id="email" class="form-control" required="true"/>
				    </div>
				  </div>
				
				  <div class="form-group row">
				    <label for="educationalQualification" class="col-sm-2 col-form-label"><b>Educational Qualification</b><span style="color:red"> *</span></label>
				    <div class="col-sm-10">
				      <form:select path="educationalQualification" id="educationalQualification" class="form-control">
				        <form:option value="">Select Highest Qualification</form:option>
				        <form:option value="High School"></form:option>
				        <form:option value="Higher Secondary"></form:option>
				        <form:option value="Diploma"></form:option>
				        <form:option value="Graduation"></form:option>
				        <form:option value="Post-Graduation"></form:option>
				      </form:select>
				      
				    </div>
				  </div>
				
			    <div class="form-group row">
				     <label for="profile" class="col-sm-2 col-form-label"><b>Profile Photo</b><span style="color:red"> *</span></label>
				     <div class="col-sm-10">
			    		<input type="file" name="profile" class="form-control-file" />
		    		</div>
			 	 </div>
			 	 
			 	 <div class="form-group row">
				     <label for="sign" class="col-sm-2 col-form-label"><b>Signature</b><span style="color:red"> *</span></label>
				     <div class="col-sm-10">
			    		<input type="file" name="sign" class="form-control-file" />
		    		</div>
			 	 </div>
			 	  
			 	 <div class="form-group row">
				    <label for="courseId" class="col-sm-2 col-form-label"><b>Course Name</b><span style="color:red"> *</span></label>
				    <div class="col-sm-10">
				      		<form:select path="courseId" class="form-control"  required="true">
						        <form:option value="0">Select Course</form:option>
						        <c:forEach items="${courses}" var="course">
						            <form:option value="${course.courseId}">${course.courseName}</form:option>
						        </c:forEach>
						    </form:select>		      
				    </div>
				  </div>
				  
				  <div style="text-align: center;">
				 	  <form:button type="submit" class="btn btn-primary">Next</form:button>
				 	  <a href='<c:url value="/admindashboard"></c:url>' class="btn btn-secondary ml-1" role="button" aria-disabled="true">Cancel</a>
			 	  </div>
		    </form:form>
		</div>    
		
		<script>
			
		</script>
</body>
</html>