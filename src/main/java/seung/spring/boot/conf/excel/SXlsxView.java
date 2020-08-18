package seung.spring.boot.conf.excel;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import seung.java.kimchi.util.SKimchiException;

@Component("sXlsxView")
public class SXlsxView extends AbstractXlsxView {

	@Override
	protected SXSSFWorkbook createWorkbook(Map<String, Object> model, HttpServletRequest request) {
		
		SXSSFWorkbook sxssfWorkbook = null;
		
		try {
			sxssfWorkbook = new SXSSFWorkbook(new SXlsxU().createXSSFWorkbook(getApplicationContext(), model, request));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SKimchiException e) {
			e.printStackTrace();
		}
		
		return sxssfWorkbook;
	}
	
	@Override
	protected void buildExcelDocument(
			Map<String, Object> model
			, Workbook workbook
			, HttpServletRequest request
			, HttpServletResponse response
			) throws Exception {
		setFileName(request, response, ((SExcelVO) model.get("sExcelVO")).getDownloadFileName());
	}
	
	private void setFileName(
			HttpServletRequest request
			, HttpServletResponse response
			, String downloadFileName
			) throws Exception {
		
		String encodedFileName = null;
		
		String browser = getBrowser(request);
		if("MSIE".equals(browser)) {
			encodedFileName = URLEncoder.encode(downloadFileName, "UTF-8").replaceAll("\\+", "%20");
		} else if("Firefox".equals(browser)) {
			encodedFileName = "\"" + new String(downloadFileName.getBytes("UTF-8"), "8859_1") +"\"";
		} else if("Opera".equals(browser)) {
			encodedFileName = "\"" + new String(downloadFileName.getBytes("UTF-8"), "8859_1") +"\"";
		} else if("Chrome".equals(browser)) {
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < downloadFileName.length(); i++) {
				char c = downloadFileName.charAt(i);
				if(c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFileName = sb.toString();
		} else {
			throw new IOException("not supported browser!!!!");
		}
		
		response.setHeader("Content-Disposition", "attachment; filename=" + encodedFileName);
		
		if("Opera".equals(browser)) {
			response.setContentType("application/octet-stream; charset=UTF-8");
		}
	}
	
	private String getBrowser(HttpServletRequest req) {
		String header = req.getHeader("User-Agent");
		if(header.indexOf("MSIE") > -1) {
			return "MSIE";
		} else if(header.indexOf("Trident") > -1) {
			return "MSIE";
		} else if(header.indexOf("Chrome") > -1) {
			return "Chrome";
		} else if(header.indexOf("Opera") > -1) {
			return "Opera";
		}
		return "Firefox";
	}
}
