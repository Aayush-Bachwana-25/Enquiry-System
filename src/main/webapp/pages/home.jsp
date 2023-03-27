<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    
    <%@include file="./base.jsp" %>

</head>
<body>
    
    <%@include file="./navbar.jsp" %>
    
	 
	<div class="container-fluid" style="margin-bottom: 75px">
	  <div class="row">
		    <div class="col">
		      	<h1 class="display-1 mx-5" style="margin-top: 100px">ComputerSeekho</h1>
			    <p class="h1 mx-5 my-5" id="my-text" style="font-weight: normal; color: #281780;"></p>
		    </div>
		    <div>
		      <img src='<c:url value="/resources/images/computer.png"></c:url>' style="margin-top: 120px; margin-right:180px;" alt="Logo" width="300px" height=300px"/>
		    </div>
		  </div>
	 </div>
	 <hr/>
	 <h1 class="h1 text-center mt-5">Offered Courses</h1>
    	
    <div class="container mt-5" style="margin-bottom: 100px">

		<table class="table border-bottom border-secondary">
		  <thead class="thead-dark">
		    <tr>
		      <th scope="col">Course Name</th>
		      <th scope="col">Age Group</th>
		      <th scope="col">No. of hrs</th>
		      <th scope="col">Fees</th>
		      <th scope="col" style="text-align:center;">Action</th>
		    </tr>
		  </thead>
		  <tbody>
			  <c:forEach var="course"  items="${courses}">
			    <tr>
			      <td scope="row"><a href="#">${course.courseName}</a></td>
			      <td>${course.ageGroup}</td>
			      <td>${course.noOfHrs}</td>
			      <td><b>&#x20B9;</b>&nbsp;${course.fees}</td>
			      <td scope="row" style="text-align:center;"><a href="#" style="color: #1E3050;"><i class="fa-sharp fa-solid fa-envelope fa-lg"></i></a>
			      
			      </td>
			    </tr>
			    </c:forEach>
		   </tbody>
		</table>
    
    </div>
    
    <script type="text/javascript">
	    var text = "We are leading educational institute providing best quality education in the industry.";
	
	    function displayWords() {
	        var words = text.split(" ");
	        var i = 0;
	        var intervalId = setInterval(function() {
	            document.getElementById("my-text").innerHTML += words[i] + " ";
	            i++;
	            if (i >= words.length) {
	                clearInterval(intervalId);
	            }
	        }, 400); // change the time interval (in milliseconds) to adjust the speed of display
	    }
	
	    window.onload = function() {
	        displayWords();
	    };

    </script>
</body>
</html>