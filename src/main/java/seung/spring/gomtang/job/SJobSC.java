package seung.spring.gomtang.job;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import seung.spring.gomtang.job.fin.service.SEbestSI;
import seung.spring.gomtang.job.fin.service.SNaverSI;
import seung.spring.gomtang.job.handler.SJobH;

@Component
public class SJobSC {

	@Resource(name = "sJobH")
	private SJobH sJobH;
	
	@Resource(name = "sEbestS")
	private SEbestSI sEbestS;
	
	@Resource(name = "sNaverS")
	private SNaverSI sNaverS;
	
//	@Scheduled(fixedRate = 1000 * 60 * 10)
//	void everyOneMinute() {
//		sJobH.ki0440();
//	}
	
//	@Scheduled(cron = "0 30 7 * * *")
//	void ebestRestart() {
//		sEbestS.sr99999("ebest", "sr99999", "1");
//		sEbestS.sr99999("ebest", "sr99999", "2");
//		sEbestS.sr66666("ebest", "sr66666", "1");
//		sEbestS.sr66666("ebest", "sr66666", "2");
//	}
	
	@Scheduled(cron = "0 0 20 * * MON-FRI")
	void ebestMiningDaily() {
//		sEbestS.t8413("ebest", "t8413");
		sEbestS.t8430("ebest", "t8430");
		sEbestS.t9945("ebest", "t9945");
		sEbestS.t1305("ebest", "t1305");
		sEbestS.t1903("ebest", "t1903");
		sEbestS.post("ebest", "post");
	}
	
//	@Scheduled(cron = "0 0 20 * * MON-FRI")
//	void naverMiningDaily() {
//		sNaverS.n0101("naver", "n0101");
//		sNaverS.n0102("naver", "n0102");
//		sNaverS.n0104("naver", "n0104");
//	}
	
	@Scheduled(fixedDelay = 1000 * 60 * 10)
	void test() {
//		sEbestS.sr99999("ebest", "sr99999", "1");
//		sEbestS.sr99999("ebest", "sr99999", "2");
//		sEbestS.sr66666("ebest", "sr66666", "1");
//		sEbestS.sr66666("ebest", "sr66666", "2");
//		sEbestS.t8430("ebest", "t8430");
//		sEbestS.t9945("ebest", "t9945");
//		sEbestS.t1305("ebest", "t1305");
//		sEbestS.t1903("ebest", "t1903");
	}
	
}
