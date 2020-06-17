package seung.spring.gomtang.job;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.exception.SKimchiException;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.spring.boot.conf.datasource.SMapperI;
import seung.spring.boot.conf.quartz.SQuartzH;

/**
 * <pre>
 * job initialization
 * </pre>
 * 
 * @author stoas
 */
@Slf4j
@Component("sJobI")
public class SJobI {

    @Resource(name = "sMapperI")
    private SMapperI sMapperI;
    
    @Resource(name = "sQuartzH")
    private SQuartzH sQuartzH;
    
    public void init() {
        
        log.info("run");
        
        List<SLinkedHashMap> cronJobs = new ArrayList<>();
        cronJobs.add(
                new SLinkedHashMap()
                    .add("group", "fin")
                    .add("name", "dart")
                    .add("dscr", "DART API 수집.")
                    .add("clss", "seung.spring.gomtang.job.fin.SJobDart")
                    .add("dateFrom", "")
                    .add("dateTo", "")
                    .add("cronExpr", "0 0 1 ? * MON-FRI *")
                );
        cronJobs.add(
                new SLinkedHashMap()
                .add("group", "fin")
                .add("name", "kiwoom")
                .add("dscr", "키움 API 수집.")
                .add("clss", "seung.spring.gomtang.job.fin.SJobKiwoom")
                .add("dateFrom", "")
                .add("dateTo", "")
                .add("cronExpr", "0 0 1,16 ? * MON-FRI *")
                );
        
        for(SLinkedHashMap job : cronJobs) {
            
            try {
                
                if(0 == sMapperI.selectList("quartz0102", job).size()) {
                    sQuartzH.addCron(
                            job.getString("group", "")
                            , job.getString("name", "")
                            , job.getString("dscr", "")
                            , job.getString("clss", "")
                            , job.getString("dateFrom", "")
                            , job.getString("dateTo", "")
                            , job.getString("cronExpr", "")
                            );
                }
                
            } catch (ClassNotFoundException e) {
                log.error("Failed to add cron job.", e);
            } catch (SchedulerException e) {
                log.error("Failed to add cron job.", e);
            } catch (SKimchiException e) {
                log.error("Failed to add cron job.", e);
            }
            
        }// end of cron jobs
        
    }
    
}
