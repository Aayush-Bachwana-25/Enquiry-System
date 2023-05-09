<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

	
    <%@include file="./base.jsp" %>
    
    <style type="text/css">
    	p{
    		font-size: 20px;
    	}
    	
    	h3{
    		margin-top: 10px;
    	}
    	.item{
    		text-decoration: underline;
    	}
    </style>
</head>
<body>
    <%@include file="./navbar.jsp" %>
    <div class="container my-5">
		<h1>About us:</h1><hr/>
		<h3>We believe:</h3>
		<p>To learn and work successfully in an increasingly information-rich society, one must be able to use technology effectively and creatively. This applies to all strata of society...students, teachers, professionals, homemakers and senior citizens.</p>
		<h1></h1>
		<h3>Our Mission:</h3>
		<p>To develop capable users of Information Technology who will effectively and creatively use the most amazing machine - a PC!</p>
	 	<h1></h1>
		<h3>What we offer:</h3>
	 	<br/>
		<h5 class="item">Customized Workshops to empower the user:</h5>
		<p>These workshops are designed keeping in mind the individual needs of students, teachers, professionals, homemakers and Senior Citizens.</p>
 	</div>
    
</body>
</html>