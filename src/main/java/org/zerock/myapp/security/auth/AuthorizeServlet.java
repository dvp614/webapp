package org.zerock.myapp.security.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
@WebServlet("/authorize")
public class AuthorizeServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		try {
			res.setCharacterEncoding("utf8");
			res.setContentType("text/html; charset=utf8");
			
			PrintWriter out = res.getWriter();
			out.println("<h3>/authorize</h3>");
			out.println("<hr>");
			out.flush();
		}catch(Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class
