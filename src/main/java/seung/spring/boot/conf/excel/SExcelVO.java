package seung.spring.boot.conf.excel;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import seung.java.kimchi.util.SLinkedHashMap;

public class SExcelVO {

	public SExcelVO() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
//		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
	
	private String templateFilePath;
	private String downloadFileName;
	private String formXmlPath;
	private SLinkedHashMap data;
	
	public String getTemplateFilePath() {
		return templateFilePath;
	}
	public void setTemplateFilePath(String templateFilePath) {
		this.templateFilePath = templateFilePath;
	}
	public String getDownloadFileName() {
		return downloadFileName;
	}
	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}
	public String getFormXmlPath() {
		return formXmlPath;
	}
	public void setFormXmlPath(String formXmlPath) {
		this.formXmlPath = formXmlPath;
	}
	public SLinkedHashMap getData() {
		return data;
	}
	public void setData(SLinkedHashMap data) {
		this.data = data;
	}
}
