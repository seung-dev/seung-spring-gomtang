package seung.spring.boot.conf.web.util;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SReflect {

	@NotEmpty
	private String request_code;
	
	private String data;
	
}
