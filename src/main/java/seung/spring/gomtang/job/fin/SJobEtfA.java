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
import seung.java.kimchi.SDate;
import seung.spring.gomtang.job.fin.service.SKiwoomSI;

@Slf4j
@Component
public class SJobEtfA extends QuartzJobBean implements InterruptableJob {

    @Resource(name = "sKiwoomS")
    private SKiwoomSI sKiwoomS;
    
    @Override
    public void interrupt() throws UnableToInterruptJobException {
        log.error("SJobDart was interrupted.");
    }
    
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        
        int timesMax = 3;
        int timesTry = -1;
        
        // kw10000
        if(SDate.getDateInteger("HH") > 18) {
            timesTry = 0;
            try {
                while(timesTry++ < timesMax) {
                    if("0000".equals(sKiwoomS.kw10000(jobDataMap.getString("job_group"), jobDataMap.getString("job_name")))) {
                        break;
                    }
                    Thread.sleep(1000 * 60 * 10);
                }
            } catch (InterruptedException e) {
                log.error("Failed to do job {}.{}.", jobDataMap.getString("job_group"), jobDataMap.getString("job_name"), e);
            }
        }
        // kw10000
        
        // tr10001
        if(SDate.getDateInteger("HH") > 18) {
            timesTry = 0;
            try {
                while(timesTry++ < timesMax) {
                    if("0000".equals(sKiwoomS.tr10001(jobDataMap.getString("job_group"), jobDataMap.getString("job_name")))) {
                        break;
                    }
                    Thread.sleep(1000 * 60 * 10);
                }
            } catch (InterruptedException e) {
                log.error("Failed to do job {}.{}.", jobDataMap.getString("job_group"), jobDataMap.getString("job_name"), e);
            }
        }
        // tr10001
        
        // tr40005
        timesTry = 0;
        try {
            while(timesTry++ < timesMax) {
                if("0000".equals(sKiwoomS.tr40005(jobDataMap.getString("job_group"), jobDataMap.getString("job_name")))) {
                    break;
                }
                Thread.sleep(1000 * 60 * 10);
            }
        } catch (InterruptedException e) {
            log.error("Failed to do job {}.{}.", jobDataMap.getString("job_group"), jobDataMap.getString("job_name"), e);
        }
        // tr40005
        
    }
    
}
