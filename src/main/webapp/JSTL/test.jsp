<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h3><%=request.getRequestURI() %></h3>
<hr>


<%
		Integer obj = 100;

		request.setAttribute("OBJ", obj);
%>

1. ${OBJ}<br>
2. type : ${OBJ.getClass().getName()}