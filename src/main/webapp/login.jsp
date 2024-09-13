<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<h1>로그인 정보 세션 저장</h1>
<hr>

<%
	pageContext.setAttribute("scopeName1", "pageScope 값");
	request.setAttribute("scopeName2", "requestScope 값");
	session.setAttribute("scopeName3", "sessionScope 값");
	application.setAttribute("scopeName4", "applicationScope 값");
%>

pageScope의속성값은: ${pageScope.scopeName1}<br>
requestScope 속성값은: ${requestScope.scopeName2}<br>
sessionScope 속성값은: ${sessionScope.scopeName3}<br>
applicationScope 속성값은: ${applicationScope.scopeName4}<br><br>

pageScope의속성값은: ${scopeName1}<br>
requestScope 속성값은: ${scopeName2}<br>
sessionScope 속성값은: ${scopeName3}<br>
applicationScope 속성값은: ${scopeName4}<br>

<br>사용자 아이디: ${param.userid}<br>
사용자 비밀번호: ${param.passwd}<br>