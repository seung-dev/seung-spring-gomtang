package seung.spring.gomtang.rest.kinsight.util;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import seung.java.kimchi.util.SLinkedHashMap;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Ki0440 {

	@Size(max = 36)
	@NotBlank
	private String request_code;
	
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
	
	@NotBlank
	private String memb_email;
	
}
