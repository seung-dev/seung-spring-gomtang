package seung.spring.gomtang.job.fin.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
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
					.get(sProperties.getJob().getProperty("seung.job.kiwoom.sr66666.url", ""))
					.connectTimeout(1000 * 3)
					.socketTimeout(1000 * 60 * 10)
					.header(sProperties.getJob().getProperty("seung.job.kiwoom.api.key.name", ""), sProperties.getJob().getProperty("seung.job.kiwoom.api.key.value", ""))
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
					.get(sProperties.getJob().getProperty("seung.job.kiwoom.sr99999.url", ""))
					.connectTimeout(1000 * 3)
					.socketTimeout(1000 * 60 * 10)
					.header(sProperties.getJob().getProperty("seung.job.kiwoom.api.key.name", ""), sProperties.getJob().getProperty("seung.job.kiwoom.api.key.value", ""))
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
					.connectTimeout(1000 * 3)
					.socketTimeout(1000 * 30)
					.header("Content-Type", "application/json")
					.header(sProperties.getJob().getProperty("seung.job.kiwoom.api.key.name", ""), sProperties.getJob().getProperty("seung.job.kiwoom.api.key.value", ""))
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
						.connectTimeout(1000 * 3)
						.socketTimeout(1000 * 30)
						.header("Content-Type", "application/json")
						.header(sProperties.getJob().getProperty("seung.job.kiwoom.api.key.name", ""), sProperties.getJob().getProperty("seung.job.kiwoom.api.key.value", ""))
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
	public synchronized String t8413_prev(
			String jobGroup
			, String jobName
			) {
		
		
		log.info("run");
		
		if(SDate.getDateInteger("HHmm") > 500 && SDate.getDateInteger("HHmm") < 910) {
			return "";
		}
		
		if(SDate.getDateInteger("HHmm") > 1800 && SDate.getDateInteger("HHmm") < 2350) {
			return "";
		}
		
		String errorCode = "E999";
		String message = "";
		String schdNo = "";
		
		new File("var/t1903").mkdirs();
		
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
		SLinkedHashMap hash = null;
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
			for(SLinkedHashMap eb_t8430_SR : sMapperI.selectList("eb_t8430_prev_SL")) {
				
				if(SDate.getDateInteger("HHmm") > 500 && SDate.getDateInteger("HHmm") < 900) {
					break;
				}
				
				if(SDate.getDateInteger("HHmm") > 1800 && SDate.getDateInteger("HHmm") < 2350) {
					break;
				}
				
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
				if(new File("var/t1903/" + shcode).exists()) {
					continue;
				}
				log.info(
						"{}.{}.{}.shcode={}"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, shcode
						);
				
				
				httpResponse = Unirest
						.post(sProperties.getJob().getProperty("seung.job.ebest.t1903.url", ""))
						.connectTimeout(1000 * 3)
						.socketTimeout(1000 * 30)
						.header("Content-Type", "application/json")
						.header(sProperties.getJob().getProperty("seung.job.kiwoom.api.key.name", ""), sProperties.getJob().getProperty("seung.job.kiwoom.api.key.value", ""))
						.body(new SLinkedHashMap()
								.add("request_code", schdNo)
								.add("date", "")
								.add("shcode", shcode)
								.add("repeat20", 20)
								.toJsonString()
//								.add("request_code", schdNo)
//								.add("gubun", "2")
//								.add("repeat500", 10)
//								.add("qrycnt", 500)
////								.add("edate", eb_t8430_SR.getString("trdd_min"))
//								.add("edate", "20191220")
//								.add("shcode", shcode)
//								.toJsonString()
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
				
				responseText = new String(httpResponse.getBody(), "UTF-8");
				if(responseText != null) {
					FileUtils.writeStringToFile(new File("var/t1903/" + shcode), responseText, "UTF-8");
					Thread.sleep(1000 * 30);
					continue;
				}
				
				hash = new SLinkedHashMap();
				for(SLinkedHashMap eb_t8413_SR : sMapperI.selectList("eb_t8413_prev_SL", eb_t8430_SR)) {
					hash.put(eb_t8413_SR.getString("date"), eb_t8413_SR.getString("hash"));
				}
				
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
					
					if(hash.isEmpty(date)) {
						eb_t8413_IR += sMapperI.insert("eb_t8413_IR", query);
					} else {
						eb_t8413_UR += sMapperI.update("eb_t8413_UR", query);
					}
					
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
	public synchronized String t1903_prev(
			String jobGroup
			, String jobName
			) {
		
		
		log.info("run");
		
		if(SDate.getDateInteger("HHmm") > 500 && SDate.getDateInteger("HHmm") < 910) {
			return "";
		}
		
		if(SDate.getDateInteger("HHmm") > 1800 && SDate.getDateInteger("HHmm") < 2350) {
			return "";
		}
		
		String errorCode = "E999";
		String message = "";
		String schdNo = "";
		
		new File("var/t1903").mkdirs();
		
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
		SLinkedHashMap hash = null;
		int eb_t8413_IGNORE = 0;
		int eb_t8413_UR = 0;
		int eb_t8413_IR = 0;
		try {
			
			jobHistMap.put("schd_no", UUID.randomUUID().toString());
			jobHistMap.put("job_data", jobHistMap.toJsonString());
			
			log.info(
					"{}.{}.{} ((START))"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					);
			
			int loopTry = 0;
			String shcode = "";
			HttpResponse<byte[]> httpResponse = null;
			List<SLinkedHashMap> t8413_SL = null;
			for(SLinkedHashMap eb_t8430_SR : sMapperI.selectList("eb_t8430_prev_SL")) {
				
				if(SDate.getDateInteger("HHmm") > 500 && SDate.getDateInteger("HHmm") < 900) {
					break;
				}
				
				if(SDate.getDateInteger("HHmm") > 1800 && SDate.getDateInteger("HHmm") < 2350) {
					break;
				}
				
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
				if(new File("var/t1903/" + shcode).exists()) {
					continue;
				}
				log.info(
						"{}.{}.{}.shcode={}"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, shcode
						);
				
				
				httpResponse = Unirest
						.post(sProperties.getJob().getProperty("seung.job.ebest.t1903.url", ""))
						.connectTimeout(1000 * 3)
						.socketTimeout(1000 * 60 * 5)
						.header("Content-Type", "application/json")
						.header(sProperties.getJob().getProperty("seung.job.kiwoom.api.key.name", ""), sProperties.getJob().getProperty("seung.job.kiwoom.api.key.value", ""))
						.body(new SLinkedHashMap()
								.add("request_code", schdNo)
								.add("date", SDate.getDateString("yyyyMMdd"))
//								.add("date", "20060101")
								.add("shcode", shcode)
								.add("repeat20", 190)
								.toJsonString()
//								.add("request_code", schdNo)
//								.add("gubun", "2")
//								.add("repeat500", 10)
//								.add("qrycnt", 500)
////								.add("edate", eb_t8430_SR.getString("trdd_min"))
//								.add("edate", "20191220")
//								.add("shcode", shcode)
//								.toJsonString()
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
					Thread.sleep(1000 * 3);
					continue;
				}
				
				responseText = new String(httpResponse.getBody(), "UTF-8");
				if(responseText != null) {
					FileUtils.writeStringToFile(new File("var/t1903/" + shcode), responseText, "UTF-8");
					Thread.sleep(1000 * 60 * 10);
					continue;
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
			log.info(
					"{}.{}.{} ((END))"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					);
		}
		
		return errorCode;
	}
	
	@Async
	@SuppressWarnings("unchecked")
	public void t8413_prev2(
			String thread
			) {
		
		File dirSrc = new File("var/t8413-20200904");
		File dirSuccess = new File("var/t8413-20200904/" + thread + "/success");
		File dirFail = new File("var/t8413-20200904/" + thread + "/fail");
		if(!dirSuccess.exists()) {
			dirSuccess.mkdirs();
		}
		if(!dirFail.exists()) {
			dirFail.mkdirs();
		}
		
		File work = null;
		File done = null;
		SLinkedHashMap data = null;
		SLinkedHashMap query = null;
		SLinkedHashMap hash = null;
		List<SLinkedHashMap> t8413_SL = null;
		String fileName = "";
		String shcode = "";
		String date = "";
		for(File src : dirSrc.listFiles()) {
			
			try {
				
				if(src.isDirectory()) {
					continue;
				}
				
				if(!src.exists()) {
					continue;
				}
				
				fileName = src.getName();
				log.info("thread.{}.{}=START", thread, fileName);
				
				work = new File("var/t8413-20200904/" + thread + "/" + src.getName());
				if(work.exists()) {
					work.delete();
				}
				FileUtils.moveFile(src, work);
				
				data = new SLinkedHashMap(FileUtils.readFileToString(work, "UTF-8"));
				shcode = data.getSLinkedHashMap("request").getString("shcode");
				query = new SLinkedHashMap()
						.add("shcode", shcode)
						;
				
				hash = new SLinkedHashMap();
				for(SLinkedHashMap eb_t8413_SR : sMapperI.selectList("eb_t8413_prev_SL", query)) {
					hash.put(eb_t8413_SR.getString("date"), eb_t8413_SR.getString("hash"));
				}
				
				t8413_SL = data.getSLinkedHashMap("response").getListSLinkedHashMap("t8413_SL");
				if(t8413_SL == null) {
					continue;
				}
				if(t8413_SL.isEmpty()) {
					continue;
				}
				
				for(SLinkedHashMap item : t8413_SL) {
					
					date = item.getString("date", "");
					
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
						continue;
					}
					
					if(hash.isEmpty(date)) {
						if(0 == sMapperI.update("eb_t8413_UR", query)) {
							sMapperI.insert("eb_t8413_IR", query);
						}
					} else {
						sMapperI.update("eb_t8413_UR", query);
					}
					
				}
				
				done = new File("var/t8413-20200904/" + thread + "/success/" + src.getName());
				log.info("thread.{}.{}=SUCCESS", thread, fileName);
				
			} catch (Exception e) {
				done = new File("var/t8413-20200904/" + thread + "/fail/" + src.getName());
				log.error("thread.{}.{}=FAIL", thread, fileName, e);
			}
			try {
				if(done.exists()) {
					done.delete();
				}
				FileUtils.moveFile(work, done);
			} catch (IOException e) {
			}
			
		}
		
	}
	
	@Async
	@SuppressWarnings("unchecked")
	public void t1903_prev2(
			String thread
			) {
		
		File dirSrc = new File("var/t1903-20200904");
		File dirSuccess = new File("var/t1903-20200904/" + thread + "/success");
		File dirFail = new File("var/t1903-20200904/" + thread + "/fail");
		if(!dirSuccess.exists()) {
			dirSuccess.mkdirs();
		}
		if(!dirFail.exists()) {
			dirFail.mkdirs();
		}
		
		File work = null;
		File done = null;
		SLinkedHashMap data = null;
		SLinkedHashMap query = null;
		SLinkedHashMap hash = null;
		List<SLinkedHashMap> t1903_SL = null;
		String fileName = "";
		String shcode = "";
		String date = "";
		for(File src : dirSrc.listFiles()) {
			
			try {
				
				if(src.isDirectory()) {
					continue;
				}
				
				if(!src.exists()) {
					continue;
				}
				
				fileName = src.getName();
				log.info("thread.{}.{}=START", thread, fileName);
				
				work = new File("var/t1903-20200904/" + thread + "/" + src.getName());
				if(work.exists()) {
					work.delete();
				}
				FileUtils.moveFile(src, work);
				
				data = new SLinkedHashMap(FileUtils.readFileToString(work, "UTF-8"));
				shcode = data.getSLinkedHashMap("request").getString("shcode");
				query = new SLinkedHashMap()
						.add("shcode", shcode)
						;
				
				hash = new SLinkedHashMap();
				for(SLinkedHashMap eb_t1903_SR : sMapperI.selectList("eb_t1903_prev_SL", query)) {
					hash.put(eb_t1903_SR.getString("date"), eb_t1903_SR.getString("hash"));
				}
				
				t1903_SL = data.getSLinkedHashMap("response").getListSLinkedHashMap("t1903_SL");
				if(t1903_SL == null) {
					continue;
				}
				if(t1903_SL.isEmpty()) {
					continue;
				}
				
				for(SLinkedHashMap item : t1903_SL) {
					
					date = item.getString("date", "");
					
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
						continue;
					}
					
					if(hash.isEmpty(date)) {
						if(0 == sMapperI.update("eb_t1903_UR", query)) {
							sMapperI.insert("eb_t1903_IR", query);
						}
					} else {
						sMapperI.update("eb_t1903_UR", query);
					}
					
				}
				
				done = new File("var/t1903-20200904/" + thread + "/success/" + src.getName());
				log.info("thread.{}.{}=SUCCESS", thread, fileName);
				
			} catch (Exception e) {
				done = new File("var/t1903-20200904/" + thread + "/fail/" + src.getName());
				log.error("thread.{}.{}=FAIL", thread, fileName, e);
			}
			try {
				if(done.exists()) {
					done.delete();
				}
				FileUtils.moveFile(work, done);
			} catch (IOException e) {
			}
			
		}
		
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
						.connectTimeout(1000 * 3)
						.socketTimeout(1000 * 30)
						.header("Content-Type", "application/json")
						.header(sProperties.getJob().getProperty("seung.job.kiwoom.api.key.name", ""), sProperties.getJob().getProperty("seung.job.kiwoom.api.key.value", ""))
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
					"eb_t8430_IGNORE=%d, eb_t8430_IR=%d, eb_t8430_UR=%d"
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
	
}
