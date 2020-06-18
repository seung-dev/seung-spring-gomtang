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
    
    @SuppressWarnings("unchecked")
    @Override
    public SResponse etf0101(SRequest sRequest) {
        
        log.debug("run");
        
        String requestCode = sRequest.getData().getString("request_code", "");
        
        SResponse sResponse = SResponse.builder()
                .request_code(requestCode)
                .data(sRequest.getData())
                .build()
                ;
        
        SLinkedHashMap etf0101_SR = null;
        List<SLinkedHashMap> etf0101_SL = null;
        try {
            
            log.info("{}.sRequest: {}", requestCode, sRequest.toJsonString(true));
            
            etf0101_SR = sMapperI.selectOne("etf0101_SR");
            
            SLinkedHashMap queryMap = new SLinkedHashMap()
                    .add("trdd", etf0101_SR.getString("trdd"))
                    .add("mmnt_date", sRequest.getData().getString("mmnt_date"))
                    .add("mmnt_unit", sRequest.getData().getString("mmnt_unit", "D"))
                    .add("mmnt_scope", sRequest.getData().getInt("mmnt_scope", 3))
                    .add("mmnt_min", sRequest.getData().getDouble("mmnt_min", 1.0))
                    ;
            queryMap.put("req_json", queryMap.toJsonString());
            etf0101_SL = sMapperI.selectList(
                    "etf0101_SL"
                    , queryMap
                    );
            
            sResponse.putResult("schema", sApplicationData.getSLinkedHashMap("schema").get("etf.etf0101"));
            
            sResponse.success();
            
        } catch (Exception e) {
            log.error("(({})) Failed to get etf0101.", requestCode, e);
            sResponse.setError_message(ExceptionUtils.getStackTrace(e));
        } finally {
            if(etf0101_SR == null) {
                sResponse.putResult("trdd", "");
                sResponse.putResult("trdd_no", "");
            } else {
                sResponse.putResult("trdd", etf0101_SR.getString("trdd", ""));
                sResponse.putResult("trdd_no", etf0101_SR.getString("trdd_no", ""));
            }
            if(etf0101_SL == null) {
                sResponse.putResult("etf0101", new ArrayList<>());
                sResponse.putResult("total_count", "-1");
            } else {
                sResponse.putResult("etf0101", etf0101_SL == null ? new ArrayList<>() : etf0101_SL);
                sResponse.putResult("total_count", etf0101_SL.size());
            }
        }
        
        sResponse.setResponse_time(SDate.getDateString());
        return sResponse;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public SResponse etf0111(SRequest sRequest) {
        
        log.debug("run");
        
        String requestCode = sRequest.getData().getString("request_code", "");
        
        SResponse sResponse = SResponse.builder()
                .request_code(requestCode)
                .data(sRequest.getData())
                .build()
                ;
        
        SLinkedHashMap etf0111_SR = null;
        List<SLinkedHashMap> etf0111_SL = null;
        try {
            
            log.info("{}.sRequest: {}", requestCode, sRequest.toJsonString(true));
            
            SLinkedHashMap queryMap = new SLinkedHashMap()
                    .add("item_code", sRequest.getData().getString("item_code"))
                    .add("trdd_from", sRequest.getData().getString("trdd_from"))
                    .add("trdd_to", sRequest.getData().getString("trdd_to"))
                    .add("page_index", sRequest.getData().getInt("page_index"))
                    .add("page_size", sRequest.getData().getInt("page_size"))
                    ;
            queryMap.put("req_json", queryMap.toJsonString());
            
            etf0111_SR = sMapperI.selectOne("etf0111_SR", queryMap);
            etf0111_SL = sMapperI.selectList("etf0111_SL", queryMap);
            
            sResponse.putResult("schema", sApplicationData.getSLinkedHashMap("schema").get("etf.etf0111"));
            
            sResponse.success();
            
        } catch (Exception e) {
            log.error("(({})) Failed to get etf0111.", requestCode, e);
            sResponse.setError_message(ExceptionUtils.getStackTrace(e));
        } finally {
            if(etf0111_SR == null) {
                sResponse.putResult("total_count", "");
            } else {
                sResponse.putResult("total_count", etf0111_SR.getString("total_count", ""));
            }
            if(etf0111_SL == null) {
                sResponse.putResult("etf0111", new ArrayList<>());
            } else {
                sResponse.putResult("etf0111", etf0111_SL);
            }
        }
        
        sResponse.setResponse_time(SDate.getDateString());
        return sResponse;
    }
    
}
