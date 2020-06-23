package seung.spring.gomtang;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.SString;
import seung.java.kimchi.util.SKimchiException;
import seung.spring.boot.conf.SProperties;
import seung.spring.gomtang.job.SJobI;

@Slf4j
@Component
public class SGomtangL {

	@Resource(name="sProperties")
	private SProperties sProperties;
	
	@Resource(name="sJobI")
	private SJobI sJobI;
	
	@PostConstruct
	public void postRun() throws SKimchiException {
		
		log.debug("run");
		
		log.info(SString.toJson(sProperties, true));
		
		// job init
		sJobI.initJob();
		
	}
	
	@PreDestroy
	public void preShutdown() {
		
		log.debug("run");
		
		log.info(SString.toJson(sProperties, true));
		
	}
	
}
