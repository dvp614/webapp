package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;

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

@WebServlet("/D")
public class DServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		try {
			// 계좌이체 - (4) 계좌이체 결과를 응답으로 전송
			log.info("\t+ (4) 계좌이체 결과를 응답으로 전송");
			
			
			// 계주경기에서, 마지막 계주선수이기때문에
			// 더이상 요청 포워딩 하지않고 아래의 응답코드만 수행
			
			@Cleanup PrintWriter out = res.getWriter();
			out.println("/D");
			
			
			out.flush();
		} catch (Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class
