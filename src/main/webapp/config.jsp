<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h1>config 실습</h1>
<hr>
<h1>config 내장객체를이용한초기화파라미터값얻기</h1>

<% 
	String dirPath = config.getInitParameter("dirPath");
	String userid = config.getInitParameter("userid");
%>

dirPath 값: <%= dirPath %><br>
userid 값: <%= userid %><br>