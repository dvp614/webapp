package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@WebServlet("/EmpSelect")
public class EmpSelectServlet extends HttpServlet {
	@Serial
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/Oracle21c") // 우리가 원하는 리소스의 주소를 여기에 넣어달라.
	private DataSource dataSource; // 데이터소스객체의 자동주입(injection) 되었는지 확인

	// 전처리(pre-processing)
	@PostConstruct
	void postConstruct() { // 필드에 데이터소스가 객체가 잘 주입(injection) 되었는지 확인
		log.trace("postConstruct() invoked.");

		Objects.requireNonNull(this.dataSource);
		log.info("\t+ this.dataSource: {}", this.dataSource);
	}// postConstruct

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("serivce(req, res) invoked.");

		try {
			// 필드에 저장된 커넥션 풀에서 한개의 가용한 커넥션을 대여받음.
			@Cleanup
			Connection conn = this.dataSource.getConnection();
			log.info("\t+ conn: {}", conn);

			res.setCharacterEncoding("utf8");
			res.setContentType("text/html; charset=utf8");
//			res.setContentType("application/octet-stream; charset=utf8");
			// download 를 자동 발생 시키는 마임타입. octet-stream 바이트기반 스트림.

			PrintWriter out = res.getWriter();

			String sql = "SELECT empno, ename, sal, deptno FROM emp";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			try (conn; pstmt; rs) { // 2nd. method: 반납
				while (rs.next()) {
					String empno = rs.getString("empno");
					String ename = rs.getString("ename");
					String sal = rs.getString("sal");
					String deptno = rs.getString("deptno");

					out.println("<h2>%s\t%s\t%s\t%s</h2>".formatted(empno, ename, sal, deptno));
				}

			} finally {
				out.flush();
			}

//         Statement stmt;
//         ResultSet rs;         

			// 대여받은 커넥션을 다 목적대로 사용이 끝나면, Connection 객체는 자원객체이기 때문에
			// 반드시 반납 해줘야 합니다.
//         conn.close(); //반납 수행

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		} // try-catch

	}// service

}// end class