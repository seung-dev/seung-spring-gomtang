package seung.spring.boot.conf.web.util;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import seung.java.kimchi.util.SLinkedHashMap;

@Builder
@Getter
@Setter
public class SResponseError {

	private int status;
	
	private long timestamp;
	
	private String error;
	
	private String message;
	
	private String trace;
	
	private String path;
	
	private List<SLinkedHashMap> errors;
	
	@Builder.Default
	private double requestTime = new Date().getTime();
	private double responseTime;
	
}
