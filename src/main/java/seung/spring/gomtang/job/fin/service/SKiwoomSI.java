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
@Service("sKiwoomS")
public class SKiwoomSI {

	@Resource(name = "sProperties")
	private SProperties sProperties;
	
	@Resource(name = "sMapperI")
	private SMapperI sMapperI;
	
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
			
			SLinkedHashMap data = new SLinkedHashMap();
			data.put("market_type", "3");
			
			HttpResponse<byte[]> httpResponse = Unirest
					.post(sProperties.getJob().getProperty("seung.job.kiwoom.kw10000.url", ""))
					.connectTimeout(1000 * 3)
					.socketTimeout(1000 * 30)
					.header(sProperties.getJob().getProperty("seung.job.kiwoom.api.key.name", ""), sProperties.getJob().getProperty("seung.job.kiwoom.api.key.value", ""))
					.header("Content-Type", "application/json")
					.body(new SLinkedHashMap().add("market_type", "3").toJsonString())
					.asBytes()
					;
			
			if(HttpStatus.OK.value() != httpResponse.getStatus()) {
				message = String.format(
						"%s.%s.%s.status %d"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, httpResponse.getStatus()
						);
				log.error(message);
				throw new SGomtangException(message);
			}
			
			SLinkedHashMap response = new SLinkedHashMap(new String(httpResponse.getBody(), "UTF-8"));
			
			List<SLinkedHashMap> comm = response.getListSLinkedHashMap("comm");
			if(comm.isEmpty()) {
				message = String.format(
						"%s.%s.%s.comm empty"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						);
				log.error(message);
				throw new SGomtangException(message);
			}
			if(!SCode.SUCCESS.equals(comm.get(0).getString("error_code", ""))) {
				message = String.format(
						"%s.%s.%s.error_code %s"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, comm.get(0).getString("error_code", "")
						);
				log.error(message);
				throw new SGomtangException(message);
			}
			
			List<SLinkedHashMap> etf = response.getSLinkedHashMap("result").getListSLinkedHashMap("etf");
			
			int loopTry = 0;
			int kw10000_IR = 0;
			int kw10000_UR = 0;
			int kw10000_DO_NOTHING = 0;
			int kw10000_IGNORE = 0;
			SLinkedHashMap kw10000_SR = null;
			for(SLinkedHashMap item : etf) {
				
				if(loopTry++ % 100 == 0) {
					log.info(
							"{}.{}.{}.try {}"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, jobHistMap.getString("schd_no", "")
							, loopTry
							);
				}
				
				query = new SLinkedHashMap();
				query.put("item_code", item.getString("item_code", ""));
				query.put("item_name", item.getString("item_name", ""));
				query.put("etf_cnst", item.getString("cnst", ""));
				query.put("etf_stts", item.getString("stts", ""));
				query.put("hash", SConvert.digestToHex("MD5", query.toJsonString()));
				kw10000_SR = sMapperI.selectOne("kw10000_SR", query);
				if(kw10000_SR == null) {
					kw10000_IR += sMapperI.insert("kw10000_IR", query);
				} else if(query.getString("hash", "").equals(kw10000_SR.getString("hash", "1"))) {
					kw10000_DO_NOTHING++;
				} else if(!query.getString("hash", "").equals(kw10000_SR.getString("hash", "1"))) {
					kw10000_UR += sMapperI.insert("kw10000_UR", query);
				} else {
					kw10000_IGNORE++;
				}
				
			}// end of etf
			
