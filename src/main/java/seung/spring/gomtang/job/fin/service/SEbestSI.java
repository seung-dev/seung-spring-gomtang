package seung.spring.gomtang.job.fin.service;

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
				.add("schd_code", "tr40005")
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
				.add("schd_code", "tr40005")
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
					continue;
				}
				
				for(SLinkedHashMap eb_t8413_SR : sMapperI.selectList("eb_t8413_SL", eb_t8430_SR)) {
					hash.put(eb_t8413_SR.getString("date"), eb_t8413_SR.getString("hash"));
				}
				
				responseText = new String(httpResponse.getBody(), "UTF-8");
				response = new SLinkedHashMap(responseText);
				
				String date = "";
				for(SLinkedHashMap item : response.getSLinkedHashMap("response").getListSLinkedHashMap("t8413_SL")) {
					
					date = item.getString("date");
					
					query = new SLinkedHashMap();
					query.put("shcode", shcode);
					query.put("date", item.getString("date"));
					query.put("date_inst", item.getString("date_inst"));
					query.put("date_updt", item.getString("date_updt"));
					query.put("hash", item.getString("hash"));
					query.put("open", item.getLong("open"));
					query.put("high", item.getLong("high"));
					query.put("low", item.getLong("low"));
					query.put("close", item.getLong("close"));
					query.put("jdiff_vol", item.getLong("jdiff_vol"));
					query.put("value", item.getLong("value"));
					query.put("jongchk", item.getLong("jongchk"));
					query.put("rate", item.getDouble("rate"));
					query.put("pricechk", item.getLong("pricechk"));
					query.put("ratevalue", item.getLong("ratevalue"));
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
				.add("schd_code", "tr40005")
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
					continue;
				}
				
				for(SLinkedHashMap eb_t8413_SR : sMapperI.selectList("eb_t8413_SL", eb_t8430_SR)) {
					hash.put(eb_t8413_SR.getString("date"), eb_t8413_SR.getString("hash"));
				}
				
				responseText = new String(httpResponse.getBody(), "UTF-8");
				response = new SLinkedHashMap(responseText);
				
				String date = "";
				for(SLinkedHashMap item : response.getSLinkedHashMap("response").getListSLinkedHashMap("t1903_SL")) {
					
					date = item.getString("date");
					
					query = new SLinkedHashMap();
					query.put("shcode", shcode);
					query.put("date", item.getString("date"));
					query.put("price", item.getString("price"));
					query.put("sign", item.getString("sign"));
					query.put("change", item.getString("change"));
					query.put("volume", item.getString("volume"));
					query.put("navdifff", item.getString("navdifff"));
					query.put("nav", item.getString("nav"));
					query.put("navchange", item.getString("navchange"));
					query.put("crate", item.getString("crate"));
					query.put("grate", item.getString("grate"));
					query.put("jisu", item.getString("jisu"));
					query.put("jichange", item.getString("jichange"));
					query.put("jirate", item.getString("jirate"));
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
	
}
