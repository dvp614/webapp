package org.zerock.myapp.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import lombok.Value;

@Value
public class EmpVO implements Serializable{		// POJO : Plan Old Java Object.
	@Serial private static final long serialVersionUID = 1L;
	
	private Integer empno;
	private String 	ename;
	private String 	job;
	private Integer mgr;
	private Date 	hireDate;
	private Double 	sal;
	private Double 	comm;
	private Integer deptno;
	
} // end class
