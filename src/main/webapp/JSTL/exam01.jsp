<%@page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page
	import="org.zerock.myapp.persistences.LoginBean" %>
<%-- <jsp:useBean id="myBean" class="org.zerock.myapp.domain.LoginBean" /> --%>

<%
	LoginBean myBean = new LoginBean();
	
	// 이 현재 JSP파일의 수행이 끝날때까지만 휴요한 page scope에 공유속성으로 바인딩
	pageContext.setAttribute("myBean", myBean);
%>

<h3><%=request.getRequestURI() %></h3>
<hr>
<h4>JSTL Core 라이브러리 실습#1</h4>
<hr>

<c:set var="__PAGE__" 			value="VALUE_1" scope="page"/>
<c:set var="__REQUEST__" 		value="VALUE_2" scope="request"/>
<c:set var="__SESSION__" 		value="VALUE_3" scope="session"/>
<c:set var="__APPLICATION__" 	value="VALUE_4" scope="application"/>

<c:out value="Hello World"/><br><br>

\${__PAGE__} 		: <c:out value="${__PAGE__}"/><br>
\${__REQUEST__} 	: <c:out value="${__REQUEST__}"/><br>
\${__SESSION__} 	: <c:out value="${__SESSION__}"/><br>
\${__APPLICATION__} : <c:out value="${__APPLICATION__}"/><br><br>

\${__PAGE__} 		: ${__PAGE__}<br>
\${__REQUEST__} 	: ${__REQUEST__}<br>
\${__SESSION__} 	: ${__SESSION__}<br>
\${__APPLICATION__} : ${__APPLICATION__}<br>

<hr>
<h4>JSTL Core 라이브러리 실습#2</h4>    
<hr>

<c:set target="${myBean}" property="myUserid" value="inky4832"/>
UserId : ${myBean.myUserid }<br>
UserId : <c:out value="${myBean.myUserid }"/>    
    
<hr>
<h4>JSTL Core 라이브러리 실습#3</h4>    
<hr>

1. 삭제 전 : <c:out value="${__REQUEST__ }"/><br>

<c:remove var="__REQUEST__"/>

2. 삭제 후 : <c:out value="${__REQUEST__ }"/><br>
