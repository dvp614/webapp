package org.zerock.myapp.controller;

import java.io.IOException;
import java.io.Serial;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

// 역할 : FC 패턴의 역할
// (1) 집중된 용청마다, "요청식별"
// (2) 모든 전송파라미터 수집
// (3) (1)와 (2) 이용해서, 실제 각 요청을 누가(서비스계층의 특정 서비스)
//     처리할지 확인해서, 실제 요청처리를 위임(Delegation)시키는 역할
//@WebServlet("/*")
public class FrontControllerServlet extends HttpServlet {
	@Serial
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("service (req, res) invoked.");

		try {
			// 요청명 획득
			String command = req.getRequestURI();
			log.info("\t+ command : {}", command);

			// 모든 전송파라미터 수집
			// 모든 전송파라미터를 획득해서 자체 자료구조에 보관합니다.
//			Enumeration<String> emu = req.getParameterNames();
//			while (emu.hasMoreElements()) {
//				String paramName = emu.nextElement();
//				String paramValue = req.getParameter(paramName);
//			} // while
			
			// 개선 코드
			// Key   : 전송파라미터명
			// Value : 전송파라미터값(1개 이상의 값으로 전송 가능)
			Map<String, String[]> params = req.getParameterMap();
			params.forEach((key, value) ->{
				log.trace("forEach({}, {}) invoked.", key, value);
			}); // .forEach

			// Switch Expression 으로 요청식별코드 작성
			switch (command) {
				case "/insert"	-> {}
				case "/delete"	-> {}
				case "/select"	-> {}
				case "/list" 	-> {}
				default 		-> {}
			} // switch expression

		} catch (Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class
