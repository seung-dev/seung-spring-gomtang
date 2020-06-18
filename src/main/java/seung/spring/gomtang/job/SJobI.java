package seung.spring.gomtang.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    @Resource(name = "sApplicationData")
    private SLinkedHashMap sApplicationData;
    
    @Resource(name = "sMapperI")
    private SMapperI sMapperI;
    
    @Resource(name = "sQuartzH")
    private SQuartzH sQuartzH;
    
    public void initJob() {
        
        log.info("run");
        
        List<SLinkedHashMap> cronJobs = new ArrayList<>();
        cronJobs.add(
                new SLinkedHashMap()
                .add("group", "fin")
                .add("name", "etfA")
                .add("dscr", "ETF DATA 수집 - KIWOOM")
                .add("clss", "seung.spring.gomtang.job.fin.SJobEtfA")
                .add("dateFrom", "")
                .add("dateTo", "")
                .add("cronExpr", "0 10 16,18 ? * MON-FRI *")
                );
        cronJobs.add(
                new SLinkedHashMap()
                .add("group", "fin")
                .add("name", "etfB")
                .add("dscr", "ETF DATA 수집 - NAVER, DART")
                .add("clss", "seung.spring.gomtang.job.fin.SJobEtfB")
                .add("dateFrom", "")
                .add("dateTo", "")
                .add("cronExpr", "0 0 4 ? * MON-FRI *")
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
    
    @SuppressWarnings("unchecked")
    public void apiSchema() {
        
        log.info("run");
        
        SLinkedHashMap schema = new SLinkedHashMap();
        SLinkedHashMap api = null;
        for(SLinkedHashMap api_schema_SR : sMapperI.selectList("api_schema_SL")) {
            api = new SLinkedHashMap();
            api.put("path", api_schema_SR.getString("api_path"));
            api.put("description", api_schema_SR.getString("api_dscr"));
            try {
                if(api_schema_SR.getString("api_data", "").trim().startsWith("{")) {
                    api.put("data", new ObjectMapper().readValue(api_schema_SR.getString("api_data"), Map.class));
                } else if(api_schema_SR.getString("api_data", "").trim().startsWith("[")) {
                    api.put("data", new ObjectMapper().readValue(api_schema_SR.getString("api_data"), List.class));
                }
                if(api_schema_SR.getString("api_rslt", "").trim().startsWith("{")) {
                    api.put("result", new ObjectMapper().readValue(api_schema_SR.getString("api_rslt"), Map.class));
                } else if(api_schema_SR.getString("api_rslt", "").trim().startsWith("[")) {
                    api.put("result", new ObjectMapper().readValue(api_schema_SR.getString("api_rslt"), List.class));
                }
            } catch (JsonProcessingException e) {
                log.error("Failed to read api schema.", e);
            }
            schema.put(String.format("%s.%s", api_schema_SR.getString("api_set"), api_schema_SR.getString("api_code")), api);
        }
        
        sApplicationData.put("schema", schema);
    }
    
}
