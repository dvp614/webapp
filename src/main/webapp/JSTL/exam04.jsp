<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3><%=request.getRequestURI() %></h3>
<hr>

<%
	int [] num = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	
	request.setAttribute("myArray", num);
%>

<c:forEach var="n" items="${myArray}" begin="0" end ='6'>
	|${n}|
</c:forEach>

<%
	ArrayList<String> list = new ArrayList<>();
	list.add("홍길동");
	list.add("이순신");
	list.add("유관순");
	request.setAttribute("name", list);
%>

<hr>

<c:forEach var="n" items="${name}">
${n}<br>
</c:forEach>
<hr>
<c:forEach var="counter" begin="0" end="9" step="1">
${counter}
</c:forEach>
<hr>

<%
	String str = "A,B,C,D";

	request.setAttribute("data", str);
%>

<c:forTokens var="token" items="${data }" delims=",">
TOKEN(${token})<br>
</c:forTokens>


	
	