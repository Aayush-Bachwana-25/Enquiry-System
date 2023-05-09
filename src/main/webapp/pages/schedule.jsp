<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<%@include file="./base.jsp" %>
	
	
	<style>
		img {
		  display: block;
		  margin: 0 auto;
		  border: 1px solid black;
		  margin-top: 40px
		}
		
		.video-container {
		  margin-right:10px;
		  width:250px;
		  display: inline-flex;
		  border: 1px solid black;
		}
		
		.video-container iframe {
	      width: 250px;
	    }
		
	</style>
</head>
<body>
	<%@include file="./navbar.jsp" %>
	
	<div class="container">
		<img src="../resources/images/${course.image}" alt="Course-banner" width="40%" height="250px"/>
		<h3 class="h3 text-center my-5">${course.courseName}</h3>
		
		<h4 class="h4">About the Course</h4>
		<p style="font-size: 20px; text-align: justify; margin-bottom: 50px;">${course.about}</p>
		
		<div class="d-flex justify-content-between">
		  <p class="h5 float-left">Age: ${course.ageGroup}</p>
		  <p class="h5 center">No. of hours: ${course.noOfHrs}</p>
		  <p class="h5 float-right">Fee: &#x20B9;&nbsp;${course.fees}</p>
		</div>
		<div style="max-height: 600px; overflow-x: hidden; overflow-y: scroll;  padding: 20px;">
		<table class="table table-bordered" style="text-align: center;">
		  <thead >
			<tr class="thead-dark">
			  <th colspan="4">Schedule</th>
			</tr>
		    <tr>
		      <th scope="col">Date</th>
		      <th scope="col">Batches</th>
		      <th scope="col">Timings</th>
		      <th scope="col">Final Presentation</th>		      
		    </tr>
		  </thead>
		  <tbody>
				<c:forEach begin="0" end="${noOfDays}" step="1">
			   		<c:forEach var="batch" items="${batches}" varStatus="batchStatus"> 
			 
  							<tr>
							  <c:if test="${batchStatus.index eq 0}">
						        <td rowspan="${countOfBatches}" style="vertical-align: middle;">${course.startDate}</td>
						      </c:if>
							  <td>${batch.name}</td>
							  <td>${batch.timings}</td>
							  <c:if test="${batchStatus.index eq 0}">
						        <td rowspan="${countOfBatches}" style="vertical-align: middle;">${course.presentationDate}</td>
						      </c:if>
							 
							</tr>			
			   		</c:forEach>
				</c:forEach>
		   </tbody>
		</table>

			
		</div>
		<br/>
		<hr/>
		<h4 class="h4">Creative Corner</h4>
		
		<!-- 	error msg if no videos are available to display -->
		
		<%@include file="./message.jsp" %>
	
		
		<div class="container">
			<c:forEach  var="link" items="${links}" >
					<div class="ratio ratio-16x9 video-container">
					  <iframe src='<c:url value="https://www.youtube.com/embed/${link}?mute=1&autoplay=1&controls=0"></c:url>' title="${title}"></iframe>
					</div>
			</c:forEach>

		</div>
	</div>

</body>
</html>