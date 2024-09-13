package org.zerock.myapp.security.service;

import javax.servlet.http.HttpSession;

import org.zerock.myapp.security.domain.Credential;
import org.zerock.myapp.security.util.SharedAttributes;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
public class AuthenticateService {
	// 핵심로직 : 인증확인 로직 수행
	public boolean isAuthenticationPermitted(HttpSession session){
		log.trace("isAuthenticationPermitted({}) invoked.", session);
		// 인증이 되었는가 : true, 인증이 아직 안되었는가 : false
			Object obj = session.getAttribute(SharedAttributes.CREDENTIAL);

			if (obj == null) { // 금고상자안에 신원정보가 없다
				// 결론 : 접근불가
				return false;
			} else { // 금고상자안에 비록 지정된 이름으로 어떤 객체가 들어있기는 하나,
						// 타입까지 Credential 객체인지 확인해야 한다.
				if (obj.getClass().getSimpleName().equals("Credential")) {
					// 신원정보가 맞을 때
					// 신원정보로 유효한 사용자이름과 권한이 존재하는지도 확인
					// 해야합니다.(보안은 최대한 철저히 검사해야함)
					if (obj instanceof Credential credential) {
						String username = credential.getUsername();
						String role = credential.getRole();

						if ((username != null && !"".equals(username)) 
								&& (role != null && !"".equals(role))) { // 결론 : 접근가능
							return true;
						} else { // 결론 : 접근불가
							return false;
						} // if-else
					} else { // 금고상자안에 맞은 공유속성이름으로 객체는 존재하되
								// 엉뚱한 객체가 들어있고, 신원정보 아님
								// 결론 : 접근불가
						return false;
					}
				} else { // 신원정보가 아닐 때
					return false;
				} // if-else
			} // if-else
	} // isAuthenticationPermitted
} // end class
