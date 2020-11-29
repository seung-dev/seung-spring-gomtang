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
import seung.spring.gomtang.job.fin.service.SNaverSI;

@Component
@Slf4j
public class SGomtangL {

	@Resource(name="sProperties")
	private SProperties sProperties;
	
	@Resource(name = "sEbestS")
	private SEbestSI sEbestS;
	
	@Resource(name = "sNaverS")
	private SNaverSI sNaverS;
	
	@PostConstruct
	public void postRun() throws SKimchiException {
		
		log.debug("run");
		
		log.info(SString.toJson(sProperties, true));
		
		new File("var/tr10081").mkdirs();
		new File("var/tr40005").mkdirs();
		
		try {
//			sNaverS.n0104("naver", "n0104");
//			sEbestS.t8430("ebest", "t8430");
//			sEbestS.t9945("ebest", "t9945");
//			sEbestS.t1305("ebest", "t1305");
//			sEbestS.t1903("ebest", "t1903");
//			sEbestS.post("ebest", "post");
		} catch (Exception e) {
			log.error(e.getMessage());
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
