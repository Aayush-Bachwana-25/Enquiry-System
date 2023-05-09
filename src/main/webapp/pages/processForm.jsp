<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
       
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="ISO-8859-1">
<%@include file="./base.jsp" %>
</head>
<body>
	<%@include file="./navbar.jsp" %>
	
	<div class="container">
	<h1 class="h1 text-center mt-5">Student Registration</h1>
		<table class="table border border-dark mt-5">
			  <thead class="thead-dark">
			  	<tr>
			      <th scope="row" colspan="6" style="text-align: center; background-color: grey;">Review Details</th>
			    </tr>
			    <tr>
			      <th scope="col">Candidate Name</th>
			      <th scope="col">Father Name</th>
			      <th scope="col">Email</th>
			      <th scope="col">Mobile</th>
			      <th scope="col">Course</th>
			      <th scope="col">Fees</th>
			    </tr>
			  </thead>
			  <tbody>
				    <tr>
				      <td scope="row">${student.studentName}</td>
				      <td>${student.fatherName}</td>
				      <td>${student.email}</td>
				      <td>${student.mobile}</td>
				      <td>${course.courseName}</td>
				      <td>${course.fees}</td>
				    </tr>
			    </tbody>
	    </table>
		<h1 class="h4 ml-3" style="margin-top: 70px">Declaration</h1>
		
		<form:form method="post" modelAttribute="transactionDetail">
		
			<div class="form-group" style="margin-top: 10px;">
					    <div class="form-check">
					      <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
					      <label class="form-check-label" for="invalidCheck">
						        I hereby apply for admission to the above-mentioned course; 
						        Paying by 
						        <select name="mode" id="mode"  required="true">
							        <option value="">Mode of Payment</option>
							        <option value="Cash">Cash</option>
							        <option value="Cheque">Cheque</option>
							        <option value="Demand Draft">Demand Draft</option>
						      	</select>
						     
						      for <b>&#x20B9;</b>&nbsp;
						      <input type="number" id="fees" value="${course.fees}" placeholder="Course fees" disabled="disabled">
						      the cheque/DD no. 
						      <form:input path="chequeNo" id="chequeNo" placeholder="Cheque Number(if applicable)"/>
						      of 
						      <form:input path="bankName" id="bankname" placeholder="Bank Name(if applicable)"/> .
						      <p style="margin-top: 30px">I agree to abide by the rules & regulations of the institute.</p>
					       </label>
					      <div class="invalid-feedback">
					        You must agree before submitting.
					      </div>
					    </div>
					  </div>
					  
					  <form:input type="hidden" path="studentId" id="studentId" value="${student.studentId}"/>
					  <form:input type="hidden" path="courseId" id="courseId" value="${course.courseId}"/>
						      
				 	  
				 	 <div style="text-align: center;">
					 	  <form:button type="submit" class="btn btn-primary">Register</form:button>
					 	  <a href='<c:url value="/admindashboard"></c:url>' class="btn btn-secondary ml-1" role="button" aria-disabled="true">Back</a>
				 	  </div> 
	
		</form:form>
	</div>
	

</body>
</html>