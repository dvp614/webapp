package org.zerock.myapp;

import java.io.IOException;
import java.io.Serial;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
@WebServlet("/Response")
public class ResponseServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("service (req, res) invoked.");
		
		try {
			// Step1. Request Scope 에 2개의 공유속성 바인딩
			// 참고) 공유속성의 이름은 String 타입이고(고정)
			//       공유속성의 값은 그 어떤 참조타입의 객체 모두 가능하다
			req.setAttribute("KEY1", "VALUE1");
			req.setAttribute("KEY2", "VALUE2");
			
			// Step2. 두번째 서블릿에 요청 위임
			RequestDispatcher dispatcher = 
					// 아래 메소드의 매개변수의 문자열 값은 아래를 의미합니다.
					// 요청을 위임받을 서블릿 컴포넌트의 매핑 URI를 의미합니다.
					req.getRequestDispatcher("/Response");
			// 완전한 위임을 해야하니까, 첫번째 서블릿이 받은 요청/응답 객체를
			// 그대로 전달하면서 위임시켜야 합니다.
			dispatcher.forward(req, res);
			log.info("\t+ Dispatch Done.");
		}catch(Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class
