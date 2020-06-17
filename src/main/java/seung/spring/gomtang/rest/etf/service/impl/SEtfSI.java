package seung.spring.gomtang.rest.etf.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.SDate;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.spring.boot.conf.datasource.SMapperI;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;
import seung.spring.gomtang.rest.etf.service.SEtfS;

@Slf4j
@Service(value = "sEtfS")
public class SEtfSI implements SEtfS {

    @Resource(name = "sApplicationData")
    private SLinkedHashMap sApplicationData;
    
    @Resource(name = "sMapperI")
    private SMapperI sMapperI;
    
    @Override
    public SResponse etf0101(SRequest sRequest) {
        
        log.debug("run");
        
        String requestCode = sRequest.getData().getString("request_code", "");
        
        SResponse sResponse = SResponse.builder()
                .request_code(requestCode)
                .data(sRequest.getData())
                .build()
                ;
        
        List<SLinkedHashMap> etf0101 = null;
        try {
            
            log.debug("{}.sRequest: {}", requestCode, sRequest.toJsonString(true));
            
            log.info(new SLinkedHashMap()
                        .add("mmnt_date", sRequest.getData().getString("mmnt_date"))
                        .add("mmnt_unit", sRequest.getData().getString("mmnt_unit"))
                        .add("mmnt_scope", sRequest.getData().getInt("mmnt_scope"))
                        .add("mmnt_min", sRequest.getData().getDouble("mmnt_min"))
                        .toJsonString());
            etf0101 = sMapperI.selectList(
                    "etf0101"
                    , new SLinkedHashMap()
                        .add("mmnt_date", sRequest.getData().getString("mmnt_date"))
                        .add("mmnt_unit", sRequest.getData().getString("mmnt_unit"))
                        .add("mmnt_scope", sRequest.getData().getInt("mmnt_scope"))
                        .add("mmnt_min", sRequest.getData().getDouble("mmnt_min"))
                        .toJsonString()
                    );
            
            sResponse.putResult("schema", sApplicationData.getSLinkedHashMap("schema").get("etf.etf0101"));
            
            sResponse.success();
            
        } catch (Exception e) {
            log.error("(({})) Failed to get etf0101.", requestCode, e);
            sResponse.setError_message(ExceptionUtils.getStackTrace(e));
        } finally {
            if(etf0101 == null) {
                etf0101 = new ArrayList<>();
            }
            sResponse.putResult("total_count", etf0101.size());
            sResponse.putResult("etf0101", etf0101);
        }
        
        sResponse.setResponse_time(SDate.getDateString());
        return sResponse;
    }
    
    @Override
    public SResponse etf0111(SRequest sRequest) {
        log.debug("run");
        
        String requestCode = sRequest.getData().getString("request_code", "");
        
        SResponse sResponse = SResponse.builder()
                .request_code(requestCode)
                .data(sRequest.getData())
                .build()
                ;
        
        List<SLinkedHashMap> etf0111 = null;
        try {
            
            log.debug("{}.sRequest: {}", requestCode, sRequest.toJsonString(true));
            
            etf0111 = sMapperI.selectList(
                    "etf0111"
                    , new SLinkedHashMap()
                        .add("item_code", sRequest.getData().getString("item_code"))
                        .add("trdd_from", sRequest.getData().getString("trdd_from"))
                        .add("trdd_to", sRequest.getData().getString("trdd_to"))
                        .add("page_index", sRequest.getData().getInt("page_index"))
                        .add("page_size", sRequest.getData().getInt("page_size"))
                    );
            
            sResponse.putResult("schema", sApplicationData.getSLinkedHashMap("schema").get("etf.etf0111"));
            
            sResponse.success();
            
        } catch (Exception e) {
            log.error("(({})) Failed to get etf0101.", requestCode, e);
            sResponse.setError_message(ExceptionUtils.getStackTrace(e));
        } finally {
            if(etf0111 == null) {
                etf0111 = new ArrayList<>();
            }
            sResponse.putResult("total_count", etf0111.size());
            sResponse.putResult("etf0111", etf0111);
        }
        
        sResponse.setResponse_time(SDate.getDateString());
        return sResponse;
    }
    
}
