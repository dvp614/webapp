package org.zerock.myapp.security.service;

import java.util.Objects;

import org.zerock.myapp.security.dao.UserDAO;
import org.zerock.myapp.security.domain.LoginDTO;
import org.zerock.myapp.security.domain.UserVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
public class SignInService {
	// DAO를 이용해서, 특정 사용자 정보를 VO받아와서,
	// UI에서 입력한 아이디/암호와 일치하는지를 검증

	// 로그인처리(=인증처리)수행
	public boolean loginProcess(LoginDTO dto) throws Exception {
		log.trace("singIn({}) invoked.", dto);
		
		UserDAO dao = new UserDAO();
		
		UserVO vo = dao.selectByUserName(dto.getUsername());
		Objects.requireNonNull(vo);
		
		if(dto.getPassword().equals(vo.getPassword())) { // 로그인정보가 일치
			return true;
		} else {
			return false;
		} // if-else
		
	} // signIn
	
	public UserVO fineByUserName(LoginDTO dto) throws Exception{
		log.trace("fineByUserName({}) invoked.", dto);
		UserDAO dao = new UserDAO();
		UserVO vo = dao.selectByUserName(dto.getUsername());
		
		return vo;
	} // fineByUserName
	
} // end class
