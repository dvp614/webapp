package org.zerock.myapp.domain;
import java.io.Serial;
import java.io.Serializable;

import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class LoginBean implements Serializable{
	@Serial private static final long serialVersionUID = 1L;
	
	private String userid;
	private String passwd;

	public void setMyPasswd(String myPasswd) {
		this.passwd = myPasswd;
	} // setMyPasswd
	
	public void setMyUserid(String myUserid) {
		this.userid = myUserid;
	} // setMyUserid
	
	public String getMyUserid() {
		return this.userid;
	} // getMyUserid
	
	public String getMyPasswd() {
		return this.passwd;
	} // getMyPasswd
} // end class
