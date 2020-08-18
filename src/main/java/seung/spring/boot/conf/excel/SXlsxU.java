package seung.spring.boot.conf.excel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.LocalizedResourceHelper;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;

import seung.java.kimchi.util.SKimchiException;
import seung.java.kimchi.util.SLinkedHashMap;

public class SXlsxU {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final String _XLSX_EXT = ".xlsx";
	private static final String _XML_EXT = ".xml";
	
	//template sheet data
	private int templateSheetEndRowNo = -1;
	private int templateSheetEndCellNo = -1;
	private int templateSheetBeginRowNo = 0;
	private int templateSheetBeginCellNo = 0;
	private List<Short> xssfRowHeightList = null;
	private List<List<XSSFCellStyle>> xssfCellStyleList = null;
	private List<List<String>> xssfCellValueList = null;
	private List<int[]> xssfCellMergeRegionList = null;
	
	public SXlsxU() {
		// TODO Auto-generated constructor stub
	}
	
	public XSSFWorkbook createXSSFWorkbook(ApplicationContext applicationContext, Map<String, Object> model, HttpServletRequest request) throws IOException, SKimchiException {
		
		//init variables
		SExcelVO sExcelVO = (SExcelVO) model.get("sExcelVO");
		
		//init xssfWorkbook
		XSSFWorkbook xssfWorkbook = null;
		if(sExcelVO.getTemplateFilePath() == null) {
			xssfWorkbook = new XSSFWorkbook();
		} else {
			xssfWorkbook = getTemplateFile(applicationContext, request, sExcelVO.getTemplateFilePath());
		}
		
//		//init excel form
//		SExcelFormXmlVO sExcelFormXmlVO = getFormXml(applicationContext, request, sExcelVO.getFormXmlPath());
//		
//		//check excel form
//		if(sExcelFormXmlVO == null) return xssfWorkbook;
		
		//build document
//		buildDocument(xssfWorkbook, sExcelFormXmlVO, sExcelVO);
		buildDocument(xssfWorkbook, sExcelVO);
		
		return xssfWorkbook;
	}
	
	private void buildDocument(XSSFWorkbook xssfWorkbook, SExcelVO sExcelVO) throws SKimchiException {
		
		//init constructor
		XSSFSheet xssfSheet;
		
		//init data
		SLinkedHashMap data = sExcelVO.getData();
		if(data == null) data = new SLinkedHashMap();
		
		SSheetVO sSheetVO = new SSheetVO();
		sSheetVO.setBeginRow(5);
		sSheetVO.setEndRow(5);
		sSheetVO.setBeginCell("A");
		sSheetVO.setEndCell("SG");
		sSheetVO.setName("DATA");
		
		xssfSheet = xssfWorkbook.getSheet(sSheetVO.getName());
		
		templateSheetBeginRowNo = sSheetVO.getBeginRow();
		templateSheetBeginCellNo = cellPosToInt(1, sSheetVO.getBeginCell());
		templateSheetEndRowNo = sSheetVO.getEndRow();
		templateSheetEndCellNo = cellPosToInt(1, sSheetVO.getEndCell());
		
		getFormSheet(xssfSheet, templateSheetBeginRowNo, templateSheetEndCellNo, templateSheetEndRowNo, templateSheetEndCellNo);
		
		createRows(xssfSheet, sSheetVO, data, templateSheetBeginRowNo);
		
	}
	
	private void buildDocument(XSSFWorkbook xssfWorkbook, SExcelFormXmlVO sExcelFormXmlVO, SExcelVO sExcelVO) throws SKimchiException {
		
		//init constructor
		XSSFSheet xssfSheet;
		
		//init data
		SLinkedHashMap data = sExcelVO.getData();
		if(data == null) data = new SLinkedHashMap();
		
		for(SSheetVO sSheetVO : sExcelFormXmlVO.getSheets()) {
			
			xssfSheet = xssfWorkbook.getSheet(sSheetVO.getName());
			
			templateSheetBeginRowNo = sSheetVO.getBeginRow();
			templateSheetBeginRowNo = 0;
			templateSheetBeginCellNo = cellPosToInt(1, sSheetVO.getBeginCell());
			templateSheetEndRowNo = sSheetVO.getEndRow();
			templateSheetEndCellNo = cellPosToInt(1, sSheetVO.getEndCell());
			
			getFormSheet(xssfSheet, templateSheetBeginRowNo, templateSheetEndCellNo, templateSheetEndRowNo, templateSheetEndCellNo);
			
			createRows(xssfSheet, sSheetVO, data, templateSheetBeginRowNo);
		}
	}
	
