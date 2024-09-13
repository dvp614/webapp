<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3><%=request.getRequestURI() %></h3>
<hr>
 
<c:set var="myColor" value="빨강" scope="request"/>

<c:if test="${myColor == '빨강'}">
색상은 빨강색이다.
</c:if>

<h1>JSTL Core 라이브러리실습2</h1>

<c:set var="grade" value="90" scope="session"/>

<c:choose>
	
	<c:when test="${ grade >= 90 }">
	${grade}
	학점은A 이다. 
	</c:when>
	
	<c:when test="${ grade >= 80}">
	${grade}
	학점은B 이다.
	</c:when>
	
	<c:when test="${ grade >= 70}">
	${grade}
	학점은C 이다.
	</c:when>
	
	<c:otherwise>
	학점은F 이다.
	</c:otherwise>

</c:choose>
