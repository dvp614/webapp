package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.persistence.EmpDAO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/Hello")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		log.trace("service ({},{}) invoked.", request, response);
		
		try {
			EmpDAO dao = new EmpDAO();
			dao.selectAllEmployees(null);
			
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print("Hello World!!!!!!!!!");
			out.flush();
		}catch(Exception e) {
			throw new IOException(e);
		} // try-catch
		
	} // service

} // end class
