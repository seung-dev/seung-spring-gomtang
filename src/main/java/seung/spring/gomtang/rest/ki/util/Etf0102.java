package seung.spring.gomtang.rest.ki.util;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Etf0102 {

	@Size(max = 36)
	@NotEmpty
	private String request_code;
	
}
