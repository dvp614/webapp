package org.zerock.myapp.security.ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.security.util.SharedAttributes;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@WebServlet("/main")
// 인증된 사용자가 보낸 요청을 처리하는 서블릿
public class MainServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		try {
			// 요청처리
//			Step 1. 인증이 된 브라우저의 요청인지 확인
//					이를 위해, 인증확인로직을 수행하는 /authenticate 으로 포워드 수행
			RequestDispatcher dispatcher = req.getRequestDispatcher("/authenticate");
			dispatcher.forward(req, res);
			
//			Step 2. 포워드된 서블릿의 수행결과를
//					Request Scope에 저장된 결과를 획득
			boolean isAuthenticated = (Boolean) req.getAttribute(SharedAttributes.IS_AUTHENTICATED);
			
//			Step 3. Step2의 인증확인결과에 따라,
//				    true인 경우 - 아래 화면을 만들어 보내주고
//				    false인 경우 - 강제로 로그인 화면으로 밀어버린다
			if(!isAuthenticated) {
				res.sendRedirect("/login");
				return;
			} // if
			
//			Step 4. Step3 에서 인증확인이 된 요청에 대해서는,
//			        아래의 응답처리로, main 화면을 응답으로 보여줍니다.
			res.setCharacterEncoding("utf8");
			res.setContentType("text/html; charset=utf8");
			
			@Cleanup PrintWriter out = res.getWriter();
			out.println("<h3>/main</h3>");
			out.println("<hr>");
			out.flush();
		}catch(Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class
