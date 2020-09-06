package seung.spring.gomtang.rest.ki.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.spring.boot.conf.datasource.SMapperI;
import seung.spring.boot.conf.excel.SExcelVO;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;
import seung.spring.gomtang.rest.ki.service.SKiS;
import seung.spring.gomtang.rest.ki.util.Ki0201;

@Service(value = "sKiS")
@Slf4j
public class SKiSI implements SKiS {

	@Resource(name = "sMapperI")
	private SMapperI sMapperI;
	
	@SuppressWarnings("unchecked")
	@Override
	public SResponse ki0201(SRequest sRequest, Ki0201 ki0201) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(ki0201.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(ki0201)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		SLinkedHashMap query = null;
		List<SLinkedHashMap> ki0201_SL = null;
		String error_message = "";
		try {
			
			log.info("({}) request={}", sResponse.getRequest_code(), ki0201.toJsonString());
			
			query = new SLinkedHashMap()
					.add("req_json", ki0201.toJsonString())
					;
			
			ki0201_SL = sMapperI.selectList(
					"ki0201_SL"
					, query
					);
			if(!ki0201_SL.isEmpty()) {
				for(SLinkedHashMap ki0201_SR : ki0201_SL) {
					ki0201_SR.put("item_data", new ObjectMapper().readValue(ki0201_SR.getString("item_data", "[]"), List.class));
				}
			}
			
			sResponse.success();
			
		} catch (Exception e) {
			log.info("({}) query={}", sResponse.getRequest_code(), query.toJsonString(), e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
			if(ki0201_SL.isEmpty()) {
				sResponse.putResponse("ki0201_S", 0);
				sResponse.putResponse("ki0201_SL", new ArrayList<>());
			} else {
				sResponse.putResponse("ki0201_S", ki0201_SL.size());
				sResponse.putResponse("ki0201_SL", ki0201_SL);
			}
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, ?> ki0190(SRequest sRequest, Ki0201 ki0201) {
		
		Map<String, Object> response = new HashMap<>();
		
		SLinkedHashMap data = new SLinkedHashMap();
		
		List<String> itemCodes = new ArrayList<>();
		itemCodes.add("종목코드");
		List<String> itemNames = new ArrayList<>();
		itemNames.add("종목명");
		for(SLinkedHashMap etf9002_A : sMapperI.selectList("etf9002_A")) {
			itemCodes.add(etf9002_A.getString("item_code"));
			itemNames.add(etf9002_A.getString("item_name"));
		}
		String[] header = itemCodes.toArray(new String[itemCodes.size()]);
		
		int times = 0;
		String trdd = "";
		SLinkedHashMap record = null;
		List<String> records = null;
		for(SLinkedHashMap etf9002_B : sMapperI.selectList("etf9002_B")) {
			
			if(++times % 100 == 1) {
				log.info("times={}", times);
			}
			
			trdd = etf9002_B.getString("trdd");
			
			record = new SLinkedHashMap();
			records = new ArrayList<>();
			for(SLinkedHashMap etf9002_C : sMapperI.selectList("etf9002_C", etf9002_B)) {
				record.put(etf9002_C.getString("item_code"), etf9002_C.getString("etf_cp", ""));
			}
			
			for(String itemCode : header) {
				if(records.isEmpty()) {
					records.add(trdd);
				} else {
					records.add(record.getString(itemCode, ""));
				}
			}
			
		}
		
		
		SExcelVO sExcelVO = new SExcelVO();
		sExcelVO.setDownloadFileName("");
		sExcelVO.setTemplateFilePath("etf.xlsx");
		sExcelVO.setData(data);
		
		response.put("sExcelVO", sExcelVO);
		
		return response;
	}
	
}
