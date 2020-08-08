package seung.spring.gomtang.job.fin.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.SConvert;
import seung.java.kimchi.util.SCode;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.spring.boot.conf.SProperties;
import seung.spring.boot.conf.datasource.SMapperI;
import seung.spring.gomtang.util.SGomtangException;

@Slf4j
@Service("sNaverS")
public class SNaverSI {

	@Resource(name = "sProperties")
	private SProperties sProperties;
	
	@Resource(name = "sMapperI")
	private SMapperI sMapperI;
	
	@SuppressWarnings("unchecked")
	public synchronized String n0101(
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
				.add("schd_code", "n0101")
				.add("error_code", errorCode)
				.add("message", message)
				;
		
		SLinkedHashMap query = null;
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
					.post(sProperties.getJob().getProperty("seung.job.naver.n0101.url", ""))
					.connectTimeout(1000 * 3)
					.socketTimeout(1000 * 10)
					.field("request_code", schdNo)
					.asBytes()
					;
			
			if(HttpStatus.OK.value() != httpResponse.getStatus()) {
				message = String.format(
						"%s.%s.%s.response_code %d"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, httpResponse.getStatus()
						);
				log.error(message);
				throw new SGomtangException(message);
			}
			
			SLinkedHashMap response = new SLinkedHashMap(new String(httpResponse.getBody(), "UTF-8"));
			
			if(!SCode.SUCCESS.equals(response.getString("error_code", ""))) {
				message = String.format(
						"%s.%s.%s.error_code %s"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, response.getString("error_code", "")
						);
				log.error(message);
				throw new SGomtangException(message);
			}
			
			List<SLinkedHashMap> n0101 = response.getSLinkedHashMap("result").getListSLinkedHashMap("n0101");
			
			int loopTry = 0;
			SLinkedHashMap n0101_SR = null;
			int n0101_IR = 0;
			int n0101_UR = 0;
			int n0101_DO_NOTHING = 0;
			int n0101_IGNORE = 0;
			for(SLinkedHashMap item : n0101) {
				
				if(loopTry++ % 100 == 0) {
					log.info(
							"{}.{}.{}.try {}"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							, loopTry
							);
				}
				
				query = new SLinkedHashMap()
						.add("item_code", item.getString("item_code", ""))
						.add("item_name", item.getString("item_name", ""))
						.add("etf_type", item.getString("etf_type", ""))
						;
				query.put("hash", SConvert.digestToHex("MD5", query.toJsonString()));
				
				n0101_SR = sMapperI.selectOne("n0101_SR", query);
				
				if(n0101_SR == null) {
					n0101_IR += sMapperI.insert("n0101_IR", query);
				} else if(query.getString("hash", "").equals(n0101_SR.getString("hash", "1"))) {
					n0101_DO_NOTHING++;
				} else if(!query.getString("hash", "").equals(n0101_SR.getString("hash", "1"))) {
					n0101_UR += sMapperI.insert("n0101_IR", query);
				} else {
					n0101_IGNORE++;
				}
				
			}
			
			message = String.format(
					"n0101_IR=%d, n0101_UR=%d, n0101_DO_NOTHING=%d, n0101_IGNORE=%d"
					, n0101_IR
					, n0101_UR
					, n0101_DO_NOTHING
					, n0101_IGNORE
					);
			errorCode = "0000";
			
		} catch (Exception e) {
			log.error(
					"{}.{}.{}.exception {}"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					, jobHistMap.getString("exception", "" + e)
					, e
					);
			if(query != null) {
				log.error(
						"{}.{}.{}.query {}"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, query.toJsonString(true)
						);
			}
			message = ExceptionUtils.getStackTrace(e);
		} finally {
			jobHistMap.put("error_code", errorCode);
			jobHistMap.put("message", message);
			log.info(
					"{}.{}.{}.error_code {}"
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
	public synchronized String n0102(
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
				.add("schd_code", "n0102")
				.add("error_code", errorCode)
				.add("message", message)
				;
		
		String itemCode = "";
		SLinkedHashMap cuLog = null;
		SLinkedHashMap query = null;
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
			HttpResponse<byte[]> httpResponse = null;
			SLinkedHashMap response = null;
			SLinkedHashMap n0102 = null;
			SLinkedHashMap n0102_SR = null;
			int n0102_IR = 0;
			int n0102_UR = 0;
			int n0102_DO_NOTHING = 0;
			int n0102_IGNORE = 0;
			List<SLinkedHashMap> n0104_SL = null;
			int n0104_IR = 0;
			int n0104_UR = 0;
			int n0104_DO_NOTHING = 0;
			int n0104_IGNORE = 0;
			boolean doInsert = false;
			for(SLinkedHashMap kw10000_SR : sMapperI.selectList("kw10000_SL", new SLinkedHashMap().add("mrkt_type", "3").add("on_prgr", "1"))) {
				
				if(loopTry++ % 100 == 0) {
					log.info(
							"{}.{}.{}.try {}"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							, loopTry
							);
				}
				
				itemCode = kw10000_SR.getString("item_code");
				
				httpResponse = Unirest
						.post(sProperties.getJob().getProperty("seung.job.naver.n0102.url", ""))
						.connectTimeout(1000 * 3)
						.socketTimeout(1000 * 10)
						.field("request_code", schdNo)
						.field("item_code", itemCode)
						.asBytes()
						;
				
				if(HttpStatus.OK.value() != httpResponse.getStatus()) {
					message = String.format(
							"%s.%s.%s.response_code %d"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							, httpResponse.getStatus()
							);
					log.error(message);
					throw new SGomtangException(message);
				}
				
				response = new SLinkedHashMap(new String(httpResponse.getBody(), "UTF-8"));
				
				if(!SCode.SUCCESS.equals(response.getString("error_code", ""))) {
					message = String.format(
							"%s.%s.%s.error_code %s"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							, response.getString("error_code", "")
							);
					log.error(message);
					throw new SGomtangException(message);
				}
				
				n0102 = response.getSLinkedHashMap("result").getSLinkedHashMap("n0102");
				query = new SLinkedHashMap();
				query.put("item_code", kw10000_SR.getString("item_code", ""));
				query.put("shar_oust", n0102.getString("shar_oust", ""));
				query.put("indx_name", n0102.getString("indx_name", ""));
				query.put("date_set", n0102.getString("date_set", ""));
				query.put("date_list", n0102.getString("date_list", ""));
				query.put("asst_clss", n0102.getString("asst_clss", ""));
				query.put("expn_rate", n0102.getString("expn_rate", ""));
				query.put("acct_perd", n0102.getString("acct_perd", ""));
				query.put("date_dstb", n0102.getString("date_dstb", ""));
				query.put("issr", n0102.getString("issr", ""));
				query.put("issr_url", n0102.getString("issr_url", ""));
				query.put("item_dscr", n0102.getString("item_dscr", ""));
				query.put("hash", SConvert.digestToHex("MD5", query.toJsonString()));
				
				n0102_SR = sMapperI.selectOne("n0102_SR", query);
				if(n0102_SR == null) {
					n0102_IR += sMapperI.insert("n0102_IR", query);
				} else if(query.getString("hash", "").equals(n0102_SR.getString("hash", "1"))) {
					n0102_DO_NOTHING++;
				} else if(!query.getString("hash", "").equals(n0102_SR.getString("hash", "1"))) {
					n0102_UR += sMapperI.insert("n0102_UR", query);
				} else {
					n0102_IGNORE++;
				}
				
				n0104_SL = sMapperI.selectList("n0104_SL", query);
				for(SLinkedHashMap cu : n0102.getListSLinkedHashMap("cu")) {
					
					cuLog = cu;
					query = new SLinkedHashMap();
					query.put("item_code", kw10000_SR.getString("item_code", ""));
					query.put("item_name_cu", cu.getString("item_name_kr", ""));
					query.put("asst_wght", cu.isEmpty("asst_wght") ? 0 : cu.getDouble("asst_wght"));
					query.put("hash", SConvert.digestToHex("MD5", query.toJsonString()));
					
					doInsert = true;
					for(SLinkedHashMap n0104_SR : n0104_SL) {
						if(
								n0104_SR.getString("item_code").equals(query.getString("item_code"))
								&& n0104_SR.getString("item_name_cu").equals(query.getString("item_name_cu"))
								) {
							if(n0104_SR.getString("hash").equals(query.getString("hash"))) {
								n0104_DO_NOTHING++;
							} else {
								n0104_UR += sMapperI.insert("n0104_UR", query);
							}
							doInsert = false;
							break;
						}
					}
					
					if(doInsert) {
						n0104_IR += sMapperI.insert("n0104_IR", query);
					}
					
				}// end of cu
				
				Thread.sleep(200);
				
			}
			
			message = String.format(
					"%s%s"
					, String.format(
							"n0102_IR=%d, n0102_UR=%d, n0102_DO_NOTHING=%d, n0102_IGNORE=%d"
							, n0102_IR
							, n0102_UR
							, n0102_DO_NOTHING
							, n0102_IGNORE
							)
					, String.format(
							", n0104_IR=%d, n0104_UR=%d, n0104_DO_NOTHING=%d, n0104_IGNORE=%d"
							, n0104_IR
							, n0104_UR
							, n0104_DO_NOTHING
							, n0104_IGNORE
							)
					);
			errorCode = "0000";
			
		} catch (Exception e) {
			log.error(
					"{}.{}.{}.exception {}"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					, jobHistMap.getString("exception", "" + e)
					, e
					);
			log.error(
					"{}.{}.{}.item_code={}"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					, itemCode
					);
			if(cuLog != null) {
				log.error(
						"{}.{}.{}.cu {}"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, cuLog.toJsonString(true)
						);
			}
			if(query != null) {
				log.error(
						"{}.{}.{}.query {}"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, query.toJsonString(true)
						);
			}
			message = ExceptionUtils.getStackTrace(e);
		} finally {
			jobHistMap.put("error_code", errorCode);
			jobHistMap.put("message", message);
			log.info(
					"{}.{}.{}.error_code {}"
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
	public synchronized String n0104(
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
				.add("schd_code", "n0104")
				.add("error_code", errorCode)
				.add("message", message)
				;
		
		String itemCode = "";
		SLinkedHashMap query = null;
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
			HttpResponse<byte[]> httpResponse = null;
			SLinkedHashMap response = null;
			int n0104_IR = 0;
			int n0104_UR = 0;
			int n0104_DO_NOTHING = 0;
			int n0104_IGNORE = 0;
			SLinkedHashMap n0104_SR = null;
			for(SLinkedHashMap item : sMapperI.selectList("n0104_SL")) {
				
				if(loopTry++ % 100 == 0) {
					log.info(
							"{}.{}.{}.try {}"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							, loopTry
							);
				}
				
				itemCode = item.getString("item_code");
				
				httpResponse = Unirest
						.post(sProperties.getJob().getProperty("seung.job.naver.n0104.url", ""))
						.connectTimeout(1000 * 3)
						.socketTimeout(1000 * 10)
						.field("request_code", schdNo)
						.field("item_code", itemCode)
						.asBytes()
						;
				
				if(HttpStatus.OK.value() != httpResponse.getStatus()) {
					message = String.format(
							"%s.%s.%s.response_code %d"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							, httpResponse.getStatus()
							);
					continue;
				}
				
				response = new SLinkedHashMap(new String(httpResponse.getBody(), "UTF-8"));
				
				if("D006".equals(response.getString("error_code", ""))) {
					log.info("D006: {}", itemCode);
				}
				
				if(!SCode.SUCCESS.equals(response.getString("error_code", ""))) {
					message = String.format(
							"%s.%s.%s.error_code %s"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							, response.getString("error_code", "")
							);
					continue;
				}
				
				for(SLinkedHashMap n0104 : response.getSLinkedHashMap("result").getListSLinkedHashMap("n0104")) {
					
					if(n0104.isEmpty("item_ta")
							|| n0104.isEmpty("item_tl")
							|| n0104.isEmpty("item_te")
							|| n0104.isEmpty("item_tr")
							|| n0104.isEmpty("item_oi")
							|| n0104.isEmpty("item_cfo")
							|| n0104.isEmpty("item_de")
							) {
						continue;
					}
					
					query = new SLinkedHashMap();
					query.put("item_code", itemCode);
					query.put("item_sd", n0104.getString("item_sd", ""));
					query.put("is_est", n0104.getInt("is_est"));
					query.put("item_ta", n0104.getBigDecimal("item_ta"));
					query.put("item_tl", n0104.getBigDecimal("item_tl"));
					query.put("item_te", n0104.getBigDecimal("item_te"));
					query.put("item_tr", n0104.getBigDecimal("item_tr"));
					query.put("item_oi", n0104.getBigDecimal("item_oi"));
					query.put("item_cfo", n0104.getBigDecimal("item_cfo"));
					query.put("item_de", n0104.getBigDecimal("item_de"));
					query.put("hash", SConvert.digestToHex("MD5", query.toJsonString()));
					
					n0104_SR = sMapperI.selectOne("n0104_SR", query);
					
					if(n0104_SR == null) {
						n0104_IR += sMapperI.insert("n0104_IR", query);
					} else if(query.getString("hash", "").equals(n0104_SR.getString("hash", "1"))) {
						n0104_IGNORE++;
					} else if(!query.getString("hash", "").equals(n0104_SR.getString("hash", "1"))) {
						n0104_UR += sMapperI.insert("n0104_UR", query);
					}
					
				}
				
				Thread.sleep(200);
				
			}
			
			message = String.format(
					"%s%s"
					, String.format(
							"n0104_IR=%d, n0104_UR=%d, n0104_DO_NOTHING=%d, n0104_IGNORE=%d"
							, n0104_IR
							, n0104_UR
							, n0104_DO_NOTHING
							, n0104_IGNORE
							)
					, String.format(
							", n0104_IR=%d, n0104_UR=%d, n0104_DO_NOTHING=%d, n0104_IGNORE=%d"
							, n0104_IR
							, n0104_UR
							, n0104_DO_NOTHING
							, n0104_IGNORE
							)
					);
			errorCode = "0000";
			
		} catch (Exception e) {
			log.error(
					"{}.{}.{}.exception {}"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					, jobHistMap.getString("exception", "" + e)
					, e
					);
			log.error(
					"{}.{}.{}.item_code={}"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					, itemCode
					);
			if(query != null) {
				log.error(
						"{}.{}.{}.query {}"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, query.toJsonString(true)
						);
			}
			message = ExceptionUtils.getStackTrace(e);
		} finally {
			jobHistMap.put("error_code", errorCode);
			jobHistMap.put("message", message);
			log.info(
					"{}.{}.{}.error_code {}"
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
