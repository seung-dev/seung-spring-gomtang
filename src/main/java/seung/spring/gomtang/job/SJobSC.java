package seung.spring.gomtang.job;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import seung.spring.gomtang.job.fin.service.SEbestSI;

@Component
public class SJobSC {

	@Resource(name = "sEbestS")
	private SEbestSI sEbestS;
	
	@Scheduled(cron = "0 0 9 * * *")
	void ebestRestart() {
//		sEbestS.sr99999("ebest", "sr99999");
//		sEbestS.sr66666("ebest", "sr66666");
	}
	
	@Scheduled(cron = "0 0 20 * * MON-FRI")
	void ebestMiningDaily() {
//		sEbestS.t8413("ebest", "t8413");
//		sEbestS.t1903("ebest", "t1903");
	}
	
	@Scheduled(fixedDelay = 1000 * 60)
	void ebestMiningPrev() {
//		sEbestS.t8413_prev("ebest", "t8413_prev");
//		sEbestS.t1903_prev("ebest", "t8413_prev");
	}
	
}
