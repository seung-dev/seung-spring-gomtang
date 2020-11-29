package seung.spring.gomtang.job.fin.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.SConvert;
import seung.java.kimchi.SDate;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.spring.boot.conf.SProperties;
import seung.spring.boot.conf.datasource.SMapperI;
import seung.spring.gomtang.util.SGomtangException;

@Component("sEbestS")
@Slf4j
public class SEbestSI {

	@Resource(name = "sProperties")
	private SProperties sProperties;
	
	@Resource(name = "sMapperI")
	private SMapperI sMapperI;
	
	@SuppressWarnings("unchecked")
	public synchronized String sr66666(
			String jobGroup
			, String jobName
			, String server
			) {
		
		String errorCode = "E999";
		String message = "";
		String schdNo = "";
		
		SLinkedHashMap jobHistMap = new SLinkedHashMap()
				.add("job_group", jobGroup)
				.add("job_name", jobName)
				.add("schd_set", jobName)
				.add("schd_code", "sr66666")
				.add("error_code", errorCode)
				.add("message", message)
				;
		
		String sr66666 = "";
		try {
			
			schdNo = sMapperI.selectOne("schd_no").getString("schd_no", "");
			jobHistMap.put("schd_no", schdNo);
			jobHistMap.put("job_data", jobHistMap.toJsonString());
			
			log.info(
					"{}.{}.{} ((START))"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					);
			
			sMapperI.insert("schd_prev", jobHistMap);
			
			HttpResponse<byte[]> httpResponse = Unirest
					.get("http://beforeALB-1915436010.ap-northeast-2.elb.amazonaws.com/kinsight/StockReset/sr66666")
					.connectTimeout(1000 * 10)
					.socketTimeout(1000 * 60 * 10)
					.header("Content-Type", "application/json")
					.header(sProperties.getJob().getProperty("seung.job.pridecho.api.key.name", ""), sProperties.getJob().getProperty("seung.job.pridecho.api.key.value", ""))
					.header("server", server)
					.asBytes()
					;
			
			sr66666 = new String(httpResponse.getBody(), "UTF-8");
			
			message = String.format(
					"sr66666=%s"
					, sr66666
					);
			
			if(sr66666.contains("1111")) {
				errorCode = "0000";
			} else {
				log.error(
						"{}.{}.{}.{}={}"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, "sr66666"
						, sr66666
						);
			}
			
		} catch (Exception e) {
			log.error(
					"{}.{}.{}.exception={}"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					, jobHistMap.getString("exception", "" + e)
					, e
					);
			message = ExceptionUtils.getStackTrace(e);
			if(message == null || "".equals(message)) {
				message = "" + e;
			}
		} finally {
			jobHistMap.put("error_code", errorCode);
			jobHistMap.put("message", message);
			log.info(
					"{}.{}.{}.error_code={}"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					, jobHistMap.getString("error_code", "")
					);
			sMapperI.insert("schd_post", jobHistMap);
			log.info(
					"{}.{}.{} ((END))"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					);
		}
		
		return errorCode;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized String sr99999(
			String jobGroup
			, String jobName
			, String server
			) {
		
		String errorCode = "E999";
		String message = "";
		String schdNo = "";
		
		SLinkedHashMap jobHistMap = new SLinkedHashMap()
				.add("job_group", jobGroup)
				.add("job_name", jobName)
				.add("schd_set", jobName)
				.add("schd_code", "sr99999")
				.add("error_code", errorCode)
				.add("message", message)
				;
		
		String sr99999 = "";
		try {
			
			schdNo = sMapperI.selectOne("schd_no").getString("schd_no", "");
			jobHistMap.put("schd_no", schdNo);
			jobHistMap.put("job_data", jobHistMap.toJsonString());
			
			log.info(
					"{}.{}.{} ((START))"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					);
			
			sMapperI.insert("schd_prev", jobHistMap);
			
			HttpResponse<byte[]> httpResponse = Unirest
					.get("http://beforeALB-1915436010.ap-northeast-2.elb.amazonaws.com/kinsight/StockReset/sr99999")
					.connectTimeout(1000 * 10)
					.socketTimeout(1000 * 60 * 10)
					.header(sProperties.getJob().getProperty("seung.job.pridecho.api.key.name", ""), sProperties.getJob().getProperty("seung.job.pridecho.api.key.value", ""))
					.header("Content-Type", "application/json")
					.header("server", server)
					.asBytes()
					;
			
			sr99999 = new String(httpResponse.getBody(), "UTF-8");
			
			message = String.format(
					"sr99999=%s"
					, sr99999
					);
			
			if(sr99999.contains("1111")) {
				errorCode = "0000";
			} else {
				log.error(
						"{}.{}.{}.{}={}"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, "sr99999"
						, sr99999
						);
			}
			
		} catch (Exception e) {
			log.error(
					"{}.{}.{}.exception={}"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					, jobHistMap.getString("exception", "" + e)
					, e
					);
			message = ExceptionUtils.getStackTrace(e);
			if(message == null || "".equals(message)) {
				message = "" + e;
			}
		} finally {
			jobHistMap.put("error_code", errorCode);
			jobHistMap.put("message", message);
			log.info(
					"{}.{}.{}.error_code={}"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					, jobHistMap.getString("error_code", "")
					);
			sMapperI.insert("schd_post", jobHistMap);
			log.info(
					"{}.{}.{} ((END))"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					);
		}
		
		return errorCode;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized String t8430(
			String jobGroup
			, String jobName
			) {
		
		String errorCode = "E999";
		String message = "";
		String schdNo = "";
		
		SLinkedHashMap jobHistMap = new SLinkedHashMap()
				.add("job_group", jobGroup)
				.add("job_name", jobName)
				.add("schd_set", jobName)
				.add("schd_code", "t8430")
				.add("error_code", errorCode)
				.add("message", message)
				;
		
		SLinkedHashMap query = null;
		String responseText = "";
		SLinkedHashMap response = null;
		List<SLinkedHashMap> eb_t8430_SL = null;
		SLinkedHashMap hash = new SLinkedHashMap();
		int eb_t8430_IGNORE = 0;
		int eb_t8430_IR = 0;
		int eb_t8430_UR = 0;
		try {
			
			schdNo = sMapperI.selectOne("schd_no").getString("schd_no", "");
			jobHistMap.put("schd_no", schdNo);
			jobHistMap.put("job_data", jobHistMap.toJsonString());
			
			log.info(
					"{}.{}.{} ((START))"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					);
			
			sMapperI.insert("schd_prev", jobHistMap);
			
			HttpResponse<byte[]> httpResponse = null;
			
			httpResponse = Unirest
					.post(sProperties.getJob().getProperty("seung.job.ebest.t8430.url", ""))
					.connectTimeout(1000 * 10)
					.socketTimeout(1000 * 30)
					.header("Content-Type", "application/json")
					.header(sProperties.getJob().getProperty("seung.job.pridecho.api.key.name", ""), sProperties.getJob().getProperty("seung.job.pridecho.api.key.value", ""))
					.body(new SLinkedHashMap()
							.add("request_code", schdNo)
							.toJsonString()
							)
					.asBytes()
					;
			
			if(HttpStatus.OK.value() != httpResponse.getStatus()) {
				message = String.format(
						"%s.%s.%s.response_code=%d"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, httpResponse.getStatus()
						);
				log.error(message);
				throw new SGomtangException(message);
			}
			
			responseText = new String(httpResponse.getBody(), "UTF-8");
			response = new SLinkedHashMap(responseText);
			
			String shcode = "";
			eb_t8430_SL = sMapperI.selectList("eb_t8430_SL");
			for(SLinkedHashMap eb_t8430_SR : eb_t8430_SL) {
				hash.put(eb_t8430_SR.getString("shcode"), eb_t8430_SR.getString("hash"));
			}
			
			int loopTry = 0;
			for(SLinkedHashMap item : response.getSLinkedHashMap("response").getListSLinkedHashMap("t8430_SL")) {
				
				if(loopTry++ % 100 == 0) {
					log.info(
							"{}.{}.{}.try={}"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							, loopTry
							);
				}
				
				shcode = item.getString("shcode");
				
				query = new SLinkedHashMap();
				query.put("shcode", item.getString("shcode"));
				query.put("gubun", item.getString("gubun"));
				query.put("etfgubun", item.getString("etfgubun"));
				query.put("hname", item.getString("hname"));
				query.put("expcode", item.getString("expcode"));
				query.put("uplmtprice", item.getLong("uplmtprice"));
				query.put("dnlmtprice", item.getLong("dnlmtprice"));
				query.put("jnilclose", item.getLong("jnilclose"));
				query.put("memedan", item.getString("memedan"));
				query.put("recprice", item.getLong("recprice"));
				query.put("hash", SConvert.digestToHex("MD5", query.toJsonString()));
				
				if(hash.getString(shcode, "").equals(query.getString("hash", ""))) {
					eb_t8430_IGNORE++;
					continue;
				}
				
				if(hash.isEmpty(shcode)) {
					eb_t8430_IR += sMapperI.insert("eb_t8430_IR", query);
				} else {
					eb_t8430_UR += sMapperI.update("eb_t8430_UR", query);
				}
				
			}
			
			message = String.format(
					"eb_t8430_IGNORE=%d, eb_t8430_IR=%d, eb_t8430_UR=%d"
					, eb_t8430_IGNORE
					, eb_t8430_IR
					, eb_t8430_UR
					)
					;
			
			errorCode = "0000";
			
		} catch (Exception e) {
			log.error(
					"{}.{}.{}.exception={}"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					, jobHistMap.getString("exception", "" + e)
					, e
					);
			if(query != null) {
				log.error(
						"{}.{}.{}.query={}"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, query.toJsonString(true)
						);
			}
			jobHistMap.put("message", ExceptionUtils.getStackTrace(e));
		} finally {
			jobHistMap.put("error_code", errorCode);
			jobHistMap.put("message", message);
			log.info(
					"{}.{}.{}.error_code={}"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					, jobHistMap.getString("error_code", "")
					);
			sMapperI.insert("schd_post", jobHistMap);
			log.info(
					"{}.{}.{} ((END))"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					);
		}
		
		return errorCode;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized String t9945(
			String jobGroup
			, String jobName
			) {
		
		String errorCode = "E999";
		String message = "";
		String schdNo = "";
		
		SLinkedHashMap jobHistMap = new SLinkedHashMap()
				.add("job_group", jobGroup)
				.add("job_name", jobName)
				.add("schd_set", jobName)
				.add("schd_code", "t9945")
				.add("error_code", errorCode)
				.add("message", message)
				;
		
		SLinkedHashMap query = null;
		String responseText = "";
		List<SLinkedHashMap> t9945_SL = null;
		SLinkedHashMap response = null;
		List<SLinkedHashMap> eb_t9945_SL = null;
		SLinkedHashMap hash = new SLinkedHashMap();
		int eb_t9945_IGNORE = 0;
		int eb_t9945_IR = 0;
		int eb_t9945_UR = 0;
		try {
			
			schdNo = sMapperI.selectOne("schd_no").getString("schd_no", "");
			jobHistMap.put("schd_no", schdNo);
			jobHistMap.put("job_data", jobHistMap.toJsonString());
			
			log.info(
					"{}.{}.{} ((START))"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					);
			
			sMapperI.insert("schd_prev", jobHistMap);
			
			HttpResponse<byte[]> httpResponse = null;
			
			t9945_SL = new ArrayList<>();
			for(int gubun = 1; gubun <= 2; gubun++) {
				
				httpResponse = Unirest
						.post(sProperties.getJob().getProperty("seung.job.ebest.t9945.url", ""))
						.connectTimeout(1000 * 10)
						.socketTimeout(1000 * 30)
						.header("Content-Type", "application/json")
						.header(sProperties.getJob().getProperty("seung.job.pridecho.api.key.name", ""), sProperties.getJob().getProperty("seung.job.pridecho.api.key.value", ""))
						.body(new SLinkedHashMap()
								.add("request_code", schdNo)
								.add("gubun", "" + gubun)
								.toJsonString()
								)
						.asBytes()
						;
				
				if(HttpStatus.OK.value() != httpResponse.getStatus()) {
					message = String.format(
							"%s.%s.%s.response_code=%d"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							, httpResponse.getStatus()
							);
					log.error(message);
					throw new SGomtangException(message);
				}
				
				responseText = new String(httpResponse.getBody(), "UTF-8");
				response = new SLinkedHashMap(responseText);
				
				for(SLinkedHashMap item : response.getSLinkedHashMap("response").getListSLinkedHashMap("t9945_SL")) {
					t9945_SL.add(item);
				}
			}
			
			String shcode = "";
			eb_t9945_SL = sMapperI.selectList("eb_t9945_SL");
			for(SLinkedHashMap eb_t9945_SR : eb_t9945_SL) {
				hash.put(eb_t9945_SR.getString("shcode"), eb_t9945_SR.getString("hash"));
			}
			
			int loopTry = 0;
//			for(SLinkedHashMap item : response.getSLinkedHashMap("response").getListSLinkedHashMap("t9945_SL")) {
			for(SLinkedHashMap item : t9945_SL) {
				
				if(loopTry++ % 100 == 0) {
					log.info(
							"{}.{}.{}.try={}"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							, loopTry
							);
				}
				
				shcode = item.getString("shcode");
				
				query = new SLinkedHashMap();
				query.put("shcode", item.getString("shcode"));
				query.put("hname", item.getString("hname"));
				query.put("expcode", item.getString("expcode"));
				query.put("etfchk", item.getString("etfchk"));
				query.put("filler", item.getString("filler"));
				query.put("hash", SConvert.digestToHex("MD5", query.toJsonString()));
				
				if(hash.getString(shcode, "").equals(query.getString("hash", ""))) {
					eb_t9945_IGNORE++;
					continue;
				}
				
				if(hash.isEmpty(shcode)) {
					eb_t9945_IR += sMapperI.insert("eb_t9945_IR", query);
				} else {
					eb_t9945_UR += sMapperI.update("eb_t9945_UR", query);
				}
				
			}
			
			message = String.format(
					"eb_t9945_IGNORE=%d, eb_t9945_IR=%d, eb_t9945_UR=%d"
					, eb_t9945_IGNORE
					, eb_t9945_IR
					, eb_t9945_UR
					)
					;
			
			errorCode = "0000";
			
		} catch (Exception e) {
			log.error(
					"{}.{}.{}.exception={}"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					, jobHistMap.getString("exception", "" + e)
					, e
					);
			if(query != null) {
				log.error(
						"{}.{}.{}.query={}"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, query.toJsonString(true)
						);
			}
			jobHistMap.put("message", ExceptionUtils.getStackTrace(e));
		} finally {
			jobHistMap.put("error_code", errorCode);
			jobHistMap.put("message", message);
			log.info(
					"{}.{}.{}.error_code={}"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					, jobHistMap.getString("error_code", "")
					);
			sMapperI.insert("schd_post", jobHistMap);
			log.info(
					"{}.{}.{} ((END))"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					);
		}
		
		return errorCode;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized String t8413(
			String jobGroup
			, String jobName
			) {
		
		String errorCode = "E999";
		String message = "";
		String schdNo = "";
		
		SLinkedHashMap jobHistMap = new SLinkedHashMap()
				.add("job_group", jobGroup)
				.add("job_name", jobName)
				.add("schd_set", jobName)
				.add("schd_code", "t8413")
				.add("error_code", errorCode)
				.add("message", message)
				;
		
		SLinkedHashMap query = null;
		String responseText = "";
		SLinkedHashMap response = null;
		SLinkedHashMap hash = new SLinkedHashMap();
		int eb_t8413_IGNORE = 0;
		int eb_t8413_UR = 0;
		int eb_t8413_IR = 0;
		try {
			
			schdNo = sMapperI.selectOne("schd_no").getString("schd_no", "");
			jobHistMap.put("schd_no", schdNo);
			jobHistMap.put("job_data", jobHistMap.toJsonString());
			
			log.info(
					"{}.{}.{} ((START))"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					);
			
			sMapperI.insert("schd_prev", jobHistMap);
			
			int loopTry = 0;
			String shcode = "";
			HttpResponse<byte[]> httpResponse = null;
			List<SLinkedHashMap> t8413_SL = null;
			for(SLinkedHashMap eb_t8430_SR : sMapperI.selectList("eb_t8430_SL")) {
				
				if(loopTry++ % 100 == 0) {
					log.info(
							"{}.{}.{}.try={}"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							, loopTry
							);
				}
				
				shcode = eb_t8430_SR.getString("shcode");
				log.info(
						"{}.{}.{}.shcode={}"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, shcode
						);
				
				httpResponse = Unirest
						.post(sProperties.getJob().getProperty("seung.job.ebest.t8413.url", ""))
						.connectTimeout(1000 * 10)
						.socketTimeout(1000 * 30)
						.header("Content-Type", "application/json")
						.header(sProperties.getJob().getProperty("seung.job.pridecho.api.key.name", ""), sProperties.getJob().getProperty("seung.job.pridecho.api.key.value", ""))
						.body(new SLinkedHashMap()
								.add("request_code", schdNo)
								.add("gubun", "2")
								.add("repeat500", 1)
								.add("qrycnt", 20)
								.add("edate", SDate.getDateString("yyyyMMdd"))
								.add("shcode", shcode)
								.toJsonString()
								)
						.asBytes()
						;
				
				if(HttpStatus.OK.value() != httpResponse.getStatus()) {
					message = String.format(
							"%s.%s.%s.response_code=%d"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							, httpResponse.getStatus()
							);
					log.error(message);
					Thread.sleep(4500);
					continue;
				}
				
				for(SLinkedHashMap eb_t8413_SR : sMapperI.selectList("eb_t8413_SL", eb_t8430_SR)) {
					hash.put(eb_t8413_SR.getString("date"), eb_t8413_SR.getString("hash"));
				}
				
				responseText = new String(httpResponse.getBody(), "UTF-8");
				response = new SLinkedHashMap(responseText);
				
				t8413_SL = response.getSLinkedHashMap("response").getListSLinkedHashMap("t8413_SL");
				
				if(t8413_SL == null) {
					message = String.format(
							"%s.%s.%s.t8413_SL=null"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							);
					log.error(message);
					Thread.sleep(4500);
					continue;
				}
				
				if(t8413_SL.isEmpty()) {
					message = String.format(
							"%s.%s.%s.t8413_SL=empty"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							);
					log.error(message);
					Thread.sleep(4500);
					continue;
				}
				
				String date = "";
				for(SLinkedHashMap item : t8413_SL) {
					
					date = item.getString("date");
					
					query = new SLinkedHashMap();
					query.put("shcode", shcode);
					query.put("date", item.getString("date"));
					query.put("open", item.getLong("open"));
					query.put("high", item.getLong("high"));
					query.put("low", item.getLong("low"));
					query.put("close", item.getLong("close"));
					query.put("jdiff_vol", item.getLong("jdiff_vol"));
					query.put("value", item.getLong("value"));
					query.put("sign", item.getLong("sign"));
					query.put("hash", SConvert.digestToHex("MD5", query.toJsonString()));
					
					if(hash.getString(date, "").equals(query.getString("hash", ""))) {
						eb_t8413_IGNORE++;
						continue;
					}
					
					if(0 == sMapperI.update("eb_t8413_UR", query)) {
						eb_t8413_IR += sMapperI.insert("eb_t8413_IR", query);
					}
//					if(hash.isEmpty(date)) {
//						eb_t8413_IR += sMapperI.insert("eb_t8413_IR", query);
//					} else {
//						eb_t8413_UR += sMapperI.update("eb_t8413_UR", query);
//					}
					
				}
				
				Thread.sleep(1000 * 2);
				
			}// end of eb_t8430_SL
			
			message = String.format(
					"eb_t8430_IGNORE=%d, eb_t8430_IR=%d, eb_t8430_UR=%d"
					, eb_t8413_IGNORE
					, eb_t8413_IR
					, eb_t8413_UR
					)
					;
			
			errorCode = "0000";
			
		} catch (Exception e) {
			log.error(
					"{}.{}.{}.exception={}"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					, jobHistMap.getString("exception", "" + e)
					, e
					);
			if(query != null) {
				log.error(
						"{}.{}.{}.query={}"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, query.toJsonString(true)
						);
			}
			jobHistMap.put("message", ExceptionUtils.getStackTrace(e));
		} finally {
			jobHistMap.put("error_code", errorCode);
			jobHistMap.put("message", message);
			log.info(
					"{}.{}.{}.error_code={}"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					, jobHistMap.getString("error_code", "")
					);
			sMapperI.insert("schd_post", jobHistMap);
			log.info(
					"{}.{}.{} ((END))"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					);
		}
		
		return errorCode;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized String t1305(
			String jobGroup
			, String jobName
			) {
		
		String errorCode = "E999";
		String message = "";
		String schdNo = "";
		
		SLinkedHashMap jobHistMap = new SLinkedHashMap()
				.add("job_group", jobGroup)
				.add("job_name", jobName)
				.add("schd_set", jobName)
				.add("schd_code", "t1305")
				.add("error_code", errorCode)
				.add("message", message)
				;
		
		SLinkedHashMap query = null;
		String responseText = "";
		SLinkedHashMap response = null;
		SLinkedHashMap hash = new SLinkedHashMap();
		int eb_t1305_IGNORE = 0;
		int eb_t1305_UR = 0;
		int eb_t1305_IR = 0;
		try {
			
			schdNo = sMapperI.selectOne("schd_no").getString("schd_no", "");
			jobHistMap.put("schd_no", schdNo);
			jobHistMap.put("job_data", jobHistMap.toJsonString());
			
			log.info(
					"{}.{}.{} ((START))"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					);
			
			sMapperI.insert("schd_prev", jobHistMap);
			
			int loopTry = 0;
			String shcode = "";
			HttpResponse<byte[]> httpResponse = null;
			List<SLinkedHashMap> t1305_SL = null;
			for(SLinkedHashMap eb_t8430_SR : sMapperI.selectList("eb_t8430_SL")) {
				
				if(loopTry++ % 100 == 0) {
					log.info(
							"{}.{}.{}.try={}"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							, loopTry
							);
				}
				
				shcode = eb_t8430_SR.getString("shcode");
				log.info(
						"{}.{}.{}.shcode={}"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, shcode
						);
				
				httpResponse = Unirest
						.post(sProperties.getJob().getProperty("seung.job.ebest.t1305.url", ""))
						.connectTimeout(1000 * 10)
						.socketTimeout(1000 * 30)
						.header("Content-Type", "application/json")
						.header(sProperties.getJob().getProperty("seung.job.pridecho.api.key.name", ""), sProperties.getJob().getProperty("seung.job.pridecho.api.key.value", ""))
						.body(new SLinkedHashMap()
								.add("request_code", schdNo)
								.add("shcode", shcode)
								.add("dwmcode", 1)
								.add("idx", 0)
								.add("cnt", 20)
								.add("repeatN", 1)
								.add("date", "")
								.toJsonString()
								)
						.asBytes()
						;
				
				if(HttpStatus.OK.value() != httpResponse.getStatus()) {
					message = String.format(
							"%s.%s.%s.response_code=%d"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							, httpResponse.getStatus()
							);
					log.error(message);
					Thread.sleep(4500);
					continue;
				}
				
				for(SLinkedHashMap eb_t1305_SR : sMapperI.selectList("eb_t1305_SL", eb_t8430_SR)) {
					hash.put(eb_t1305_SR.getString("date"), eb_t1305_SR.getString("hash"));
				}
				
				responseText = new String(httpResponse.getBody(), "UTF-8");
				response = new SLinkedHashMap(responseText);
				
				t1305_SL = response.getSLinkedHashMap("response").getListSLinkedHashMap("t1305_SL");
				
				if(t1305_SL == null) {
					message = String.format(
							"%s.%s.%s.t1305_SL=null"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							);
					log.error(message);
					Thread.sleep(4500);
					continue;
				}
				
				if(t1305_SL.isEmpty()) {
					message = String.format(
							"%s.%s.%s.t1305_SL=empty"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							);
					log.error(message);
					Thread.sleep(4500);
					continue;
				}
				
				String date = "";
				for(SLinkedHashMap item : t1305_SL) {
					
					date = item.getString("date");
					
					query = new SLinkedHashMap();
					query.put("shcode", shcode);
					query.put("date", item.getString("date"));
					query.put("open", item.getLong("open"));
					query.put("high", item.getLong("high"));
					query.put("low", item.getLong("low"));
					query.put("close", item.getLong("close"));
					query.put("sign", item.getString("sign"));
					query.put("change", item.getLong("change"));
					query.put("diff", item.getDouble("diff"));
					query.put("volume", item.getLong("volume"));
					query.put("fpvolume", item.getLong("fpvolume"));
					query.put("covolume", item.getLong("covolume"));
					query.put("ppvolume", item.getLong("ppvolume"));
					query.put("value", item.getLong("value"));
					query.put("marketcap", item.getLong("marketcap"));
					query.put("hash", SConvert.digestToHex("MD5", query.toJsonString()));
					
					if(hash.getString(date, "").equals(query.getString("hash", ""))) {
						eb_t1305_IGNORE++;
						continue;
					}
					
					if(0 == sMapperI.update("eb_t1305_UR", query)) {
						eb_t1305_IR += sMapperI.insert("eb_t1305_IR", query);
					}
//					if(hash.isEmpty(date)) {
//						eb_t1305_IR += sMapperI.insert("eb_t1305_IR", query);
//					} else {
//						eb_t1305_UR += sMapperI.update("eb_t1305_UR", query);
//					}
					
				}
				
				Thread.sleep(1000 * 2);
				
			}// end of eb_t8430_SL
			
			message = String.format(
					"eb_t1305_IGNORE=%d, eb_t1305_IR=%d, eb_t1305_UR=%d"
					, eb_t1305_IGNORE
					, eb_t1305_IR
					, eb_t1305_UR
					)
					;
			
			errorCode = "0000";
			
		} catch (Exception e) {
			log.error(
					"{}.{}.{}.exception={}"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					, jobHistMap.getString("exception", "" + e)
					, e
					);
			if(query != null) {
				log.error(
						"{}.{}.{}.query={}"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, query.toJsonString(true)
						);
			}
			jobHistMap.put("message", ExceptionUtils.getStackTrace(e));
		} finally {
			jobHistMap.put("error_code", errorCode);
			jobHistMap.put("message", message);
			log.info(
					"{}.{}.{}.error_code={}"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					, jobHistMap.getString("error_code", "")
					);
			sMapperI.insert("schd_post", jobHistMap);
			log.info(
					"{}.{}.{} ((END))"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					);
		}
		
		return errorCode;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized String t1903(
			String jobGroup
			, String jobName
			) {
		
		String errorCode = "E999";
		String message = "";
		String schdNo = "";
		
		SLinkedHashMap jobHistMap = new SLinkedHashMap()
				.add("job_group", jobGroup)
				.add("job_name", jobName)
				.add("schd_set", jobName)
				.add("schd_code", "t1903")
				.add("error_code", errorCode)
				.add("message", message)
				;
		
		SLinkedHashMap query = null;
		String responseText = "";
		SLinkedHashMap response = null;
		SLinkedHashMap hash = new SLinkedHashMap();
		int eb_t1903_IGNORE = 0;
		int eb_t1903_UR = 0;
		int eb_t1903_IR = 0;
		try {
			
			schdNo = sMapperI.selectOne("schd_no").getString("schd_no", "");
			jobHistMap.put("schd_no", schdNo);
			jobHistMap.put("job_data", jobHistMap.toJsonString());
			
			log.info(
					"{}.{}.{} ((START))"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					);
			
			sMapperI.insert("schd_prev", jobHistMap);
			
			int loopTry = 0;
			String shcode = "";
			HttpResponse<byte[]> httpResponse = null;
			List<SLinkedHashMap> t1903_SL = null;
			for(SLinkedHashMap eb_t8430_SR : sMapperI.selectList("eb_t8430_SL", jobHistMap)) {
				
				if(loopTry++ % 100 == 0) {
					log.info(
							"{}.{}.{}.try={}"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							, loopTry
							);
				}
				
				shcode = eb_t8430_SR.getString("shcode");
				log.info(
						"{}.{}.{}.shcode={}"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, shcode
						);
				
				httpResponse = Unirest
						.post(sProperties.getJob().getProperty("seung.job.ebest.t1903.url", ""))
						.connectTimeout(1000 * 10)
						.socketTimeout(1000 * 30)
						.header("Content-Type", "application/json")
						.header(sProperties.getJob().getProperty("seung.job.pridecho.api.key.name", ""), sProperties.getJob().getProperty("seung.job.pridecho.api.key.value", ""))
						.body(new SLinkedHashMap()
								.add("request_code", schdNo)
//								.add("date", SDate.getDateString("yyyyMMdd"))
								.add("date", "")
								.add("shcode", shcode)
								.add("repeat20", 1)
								.toJsonString()
								)
						.asBytes()
						;
				
				if(HttpStatus.OK.value() != httpResponse.getStatus()) {
					message = String.format(
							"%s.%s.%s.response_code=%d"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							, httpResponse.getStatus()
							);
					log.error(message);
					Thread.sleep(4500);
					continue;
				}
				
				for(SLinkedHashMap eb_t1903_SR : sMapperI.selectList("eb_t1903_SL", eb_t8430_SR)) {
					hash.put(eb_t1903_SR.getString("date"), eb_t1903_SR.getString("hash"));
				}
				
				responseText = new String(httpResponse.getBody(), "UTF-8");
				response = new SLinkedHashMap(responseText);
				
				t1903_SL = response.getSLinkedHashMap("response").getListSLinkedHashMap("t1903_SL");
				
				if(t1903_SL == null) {
					message = String.format(
							"%s.%s.%s.t1903_SL=null"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							);
					log.error(message);
					Thread.sleep(4500);
					continue;
				}
				
				if(t1903_SL.isEmpty()) {
					message = String.format(
							"%s.%s.%s.t1903_SL=empty"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							);
					log.error(message);
					Thread.sleep(4500);
					continue;
				}
				
				String date = "";
				for(SLinkedHashMap item : t1903_SL) {
					
					date = item.getString("date");
					
					query = new SLinkedHashMap();
					
					query.put("shcode", shcode);
					query.put("date", item.getString("date"));
					query.put("price", item.getDouble("price"));
					query.put("sign", item.getString("sign"));
					query.put("change", item.getDouble("change"));
					query.put("volume", item.getDouble("volume"));
					query.put("navdiff", item.getDouble("navdiff"));
					query.put("nav", item.getDouble("nav"));
					query.put("navchange", item.getDouble("navchange"));
					query.put("crate", item.getDouble("crate"));
					query.put("grate", item.getDouble("grate"));
					query.put("jisu", item.getDouble("jisu"));
					query.put("jichange", item.getDouble("jichange"));
					query.put("jirate", item.getDouble("jirate"));
					query.put("hash", SConvert.digestToHex("MD5", query.toJsonString()));
					
					if(hash.getString(date, "").equals(query.getString("hash", ""))) {
						eb_t1903_IGNORE++;
						continue;
					}
					
					if(hash.isEmpty(date)) {
						eb_t1903_IR += sMapperI.insert("eb_t1903_IR", query);
					} else {
						eb_t1903_UR += sMapperI.update("eb_t1903_UR", query);
					}
					
				}
				
				Thread.sleep(1000 * 2);
				
			}// end of eb_t8430_SL
			
			message = String.format(
					"eb_t1903_IGNORE=%d, eb_t1903_IR=%d, eb_t1903_UR=%d"
					, eb_t1903_IGNORE
					, eb_t1903_IR
					, eb_t1903_UR
					)
					;
			
			errorCode = "0000";
			
		} catch (Exception e) {
			log.error(
					"{}.{}.{}.exception={}"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					, jobHistMap.getString("exception", "" + e)
					, e
					);
			if(query != null) {
				log.error(
						"{}.{}.{}.query={}"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, query.toJsonString(true)
						);
			}
			jobHistMap.put("message", ExceptionUtils.getStackTrace(e));
		} finally {
			jobHistMap.put("error_code", errorCode);
			jobHistMap.put("message", message);
			log.info(
					"{}.{}.{}.error_code={}"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					, jobHistMap.getString("error_code", "")
					);
			sMapperI.insert("schd_post", jobHistMap);
			log.info(
					"{}.{}.{} ((END))"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					);
		}
		
		return errorCode;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized String post(
			String jobGroup
			, String jobName
			) {
		
		String errorCode = "E999";
		String message = "";
		String schdNo = "";
		
		SLinkedHashMap jobHistMap = new SLinkedHashMap()
				.add("job_group", jobGroup)
				.add("job_name", jobName)
				.add("schd_set", jobName)
				.add("schd_code", "t1903")
				.add("error_code", errorCode)
				.add("message", message)
				;
		
		SLinkedHashMap query = null;
		int f_ki_ki011001 = 0;
		int f_ki_ki031001 = 0;
		try {
			
			schdNo = sMapperI.selectOne("schd_no").getString("schd_no", "");
			jobHistMap.put("schd_no", schdNo);
			jobHistMap.put("job_data", jobHistMap.toJsonString());
			
			log.info(
					"{}.{}.{} ((START))"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					);
			
			sMapperI.insert("schd_prev", jobHistMap);
			
			SLinkedHashMap result = null;
			result = sMapperI.selectOne("f_ki_ki011001");
			f_ki_ki011001 = result.getInt("post", 0);
			
			result = sMapperI.selectOne("f_ki_ki031001");
			f_ki_ki031001 = result.getInt("f_ki_ki031001", 0);
			
			message = String.format(
					"f_ki_ki011001=%d, f_ki_ki031001=%d"
					, f_ki_ki011001
					, f_ki_ki031001
					)
					;
			
			errorCode = "0000";
			
		} catch (Exception e) {
			log.error(
					"{}.{}.{}.exception={}"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					, jobHistMap.getString("exception", "" + e)
					, e
					);
			jobHistMap.put("message", ExceptionUtils.getStackTrace(e));
		} finally {
			jobHistMap.put("error_code", errorCode);
			jobHistMap.put("message", message);
			log.info(
					"{}.{}.{}.error_code={}"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					, jobHistMap.getString("error_code", "")
					);
			sMapperI.insert("schd_post", jobHistMap);
			log.info(
					"{}.{}.{} ((END))"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					);
		}
		
		return errorCode;
	}
	
}
