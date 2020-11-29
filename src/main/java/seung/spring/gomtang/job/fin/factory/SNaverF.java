package seung.spring.gomtang.job.fin.factory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.util.SLinkedHashMap;

@Component("sNaverF")
@Slf4j
public class SNaverF {

	@SuppressWarnings("unchecked")
	public List<SLinkedHashMap> n0104(
			String item_code
			) {
		
		log.debug("run");
		
		List<SLinkedHashMap> n0104 = new ArrayList<>();
		
		try {
			
			HttpResponse<byte[]> httpResponse = Unirest
					.get(String.format("https://finance.naver.com/item/coinfo.nhn?code=%s", item_code))
					.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko")
					.asBytes()
					;
			httpResponse = Unirest
					.get(String.format("https://navercomp.wisereport.co.kr/v2/company/c1010001.aspx?cmp_cd=%s", item_code))
					.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko")
					.asBytes()
					;
			
			while(true) {
				
				if(HttpStatus.OK.value() != httpResponse.getStatus()) {
					break;
				}
				
				String response = new String(httpResponse.getBody(), "UTF-8");
				
				if(response.indexOf("\"ajax/cF1001.aspx\"") == -1) {
					break;
				}
				
				if(response.indexOf("encparam:") == -1) {
					break;
				}
				
				if(response.indexOf("id:") == -1) {
					break;
				}
				
				response = response.split("\"ajax/cF1001.aspx\"")[1].split("\\{")[1].split("\\}")[0];
				String encparam = response.split("encparam:")[1].split("'")[1].split("'")[0];
				String id = response.split("id:")[1].split("'")[1].split("'")[0];
				
//				log.info(String.format(sProperties.getSeung().getProperty("seung.mine.naver.n0105.url", ""), item_code, encparam, id));
				httpResponse = Unirest
						.get(String.format("https://navercomp.wisereport.co.kr/v2/company/ajax/cF1001.aspx?cmp_cd=%s&fin_typ=0&freq_typ=Y&encparam=%s&id=%s", item_code, encparam, id))
						.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko")
						.header("Referer", String.format("https://navercomp.wisereport.co.kr/v2/company/c1010001.aspx?cmp_cd=%s", item_code))
						.asBytes()
						;
				
				
				if(HttpStatus.OK.value() != httpResponse.getStatus()) {
					break;
				}
				
				response = new String(httpResponse.getBody(), "UTF-8");
				
//				log.info(response);
				
				if(response.indexOf("연간") == -1) {
					break;
				}
				
				response = response.split("연간")[1];
				
				String[] item_sds = response.split("</thead>")[0].split("<th");
				String item_sd = "";
				for(int i = 1; i < item_sds.length; i++) {
					item_sd = item_sds[i].split(">")[1].split("<")[0];
					n0104.add(new SLinkedHashMap()
							.add("item_sd", item_sd.replaceAll("[^0-9-]", ""))
							.add("is_est", item_sd.contains("E") ? 1 : 0)
							);
				}
				
				String[] item_trs = response.split("매출액")[1].split("<td");
				String item_tr = "";
				for(int i = 1; i <= n0104.size(); i++) {
					item_tr = item_trs[i];
					if(item_tr.contains("<span")) {
						item_tr = item_tr.split("<span")[1].split(">")[1].split("<")[0];
					} else {
						item_tr = "";
					}
					n0104.get(i - 1).put("item_tr", item_tr.replaceAll("[^0-9-]", ""));
				}
				
				String[] item_ois = response.split("영업이익")[1].split("<td");
				String item_oi = "";
				for(int i = 1; i <= n0104.size(); i++) {
					item_oi = item_ois[i];
					if(item_oi.contains("<span")) {
						item_oi = item_oi.split("<span")[1].split(">")[1].split("<")[0];
					} else {
						item_oi = "";
					}
					n0104.get(i - 1).put("item_oi", item_oi.replaceAll("[^0-9-]", ""));
				}
				
				String[] item_nis = response.split("당기순이익")[1].split("<td");
				String item_ni = "";
				for(int i = 1; i <= n0104.size(); i++) {
					item_ni = item_nis[i];
					if(item_ni.contains("<span")) {
						item_ni = item_ni.split("<span")[1].split(">")[1].split("<")[0];
					} else {
						item_ni = "";
					}
					n0104.get(i - 1).put("item_ni", item_ni.replaceAll("[^0-9-]", ""));
				}
				
				String[] item_nicis = response.split("당기순이익\\(지배\\)")[1].split("<td");
				String item_nici = "";
				for(int i = 1; i <= n0104.size(); i++) {
					item_nici = item_nicis[i];
					if(item_nici.contains("<span")) {
						item_nici = item_nici.split("<span")[1].split(">")[1].split("<")[0];
					} else {
						item_nici = "";
					}
					n0104.get(i - 1).put("item_nici", item_nici.replaceAll("[^0-9-]", ""));
				}
				
				String[] item_tas = response.split("자산총계")[1].split("<td");
				String item_ta = "";
				for(int i = 1; i <= n0104.size(); i++) {
					item_ta = item_tas[i];
					if(item_ta.contains("<span")) {
						item_ta = item_ta.split("<span")[1].split(">")[1].split("<")[0];
					} else {
						item_ta = "";
					}
					n0104.get(i - 1).put("item_ta", item_ta.replaceAll("[^0-9-]", ""));
				}
				
				String[] item_tls = response.split("부채총계")[1].split("<td");
				String item_tl = "";
				for(int i = 1; i <= n0104.size(); i++) {
					item_tl = item_tls[i];
					if(item_tl.contains("<span")) {
						item_tl = item_tl.split("<span")[1].split(">")[1].split("<")[0];
					} else {
						item_tl = "";
					}
					n0104.get(i - 1).put("item_tl", item_tl.replaceAll("[^0-9-]", ""));
				}
				
				String[] item_tes = response.split("자본총계")[1].split("<td");
				String item_te = "";
				for(int i = 1; i <= n0104.size(); i++) {
					item_te = item_tes[i];
					if(item_te.contains("<span")) {
						item_te = item_te.split("<span")[1].split(">")[1].split("<")[0];
					} else {
						item_te = "";
					}
					n0104.get(i - 1).put("item_te", item_te.replaceAll("[^0-9-]", ""));
				}
				
				String[] item_cfos = response.split("영업활동현금흐름")[1].split("<td");
				String item_cfo = "";
				for(int i = 1; i <= n0104.size(); i++) {
					item_cfo = item_cfos[i];
					if(item_cfo.contains("<span")) {
						item_cfo = item_cfo.split("<span")[1].split(">")[1].split("<")[0];
					} else {
						item_cfo = "";
					}
					n0104.get(i - 1).put("item_cfo", item_cfo.replaceAll("[^0-9-]", ""));
				}
				
				String[] item_des = response.split("부채비율")[1].split("<td");
				String item_de = "";
				for(int i = 1; i <= n0104.size(); i++) {
					item_de = item_des[i];
					if(item_de.contains("<span")) {
						item_de = item_de.split("<span")[1].split(">")[1].split("<")[0];
					} else {
						item_de = "";
					}
					n0104.get(i - 1).put("item_de", item_de.replaceAll("[^0-9-]", ""));
				}
				
				break;
			}
			
		} catch (Exception e) {
			log.error(
					"naver.0104.exception="
					, e
					);
		}
		
		return n0104;
	}// end of etfN0102
	
}
