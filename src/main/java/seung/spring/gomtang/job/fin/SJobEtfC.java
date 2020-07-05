package seung.spring.gomtang.job.fin;

import javax.annotation.Resource;

import org.quartz.InterruptableJob;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import seung.spring.gomtang.job.fin.service.SKiwoomSI;

@Slf4j
@Component
public class SJobEtfC extends QuartzJobBean implements InterruptableJob {

	@Resource(name = "sKiwoomS")
	private SKiwoomSI sKiwoomS;
	
	@Override
	public void interrupt() throws UnableToInterruptJobException {
		log.error("SJobEtfC was interrupted.");
	}
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		
		int timesMax = 3;
		int timesTry = -1;
		
		// sr99999
		String sr99999 = "";
		timesTry = 0;
		try {
			while(timesTry++ < timesMax) {
				sr99999 = sKiwoomS.sr99999(jobDataMap.getString("job_group"), jobDataMap.getString("job_name"));
				if("0000".equals(sr99999)) {
					break;
				}
				Thread.sleep(1000 * 60 * 10);
			}
		} catch (InterruptedException e) {
			log.error("Failed to do job {}.{}.", jobDataMap.getString("job_group"), jobDataMap.getString("job_name"), e);
		}
		if(!"0000".equals(sr99999)) {
			return;
		}
		// sr99999
		
		// sr66666
		timesTry = 0;
		try {
			while(timesTry++ < timesMax) {
				if("0000".equals(sKiwoomS.sr66666(jobDataMap.getString("job_group"), jobDataMap.getString("job_name")))) {
					break;
				}
				Thread.sleep(1000 * 60 * 10);
			}
		} catch (InterruptedException e) {
			log.error("Failed to do job {}.{}.", jobDataMap.getString("job_group"), jobDataMap.getString("job_name"), e);
		}
		// sr66666
		
	}
	
}