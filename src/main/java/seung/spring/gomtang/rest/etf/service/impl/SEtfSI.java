package seung.spring.gomtang.rest.etf.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.spring.boot.conf.datasource.SMapperI;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;
import seung.spring.gomtang.rest.etf.service.SEtfS;
import seung.spring.gomtang.rest.etf.util.Etf0101;
import seung.spring.gomtang.rest.etf.util.Etf0102;
import seung.spring.gomtang.rest.etf.util.Etf0103;
import seung.spring.gomtang.rest.etf.util.Etf0111;
import seung.spring.gomtang.rest.etf.util.Etf0112;
import seung.spring.gomtang.rest.etf.util.Etf0113;

@Slf4j
@Service(value = "sEtfS")
public class SEtfSI implements SEtfS {

	@Resource(name = "sMapperI")
	private SMapperI sMapperI;
	
//	@SuppressWarnings("unchecked")
//	@Override
//	public SResponse etf0000(SRequest sRequest) {
//		
//		String apiCode = "etf0000";
//		String error_message = "";
//		String requestCode = sRequest.getData().getString("request_code", "");
//		log.info("{}.{} ((START))", apiCode, requestCode);
//		
//		SResponse sResponse = SResponse.builder()
//				.request_code(requestCode)
//				.error_code(SCode.ERROR)
//				.data(sRequest.getData())
//				.build()
//				;
//		
//		List<SLinkedHashMap> etf0000_SL = null;
//		try {
//			
//			log.info("{}.{}.query: {}", apiCode, requestCode, sRequest.getData().toJsonString());
//			
//			etf0000_SL = sMapperI.selectList(
//					"etf0000_SL"
//					, new SLinkedHashMap()
//						.add("api_set", sRequest.getData().getString("api_set", ""))
//						.add("api_code", sRequest.getData().getString("api_code", ""))
//					);
//			for(SLinkedHashMap etf0000_SR : etf0000_SL) {
//				if(etf0000_SR.getString("api_data", "").trim().startsWith("{")) {
//					etf0000_SR.put("api_data", new ObjectMapper().readValue(etf0000_SR.getString("api_data"), Map.class));
//				} else if(etf0000_SR.getString("api_data", "").trim().startsWith("[")) {
//					etf0000_SR.put("api_data", new ObjectMapper().readValue(etf0000_SR.getString("api_data"), List.class));
//				}
//				if(etf0000_SR.getString("api_rslt", "").trim().startsWith("{")) {
//					etf0000_SR.put("api_rslt", new ObjectMapper().readValue(etf0000_SR.getString("api_rslt"), Map.class));
//				} else if(etf0000_SR.getString("api_rslt", "").trim().startsWith("[")) {
//					etf0000_SR.put("api_rslt", new ObjectMapper().readValue(etf0000_SR.getString("api_rslt"), List.class));
//				}
//			}
//			
//			sResponse.success();
//			
//		} catch (Exception e) {
//			log.error("{}.{}.exception", apiCode, requestCode, e);
//			error_message = ExceptionUtils.getStackTrace(e);
//			if(error_message == null || "".equals(error_message)) {
//				error_message = "" + e;
//			}
//		} finally {
//			sResponse.setError_message(error_message);
//			if(etf0000_SL == null) {
//				sResponse.putResponse("total_count", "0");
//				sResponse.putResponse("etf0000", new ArrayList<>());
//			} else {
//				sResponse.putResponse("total_count", "" + etf0000_SL.size());
//				sResponse.putResponse("etf0000", etf0000_SL);
//			}
//		}
//		
//		log.info("{}.{} ((END))", apiCode, requestCode);
//		sResponse.setResponse_time(String.valueOf(new Date().getTime()));
//		return sResponse;
//	}
//	
//	@Override
//	public SResponse etf0001(SRequest sRequest) {
//		
//		String apiCode = "etf0001";
//		String error_message = "";
//		String requestCode = sRequest.getData().getString("request_code", "");
//		log.info("{}.{} ((START))", apiCode, requestCode);
//		
//		SResponse sResponse = SResponse.builder()
//				.request_code(requestCode)
//				.error_code(SCode.ERROR)
//				.data(sRequest.getData())
//				.build()
//				;
//		
//		List<SLinkedHashMap> etf0001_SL = null;
//		try {
//			
//			log.info("{}.{}.query: {}", apiCode, requestCode, sRequest.getData().toJsonString());
//			
//			etf0001_SL = sMapperI.selectList(
//					"etf0001_SL"
//					, new SLinkedHashMap()
//						.add("optn_set", sRequest.getData().getString("optn_set", ""))
//					);
//			
//			sResponse.success();
//			
//		} catch (Exception e) {
//			log.error("{}.{}.exception", apiCode, requestCode, e);
//			error_message = ExceptionUtils.getStackTrace(e);
//			if(error_message == null || "".equals(error_message)) {
//				error_message = "" + e;
//			}
//		} finally {
//			sResponse.setError_message(error_message);
//			if(etf0001_SL == null) {
//				sResponse.putResponse("total_count", "0");
//				sResponse.putResponse("etf0001", new ArrayList<>());
//			} else {
//				sResponse.putResponse("total_count", "" + etf0001_SL.size());
//				sResponse.putResponse("etf0001", etf0001_SL);
//			}
//		}
//		
//		log.info("{}.{} ((END))", apiCode, requestCode);
//		sResponse.setResponse_time(String.valueOf(new Date().getTime()));
//		return sResponse;
//	}
	
