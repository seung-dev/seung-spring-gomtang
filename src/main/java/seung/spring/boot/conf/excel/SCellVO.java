package seung.spring.boot.conf.excel;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class SCellVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public SCellVO() {
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
	@JacksonXmlProperty
	private String pos;
	@JacksonXmlProperty
	private String type;
//	@JacksonXmlProperty
//	private String color;
	
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
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
//	public String getColor() {
//		return color;
//	}
//	public void setColor(String color) {
//		this.color = color;
//	}
}
