package seung.spring.boot.conf.excel;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName="excel")
public class SExcelFormXmlVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public SExcelFormXmlVO() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	@JacksonXmlElementWrapper(localName="sheets")
	private SSheetVO[] sheets;
	
	public SSheetVO[] getSheets() {
		return sheets;
	}
	public void setSheet(SSheetVO[] sheets) {
		this.sheets = sheets;
	}
}
