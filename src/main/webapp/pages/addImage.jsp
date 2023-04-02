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

	<h1 class="h1 text-center m-3">Add Images for album: ${album.albumName}</h1>
		
	<!-- 	error msg -->
	<c:if test="${not empty sessionScope.message}">
	  <div class="alert alert-${sessionScope.message.type} container text-center" role="alert">
	    ${sessionScope.message.messageContent}
	  </div>
	  <c:remove var="message" scope="session"/>
	</c:if>
	
	<div class="container mb-5">
			<form method="post" action="" enctype="multipart/form-data">
				  <div class="form-group row">
				    <label for="albumName" class="col-sm-2 col-form-label"><b>Album Name</b></label>
				    <div class="col-sm-10">
				      <input name="albumName" id="albumName" value="${album.albumName}" class="form-control" disabled="disabled" /><span style="color:red">*</span>
				    </div>
				  </div>
		
				    
			    <div class="form-group row">
				     <label for="imageData" class="col-sm-2 col-form-label"><b>Image</b></label>
				     <div class="col-sm-10">
			    		<input type="file" name="imageData" id="imageData" class="form-control-file" />
		    		</div>
			 	 </div>
			 	 
			 	  <button type="submit" class="btn btn-primary">Add Image</button>
			 	  <a href="admindashboard" class="btn btn-secondary ml-1" role="button" aria-disabled="true">Back</a>
		    </form>
		</div>    
</body>
</html>