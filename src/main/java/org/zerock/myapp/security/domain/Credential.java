package org.zerock.myapp.security.domain;

import lombok.Value;


@Value
public class Credential{
	private String username;
	private String password;
	private String role;

} // end class