	@Override
	public SResponse etf0101(SRequest sRequest, Etf0101 etf0101) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(etf0101.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(etf0101)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		SLinkedHashMap query = null;
		SLinkedHashMap etf0102_SR = null;
		List<SLinkedHashMap> etf0101_SL = null;
		String error_message = "";
		try {
			
			log.info("({}) request={}", sResponse.getRequest_code(), etf0101.toJsonString());
			
//			String rules = "";
//			if(!"".equals(sRequest.getData().getString("rules", "").trim())) {
//				rules = String.format(
//						"AND %s"
//						, new SqlQueryBuilderFactory()
//							.builder()
//							.build(sRequest.getData().getString("rules"))
//							.getQuery(true)
//						)
//						;
//			}
			
			query = new SLinkedHashMap()
					.add("req_json", etf0101.toJsonString())
					;
			
			etf0102_SR = sMapperI.selectOne("etf0102_SR");
			etf0101_SL = sMapperI.selectList(
					"etf0101_SL"
					, query
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
			if(etf0102_SR == null) {
				sResponse.putResponse("trdd", "");
				sResponse.putResponse("trdd_no", "");
			} else {
				sResponse.putResponse("trdd", etf0102_SR.getString("trdd_to", ""));
				sResponse.putResponse("trdd_no", etf0102_SR.getString("trdd_no_max", ""));
			}
			if(etf0101_SL == null) {
				sResponse.putResponse("etf0101_S", 0);
				sResponse.putResponse("etf0101_SL", new ArrayList<>());
			} else {
				sResponse.putResponse("etf0101_S", etf0101_SL.size());
				sResponse.putResponse("etf0101_SL", etf0101_SL == null ? new ArrayList<>() : etf0101_SL);
			}
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	@Override
	public SResponse etf0102(SRequest sRequest, Etf0102 etf0102) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(etf0102.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(etf0102)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		SLinkedHashMap etf0102_SR = null;
		String error_message = "";
		try {
			
			etf0102_SR = sMapperI.selectOne("etf0102_SR");
			
			sResponse.success();
			
		} catch (Exception e) {
			log.info("({}) ", sResponse.getRequest_code(), e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
			sResponse.putResponse("etf0102_SR", etf0102_SR);
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	@Override
	public SResponse etf0103(SRequest sRequest, Etf0103 etf0103) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(etf0103.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(etf0103)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		List<SLinkedHashMap> etf0103_SL = null;
		String error_message = "";
		try {
			
			etf0103_SL = sMapperI.selectList("etf0103_SL");
			
			sResponse.success();
			
		} catch (Exception e) {
			log.info("({}) ", sResponse.getRequest_code(), e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
			if(etf0103_SL.isEmpty()) {
				sResponse.putResponse("etf0103_S", 0);
				sResponse.putResponse("etf0103_SL", new ArrayList<>());
			} else {
				sResponse.putResponse("etf0103_S", etf0103_SL.size());
				sResponse.putResponse("etf0103_SL", etf0103_SL);
			}
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SResponse etf0111(SRequest sRequest, Etf0111 etf0111) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(etf0111.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(etf0111)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		SLinkedHashMap query = null;
		SLinkedHashMap etf0111_SR = null;
		int etf0111_S = -1;
		List<SLinkedHashMap> etf0111_SL = null;
		String error_message = "";
		try {
			
			log.info("({}) request={}", sResponse.getRequest_code(), etf0111.toJsonString());
			
			query = new SLinkedHashMap();
			query.put("req_json", query.toJsonString());
			
			etf0111_SR = sMapperI.selectOne("etf0111_SR", query);
			if(etf0111_SR != null) {
				etf0111_S = etf0111_SR.getInt("total_count", 0);
			}
			etf0111_SL = sMapperI.selectList("etf0111_SL", query);
			
			sResponse.success();
			
		} catch (Exception e) {
			log.info("({}) query={}", sResponse.getRequest_code(), query.toJsonString(), e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
			sResponse.putResponse("etf0111_S", etf0111_S);
			if(etf0111_SL == null) {
				sResponse.putResponse("etf0111_SL", new ArrayList<>());
			} else {
				sResponse.putResponse("etf0111_SL", etf0111_SL);
			}
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	@Override
	public SResponse etf0112(SRequest sRequest, Etf0112 etf0112) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(etf0112.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(etf0112)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		SLinkedHashMap query = null;
		SLinkedHashMap etf0112_SR = null;
		String error_message = "";
		try {
			
			log.info("({}) request={}", sResponse.getRequest_code(), etf0112.toJsonString());
			
			query = new SLinkedHashMap(etf0112);
			
			etf0112_SR = sMapperI.selectOne(
					"etf0112_SR"
					, query
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
			sResponse.putResponse("etf0112_SR", etf0112_SR == null ? new SLinkedHashMap() : etf0112_SR);
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	@Override
	public SResponse etf0113(SRequest sRequest, Etf0113 etf0113) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(etf0113.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(etf0113)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		SLinkedHashMap query = null;
		List<SLinkedHashMap> etf0113_SL = null;
		String error_message = "";
		try {
			
			log.info("({}) request={}", sResponse.getRequest_code(), etf0113.toJsonString());
			
			query = new SLinkedHashMap(etf0113);
			
			etf0113_SL = sMapperI.selectList(
					"etf0113_SL"
					, query
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
			if(etf0113_SL == null) {
				sResponse.putResponse("etf0113_S", 0);
				sResponse.putResponse("etf0113_SL", new ArrayList<>());
			} else {
				sResponse.putResponse("etf0113_S", etf0113_SL.size());
				sResponse.putResponse("etf0113_SL", etf0113_SL);
			}
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
//	@SuppressWarnings("unchecked")
//	@Override
//	public SResponse etf9001(SRequest sRequest) {
//		
//		String apiCode = "etf9001";
//		String error_message = "";
//		String requestCode = sRequest.getData().getString("request_code", "");
//		log.info("{}.{} ((START))", apiCode, requestCode);
//		
//		SResponse sResponse = SResponse.builder()
//				.request_code(requestCode)
//				.error_code(SCode.ERROR)
//				.data(sRequest.getData())
//				.build()
//				;
//		
//		String itemCode = "";
//		SLinkedHashMap query = null;
//		try {
//			
//			log.info("{}.{}.query: {}", apiCode, requestCode, sRequest.getData().toJsonString());
//			
//			SLinkedHashMap item = null;
//			File fileRun = null;
//			File fileDone = null;
//			SLinkedHashMap comm = null;
//			SLinkedHashMap data = null;
//			SLinkedHashMap etf9001_B = null;
//			for(File itemFile : new File("E:/temps/tr10081").listFiles()) {
//				
//				if(!itemFile.exists()) {
//					continue;
//				}
//				
//				fileRun = new File("E:/temps/tr10081/run/" + itemFile.getName());
//				fileDone = new File("E:/temps/tr10081/done/" + itemFile.getName());
//				
//				FileUtils.moveFile(itemFile, fileRun);
//				
//				item = new SLinkedHashMap(FileUtils.readFileToString(fileRun, "UTF-8"));
//				
//				comm = item.getListSLinkedHashMap("comm").get(0);
//				if(!"0000".equals(comm.getString("error_code"))) {
//					continue;
//				}
//				
//				data = item.getListSLinkedHashMap("data").get(0);
//				itemCode = data.getString("item_code");
//				
//				for(SLinkedHashMap dailyData : item.getSLinkedHashMap("result").getListSLinkedHashMap("tr10081")) {
//					
//					query = new SLinkedHashMap();
//					query.put("item_code", itemCode);
//					query.put("trdd", dailyData.getString("date"));
//					query.put("etf_cp", dailyData.getDouble("cp"));
//					query.put("etf_vol", dailyData.getBigInteger("vol"));
//					query.put("etf_lp", dailyData.getDouble("lp"));
//					query.put("etf_hp", dailyData.getDouble("hp"));
//					query.put("etf_op", dailyData.getDouble("op"));
//					query.put("hash", SConvert.digestToHex("MD5", query.toJsonString()));
//					
//					etf9001_B = sMapperI.selectOne("etf9001_B", query);
//					
//					if(etf9001_B == null) {
//						sMapperI.insert("etf9001_A", query);
//					} else if(!query.getString("hash").equals(etf9001_B.getString("hash"))) {
//						sMapperI.update("etf9001_C", query);
//					}
//					
//				}// end of daily
//				
//				FileUtils.moveFile(fileRun, fileDone);
//				
//			}// end of dir
//			
//			sResponse.success();
//			
//		} catch (Exception e) {
//			log.error("{}.{}.exception", apiCode, requestCode, e);
//			log.error("{}.{}.query", apiCode, requestCode, query.toJsonString(true));
//			error_message = ExceptionUtils.getStackTrace(e);
//			if(error_message == null || "".equals(error_message)) {
//				error_message = "" + e;
//			}
//		} finally {
//			sResponse.setError_message(error_message);
//		}
//		
//		log.info("{}.{} ((END))", apiCode, requestCode);
//		sResponse.setResponse_time(String.valueOf(new Date().getTime()));
//		return sResponse;
//	}
//	
//	@Override
//	public SResponse etf9002(SRequest sRequest) {
//		
//		String apiCode = "etf9002";
//		String error_message = "";
//		String requestCode = sRequest.getData().getString("request_code", "");
//		log.info("{}.{} ((START))", apiCode, requestCode);
//		
//		SResponse sResponse = SResponse.builder()
//				.request_code(requestCode)
//				.error_code(SCode.ERROR)
//				.data(sRequest.getData())
//				.build()
//				;
//		
//		SLinkedHashMap query = null;
//		try {
//			
//			log.info("{}.{}.query: {}", apiCode, requestCode, sRequest.getData().toJsonString());
//			
//			String uri = "e:/temps/etf_cp_20200728.csv";
//			
//			List<String> headers = new ArrayList<>();
//			headers.add("date");
//			for(SLinkedHashMap etf9002_A : sMapperI.selectList("etf9002_A")) {
//				headers.add(etf9002_A.getString("item_code"));
//			}
//			String[] header = headers.toArray(new String[headers.size()]);
//			
//			int times = 0;
//			try(
//					BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(uri));
//					CSVPrinter csvPrinter = new CSVPrinter(bufferedWriter, CSVFormat.DEFAULT.withHeader(header));
//					) {
//				
//				String trdd = "";
//				SLinkedHashMap record = null;
//				List<String> records = null;
//				for(SLinkedHashMap etf9002_B : sMapperI.selectList("etf9002_B")) {
//					
//					if(++times % 100 == 1) {
//						log.info("times={}", times);
//					}
//					
//					trdd = etf9002_B.getString("trdd");
//					
//					record = new SLinkedHashMap();
//					records = new ArrayList<>();
//					for(SLinkedHashMap etf9002_C : sMapperI.selectList("etf9002_C", etf9002_B)) {
//						record.put(etf9002_C.getString("item_code"), etf9002_C.getString("etf_cp", ""));
//					}
//					
//					for(String itemCode : header) {
//						if(records.isEmpty()) {
//							records.add(trdd);
//						} else {
//							records.add(record.getString(itemCode, ""));
//						}
//					}
//					
//					csvPrinter.printRecord(records.toArray(new String[headers.size()]));
//					
//				}
//				
//			}
//			
//			sResponse.success();
//			
//		} catch (Exception e) {
//			log.error("{}.{}.exception", apiCode, requestCode, e);
//			log.error("{}.{}.query", apiCode, requestCode, query.toJsonString(true));
//			error_message = ExceptionUtils.getStackTrace(e);
//			if(error_message == null || "".equals(error_message)) {
//				error_message = "" + e;
//			}
//		} finally {
//			sResponse.setError_message(error_message);
//		}
//		
//		log.info("{}.{} ((END))", apiCode, requestCode);
//		sResponse.setResponse_time(String.valueOf(new Date().getTime()));
//		return sResponse;
//	}
	
}
