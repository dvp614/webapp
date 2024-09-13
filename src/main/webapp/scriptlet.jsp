<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"%>
    
<%  
	for(int counter = 0; counter <= 9; counter++) {
%>	
	
	<%= counter %> <br>
	
<%
	} // for
%>

<hr>

<% 
	int counter = 0;
	while(counter < 10){
		counter++;
%>
	<%= counter %> <br>
	
<%
	} // while
%>

<hr>
<% int number = 3; %>
<%
    switch (number) {
        case 1:
%>
            Number is 1<br>
<%
            break;
        case 2:
%>
            Number is 2<br>
<%
            break;
        case 3:
%>
            Number is 3<br>
<%
            break;
        default:
%>
            Number is not 1, 2, or 3<br>
<%
            break;
    } // switch
%>

<hr>

<% 
	List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
%>

<% 
	for(Integer num:list){
%>
	|<%= num %>|
<%
	}
%>
	
	
	
	
	
	
	
	