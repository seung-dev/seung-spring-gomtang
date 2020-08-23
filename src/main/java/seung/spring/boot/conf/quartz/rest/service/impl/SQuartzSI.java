package seung.spring.boot.conf.quartz.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.SDate;
import seung.java.kimchi.util.SKimchiException;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.spring.boot.conf.datasource.SMapperI;
import seung.spring.boot.conf.quartz.SQuartzH;
import seung.spring.boot.conf.quartz.rest.service.SQuartzS;
import seung.spring.boot.conf.quartz.rest.util.Quartz0101;
import seung.spring.boot.conf.quartz.rest.util.Quartz0111;
import seung.spring.boot.conf.quartz.rest.util.Quartz0112;
import seung.spring.boot.conf.quartz.rest.util.Quartz0131;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;

@Service(value = "sQuartzS")
@Slf4j
public class SQuartzSI implements SQuartzS {

	@Resource(name = "sMapperI")
	private SMapperI sMapperI;
	
	@Resource(name = "sQuartzH")
	private SQuartzH sQuartzH;
	
	/* (non-javadoc)
	 * @see seung.spring.gomtang.quartz.service.SQuartzS#quartz0101(seung.spring.boot.conf.web.util.SRequest)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public SResponse quartz0101(SRequest sRequest, Quartz0101 quartz0101) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(quartz0101.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(quartz0101)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		SLinkedHashMap query = null;
		List<SLinkedHashMap> quartz0101_SL = null;
		String error_message = "";
		try {
			
			log.info("({}) request={}", sResponse.getRequest_code(), quartz0101.toJsonString());
			
			quartz0101_SL = sMapperI.selectList("quartz0101_SL");
			
			for(SLinkedHashMap job : quartz0101_SL) {
				
				job.putAll(sQuartzH.detail(job.getString("job_group"), job.getString("job_name")));
				
			}// end of loop job
			
			sResponse.success();
			
		} catch (Exception e) {
			log.info("({}) query={}", sResponse.getRequest_code(), query.toJsonString(), e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
			if(quartz0101_SL.isEmpty()) {
				sResponse.putResponse("quartz0101_S", 0);
				sResponse.putResponse("quartz0101_SL", new ArrayList<>());
			} else {
				sResponse.putResponse("quartz0101_S", quartz0101_SL.size());
				sResponse.putResponse("quartz0101_SL", quartz0101_SL);
			}
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	/* (non-javadoc)
	 * @see seung.spring.gomtang.quartz.service.SQuartzS#quartz0111(seung.spring.boot.conf.web.util.SRequest)
	 */
	@Override
	public SResponse quartz0111(SRequest sRequest, Quartz0111 quartz0111) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(quartz0111.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(quartz0111)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		SLinkedHashMap query = null;
		String quartz0111_IR = "";
		String error_message = "";
		try {
			
			log.info("({}) request={}", sResponse.getRequest_code(), quartz0111.toJsonString());
			
			query = new SLinkedHashMap(quartz0111);
			
			quartz0111_IR = sQuartzH.addSimple(
					query.getString("job_group")
					, query.getString("job_name")
					, query.getString("dscr", "")
					, query.getString("clss")
					, query.getString("date_from", "")
					, query.getString("date_to", "")
					, query.getLong("intv", 1000 * 60l)
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
			sResponse.putResponse("quartz0111_IR", quartz0111_IR);
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	/* (non-javadoc)
	 * @see seung.spring.gomtang.quartz.service.SQuartzS#quartz0111(seung.spring.boot.conf.web.util.SRequest)
	 */
	@Override
	public SResponse quartz0112(SRequest sRequest, Quartz0112 quartz0112) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(quartz0112.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(quartz0112)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		SLinkedHashMap query = null;
		String quartz0112_IR = "";
		String error_message = "";
		try {
			
			log.info("({}) request={}", sResponse.getRequest_code(), quartz0112.toJsonString());
			
			query = new SLinkedHashMap(quartz0112);
			
			quartz0112_IR = sQuartzH.addCron(
					query.getString("job_group")
					, query.getString("job_name")
					, query.getString("dscr", "")
					, query.getString("clss")
					, query.getString("date_from", "")
					, query.getString("date_to", "")
					, query.getString("cron_expr")
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
			sResponse.putResponse("quartz0112_IR", quartz0112_IR);
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	/* (non-javadoc)
	 * @see seung.spring.gomtang.quartz.service.SQuartzS#quartz0111(seung.spring.boot.conf.web.util.SRequest)
	 */
	@Override
	public SResponse quartz0121(SRequest sRequest, Quartz0111 quartz0111) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(quartz0111.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(quartz0111)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		SLinkedHashMap query = null;
		String quartz0121_UR = "";
		String error_message = "";
		try {
			
			log.info("({}) request={}", sResponse.getRequest_code(), quartz0111.toJsonString());
			
			query = new SLinkedHashMap(quartz0111);
			
			quartz0121_UR = sQuartzH.editSimple(
					query.getString("job_group")
					, query.getString("job_name")
					, query.getString("date_from", "")
					, query.getString("date_to", "")
					, query.getLong("intv", 1000 * 60l)
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
			sResponse.putResponse("quartz0121_UR", quartz0121_UR);
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	/* (non-javadoc)
	 * @see seung.spring.gomtang.quartz.service.SQuartzS#quartz0131(seung.spring.boot.conf.web.util.SRequest)
	 */
	@Override
	public SResponse quartz0131(SRequest sRequest, Quartz0131 quartz0131) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(quartz0131.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(quartz0131)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		SLinkedHashMap query = null;
		int quartz0131_DR = 0;
		String error_message = "";
		try {
			
			log.info("({}) request={}", sResponse.getRequest_code(), quartz0131.toJsonString());
			
			query = new SLinkedHashMap(quartz0131);
			
			quartz0131_DR = sQuartzH.delete(
					query.getString("job_group")
					, query.getString("job_name")
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
			sResponse.putResponse("quartz0131_DR", quartz0131_DR);
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
//	/* (non-javadoc)
//	 * @see seung.spring.gomtang.quartz.service.SQuartzS#quartz0121(seung.spring.boot.conf.web.util.SRequest)
//	 */
//	@Override
//	public SResponse quartz0121(SRequest sRequest, Quartz0111 quartz0111) {
//		
//		log.debug("run");
//		
//		String requestCode = sRequest.getData().getString("request_code", "");
//		
//		SResponse sResponse = SResponse.builder()
//				.request_code(requestCode)
//				.data(sRequest.getData())
//				.build()
//				;
//		
//		String quartz0121 = "";
//		try {
//			
//			log.debug("{}.sRequest: {}", requestCode, sRequest.toJsonString(true));
//			
//			quartz0121 = sQuartzH.editSimple(
//					sRequest.getData().getString("job_group")
//					, sRequest.getData().getString("job_name")
//					, sRequest.getData().getString("date_from", "")
//					, sRequest.getData().getString("date_to", "")
//					, sRequest.getData().getLong("intv", 1000 * 60l)
//					);
//			
//			sResponse.success();
//			
//		} catch (SKimchiException e) {
//			log.error("(({})) Failed to update simple job.", requestCode, e);
//			sResponse.setError_message(ExceptionUtils.getStackTrace(e));
//		} catch (SchedulerException e) {
//			log.error("(({})) Failed to update simple job.", requestCode, e);
//			sResponse.setError_message(ExceptionUtils.getStackTrace(e));
//		} finally {
//			sResponse.putResult("quartz0121", quartz0121);
//		}
//		
//		sResponse.setResponse_time(SDate.getDateString());
//		return sResponse;
//	}
//	
//	/* (non-javadoc)
//	 * @see seung.spring.gomtang.quartz.service.SQuartzS#quartz0122(seung.spring.boot.conf.web.util.SRequest)
//	 */
//	@Override
//	public SResponse quartz0122(SRequest sRequest) {
//		
//		log.debug("run");
//		
//		String requestCode = sRequest.getData().getString("request_code", "");
//		
//		SResponse sResponse = SResponse.builder()
//				.request_code(requestCode)
//				.data(sRequest.getData())
//				.build()
//				;
//		
//		String quartz0122 = "";
//		try {
//			
//			log.debug("{}.sRequest: {}", requestCode, sRequest.toJsonString(true));
//			
//			quartz0122 = sQuartzH.editCron(
//					sRequest.getData().getString("job_group")
//					, sRequest.getData().getString("job_name")
//					, sRequest.getData().getString("date_from", "")
//					, sRequest.getData().getString("date_to", "")
//					, sRequest.getData().getString("cron_expr")
//					);
//			
//			sResponse.success();
//			
//		} catch (SKimchiException e) {
//			log.error("(({})) Failed to update cron job.", requestCode, e);
//			sResponse.setError_message(ExceptionUtils.getStackTrace(e));
//		} catch (SchedulerException e) {
//			log.error("(({})) Failed to update cron job.", requestCode, e);
//			sResponse.setError_message(ExceptionUtils.getStackTrace(e));
//		} finally {
//			sResponse.putResult("quartz0122", quartz0122);
//		}
//		
//		sResponse.setResponse_time(SDate.getDateString());
//		return sResponse;
//	}
//	
//	/* (non-javadoc)
//	 * @see seung.spring.gomtang.quartz.service.SQuartzS#quartz0131(seung.spring.boot.conf.web.util.SRequest)
//	 */
//	@Override
//	public SResponse quartz0131(SRequest sRequest) {
//		
//		log.debug("run");
//		
//		String requestCode = sRequest.getData().getString("request_code", "");
//		
//		SResponse sResponse = SResponse.builder()
//				.request_code(requestCode)
//				.data(sRequest.getData())
//				.build()
//				;
//		
//		int quartz0131 = 0;
//		try {
//			
//			log.debug("{}.sRequest: {}", requestCode, sRequest.toJsonString(true));
//			
//			quartz0131 = sQuartzH.delete(
//					sRequest.getData().getString("job_group")
//					, sRequest.getData().getString("job_name")
//					);
//			
//			sResponse.success();
//			
//		} catch (SKimchiException e) {
//			log.error("(({})) Failed to pause job.", requestCode, e);
//			sResponse.setError_message(ExceptionUtils.getStackTrace(e));
//		} catch (SchedulerException e) {
//			log.error("(({})) Failed to pause job.", requestCode, e);
//			sResponse.setError_message(ExceptionUtils.getStackTrace(e));
//		} finally {
//			sResponse.putResult("quartz0131", quartz0131);
//		}
//		
//		sResponse.setResponse_time(SDate.getDateString());
//		return sResponse;
//	}
//	
//	/* (non-javadoc)
//	 * @see seung.spring.gomtang.quartz.service.SQuartzS#quartz0110(seung.spring.boot.conf.web.util.SRequest)
//	 */
//	@Override
//	public SResponse quartz0141(SRequest sRequest) {
//		
//		log.debug("run");
//		
//		String requestCode = sRequest.getData().getString("request_code", "");
//		
//		SResponse sResponse = SResponse.builder()
//				.request_code(requestCode)
//				.data(sRequest.getData())
//				.build()
//				;
//		
//		String quartz0141 = "";
//		try {
//			
//			log.debug("{}.sRequest: {}", requestCode, sRequest.toJsonString(true));
//			
//			quartz0141 = sQuartzH.run(
//					sRequest.getData().getString("job_group")
//					, sRequest.getData().getString("job_name")
//					);
//			
//			sResponse.success();
//			
//		} catch (SKimchiException e) {
//			log.error("(({})) Failed to run job.", requestCode, e);
//			sResponse.setError_message(ExceptionUtils.getStackTrace(e));
//		} catch (SchedulerException e) {
//			log.error("(({})) Failed to run job.", requestCode, e);
//			sResponse.setError_message(ExceptionUtils.getStackTrace(e));
//		} finally {
//			sResponse.putResult("quartz0141", quartz0141);
//		}
//		
//		sResponse.setResponse_time(SDate.getDateString());
//		return sResponse;
//	}
//	
//	/* (non-javadoc)
//	 * @see seung.spring.gomtang.quartz.service.SQuartzS#quartz0111(seung.spring.boot.conf.web.util.SRequest)
//	 */
//	@Override
//	public SResponse quartz0142(SRequest sRequest) {
//		
//		log.debug("run");
//		
//		String requestCode = sRequest.getData().getString("request_code", "");
//		
//		SResponse sResponse = SResponse.builder()
//				.request_code(requestCode)
//				.data(sRequest.getData())
//				.build()
//				;
//		
//		String quartz0142 = "";
//		try {
//			
//			log.debug("{}.sRequest: {}", requestCode, sRequest.toJsonString(true));
//			
//			quartz0142 = sQuartzH.trigger(
//					sRequest.getData().getString("job_group")
//					, sRequest.getData().getString("job_name")
//					);
//			
//			sResponse.success();
//			
//		} catch (SKimchiException e) {
//			log.error("(({})) Failed to trigger job.", requestCode, e);
//			sResponse.setError_message(ExceptionUtils.getStackTrace(e));
//		} catch (SchedulerException e) {
//			log.error("(({})) Failed to trigger job.", requestCode, e);
//			sResponse.setError_message(ExceptionUtils.getStackTrace(e));
//		} finally {
//			sResponse.putResult("quartz0142", quartz0142);
//		}
//		
//		sResponse.setResponse_time(SDate.getDateString());
//		return sResponse;
//	}
//	
//	/* (non-javadoc)
//	 * @see seung.spring.gomtang.quartz.service.SQuartzS#quartz0120(seung.spring.boot.conf.web.util.SRequest)
//	 */
//	@Override
//	public SResponse quartz0143(SRequest sRequest) {
//		
//		log.debug("run");
//		
//		String requestCode = sRequest.getData().getString("request_code", "");
//		
//		SResponse sResponse = SResponse.builder()
//				.request_code(requestCode)
//				.data(sRequest.getData())
//				.build()
//				;
//		
//		String quartz0143 = "";
//		try {
//			
//			log.debug("{}.sRequest: {}", requestCode, sRequest.toJsonString(true));
//			
//			quartz0143 = sQuartzH.pause(
//					sRequest.getData().getString("job_group")
//					, sRequest.getData().getString("job_name")
//					);
//			
//			sResponse.success();
//			
//		} catch (SKimchiException e) {
//			log.error("(({})) Failed to pause job.", requestCode, e);
//			sResponse.setError_message(ExceptionUtils.getStackTrace(e));
//		} catch (SchedulerException e) {
//			log.error("(({})) Failed to pause job.", requestCode, e);
//			sResponse.setError_message(ExceptionUtils.getStackTrace(e));
//		} finally {
//			sResponse.putResult("quartz0143", quartz0143);
//		}
//		
//		sResponse.setResponse_time(SDate.getDateString());
//		return sResponse;
//	}
	
}
