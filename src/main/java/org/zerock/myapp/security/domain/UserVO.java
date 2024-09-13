package org.zerock.myapp.security.domain;

import java.io.Serial;
import java.io.Serializable;

import lombok.Value;

@Value
// Session Scope(=금고상자)안에 넣음,
// 인증에 성공한 사용자정보는 가지고 있는 객체이다.
// 이를 보안에서는
public class UserVO implements Serializable{
	@Serial private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String role;
} // end class
