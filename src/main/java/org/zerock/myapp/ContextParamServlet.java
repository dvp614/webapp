package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@WebServlet("/ContextParam")
public class ContextParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String param1;
	private String param2;
	
	public void init(ServletConfig config) throws ServletException {
		log.trace("init({}) invoked.", config);
		
		try {
			ServletContext sc = config.getServletContext();
			
			this.param1 = sc.getInitParameter("CONTEXT_PARAM1");
			this.param2 = sc.getInitParameter("CONTEXT_PARAM2");
			
			Objects.requireNonNull(this.param1);
			Objects.requireNonNull(this.param2);
			
			log.info("\t+ contextParam1 : {}", this.param1);
			log.info("\t+ contextParam2 : {}", this.param2);
		}catch (Exception e){
			e.printStackTrace();
			throw new ServletException(e);
		} // try-catch
	} // init

	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("service (res, req) invoked.");
		
		try {
			// 요청처리
			req.setCharacterEncoding("UTF-8");
			
			// 모든 웹컴포넌트에서 가져다 사용할 수 있는 공유되는 초기화 파라미터인
			// 컨텍스트 파라미터를 획득
//			ServletContext sc = this.getServletContext();
//			String contextParam1 = sc.getInitParameter("CONTEXT_PARAM1");
//			String contextParam2 = sc.getInitParameter("CONTEXT_PARAM2");
			
			Objects.requireNonNull(this.param1);
			Objects.requireNonNull(this.param2);
			
			log.info("\t+ contextParam1 : {}", this.param1);
			log.info("\t+ contextParam2 : {}", this.param2);
			
			// 응답처리
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = res.getWriter();
			
			out.flush();
		}catch (Exception e) {
			e.printStackTrace();
			throw new IOException();
		} // try-catch
	} // service

} // end class
