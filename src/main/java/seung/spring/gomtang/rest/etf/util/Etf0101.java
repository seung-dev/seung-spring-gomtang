package seung.spring.gomtang.rest.etf.util;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.Getter;
import lombok.Setter;
import seung.java.kimchi.util.SLinkedHashMap;

@Setter
@Getter
public class Etf0101 {

	@Size(max = 36)
	@NotEmpty
	private String request_code;
	
	@Size(min = 8, max = 10)
	@NotEmpty
	private String mmnt_date;
	
	@Size(max = 1)
	@NotEmpty
	private String mmnt_unit;
	
	@Min(value = 1)
	@NotEmpty
	private int mmnt_scope;
	
	@Size(min =3, max = 3)
	@NotEmpty
	private String mmnt_min_max;
	
	@NotEmpty
	private float mmnt_threshold;
	
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
