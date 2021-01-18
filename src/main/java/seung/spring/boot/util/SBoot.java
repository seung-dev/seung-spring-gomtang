package seung.spring.boot.util;

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
@Getter
@Setter
public class SBoot {

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
	
	@Builder.Default
	private int isWindows = 0;
	
	@Builder.Default
	private int isLinux = 0;
	
	private String profilesActive;
	
	@Builder.Default
	private int isOps = 0;
	
	@Builder.Default
	private int ki0440 = 0;
	
}