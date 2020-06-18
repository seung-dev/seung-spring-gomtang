package seung.spring.gomtang.job.fin.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.quartz.JobExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.SConvert;
import seung.java.kimchi.http.SHttp;
import seung.java.kimchi.http.SHttpRequest;
import seung.java.kimchi.http.SHttpResponse;
import seung.java.kimchi.http.SRequestMethod;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.spring.boot.conf.SProperties;
import seung.spring.boot.conf.datasource.SMapperI;
import seung.spring.boot.conf.web.util.SResponse;

@Slf4j
@Service("sKiwoomS")
public class SKiwoomSI {

    @Resource(name = "sProperties")
    private SProperties sProperties;
    
    @Resource(name = "sMapperI")
    private SMapperI sMapperI;
    
    @SuppressWarnings("unchecked")
    public String kw10000(
            String jobGroup
            , String jobName
            ) {
        
        String errorCode = "E999";
        
        SLinkedHashMap jobHistMap = new SLinkedHashMap()
                .add("job_group", jobGroup)
                .add("job_name", jobName)
                .add("schd_set", jobName)
                .add("schd_code", "kw10000")
                .add("error_code", errorCode)
                .add("message", "")
                ;
        
        SLinkedHashMap queryMap = null;
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
            
            SLinkedHashMap data = new SLinkedHashMap();
            data.put("market_type", "3");
            SHttpRequest sHttpRequest = SHttpRequest
                    .builder()
                    .url(sProperties.getJob().getProperty("seung.job.kiwoom.kw10000.url", ""))
                    .requestMethod(SRequestMethod.POST)
                    .readTimeout(1000 * 10)
                    .useBody(true)
                    .body(data.toJsonString())
                    .build()
                    ;
            
            sHttpRequest.addHeader("Content-Type", "application/json");
            sHttpRequest.addHeader(sProperties.getJob().getProperty("seung.job.kiwoom.api.key.name", ""), sProperties.getJob().getProperty("seung.job.kiwoom.api.key.value", ""));
            
            SHttpResponse sHttpResponse = SHttp.request(sHttpRequest);
            
            if(HttpStatus.OK.value() != sHttpResponse.getResponseCode()) {
                throw new JobExecutionException(String.format(
                        "Failed to call kw10000. Response code is (%d)."
                        , sHttpResponse.getResponseCode()
                        ));
            }
            
            SLinkedHashMap kiwoomKw10000 = new SLinkedHashMap(new String(sHttpResponse.getResponseBody(), sHttpResponse.getResponseCharset("UTF-8")));
            List<SLinkedHashMap> comm = kiwoomKw10000.getListSLinkedHashMap("comm");
            if(comm.isEmpty()) {
                throw new JobExecutionException(String.format(
                        "Failed to call kw10000. comm is empty."
                        ));
            }
            if(!SResponse.SUCCESS.equals(comm.get(0).getString("error_code", ""))) {
                throw new JobExecutionException(String.format(
                        "Failed to call kw10000. Error code is (%s)."
                        , comm.get(0).getString("error_code", "")
                        ));
            }
            
            List<SLinkedHashMap> etf = kiwoomKw10000.getSLinkedHashMap("result").getListSLinkedHashMap("etf");
            
            int loopTry = 0;
            int kw10000_IR = 0;
            int kw10000_UR = 0;
            int kw10000_DO_NOTHING = 0;
            int kw10000_IGNORE = 0;
            SLinkedHashMap kw10000_SR = null;
            for(SLinkedHashMap item : etf) {
                if(loopTry++ % 100 == 0) {
                    log.info(
                            "{}.{}.{}.try {}"
                            , jobHistMap.getString("schd_set", "")
                            , jobHistMap.getString("schd_code", "")
                            , jobHistMap.getString("schd_no", "")
                            , loopTry
                            );
                }
                queryMap = new SLinkedHashMap();
                queryMap.put("item_code", item.getString("item_code", ""));
                queryMap.put("item_name", item.getString("item_name", ""));
                queryMap.put("etf_cnst", item.getString("cnst", ""));
                queryMap.put("etf_stts", item.getString("stts", ""));
                queryMap.put("hash", SConvert.digestToHex("MD5", queryMap.toJsonString()));
                kw10000_SR = sMapperI.selectOne("kw10000_SR", queryMap);
                if(kw10000_SR == null) {
                    kw10000_IR += sMapperI.insert("kw10000_IR", queryMap);
                } else if(queryMap.getString("hash", "").equals(kw10000_SR.getString("hash", "1"))) {
                    kw10000_DO_NOTHING++;
                } else if(!queryMap.getString("hash", "").equals(kw10000_SR.getString("hash", "1"))) {
                    kw10000_UR += sMapperI.insert("kw10000_UR", queryMap);
                } else {
                    kw10000_IGNORE++;
                }
            }
            
            jobHistMap.put(
                    "message"
                    , String.format(
                            "kw10000_IR=%d, kw10000_UR=%d, kw10000_DO_NOTHING=%d, kw10000_IGNORE=%d"
                            , kw10000_IR
                            , kw10000_UR
                            , kw10000_DO_NOTHING
                            , kw10000_IGNORE
                            )
                    );
            errorCode = "0000";
            
        } catch (Exception e) {
            log.error("Failed to call kw10000.", e);
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
    
    @SuppressWarnings("unchecked")
    public String tr10001(
            String jobGroup
            , String jobName
            ) {
        
        String errorCode = "E999";
        
        SLinkedHashMap jobHistMap = new SLinkedHashMap()
                .add("job_group", jobGroup)
                .add("job_name", jobName)
                .add("schd_set", jobName)
                .add("schd_code", "tr10001")
                .add("error_code", errorCode)
                .add("message", "")
                ;
        SLinkedHashMap queryMap = null;
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
            SLinkedHashMap data = null;
            SHttpRequest sHttpRequest = null;
            SHttpResponse sHttpResponse = null;
            SLinkedHashMap kiwoomKr10001 = null;
            List<SLinkedHashMap> tr10001Comm = null;
            List<SLinkedHashMap> tr10001Data = null;
            String itemName = "";
            String itemCode = "";
            SLinkedHashMap tr10001 = null;
            int tr10001_IR = 0;
            int tr10001_UR = 0;
            int tr10001_DO_NOTHING = 0;
            int tr10001_IGNORE = 0;
            SLinkedHashMap tr10001_SR = null;
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
                
                data = new SLinkedHashMap();
                data.put("item_code", kw10000_SR.getString("item_code"));
                sHttpRequest = SHttpRequest
                        .builder()
                        .url(sProperties.getJob().getProperty("seung.job.kiwoom.tr10001.url", ""))
                        .requestMethod(SRequestMethod.POST)
                        .readTimeout(1000 * 10)
                        .useBody(true)
                        .body(data.toJsonString())
                        .build()
                        ;
                sHttpRequest.addHeader("Content-Type", "application/json");
                sHttpRequest.addHeader(sProperties.getJob().getProperty("seung.job.kiwoom.api.key.name", ""), sProperties.getJob().getProperty("seung.job.kiwoom.api.key.value", ""));
                
                sHttpResponse = SHttp.request(sHttpRequest);
                
                if(HttpStatus.OK.value() != sHttpResponse.getResponseCode()) {
                    throw new JobExecutionException(String.format(
                            "Failed to call %s. Response code is (%d)."
                            , jobHistMap.getString("schd_code", "")
                            , sHttpResponse.getResponseCode()
                            ));
                }
                
                kiwoomKr10001 = new SLinkedHashMap(new String(sHttpResponse.getResponseBody(), sHttpResponse.getResponseCharset("UTF-8")));
                
                tr10001Comm = kiwoomKr10001.getListSLinkedHashMap("comm");
                if(tr10001Comm.isEmpty()) {
                    throw new JobExecutionException(String.format(
                            "Failed to call %s. comm is empty."
                            , jobHistMap.getString("schd_code", "")
                            ));
                }
                if(!SResponse.SUCCESS.equals(tr10001Comm.get(0).getString("error_code", ""))) {
                    throw new JobExecutionException(String.format(
                            "Failed to call tr10001. Error code is (%s)."
                            , tr10001Comm.get(0).getString("error_code", "")
                            ));
                }
                
                itemName = "";
                itemCode = "";
                tr10001Data = kiwoomKr10001.getListSLinkedHashMap("data");
                if(tr10001Data.isEmpty()) {
                    throw new JobExecutionException(String.format(
                            "Failed to call %s. data is empty."
                            , jobHistMap.getString("schd_code", "")
                            ));
                }
                itemName = tr10001Data.get(0).getString("item_name", "");
                itemCode = tr10001Data.get(0).getString("item_code", "");
                
                tr10001 = kiwoomKr10001.getSLinkedHashMap("result").getListSLinkedHashMap("tr10001").get(0);
                
                queryMap = new SLinkedHashMap();
                queryMap.put("item_code", itemCode);
                queryMap.put("item_name", itemName);
                queryMap.put("etf_fm", tr10001.getString("fm", ""));
                queryMap.put("etf_fv", tr10001.getDouble("fv"));
                queryMap.put("etf_equity", tr10001.getDouble("equity"));
                queryMap.put("etf_is", tr10001.getBigInteger("is"));
                queryMap.put("etf_cr", tr10001.getDouble("cr"));
                queryMap.put("etf_yh", tr10001.getDouble("yh"));
                queryMap.put("etf_yl", tr10001.getDouble("yl"));
                queryMap.put("etf_mc", tr10001.getDouble("mc"));
                queryMap.put("etf_mcr", tr10001.getString("mcr", ""));
                queryMap.put("etf_for", tr10001.getDouble("for"));
                queryMap.put("etf_sp", tr10001.getDouble("sp"));
                queryMap.put("etf_250h", tr10001.getDouble("250h"));
                queryMap.put("etf_250l", tr10001.getDouble("250l"));
                queryMap.put("etf_op", tr10001.getDouble("op"));
                queryMap.put("etf_hp", tr10001.getDouble("hp"));
                queryMap.put("etf_lp", tr10001.getDouble("lp"));
                queryMap.put("etf_hhp", tr10001.getDouble("hhp"));
                queryMap.put("etf_llp", tr10001.getDouble("llp"));
                queryMap.put("etf_bp", tr10001.getDouble("bp"));
                queryMap.put("etf_ep", tr10001.getDouble("ep"));
                queryMap.put("etf_eq", tr10001.getDouble("eq"));
                queryMap.put("etf_d250h", tr10001.getString("d250h", ""));
                queryMap.put("etf_vs250h", tr10001.getDouble("vs250h"));
                queryMap.put("etf_d250l", tr10001.getString("d250l", ""));
                queryMap.put("etf_vs250l", tr10001.getDouble("vs250l"));
                queryMap.put("etf_pp", tr10001.getDouble("pp"));
                queryMap.put("etf_pinc", tr10001.getDouble("pinc"));
                queryMap.put("etf_pcp", tr10001.getDouble("pcp"));
                queryMap.put("etf_vol", tr10001.getBigInteger("vol"));
                queryMap.put("etf_pcv", tr10001.getDouble("pcv"));
                queryMap.put("etf_fvu", tr10001.getString("fvu", ""));
                queryMap.put("etf_os", tr10001.getString("os", ""));
                queryMap.put("etf_osr", tr10001.getString("osr", ""));
                queryMap.put("hash", SConvert.digestToHex("MD5", queryMap.toJsonString()));
                tr10001_SR = sMapperI.selectOne("tr10001_SR", queryMap);
                if(tr10001_SR == null) {
                    tr10001_IR += sMapperI.insert("tr10001_IR", queryMap);
                } else if(queryMap.getString("hash", "").equals(tr10001_SR.getString("hash", "1"))) {
                    tr10001_DO_NOTHING++;
                } else if(!queryMap.getString("hash", "").equals(tr10001_SR.getString("hash", "1"))) {
                    tr10001_UR += sMapperI.insert("tr10001_UR", queryMap);
                } else {
                    tr10001_IGNORE++;
                }
                
                Thread.sleep(500);
                
            }
            
            jobHistMap.put(
                    "message"
                    , String.format(
                            "tr10001_IR=%d, tr10001_UR=%d, tr10001_DO_NOTHING=%d, tr10001_IGNORE=%d"
                            , tr10001_IR
                            , tr10001_UR
                            , tr10001_DO_NOTHING
                            , tr10001_IGNORE
                            )
                    );
            errorCode = "0000";
            
        } catch (Exception e) {
            log.error("Failed to call tr10001. queryMap={}", queryMap.toJsonString(true), e);
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
    
    @SuppressWarnings("unchecked")
    public String tr40005(
            String jobGroup
            , String jobName
            ) {
        
        String errorCode = "E999";
        
        SLinkedHashMap jobHistMap = new SLinkedHashMap()
                .add("job_group", jobGroup)
                .add("job_name", jobName)
                .add("schd_set", jobName)
                .add("schd_code", "tr40005")
                .add("error_code", errorCode)
                .add("message", "")
                ;
        
        SLinkedHashMap queryMap = null;
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
            SLinkedHashMap data = null;
            SHttpRequest sHttpRequest = null;
            SHttpResponse sHttpResponse = null;
            SLinkedHashMap kiwoomKr40005 = null;
            List<SLinkedHashMap> tr40005Comm = null;
            List<SLinkedHashMap> tr40005 = null;
            int tr40005_IR = 0;
            int tr40005_UR = 0;
            int tr40005_DO_NOTHING = 0;
            int tr40005_IGNORE = 0;
            SLinkedHashMap tr40005_SR = null;
            int prev_IR = 0;
            int post_IR = 0;
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
                
                data = new SLinkedHashMap();
                data.put("item_code", kw10000_SR.getString("item_code"));
                data.put("repeat_30", "1");
                sHttpRequest = SHttpRequest
                        .builder()
                        .url(sProperties.getJob().getProperty("seung.job.kiwoom.tr40005.url", ""))
                        .requestMethod(SRequestMethod.POST)
                        .readTimeout(1000 * 10)
                        .useBody(true)
                        .body(data.toJsonString())
                        .build()
                        ;
                sHttpRequest.addHeader("Content-Type", "application/json");
                sHttpRequest.addHeader(sProperties.getJob().getProperty("seung.job.kiwoom.api.key.name", ""), sProperties.getJob().getProperty("seung.job.kiwoom.api.key.value", ""));
                
                sHttpResponse = SHttp.request(sHttpRequest);
                
                if(HttpStatus.OK.value() != sHttpResponse.getResponseCode()) {
                    throw new JobExecutionException(String.format(
                            "Failed to call %s. Response code is (%d)."
                            , jobHistMap.getString("schd_code", "")
                            , sHttpResponse.getResponseCode()
                            ));
                }
                
                kiwoomKr40005 = new SLinkedHashMap(new String(sHttpResponse.getResponseBody(), sHttpResponse.getResponseCharset("UTF-8")));
                
                tr40005Comm = kiwoomKr40005.getListSLinkedHashMap("comm");
                if(tr40005Comm.isEmpty()) {
                    throw new JobExecutionException(String.format(
                            "Failed to call %s. comm is empty."
                            , jobHistMap.getString("schd_code", "")
                            ));
                }
                if(!SResponse.SUCCESS.equals(tr40005Comm.get(0).getString("error_code", ""))) {
                    throw new JobExecutionException(String.format(
                            "Failed to call kr40005. Error code is (%s)."
                            , tr40005Comm.get(0).getString("error_code", "")
                            ));
                }
                
                tr40005 = kiwoomKr40005.getSLinkedHashMap("result").getListSLinkedHashMap("tr40005");
                if(tr40005.isEmpty()) {
                    throw new JobExecutionException(String.format(
                            "Failed to call %s. tr40005 is empty."
                            , jobHistMap.getString("schd_code", "")
                            ));
                }
                
                for(SLinkedHashMap item : tr40005) {
                    
                    queryMap = new SLinkedHashMap();
                    queryMap.put("trdd", item.getString("date"));
                    queryMap.put("item_code", kw10000_SR.getString("item_code"));
                    queryMap.put("etf_cp", item.getDouble("cp"));
                    queryMap.put("etf_inc", item.getDouble("inc"));
                    queryMap.put("etf_pcp", item.getDouble("pcp"));
                    queryMap.put("etf_vol", item.getBigInteger("vol"));
                    queryMap.put("etf_nav", item.getDouble("nav"));
                    queryMap.put("etf_volaccu", item.getBigInteger("volaccu"));
                    queryMap.put("etf_indexd", item.getDouble("indexd"));
                    queryMap.put("etf_etfd", item.getDouble("etfd"));
                    queryMap.put("etf_ter", item.getDouble("ter"));
                    queryMap.put("etf_ti", item.getDouble("ti"));
                    queryMap.put("etf_tiinc", item.getDouble("tiinc"));
                    queryMap.put("hash", SConvert.digestToHex("MD5", queryMap.toJsonString()));
                    tr40005_SR = sMapperI.selectOne("tr40005_SR", queryMap);
                    
                    if(tr40005_SR == null) {
                        tr40005_IR += sMapperI.insert("tr40005_IR", queryMap);
                    } else if(queryMap.getString("hash", "").equals(tr40005_SR.getString("hash", "1"))) {
                        tr40005_DO_NOTHING++;
                        break;
                    } else if(!queryMap.getString("hash", "").equals(tr40005_SR.getString("hash", "1"))) {
                        tr40005_UR += sMapperI.insert("tr40005_UR", queryMap);
                        break;
                    } else {
                        tr40005_IGNORE++;
                        break;
                    }
                    
                }// end of tr40005
                
                Thread.sleep(500);
                
            }// end of kw10000_SL
            
            SLinkedHashMap trddNo = sMapperI.selectOne("trdd_no");
            prev_IR = trddNo.getInt("prev");
            post_IR = trddNo.getInt("post");
            
            jobHistMap.put(
                    "message"
                    , String.format(
                            "tr40005_IR=%d, tr40005_UR=%d, tr40005_DO_NOTHING=%d, tr40005_IGNORE=%d, prev_IR=%d, post_IR=%d"
                            , tr40005_IR
                            , tr40005_UR
                            , tr40005_DO_NOTHING
                            , tr40005_IGNORE
                            , prev_IR
                            , post_IR
                            )
                    );
            errorCode = "0000";
            
        } catch (Exception e) {
            log.error("Failed to call tr40005. queryMap={}", queryMap.toJsonString(true), e);
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
