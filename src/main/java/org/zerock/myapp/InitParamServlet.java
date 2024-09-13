package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@Log4j2

//@WebServlet("/InitParam")
public class InitParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("sevice (res,req) invoked.");
		
		String dirPath = getInitParameter("dirPath");
		String userid = getInitParameter("userid");
		
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		
		out.print("<html><body>");
		out.print("디렉터리경로 : " + dirPath + "<br>");
		out.print("아이디 값 : " + userid + "<br>");
		out.print("</body></html>");
	} // service
	
} // end class
