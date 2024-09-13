package org.zerock.myapp;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@WebServlet("/ContextSet")
public class ContextSetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("service (res, req) invoked.");
		
		try {
			req.setCharacterEncoding("utf8");
			
			// 1-1. 공유속성(= 공유영역에 공유할 데이터) 생성
			String name = "Yoon";
			int age = 23;
			
			// 1-2. 공유영역(Apllication Scope)에 공유속성 바인딩(즉, 올려놓아요)
			ServletContext sc = this.getServletContext();
			sc.setAttribute("NAME", name);		// 이름 공유속성 바인딩
			sc.setAttribute("AGE", age);		// 나이 공유속성 바인딩
			
			res.setCharacterEncoding("utf8");
			res.setContentType("text/html; charset=utf8");
		}catch (Exception e) {
			e.printStackTrace();
			throw new IOException();
		}
	} // service

} // end class
