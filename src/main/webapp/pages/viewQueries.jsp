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
		<table class="table">
		  <thead class="thead-dark">
		  <tr>
		      <td colspan="7" style="{text-align: center;}">Today's Queries</td>
		    </tr>
		    <tr>
		      <th scope="col">Candidate Name</th>
		      <th scope="col">Parent Name</th>
		      <th scope="col">Email</th>
		      <th scope="col">Mobile</th>
		      <th scope="col">Date of Enquiry</th>
		      <th scope="col">Query</th>
		      <th scope="col">Follow Date</th>
		      <th scope="col">Action</th>
		    </tr>
		  </thead>
		  <tbody>
			  <c:forEach var="enquiry"  items="${enquiries}">
			    <tr>
			      <td scope="row"><a href="followUpUser/${enquiry.id}">${enquiry.candidateName}</a></td>
			      <td>${enquiry.parentName}</td>
			      <td>${enquiry.email}</td>
			      <td>${enquiry.mobile}</td>
			      <td>${enquiry.enquiry_date}</td>
			      <td>${enquiry.query}</td>
			      <td><b>${enquiry.follow_date}</b></td>
			      <td><a href="#">Enroll Student</a></td>
			    </tr>
			    </c:forEach>
		   </tbody>
		</table>
    
    </div>
</body>
</html>