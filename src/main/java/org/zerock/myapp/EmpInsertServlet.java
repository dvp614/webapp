package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

@WebServlet(description = "//insert", urlPatterns = { "/EmpInsert" })
public class EmpInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/Oracle21c")
	private DataSource dataSource;
	
	@PostConstruct
	   void postConstruct() {
	      log.trace("postConstruct() invoked.");
	      
	      Objects.requireNonNull(this.dataSource);
	   }

	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("service (req, res) invoked.");
		
		try {
			String empId = req.getParameter("emp_id");
			String ename = req.getParameter("ename");
			String salary = req.getParameter("salary");
			String depart = req.getParameter("depart");
			
			@Cleanup Connection conn = this.dataSource.getConnection();
			log.info("\t+ conn : {}, type : {}", conn, conn.getClass().getName());
			
			String sql = "INSERT INTO emp(EMPNO, ENAME, SAL, DEPTNO) VALUES (?, ?, ?, ?)";
			@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(empId));
			pstmt.setString(2, ename);
			pstmt.setDouble(3, Double.parseDouble(salary));
			pstmt.setInt(4, Integer.parseInt(depart));
			
			int affectedRows = pstmt.executeUpdate();
			log.info("\t+ affectedRows : {}", affectedRows);
			
			res.setCharacterEncoding("utf8");
			res.setContentType("text/html; charset=utf8");
			
			@Cleanup PrintWriter out = res.getWriter();
			out.println("<h1>affectedRows : %d</h1>" .formatted(affectedRows));
			
			out.flush();
		} catch(Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class
