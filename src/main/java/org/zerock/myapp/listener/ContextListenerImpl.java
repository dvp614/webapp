package org.zerock.myapp.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import lombok.extern.log4j.Log4j2;

@Log4j2
//@NoArgsConstructor

@WebListener
public class ContextListenerImpl implements ServletContextListener {
	
	
	public ContextListenerImpl() {
		log.trace("ContextListenerImpl() invoked.");
	} // Default Constructor

	// 1. 우리가 WAS에 배포한, 웹어플리케이션이 초기화될 때(= 서비스를 제공할 수 있는 상태로 되었을 떄)
	//    자동으로 callback(by servlet container)되는 메소드
	@Override
	public void contextInitialized(ServletContextEvent event)  { 
		log.trace("contextDestroyed({}) invoked.", event);
	} // contextInitialized
	
	// 2. 우리가 WAS에 배포한, 웹어플리케이션이 파괴될 때,
	//    자동으로 callback(by servlet container)되는 메소드
	@Override
	public void contextDestroyed(ServletContextEvent event)  { 
		log.trace("contextDestroyed({}) invoked.", event);
	} // contextDestroyed
	
} // end class
