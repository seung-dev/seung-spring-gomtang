package seung.spring.boot.conf.web.util;

import java.util.Date;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import seung.java.kimchi.util.SLinkedHashMap;

@Builder
@Setter
@Getter
public class SRequest {

	@Builder.Default
	private double request_time = new Date().getTime();
	
	private SLinkedHashMap network;
	
	private SLinkedHashMap header;
	
	private SLinkedHashMap session;
	
	@SuppressWarnings("unchecked")
	public SRequest putNetwork(String key, String value) {
		this.network.put(key, value);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public SRequest putHeader(String key, String value) {
		this.header.put(key, value);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public SRequest putSession(String key, String value) {
		this.session.put(key, value);
		return this;
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
