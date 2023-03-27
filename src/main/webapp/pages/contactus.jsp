<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    

<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="./base.jsp" %>
</head>
<body>
	<%@include file="./navbar.jsp" %>
	
	<div class="mapouter">
		<div class="gmap_canvas">
			<iframe src="https://maps.google.com/maps?q=Vidyanidhi%20Education%20Complex,JVPD%20Scheme&amp;t=&amp;z=14&amp;ie=UTF8&amp;iwloc=&amp;output=embed" 
					id="gmap_canvas" 
					frameborder="0" 
					scrolling="no" 
					style="width: 790px; height: 240px;">
					
			</iframe>
			<a href="https://fnfmods.net">fnf mod</a>
			
			<style>.mapouter{position:relative;text-align:right;height:240px;width:790px;}</style><style>.gmap_canvas{overflow:hidden;background:none!important;height:240px;width:790px;}</style>
		</div>
	</div>
	
	<div>
		<form:form method="post" modelAttribute="myquery">
			<table>
				<tr>
					<td>
						<form:label path="parentName"><b>Parent's Name</b></form:label>
					</td>
					
					<td>
						<form:input path="parentName" />
					</td>
					
					
				</tr>
				
				<tr>
					<td>
						<form:label path="candidateName"><b>Your Name</b><br/><span style="color:orange">*Required</span></form:label>
					</td>
					
					<td>
						<form:input path="candidateName"/>
					</td>
					
					<td>
						<span style="color:orange">*</span>
					</td>
				</tr>
				
				<tr>
					<td>
						<form:label path="email"><b>Email</b><br/><span style="color:orange">*Required</span></form:label>
					</td>
					
					<td>
						<form:input path="email"/>
					</td>
					
					<td>
						<span style="color:orange">*</span>
					</td>
				</tr>
				
				<tr>
					<td>
						<form:label path="mobile"><b>Mobile</b><br/><span style="color:orange">*Required</span></form:label>
					</td>
					
					<td>
						<form:input path="mobile"/>
					</td>
					
					<td>
						<span style="color:orange">*</span>
					</td>
				</tr>
				
				
				<tr>
					<td>
						<form:label path="query"><b>Your Query</b></form:label>
					</td>
					
					<td>
						<form:input path="query"/>
					</td>
					
				</tr>
				
			</table>	
			<form:button type="submit">Submit</form:button>
		
		</form:form>
	</div>
	
	
	<div>
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
	
</body>
</html>