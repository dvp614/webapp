<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h3><%=request.getRequestURI() %></h3>
<hr>

<c:set var="myDate" value="<%= new Date() %>" scope="request" />
now : ${myDate }
<c:out value="${myDate.getClass().getName() }"/>
<fmt:formatDate value="${myDate}" type="date"/><br>
<fmt:formatDate value="${myDate}" type="time"/><br>
<fmt:formatDate value="${myDate}" type="both"/><br><br>

<fmt:formatDate value="${myDate}" type="date" dateStyle="short" timeStyle="long"/><br>
<fmt:formatDate value="${myDate}" type="date" dateStyle="long" timeStyle="short"/><br><br>

<fmt:formatDate value="${myDate}" pattern="yyyy/MM/dd HH:mm:ss.SSS"/><br>
