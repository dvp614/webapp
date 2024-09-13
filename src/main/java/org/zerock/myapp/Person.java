package org.zerock.myapp;

import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@ToString
@Log4j2
public class Person {
	private String name = "윤선기";
	private int age = 23;
	
	public String getN() {
		return this.name;
	} // getName
	
	public int getA() {
		return this.age;
	} // getAge
	
	public static void main(String...args) {
		Person person = new Person();
		
		log.trace("\t+ name : {}", person.getN());
		log.trace("\t+ age : {}", person.getA());
		log.trace("\t+ name : {}", person.name);
		log.trace("\t+ age : {}", person.age);
	} // main
	
} // end class
