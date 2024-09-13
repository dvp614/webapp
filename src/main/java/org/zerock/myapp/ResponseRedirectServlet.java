package org.zerock.myapp;

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


// 첫번째 RequestServlet으로부터 요청처리를 위임받는 서블릿 컴포넌트
@WebServlet("/ResponseRedirect")
public class ResponseRedirectServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		try {
			// Step 1. 첫번째 서블릿이 바인딩한 공유속성을 Request Scope에서 획득
			String key1 = (String) req.getAttribute("KEY1");
			String key2 = (String) req.getAttribute("KEY2");
			
			// Step 2. Step 1.에서 획득한 공유속성으로 응답생성/전송
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = res.getWriter();
			
			out.println("1. key1: %s".formatted(key1));
			out.println("2. key2: %s".formatted(key2));
			
			out.flush();
			// Step 3. Step 2.가 수행된 이후에도 될까 실험
			log.info("Done.");
			
		} catch (Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class
