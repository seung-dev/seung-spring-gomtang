package seung.spring.boot.conf.excel;

import java.util.List;

import seung.java.kimchi.util.SLinkedHashMap;

/**
 * (c) seung <http://www.stoas.co.kr>
 * @file	SXlsSheetVO.java
 * @desc	xls sheet value object
 * @author	seung (010-5654-5179 stoas0605@gmail.com)
 * @since	2016.07.21
 */
public class SXlsSheetVO {

	private int				sheetNo;
	private String			sheetName;
	private int				startCellX;
	private String			startCellY;
	private List<SLinkedHashMap>		data;
	private int				dataValidationStartCellX = -1;
	private String			dataValidationStartCellY;
	private String[]		dataValidationStringArray;
	
	public int getSheetNo() {
		return sheetNo;
	}
	public void setSheetNo(int sheetNo) {
		this.sheetNo = sheetNo;
	}
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public int getStartCellX() {
		return startCellX;
	}
	public void setStartCellX(int startCellX) {
		this.startCellX = startCellX;
	}
	public String getStartCellY() {
		return startCellY == null || startCellY.length() == 0 ? "A" : startCellY;
	}
	public void setStartCellY(String startCellY) {
		this.startCellY = startCellY;
	}
	public List<SLinkedHashMap> getData() {
		return data;
	}
	public void setData(List<SLinkedHashMap> data) {
		this.data = data;
	}
	public int getDataValidationStartCellX() {
		return dataValidationStartCellX;
	}
	public void setDataValidationStartCellX(int dataValidationStartCellX) {
		this.dataValidationStartCellX = dataValidationStartCellX;
	}
	public String getDataValidationStartCellY() {
		return dataValidationStartCellY;
	}
	public void setDataValidationStartCellY(String dataValidationStartCellY) {
		this.dataValidationStartCellY = dataValidationStartCellY;
	}
	public String[] getDataValidationStringArray() {
		return dataValidationStringArray;
	}
	public void setDataValidationStringArray(String[] dataValidationStringArray) {
		this.dataValidationStringArray = dataValidationStringArray;
	}
}
