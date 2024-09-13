package org.zerock.myapp.domain;

import java.util.Date;

// lombok의 @Value 어노테이션이 자동으로 만들어주는
// 멤버와 동일한 멤버를 가지고 있습니다.
public record EmpRecVO(
	// 읽기전용 각 필드에는 접근제한자(Access Modifier)를
	// 붙일 수 없지만, 안붙여도 자동으로 private으로 은닉화(캡슐화) 시켜 버린다.
	Integer empno, String ename, String job, Integer mgr, 
	Date hireDate, Double sal, Double comm, Integer deptno
) { } // end record
