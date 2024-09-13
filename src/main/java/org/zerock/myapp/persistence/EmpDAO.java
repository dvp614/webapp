package org.zerock.myapp.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.zerock.myapp.domain.EmpDTO;
import org.zerock.myapp.domain.EmpVO;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

@Log4j2
// 1. DAO는 영속성 계층의 패턴
// 2. 보통 인스턴스로 만들어서, 인스턴스 메소드를 호출하게 만듦
//	  (왜 Helper Class 처럼, 정적메소드로 제공하지 못하는가?
//	   트랜잭션 처리와 쓰레드 동기화처리 때문에 객체로 만들어 제공해야함)
// 3. 핵심은 메소드이다
// 4. 객체생성을 쉽게 하도록, 기본생성자를 제공하는게 원칙
public class EmpDAO { // POJO
	// 아래의 @Resource 어노테이션은, 마찬가지로, 서블릿 클랙스 내에서만
	// 정상작동하는 어노테이션 입니다. 때문에, 주입이 될 수가 없습니다.
	// 그래서 우리가 직접 데이터소스를 얻어내야 하는데 이때 사용되는
	// WAS의 표준기능으로 JNDI (Java Naming & Directory Service) 이용해서
	// 데이터소스를 비롯한 다양한 객체를 얻어낼 수가 있습니다.
//	@Resource(name = "jdbc/Oracle21c")	// XX -> JNDI 서비스를 이용해서 획득해야함
	private DataSource dataSource;

	public EmpDAO() throws NamingException{
		log.trace("Default Constructor invoked.");
		
		// JNDI 서비스를 이용해서, WAS가 설정에 따라 표준대로 만든
		// 커넥션풀의 주소를 얻어내어, 필드에 저장
		// JNDI -> Java Naming & Directory Service(= MS active directory)
		// 		   개념 : 마치 과수에 열리는 열매처럼, 어떠한 참조타입의 객체든
		//				  열매로서 과수나무에 매달아 놓음
		//				  그러면, 열매처럼 나무에 매달아 놓은 참조타입의 객체를
		//				  "이름"을 통해서 획득할 수 있다.
		
		// 아래 스텝대로 JNDI 서비스가 제공하는 API를 사용하시면 됩니다.
		// 최종목적 : 이름을 가지고 우리가 원하는 자원객체를 JNDI Tree에서 찾아서 획듯
		
		// Step1. JNDI Tree 에서 우선 접근가능해야 하니, JNDI Tree 접근가능객체를 획듯
		// 	      이게 바로 InitialContext (JNDI Tree 의 뿌리) 타입의 객체를 얻음.
		InitialContext ctx = new InitialContext();
		log.info("\t+ ctx : {}", ctx);
			
		// Step2. Lookup (탬색 & 획득)
		// (JNDI의 이름규칙)
		// 우리가 지정한 자원의 이름 앞에 접두사로 아래같이 붙여주셔야 합니다.
		// 최종자원이름 : (접두사 : java:comp/env/ + 자원이름
		Object obj = ctx.lookup("java:comp/env/jdbc/Oracle21c");
		log.info("\t+ obj : {}", obj);
			
		// Step3. JNDI lookup으로 얻어낸 참조타입의 객체를 필드에 초기화
		this.dataSource = (javax.sql.DataSource) obj;
		
	} // Default Constructor

	// 아래의 2개의 어노테이션은 서블릿 클래스 내에서만 사용가능하다
//	@PreDestroy		// XX
//	@PostConstruct	// XX
	// --------------------------
//	void postConstruct() {	// 전처리 작업 수행
//		// 필드에 주입된 데이터소스(즉, 커넥션 풀)가
//		// 진짜 잘 주입되었는지 확인하자
//		log.trace("postConstruct() invoked.");
//		
//	} // postConstruct

	// 전체사원목록 조회 및 반환
	public List<EmpVO> selectAllEmployees(EmpDTO dto) throws Exception {
		Objects.requireNonNull(this.dataSource);
		log.info("\t+ dataSource : {}", this.dataSource);
		
		@Cleanup Connection conn = this.dataSource.getConnection();
		log.info("\t+ conn : {}", conn);

		String sql = "SELECT * FROM	emp";
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		@Cleanup ResultSet rs = pstmt.executeQuery();

		List<EmpVO> list = new ArrayList<>();

		while (rs.next()) {
			Integer empno     = rs.getInt("EMPNO");
			String 	ename     = rs.getString("ENAME");
			String 	job 	  = rs.getString("JOB");
			Integer mgr    	  = rs.getInt("MGR");
			Date 	hireDate  = rs.getDate("HIREDATE");
			Double 	sal       = rs.getDouble("SAL");
			Double 	comm      = rs.getDouble("COMM");
			Integer deptno    = rs.getInt("DEPTNO");
	
			// EmpVO 객체 생성
			// VO 패턴은 결국 생성자를 통해서 필드에 값을 저장하고
			// Getter 메소드만 제공 -> 읽기전용(수정불가) : Immutable Object
			EmpVO vo = new EmpVO(empno, ename, job, mgr, hireDate, sal, comm, deptno);

			list.add(vo); // 각 사원정보를 저장하고 있는 Vo(Value Object) 저장
		} // while
		
		return list;
	} // selectAllEmployees
	
