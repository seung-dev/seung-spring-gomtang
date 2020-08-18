package seung.spring.boot.conf.excel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.Gson;

import seung.java.kimchi.util.SLinkedHashMap;

public class SExcelUtil {

	private Map<String, Object> model;
	private XSSFWorkbook xssfFormWorkbook;
	private SExcelFormXmlVO sExcelVO;
	private SLinkedHashMap xlsxData;
	
	private int formSheetEndRowNo = 0;
	private int formSheetEndCellNo = 0;
	
	//getFormSheet
	private List<List<XSSFCellStyle>> formCellStyleList = null;
	private List<List<String>> formCellValueList = null;
	private List<int[]> formCellMergeRegionList = null;
	
	public SExcelUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public SExcelUtil(Map<String, Object> model) {
		this.model = model;
	}
	
//	public XSSFWorkbook create() {
//		
//		//formWorkbook();
//		XSSFSheet xssfFormSheet = null;
//		int formRowNo = 0;
//		SRowVO formRowVO = null;
//		SCellVO formCellVO = null;
//		
//		//createWorkbook();
//		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
//		XSSFSheet xssfSheet = null;
//		XSSFRow xssfRow = null;
//		XSSFCell xssfCell = null;
//		int createRowNo = 0;
//		int createCellNo = 0;
//		
//		for(SSheetVO sSheetVO : sExcelVO.getSheet()) {
//			
//			formSheetEndRowNo = Integer.parseInt(sSheetVO.getEndRow());
//			formSheetEndCellNo = cellPosToInt(1, sSheetVO.getEndCell());
//			
//			xssfFormSheet = xssfFormWorkbook.getSheet(sSheetVO.getName());
//			
//			setFormSheet(xssfFormSheet);
//			
//			xssfSheet = xssfWorkbook.createSheet(sSheetVO.getName());
//			for(formRowNo = 0; formRowNo < formSheetEndRowNo; formRowNo++) {
//				
//				createRowNo = 0;
//				
//				formRowVO = null;
//				for(SRowVO sRowVO : sSheetVO.getRow()) {
//					if(sRowVO.getPos() - 1 == formRowNo) {
//						formRowVO = sRowVO;
//						break;
//					}
//				}
//				
//				xssfRow = xssfSheet.createRow(createRowNo++);
//				for(createCellNo = 0; createCellNo < formSheetEndCellNo; createCellNo++) {
//					
//					try {
//						xssfCell = xssfRow.createCell(createCellNo);
//					} catch (NullPointerException e) {
//						xssfCell = null;
//					}
//					
//					if(xssfCell != null) {
//						
//						formCellVO = null;
//						if(formRowVO != null) {
//							for(SCellVO sCellVO : formRowVO.getCell()) {
//								if(cellPosToInt(1, sCellVO.getPos()) - 1 == createCellNo) {
//									formCellVO = sCellVO;
//									break;
//								}
//							}
//						}
//						
//						if(formCellStyleList.get(formRowNo).get(createCellNo) != null) xssfCell.setCellStyle(formCellStyleList.get(formRowNo).get(createCellNo));
//						if(formCellVO != null) {
//							xssfCell.setCellValue(xlsxData.get(formCellVO.getKey()) != null ? xlsxData.getString(formCellVO.getKey()) : "test");
//						} else {
//							if(formCellValueList.get(formRowNo).get(createCellNo) != null) xssfCell.setCellValue(formCellValueList.get(formRowNo).get(createCellNo));
//						}
//					}
//				}
//				
//				if(formRowVO != null && !formRowVO.isLoop()) {
//				} else {//end of not loop
//				}//end of loop
//			}
//		}
//		
//		return xssfWorkbook;
//	}
	
	private void setFormSheet(XSSFSheet xssfFormSheet) {
		
		formCellStyleList = new ArrayList<List<XSSFCellStyle>>();
		formCellValueList = new ArrayList<List<String>>();
		formCellMergeRegionList = new ArrayList<int[]>();
		
		XSSFRow formRow = null;
		XSSFCell formCell = null;
		CellType formCellType = null;
		List<XSSFCellStyle> formCellStyles = null;
		List<String> formCellValues = null;
		int[] formCellMergeRegion = null;
		
		int formRowNo = 0;
		int formCellNo = 0;
		for(formRowNo = 0; formRowNo < formSheetEndRowNo; formRowNo++) {
			
			formCellStyles = new ArrayList<XSSFCellStyle>();
			formCellValues = new ArrayList<String>();
			
			formRow = xssfFormSheet.getRow(formRowNo);
			for(formCellNo = 0; formCellNo < formSheetEndCellNo; formCellNo++) {
				try {
					formCell = formRow.getCell(formCellNo);
				} catch (NullPointerException e) {
					formCell = null;
				}
				if(formCell != null) {
					try {
						formCellStyles.add(formCell.getCellStyle());
					} catch (NullPointerException e) {
						formCellStyles.add(null);
					}
					try {
						formCellType = formCell.getCellTypeEnum();
						if(formCellType == CellType.BLANK) {
							formCellValues.add("");
						} else if(formCellType == CellType.STRING) {
							formCellValues.add(formCell.getStringCellValue());
						} else if(formCellType == CellType.NUMERIC) {
							if(DateUtil.isCellDateFormatted(formCell)) {
								formCellValues.add(getDateString(formCell.getDateCellValue(), "yyyyMMddHHmmss"));
							} else {
								formCellValues.add(NumberToTextConverter.toText(formCell.getNumericCellValue()));
							}
						} else if(formCellType == CellType.BOOLEAN) {
							formCellValues.add("" + formCell.getBooleanCellValue());
						} else if(formCellType == CellType.FORMULA) {
							formCellValues.add("" + formCell.getCellFormula());
						}
					} catch (Exception e) {
						formCellValues.add("");
					}
				}
			}//end of cell
			
			formCellStyleList.add(formCellStyles);
			formCellValueList.add(formCellValues);
		}//end of row
		
		int numMergedRegions = xssfFormSheet.getNumMergedRegions();
		for(int i = 0; i < numMergedRegions; i++) {
			formCellMergeRegion = new int[5];
			//[0]: type
			//[1]: fst column
			//[2]: fst row
			//[3]: dff column
			//[4]: dff row
			formCellMergeRegion[0] = i;
			formCellMergeRegion[1] = xssfFormSheet.getMergedRegions().get(i).getFirstColumn();
			formCellMergeRegion[2] = xssfFormSheet.getMergedRegions().get(i).getFirstRow();
			formCellMergeRegion[3] = xssfFormSheet.getMergedRegions().get(i).getLastColumn() - formCellMergeRegion[1];
			formCellMergeRegion[4] = xssfFormSheet.getMergedRegions().get(i).getLastRow() - formCellMergeRegion[2];
			
			formCellMergeRegionList.add(formCellMergeRegion);
		}
		
		for(int i = 0; i < numMergedRegions; i++) {
			xssfFormSheet.removeMergedRegion(0);
		}
		
		System.out.println(new Gson().toJson(formCellValueList));
		System.out.println(new Gson().toJson(formCellMergeRegionList));
	}
	
	private String getDateString(Date date, String pattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}
	
	private int cellPosToInt(int start, String cellPos) {
		return (int) cellPos.charAt(0) + start - 65;
	}
}
