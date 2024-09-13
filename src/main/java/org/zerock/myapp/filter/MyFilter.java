package org.zerock.myapp.filter;

import java.io.IOException;
import java.io.Serial;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

//@WebFilter("/*")
public class MyFilter 
	extends HttpFilter 
	implements Filter {
	@Serial private static final long serialVersionUID = 1L;

//	public MyFilter() {
//		super();
//		log.trace("MyFilter() invoked.");
//	} // default Constructor
	
	
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		log.trace("init({}) invoked.", fConfig);
	} // init
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) 
			throws IOException, ServletException {
		log.trace("doFilter (req, res, chain : {}) invoked.", chain);
		//------------------------------------------------------------------
		// 요청을 필터링 하는 전처리(Pre-processing) 코드는 여기에 넣으세요.
		//------------------------------------------------------------------
		log.info("\t (1) Pre-processing");
		
		req.setCharacterEncoding("utf8"); // 전처리작업으로, 한글처리 수행
		
		// 만일 여기서 응답처리를 한다면, 더이상 그 다음 필터로 체인을 따라
		// 요청/응답을 넘겨줄 필요가 없다 때문에 아래의 기계적인 코드는
		// 수행하지 않습니다.
		
		// 아래코드는, 필터체인을 따라서, 서블릿컨테이너가 매개변수에 넘겨준
		// 요청/응답 객체를 모두 함께 전달합니다.
		// 이렇게 체인을 따라서 전달하다보면, 결국은 최종 요청처리 컴포넌트(=서블릿)
		// 까지 요청/응답객체가 전달되어 요청이 처리되고, 응답도 만들어지죠.
		// 하지만, 응답은 필터에서 보내버릴 수도 있습니다.(서블릿으로 요청을 넘기지 않고)
		// 왜? 도저히 처리불가능한 형태로 온 요청이라면, 필터에서 응답을 만들어 주고
		// 서블릿 컴포넌트로 요청을 넘기지 않고 바로 끝내버릴 수도 있는 겁니다.
		chain.doFilter(req, res);
		
		//------------------------------------------------------------------
		// 응답을 필터링 하는 후처리(Post-processing) 코드는 여기에 넣으세요.
		//------------------------------------------------------------------
		log.info("\t (2) Post-processing");
		
		res.setContentType("text/html; charset=utf8");
		
	} // doFilter

	@Override
	public void destroy() {
		log.trace("destroy() invoked.");
	} // destroy

} // end class