	private void createRows(XSSFSheet xssfSheet, SSheetVO sSheetVO, SLinkedHashMap data, int templateSheetBeginRowNo) throws SKimchiException {
		
		//init constructor
		XSSFRow xssfRow = null;
		int createRowNo = 0;
		SRowVO sRowVO = null;
		List<int[]> createCellMergeRegionList = new ArrayList<int[]>();;
		
		for(int rowNo = 0; rowNo < templateSheetEndRowNo; rowNo++) {
			
			if(rowNo >= templateSheetBeginRowNo) {
				
				sRowVO = null;
				for(SRowVO formRowVO : sSheetVO.getRow()) {
					if(formRowVO.getPos() - 1 == rowNo) {
						sRowVO = formRowVO;
						break;
					}
				}
				
				if(sRowVO != null && sRowVO.isLoop()) {
					
					if(data.get(sRowVO.getKey()) == null) {
						
						xssfRow = xssfSheet.createRow(createRowNo++);
						if(xssfRowHeightList.get(rowNo) != null) xssfRow.setHeight(xssfRowHeightList.get(rowNo));
						
						createCells(xssfRow, sRowVO, rowNo, data, createCellMergeRegionList);
						
					} else {
						
						for(SLinkedHashMap loopMap : data.getListSLinkedHashMap(sRowVO.getKey())) {
							
							xssfRow = xssfSheet.createRow(createRowNo++);
							if(xssfRowHeightList.get(rowNo) != null) xssfRow.setHeight(xssfRowHeightList.get(rowNo));
							
							createCells(xssfRow, sRowVO, rowNo, loopMap, createCellMergeRegionList);
							
						}
						
					}
					
				} else {
					
					xssfRow = xssfSheet.createRow(createRowNo++);
					if(xssfRowHeightList.get(rowNo) != null) xssfRow.setHeight(xssfRowHeightList.get(rowNo));
					
					createCells(xssfRow, sRowVO, rowNo, data, createCellMergeRegionList);
					
				}
			}
		}
		
		for(int[] createCellMergeRegion : createCellMergeRegionList) {
			xssfSheet.addMergedRegion(
				new CellRangeAddress(
						createCellMergeRegion[2]
						, createCellMergeRegion[2] + createCellMergeRegion[4]
						, createCellMergeRegion[1]
						, createCellMergeRegion[1] + createCellMergeRegion[3]
						)
				);
		}
	}
	
	private void createCells(XSSFRow xssfRow, SRowVO sRowVO, int rowNo, SLinkedHashMap data, List<int[]> createCellMergeRegionList) throws SKimchiException {
		
		//init constructor
		XSSFCell xssfCell = null;
		SCellVO sCellVO = null;
		int[] createCellMergedRegion = null;
		
		for(int cellNo = 0; cellNo < templateSheetEndCellNo; cellNo++) {
			
			xssfCell = xssfRow.createCell(cellNo);
			
			createCellMergedRegion = new int[5];
			for(int[] xssfCellMergeRegion : xssfCellMergeRegionList) {
				if(rowNo == xssfCellMergeRegion[2] && cellNo == xssfCellMergeRegion[1]) {
					createCellMergedRegion[0] = xssfCellMergeRegion[0];
					createCellMergedRegion[1] = cellNo;
					createCellMergedRegion[2] = xssfRow.getRowNum();
					createCellMergedRegion[3] = xssfCellMergeRegion[3];
					createCellMergedRegion[4] = xssfCellMergeRegion[4];
					createCellMergeRegionList.add(createCellMergedRegion);
				}
			}
			
			sCellVO = null;
			if(sRowVO != null) {
				for(SCellVO formCellVO : sRowVO.getCell()) {
					if(cellPosToInt(1, formCellVO.getPos()) - 1 == cellNo) {
						sCellVO = formCellVO;
						break;
					}
				}
			}
			
			if(xssfCellStyleList.get(rowNo).get(cellNo) != null) xssfCell.setCellStyle(xssfCellStyleList.get(rowNo).get(cellNo));
			if(sCellVO != null) {
				writeCellValue(xssfCell, sCellVO, data);
			} else {
				if(xssfCellValueList.get(rowNo).get(cellNo) != null) xssfCell.setCellValue(xssfCellValueList.get(rowNo).get(cellNo));
			}
		}
		
	}
	
	private void writeCellValue(XSSFCell xssfCell, SCellVO sCellVO, SLinkedHashMap data) throws SKimchiException {
		if("string".equals(sCellVO.getType())) {
			if(data.get(sCellVO.getKey()) != null) xssfCell.setCellValue(data.getString(sCellVO.getKey()));
		} else if("bigint".equals(sCellVO.getType())) {
			if(data.get(sCellVO.getKey()) != null) xssfCell.setCellValue(data.getInt(sCellVO.getKey()));
		}
	}
	
