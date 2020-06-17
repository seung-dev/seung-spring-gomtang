package seung.spring.boot.conf.quartz.rest.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.SDate;
import seung.java.kimchi.exception.SKimchiException;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.spring.boot.conf.datasource.SMapperI;
import seung.spring.boot.conf.quartz.SQuartzH;
import seung.spring.boot.conf.quartz.rest.service.SQuartzS;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;

@Slf4j
@Service(value = "sQuartzS")
public class SQuartzSI implements SQuartzS {

    @Resource(name = "sMapperI")
    private SMapperI sMapperI;
    
    @Resource(name = "sQuartzH")
    private SQuartzH sQuartzH;
    
    
    /* (non-javadoc)
     * @see seung.spring.gomtang.quartz.service.SQuartzS#quartz0101(seung.spring.boot.conf.web.util.SRequest)
     */
    @SuppressWarnings("unchecked")
    @Override
    public SResponse quartz0101(SRequest sRequest) {
        
        log.debug("run");
        
        String requestCode = sRequest.getData().getString("request_code", "");
        
        SResponse sResponse = SResponse.builder()
                .request_code(requestCode)
                .data(sRequest.getData())
                .build()
                ;
        
        List<SLinkedHashMap> quartz0101 = null;
        try {
            
            log.debug("{}.sRequest: {}", requestCode, sRequest.toJsonString(true));
            
            quartz0101 = sMapperI.selectList("quartz0101");
            
            for(SLinkedHashMap job : quartz0101) {
                
                job.putAll(sQuartzH.detail(job.getString("job_group"), job.getString("job_name")));
                
            }// end of loop job
            
            sResponse.success();
            
        } catch (SKimchiException e) {
            log.error("(({})) Failed to get job list.", requestCode, e);
            sResponse.setError_message(ExceptionUtils.getStackTrace(e));
        } catch (SchedulerException e) {
            log.error("(({})) Failed to get job list.", requestCode, e);
            sResponse.setError_message(ExceptionUtils.getStackTrace(e));
        } finally {
            sResponse.putResult("quartz0101", quartz0101);
        }
        
        sResponse.setResponse_time(SDate.getDateString());
        return sResponse;
    }
    
    /* (non-javadoc)
     * @see seung.spring.gomtang.quartz.service.SQuartzS#quartz0111(seung.spring.boot.conf.web.util.SRequest)
     */
    @Override
    public SResponse quartz0111(SRequest sRequest) {
        
        log.debug("run");
        
        String requestCode = sRequest.getData().getString("request_code", "");
        
        SResponse sResponse = SResponse.builder()
                .request_code(requestCode)
                .data(sRequest.getData())
                .build()
                ;
        
        String quartz0111 = "";
        try {
            
            log.debug("{}.sRequest: {}", requestCode, sRequest.toJsonString(true));
            
            quartz0111 = sQuartzH.addSimple(
                    sRequest.getData().getString("job_group")
                    , sRequest.getData().getString("job_name")
                    , sRequest.getData().getString("dscr", "")
                    , sRequest.getData().getString("clss")
                    , sRequest.getData().getString("date_from", "")
                    , sRequest.getData().getString("date_to", "")
                    , sRequest.getData().getLong("intv", 1000 * 60l)
                    );
            
            sResponse.success();
            
        } catch (SKimchiException e) {
            log.error("(({})) Failed to add simple job.", requestCode, e);
            sResponse.setError_message(ExceptionUtils.getStackTrace(e));
        } catch (ClassNotFoundException e) {
            log.error("(({})) Failed to add simple job.", requestCode, e);
            sResponse.setError_message(ExceptionUtils.getStackTrace(e));
        } catch (SchedulerException e) {
            log.error("(({})) Failed to add simple job.", requestCode, e);
            sResponse.setError_message(ExceptionUtils.getStackTrace(e));
        } finally {
            sResponse.putResult("quartz0111", quartz0111);
        }
        
        sResponse.setResponse_time(SDate.getDateString());
        return sResponse;
    }
    
