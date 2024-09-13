package org.zerock.myapp.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;

@Log4j2
@EqualsAndHashCode

// 영속성 계층에 속한, VO/DTO 클래스를 만드는 아래와 같은 규칙은 
// 다름아닌, 자바빈즈 클래스(JavaBeans Class)를 선언하는 규칙과 같다!!
// 다름아닌, 자바빈즈 클래스에서 생성한 객체를 "자바빈" -> 그냥 "빈"이라 부른다
// 이렇게, 자바빈 객체의 조작은, 직접 필드를 통해서가 아니라,
// Getter/Setter 메소드에서, 접두사를 뺀 이름으로 나오는 소위 "프로퍼티(Property)"를
// 통해서 필드의 데이터를 조작하게 됩니다.

// Read-only Object / 영속성 계층에서 발생된 데이터를 보관하는 객체
// VO 패턴의 규칙
//
// (1) Serialiable Tag 인터페이스를 구현(선택)
// (2) 필드의 이름과 개수는 통상, 특정 테이블의 스키마와 동일하게 선언
//     - 테이블의 컬럼이름을 자바식별자 규칙에 맞게 선언
//     - 필드의 타입은, 컬럼의 타입에 대응되는 자바타입으로 선언
//	   - 테이블 스키마대로 모든 컬럼을 필드로 매핑합니다.
// (3) 모든 필드는 기본으로 private 접근제한자 적용(의닉화=캡슐화)
// (4) 생성자는 모든 필드를 매개변수로 가지는 매개변수 있는 생성자만
//     오직 선언해야 합니다(읽기전용객체로 만들어야 하기 때문에)
// (5) 2단계 중복판정 알고리즘에 대비해서 구현해야 된다
//	   (JCF 자료구조에 저장되는게 일반적이니까 중복판정이 가능해야 함)
// (6) VO/DTO 모두, 필드의 타입으로 절대 기본타입을 사용해서는 안된다.
//     참조타입을 써야 된다
public class EmpVOBeforeLombok implements Serializable{		// POJO : Plan Old Java Object.
	@Serial private static final long serialVersionUID = 1L;
	
	private Integer empno; 
	private String ename;
	private String job;
	private Integer mgr;
	
	private Date hireDate; 			// @til JAVA 7
//	private Timestamp hireDate;
	
//	@since 8 JoDa Time (LocalDate, LocalTime, LocalDateTime)
//	private LocalDateTime hireDate; 
	
	private Double sal;
	private Double comm;
	private Integer deptno;
	
	public EmpVOBeforeLombok(
			int empno, String ename, String job, Integer mgr, 
			Date hireDate, Double sal, Double comm, Integer deptno
	){
		log.trace("EmpVO( {}, {}, {}, {}, {}, {}, {}, {}) invoked."
				,empno, ename, job, mgr, hireDate, sal, comm, deptno);
		
		// 필드에 데이터를 넣어줌(단 한번만 초기화)
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.mgr = mgr;
		this.hireDate = hireDate;
		this.sal = sal;
		this.comm = comm;
		this.deptno = deptno;
	} // Constructor

	public int getEmpno() {
		log.trace("getEmpno() invoked.");
		return empno;
	} // getEmpno

	public String getEname() {
		log.trace("getEname() invoked.");
		return ename;
	} // getEname

	public String getJob() {
		log.trace("getJob() invoked.");
		return job;
	} // getJob

	public int getMgr() {
		log.trace("getMgr() invoked.");
		return mgr;
	} // getMgr

	public Date getHireDate() {
		log.trace("getHireDate() invoked.");
		return hireDate;
	} // getHireDate

	public Double getSal() {
		log.trace("getSal() invoked.");
		return sal;
	} // getSal

	public Double getComm() {
		log.trace("getComm() invoked.");
		return comm;
	} // getComm

	public int getDeptno() {
		log.trace("getDeptno() invoked.");
		return deptno;
	} // getDeptno
	
	
} // end class
