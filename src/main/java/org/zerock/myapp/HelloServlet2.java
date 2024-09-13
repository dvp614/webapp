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

@WebServlet(name = "Myservlet", 
			urlPatterns = {
					"/Hello1",
					"/Hello2",
					"/myservlet/hello"
			})
public class HelloServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		log.trace("service({}, {}) invoked.", req, res);
		
		log.info("HelloServlet 요청");
		
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		out.println("Hello2!!!!");
		out.flush();
	} // service

} // end class
