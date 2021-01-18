package seung.spring.gomtang.job.fin.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.spring.boot.conf.SProperties;
import seung.spring.boot.conf.datasource.SMapperI;

@Component("sKi04S")
@Slf4j
public class SKi04S {

	@Resource(name = "sProperties")
	private SProperties sProperties;
	
	@Resource(name = "sMapperI")
	private SMapperI sMapperI;
	
	@SuppressWarnings("unchecked")
	public synchronized String ki0430(
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
				.add("schd_code", "ki0430")
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
					, jobGroup
					, jobName
					, schdNo
					);
			
			sMapperI.insert("schd_prev", jobHistMap);
			
			HttpResponse<byte[]> httpResponse = null;
			String responseText = "";
			SLinkedHashMap response = null;
			SLinkedHashMap t0424_SR = null;
			List<SLinkedHashMap> t0424_SL = null;
			String accno = "";
			String dateNo = "";
			while(true) {
				
				for(SLinkedHashMap data : sMapperI.selectList("ki_ki043010")) {
					
					log.info(
							"({}) {}.{}.memb_email={}"
							, schdNo
							, jobGroup
							, jobName
							, data.getString("memb_email")
							);
					
					dateNo = data.getString("date_no");
					
					httpResponse = Unirest
							.post("http://beforeALB-1915436010.ap-northeast-2.elb.amazonaws.com/kinsight/Portfolio/t0424")
							.connectTimeout(1000 * 5)
							.socketTimeout(1000 * 5)
							.header("Content-Type", "application/json")
							.header(sProperties.getJob().getProperty("seung.job.pridecho.api.key.name", ""), sProperties.getJob().getProperty("seung.job.pridecho.api.key.value", ""))
							.body(new SLinkedHashMap()
									.add("request_code", schdNo)
									.add("kinsightID", data.getString("memb_email"))
									.add("secret", data.getString("memb_email"))
									.toJsonString()
									)
							.asBytes()
							;
					
					if(HttpStatus.OK.value() != httpResponse.getStatus()) {
						message = String.format(
								"%s.%s.%s.response_code=%d"
								, jobGroup
								, jobName
								, schdNo
								, httpResponse.getStatus()
								);
						log.error(message);
						continue;
					}
					
					responseText = new String(httpResponse.getBody(), "UTF-8");
					response = new SLinkedHashMap(responseText);
					
					t0424_SR = response.getSLinkedHashMap("response").getSLinkedHashMap("t0424_SR");
					accno = t0424_SR.getString("accountNo", "");
					
					t0424_SL = response.getSLinkedHashMap("response").getListSLinkedHashMap("t0424_SL");
					for(SLinkedHashMap t0424 : t0424_SL) {
						
						query = new SLinkedHashMap(t0424);
						query.put("memb_code", data.getString("memb_code", ""));
						query.put("inv_sec_code", data.getString("inv_sec_code", ""));
						query.put("inv_sec_id", data.getString("inv_sec_id", ""));
						query.put("accno", accno);
						query.put("date_no", dateNo);
						
						sMapperI.insert("ki_ki043020", query);
					}// end of t0424_SL
					
					query = new SLinkedHashMap();
					query.put("memb_code", data.getString("memb_code", ""));
					query.put("inv_sec_code", data.getString("inv_sec_code", ""));
					query.put("inv_sec_id", data.getString("inv_sec_id", ""));
					query.put("accno", accno);
					query.put("date_no", dateNo);
					query.put("sunamt", t0424_SR.getString("sunamt", ""));
					query.put("dtsunik", t0424_SR.getString("dtsunik", ""));
					query.put("mamt", t0424_SR.getString("mamt", ""));
					query.put("sunamt1", t0424_SR.getString("sunamt1", ""));
					query.put("cts_expcode", t0424_SR.getString("cts_expcode", ""));
					query.put("tappamt", t0424_SR.getString("tappamt", ""));
					query.put("tdtsunik", t0424_SR.getString("tdtsunik", ""));
					
					sMapperI.insert("ki_ki043030", query);
					if(0 == sMapperI.update("ki_ki043040", query)) {
						sMapperI.insert("ki_ki043050", query);
					}
					
				}// end of ki_ki043010
				
				break;
			}
			
			errorCode = "0000";
			
		} catch (Exception e) {
			log.error(
					"{}.{}.{}.exception={}"
					, jobGroup
					, jobName
					, schdNo
					, jobHistMap.getString("exception", "" + e)
					, e
					);
			if(query != null) {
				log.error(
						"{}.{}.{}.query={}"
						, jobGroup
						, jobName
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
					, jobGroup
					, jobName
					, schdNo
					, jobHistMap.getString("error_code", "")
					);
			sMapperI.insert("schd_post", jobHistMap);
			log.info(
					"{}.{}.{} ((END))"
					, jobGroup
					, jobName
					, schdNo
					);
		}
		
		return errorCode;
	}
}
