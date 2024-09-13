<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h3><%=request.getRequestURI() %></h3>
<hr>

<%
	String value = 
		ResourceBundle
			.getBundle("message")
			.getString("sendMessage");
%>

<%=value %><br>

<fmt:setBundle basename="message" var="mybundle"/>

출력메세지 : <fmt:message key="sendMessage" bundle="${mybundle }"/>
<br>
sendMessage : ${mybundle }