    /* (non-javadoc)
     * @see seung.spring.gomtang.quartz.service.SQuartzS#quartz0112(seung.spring.boot.conf.web.util.SRequest)
     */
    @Override
    public SResponse quartz0112(SRequest sRequest) {
        
        log.debug("run");
        
        String requestCode = sRequest.getData().getString("request_code", "");
        
        SResponse sResponse = SResponse.builder()
                .request_code(requestCode)
                .data(sRequest.getData())
                .build()
                ;
        
        String quartz0112 = "";
        try {
            
            log.debug("{}.sRequest: {}", requestCode, sRequest.toJsonString(true));
            
            quartz0112 = sQuartzH.addCron(
                    sRequest.getData().getString("job_group")
                    , sRequest.getData().getString("job_name")
                    , sRequest.getData().getString("dscr", "")
                    , sRequest.getData().getString("clss")
                    , sRequest.getData().getString("date_from")
                    , sRequest.getData().getString("date_to")
                    , sRequest.getData().getString("cron_expr")
                    );
            
            sResponse.success();
            
        } catch (SKimchiException e) {
            log.error("(({})) Failed to add cron job.", requestCode, e);
            sResponse.setError_message(ExceptionUtils.getStackTrace(e));
        } catch (ClassNotFoundException e) {
            log.error("(({})) Failed to add cron job.", requestCode, e);
            sResponse.setError_message(ExceptionUtils.getStackTrace(e));
        } catch (SchedulerException e) {
            log.error("(({})) Failed to add cron job.", requestCode, e);
            sResponse.setError_message(ExceptionUtils.getStackTrace(e));
        } finally {
            sResponse.putResult("quartz0112", quartz0112);
        }
        
        sResponse.setResponse_time(SDate.getDateString());
        return sResponse;
    }
    
    /* (non-javadoc)
     * @see seung.spring.gomtang.quartz.service.SQuartzS#quartz0121(seung.spring.boot.conf.web.util.SRequest)
     */
    @Override
    public SResponse quartz0121(SRequest sRequest) {
        
        log.debug("run");
        
        String requestCode = sRequest.getData().getString("request_code", "");
        
        SResponse sResponse = SResponse.builder()
                .request_code(requestCode)
                .data(sRequest.getData())
                .build()
                ;
        
        String quartz0121 = "";
        try {
            
            log.debug("{}.sRequest: {}", requestCode, sRequest.toJsonString(true));
            
            quartz0121 = sQuartzH.editSimple(
                    sRequest.getData().getString("job_group")
                    , sRequest.getData().getString("job_name")
                    , sRequest.getData().getString("date_from", "")
                    , sRequest.getData().getString("date_to", "")
                    , sRequest.getData().getLong("intv", 1000 * 60l)
                    );
            
            sResponse.success();
            
        } catch (SKimchiException e) {
            log.error("(({})) Failed to update simple job.", requestCode, e);
            sResponse.setError_message(ExceptionUtils.getStackTrace(e));
        } catch (SchedulerException e) {
            log.error("(({})) Failed to update simple job.", requestCode, e);
            sResponse.setError_message(ExceptionUtils.getStackTrace(e));
        } finally {
            sResponse.putResult("quartz0121", quartz0121);
        }
        
        sResponse.setResponse_time(SDate.getDateString());
        return sResponse;
    }
    
    /* (non-javadoc)
     * @see seung.spring.gomtang.quartz.service.SQuartzS#quartz0122(seung.spring.boot.conf.web.util.SRequest)
     */
    @Override
    public SResponse quartz0122(SRequest sRequest) {
        
        log.debug("run");
        
        String requestCode = sRequest.getData().getString("request_code", "");
        
        SResponse sResponse = SResponse.builder()
                .request_code(requestCode)
                .data(sRequest.getData())
                .build()
                ;
        
        String quartz0122 = "";
        try {
            
            log.debug("{}.sRequest: {}", requestCode, sRequest.toJsonString(true));
            
            quartz0122 = sQuartzH.editCron(
                    sRequest.getData().getString("job_group")
                    , sRequest.getData().getString("job_name")
                    , sRequest.getData().getString("date_from", "")
                    , sRequest.getData().getString("date_to", "")
                    , sRequest.getData().getString("cron_expr")
                    );
            
            sResponse.success();
            
        } catch (SKimchiException e) {
            log.error("(({})) Failed to update cron job.", requestCode, e);
            sResponse.setError_message(ExceptionUtils.getStackTrace(e));
        } catch (SchedulerException e) {
            log.error("(({})) Failed to update cron job.", requestCode, e);
            sResponse.setError_message(ExceptionUtils.getStackTrace(e));
        } finally {
            sResponse.putResult("quartz0122", quartz0122);
        }
        
        sResponse.setResponse_time(SDate.getDateString());
        return sResponse;
    }
    
