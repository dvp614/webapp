package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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

@WebServlet("/Response2")
public class ex3_5 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@PostConstruct
	void postConstruct() {
		log.trace("postConstruct() invoked.");
	} // postConstruct
	
	@PreDestroy
	void preDestroy() {
		log.trace("preDestroy() invoked.");
	} // preDestroy
	
	//---------------
	
	@Override
	public void init() throws ServletException {
		log.trace("init() invoked.");
	} // init
	
	@Override
	public void destroy() {
		log.trace("destroy() invoked.");
	} // destroy

	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("service ({}, {}) invoked.", req, res);
		
		// Step1. Body 안에 넣은 데이터가 어떤 종류의 컨텐츠인지를 설정하라!
		res.setContentType("text/html"); // MIME TYPE를 지정합니다.
		res.setCharacterEncoding("UTF-8");
		
		// Step2. 응답매개변수에 서블릿 컨테이너가 준 객체의 메소드를 통해, out 객체 획득
		@Cleanup PrintWriter out = res.getWriter();
	
		// Step3. out.println(String) 메소드가 호출될 때마다, HTTP response 메세지의
		// Body( = Payload)에 출력되게 됩니다.
		out.println("<html><body>");
		out.println("ResponseServlet 요청성공");
		out.println("</body></html>");
		
		// Step4.
		// 출력스트림에 잔류하는 모든 바이트(데이터)를 강제로 출력하라
		out.flush();
	} // service

} // end class
