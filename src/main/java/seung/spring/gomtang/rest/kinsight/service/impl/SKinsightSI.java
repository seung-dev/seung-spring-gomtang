package seung.spring.gomtang.rest.kinsight.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.spring.boot.conf.datasource.SMapperI;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;
import seung.spring.gomtang.rest.kinsight.service.SKinsightS;
import seung.spring.gomtang.rest.kinsight.util.Ki0100;
import seung.spring.gomtang.rest.kinsight.util.Ki0110;
import seung.spring.gomtang.rest.kinsight.util.Ki0120;
import seung.spring.gomtang.rest.kinsight.util.Ki0300;
import seung.spring.gomtang.rest.kinsight.util.Ki0310;

@Service(value = "sKinsightS")
@Slf4j
public class SKinsightSI implements SKinsightS {

	@Resource(name = "sMapperI")
	private SMapperI sMapperI;
	
	@Override
	public SResponse ki0100(SRequest sRequest, Ki0100 ki0100) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(ki0100.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(ki0100)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		SLinkedHashMap query = null;
		List<SLinkedHashMap> f_ki_ki010000 = null;
		String error_message = "";
		try {
			
			log.info("({}) request={}", sResponse.getRequest_code(), ki0100.toJsonString());
			
			query = new SLinkedHashMap()
					.add("request", ki0100.toJsonString())
					;
			
			f_ki_ki010000 = sMapperI.selectList(
					"f_ki_ki010000"
					, query
					);
			
			sResponse.success();
			
		} catch (Exception e) {
			log.info("({}) query={}", sResponse.getRequest_code(), query.toJsonString(), e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
			if(f_ki_ki010000.isEmpty()) {
				sResponse.putResponse("ki0100_S", 0);
				sResponse.putResponse("ki0100_SL", new ArrayList<>());
			} else {
				sResponse.putResponse("ki0100_S", f_ki_ki010000.size());
				sResponse.putResponse("ki0100_SL", f_ki_ki010000);
			}
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SResponse ki0110(SRequest sRequest, Ki0110 ki0110) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(ki0110.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(ki0110)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		SLinkedHashMap query = null;
		SLinkedHashMap f_ki_ki011000 = null;
		String error_message = "";
		try {
			
			log.info("({}) request={}", sResponse.getRequest_code(), ki0110.toJsonString());
			
			query = new SLinkedHashMap()
					.add("request", ki0110.toJsonString())
					;
			
			f_ki_ki011000 = sMapperI.selectOne(
					"f_ki_ki011000"
					, query
					);
			
			sResponse.success();
			
		} catch (Exception e) {
			log.info("({}) query={}", sResponse.getRequest_code(), query.toJsonString(), e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
			sResponse.getResponse().putAll(f_ki_ki011000 == null ? new SLinkedHashMap() : f_ki_ki011000);
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	@Override
	public SResponse ki0120(SRequest sRequest, Ki0120 ki0120) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(ki0120.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(ki0120)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		SLinkedHashMap query = null;
		List<SLinkedHashMap> f_ki_ki012000 = null;
		String error_message = "";
		try {
			
			log.info("({}) request={}", sResponse.getRequest_code(), ki0120.toJsonString());
			
			query = new SLinkedHashMap()
					.add("request", ki0120.toJsonString())
					;
			
			f_ki_ki012000 = sMapperI.selectList(
					"f_ki_ki012000"
					, query
					);
			
			sResponse.success();
			
		} catch (Exception e) {
			log.info("({}) query={}", sResponse.getRequest_code(), query.toJsonString(), e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
			if(f_ki_ki012000.isEmpty()) {
				sResponse.putResponse("ki0120_S", 0);
				sResponse.putResponse("ki0120_SL", new ArrayList<>());
			} else {
				sResponse.putResponse("ki0120_S", f_ki_ki012000.size());
				sResponse.putResponse("ki0120_SL", f_ki_ki012000);
			}
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	@Override
	public SResponse ki0300(SRequest sRequest, Ki0300 ki0300) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(ki0300.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(ki0300)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		SLinkedHashMap query = null;
		List<SLinkedHashMap> f_ki_ki030000 = null;
		String error_message = "";
		try {
			
			log.info("({}) request={}", sResponse.getRequest_code(), ki0300.toJsonString());
			
			query = new SLinkedHashMap()
					.add("request", ki0300.toJsonString())
					;
			
			f_ki_ki030000 = sMapperI.selectList(
					"f_ki_ki030000"
					, query
					);
			
			sResponse.success();
			
		} catch (Exception e) {
			log.info("({}) query={}", sResponse.getRequest_code(), query.toJsonString(), e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
			if(f_ki_ki030000.isEmpty()) {
				sResponse.putResponse("ki0300_S", 0);
				sResponse.putResponse("ki0300_SL", new ArrayList<>());
			} else {
				sResponse.putResponse("ki0300_S", f_ki_ki030000.size());
				sResponse.putResponse("ki0300_SL", f_ki_ki030000);
			}
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	@Override
	public SResponse ki0310(SRequest sRequest, Ki0310 ki0310) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(ki0310.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(ki0310)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		SLinkedHashMap query = null;
		SLinkedHashMap f_ki_ki011000 = null;
		List<SLinkedHashMap> f_ki_ki031000 = null;
		String error_message = "";
		try {
			
			log.info("({}) request={}", sResponse.getRequest_code(), ki0310.toJsonString());
			
			query = new SLinkedHashMap()
					.add("request", ki0310.toJsonString())
					;
			
			f_ki_ki011000 = sMapperI.selectOne(
					"f_ki_ki011000"
					, query
					);
			
			log.info(query.toJsonString(true));
			f_ki_ki031000 = sMapperI.selectList(
					"f_ki_ki031000"
					, query
					);
			
			sResponse.success();
			
		} catch (Exception e) {
			log.info("({}) query={}", sResponse.getRequest_code(), query.toJsonString(), e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
			if(f_ki_ki011000 != null) {
				sResponse.putResponse("trdd", "");
				sResponse.putResponse("trdd_no", "");
			} else {
				sResponse.putResponse("trdd", f_ki_ki011000.getString("trdd_to", ""));
				sResponse.putResponse("trdd_no", f_ki_ki011000.getString("trdd_no_max", ""));
			}
			if(f_ki_ki031000.isEmpty()) {
				sResponse.putResponse("ki0310_S", 0);
				sResponse.putResponse("ki0310_SL", new ArrayList<>());
			} else {
				sResponse.putResponse("ki0310_S", f_ki_ki031000.size());
				sResponse.putResponse("ki0310_SL", f_ki_ki031000);
			}
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
}
