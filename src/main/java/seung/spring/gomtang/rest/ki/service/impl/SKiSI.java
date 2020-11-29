package seung.spring.gomtang.rest.ki.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.spring.boot.conf.datasource.SMapperI;
import seung.spring.boot.conf.excel.SExcelVO;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.gomtang.rest.ki.service.SKiS;
import seung.spring.gomtang.rest.ki.util.Ki0201;

@Service(value = "sKiS")
@Slf4j
public class SKiSI implements SKiS {

	@Resource(name = "sMapperI")
	private SMapperI sMapperI;
	
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
