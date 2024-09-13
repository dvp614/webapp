package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@WebServlet("/CartBasket")
public class CartBasketServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;
	
	// 현재 브라우저에 이름(=세션ID)으로만 열수있는 금고상자(=Session Scope)에
	// 들어있는 물품목록을 출력하는 역할 수행
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		try {
			// Step1. 현재요청을 보낸 웹브라우저의 세션ID로 접근 가능한 금고상자에
			// 접근하기 위해서, HttpSession 객체를 request객체로부터 얻어냄
//			HttpSession session = req.getSession();			// XX
			HttpSession session = req.getSession(false);	// OK, 역할상 이게 맞음
			
			@SuppressWarnings("unchecked")
			List<String> basket = (List<String>) session.getAttribute(CartSaveServlet.BASKET);
			
			// 응답처리
			res.setCharacterEncoding("utf8");
			res.setContentType("text/html; charset=utf8");
			
			PrintWriter out = res.getWriter();
			basket.forEach(product ->{
				out.println(product);
			}); // forEach
			
			out.flush();
		} catch (Exception e) {
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class
