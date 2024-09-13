package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.domain.EmpDTO;
import org.zerock.myapp.persistence.EmpDAO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@WebServlet({"/EmpSelectDAO", "/List", "/Create", "/Read", "/Update", "/Delete"})
public class EmpSelectDAOServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		log.trace("service (req,res) invoked.");
		// 전체사원목록을 만들어 반환하는 요청을 처리합니다.
		try {
			// 요청을 처리할 비지니스 로직 수행
//			EmpDAO dao = new EmpDAO();		// 영속성 계층의 DAO를 이용해서 DB작업수행
//			List<EmpVO> list = dao.selectAllEmployees();
//			list.forEach(log::info); // 메소드참조 이용해서, 리스트의 모든요소 출력
			
			EmpDTO dto = new EmpDTO();
			
			Enumeration<String> emu = req.getParameterNames();
			while(emu.hasMoreElements()) {
				String paramName = emu.nextElement();
				String paramValue = req.getParameter(paramName);
				
				switch(paramName){
					case "empno" : 		dto.setEmpno(Integer.valueOf(paramValue)); break;
					case "ename" :		dto.setEname(paramValue); break;
					case "job" :		dto.setJob(paramValue); break;
					case "mgr" :		dto.setMgr(Integer.valueOf(paramValue)); break;
					case "hireDate" :	
						DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
						dto.setHireDate(formatter.parse(paramValue));
						break;
					case "sal" :		dto.setSal(Double.valueOf(paramValue)); break;
					case "comm" :		dto.setComm(Double.valueOf(paramValue)); break;
					case "deptno" :		dto.setDeptno(Integer.valueOf(paramValue)); break;
					
					default:
				} // switch
				log.info("\t+ EmpDTO : {}", dto);
				
			} // while
			
			Object result = null;
			
			EmpDAO dao = new EmpDAO();
			
			String requestURI = req.getRequestURI();
			
			if("/List".equals(requestURI)) {
				result = dao.selectAllEmployees(dto);
			} else if("/Create".equals(requestURI)) {
				result = dao.insertEmployees(dto);
			} else if("/Read".equals(requestURI)) {
				result = dao.selectEmployees(dto);
			} else if("/Update".equals(requestURI)) {
				result = dao.updateEmployees(dto);
			} else if("/Delete".equals(requestURI)) {
				result = dao.deleteEmployees(dto);
			} // if-else
			
			// 응답처리
			res.setCharacterEncoding("utf8");
			res.setContentType("text/html; charset=utf8");
			
			PrintWriter out = res.getWriter();
			out.println(result);
//			// 1st. method
//			for(EmpVO vo : list) {
//				out.println(vo + "<br>");
//			} // enhanced for
			
			// 2nd. method : forEach 메소드 & 람다식 이용
//			list.forEach( vo -> out.println(vo + "<br>") );
			
			// 3rd. method : 메소드 참조 이용
//			list.forEach(out::println);

			out.flush();
		}catch(Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class
