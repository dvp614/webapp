<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h1>out 예제</h1>

<%
	String name = "홍길동";
	out.println("이것은 out 내장객체로 출력 : " + name +  "<br>");
%>

이것은 expression tag로 출력: <%= name %>