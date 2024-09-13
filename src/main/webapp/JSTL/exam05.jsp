<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3><%=request.getRequestURI() %></h3>
<hr>

<%
	int [] num = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	
	request.setAttribute("myArray", num);
%>

<c:forEach var="element" items="${myArray}">
	|${element}|
</c:forEach>