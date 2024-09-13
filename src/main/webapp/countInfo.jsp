<%@ page
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	int count = (Integer)application.getAttribute("countValue");
%>

<h1>현재까지 총 방문자수 : <%= count %></h1>