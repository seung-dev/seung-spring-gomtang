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
import seung.spring.gomtang.job.fin.service.SNaverSI;

@Slf4j
@Component
public class SJobEtfB extends QuartzJobBean implements InterruptableJob {

    @Resource(name = "sNaverS")
    private SNaverSI sNaverS;
    
    @Override
    public void interrupt() throws UnableToInterruptJobException {
        log.error("SJobDart was interrupted.");
    }
    
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        
        int timesMax = 3;
        int timesTry = 0;
        
        // n0101
        timesTry = 0;
        try {
            while(timesTry++ < timesMax) {
                if("0000".equals(sNaverS.n0101(jobDataMap.getString("job_group"), jobDataMap.getString("job_name")))) {
                    break;
                }
                Thread.sleep(1000 * 60 * 1);
            }
        } catch (InterruptedException e) {
            log.error("Failed to do job {}.{}.", jobDataMap.getString("job_group"), jobDataMap.getString("job_name"), e);
        }
        // n0101
        
        // n0102
        timesTry = 0;
        try {
            while(timesTry++ < timesMax) {
                if("0000".equals(sNaverS.n0102(jobDataMap.getString("job_group"), jobDataMap.getString("job_name")))) {
                    break;
                }
                Thread.sleep(1000 * 60 * 1);
            }
        } catch (InterruptedException e) {
            log.error("Failed to do job {}.{}.", jobDataMap.getString("job_group"), jobDataMap.getString("job_name"), e);
        }
        // n0102
        
    }
    
}
