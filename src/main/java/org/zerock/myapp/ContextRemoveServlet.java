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

@WebServlet("/ContextRemove")
public class ContextRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("service (res, req) invoked.");
		
		try {
			req.setCharacterEncoding("UTF-8");
			
			// 1-1. 공유속성 언바인딩
			ServletContext sc = this.getServletContext();
			sc.removeAttribute("NAME");
			sc.removeAttribute("AGE");
			
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = res.getWriter();
			
			out.println("1. NAME 공유속성 삭제 성공");
			out.println("2. AGE 공유속성 삭제 성공");
			
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException();
		} // try-catch
	} // service
	
} // end class
