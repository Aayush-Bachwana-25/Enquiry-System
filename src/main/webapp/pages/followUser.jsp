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
<!--setting query-id to retrieve details on next page -->
	<c:set var="enquiryId" value="${enquiry.id}" scope="session"/>
	<c:set var="url" value="${requestScope.requestURL}" scope="session"/>
	

	<div class="container">
		
		<%@include file="./message.jsp" %>
	
		<h1 class="h1 my-5 text-center">Follow-Up User </h1>
		<form method="post" action="saveFollowUp">
			<table class="table">
			  <thead class="thead-dark">
			  	<tr>
			      <th scope="row" colspan="5" style="text-align: center; background-color: grey;">Follow-up</th>
			    </tr>
			    <tr>
			      <th scope="col">Candidate Name</th>
			      <th scope="col">Parent Name</th>
			      <th scope="col">Email</th>
			      <th scope="col">Mobile</th>
			      <th scope="col">Date of Enquiry</th>
			    </tr>
			  </thead>
			  <tbody>
				    <tr>
				      <td scope="row">${enquiry.candidateName}</td>
				      <td>${enquiry.parentName}</td>
				      <td>${enquiry.email}</td>
				      <td>${enquiry.mobile}</td>
				      <td>${enquiry.enquiry_date}</td>
				    </tr>
				    
				    <tr class="thead-dark">
				    	<th scope="row" colspan="5" style="text-align: center; background-color: grey;">Feedback Summary</th>
				    </tr>
				    
				    <c:forEach var="Feedback" items="${feedbackHistory}">
				    	<tr>
				    		<th colspan="1" style="background-color: aqua;">${Feedback.timestamp}</th>
				    		<td colspan="4">${Feedback.feedback}</td>
				    	</tr>
				    </c:forEach>
				    
				    <tr class="thead-dark">
					    <th scope="row" colspan="5" style="text-align: center; background-color: grey;">Follow-up Date</th>
				  	</tr>
				  	<tr>
					    <td colspan="5"><input name="followDate" type="date" id="followDate" class="form-control" placeholder="DD-MM-YYYY" /></td>
				  	</tr>
				  	
				  	<tr class="thead-dark">
					    <th scope="row" colspan="5" style="text-align: center; background-color: grey;">Give Feedback</th>
				  	</tr>
				  	<tr>
					    <td colspan="5"><textarea name="feedback" id="feedback" class="form-control" placeholder="Enter feedback here" rows="3"></textarea></td>
				  	</tr>
			   </tbody>
			</table>
			

			<div style="text-align: center;">
			  
			  <button type="submit" class="btn btn-primary">Update</button>
			  <a href="<c:url value="/admin/viewqueries" />" class="btn btn-danger ml-1" role="button" aria-disabled="true">Cancel</a>
			  
			</div>
			
			<a href='<c:url value="/admin/addStudent"></c:url>' class="btn btn-success ml-1 float-right" role="button" aria-disabled="true">Register Student</a>

		</form>
    
    </div>
</body>
</html>