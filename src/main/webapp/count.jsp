<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%! int count; %>

<%
	count++;

	application.setAttribute("countValue", count);
%>

<h1>현재 방문자수 : <%= count %></h1>