	// 사원 삭제
	public boolean deleteEmployees(EmpDTO dto) throws Exception{
		log.trace("deleteEmployees({}) invoked.", dto);
		
		Objects.requireNonNull(dto.getEmpno());
		
		@Cleanup Connection conn = this.dataSource.getConnection();
		
		String sql = "DELETE FROM emp WHERE empno = ?";
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, dto.getEmpno());
		
		int affectedRows = pstmt.executeUpdate();
		
		return (affectedRows == 1);
	} // deleteEmployees
	
	// 사원 등록
	public boolean insertEmployees(EmpDTO dto) throws Exception{
		log.trace("insertEmployees({}) invoked.");
		
		@Cleanup Connection conn = this.dataSource.getConnection();
		
		String sql = """
				INSERT INTO emp VALUES (?, ?, ?, ?, ?, ?, ?, ?)
				""";
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, dto.getEmpno());
		pstmt.setString(2, dto.getEname());
		pstmt.setString(3, dto.getJob());
		pstmt.setInt(4, dto.getMgr());
		pstmt.setDate(5, (java.sql.Date) dto.getHireDate());
		pstmt.setDouble(6, dto.getSal());
		pstmt.setDouble(7, dto.getComm());
		pstmt.setInt(8, dto.getDeptno());
		
		int affectedRows = pstmt.executeUpdate();
		
		return (affectedRows == 1);
	} // insertEmployees
	
	public boolean updateEmployees(EmpDTO dto) throws Exception{
		log.trace("insertEmployees({}) invoked.");
		
		@Cleanup Connection conn = this.dataSource.getConnection();
		
		String sql = """
				UPDATE emp
				SET
					ename = ?
					job = ?
					mgr = ?
					hireDate = ?
					sal = ?
					comm = ?
					deptno = ?
				WHERE = ?
				VALUES (?, ?, ?, ?, ?, ?, ?, ?)
				""";
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, dto.getEmpno());
		pstmt.setString(2, dto.getEname());
		pstmt.setString(3, dto.getJob());
		pstmt.setInt(4, dto.getMgr());
		pstmt.setDate(5, new java.sql.Date(dto.getHireDate().getTime()));
		pstmt.setDouble(6, dto.getSal());
		pstmt.setDouble(7, dto.getComm());
		pstmt.setInt(8, dto.getDeptno());
		
		int affectedRows = pstmt.executeUpdate();
		
		return (affectedRows == 1);
	} // updateEmployees
	
	public EmpVO selectEmployees(EmpDTO dto) throws Exception {
        log.trace("selectEmployee({}) invoked. " );
        Objects.requireNonNull(this.dataSource);
        
        @Cleanup Connection conn = this.dataSource.getConnection();
        log.info("\t+ conn: {}", conn);
        
        String sql = "SELECT * FROM emp WHERE empno = ?";
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        
        pstmt.setInt(1, dto.getEmpno());
        
        @Cleanup ResultSet rs = pstmt.executeQuery();      // DQL
        
        EmpVO vo = null;   //초기화
        
        if(rs.next()) {   // 각 행마다, 모든 컬럼의 값을 추출
           Integer 	empno 		= rs.getInt("EMPNO");
           String 	ename 		= rs.getString("ENAME");
           String 	job 		= rs.getString("JOB");
           Integer 	mgr 		= rs.getInt("MGR");
           Date 	hireDate   	= rs.getDate("HIREDATE");
           Double 	sal 		= rs.getDouble("SAL");
           Double 	comm 		= rs.getDouble("COMM");
           Integer 	deptno 		= rs.getInt("DEPTNO");
           
           // VO 객체 생성
           vo = new EmpVO(empno, ename, job, mgr, hireDate, sal, comm, deptno);
           
        } //if
              
        return vo;
     } // selectEmployees 
	
} // end class
