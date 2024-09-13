package org.zerock.myapp;

import java.io.IOException;
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

@WebServlet("/CreateNewSession")
public class CreateNewSessionServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		try {
			// 최초요청을 브라우저가 보내면, 서버는 브라우저와의 세션(추상화된 연결)을
			// 형성하기 위해서, 세션ID(브라우저의 이름)을 새로이 만들고, 이를
			// 응답메시지의 헤더에 담아서 브라우저로 전송 -> 브라우저는 자기의 이름(세션ID)
			// 를 브라우저의 "쿠키저장소"에 저장한다
			req.getSession(); // == req.getSession(true)
			
			
		} catch (Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class
