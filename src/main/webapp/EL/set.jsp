<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="org.zerock.myapp.persistences.LoginBean"
    import="java.util.*"%>
    
<% 
	LoginBean oneBean = new LoginBean();
	oneBean.setMyUserid("aaa");
	oneBean.setMyPasswd("1234");
	
	LoginBean twoBean = new LoginBean();
	twoBean.setMyUserid("bbb");
	twoBean.setMyPasswd("2222");
	
	ArrayList<LoginBean> list = new ArrayList<>();
	list.add(oneBean);
	list.add(twoBean);
	
	request.setAttribute("__MODEL__", list );
%>

<jsp:forward page ="/EL/get.jsp">
	<jsp:param name = "name" value="Yoon"/>
	<jsp:param name = "age" value="23"/>
</jsp:forward>