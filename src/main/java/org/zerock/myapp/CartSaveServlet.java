package org.zerock.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@WebServlet("/CartSave")
public class CartSaveServlet extends HttpServlet {
	@Serial private static final long serialVersionUID = 1L;
	public static final String BASKET = "_BASKET_";
	
	// 한개 이상의 물품명을 저장가능하도록 수정
	@SuppressWarnings("unchecked")
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		try {
			// Step 1. 현재 요청을 보낸 웹브라우저에 대해서,
			//		   이름(=세션ID)이 있으면 그 이름을 그대로 사용하고
			//		   만일 최초요청이라 없으면, 새로운 이름(=세션ID)을 만들어주자!
			
			// 세션ID가 없으면 만들고, 있으면 그대로 사용하자!!!
			HttpSession session = req.getSession();
			
			// Step 2. 전송파라미터의 값을 획득
			//		   0개 이상의 물품명을 저장가능한 리스트를 만들어 넣음
			Object obj = session.getAttribute(BASKET);
			log.info("\t+ obj : {}", obj);
//			String product = req.getParameter("product");	// BMW
			
			// Step 3. Session Scope(= 금고상자)에 저장 (SetAttribute)
//			session.setAttribute("_PRODUCT_", product); // 금고상자에 "BMW" 문자열 저장
			if(session.getAttribute(BASKET) == null) {
				List<String> basket = new ArrayList<>();		// 장바구니
				session.setAttribute(BASKET, basket);			
			} // if
			
			// Step 3-1. 전송파라미터로 온 물품명을 장바구니에 추가
			List<String> savedBasket = (List<String>) session.getAttribute(BASKET);
			
			String product = req.getParameter("product");
			savedBasket.add(product);
			
			savedBasket.forEach(log::info);
			
			// Step 4. Session Scope에 저장된 공유속성 확인/출력
//			product = (String) session.getAttribute("_PRODUCT_");
//			log.info("\t+ product : {}", product);
			
			// Step 5. Session Scope에 저장된 공유속성 삭제
//			session.removeAttribute("_PRODUCT_");
			
			// Step 6. Session Scope 파괴시킴
//			session.invalidate();
			
			// 응답처리
			res.setCharacterEncoding("utf8");
			res.setContentType("text/html; charset=utf8");
			
			@Cleanup PrintWriter out = res.getWriter();
			out.println("장바구니에 물품명(%s)이 잘 추가되었습니다.".formatted(product));
			out.flush();
		} catch (Exception e) {
						
			throw new ServletException(e);
		} // try-catch
	} // service

} // end class
