<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	pageContext.setAttribute("scopeName", "pageScope 값");
	request.setAttribute("scopeName", "requestScope 값");
	session.setAttribute("scopeName", "sessionScope 값");
	application.setAttribute("scopeName", "applicationScope 값");
%>

pageScope의속성값은: ${pageScope.scopeName}<br>
requestScope 속성값은: ${requestScope.scopeName}<br>
sessionScope 속성값은: ${sessionScope.scopeName}<br>
applicationScope 속성값은: ${applicationScope.scopeName}<br>