    /* (non-javadoc)
     * @see seung.spring.gomtang.quartz.service.SQuartzS#quartz0131(seung.spring.boot.conf.web.util.SRequest)
     */
    @Override
    public SResponse quartz0131(SRequest sRequest) {
        
        log.debug("run");
        
        String requestCode = sRequest.getData().getString("request_code", "");
        
        SResponse sResponse = SResponse.builder()
                .request_code(requestCode)
                .data(sRequest.getData())
                .build()
                ;
        
        int quartz0131 = 0;
        try {
            
            log.debug("{}.sRequest: {}", requestCode, sRequest.toJsonString(true));
            
            quartz0131 = sQuartzH.delete(
                    sRequest.getData().getString("job_group")
                    , sRequest.getData().getString("job_name")
                    );
            
            sResponse.success();
            
        } catch (SKimchiException e) {
            log.error("(({})) Failed to pause job.", requestCode, e);
            sResponse.setError_message(ExceptionUtils.getStackTrace(e));
        } catch (SchedulerException e) {
            log.error("(({})) Failed to pause job.", requestCode, e);
            sResponse.setError_message(ExceptionUtils.getStackTrace(e));
        } finally {
            sResponse.putResult("quartz0131", quartz0131);
        }
        
        sResponse.setResponse_time(SDate.getDateString());
        return sResponse;
    }
    
    /* (non-javadoc)
     * @see seung.spring.gomtang.quartz.service.SQuartzS#quartz0110(seung.spring.boot.conf.web.util.SRequest)
     */
    @Override
    public SResponse quartz0141(SRequest sRequest) {
        
        log.debug("run");
        
        String requestCode = sRequest.getData().getString("request_code", "");
        
        SResponse sResponse = SResponse.builder()
                .request_code(requestCode)
                .data(sRequest.getData())
                .build()
                ;
        
        String quartz0141 = "";
        try {
            
            log.debug("{}.sRequest: {}", requestCode, sRequest.toJsonString(true));
            
            quartz0141 = sQuartzH.run(
                    sRequest.getData().getString("job_group")
                    , sRequest.getData().getString("job_name")
                    );
            
            sResponse.success();
            
        } catch (SKimchiException e) {
            log.error("(({})) Failed to run job.", requestCode, e);
            sResponse.setError_message(ExceptionUtils.getStackTrace(e));
        } catch (SchedulerException e) {
            log.error("(({})) Failed to run job.", requestCode, e);
            sResponse.setError_message(ExceptionUtils.getStackTrace(e));
        } finally {
            sResponse.putResult("quartz0141", quartz0141);
        }
        
        sResponse.setResponse_time(SDate.getDateString());
        return sResponse;
    }
    
    /* (non-javadoc)
     * @see seung.spring.gomtang.quartz.service.SQuartzS#quartz0111(seung.spring.boot.conf.web.util.SRequest)
     */
    @Override
    public SResponse quartz0142(SRequest sRequest) {
        
        log.debug("run");
        
        String requestCode = sRequest.getData().getString("request_code", "");
        
        SResponse sResponse = SResponse.builder()
                .request_code(requestCode)
                .data(sRequest.getData())
                .build()
                ;
        
        String quartz0142 = "";
        try {
            
            log.debug("{}.sRequest: {}", requestCode, sRequest.toJsonString(true));
            
            quartz0142 = sQuartzH.trigger(
                    sRequest.getData().getString("job_group")
                    , sRequest.getData().getString("job_name")
                    );
            
            sResponse.success();
            
        } catch (SKimchiException e) {
            log.error("(({})) Failed to trigger job.", requestCode, e);
            sResponse.setError_message(ExceptionUtils.getStackTrace(e));
        } catch (SchedulerException e) {
            log.error("(({})) Failed to trigger job.", requestCode, e);
            sResponse.setError_message(ExceptionUtils.getStackTrace(e));
        } finally {
            sResponse.putResult("quartz0142", quartz0142);
        }
        
        sResponse.setResponse_time(SDate.getDateString());
        return sResponse;
    }
    
    /* (non-javadoc)
     * @see seung.spring.gomtang.quartz.service.SQuartzS#quartz0120(seung.spring.boot.conf.web.util.SRequest)
     */
    @Override
    public SResponse quartz0143(SRequest sRequest) {
        
        log.debug("run");
        
        String requestCode = sRequest.getData().getString("request_code", "");
        
        SResponse sResponse = SResponse.builder()
                .request_code(requestCode)
                .data(sRequest.getData())
                .build()
                ;
        
        String quartz0143 = "";
        try {
            
            log.debug("{}.sRequest: {}", requestCode, sRequest.toJsonString(true));
            
            quartz0143 = sQuartzH.pause(
                    sRequest.getData().getString("job_group")
                    , sRequest.getData().getString("job_name")
                    );
            
            sResponse.success();
            
        } catch (SKimchiException e) {
            log.error("(({})) Failed to pause job.", requestCode, e);
            sResponse.setError_message(ExceptionUtils.getStackTrace(e));
        } catch (SchedulerException e) {
            log.error("(({})) Failed to pause job.", requestCode, e);
            sResponse.setError_message(ExceptionUtils.getStackTrace(e));
        } finally {
            sResponse.putResult("quartz0143", quartz0143);
        }
        
        sResponse.setResponse_time(SDate.getDateString());
        return sResponse;
    }
    
}
