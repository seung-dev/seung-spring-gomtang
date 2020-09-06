package seung.spring.gomtang.job.fin;

//import org.quartz.InterruptableJob;
//import org.quartz.JobDataMap;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//import org.quartz.UnableToInterruptJobException;
//import org.springframework.scheduling.quartz.QuartzJobBean;

//@Slf4j
//@Component
public class SJobEtfA {
//public class SJobEtfA extends QuartzJobBean implements InterruptableJob {
//
//	@Resource(name = "sKiwoomS")
//	private SKiwoomSI sKiwoomS;
//	
//	@Override
//	public void interrupt() throws UnableToInterruptJobException {
//		log.error("SJobEtfA was interrupted.");
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
//		String kw00000 = "";
//		String sr66666 = "";
//		
//		// kw10000
//		kw00000 = "";
//		sr66666 = "";
//		timesTry = 0;
//		try {
//			sKiwoomS.kw10000(jobDataMap.getString("job_group"), jobDataMap.getString("job_name"));
//			while(timesTry++ < timesMax) {
//				kw00000 = sKiwoomS.kw00000(jobDataMap.getString("job_group"), jobDataMap.getString("job_name"));
//				if(!"0000".equals(kw00000)) {
//					sKiwoomS.sr99999(jobDataMap.getString("job_group"), jobDataMap.getString("job_name"));
//					sr66666 = sKiwoomS.sr66666(jobDataMap.getString("job_group"), jobDataMap.getString("job_name"));
//					if(!"0000".equals(sr66666)) {
//						continue;
//					}
//				}
//				if("0000".equals(sKiwoomS.kw10000(jobDataMap.getString("job_group"), jobDataMap.getString("job_name")))) {
//					break;
//				}
//				Thread.sleep(1000 * 60 * 10);
//			}
//		} catch (InterruptedException e) {
//			log.error("Failed to do job {}.{}.", jobDataMap.getString("job_group"), jobDataMap.getString("job_name"), e);
//		}
//		// kw10000
//		
//		// tr10001
//		kw00000 = "";
//		sr66666 = "";
//		timesTry = 0;
//		try {
//			while(timesTry++ < timesMax) {
//				kw00000 = sKiwoomS.kw00000(jobDataMap.getString("job_group"), jobDataMap.getString("job_name"));
//				if(!"0000".equals(kw00000)) {
//					sKiwoomS.sr99999(jobDataMap.getString("job_group"), jobDataMap.getString("job_name"));
//					sr66666 = sKiwoomS.sr66666(jobDataMap.getString("job_group"), jobDataMap.getString("job_name"));
//					if(!"0000".equals(sr66666)) {
//						continue;
//					}
//				}
//				if("0000".equals(sKiwoomS.tr10001(jobDataMap.getString("job_group"), jobDataMap.getString("job_name")))) {
//					break;
//				}
//				Thread.sleep(1000 * 60 * 10);
//			}
//		} catch (InterruptedException e) {
//			log.error("Failed to do job {}.{}.", jobDataMap.getString("job_group"), jobDataMap.getString("job_name"), e);
//		}
//		// tr10001
//		
//		// tr10081
//		kw00000 = "";
//		sr66666 = "";
//		timesTry = 0;
//		try {
//			while(timesTry++ < timesMax) {
//				kw00000 = sKiwoomS.kw00000(jobDataMap.getString("job_group"), jobDataMap.getString("job_name"));
//				if(!"0000".equals(kw00000)) {
//					sKiwoomS.sr99999(jobDataMap.getString("job_group"), jobDataMap.getString("job_name"));
//					sr66666 = sKiwoomS.sr66666(jobDataMap.getString("job_group"), jobDataMap.getString("job_name"));
//					if(!"0000".equals(sr66666)) {
//						continue;
//					}
//				}
//				if("0000".equals(sKiwoomS.tr10081(jobDataMap.getString("job_group"), jobDataMap.getString("job_name")))) {
//					break;
//				}
//				Thread.sleep(1000 * 60 * 10);
//			}
//		} catch (InterruptedException e) {
//			log.error("Failed to do job {}.{}.", jobDataMap.getString("job_group"), jobDataMap.getString("job_name"), e);
//		}
//		// tr10081
//		
//		// tr40005
//		kw00000 = "";
//		sr66666 = "";
//		timesTry = 0;
//		try {
//			while(timesTry++ < timesMax) {
//				kw00000 = sKiwoomS.kw00000(jobDataMap.getString("job_group"), jobDataMap.getString("job_name"));
//				if(!"0000".equals(kw00000)) {
//					sKiwoomS.sr99999(jobDataMap.getString("job_group"), jobDataMap.getString("job_name"));
//					sr66666 = sKiwoomS.sr66666(jobDataMap.getString("job_group"), jobDataMap.getString("job_name"));
//					if(!"0000".equals(sr66666)) {
//						continue;
//					}
//				}
//				if("0000".equals(sKiwoomS.tr40005(jobDataMap.getString("job_group"), jobDataMap.getString("job_name")))) {
//					break;
//				}
//				Thread.sleep(1000 * 60 * 10);
//			}
//		} catch (InterruptedException e) {
//			log.error("Failed to do job {}.{}.", jobDataMap.getString("job_group"), jobDataMap.getString("job_name"), e);
//		}
//		// tr40005
//		
//	}
	
}
