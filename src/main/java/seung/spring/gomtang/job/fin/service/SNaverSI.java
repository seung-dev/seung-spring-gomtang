package seung.spring.gomtang.job.fin.service;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.SConvert;
import seung.java.kimchi.SString;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.spring.boot.conf.SProperties;
import seung.spring.boot.conf.datasource.SMapperI;
import seung.spring.boot.conf.web.util.SResponse;
import seung.spring.gomtang.util.SGomtangException;

@Slf4j
@Service("sNaverS")
public class SNaverSI {

    @Resource(name = "sProperties")
    private SProperties sProperties;
    
    @Resource(name = "sMapperI")
    private SMapperI sMapperI;
    
    @SuppressWarnings("unchecked")
    public synchronized String n0102(
            String jobGroup
            , String jobName
            ) {
        
        String errorCode = "E999";
        
        SLinkedHashMap jobHistMap = new SLinkedHashMap()
                .add("job_group", jobGroup)
                .add("job_name", jobName)
                .add("schd_set", jobName)
                .add("schd_code", "n0102")
                .add("error_code", errorCode)
                .add("message", "")
                ;
        
        SLinkedHashMap n0102 = null;
        try {
            
            jobHistMap.put("schd_no", sMapperI.selectOne("schd_no").getString("schd_no", ""));
            jobHistMap.put("job_data", jobHistMap.toJsonString());
            
            log.info(
                    "{}.{}.{} ((START))"
                    , jobHistMap.getString("schd_set", "")
                    , jobHistMap.getString("schd_code", "")
                    , jobHistMap.getString("schd_no", "")
                    );
            
            sMapperI.insert("schd_prev", jobHistMap);
            
            int loopTry = 0;
            HttpResponse<byte[]> httpResponse = null;
            SLinkedHashMap response = null;
            SLinkedHashMap n0102_SR = null;
            int n0102_IR = 0;
            int n0102_UR = 0;
            int n0102_DO_NOTHING = 0;
            int n0102_IGNORE = 0;
            for(SLinkedHashMap kw10000_SR : sMapperI.selectList("kw10000_SL")) {
                
                if(loopTry++ % 100 == 0) {
                    log.info(
                            "{}.{}.{}.try {}"
                            , jobHistMap.getString("schd_set", "")
                            , jobHistMap.getString("schd_code", "")
                            , jobHistMap.getString("schd_no", "")
                            , loopTry
                            );
                }
                
                httpResponse = Unirest
                        .post("http://127.0.0.1:11131/rest/fin/n0102")
                        .connectTimeout(1000 * 3)
                        .socketTimeout(1000 * 10)
                        .field("request_code", jobHistMap.getString("schd_no", ""))
                        .field("item_code", kw10000_SR.getString("item_code"))
                        .asBytes()
                        ;
                
                if(HttpStatus.OK.value() != httpResponse.getStatus()) {
                    throw new SGomtangException(String.format(
                            "Failed to call %s. Response code is (%d)."
                            , jobHistMap.getString("schd_code", "")
                            , httpResponse.getStatus()
                            ));
                }
                
                response = new SLinkedHashMap(new String(httpResponse.getBody(), "UTF-8"));
                
                if(!SResponse.SUCCESS.equals(response.getString("error_code", ""))) {
                    throw new SGomtangException(String.format(
                            "%s: %s"
                            , response.getString("message", "")
                            , response.getString("error_code", "")
                            ));
                }
                
                n0102 = response.getSLinkedHashMap("result").getSLinkedHashMap("n0102");
                n0102.put("item_code", kw10000_SR.getString("item_code"));
                n0102.put("cu", SString.toJson(n0102.getListSLinkedHashMap("cu")));
                n0102.put("hash", SConvert.digestToHex("MD5", n0102.toJsonString()));
                
                n0102_SR = sMapperI.selectOne("n0102_SR", n0102);
                if(n0102_SR == null) {
                    n0102_IR += sMapperI.insert("n0102_IR", n0102);
                } else if(n0102.getString("hash", "").equals(n0102_SR.getString("hash", "1"))) {
                    n0102_DO_NOTHING++;
                } else if(!n0102.getString("hash", "").equals(n0102_SR.getString("hash", "1"))) {
                    n0102_UR += sMapperI.insert("n0102_UR", n0102);
                } else {
                    n0102_IGNORE++;
                }
                
                Thread.sleep(200);
                
            }
            
            jobHistMap.put(
                    "message"
                    , String.format(
                            "n0102_IR=%d, n0102_UR=%d, n0102_DO_NOTHING=%d, n0102_IGNORE=%d"
                            , n0102_IR
                            , n0102_UR
                            , n0102_DO_NOTHING
                            , n0102_IGNORE
                            )
                    );
            errorCode = "0000";
            
        } catch (Exception e) {
            log.error("Failed to call n0102. queryMap={}", n0102.toJsonString(true), e);
            jobHistMap.put("message", ExceptionUtils.getStackTrace(e));
        } finally {
            jobHistMap.put("error_code", errorCode);
            log.info(
                    "{}.{}.{}.error_code {}"
                    , jobHistMap.getString("schd_set", "")
                    , jobHistMap.getString("schd_code", "")
                    , jobHistMap.getString("schd_no", "")
                    , jobHistMap.getString("error_code", "")
                    );
            sMapperI.insert("schd_post", jobHistMap);
            log.info(
                    "{}.{}.{} ((END))"
                    , jobHistMap.getString("schd_set", "")
                    , jobHistMap.getString("schd_code", "")
                    , jobHistMap.getString("schd_no", "")
                    );
        }
        
        
        return errorCode;
    }
    
}
