package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@WebServlet(
		urlPatterns = { "/InitParamAnno" }, 
		initParams = { 
			@WebInitParam(name = "PARAM1", value = "VALUE1"), 
			@WebInitParam(name = "PARAM2", value = "VALUE2")
		})
public class InitParamAnnoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String param1;
	private String param2;
	
	// 주의사항 : 각 서블릿의 등록된 초기화 파라미터는, 아래의 init callback에서
	//			  사용하는게 맞다.
	@Override
	public void init(ServletConfig config) throws ServletException {
		log.trace("init({}) invoked.", config);
		
		log.info("\t+ PARAM1 : {}", config.getInitParameter("PARAM1"));
		log.info("\t+ PARAM2 : {}", config.getInitParameter("PARAM2"));
		
		this.param1 = config.getInitParameter("PARAM1");
		this.param2 = config.getInitParameter("PARAM2");
	} // init
	
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("service (res,req) invoked.");
		
		try {
			// 요청처리
			req.setCharacterEncoding("UTF-8");
			
			// 주의사항 : 각 서블릿의 등록된 초기화 파라미터는, 아래의 init callback에서
			//			  사용하는게 맞다.
//			log.info("\t+ PARAM1 : {}", this.getInitParameter("PARAM1"));
//			log.info("\t+ PARAM2 : {}", this.getInitParameter("PARAM2"));
			
			log.info("\t+ PARAM1 : {}", this.param1);
			log.info("\t+ PARAM1 : {}", this.param2);
			
			// 응답처리
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = res.getWriter();
			
			out.print("<html><body>");
			out.print("디렉터리경로 : " + this.param1 + "<br>");
			out.print("아이디 값 : " + this.param2 + "<br>");
			out.print("</body></html>");
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new IOException();
		} // try-catch
		
	} // service

} // end class
