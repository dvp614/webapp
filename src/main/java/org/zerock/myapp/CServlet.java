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

@WebServlet("/C")
public class CServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		try {
			// ---------------------------------------------------
			// 요청처리 로직 분담
			// ---------------------------------------------------
			// 계좌이체 - (3) 계좌이체이력을 원장에 기록
			log.info("\t+ (3) 계좌이체이력을 원장에 기록");
						
			// ---------------------------------------------------
			// 요청 포워딩 수행
			// ---------------------------------------------------
			@Cleanup PrintWriter out = res.getWriter();
			out.println("/C");
						
//			out.flush();
			
			// ---------------------------------------------------
			// 응답메세지 바디에 출력
			// ---------------------------------------------------
			RequestDispatcher dispatcher = req.getRequestDispatcher("/D");
			dispatcher.forward(req, res);
			
			log.info("C");
		} catch (Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class
