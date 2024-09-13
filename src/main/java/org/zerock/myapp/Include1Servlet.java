package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@WebServlet("/Include1")
public class Include1Servlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		try {
			// Step 1. 현재 서블릿의 응답생성
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html; charset=utf8");
			
			@Cleanup PrintWriter out = res.getWriter();
			out.println("Header<br>");
			
			// Step 2. 두번째 서블릿을 수행시켜 그 응답을 포함시키기 위해서
			//			Include 기법 수행
			RequestDispatcher dispatcher = 
					// targetURI에는, 응답으로 포함시킬 다른 컴포넌트의 매핑 URI 지정
					req.getRequestDispatcher("/Include2");
			
			dispatcher.include(req, res);
			
			
			// Step 3. 세번째 서블릿을 수행시켜 그 응답을 포함시키기 위해서
			//			Include 기법 수행
			RequestDispatcher dispatcher2 = 
					// targetURI에는, 응답으로 포함시킬 다른 컴포넌트의 매핑 URI 지정
					req.getRequestDispatcher("/Include3");
			
			dispatcher2.include(req, res);
			
		} catch (Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class
