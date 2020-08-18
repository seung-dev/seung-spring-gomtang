package seung.spring.boot.conf.excel;

import java.util.List;

/**
 * (c) seung <http://www.stoas.co.kr>
 * @file	SXlsVO.java
 * @desc	xls value object
 * @author	seung (010-5654-5179 stoas0605@gmail.com)
 * @since	2016.07.21
 */
public class SXlsVO {

	private String	downloadFileName;
	private String	downloadFileExtension;
	private String	templateFilePath;
	private int		maxRowNumber;
	private List<SXlsSheetVO> sXlsSheetVOList;
	
	public String getDownloadFileName() {
		return downloadFileName;
	}
	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}
	public String getDownloadFileExtension() {
		return downloadFileExtension;
	}
	public void setDownloadFileExtension(String downloadFileExtension) {
		this.downloadFileExtension = downloadFileExtension;
	}
	public String getTemplateFilePath() {
		return templateFilePath;
	}
	public void setTemplateFilePath(String templateFilePath) {
		this.templateFilePath = templateFilePath;
	}
	public int getMaxRowNumber() {
		return maxRowNumber;
	}
	public void setMaxRowNumber(int maxRowNumber) {
		this.maxRowNumber = maxRowNumber;
	}
	public List<SXlsSheetVO> getSXlsSheetVOList() {
		return sXlsSheetVOList;
	}
	public void setSXlsSheetVOList(List<SXlsSheetVO> sXlsSheetVOList) {
		this.sXlsSheetVOList = sXlsSheetVOList;
	}
}
