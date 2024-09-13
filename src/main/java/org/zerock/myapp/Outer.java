package org.zerock.myapp;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Outer {

	public static void main(String...args) {
		Person person = new Person();
		
		String name = person.getN();
		int age = person.getA();
		
		log.trace("\t+ name : {}", name);
		log.trace("\t+ age : {}", age);
	} // main
	
} // end class
