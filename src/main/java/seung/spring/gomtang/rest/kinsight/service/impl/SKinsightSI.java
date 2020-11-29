package seung.spring.gomtang.rest.kinsight.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.util.SLinkedHashMap;
import seung.spring.boot.conf.SProperties;
import seung.spring.boot.conf.datasource.SMapperI;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;
import seung.spring.gomtang.rest.kinsight.handler.SMailH;
import seung.spring.gomtang.rest.kinsight.service.SKinsightS;
import seung.spring.gomtang.rest.kinsight.util.Ki0001;
import seung.spring.gomtang.rest.kinsight.util.Ki0100;
import seung.spring.gomtang.rest.kinsight.util.Ki0110;
import seung.spring.gomtang.rest.kinsight.util.Ki0120;
import seung.spring.gomtang.rest.kinsight.util.Ki0121;
import seung.spring.gomtang.rest.kinsight.util.Ki0200;
import seung.spring.gomtang.rest.kinsight.util.Ki0210;
import seung.spring.gomtang.rest.kinsight.util.Ki0300;
import seung.spring.gomtang.rest.kinsight.util.Ki0310;

@Service(value = "sKinsightS")
@Slf4j
public class SKinsightSI implements SKinsightS {

	@Resource(name = "sProperties")
	private SProperties sProperties;
	
	@Resource(name = "sMapperI")
	private SMapperI sMapperI;
	
	@Resource(name = "sMailH")
	private SMailH sMailH;
	
