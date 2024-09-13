package org.zerock.myapp.security.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.zerock.myapp.security.domain.UserVO;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

@Log4j2
//@NoArgsConstructor
public class UserDAO{
	private DataSource dataSource;
	
	
	public UserDAO() throws NamingException {
		log.trace("Default Constructor() invoked.");
		Context ctx = new InitialContext();
		
		Object foundObj = ctx.lookup("java:comp/env/jdbc/Oracle21c");
		log.info("\t+ foundObj : {}", foundObj);
		
		Objects.requireNonNull(foundObj);
		this.dataSource = (javax.sql.DataSource) foundObj;
	} // Default Constructor
	
	public UserVO selectByUserName(String username) throws Exception {
        log.trace("selectByUserName({}) invoked. " );
        Objects.requireNonNull(this.dataSource);
        
        @Cleanup Connection conn = this.dataSource.getConnection();
        log.info("\t+ conn: {}", conn);
        
        String sql = "SELECT * FROM t_user WHERE username = ?";
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        
        pstmt.setString(1, username);
        
        @Cleanup ResultSet rs = pstmt.executeQuery();      // DQL
        
        if(rs.next()) {   // 각 행마다, 모든 컬럼의 값을 추출
           String 	password 		= rs.getString("PASSWORD");
           String 	role 			= rs.getString("ROLE");
           
           // VO 객체 생성
           return new UserVO(username, password, role);
           
        } //if
              
        return null; // NO found.
     } // selectByUserName
	
} // end class
