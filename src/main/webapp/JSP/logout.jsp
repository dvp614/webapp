<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<h1>로그인 정보 보기</h1>

<%
	session.invalidate();

	response.sendRedirect("/loginForm.html");
%>