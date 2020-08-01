package seung.spring.boot.conf.web.util;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import seung.java.kimchi.util.SCode;
import seung.java.kimchi.util.SLinkedHashMap;

@Builder
@Getter
@Setter
public class SResponse {

	@NotEmpty
	private String request_code;
	
	@Builder.Default
	private String error_code = "E999";
	
	@Builder.Default
	private String error_message = "";
	
	private long request_time;
	
	private long response_time;
	
	private Object request;
	
	@Builder.Default
	private SLinkedHashMap response = new SLinkedHashMap();
	
	@SuppressWarnings("unchecked")
	public SResponse putResponse(Object key, Object value) {
		this.response.put(key, value);
		return this;
	}
	
	public void success() {
		this.error_code = SCode.SUCCESS;
	}
	
	public void done() {
		this.response_time = new Date().getTime();
	}
	
	public String toJsonString() {
		return toJsonString(false);
	}
	public String toJsonString(boolean isPretty) {
		try {
			return new ObjectMapper()
					.setSerializationInclusion(Include.ALWAYS)
					.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
					.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true)
					.configure(SerializationFeature.INDENT_OUTPUT, isPretty)
					.writeValueAsString(this)
					;
		} catch (JsonProcessingException e) {
			return new SLinkedHashMap()
					.add("exception", ExceptionUtils.getStackTrace(e))
					.toJsonString(isPretty)
					;
		}
	}
	
}
