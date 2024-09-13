<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<p>Declaration 태그를 이용한  JSP Life Cycle 메소드</p>

<%!
	private String initMesg = "jspInit 메소드";
	private String delMesg="jspDestroy 메소드";
	
	public static final double PI = 3.14159;
	
	public void jspInit(){
		System.out.println(">>>> " + initMesg + " <<<<<");
		System.out.println("\t+ initMesg : " + initMesg);
		System.out.println("\t+ PI : " + PI);
	} // jspInit
	
	public void jspDestroy(){
		System.out.println(">>>> " + delMesg + " <<<<<");
		System.out.println("\t+ delMesg : " + delMesg);
	} // jspDestroy
	
%>