	@Override
	public SResponse ki0001(SRequest sRequest, Ki0001 ki0001) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(ki0001.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(ki0001)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		String error_message = "";
		try {
			
			log.info("({}) request={}", sResponse.getRequest_code(), ki0001.toJsonString());
			
			sMailH.send(
					sProperties.getJob().getProperty("seung.job.mail.host", "")
					, sProperties.getJob().getProperty("seung.job.mail.port", "")
					, sProperties.getJob().getProperty("seung.job.mail.username", "")
					, sProperties.getJob().getProperty("seung.job.mail.password", "")
					, sProperties.getJob().getProperty("seung.job.mail.username", "")
					, "박종승"
					, ki0001.getTo()
					, ki0001.getCc()
					, ki0001.getSubject()
					, ki0001.getContent()
					);
			
			sResponse.success();
			
		} catch (Exception e) {
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	@Override
	public SResponse ki0100(SRequest sRequest, Ki0100 ki0100) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(ki0100.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(ki0100)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		SLinkedHashMap query = null;
		List<SLinkedHashMap> f_ki_ki010000 = null;
		String error_message = "";
		try {
			
			log.info("({}) request={}", sResponse.getRequest_code(), ki0100.toJsonString());
			
			query = new SLinkedHashMap()
					.add("request", ki0100.toJsonString())
					;
			
			f_ki_ki010000 = sMapperI.selectList(
					"f_ki_ki010000"
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
			if(f_ki_ki010000.isEmpty()) {
				sResponse.putResponse("ki0100_S", 0);
				sResponse.putResponse("ki0100_SL", new ArrayList<>());
			} else {
				sResponse.putResponse("ki0100_S", f_ki_ki010000.size());
				sResponse.putResponse("ki0100_SL", f_ki_ki010000);
			}
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SResponse ki0110(SRequest sRequest, Ki0110 ki0110) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(ki0110.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(ki0110)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		SLinkedHashMap query = null;
		SLinkedHashMap f_ki_ki011000 = null;
		String error_message = "";
		try {
			
			log.info("({}) request={}", sResponse.getRequest_code(), ki0110.toJsonString());
			
			query = new SLinkedHashMap()
					.add("request", ki0110.toJsonString())
					;
			
			f_ki_ki011000 = sMapperI.selectOne(
					"f_ki_ki011000"
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
			sResponse.getResponse().putAll(f_ki_ki011000 == null ? new SLinkedHashMap() : f_ki_ki011000);
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	@Override
	public SResponse ki0120(SRequest sRequest, Ki0120 ki0120) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(ki0120.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(ki0120)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		SLinkedHashMap query = null;
		List<SLinkedHashMap> f_ki_ki012000 = null;
		String error_message = "";
		try {
			
			log.info("({}) request={}", sResponse.getRequest_code(), ki0120.toJsonString());
			
			query = new SLinkedHashMap()
					.add("request", ki0120.toJsonString())
					;
			
			f_ki_ki012000 = sMapperI.selectList(
					"f_ki_ki012000"
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
			if(f_ki_ki012000.isEmpty()) {
				sResponse.putResponse("ki0120_S", 0);
				sResponse.putResponse("ki0120_SL", new ArrayList<>());
			} else {
				sResponse.putResponse("ki0120_S", f_ki_ki012000.size());
				sResponse.putResponse("ki0120_SL", f_ki_ki012000);
			}
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	@Override
	public SResponse ki0121(SRequest sRequest, Ki0121 ki0121) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(ki0121.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(ki0121)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		SLinkedHashMap query = null;
		int ki0121_UR = 0;
		String error_message = "";
		try {
			
			log.info("({}) request={}", sResponse.getRequest_code(), ki0121.toJsonString());
			
			query = new SLinkedHashMap(ki0121)
					.add("request", ki0121.toJsonString())
					;
			
			ki0121_UR = sMapperI.update(
					"ki_ki0121"
					, ki0121
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
			sResponse.putResponse("ki0121_UR", ki0121_UR);
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public SResponse ki0200(SRequest sRequest, Ki0200 ki0200) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(ki0200.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(ki0200)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		SLinkedHashMap query = null;
		List<SLinkedHashMap> ki0200_SL = null;
		String error_message = "";
		try {
			
			log.info("({}) request={}", sResponse.getRequest_code(), ki0200.toJsonString());
			
			query = new SLinkedHashMap()
					.add("request", ki0200.toJsonString())
					;
			
//			ki0200_SL = sMapperI.selectList(
//					"ki0200_SL"
//					, query
//					);
			ki0200_SL = sMapperI.selectList(
					"ki_ki020000"
					, query
					);
			if(!ki0200_SL.isEmpty()) {
				for(SLinkedHashMap ki0200_SR : ki0200_SL) {
					ki0200_SR.put("item_data", new ObjectMapper().readValue(ki0200_SR.getString("item_data", "[]"), List.class));
				}
			}
			
			sResponse.success();
			
		} catch (Exception e) {
			log.info("({}) query={}", sResponse.getRequest_code(), ki0200.toJsonString(), e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
			sResponse.setError_message(error_message);
			if(ki0200_SL.isEmpty()) {
				sResponse.putResponse("ki0200_S", 0);
				sResponse.putResponse("ki0200_SL", new ArrayList<>());
			} else {
				sResponse.putResponse("ki0200_S", ki0200_SL.size());
				sResponse.putResponse("ki0200_SL", ki0200_SL);
			}
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	@SuppressWarnings({ "resource", "unchecked" })
	@Override
	public SResponse ki0210(SRequest sRequest, Ki0210 ki0210) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(ki0210.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(ki0210)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		new File("var/k0210").mkdirs();
		
		String error_message = "";
		try {
			
			log.info("({}) request={}", sResponse.getRequest_code(), ki0210.toJsonString());
			
			File file = new File(String.format("var/k0210/%s.xlsx", ki0210.getRequest_code()));
			
			List<SLinkedHashMap> ki_ki021000 = sMapperI.selectList("ki_ki021000");
			List<String> shcodes = new ArrayList<>();
			List<String> hnames = new ArrayList<>();
			shcodes.add("");
			hnames.add("");
			for(int i = 0; i < ki_ki021000.size(); i++) {
				shcodes.add(ki_ki021000.get(i).getString("shcode"));
				hnames.add(ki_ki021000.get(i).getString("hname"));
			}
			List<SLinkedHashMap> ki_ki021010 = sMapperI.selectList("ki_ki021010");
			List<String> dates = new ArrayList<>();
			for(int i = 0; i < ki_ki021010.size(); i++) {
				dates.add(ki_ki021010.get(i).getString("trdd"));
			}
			List<SLinkedHashMap> ki_ki021020 = sMapperI.selectList("ki_ki021020");
			SLinkedHashMap closes = new SLinkedHashMap();
			for(SLinkedHashMap close : ki_ki021020) {
				closes.put(close.getString("key"), close.getString("close"));
			}
			
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
			XSSFSheet xssfSheet = xssfWorkbook.createSheet("ETF");
			XSSFRow xssfRow = null;
			XSSFCell xssfCell = null;
			int rowNo = 0;
			
			xssfRow = xssfSheet.createRow(rowNo++);
			for(int cellNo = 0; cellNo < shcodes.size(); cellNo++) {
				xssfCell = xssfRow.createCell(cellNo);
				xssfCell.setCellValue(shcodes.get(cellNo));
			}
			
			xssfRow = xssfSheet.createRow(rowNo++);
			for(int cellNo = 0; cellNo < hnames.size(); cellNo++) {
				xssfCell = xssfRow.createCell(cellNo);
				xssfCell.setCellValue(hnames.get(cellNo));
			}
			
			for(int i = 0; i < dates.size(); i++) {
				xssfRow = xssfSheet.createRow(rowNo++);
				xssfCell = xssfRow.createCell(0);
				xssfCell.setCellValue(dates.get(i));
				for(int ii = 1; ii < shcodes.size(); ii++) {
					if(closes.containsKey(shcodes.get(ii) + dates.get(i))) {
						xssfCell = xssfRow.createCell(ii);
						xssfCell.setCellValue(closes.getString(shcodes.get(ii) + dates.get(i)));
					}
				}
			}
			
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			xssfWorkbook.write(fileOutputStream);
			fileOutputStream.close();
			xssfWorkbook.close();
			
			sResponse.success();
			
		} catch (Exception e) {
			log.info("({}) ", sResponse.getRequest_code(), e);
			error_message = ExceptionUtils.getStackTrace(e);
			if(error_message == null || "".equals(error_message)) {
				error_message = "" + e;
			}
		} finally {
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	@Override
	public SResponse ki0300(SRequest sRequest, Ki0300 ki0300) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(ki0300.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(ki0300)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		SLinkedHashMap query = null;
		List<SLinkedHashMap> f_ki_ki030000 = null;
		String error_message = "";
		try {
			
			log.info("({}) request={}", sResponse.getRequest_code(), ki0300.toJsonString());
			
			query = new SLinkedHashMap()
					.add("request", ki0300.toJsonString())
					;
			
			f_ki_ki030000 = sMapperI.selectList(
					"f_ki_ki030000"
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
			if(f_ki_ki030000.isEmpty()) {
				sResponse.putResponse("ki0300_S", 0);
				sResponse.putResponse("ki0300_SL", new ArrayList<>());
			} else {
				sResponse.putResponse("ki0300_S", f_ki_ki030000.size());
				sResponse.putResponse("ki0300_SL", f_ki_ki030000);
			}
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
	@Override
	public SResponse ki0310(SRequest sRequest, Ki0310 ki0310) {
		
		log.debug("run");
		
		SResponse sResponse = SResponse.builder()
				.request_code(ki0310.getRequest_code())
				.request_time(sRequest.getRequest_time())
				.request(ki0310)
				.build()
				;
		
		log.info("({}) ((START))", sResponse.getRequest_code());
		
		SLinkedHashMap query = null;
		SLinkedHashMap f_ki_ki011000 = null;
		List<SLinkedHashMap> f_ki_ki031000 = null;
		String error_message = "";
		try {
			
			log.info("({}) request={}", sResponse.getRequest_code(), ki0310.toJsonString());
			
			query = new SLinkedHashMap()
					.add("request", ki0310.toJsonString())
					;
			
			f_ki_ki011000 = sMapperI.selectOne(
					"f_ki_ki011000"
					, query
					);
			
			log.info(query.toJsonString(true));
			f_ki_ki031000 = sMapperI.selectList(
					"f_ki_ki031000"
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
			if(f_ki_ki011000 != null) {
				sResponse.putResponse("trdd", f_ki_ki011000.getString("trdd_to", ""));
				sResponse.putResponse("trdd_no", f_ki_ki011000.getString("trdd_no_max", ""));
			} else {
				sResponse.putResponse("trdd", "");
				sResponse.putResponse("trdd_no", "");
			}
			if(f_ki_ki031000.isEmpty()) {
				sResponse.putResponse("ki0310_S", 0);
				sResponse.putResponse("ki0310_SL", new ArrayList<>());
			} else {
				sResponse.putResponse("ki0310_S", f_ki_ki031000.size());
				sResponse.putResponse("ki0310_SL", f_ki_ki031000);
			}
		}
		
		log.info("({}) ((END))", sResponse.getRequest_code());
		sResponse.done();
		return sResponse;
	}
	
}
