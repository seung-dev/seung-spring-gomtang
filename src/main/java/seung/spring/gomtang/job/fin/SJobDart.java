package seung.spring.gomtang.job.fin;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.quartz.InterruptableJob;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.SString;
import seung.java.kimchi.exception.SKimchiException;
import seung.java.kimchi.http.SHttp;
import seung.java.kimchi.http.SHttpRequest;
import seung.java.kimchi.http.SHttpResponse;
import seung.java.kimchi.http.SRequestMethod;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.spring.boot.conf.SProperties;
import seung.spring.boot.conf.datasource.SMapperI;
import seung.spring.boot.conf.web.util.SResponse;

@Slf4j
@Component
public class SJobDart extends QuartzJobBean implements InterruptableJob {

    @Resource(name = "sProperties")
    private SProperties sProperties;
    
    @Resource(name = "sMapperI")
    private SMapperI sMapperI;
    
    @Override
    public void interrupt() throws UnableToInterruptJobException {
        log.error("SJobDart was interrupted.");
    }
    
    @SuppressWarnings("unchecked")
//    @Transactional(propagation=Propagation.NEVER)
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        SLinkedHashMap queryMap = null;
        
        String schdCode = "";
        
        schdCode = sMapperI.selectOne("schd_code").getString("schd_code", "");
        queryMap = new SLinkedHashMap(jobDataMap)
                .add("schd_code", schdCode)
                .add("schd_name", "d0101")
                .add("error_code", "E999")
                .add("message", "")
                .add("d0101_IR", 0)
                .add("d0101_UR", 0)
                ;
        try {
            
            log.info(
                    "{}.{}.{}.{} ((START))"
                    , queryMap.getString("job_group", "")
                    , queryMap.getString("job_name", "")
                    , queryMap.getString("schd_name", "")
                    , queryMap.getString("schd_code", "")
                    );
            
            queryMap.put("job_data", queryMap.toJsonString());
            sMapperI.insert("job_hist_run", queryMap);
            
            String requestCode = SString.getUUID();
            SHttpRequest sHttpRequest = SHttpRequest
                    .builder()
                    .url("http://127.0.0.1:11131/rest/etf/d0101")
                    .url(sProperties.getJob().getProperty("seung.job.dart.d0101.url", ""))
                    .requestMethod(SRequestMethod.POST)
//                    .dataMap(Pair.of("request_code", requestCode))
                    .build()
                    ;
            
            SHttpResponse sHttpResponse = SHttp.request(sHttpRequest);
            
            if(HttpStatus.OK.value() != sHttpResponse.getResponseCode()) {
                throw new JobExecutionException(String.format(
                        "Failed to call api. Response code is (%d)."
                        , sHttpResponse.getResponseCode()
                        ));
            }
            
            SLinkedHashMap etfD0101 = new SLinkedHashMap(new String(sHttpResponse.getResponseBody(), sHttpResponse.getResponseCharset()));
            
            if(!SResponse.SUCCESS.equals(etfD0101.getString("error_code", ""))) {
                throw new JobExecutionException(String.format(
                        "Failed to call api. Error code is (%s)."
                        , etfD0101.getString("error_code", "")
                        ));
            }
            
            int logging = 0;
            int d0101_IR = 0;
            int d0101_UR = 0;
            int d0101_DO_NOTHING = 0;
            int d0101_IGNORE = 0;
            SLinkedHashMap d0101_SR = null;
            for(SLinkedHashMap corp : etfD0101.getSLinkedHashMap("result").getListSLinkedHashMap("d0101")) {
                corp.put("corp_hash", corp.toJsonString().hashCode());
                d0101_SR = sMapperI.selectOne("d0101_SR", corp);
                if(d0101_SR == null) {
                    d0101_IR += sMapperI.insert("d0101_IR", corp);
                } else if(corp.getInt("corp_hash", -1) == d0101_SR.getInt("corp_hash", 0)) {
                    d0101_DO_NOTHING++;
                } else if(corp.getInt("corp_hash", -1) != d0101_SR.getInt("corp_hash", 0)) {
                    d0101_UR += sMapperI.insert("d0101_UR", corp);
                } else {
                    d0101_IGNORE++;
                }
                if(++logging % 1000 == 0) {
                    log.info(
                            "{}.{}.{}.{}.message {}"
                            , queryMap.getString("job_group", "")
                            , queryMap.getString("job_name", "")
                            , queryMap.getString("schd_name", "")
                            , queryMap.getString("schd_code", "")
                            , String.format("d0101IR=%d, d0101UR=%d, d0101_DO_NOTHING=%d, d0101_IGNORE=%d", d0101_IR, d0101_UR, d0101_DO_NOTHING, d0101_IGNORE)
                            );
                }
            }
            
            queryMap.put(
                    "message"
                    , String.format("d0101IR=%d, d0101UR=%d, d0101_DO_NOTHING=%d, d0101_IGNORE=%d", d0101_IR, d0101_UR, d0101_DO_NOTHING, d0101_IGNORE)
                    );
            queryMap.put("error_code", "0000");
            
        } catch (SKimchiException e) {
            log.error("Failed to call d0101.", e);
            queryMap.put("message", ExceptionUtils.getStackTrace(e));
        } catch (UnsupportedEncodingException e) {
            log.error("Failed to call d0101.", e);
            queryMap.put("message", ExceptionUtils.getStackTrace(e));
        } finally {
            log.info(
                    "{}.{}.{}.{}.error_code {}"
                    , queryMap.getString("job_group", "")
                    , queryMap.getString("job_name", "")
                    , queryMap.getString("schd_name", "")
                    , queryMap.getString("schd_code", "")
                    , queryMap.getString("error_code", "")
                    );
            sMapperI.insert("job_hist_cmpl", queryMap);
            log.info(
                    "{}.{}.{}.{} ((END))"
                    , queryMap.getString("job_group", "")
                    , queryMap.getString("job_name", "")
                    , queryMap.getString("schd_name", "")
                    , queryMap.getString("schd_code", "")
                    );
        }
        
    }
    
}
