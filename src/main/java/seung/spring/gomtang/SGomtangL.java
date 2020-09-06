package seung.spring.gomtang;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.SString;
import seung.java.kimchi.util.SKimchiException;
import seung.spring.boot.conf.SProperties;
import seung.spring.gomtang.job.fin.service.SEbestSI;

@Component
@Slf4j
public class SGomtangL {

	@Resource(name="sProperties")
	private SProperties sProperties;
	
	@Resource(name = "sEbestS")
	private SEbestSI sEbestS;
	
	@PostConstruct
	public void postRun() throws SKimchiException {
		
		log.debug("run");
		
		log.info(SString.toJson(sProperties, true));
		
		new File("var/tr10081").mkdirs();
		new File("var/tr40005").mkdirs();
		
		try {
//			sEbestS.t1903_prev2("a");
//			sEbestS.t8413_prev2("a");
//			Thread.sleep(3000);
//			sEbestS.t8413_prev2("b");
//			Thread.sleep(3000);
//			sEbestS.t8413_prev2("c");
//			Thread.sleep(3000);
//			sEbestS.t8413_prev2("d");
//			Thread.sleep(3000);
//			sEbestS.t8413_prev2("e");
		} catch (Exception e) {
			// TODO: handle exception
		}
		// job init
//		sJobI.initJob();
		
	}
	
	@PreDestroy
	public void preShutdown() {
		
		log.debug("run");
		
		log.info(SString.toJson(sProperties, true));
		
	}
	
}
