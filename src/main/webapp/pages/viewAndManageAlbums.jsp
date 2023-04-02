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
    	    		
    	<c:if test="${not empty sessionScope.message}">
			  <div class="alert alert-${sessionScope.message.type} container text-center" role="alert">
			    ${sessionScope.message.messageContent}
			  </div>
			  <c:remove var="message" scope="session"/>
		</c:if>
			
		<a class="nav-link text-white float-right my-3" href="addAlbum" style="background-color: #1a7f42;">Add new album</a>
				
		<table class="table">
		  <thead class="thead-dark">
		    <tr>
		      <th scope="col">Album Name</th>
		      <th scope="col" colspan="2">Action</th>
		    </tr>
		  </thead>
		  
		  
		
		  <tbody>
			  <c:forEach var="album"  items="${albums}">
			    <tr>
			      <td scope="row"><a href="manageAlbumImages/${album.albumId}">${album.albumName}</a></td>
			      <td><a href="deleteAlbum/${album.albumId}"><i class="fas fa-trash" style="color: #ed0707;"></i></a></td>
			    </tr>
			    </c:forEach>	
		   </tbody>
		</table>
    	
    	
    </div>
	
</body>
</html>