<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page 
	import="java.util.*"
%>
    
<%-- 
	현재시간이 오전 -> Good Morning
	현재시간이 오후 -> Good Aftern
--%>

<%--
 <% 
 	if (Calendar.getInstance().get(Calendar.AM_PM) == Calendar.AM) {
 %>
 	Good Morning
 <% 
 	} else { 
 %>
 	Good Afternoon
 <% 
 	}
  %>
--%>

<% 
	Calendar cal = Calendar.getInstance();
	int result = cal.get(Calendar.AM_PM);
%>
	현재 시간 : <%= cal.getTime() %> <br>
<%
	if(result == Calendar.AM){ 
%>	

		Good Morning
		
<% 
	} else{ 
%>

		Good Afternoon
		
<% 
	} // if-else 
%>

