package seung.spring.gomtang.rest.job.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import seung.spring.boot.conf.web.util.SReflect;
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
	
	@Override
	public SResponse sr33333(SRequest sRequest, SReflect sReflect) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(sReflect.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(sReflect)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		String error_message = "";
		try {
			
			sResponse.putResponse("sr33333", sKiwoomS.sr33333("rest", "kiwoom"));
			sResponse.success();
			
		} catch (Exception e) {
			log.info("({}) ", sResponse.getRequest_code(), e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	@Override
	public SResponse sr66666(SRequest sRequest, SReflect sReflect) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(sReflect.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(sReflect)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		String error_message = "";
		try {
			
			sResponse.putResponse("sr66666", sKiwoomS.sr66666("rest", "kiwoom"));
			sResponse.success();
			
		} catch (Exception e) {
			log.info("({}) ", sResponse.getRequest_code(), e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	@Override
	public SResponse sr99999(SRequest sRequest, SReflect sReflect) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(sReflect.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(sReflect)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		String error_message = "";
		try {
			
			sResponse.putResponse("sr99999", sKiwoomS.sr99999("rest", "kiwoom"));
			sResponse.success();
			
		} catch (Exception e) {
			log.info("({}) ", sResponse.getRequest_code(), e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	@Override
	public SResponse kw10000(SRequest sRequest, SReflect sReflect) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(sReflect.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(sReflect)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		String error_message = "";
		try {
			
			sResponse.putResponse("kw10000", sKiwoomS.kw10000("rest", "kiwoom"));
			sResponse.success();
			
		} catch (Exception e) {
			log.info("({}) ", sResponse.getRequest_code(), e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	@Override
	public SResponse tr10001(SRequest sRequest, SReflect sReflect) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(sReflect.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(sReflect)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		String error_message = "";
		try {
			
			sResponse.putResponse("tr10001", sKiwoomS.tr10001("rest", "kiwoom"));
			sResponse.success();
			
		} catch (Exception e) {
			log.info("({}) ", sResponse.getRequest_code(), e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	@Override
	public SResponse tr10081(SRequest sRequest, SReflect sReflect) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(sReflect.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(sReflect)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		String error_message = "";
		try {
			
			sResponse.putResponse("tr10081", sKiwoomS.tr10081("rest", "kiwoom"));
			sResponse.success();
			
		} catch (Exception e) {
			log.info("({}) ", sResponse.getRequest_code(), e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	@Override
	public SResponse tr40005(SRequest sRequest, SReflect sReflect) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(sReflect.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(sReflect)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		String error_message = "";
		try {
			
			sResponse.putResponse("tr40005", sKiwoomS.tr40005("rest", "kiwoom"));
			sResponse.success();
			
		} catch (Exception e) {
			log.info("({}) ", sResponse.getRequest_code(), e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	@Override
	public SResponse n0101(SRequest sRequest, SReflect sReflect) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(sReflect.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(sReflect)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		String error_message = "";
		try {
			
			sResponse.putResponse("n0101", sNaverS.n0101("rest", "naver"));
			sResponse.success();
			
		} catch (Exception e) {
			log.info("({}) ", sResponse.getRequest_code(), e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	@Override
	public SResponse n0102(SRequest sRequest, SReflect sReflect) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(sReflect.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(sReflect)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		String error_message = "";
		try {
			
			sResponse.putResponse("n0102", sNaverS.n0102("rest", "naver"));
			sResponse.success();
			
		} catch (Exception e) {
			log.info("({}) ", sResponse.getRequest_code(), e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	@Override
	public SResponse n0104(SRequest sRequest, SReflect sReflect) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(sReflect.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(sReflect)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		String error_message = "";
		try {
			
			sResponse.putResponse("n0104", sNaverS.n0104("rest", "naver"));
			sResponse.success();
			
		} catch (Exception e) {
			log.info("({}) ", sResponse.getRequest_code(), e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
}
