package seung.spring.gomtang.job.handler;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.SString;
import seung.spring.boot.util.SBoot;
import seung.spring.gomtang.job.fin.service.SKi04S;

@Component("sJobH")
@Slf4j
public class SJobH {

	@Resource(name = "sBoot")
	private SBoot sBoot;
	
	@Resource(name = "sKi04S")
	private SKi04S sKi04S;
	
	@Async
	public void ki0440() {
		
		log.debug("run");
		
		String requestCode = SString.getUUID();
		String jobSet = "ki";
		String jobName = "ki0440";
		String jobCode = String.format("%s.%s", jobSet, jobName);
		
		if(1 == sBoot.getKi0440()) {
			log.info(
					"({}) {}=running"
					, requestCode
					, jobCode
					);
			return;
		}
		
		log.info(
				"({}) {}={}"
				, requestCode
				, jobCode
				, "begin"
				);
		
		try {
			
			while(true) {
				
				// 동적상태 활성화
				sBoot.setKi0440(1);
				
				// 회원 보유 현황
				sKi04S.ki0430("ki", "ki0430");
				
				break;
				
			}// end of while
			
		} catch (Exception e) {
			log.error(
					"({}) {}.error="
					, requestCode
					, jobCode
					, e
					);
		} finally {
			// 상태변경 - 대기중
			sBoot.setKi0440(0);
		}
		
		log.info(
				"({}) {}={}"
				, requestCode
				, jobCode
				, "end"
				);
	}
	
}
