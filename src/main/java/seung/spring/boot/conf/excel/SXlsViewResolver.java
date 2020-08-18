package seung.spring.boot.conf.excel;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

public class SXlsViewResolver extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
	}

//	private SXlsVO sXlsVO;
//	
//	@Override
//	protected void buildExcelDocument(
//			Map<String, Object> model
//			, HSSFWorkbook xssfWorkbook
//			, HttpServletRequest httpServletRequest
//			, HttpServletResponse httpServletResponse
//			) throws Exception {
//		
//		setFileName(httpServletRequest, httpServletResponse);
//		
//		mergeWorkBook(xssfWorkbook);
//	}
//	
//	private void mergeWorkBook(XSSFWorkbook xssfWorkbook) {
//		
//		HSSFSheet hssfSheet = null;
//		for(int sheetNo = 0; sheetNo < xssfWorkbook.getNumberOfSheets(); sheetNo++) {
//			
//			hssfSheet = xssfWorkbook.getSheetAt(sheetNo);
//			
//			for(SXlsSheetVO sXlsSheetVO : sXlsVO.getSXlsSheetVOList()) {
//				
//				if(sheetNo == sXlsSheetVO.getSheetNo()) {
//					mergeSheet(xssfWorkbook, hssfSheet, sXlsSheetVO);
//				}
//				
//			}//end of match sheet name
//			
//		}//end of template excel sheet loop
//		
//	}
//	
//	@SuppressWarnings("unchecked")
//	private void mergeSheet(HSSFWorkbook xssfWorkbook, HSSFSheet hssfSheet, SXlsSheetVO sXlsSheetVO) {
//		
//		xssfWorkbook.setSheetName(sXlsSheetVO.getSheetNo(), sXlsSheetVO.getSheetName());
//		
//		ArrayList<HSSFCellStyle> hssfCellStyleList = null;//new ArrayList<HSSFCellStyle>();
//		ArrayList<Integer> hssfCellTypeList = null;//new ArrayList<HSSFCellStyle>();
//		
//		int startCellX = sXlsSheetVO.getStartCellX() - 1;
//		int startCellY = stringToInt(0, sXlsSheetVO.getStartCellY());
//		List<SMap> data = sXlsSheetVO.getData();
//		
//		if(data == null || data.size() == 0) {
//			hssfSheet.removeRow(hssfSheet.getRow(startCellX));
//		} else {
//			
//			HSSFRow hssfRow = null;
//			HSSFCell hssfCell = null;
//			
//			Set<String> keySet = data.get(0).keySet();
//			String[] keys = keySet.toArray(new String[keySet.size()]);
//			
//			//copy cell style
//			hssfRow = hssfSheet.getRow(startCellX);
//			Short rowHeight = hssfRow.getHeight();
//			if(hssfRow != null) {
//				hssfCellStyleList = new ArrayList<HSSFCellStyle>();
//				hssfCellTypeList = new ArrayList<Integer>();
//				for(int keyNo = 0; keyNo < keys.length; keyNo++) {
//					hssfCell = hssfRow.getCell(keyNo);
//					hssfCellStyleList.add(hssfCell.getCellStyle());
//					hssfCellTypeList.add(hssfCell.getCellType());
//				}
//			}
//			
//			for(int dataNo = 0; dataNo < data.size(); dataNo++) {
//				
//				//create row
//				hssfRow = hssfSheet.createRow(startCellX++);
//				for(int keyNo = 0; keyNo < keys.length; keyNo++) {
//					if(hssfCellTypeList.get(keyNo) == Cell.CELL_TYPE_NUMERIC) {
//						if("".equals(data.get(dataNo).getString(keys[keyNo]))) hssfRow.createCell(startCellY + keyNo).setCellValue(data.get(dataNo).getString(keys[keyNo]));
//						else hssfRow.createCell(startCellY + keyNo).setCellValue(data.get(dataNo).getDouble(keys[keyNo]));
//					} else {
//						hssfRow.createCell(startCellY + keyNo).setCellValue(data.get(dataNo).getString(keys[keyNo],true));
//					}
//					hssfRow.setHeight(rowHeight);
//					hssfRow.getCell(startCellY + keyNo).setCellStyle(hssfCellStyleList.get(keyNo));
//				}
//				
//				//check max row number
//				if(sXlsVO.getMaxRowNumber() > 0 && dataNo > sXlsVO.getMaxRowNumber()) break;
//			}
//			
//			if(sXlsSheetVO.getDataValidationStartCellX() > -1 && sXlsSheetVO.getDataValidationStartCellY() != null && sXlsSheetVO.getDataValidationStringArray() != null) {
//				CellRangeAddressList regions = new CellRangeAddressList(
//						sXlsSheetVO.getDataValidationStartCellX()
//						, sXlsSheetVO.getDataValidationStartCellX() + data.size()
//						, stringToInt(0, sXlsSheetVO.getDataValidationStartCellY())
//						, stringToInt(0, sXlsSheetVO.getDataValidationStartCellY())
//						);
//				DVConstraint constraint = DVConstraint.createExplicitListConstraint(sXlsSheetVO.getDataValidationStringArray());
//				HSSFDataValidation hssfDataValidation = new HSSFDataValidation(regions, constraint);
//				hssfSheet.addValidationData(hssfDataValidation);
//			}
//		}
//	}
//	
//	private int stringToInt(int start, String s) {
//		return (int) (s).charAt(0) + start - 65;
//	}
//	
//	private String getDateString(String pattern) {
//		SimpleDateFormat sdf = new SimpleDateFormat(pattern.length() > 0 ? pattern : "yyyy-MM-dd HH:mm:sssss");
//		return sdf.format(new Date());
//	}
//	
//	private void setFileName(
//			HttpServletRequest httpServletRequest
//			, HttpServletResponse httpServletResponse
//			) throws Exception {
//		
//		String fileName = (sXlsVO.getDownloadFileName() == null || sXlsVO.getDownloadFileName().length() == 0 ? "통합조회서비스" : sXlsVO.getDownloadFileName()) + "_" + getDateString("yyyyMMddHHmmss") + ".xls";
//		// + "." + sXlsVO.getDownloadFileExtension()
//		String encodedFileName = null;
//		
//		String browser = getBrowser(httpServletRequest);
//		if("MSIE".equals(browser)) {
//			encodedFileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
//		} else if("Firefox".equals(browser)) {
//			encodedFileName = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") +"\"";
//		} else if("Opera".equals(browser)) {
//			encodedFileName = "\"" + new String(fileName.getBytes("UTF-8"), "8859_1") +"\"";
//		} else if("Chrome".equals(browser)) {
//			StringBuffer sb = new StringBuffer();
//			for(int i = 0; i < fileName.length(); i++) {
//				char c = fileName.charAt(i);
//				if(c > '~') {
//					sb.append(URLEncoder.encode("" + c, "UTF-8"));
//				} else {
//					sb.append(c);
//				}
//			}
//			encodedFileName = sb.toString();
//		} else {
//			throw new IOException("not supported browser!!!!");
//		}
//		
//		httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + encodedFileName);
//		
//		if("Opera".equals(browser)) {
//			httpServletResponse.setContentType("application/octet-stream; charset=UTF-8");
//		}
//	}
//	
//	private String getBrowser(HttpServletRequest httpServletRequest) {
//		String header = httpServletRequest.getHeader("User-Agent");
//		if(header.indexOf("MSIE") > -1 || header.indexOf("Trident") > -1) {
//			return "MSIE";
//		} else if(header.indexOf("Chrome") > -1) {
//			return "Chrome";
//		} else if(header.indexOf("Opera") > -1) {
//			return "Opera";
//		}
//		return "Firefox";
//	}
}
