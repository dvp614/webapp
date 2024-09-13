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

@WebServlet("/CartSaveCookie")
public class CartSaveCookieServlet extends HttpServlet {
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
			String product = req.getParameter("product");
			
			Cookie [] cookies = req.getCookies();
			Cookie c = null;
			
			if(cookies == null || cookies.length == 0) {
				c = new Cookie("product", product);
			}else {
				c = new Cookie("product" + (cookies.length+1), product);
			} // if-else
			
			c.setMaxAge(60*60);
			res.addCookie(c);
			
			out.println("<html><body>");
			out.println("Product 추가" + "<br>");
			out.println("<a href='CartBasketCookie'>장바구니 보기</a>");
			out.println("</html></body>");
			out.flush();
			
		}catch(Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class
