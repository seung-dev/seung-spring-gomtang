package seung.spring.boot.conf;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
//@Setter
public class SProperties {

	@Builder.Default
	private Properties environment = new Properties();
	
	@Builder.Default
	private Properties swagger = new Properties();
	
	@Builder.Default
	private List<Properties> datasource = new ArrayList<>();
	
	@Builder.Default
	private Properties jpa = new Properties();
	
	@Builder.Default
	private Properties jpaVendor = new Properties();
	
	@Builder.Default
	private Properties quartz = new Properties();
	
	@Builder.Default
	private Properties job = new Properties();
	
	@Builder.Default
	private Properties seung = new Properties();
	
}
