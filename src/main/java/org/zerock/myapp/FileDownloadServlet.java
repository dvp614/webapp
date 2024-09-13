package org.zerock.myapp;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@WebServlet("/FileDownload")
public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

		
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		log.trace("service(req, res) invoked.");
		
		try {
			// 1. 요청처리
			req.setCharacterEncoding("utf8");
			
			// Step1. 다운로드요청한 UUID형식의 파일명을 전송파라미터로 수신
			String downloadFileName = req.getParameter("downloadFileName");
			log.info("\t+ downloadFileName: {}", downloadFileName);
			
			Objects.requireNonNull(downloadFileName);
			
			// 1-1. 다운로드할 파일의 절대경로 생성
			final String uploadPath = "C:/temp/upload/";
			final String downloadPath = uploadPath + downloadFileName;
			log.info("\t+ downloadPath: {}", downloadPath);
			
			// --------------------------------
			// Step2. 실제 다운로드 수행	
			// --------------------------------		
			// 이 서블릿에서 주는 응답은 다운로드할 파일의 데이터를 브라우저로 전송
			// 때문에, 웹브라우저가 받은 데이터로, 렌더링하라고 주는게 아니라,
			// 파일 다운로드시키라!!! 라고 브라우저의 동작을 변경시켜야 합니다.
			
			// 2-1. 응답메시지에 아래의 특정 헤더를 넣습니다.
//			res.setContentType("text/html; charset=utf8");
			
			// 아래의 컨텐츠 유형을 헤더에 지정하면, 브라우저는
			// "아! 응답메시지의 바디에 있는 것은, 렌더링할 데이터가 아니라,
			//  다운로드시켜야할 바이트 기반의 데이터가 들어있구나!"라고
			//  알려주는게 됩니다.
			res.setContentType("application/octet-stream");		// (1) Content-Type
			
			// 2-2. 아래의 원래파일명으로 다운로드 하도록 원본파일명을 테이블에서 가져옴
			String originalFilename = "XXX.txt";
			
			// 2-3. 아래의 두번째 헤더를 통해서, 브라우저는 2-2에서 지정한
			//      원래파일명으로 다운로드를 해줍니다.
			res.setHeader(
				"Content-Disposition", 							// (2)
				"attachment; filename=" + originalFilename
			);
			
			// 2-4. 실제 파일데이터를 읽어서, 응답메시지의 바디에 출력
			@Cleanup FileInputStream fis = new FileInputStream(downloadPath);
			@Cleanup ServletOutputStream sos = res.getOutputStream();
			
			byte[] 바가지 = new byte[100];
			
			int readBytes;
			while((readBytes = fis.read(바가지)) != -1) { // -1 : EOF(파일의 끝)
				// 응답메시지의 바디에 바가지 채로 출력
				sos.write(바가지, 0, readBytes);
			} // while
			
			sos.flush();
		} catch(Exception e) {
			throw new IOException(e);
		} // try-catch
	} // service


} // end class

