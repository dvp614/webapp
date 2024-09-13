package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;

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

@WebServlet("/ContextGet")
public class ContextGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("service (res, req) invoked.");
		
		try {
			req.setCharacterEncoding("UTF-8");
			
			ServletContext sc = this.getServletContext();
			
			String name = (String) sc.getAttribute("NAME");
			Integer age = (Integer) sc.getAttribute("AGE");
			
			// NAME 공유속성의 값을 변경
			sc.setAttribute("NAME", "Trinity"); // 공유속성의 값 변경(= Not un-binding)
			
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html; charset=UTF-8");
			
			log.info("\t+ 이름 : {}", name);
			log.info("\t+ 나이 : {}", age);
			
			PrintWriter out = res.getWriter();
			
			out.println("이름 : " + name);
			out.println("나이 : " + age);
			
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException();
		} // try-catch
	} // service

	
} // end class
