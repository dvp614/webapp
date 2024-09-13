<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
    
	첫 번째 LoginBean 정보는 ${__MODEL__[0].myUserid} , ${__MODEL__[0].myPasswd} <br>
	두 번째 LoginBean 정보는 ${__MODEL__[1].myUserid} , ${__MODEL__[1].myPasswd} <br><br>
	name : ${param.name}<br>
	age : ${param.age}
	