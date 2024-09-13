package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@WebServlet(
		description = "설명", 
		urlPatterns = {
				"/1", 
				"/2", 
				"/3" 
		})
public class MappingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("service({},{})", req, res);
		
		res.setCharacterEncoding("UTF-8");
		
		PrintWriter out = res.getWriter();
		out.println("MappingServlet's Response");
		out.flush();
	} // service

} // end class
