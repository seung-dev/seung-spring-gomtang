package seung.spring.gomtang.rest.job.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.util.SCode;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;
import seung.spring.gomtang.job.fin.service.SKiwoomSI;
import seung.spring.gomtang.job.fin.service.SNaverSI;
import seung.spring.gomtang.rest.job.service.SJobS;

@Slf4j
@Service(value = "sJobS")
public class SJobSI implements SJobS {

	@Resource(name = "sKiwoomS")
	private SKiwoomSI sKiwoomS;
	
	@Resource(name = "sNaverS")
	private SNaverSI sNaverS;
	
	@SuppressWarnings("unchecked")
	@Override
	public SResponse sr33333(SRequest sRequest) {
		
		String apiCode = "sr33333";
		String error_message = "";
		String requestCode = sRequest.getData().getString("request_code", "");
		log.info("{}.{} ((START))", apiCode, requestCode);
		
		SResponse sResponse = SResponse.builder()
				.request_code(requestCode)
				.error_code(SCode.ERROR)
				.data(sRequest.getData())
				.build()
				;
		
		try {
			
			log.info("{}.{}.query: {}", apiCode, requestCode, sRequest.getData().toJsonString());
			
			sResponse.getResult().put("sr33333", sKiwoomS.sr33333("rest", "kiwoom"));
			sResponse.success();
			
		} catch (Exception e) {
			log.error("{}.{}.exception", apiCode, requestCode, e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
		}
		
		return sResponse;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SResponse sr66666(SRequest sRequest) {
		
		String apiCode = "sr66666";
		String error_message = "";
		String requestCode = sRequest.getData().getString("request_code", "");
		log.info("{}.{} ((START))", apiCode, requestCode);
		
		SResponse sResponse = SResponse.builder()
				.request_code(requestCode)
				.error_code(SCode.ERROR)
				.data(sRequest.getData())
				.build()
				;
		
		try {
			
			log.info("{}.{}.query: {}", apiCode, requestCode, sRequest.getData().toJsonString());
			
			sResponse.getResult().put("sr66666", sKiwoomS.sr66666("rest", "kiwoom"));
			sResponse.success();
			
		} catch (Exception e) {
			log.error("{}.{}.exception", apiCode, requestCode, e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
		}
		
		return sResponse;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SResponse sr99999(SRequest sRequest) {
		
		String apiCode = "sr99999";
		String error_message = "";
		String requestCode = sRequest.getData().getString("request_code", "");
		log.info("{}.{} ((START))", apiCode, requestCode);
		
		SResponse sResponse = SResponse.builder()
				.request_code(requestCode)
				.error_code(SCode.ERROR)
				.data(sRequest.getData())
				.build()
				;
		
		try {
			
			log.info("{}.{}.query: {}", apiCode, requestCode, sRequest.getData().toJsonString());
			
			sResponse.getResult().put("sr99999", sKiwoomS.sr99999("rest", "kiwoom"));
			sResponse.success();
			
		} catch (Exception e) {
			log.error("{}.{}.exception", apiCode, requestCode, e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
		}
		
		return sResponse;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SResponse kw10000(SRequest sRequest) {
		
		String apiCode = "kw10000";
		String error_message = "";
		String requestCode = sRequest.getData().getString("request_code", "");
		log.info("{}.{} ((START))", apiCode, requestCode);
		
		SResponse sResponse = SResponse.builder()
				.request_code(requestCode)
				.error_code(SCode.ERROR)
				.data(sRequest.getData())
				.build()
				;
		
		try {
			
			log.info("{}.{}.query: {}", apiCode, requestCode, sRequest.getData().toJsonString());
			
			sResponse.getResult().put("kw10000", sKiwoomS.kw10000("rest", "kiwoom"));
			sResponse.success();
			
		} catch (Exception e) {
			log.error("{}.{}.exception", apiCode, requestCode, e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
		}
		
		return sResponse;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SResponse tr10001(SRequest sRequest) {
		
		String apiCode = "tr10001";
		String error_message = "";
		String requestCode = sRequest.getData().getString("request_code", "");
		log.info("{}.{} ((START))", apiCode, requestCode);
		
		SResponse sResponse = SResponse.builder()
				.request_code(requestCode)
				.error_code(SCode.ERROR)
				.data(sRequest.getData())
				.build()
				;
		
		try {
			
			log.info("{}.{}.query: {}", apiCode, requestCode, sRequest.getData().toJsonString());
			
			sResponse.getResult().put("tr10001", sKiwoomS.tr10001("rest", "kiwoom"));
			sResponse.success();
			
		} catch (Exception e) {
			log.error("{}.{}.exception", apiCode, requestCode, e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
		}
		
		return sResponse;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SResponse tr10081(SRequest sRequest) {
		
		String apiCode = "tr10081";
		String error_message = "";
		String requestCode = sRequest.getData().getString("request_code", "");
		log.info("{}.{} ((START))", apiCode, requestCode);
		
		SResponse sResponse = SResponse.builder()
				.request_code(requestCode)
				.error_code(SCode.ERROR)
				.data(sRequest.getData())
				.build()
				;
		
		try {
			
			log.info("{}.{}.query: {}", apiCode, requestCode, sRequest.getData().toJsonString());
			
			sResponse.getResult().put("tr10081", sKiwoomS.tr10081("rest", "kiwoom"));
			sResponse.success();
			
		} catch (Exception e) {
			log.error("{}.{}.exception", apiCode, requestCode, e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
		}
		
		return sResponse;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SResponse tr40005(SRequest sRequest) {
		
		String apiCode = "tr40005";
		String error_message = "";
		String requestCode = sRequest.getData().getString("request_code", "");
		log.info("{}.{} ((START))", apiCode, requestCode);
		
		SResponse sResponse = SResponse.builder()
				.request_code(requestCode)
				.error_code(SCode.ERROR)
				.data(sRequest.getData())
				.build()
				;
		
		try {
			
			log.info("{}.{}.query: {}", apiCode, requestCode, sRequest.getData().toJsonString());
			
			sResponse.getResult().put("tr40005", sKiwoomS.tr40005("rest", "kiwoom"));
			sResponse.success();
			
		} catch (Exception e) {
			log.error("{}.{}.exception", apiCode, requestCode, e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
		}
		
		return sResponse;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SResponse n0101(SRequest sRequest) {
		
		String apiCode = "n0102";
		String error_message = "";
		String requestCode = sRequest.getData().getString("request_code", "");
		log.info("{}.{} ((START))", apiCode, requestCode);
		
		SResponse sResponse = SResponse.builder()
				.request_code(requestCode)
				.error_code(SCode.ERROR)
				.data(sRequest.getData())
				.build()
				;
		
		try {
			
			log.info("{}.{}.query: {}", apiCode, requestCode, sRequest.getData().toJsonString());
			
			sResponse.getResult().put("n0101", sNaverS.n0101("rest", "naver"));
			sResponse.success();
			
		} catch (Exception e) {
			log.error("{}.{}.exception", apiCode, requestCode, e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
		}
		
		return sResponse;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SResponse n0102(SRequest sRequest) {
		
		String apiCode = "n0102";
		String error_message = "";
		String requestCode = sRequest.getData().getString("request_code", "");
		log.info("{}.{} ((START))", apiCode, requestCode);
		
		SResponse sResponse = SResponse.builder()
				.request_code(requestCode)
				.error_code(SCode.ERROR)
				.data(sRequest.getData())
				.build()
				;
		
		try {
			
			log.info("{}.{}.query: {}", apiCode, requestCode, sRequest.getData().toJsonString());
			
			sResponse.getResult().put("n0102", sNaverS.n0102("rest", "naver"));
			sResponse.success();
			
		} catch (Exception e) {
			log.error("{}.{}.exception", apiCode, requestCode, e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
		}
		
		return sResponse;
	}
	
}