			message = String.format(
					"kw10000_IR=%d, kw10000_UR=%d, kw10000_DO_NOTHING=%d, kw10000_IGNORE=%d"
					, kw10000_IR
					, kw10000_UR
					, kw10000_DO_NOTHING
					, kw10000_IGNORE
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
			jobHistMap.put("message", ExceptionUtils.getStackTrace(e));
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
			String itemCode = "";
			SLinkedHashMap tr10001 = null;
			int tr10001_IR = 0;
			int tr10001_UR = 0;
			int tr10001_DO_NOTHING = 0;
			int tr10001_IGNORE = 0;
			SLinkedHashMap tr10001_SR = null;
			for(SLinkedHashMap kw10000_SR : sMapperI.selectList("kw10000_SL")) {
				
				if(loopTry++ % 100 == 0) {
					log.info(
							"{}.{}.{}.try {}"
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
						.body(new SLinkedHashMap().add("item_code", kw10000_SR.getString("item_code")).toJsonString())
						.asBytes()
						;
				
				if(HttpStatus.OK.value() != httpResponse.getStatus()) {
					message = String.format(
							"%s.%s.%s.status %d"
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
							"%s.%s.%s.comm empty"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							);
					log.error(message);
					throw new SGomtangException(message);
				}
				if(!SCode.SUCCESS.equals(comm.get(0).getString("error_code", ""))) {
					message = String.format(
							"%s.%s.%s.error_code %s"
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
							"%s.%s.%s.data empty"
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
				
				tr10001_SR = sMapperI.selectOne("tr10001_SR", query);
				if(tr10001_SR == null) {
					tr10001_IR += sMapperI.insert("tr10001_IR", query);
				} else if(query.getString("hash", "").equals(tr10001_SR.getString("hash", "1"))) {
					tr10001_DO_NOTHING++;
				} else if(!query.getString("hash", "").equals(tr10001_SR.getString("hash", "1"))) {
					tr10001_UR += sMapperI.insert("tr10001_UR", query);
				} else {
					tr10001_IGNORE++;
				}
				
				Thread.sleep(500);
				
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
						, query != null ? query.toJsonString(true) : ""
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
			
			int loopTry = 0;
			HttpResponse<byte[]> httpResponse = null;
			SLinkedHashMap response = null;
			List<SLinkedHashMap> comm = null;
			List<SLinkedHashMap> tr40005 = null;
			int tr40005_IR = 0;
			int tr40005_UR = 0;
			int tr40005_DO_NOTHING = 0;
			int tr40005_IGNORE = 0;
			SLinkedHashMap tr40005_SR = null;
			int prev_IR = 0;
			int post_IR = 0;
			for(SLinkedHashMap tr40005_rnk : sMapperI.selectList("tr40005_SL", jobHistMap)) {
				
				log.info(
						"{}.{}.{}.item_code {}"
						, jobHistMap.getString("schd_set", "")
						, jobHistMap.getString("schd_code", "")
						, schdNo
						, tr40005_rnk.getString("item_code")
						);
				if(loopTry++ % 100 == 0) {
					log.info(
							"{}.{}.{}.try {}"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							, loopTry
							);
				}
				
				httpResponse = Unirest
						.post(sProperties.getJob().getProperty("seung.job.kiwoom.tr40005.url", ""))
						.connectTimeout(1000 * 3)
						.socketTimeout(1000 * 30)
						.header("Content-Type", "application/json")
						.header(sProperties.getJob().getProperty("seung.job.kiwoom.api.key.name", ""), sProperties.getJob().getProperty("seung.job.kiwoom.api.key.value", ""))
						.body(new SLinkedHashMap()
								.add("repeat_30", "1")
								.add("item_code", tr40005_rnk.getString("item_code"))
								.toJsonString()
								)
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
				
				comm = response.getListSLinkedHashMap("comm");
				if(comm.isEmpty()) {
					message = String.format(
							"%s.%s.%s.comm empty"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							);
					log.error(message);
					throw new SGomtangException(message);
				}
				if(!SCode.SUCCESS.equals(comm.get(0).getString("error_code", ""))) {
					message = String.format(
							"%s.%s.%s.error_code %s"
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
							"%s.%s.%s.tr40005 empty"
							, jobHistMap.getString("schd_set", "")
							, jobHistMap.getString("schd_code", "")
							, schdNo
							);
					log.error(message);
					throw new SGomtangException(message);
				}
				
				for(SLinkedHashMap item : tr40005) {
					
					query = new SLinkedHashMap();
					query.put("trdd", item.getString("date"));
					query.put("item_code", tr40005_rnk.getString("item_code"));
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
					
					tr40005_SR = sMapperI.selectOne("tr40005_SR", query);
					if(tr40005_SR == null) {
						tr40005_IR += sMapperI.insert("tr40005_IR", query);
					} else if(query.getString("hash", "").equals(tr40005_SR.getString("hash", "1"))) {
						tr40005_DO_NOTHING++;
					} else if(!query.getString("hash", "").equals(tr40005_SR.getString("hash", "1"))) {
						tr40005_UR += sMapperI.insert("tr40005_UR", query);
					} else {
						tr40005_IGNORE++;
					}
					
					if(tr40005_rnk.getString("trdd", "20200101").equals(item.getString("date", ""))) {
						break;
					}
					
				}// end of tr40005
				
				Thread.sleep(1000);
				
			}// end of tr40005_SL
			
			SLinkedHashMap trddNo = sMapperI.selectOne("trdd_no_IL");
			prev_IR = trddNo.getInt("prev");
			post_IR = trddNo.getInt("post");
			
			message = String.format(
					"tr40005_IR=%d, tr40005_UR=%d, tr40005_DO_NOTHING=%d, tr40005_IGNORE=%d, prev_IR=%d, post_IR=%d"
					, tr40005_IR
					, tr40005_UR
					, tr40005_DO_NOTHING
					, tr40005_IGNORE
					, prev_IR
					, post_IR
					)
					;
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
			jobHistMap.put("message", ExceptionUtils.getStackTrace(e));
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
