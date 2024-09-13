package org.zerock.myapp.security.auth;

import java.io.IOException;
import java.io.Serial;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.zerock.myapp.security.service.AuthenticateService;
import org.zerock.myapp.security.util.SharedAttributes;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
@WebServlet("/authenticate")
public class AuthenticateServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		try {	// 보안의 첫번째 단계 : 인증 확인
			// 핵심로직 : 금고상자(=Session Scope)안에, 신원정보(Credential)이 있는지
			HttpSession session = req.getSession();
			
			AuthenticateService service = new AuthenticateService();
			boolean isAuthenticated = service.isAuthenticationPermitted(session);
			
			// 인증확인 결과를, 포워드시킨 이전 서블릿에
			// Request Scope의 공유속성으로 되돌려 주자.
			req.setAttribute(SharedAttributes.IS_AUTHENTICATED, isAuthenticated);
			res.sendRedirect("/login");
		}catch(Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class
