package seung.spring.gomtang.job.fin;

//import org.quartz.InterruptableJob;
//import org.quartz.JobDataMap;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.quartz.UnableToInterruptJobException;
//import org.springframework.scheduling.quartz.QuartzJobBean;

//@Slf4j
//@Component
public class SJobEtfC {
//public class SJobEtfC extends QuartzJobBean implements InterruptableJob {
//
//	@Resource(name = "sKiwoomS")
//	private SKiwoomSI sKiwoomS;
//	
//	@Override
//	public void interrupt() throws UnableToInterruptJobException {
//		log.error("SJobEtfC was interrupted.");
//	}
//	
//	@Override
//	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
//		
//		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
//		
//		int timesMax = 3;
//		int timesTry = -1;
//		
//		// sr99999
//		String sr99999 = "";
//		timesTry = 0;
//		try {
//			while(timesTry++ < timesMax) {
//				sr99999 = sKiwoomS.sr99999(jobDataMap.getString("job_group"), jobDataMap.getString("job_name"));
//				if(SCode.SUCCESS.equals(sr99999)) {
//					break;
//				}
//				Thread.sleep(1000 * 60 * 10);
//			}
//		} catch (InterruptedException e) {
//			log.error("Failed to do job {}.{}.", jobDataMap.getString("job_group"), jobDataMap.getString("job_name"), e);
//		}
//		if(!SCode.SUCCESS.equals(sr99999)) {
//			return;
//		}
//		// sr99999
//		
//		// sr33333
//		String sr33333 = "";
//		timesTry = 0;
//		try {
//			while(timesTry++ < timesMax) {
//				sr33333 = sKiwoomS.sr33333(jobDataMap.getString("job_group"), jobDataMap.getString("job_name"));
//				if(SCode.SUCCESS.equals(sr33333)) {
//					break;
//				}
//				Thread.sleep(1000 * 60 * 10);
//			}
//		} catch (InterruptedException e) {
//			log.error("Failed to do job {}.{}.", jobDataMap.getString("job_group"), jobDataMap.getString("job_name"), e);
//		}
//		if(!SCode.SUCCESS.equals(sr33333)) {
//			return;
//		}
//		// sr33333
//		
//		// sr66666
//		timesTry = 0;
//		try {
//			while(timesTry++ < timesMax) {
//				if(SCode.SUCCESS.equals(sKiwoomS.sr66666(jobDataMap.getString("job_group"), jobDataMap.getString("job_name")))) {
//					break;
//				}
//				Thread.sleep(1000 * 60 * 10);
//			}
//		} catch (InterruptedException e) {
//			log.error("Failed to do job {}.{}.", jobDataMap.getString("job_group"), jobDataMap.getString("job_name"), e);
//		}
//		// sr66666
//		
//	}
	
}
