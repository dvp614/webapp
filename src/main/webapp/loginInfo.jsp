<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<h1>로그인 정보 보기</h1>

<%
	String id = (String) session.getAttribute("userid");
	
	if (id == null) {
	
		response.sendRedirect("loginForm.html");
	
	} else {
	
		String pw = (String) session.getAttribute("passwd");
	
		out.print("사용자아이디값: " + id + "<br>");
		out.print("사용자비밀번호값: " + pw + "<br>");
	
		out.print("<a href='/JSP/logout.jsp'>로그아웃</a>");
	
	}
%>