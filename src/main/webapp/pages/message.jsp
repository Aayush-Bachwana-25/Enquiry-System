<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!-- 	error msg -->
<c:if test="${not empty sessionScope.message}">
  <div class="alert alert-${sessionScope.message.type} container text-center" role="alert">
    ${sessionScope.message.messageContent}
  </div>
  <c:remove var="message" scope="session"/>
</c:if>