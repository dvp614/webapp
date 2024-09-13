package org.zerock.myapp.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@WebListener
public class TTT implements ServletContextAttributeListener {

	
    public void attributeAdded(ServletContextAttributeEvent event)  { 
         log.trace("attributeAdded({}) invoked.", event);
    } // attributeAdded

    public void attributeRemoved(ServletContextAttributeEvent event)  { 
         log.trace("attributeAdded({}) invoked.", event);
    } // attributeRemoved

    public void attributeReplaced(ServletContextAttributeEvent event)  { 
         log.trace("attributeReplaced({}) invoked.", event);
    } // attributeReplaced
	
} // end class
