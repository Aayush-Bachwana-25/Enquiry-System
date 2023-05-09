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
	
	<div class="container">
			<h1 class="h1 text-center mt-5">Enquiry form</h1><hr/>
			
			<%@include file="./message.jsp" %>
			
			<div class="embed-responsive embed-responsive-16by9 my-5" style="width: 60%; margin: 0px auto; border: 2px solid black;">
					<iframe src="https://maps.google.com/maps?q=Vidyanidhi%20Education%20Complex,JVPD%20Scheme&amp;t=&amp;z=14&amp;ie=UTF8&amp;iwloc=&amp;output=embed" 
							id="gmap_canvas" 
							frameborder="0" 
							scrolling="no" >
							
					</iframe>
			</div>
			<h3>Enter details:</h3><hr/>
			    
			<div class="row mb-5">
			    <div class="col">
			    		<form:form method="post" modelAttribute="myquery">
							<div class="form-group">
								<label for="parentName"><b>Parent's Name</b><span style="color:red">*</span></label>
								<form:input path="parentName" class="form-control" id="parentName"  required="true"/>
							</div>
							
							<div class="form-group">
								<label for="candidateName"><b>Candidate Name</b><span style="color:red">*</span></label>
								<form:input path="candidateName" class="form-control" id="candidateName" required="true"/>
							</div>
														
							<div class="form-group">
								<label for="email"><b>Email</b><span style="color:red">*</span></label>
								<form:input path="email" class="form-control" id="email" required="true"/>
							</div>
							
							<div class="form-group">
								<label for="mobile"><b>Mobile</b><span style="color:red">*</span></label>
								<form:input path="mobile" class="form-control" id="mobile" required="true"/>
							</div>
							
							<div class="form-group">
								<label for="query"><b>Your Query</b><span style="color:red">*</span></label>
								<form:textarea path="query" class="form-control" id="query" required="true"></form:textarea>
							</div>
							
							<button type="submit" class="btn btn-success">Submit</button>
						
						</form:form>
			    </div>
			    
			    <div class="col d-flex align-items-center justify-content-center">
			      	<div style="font-size: 24px;">
			      	<b>Vidyanidhi Infotech Academy</b><br/>
					5th Floor, Vidyanidhi Education<br/>
					Complex, JVPD Scheme, Juhu,<br/>
					Mumbai -400049.<br/>
					
					<b>Tel:</b> 26255629/ 26705498/<br/>
					61504639.
					<p>
					<b>Mobile: </b>9029435311<br/>
					<b>e-Mail: </b>comps4every1@gmail.com<br/>
					</div>
			    </div>
			  </div>

		</div>
	
</body>
</html>