	private void getFormSheet(
			XSSFSheet xssfSheet
			, int templateSheetBeginRowNo
			, int templateSheetBeginCellNo
			, int templateSheetEndRowNo
			, int templateSheetEndCellNo
			) {
		
		xssfRowHeightList = new ArrayList<Short>();
		xssfCellStyleList = new ArrayList<List<XSSFCellStyle>>();
		xssfCellValueList = new ArrayList<List<String>>();
		xssfCellMergeRegionList = new ArrayList<int[]>();
		
		XSSFRow xssfRow = null;
		XSSFCell xssfCell = null;
		CellType xssfCellType = null;
		List<XSSFCellStyle> xssfCellStyles = null;
		List<String> xssfCellValues = null;
		int[] xssfCellMergeRegion = null;
		List<Integer> removeCellMergeRegion = new ArrayList<Integer>();
		
		int xssfRowNo = 0;
		int xssfCellNo = 0;
		for(xssfRowNo = 0; xssfRowNo < templateSheetEndRowNo; xssfRowNo++) {
			
			xssfCellStyles = new ArrayList<XSSFCellStyle>();
			xssfCellValues = new ArrayList<String>();
			
			xssfRow = xssfSheet.getRow(xssfRowNo);
			xssfRowHeightList.add(xssfRow.getHeight());
			
			for(xssfCellNo = 0; xssfCellNo < templateSheetEndCellNo; xssfCellNo++) {
				try {
					xssfCell = xssfRow.getCell(xssfCellNo);
				} catch (NullPointerException e) {
					xssfCell = null;
				}
				if(xssfCell != null) {
					try {
						xssfCellStyles.add(xssfCell.getCellStyle());
					} catch (NullPointerException e) {
						xssfCellStyles.add(null);
					}
					try {
						xssfCellType = xssfCell.getCellTypeEnum();
						if(xssfCellType == CellType.BLANK) {
							xssfCellValues.add("");
						} else if(xssfCellType == CellType.STRING) {
							xssfCellValues.add(xssfCell.getStringCellValue());
						} else if(xssfCellType == CellType.NUMERIC) {
							if(DateUtil.isCellDateFormatted(xssfCell)) {
								xssfCellValues.add(getDateString(xssfCell.getDateCellValue(), "yyyyMMddHHmmss"));
							} else {
								xssfCellValues.add(NumberToTextConverter.toText(xssfCell.getNumericCellValue()));
							}
						} else if(xssfCellType == CellType.BOOLEAN) {
							xssfCellValues.add("" + xssfCell.getBooleanCellValue());
						} else if(xssfCellType == CellType.FORMULA) {
							xssfCellValues.add("" + xssfCell.getCellFormula());
						}
					} catch (Exception e) {
						xssfCellValues.add("");
					}
				}
			}//end of cell
			
			xssfCellStyleList.add(xssfCellStyles);
			xssfCellValueList.add(xssfCellValues);
		}//end of row
		
		int numMergedRegions = xssfSheet.getNumMergedRegions();
		for(int i = 0; i < numMergedRegions; i++) {
			xssfCellMergeRegion = new int[5];
			//[0]: type
			//[1]: fst column
			//[2]: fst row
			//[3]: dff column
			//[4]: dff row
			xssfCellMergeRegion[0] = i;
			xssfCellMergeRegion[1] = xssfSheet.getMergedRegions().get(i).getFirstColumn();
			xssfCellMergeRegion[2] = xssfSheet.getMergedRegions().get(i).getFirstRow();
			xssfCellMergeRegion[3] = xssfSheet.getMergedRegions().get(i).getLastColumn() - xssfCellMergeRegion[1];
			xssfCellMergeRegion[4] = xssfSheet.getMergedRegions().get(i).getLastRow() - xssfCellMergeRegion[2];
			
			xssfCellMergeRegionList.add(xssfCellMergeRegion);
		}
		
		//clear merged regions
		for(int i = 0; i < numMergedRegions; i++) {
			xssfSheet.removeMergedRegion(0);
		}
	}
	
	private String getDateString(Date date, String pattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}
	
	private int cellPosToInt(int start, String cellPos) {
		return (int) cellPos.charAt(0) + start - 65;
	}
	
	private XSSFWorkbook getTemplateFile(ApplicationContext applicationContext, HttpServletRequest request, String url) throws IOException {
		Resource resource = getResource(applicationContext, request, _XLSX_EXT, url);
		return resource == null ? new XSSFWorkbook() : new XSSFWorkbook(resource.getInputStream());
	}
	
	private SExcelFormXmlVO getFormXml(ApplicationContext applicationContext, HttpServletRequest request, String url) throws IOException {
		Resource resource = getResource(applicationContext, request, _XML_EXT, url);
//		JAXBContext jaxbContext = JAXBContext.newInstance(SExcelFormXmlVO.class);
//		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//		return (SExcelFormXmlVO) unmarshaller.unmarshal(resource.getInputStream());
		return resource == null ? null : new XmlMapper().readValue(resource.getFile(), SExcelFormXmlVO.class);
	}
	
	private Resource getResource(ApplicationContext applicationContext, HttpServletRequest request, String extension, String url) {
		LocalizedResourceHelper localizedResourceHelper = new LocalizedResourceHelper(applicationContext);
		Locale locale = RequestContextUtils.getLocale(request);
		return localizedResourceHelper.findLocalizedResource(url, extension, locale);
	}
}
