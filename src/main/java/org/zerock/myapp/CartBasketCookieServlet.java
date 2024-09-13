package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@WebServlet("/CartBasketCookie")
public class CartBasketCookieServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;

	// 웹브라우저의 쿠키저장소를 이용해서, 장바구니를 저장하는 서블릿
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("service() invoked.");
		
		try {
			res.setCharacterEncoding("utf8");
			res.setContentType("text/html; charset=utf8");
			
			PrintWriter out = res.getWriter();
			
			out.println("<html><body>");
			out.println("장바구니 리스트" + "<br>");
			
			Cookie [] cookies = req.getCookies();
			
			if(cookies != null) {
				for(Cookie c : cookies) {
					out.println(c.getName() + " : " + c.getValue() + "<br>");
				} // enhanced for
			}else {
				out.println("장바구니비었음.");
			} // if-else
			
			out.println("<a href='product.html'>상품 선택 페이지</a>");
			out.println("<a href='CartDeleteCookie'>장바구니 비우기</a>");
			out.println("</html></body>");
			
			out.flush();
			
		}catch(Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class
