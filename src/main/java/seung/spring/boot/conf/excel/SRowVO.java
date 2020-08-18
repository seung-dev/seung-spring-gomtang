package seung.spring.boot.conf.excel;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class SRowVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public SRowVO() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	@JacksonXmlProperty
	private String key;
	@JacksonXmlProperty
	private int pos;
	@JacksonXmlProperty
	private boolean loop;
	@JacksonXmlElementWrapper(localName="cells")
	private SCellVO[] cell;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public boolean isLoop() {
		return loop;
	}
	public void setLoop(boolean loop) {
		this.loop = loop;
	}
	public SCellVO[] getCell() {
		return cell;
	}
	public void setCell(SCellVO[] cell) {
		this.cell = cell;
	}
}
