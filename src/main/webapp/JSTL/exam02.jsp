<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3><%=request.getRequestURI() %></h3>
<hr>

1. ${__PAGE__}<br>
2. ${__REQUEST__}<br>
3. ${__SESSION__}<br>
4. ${__APPLICATION__}<br>
 
