package seung.spring.gomtang.job.fin.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.SConvert;
import seung.java.kimchi.SDate;
import seung.java.kimchi.util.SCode;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.spring.boot.conf.SProperties;
import seung.spring.boot.conf.datasource.SMapperI;
import seung.spring.gomtang.util.SGomtangException;

@Slf4j
@Service("sKiwoomS")
public class SKiwoomSI {

	@Resource(name = "sProperties")
	private SProperties sProperties;
	
	@Resource(name = "sMapperI")
	private SMapperI sMapperI;
	
	@SuppressWarnings("unchecked")
	public synchronized String sr33333(
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
				.add("schd_code", "sr33333")
				.add("error_code", errorCode)
				.add("message", message)
				;
		
		String sr33333 = "";
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
					.get(sProperties.getJob().getProperty("seung.job.kiwoom.sr33333.url", ""))
					.connectTimeout(1000 * 3)
					.socketTimeout(1000 * 60 * 10)
					.header(sProperties.getJob().getProperty("seung.job.kiwoom.api.key.name", ""), sProperties.getJob().getProperty("seung.job.kiwoom.api.key.value", ""))
					.asBytes()
					;
			
			sr33333 = new String(httpResponse.getBody(), "UTF-8");
			
			message = String.format(
					"sr33333=%s"
					, sr33333
					);
			
			if(sr33333.contains("1111")) {
				errorCode = "0000";
			} else {
				log.error(
						"{}.{}.{}.{}={}"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, "sr33333"
						, sr33333
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
			
			Unirest
				.get(sProperties.getJob().getProperty("seung.job.kiwoom.kw00000.url", ""))
				.connectTimeout(1000 * 3)
				.socketTimeout(1000 * 3)
				.header(sProperties.getJob().getProperty("seung.job.kiwoom.api.key.name", ""), sProperties.getJob().getProperty("seung.job.kiwoom.api.key.value", ""))
				.asBytes()
				;
			
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
	public String kw00000(
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
				.add("schd_code", "kw00000")
				.add("error_code", errorCode)
				.add("message", message)
				;
		
		String kw00000 = "";
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
					.get(sProperties.getJob().getProperty("seung.job.kiwoom.kw00000.url", ""))
					.connectTimeout(1000 * 3)
					.socketTimeout(1000 * 3)
					.header(sProperties.getJob().getProperty("seung.job.kiwoom.api.key.name", ""), sProperties.getJob().getProperty("seung.job.kiwoom.api.key.value", ""))
					.asBytes()
					;
			
			kw00000 = new String(httpResponse.getBody(), "UTF-8");
			
			message = String.format(
					"kw00000=%s"
					, kw00000
					);
			
			if(kw00000.contains("1")) {
				errorCode = "0000";
			} else {
				log.error(
						"{}.{}.{}.{}={}"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, "kw00000"
						, kw00000
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
	public synchronized String kw10000(
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
				.add("schd_code", "kw10000")
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
			
			sMapperI.update("kw10000_UL");
			
			String market_type = "";
			List<SLinkedHashMap> preItems = null;
			HttpResponse<byte[]> httpResponse = null;
			SLinkedHashMap response = null;
			List<SLinkedHashMap> comm = null;
			List<SLinkedHashMap> result = null;
			int loopTry = 0;
			boolean isUpdate = false;
			int kw10000_IR = 0;
			int kw10000_UR = 0;
			for(SLinkedHashMap market : sMapperI.selectList("api_optn_SL", new SLinkedHashMap().add("optn_set", "kw10000.market_type"))) {
				
				market_type = market.getString("optn_code");
				log.info(
						"{}.{}.{}.{}={}"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, "market_type"
						, market_type
						);
				preItems = sMapperI.selectList("kw10000_SL", new SLinkedHashMap().add("mrkt_type", market_type));
				log.info(
						"{}.{}.{}.{}={}"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, "preItems"
						, preItems.size()
						);
				
				httpResponse = Unirest
						.post(sProperties.getJob().getProperty("seung.job.kiwoom.kw10000.url", ""))
						.connectTimeout(1000 * 3)
						.socketTimeout(1000 * 30)
						.header(sProperties.getJob().getProperty("seung.job.kiwoom.api.key.name", ""), sProperties.getJob().getProperty("seung.job.kiwoom.api.key.value", ""))
						.header("Content-Type", "application/json")
						.body(new SLinkedHashMap().add("market_type", market_type).toJsonString())
						.asBytes()
						;
				
				if(HttpStatus.OK.value() != httpResponse.getStatus()) {
					message = String.format(
							"%s.%s.%s.status=%d"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							, httpResponse.getStatus()
							);
					log.error(message);
					throw new SGomtangException(message);
				}
				
				response = new SLinkedHashMap(new String(httpResponse.getBody(), "UTF-8"));
				
				comm = response.getListSLinkedHashMap("comm");
				if(comm.isEmpty()) {
					message = String.format(
							"%s.%s.%s.comm=empty"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							);
					log.error(message);
					throw new SGomtangException(message);
				}
				
				if(!SCode.SUCCESS.equals(comm.get(0).getString("error_code", ""))) {
					message = String.format(
							"%s.%s.%s.error_code=%s"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							, comm.get(0).getString("error_code", "")
							);
					log.error(message);
					throw new SGomtangException(message);
				}
				
				result = response.getSLinkedHashMap("result").getListSLinkedHashMap(market.getString("optn_name_en").toLowerCase());
				
				for(SLinkedHashMap item : result) {
					
					if(loopTry++ % 100 == 0) {
						log.info(
								"{}.{}.{}.try={}"
								, jobHistMap.getString("schd_set", "")
								, jobHistMap.getString("schd_code", "")
								, jobHistMap.getString("schd_no", "")
								, loopTry
								);
					}
					
					query = new SLinkedHashMap();
					query.put("item_code", item.getString("item_code", ""));
					query.put("mrkt_type", market_type);
					query.put("on_prgr", "1");
					query.put("item_name", item.getString("item_name", ""));
					query.put("item_cnst", item.getString("cnst", ""));
					query.put("item_stts", item.getString("stts", ""));
					query.put("hash", SConvert.digestToHex("MD5", query.toJsonString()));
					
					isUpdate = false;
					for(SLinkedHashMap preItem : preItems) {
						if(preItem.getString("item_code").equals(query.getString("item_code", ""))) {
							isUpdate = true;
							break;
						}
					}
					
					if(isUpdate) {
						kw10000_UR += sMapperI.update("kw10000_UR", query);
					} else {
						kw10000_IR += sMapperI.insert("kw10000_IR", query);
					}
					
				}// end of etf
				
			}// end of market
			
			message = String.format(
					"kw10000_IR=%d, kw10000_UR=%d"
					, kw10000_IR
					, kw10000_UR
					);
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
	public synchronized String tr10001(
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
				.add("schd_code", "tr10001")
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
			List<SLinkedHashMap> comm = null;
			List<SLinkedHashMap> data = null;
			String itemName = "";
			SLinkedHashMap tr10001 = null;
			int tr10001_IR = 0;
			int tr10001_UR = 0;
			int tr10001_DO_NOTHING = 0;
			int tr10001_IGNORE = 0;
			for(SLinkedHashMap tr10001_SR : sMapperI.selectList("tr10001_SL", new SLinkedHashMap().add("mrkt_type", "3").add("on_prgr", "1"))) {
				
				if(loopTry++ % 100 == 0) {
					log.info(
							"{}.{}.{}.try={}"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, jobHistMap.getString("schd_no", "")
							, loopTry
							);
				}
				
				httpResponse = Unirest
						.post(sProperties.getJob().getProperty("seung.job.kiwoom.tr10001.url", ""))
						.connectTimeout(1000 * 3)
						.socketTimeout(1000 * 3)
						.header("Content-Type", "application/json")
						.header(sProperties.getJob().getProperty("seung.job.kiwoom.api.key.name", ""), sProperties.getJob().getProperty("seung.job.kiwoom.api.key.value", ""))
						.body(new SLinkedHashMap().add("item_code", tr10001_SR.getString("item_code")).toJsonString())
						.asBytes()
						;
				
				if(HttpStatus.OK.value() != httpResponse.getStatus()) {
					message = String.format(
							"%s.%s.%s.status=%d"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							, httpResponse.getStatus()
							);
					log.error(message);
					throw new SGomtangException(message);
				}
				
				response = new SLinkedHashMap(new String(httpResponse.getBody(), "UTF-8"));
				
				comm = response.getListSLinkedHashMap("comm");
				if(comm.isEmpty()) {
					message = String.format(
							"%s.%s.%s.comm=empty"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							);
					log.error(message);
					throw new SGomtangException(message);
				}
				if(!SCode.SUCCESS.equals(comm.get(0).getString("error_code", ""))) {
					message = String.format(
							"%s.%s.%s.error_code=%s"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							, comm.get(0).getString("error_code", "")
							);
					log.error(message);
					throw new SGomtangException(message);
				}
				
				itemName = "";
				itemCode = "";
				data = response.getListSLinkedHashMap("data");
				if(data.isEmpty()) {
					message = String.format(
							"%s.%s.%s.data=empty"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							);
					log.error(message);
					throw new SGomtangException(message);
				}
				itemName = data.get(0).getString("item_name", "");
				itemCode = data.get(0).getString("item_code", "");
				
				tr10001 = response.getSLinkedHashMap("result").getListSLinkedHashMap("tr10001").get(0);
				
				query = new SLinkedHashMap();
				query.put("item_code", itemCode);
				query.put("item_name", itemName);
				query.put("etf_fm", tr10001.getString("fm", ""));
				query.put("etf_fv", tr10001.getDouble("fv"));
				query.put("etf_equity", tr10001.getDouble("equity"));
				query.put("etf_is", tr10001.getBigInteger("is"));
				query.put("etf_cr", tr10001.getDouble("cr"));
				query.put("etf_yh", tr10001.getDouble("yh"));
				query.put("etf_yl", tr10001.getDouble("yl"));
				query.put("etf_mc", tr10001.getDouble("mc"));
				query.put("etf_mcr", tr10001.getString("mcr", ""));
				query.put("etf_for", tr10001.getDouble("for"));
				query.put("etf_sp", tr10001.getDouble("sp"));
				query.put("etf_250h", tr10001.getDouble("250h"));
				query.put("etf_250l", tr10001.getDouble("250l"));
				query.put("etf_op", tr10001.getDouble("op"));
				query.put("etf_hp", tr10001.getDouble("hp"));
				query.put("etf_lp", tr10001.getDouble("lp"));
				query.put("etf_hhp", tr10001.getDouble("hhp"));
				query.put("etf_llp", tr10001.getDouble("llp"));
				query.put("etf_bp", tr10001.getDouble("bp"));
				query.put("etf_ep", tr10001.getDouble("ep"));
				query.put("etf_eq", tr10001.getDouble("eq"));
				query.put("etf_d250h", tr10001.getString("d250h", ""));
				query.put("etf_vs250h", tr10001.getDouble("vs250h"));
				query.put("etf_d250l", tr10001.getString("d250l", ""));
				query.put("etf_vs250l", tr10001.getDouble("vs250l"));
				query.put("etf_pp", tr10001.getDouble("pp"));
				query.put("etf_pinc", tr10001.getDouble("pinc"));
				query.put("etf_pcp", tr10001.getDouble("pcp"));
				query.put("etf_vol", tr10001.getBigInteger("vol"));
				query.put("etf_pcv", tr10001.getDouble("pcv"));
				query.put("etf_fvu", tr10001.getString("fvu", ""));
				query.put("etf_os", tr10001.getString("os", ""));
				query.put("etf_osr", tr10001.getString("osr", ""));
				query.put("hash", SConvert.digestToHex("MD5", query.toJsonString()));
				
				if(tr10001_SR.isEmpty("hash")) {
					tr10001_IR += sMapperI.insert("tr10001_IR", query);
				} else if(query.getString("hash", "").equals(tr10001_SR.getString("hash", "1"))) {
					tr10001_DO_NOTHING++;
				} else if(!query.getString("hash", "").equals(tr10001_SR.getString("hash", "1"))) {
					tr10001_UR += sMapperI.insert("tr10001_UR", query);
				} else {
					tr10001_IGNORE++;
				}
				
				Thread.sleep(1000);
				
			}// end of kw10000_SL
			
			message = String.format(
					"tr10001_IR=%d, tr10001_UR=%d, tr10001_DO_NOTHING=%d, tr10001_IGNORE=%d"
					, tr10001_IR
					, tr10001_UR
					, tr10001_DO_NOTHING
					, tr10001_IGNORE
					);
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
			log.error(
					"{}.{}.{}.item_code={}"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					, itemCode
					);
			if(query != null) {
				log.error(
						"{}.{}.{}.query={}"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, query != null ? query.toJsonString(true) : ""
						);
			}
			message = ExceptionUtils.getStackTrace(e);
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
	public synchronized String tr10081(
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
				.add("schd_code", "tr10081")
				.add("error_code", errorCode)
				.add("message", message)
				;
		
		SLinkedHashMap query = null;
		try {
			
			schdNo = sMapperI.selectOne("schd_no").getString("schd_no", "");
			jobHistMap.put("schd_no", schdNo);
			jobHistMap.put("rnk", 3);
			jobHistMap.put("job_data", jobHistMap.toJsonString());
			
			log.info(
					"{}.{}.{} ((START))"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					);
			
			sMapperI.insert("schd_prev", jobHistMap);
			
			String itemCode = "";
			int loopTry = 0;
			int timesMax = 3;
			int timesTry = 0;
			HttpResponse<byte[]> httpResponse = null;
			SLinkedHashMap response = null;
			List<SLinkedHashMap> comm = null;
			List<SLinkedHashMap> tr10081 = null;
			int limit = 3;
			List<SLinkedHashMap> tr10081_SL = null;
			boolean isInsert = false;
			boolean isUpdate = false;
			boolean isContinue = false;
			boolean isBreak = false;
			int tr10081_IR = 0;
			int tr10081_UR = 0;
			int tr10081_DO_NOTHING = 0;
			int tr10081_IGNORE = 0;
			
			File logFile = null;
			String responseText = "";
			for(SLinkedHashMap kw10000_SR : sMapperI.selectList("kw10000_SL", new SLinkedHashMap().add("mrkt_type", "3").add("on_prgr", "1"))) {
				
				itemCode = kw10000_SR.getString("item_code");
				
				log.info(
						"{}.{}.{}.item_code={}"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, itemCode
						);
				
				if(loopTry++ % 100 == 0) {
					log.info(
							"{}.{}.{}.try={}"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							, loopTry
							);
				}
				
				timesTry = 0;
				while(timesTry++ < timesMax) {
					
					try {
						
						httpResponse = Unirest
								.post(sProperties.getJob().getProperty("seung.job.kiwoom.tr10081.url", ""))
								.connectTimeout(1000 * 3)
								.socketTimeout(1000 * 30)
								.header("Content-Type", "application/json")
								.header(sProperties.getJob().getProperty("seung.job.kiwoom.api.key.name", ""), sProperties.getJob().getProperty("seung.job.kiwoom.api.key.value", ""))
								.body(new SLinkedHashMap()
										.add("date", SDate.getDateString("yyyyMMdd"))
										.add("repeat_600", "1")
										.add("item_code", itemCode)
										.toJsonString()
										)
								.asBytes()
								;
						
						break;
						
					} catch (Exception e) {
						
						Unirest
							.get(sProperties.getJob().getProperty("seung.job.kiwoom.sr99999.url", ""))
							.connectTimeout(1000 * 3)
							.socketTimeout(1000 * 60 * 10)
							.header(sProperties.getJob().getProperty("seung.job.kiwoom.api.key.name", ""), sProperties.getJob().getProperty("seung.job.kiwoom.api.key.value", ""))
							.asBytes()
							;
						
						Thread.sleep(1000);
						
						Unirest
							.get(sProperties.getJob().getProperty("seung.job.kiwoom.sr66666.url", ""))
							.connectTimeout(1000 * 3)
							.socketTimeout(1000 * 60 * 10)
							.header(sProperties.getJob().getProperty("seung.job.kiwoom.api.key.name", ""), sProperties.getJob().getProperty("seung.job.kiwoom.api.key.value", ""))
							.asBytes()
							;
					}
					
				}
				
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
				
				logFile = new File(String.format("var/tr10081/%s.json", itemCode));
				logFile.delete();
				responseText = new String(httpResponse.getBody(), "UTF-8");
				FileUtils.write(logFile, responseText, "UTF-8");
				response = new SLinkedHashMap(responseText);
				
				comm = response.getListSLinkedHashMap("comm");
				if(comm.isEmpty()) {
					message = String.format(
							"%s.%s.%s.comm=empty"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							);
					log.error(message);
					throw new SGomtangException(message);
				}
				if(!SCode.SUCCESS.equals(comm.get(0).getString("error_code", ""))) {
					message = String.format(
							"%s.%s.%s.error_code=%s"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							, comm.get(0).getString("error_code", "")
							);
					log.error(message);
					throw new SGomtangException(message);
				}
				
				tr10081 = response.getSLinkedHashMap("result").getListSLinkedHashMap("tr10081");
				
				if(tr10081.isEmpty()) {
					message = String.format(
							"%s.%s.%s.tr10081=empty"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							);
					log.error(message);
					throw new SGomtangException(message);
				}
				
				kw10000_SR.put("limit", limit);
				tr10081_SL = sMapperI.selectList("tr10081_SL", kw10000_SR);
				for(SLinkedHashMap item : tr10081) {
					
					query = new SLinkedHashMap();
					query.put("item_code", itemCode);
					query.put("trdd", item.getString("date"));
					query.put("etf_cp", item.getDouble("cp"));
					query.put("etf_vol", item.getBigInteger("vol"));
					query.put("etf_lp", item.getDouble("lp"));
					query.put("etf_hp", item.getDouble("hp"));
					query.put("etf_op", item.getDouble("op"));
					query.put("hash", SConvert.digestToHex("MD5", query.toJsonString()));
					
					isInsert = true;
					isUpdate = false;
					isContinue = false;
					isBreak = false;
					for(SLinkedHashMap tr10081_SR : tr10081_SL) {
						if(tr10081_SR.getString("trdd").equals(query.getString("trdd"))) {
							if(tr10081_SR.getString("hash").equals(query.getString("hash"))) {
								isContinue = true;
							} else {
								isUpdate = true;
							}
							if(tr10081_SR.getInt("rn") == limit) {
								isBreak = true;
							}
							isInsert = false;
							break;
						}
					}
					
					if(isInsert) {
						tr10081_IR += sMapperI.insert("tr10081_IR", query);
						log.debug(
								"{}.{}.{}.{}.IR={}"
								, jobHistMap.getString("schd_set", "")
								, jobHistMap.getString("schd_code", "")
								, schdNo
								, itemCode
								, query.getString("trdd")
								);
					} else if(isContinue) {
						log.debug(
								"{}.{}.{}.{}.IGNORE={}"
								, jobHistMap.getString("schd_set", "")
								, jobHistMap.getString("schd_code", "")
								, schdNo
								, itemCode
								, query.getString("trdd")
								);
						tr10081_IGNORE++;
						if(isBreak) {
							break;
						} else {
							continue;
						}
					} else if(isUpdate) {
						log.debug(
								"{}.{}.{}.{}.UR={}"
								, jobHistMap.getString("schd_set", "")
								, jobHistMap.getString("schd_code", "")
								, schdNo
								, itemCode
								, query.getString("trdd")
								);
						tr10081_UR += sMapperI.insert("tr10081_UR", query);
					}
					
					if(isBreak) {
						break;
					}
					
				}// end of tr10081
				
				Thread.sleep(1000);
				
			}// end of tr10081_SL
			
			message = String.format(
					"tr10081_IR=%d, tr10081_UR=%d, tr10081_DO_NOTHING=%d, tr10081_IGNORE=%d"
					, tr10081_IR
					, tr10081_UR
					, tr10081_DO_NOTHING
					, tr10081_IGNORE
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
	public synchronized String tr40005(
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
		try {
			
			schdNo = sMapperI.selectOne("schd_no").getString("schd_no", "");
			jobHistMap.put("schd_no", schdNo);
			jobHistMap.put("rnk", 3);
			jobHistMap.put("job_data", jobHistMap.toJsonString());
			
			log.info(
					"{}.{}.{} ((START))"
					, jobHistMap.getString("schd_set", "")
					, jobHistMap.getString("schd_code", "")
					, schdNo
					);
			
			sMapperI.insert("schd_prev", jobHistMap);
			
			String itemCode = "";
			int loopTry = 0;
			int timesMax = 3;
			int timesTry = 0;
			HttpResponse<byte[]> httpResponse = null;
			SLinkedHashMap response = null;
			List<SLinkedHashMap> comm = null;
			List<SLinkedHashMap> tr40005 = null;
			int limit = 3;
			List<SLinkedHashMap> tr40005_SL = null;
			boolean isInsert = false;
			boolean isUpdate = false;
			boolean isContinue = false;
			boolean isBreak = false;
			int tr40005_IR = 0;
			int tr40005_UR = 0;
			int tr40005_DO_NOTHING = 0;
			int tr40005_IGNORE = 0;
			int prev_IR = 0;
			int post_IR = 0;
			File logFile = null;
			String responseText = "";
			for(SLinkedHashMap kw10000_SR : sMapperI.selectList("kw10000_SL", new SLinkedHashMap().add("mrkt_type", "3").add("on_prgr", "1"))) {
				
				itemCode = kw10000_SR.getString("item_code");
				
				log.info(
						"{}.{}.{}.item_code={}"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, itemCode
						);
				
				if(loopTry++ % 100 == 0) {
					log.info(
							"{}.{}.{}.try={}"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							, loopTry
							);
				}
				
				timesTry = 0;
				while(timesTry++ < timesMax) {
					
					try {
						
						httpResponse = Unirest
								.post(sProperties.getJob().getProperty("seung.job.kiwoom.tr40005.url", ""))
								.connectTimeout(1000 * 3)
								.socketTimeout(1000 * 30)
								.header("Content-Type", "application/json")
								.header(sProperties.getJob().getProperty("seung.job.kiwoom.api.key.name", ""), sProperties.getJob().getProperty("seung.job.kiwoom.api.key.value", ""))
								.body(new SLinkedHashMap()
										.add("repeat_30", "1")
										.add("item_code", itemCode)
										.toJsonString()
										)
								.asBytes()
								;
						
						break;
						
					} catch (Exception e) {
						
						Unirest
						.get(sProperties.getJob().getProperty("seung.job.kiwoom.sr99999.url", ""))
						.connectTimeout(1000 * 3)
						.socketTimeout(1000 * 60 * 10)
						.header(sProperties.getJob().getProperty("seung.job.kiwoom.api.key.name", ""), sProperties.getJob().getProperty("seung.job.kiwoom.api.key.value", ""))
						.asBytes()
						;
						
						Thread.sleep(1000);
						
						Unirest
						.get(sProperties.getJob().getProperty("seung.job.kiwoom.sr66666.url", ""))
						.connectTimeout(1000 * 3)
						.socketTimeout(1000 * 60 * 10)
						.header(sProperties.getJob().getProperty("seung.job.kiwoom.api.key.name", ""), sProperties.getJob().getProperty("seung.job.kiwoom.api.key.value", ""))
						.asBytes()
						;
					}
					
				}
				
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
				
				logFile = new File(String.format("var/tr40005/%s.json", itemCode));
				logFile.delete();
				responseText = new String(httpResponse.getBody(), "UTF-8");
				FileUtils.write(logFile, responseText, "UTF-8");
				response = new SLinkedHashMap(responseText);
				
				comm = response.getListSLinkedHashMap("comm");
				if(comm.isEmpty()) {
					message = String.format(
							"%s.%s.%s.comm=empty"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							);
					log.error(message);
					throw new SGomtangException(message);
				}
				if(!SCode.SUCCESS.equals(comm.get(0).getString("error_code", ""))) {
					message = String.format(
							"%s.%s.%s.error_code=%s"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							, comm.get(0).getString("error_code", "")
							);
					log.error(message);
					throw new SGomtangException(message);
				}
				
				tr40005 = response.getSLinkedHashMap("result").getListSLinkedHashMap("tr40005");
				
				if(tr40005.isEmpty()) {
					message = String.format(
							"%s.%s.%s.tr40005=empty"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							);
					log.error(message);
					throw new SGomtangException(message);
				}
				
				kw10000_SR.put("limit", limit);
				tr40005_SL = sMapperI.selectList("tr40005_SL", kw10000_SR);
				for(SLinkedHashMap item : tr40005) {
					
					query = new SLinkedHashMap();
					query.put("item_code", itemCode);
					query.put("trdd", item.getString("date"));
					query.put("etf_cp", item.getDouble("cp"));
					query.put("etf_inc", item.getDouble("inc"));
					query.put("etf_pcp", item.getDouble("pcp"));
					query.put("etf_vol", item.getBigInteger("vol"));
					query.put("etf_nav", item.getDouble("nav"));
					query.put("etf_volaccu", item.getBigInteger("volaccu"));
					query.put("etf_indexd", item.getDouble("indexd"));
					query.put("etf_etfd", item.getDouble("etfd"));
					query.put("etf_ter", item.getDouble("ter"));
					query.put("etf_ti", item.getDouble("ti"));
					query.put("etf_tiinc", item.getDouble("tiinc"));
					query.put("hash", SConvert.digestToHex("MD5", query.toJsonString()));
					
					isInsert = true;
					isUpdate = false;
					isContinue = false;
					isBreak = false;
					for(SLinkedHashMap tr40005_SR : tr40005_SL) {
						if(tr40005_SR.getString("trdd").equals(query.getString("trdd"))) {
							if(tr40005_SR.getString("hash").equals(query.getString("hash"))) {
								isContinue = true;
							} else {
								isUpdate = true;
							}
							if(tr40005_SR.getInt("rn") == limit) {
								isBreak = true;
							}
							isInsert = false;
							break;
						}
					}
					
					if(isInsert) {
						tr40005_IR += sMapperI.insert("tr40005_IR", query);
						log.debug(
								"{}.{}.{}.{}.IR={}"
								, jobHistMap.getString("schd_set", "")
								, jobHistMap.getString("schd_code", "")
								, schdNo
								, itemCode
								, query.getString("trdd")
								);
					} else if(isContinue) {
						log.debug(
								"{}.{}.{}.{}.IGNORE={}"
								, jobHistMap.getString("schd_set", "")
								, jobHistMap.getString("schd_code", "")
								, schdNo
								, itemCode
								, query.getString("trdd")
								);
						tr40005_IGNORE++;
						if(isBreak) {
							break;
						} else {
							continue;
						}
					} else if(isUpdate) {
						log.debug(
								"{}.{}.{}.{}.UR={}"
								, jobHistMap.getString("schd_set", "")
								, jobHistMap.getString("schd_code", "")
								, schdNo
								, itemCode
								, query.getString("trdd")
								);
						tr40005_UR += sMapperI.insert("tr40005_UR", query);
					}
					
					if(isBreak) {
						break;
					}
					
				}// end of tr40005
				
				Thread.sleep(1000);
				
			}// end of tr40005_SL
			
			SLinkedHashMap trddNo = sMapperI.selectOne("trdd_no_IL");
			prev_IR = trddNo.getInt("prev");
			post_IR = trddNo.getInt("post");
			
			SLinkedHashMap etf_calc_IL = sMapperI.selectOne("etf_calc_IL");
			
			message = String.format(
					"tr40005_IR=%d, tr40005_UR=%d, tr40005_DO_NOTHING=%d, tr40005_IGNORE=%d, prev_IR=%d, post_IR=%d, etf_calc_IL=%d"
					, tr40005_IR
					, tr40005_UR
					, tr40005_DO_NOTHING
					, tr40005_IGNORE
					, prev_IR
					, post_IR
					, etf_calc_IL.getInt("f_etf_calc")
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
