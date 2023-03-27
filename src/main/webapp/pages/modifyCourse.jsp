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
	
	<form:form method="post" modelAttribute="course" enctype="multipart/form-data">
			<table>
				<tr>
					<td>
						<form:label path="courseName"><b>Course Name</b></form:label>
					</td>
					
					<td>
						<form:input path="courseName" value="${course.courseName}"/><span style="color:red">*</span>
					</td>
				</tr>
				
				<tr>
					<td>
						<form:label path="ageGroup"><b>Age Group</b><br/><span style="color:orange">*Required</span></form:label>
					</td>
					
					<td>	
						<form:select path="ageGroup">
						<form:option value="${course.ageGroup}">Select Group</form:option>
							<form:option value="0-10">0-10 years</form:option>
							<form:option value="10-20">10-20 years</form:option>
							<form:option value="20-30">20-30 years</form:option>
						</form:select>
						<span style="color:red">*</span>
					</td>
				</tr>
				
				<tr>
					<td>
						<form:label path="noOfHrs"><b>No. of Hrs</b><br/><span style="color:orange">*Required</span></form:label>
					</td>
					
					<td>
						<form:input path="noOfHrs" type="number" value="${course.noOfHrs}"/><span style="color:red">*</span>
					</td>
					
				</tr>
				
				<tr>
					<td>
						<form:label path="startDate"><b>Start Date</b><br/><span style="color:orange">*Required</span></form:label>
					</td>
					
					<td>
						<form:input path="startDate" type="date" placeholder="DD-MM-YYYY" value="${course.startDate}"/><span style="color:red">*</span>
					</td>
				</tr>
				
				
				<tr>
					<td>
						<form:label path="endDate"><b>End Date</b></form:label>
					</td>
					
					<td>
						<form:input path="endDate" type="date" placeholder="DD-MM-YYYY" value="${course.endDate}"/><span style="color:red">*</span>
					</td>
					
				</tr>
				
				<tr>
					<td>
						<form:label path="fees"><b>Fees</b></form:label>
					</td>
					
					<td>
						<form:input path="fees" value="${course.fees}"/><span style="color:red">*</span>
					</td>
					
				</tr>
				
				<tr>
					<td>
						<form:label path="about"><b>About</b></form:label>
					</td>
					
					<td>
						<form:input path="about" value="${course.about}" type="textarea"/><span style="color:red">*</span>
					</td>
					
				</tr>
				
				<tr>
					<td>
						<form:label path="imageData"><b>Image</b></form:label>
					</td>
					
					<td>
						<form:input path="imageData" type="file"/><span style="color:red">*</span>
					</td>
					
				</tr> 
				
			</table>	
			<form:button type="submit">Update</form:button>
		
		</form:form>
</body>
</html>