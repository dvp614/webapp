package org.zerock.myapp.security.auth;

import java.io.IOException;
import java.io.Serial;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.zerock.myapp.security.domain.Credential;
import org.zerock.myapp.security.domain.LoginDTO;
import org.zerock.myapp.security.domain.UserVO;
import org.zerock.myapp.security.service.SignInService;
import org.zerock.myapp.security.util.SharedAttributes;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@WebServlet("/SignIn")
public class SignInServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		try {	// 로그인 처리(인증처리) 수행
				// 비지니스(=서비스) 계층의 서비스 객체생성 및
				// 인증처리 메소드 호출 with DTO
			
//			Step 1. 로그인 전송파라미터 획득
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
//			Step 2. Step1에서 얻어낸 모든 전송파라미터를 저장하는 DTO 생성
			LoginDTO dto = new LoginDTO();
			dto.setUsername(username);
			dto.setPassword(password);
			
//			Step 3. 비지니스(=서비스) 계층의 메소드 호출 with DTO
			SignInService service = new SignInService();
			boolean isAuthenticated = service.loginProcess(dto);
			
//			Step 4. 인증에 성공했으면, Session Scope(=브라우저마다 하나씩 만들어지는 금고상자)안에,
//			        인증정보(=Credential)를 생성해서, Session Scope에 공유속성으로 저장
			if(isAuthenticated) {	// 인증성공시에
				// 인증성공한 사용자 정보(=Credential) 객체 생성
				// Session Scope(=금고상자)에 공유속성으로 저장
				UserVO vo = service.fineByUserName(dto);
				Credential credential = new Credential(dto.getUsername(), vo.getRole(), password);
				
				HttpSession session = req.getSession(false);
				if(session != null) {
					session.setAttribute(SharedAttributes.CREDENTIAL, credential);
					
				} else {	// 금고상가 없다면 -> 부적절한 상태
					throw new IllegalStateException("No Session Scope");
				} // if-else
				
				//로그인 처리 성공완료했으면, 메인화면으로 이동
				res.sendRedirect("/main");
			} else {				// 인증실패시에 => 다시 로그인 화면으로 강제 이동
				// Re-direction을 이용해서, 강제로 화면전환시킴
				res.sendRedirect("/login"); // 로그인 화면으로 이동
			} // if-else
			
		}catch (Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class
