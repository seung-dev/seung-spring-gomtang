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
public class SJobKiwoom extends QuartzJobBean implements InterruptableJob {

    @Resource(name = "sKiwoomS")
    private SKiwoomSI sKiwoomS;
    
    @Override
    public void interrupt() throws UnableToInterruptJobException {
        log.error("SJobDart was interrupted.");
    }
    
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        
        // kw10000
        sKiwoomS.kw10000(
                jobDataMap.getString("job_group")
                , jobDataMap.getString("job_name")
                );
        // kw10000
        
        // tr10001
        sKiwoomS.tr10001(
                jobDataMap.getString("job_group")
                , jobDataMap.getString("job_name")
                );
        // tr10001
        
        // tr40005
        sKiwoomS.tr40005(
                jobDataMap.getString("job_group")
                , jobDataMap.getString("job_name")
                );
        // tr40005
        
    }
    
}
