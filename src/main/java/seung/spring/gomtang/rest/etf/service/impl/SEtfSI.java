package seung.spring.gomtang.rest.etf.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itfsw.query.builder.SqlQueryBuilderFactory;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.util.SCode;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.spring.boot.conf.datasource.SMapperI;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;
import seung.spring.gomtang.rest.etf.service.SEtfS;

@Slf4j
@Service(value = "sEtfS")
public class SEtfSI implements SEtfS {

	@Resource(name = "sMapperI")
	private SMapperI sMapperI;
	
	@SuppressWarnings("unchecked")
	@Override
	public SResponse etf0000(SRequest sRequest) {
		
		String apiCode = "etf0000";
		String error_message = "";
		String requestCode = sRequest.getData().getString("request_code", "");
		log.info("{}.{} ((START))", apiCode, requestCode);
		
		SResponse sResponse = SResponse.builder()
				.request_code(requestCode)
				.error_code(SCode.ERROR)
				.data(sRequest.getData())
				.build()
				;
		
		List<SLinkedHashMap> etf0000_SL = null;
		try {
			
			log.info("{}.{}.query: {}", apiCode, requestCode, sRequest.getData().toJsonString());
			
			etf0000_SL = sMapperI.selectList(
					"etf0000_SL"
					, new SLinkedHashMap()
						.add("api_set", sRequest.getData().getString("api_set", ""))
						.add("api_code", sRequest.getData().getString("api_code", ""))
					);
			for(SLinkedHashMap etf0000_SR : etf0000_SL) {
				if(etf0000_SR.getString("api_data", "").trim().startsWith("{")) {
					etf0000_SR.put("api_data", new ObjectMapper().readValue(etf0000_SR.getString("api_data"), Map.class));
				} else if(etf0000_SR.getString("api_data", "").trim().startsWith("[")) {
					etf0000_SR.put("api_data", new ObjectMapper().readValue(etf0000_SR.getString("api_data"), List.class));
				}
				if(etf0000_SR.getString("api_rslt", "").trim().startsWith("{")) {
					etf0000_SR.put("api_rslt", new ObjectMapper().readValue(etf0000_SR.getString("api_rslt"), Map.class));
				} else if(etf0000_SR.getString("api_rslt", "").trim().startsWith("[")) {
					etf0000_SR.put("api_rslt", new ObjectMapper().readValue(etf0000_SR.getString("api_rslt"), List.class));
				}
			}
			
			sResponse.success();
			
		} catch (Exception e) {
			log.error("{}.{}.exception", apiCode, requestCode, e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
			if(etf0000_SL == null) {
				sResponse.putResult("total_count", "0");
				sResponse.putResult("etf0000", new ArrayList<>());
			} else {
				sResponse.putResult("total_count", "" + etf0000_SL.size());
				sResponse.putResult("etf0000", etf0000_SL);
			}
		}
		
		log.info("{}.{} ((END))", apiCode, requestCode);
		sResponse.setResponse_time(String.valueOf(new Date().getTime()));
		return sResponse;
	}
	
	@Override
	public SResponse etf0001(SRequest sRequest) {
		
		String apiCode = "etf0001";
		String error_message = "";
		String requestCode = sRequest.getData().getString("request_code", "");
		log.info("{}.{} ((START))", apiCode, requestCode);
		
		SResponse sResponse = SResponse.builder()
				.request_code(requestCode)
				.error_code(SCode.ERROR)
				.data(sRequest.getData())
				.build()
				;
		
		List<SLinkedHashMap> etf0001_SL = null;
		try {
			
			log.info("{}.{}.query: {}", apiCode, requestCode, sRequest.getData().toJsonString());
			
			etf0001_SL = sMapperI.selectList(
					"etf0001_SL"
					, new SLinkedHashMap()
						.add("optn_set", sRequest.getData().getString("optn_set", ""))
					);
			
			sResponse.success();
			
		} catch (Exception e) {
			log.error("{}.{}.exception", apiCode, requestCode, e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
			if(etf0001_SL == null) {
				sResponse.putResult("total_count", "0");
				sResponse.putResult("etf0001", new ArrayList<>());
			} else {
				sResponse.putResult("total_count", "" + etf0001_SL.size());
				sResponse.putResult("etf0001", etf0001_SL);
			}
		}
		
		log.info("{}.{} ((END))", apiCode, requestCode);
		sResponse.setResponse_time(String.valueOf(new Date().getTime()));
		return sResponse;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SResponse etf0101(SRequest sRequest) {
		
		String apiCode = "etf0101";
		String error_message = "";
		String requestCode = sRequest.getData().getString("request_code", "");
		log.info("{}.{} ((START))", apiCode, requestCode);
		
		SResponse sResponse = SResponse.builder()
				.request_code(requestCode)
				.error_code(SCode.ERROR)
				.data(sRequest.getData())
				.build()
				;
		
		SLinkedHashMap etf0102_SR = null;
		List<SLinkedHashMap> etf0101_SL = null;
		try {
			
			log.info("{}.{}.query: {}", apiCode, requestCode, sRequest.getData().toJsonString());
			
			etf0102_SR = sMapperI.selectOne("etf0102_SR");
			
			String rules = "";
			if(!"".equals(sRequest.getData().getString("rules", "").trim())) {
				rules = String.format(
						"AND %s"
						, new SqlQueryBuilderFactory()
							.builder()
							.build(sRequest.getData().getString("rules"))
							.getQuery(true)
						)
						;
			}
			
			SLinkedHashMap query = new SLinkedHashMap()
					.add("trdd", etf0102_SR.getString("trdd_to"))
					.add("mmnt_date", sRequest.getData().isEmpty("mmnt_date") ? etf0102_SR.getString("trdd") : sRequest.getData().getString("mmnt_date"))
					.add("mmnt_unit", sRequest.getData().getString("mmnt_unit", "D"))
					.add("mmnt_scope", sRequest.getData().getInt("mmnt_scope", 3))
					.add("mmnt_min_max", sRequest.getData().getString("mmnt_min_max", ""))
					.add("mmnt_threshold", sRequest.getData().getDouble("mmnt_threshold", 1.0))
					.add("rules", rules)
					;
			
			query.put("req_json", query.toJsonString());
			log.info("{}.{}.req_json {}", apiCode, requestCode, query.getString("req_json", ""));
			
			etf0101_SL = sMapperI.selectList(
					"etf0101_SL"
					, query
					);
			
			sResponse.success();
			
		} catch (Exception e) {
			log.error("{}.{}.exception", apiCode, requestCode, e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
			if(etf0102_SR == null) {
				sResponse.putResult("trdd", "");
				sResponse.putResult("trdd_no", "");
			} else {
				sResponse.putResult("trdd", etf0102_SR.getString("trdd_to", ""));
				sResponse.putResult("trdd_no", etf0102_SR.getString("trdd_no_max", ""));
			}
			if(etf0101_SL == null) {
				sResponse.putResult("total_count", "0");
				sResponse.putResult("etf0101", new ArrayList<>());
			} else {
				sResponse.putResult("total_count", "" + etf0101_SL.size());
				sResponse.putResult("etf0101", etf0101_SL == null ? new ArrayList<>() : etf0101_SL);
			}
		}
		
		log.info("{}.{} ((END))", apiCode, requestCode);
		sResponse.setResponse_time(String.valueOf(new Date().getTime()));
		return sResponse;
	}
	
	@Override
	public SResponse etf0102(SRequest sRequest) {
		
		String apiCode = "etf0102";
		String error_message = "";
		String requestCode = sRequest.getData().getString("request_code", "");
		log.info("{}.{} ((START))", apiCode, requestCode);
		
		SResponse sResponse = SResponse.builder()
				.request_code(requestCode)
				.error_code(SCode.ERROR)
				.data(sRequest.getData())
				.build()
				;
		
		SLinkedHashMap etf0102_SR = null;
		try {
			
			log.info("{}.{}.query: {}", apiCode, requestCode, sRequest.getData().toJsonString());
			
			etf0102_SR = sMapperI.selectOne("etf0102_SR");
			
			sResponse.success();
			
		} catch (Exception e) {
			log.error("{}.{}.exception", apiCode, requestCode, e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
			sResponse.putResult("etf0102", etf0102_SR);
		}
		
		log.info("{}.{} ((END))", apiCode, requestCode);
		sResponse.setResponse_time(String.valueOf(new Date().getTime()));
		return sResponse;
	}
	
	@Override
	public SResponse etf0103(SRequest sRequest) {
		
		String apiCode = "etf0103";
		String error_message = "";
		String requestCode = sRequest.getData().getString("request_code", "");
		log.info("{}.{} ((START))", apiCode, requestCode);
		
		SResponse sResponse = SResponse.builder()
				.request_code(requestCode)
				.error_code(SCode.ERROR)
				.data(sRequest.getData())
				.build()
				;
		
		List<SLinkedHashMap> etf0103_SL = null;
		try {
			
			log.info("{}.{}.query: {}", apiCode, requestCode, sRequest.getData().toJsonString());
			
			etf0103_SL = sMapperI.selectList("etf0103_SL");
			
			sResponse.success();
			
		} catch (Exception e) {
			log.error("{}.{}.exception", apiCode, requestCode, e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
			if(etf0103_SL.isEmpty()) {
				sResponse.putResult("total_count", "0");
				sResponse.putResult("etf0103", new ArrayList<>());
			} else {
				sResponse.putResult("total_count", "" + etf0103_SL.size());
				sResponse.putResult("etf0103", etf0103_SL);
			}
		}
		
		log.info("{}.{} ((END))", apiCode, requestCode);
		sResponse.setResponse_time(String.valueOf(new Date().getTime()));
		return sResponse;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SResponse etf0111(SRequest sRequest) {
		
		String apiCode = "etf0111";
		String error_message = "";
		String requestCode = sRequest.getData().getString("request_code", "");
		log.info("{}.{} ((START))", apiCode, requestCode);
		
		SResponse sResponse = SResponse.builder()
				.request_code(requestCode)
				.error_code(SCode.ERROR)
				.data(sRequest.getData())
				.build()
				;
		
		SLinkedHashMap etf0111_SR = null;
		List<SLinkedHashMap> etf0111_SL = null;
		try {
			
			log.info("{}.{}.query: {}", apiCode, requestCode, sRequest.getData().toJsonString());
			
			SLinkedHashMap query = new SLinkedHashMap()
					.add("item_code", sRequest.getData().getString("item_code"))
					.add("trdd_from", sRequest.getData().getString("trdd_from"))
					.add("trdd_to", sRequest.getData().getString("trdd_to"))
					.add("page_index", sRequest.getData().getInt("page_index"))
					.add("page_size", sRequest.getData().getInt("page_size"))
					;
			query.put("req_json", query.toJsonString());
			
			etf0111_SR = sMapperI.selectOne("etf0111_SR", query);
			etf0111_SL = sMapperI.selectList("etf0111_SL", query);
			
			sResponse.success();
			
		} catch (Exception e) {
			log.info("{}.{}.exception", apiCode, requestCode, e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
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
		
		log.info("{}.{} ((END))", apiCode, requestCode);
		sResponse.setResponse_time(String.valueOf(new Date().getTime()));
		return sResponse;
	}
	
	@Override
	public SResponse etf0112(SRequest sRequest) {
		
		String apiCode = "etf0112";
		String error_message = "";
		String requestCode = sRequest.getData().getString("request_code", "");
		log.info("{}.{} ((START))", apiCode, requestCode);
		
		SResponse sResponse = SResponse.builder()
				.request_code(requestCode)
				.error_code(SCode.ERROR)
				.data(sRequest.getData())
				.build()
				;
		
		SLinkedHashMap etf0112_SR = null;
		try {
			
			log.info("{}.{}.query: {}", apiCode, requestCode, sRequest.getData().toJsonString());
			
			etf0112_SR = sMapperI.selectOne(
					"etf0112_SR"
					, new SLinkedHashMap()
						.add("item_code", sRequest.getData().getString("item_code"))
					);
			
			sResponse.success();
			
		} catch (Exception e) {
			log.info("{}.{}.exception", apiCode, requestCode, e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
			sResponse.putResult("etf0112", etf0112_SR == null ? new SLinkedHashMap() : etf0112_SR);
		}
		
		log.info("{}.{} ((END))", apiCode, requestCode);
		sResponse.setResponse_time(String.valueOf(new Date().getTime()));
		return sResponse;
	}
	
	@Override
	public SResponse etf0113(SRequest sRequest) {
		
		String apiCode = "etf0113";
		String error_message = "";
		String requestCode = sRequest.getData().getString("request_code", "");
		log.info("{}.{} ((START))", apiCode, requestCode);
		
		SResponse sResponse = SResponse.builder()
				.request_code(requestCode)
				.error_code(SCode.ERROR)
				.data(sRequest.getData())
				.build()
				;
		
		List<SLinkedHashMap> etf0113_SL = null;
		try {
			
			log.info("{}.{}.query: {}", apiCode, requestCode, sRequest.getData().toJsonString());
			
			etf0113_SL = sMapperI.selectList(
					"etf0113_SL"
					, new SLinkedHashMap()
						.add("item_code", sRequest.getData().getString("item_code"))
					);
			
			sResponse.success();
			
		} catch (Exception e) {
			log.info("{}.{}.exception", apiCode, requestCode, e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
			if(etf0113_SL == null) {
				sResponse.putResult("total_count", "0");
				sResponse.putResult("etf0113", new ArrayList<>());
			} else {
				sResponse.putResult("total_count", "" + etf0113_SL.size());
				sResponse.putResult("etf0113", etf0113_SL);
			}
		}
		
		log.info("{}.{} ((END))", apiCode, requestCode);
		sResponse.setResponse_time(String.valueOf(new Date().getTime()));
		return sResponse;
	}
	
}
