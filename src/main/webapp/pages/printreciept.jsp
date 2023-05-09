<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="./base.jsp" %>

	<style type="text/css">
		table{
			font-size: 20px; 
			margin: 20px;
		}
		td{
		
			padding-left: 50px;
			padding-top: 5px;
		}
		body{
		padding-bottom: 50px;
		}
		
		.button {
			display: block;
			margin:0px auto;
			margin-top: 20px;
			
		}
	</style>
</head>
<body>
	<%@include file="./navbar.jsp" %>
	
	
		<div class="container">
			<h1 class="h1">Receipt</h1><hr/>
			
			<h3 class="h3 text-center">Personal Details</h3>
			<div>
			<table style="display: inline-flex;">
				<tr>
					<th>Name: </th>
					<td>${student.studentName}</td>
				</tr>
				
				<tr>
					<th>Father's name: </th>
					<td>${student.fatherName}</td>
				</tr>
				
				<tr>
					<th>Mother's name: </th>
					<td>${student.motherName}</td>
				</tr>
				
				<tr>
					<th>Date of Birth:</th>
					<td>${student.dateOfBirth}</td>
				</tr>
				
				<tr>
					<th>Gender: </th>
					<td>${student.gender}</td>
				</tr>
				
				<tr>
					<th>Mobile: </th>
					<td>${student.mobile}</td>
				</tr>
				
				<tr>
					<th>Email: </th>
					<td>${student.email}</td>
				</tr>
				
				<tr>
					<th>Address: </th>
					<td>${student.residentialAddress}</td>
				</tr>			
			</table>
			
			<div style="width: 125px; height: 200px; margin-top: 30px; float: right;">
				<img alt="Profile" src='<c:url value="/resources/images/${student.profilePhoto}"></c:url>' width="140px" height="150px" style="display: block; float: right;border: 1px solid black;"/>
				<img alt="Sign" src='<c:url value="/resources/images/${student.signature}"></c:url>' width="140px" height="50px" style="display: block; float: right; margin-top: 5px; border: 1px solid black;">
			</div>
			
			</div>
			
			<h3 class="h3 text-center">Course Details</h3><hr/>
			
			<table>
				<tr>
					<th>Course Name: </th>
					<td>${course.courseName}</td>
				</tr>
				
				<tr>
					<th>Start Date: </th>
					<td>${course.startDate}</td>
				</tr>
				
				<tr>
					<th>End Date: </th>
					<td>${course.endDate}</td>
				</tr>
				
				<tr>
					<th>Fees: </th>
					<td>${course.fees}</td>
				</tr>
			</table>
			
			<h3 class="h3 text-center">Transaction Details</h3><hr/>
			
			<table>
				<tr>
					<th>Status</th>
					<td style="color: green;">Received</td>
				</tr>
				
				<tr>
					<th>Transaction Id: </th>
					<td>T00${transaction.transactionId}</td>
				</tr>
				
				<tr>
					<th>Payment mode: </th>
					<td>${transaction.mode}</td>
				</tr>
				
				<c:if test="${transaction.mode ne 'Cash'}">
					<tr>
						<th>Cheque/DD No.: </th>
						<td>${transaction.chequeNo}</td>
					</tr>
					
					<tr>
						<th>Bank Name: </th>
						<td>${transaction.bankName}</td>
					</tr>
				</c:if>
				
			</table>
			<hr/>
		</div>
		<button type="button" class="btn btn-secondary my-3 button" onclick="window.print()">Print</button>
		

</body>
</html>