package seung.spring.boot.conf.excel;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class SSheetVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public SSheetVO() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	@JacksonXmlProperty
	private String key;
	@JacksonXmlProperty
	private String name;
	@JacksonXmlProperty(localName="begin-row")
	private int beginRow = 0;
	@JacksonXmlProperty(localName="begin-cell")
	private String beginCell = "A";
	@JacksonXmlProperty(localName="end-row")
	private int endRow;
	@JacksonXmlProperty(localName="end-cell")
	private String endCell;
	@JacksonXmlElementWrapper(localName="rows")
	private SRowVO[] row;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBeginRow() {
		return beginRow;
	}
	public void setBeginRow(int beginRow) {
		this.beginRow = beginRow;
	}
	public String getBeginCell() {
		return beginCell;
	}
	public void setBeginCell(String beginCell) {
		this.beginCell = beginCell;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public String getEndCell() {
		return endCell;
	}
	public void setEndCell(String endCell) {
		this.endCell = endCell;
	}
	public SRowVO[] getRow() {
		return row;
	}
	public void setRow(SRowVO[] row) {
		this.row = row;
	}
}
