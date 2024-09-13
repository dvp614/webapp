<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page 
	import="java.util.Date"
	import="java.util.Calendar"
	import="java.util.List"
%>
	
<%
	Date d = new Date();
%>

<h1>현재 날짜 출력 실습</h1>
<h2>현재 날짜 :  <%= d %></h2>
<h2>리터럴 : 	 <%= true %></h2> 
<h2>연산식 : 	 <%= 100+300 %></h2>
<h2>메소드호출 : <%= mul(100,200) %></h2>

<%!
	public int mul(int number1, int number2){
		return number1 * number2;
	} // mul
%>





