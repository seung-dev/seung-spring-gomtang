package seung.spring.gomtang.rest.ki;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;
import seung.spring.gomtang.rest.ki.service.SEtfS;
import seung.spring.gomtang.rest.ki.util.Etf0101;
import seung.spring.gomtang.rest.ki.util.Etf0102;
import seung.spring.gomtang.rest.ki.util.Etf0103;
import seung.spring.gomtang.rest.ki.util.Etf0111;
import seung.spring.gomtang.rest.ki.util.Etf0112;
import seung.spring.gomtang.rest.ki.util.Etf0113;

@RestController
@Slf4j
public class SEtfC {

	@Resource(name = "sEtfS")
	private SEtfS sEtfS;
	
//	@RequestMapping(value = {"/rest/etf/etf0000"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
//	public String etf0000(
//			Model model
//			, @ApiIgnore SRequest sRequest
//			, @ApiParam(
//					value = "request code"
//					, type = "string"
//					, required = true
//					, example = "request_code_test"
//					) @RequestParam String request_code
//			) throws Exception {
//		
//		log.debug("run");
//		
//		model.addAttribute("no-wrap", sEtfS.etf0000(sRequest));
//		
//		return "jsonView";
//	}
	
//	@RequestMapping(value = {"/rest/etf/etf0001"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
//	public String etf0001(
//			Model model
//			, @ApiIgnore SRequest sRequest
//			, @ApiParam(
//					value = "request code"
//					, type = "string"
//					, required = true
//					, example = "request_code_test"
//					) @RequestParam String request_code
//			) throws Exception {
//		
//		log.debug("run");
//		
//		model.addAttribute("no-wrap", sEtfS.etf0001(sRequest));
//		
//		return "jsonView";
//	}
	
	@RequestMapping(value = {"/rest/etf/etf0101"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse etf0101(
			SRequest sRequest
			, @Valid @RequestBody Etf0101 etf0101
			) throws Exception {
		
		log.debug("run");
		
		return sEtfS.etf0101(sRequest, etf0101);
	}
	
	@RequestMapping(value = {"/rest/etf/etf0102"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse etf0102(
			SRequest sRequest
			, @Valid @RequestBody Etf0102 etf0102
			) throws Exception {
		
		log.debug("run");
		
		return sEtfS.etf0102(sRequest, etf0102);
	}
	
	@RequestMapping(value = {"/rest/etf/etf0103"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse etf0103(
			SRequest sRequest
			, @Valid @RequestBody Etf0103 etf0103
			) throws Exception {
		
		log.debug("run");
		
		return sEtfS.etf0103(sRequest, etf0103);
	}
	
	@RequestMapping(value = {"/rest/etf/etf0111"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse etf0111(
			SRequest sRequest
			, @Valid @RequestBody Etf0111 etf0111
			) throws Exception {
		
		log.debug("run");
		
		return sEtfS.etf0111(sRequest, etf0111);
	}
	
	@RequestMapping(value = {"/rest/etf/etf0112"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse etf0112(
			SRequest sRequest
			, @Valid @RequestBody Etf0112 etf0112
			) throws Exception {
		
		log.debug("run");
		
		return sEtfS.etf0112(sRequest, etf0112);
	}
	
	@RequestMapping(value = {"/rest/etf/etf0113"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse etf0113(
			SRequest sRequest
			, @Valid @RequestBody Etf0113 etf0113
			) throws Exception {
		
		log.debug("run");
		
		return sEtfS.etf0113(sRequest, etf0113);
	}
	
//	@RequestMapping(value = {"/rest/etf/etf9001"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
//	public String etf9001(
//			Model model
//			, @ApiIgnore SRequest sRequest
//			, @ApiParam(
//					value = "request code"
//					, type = "string"
//					, required = true
//					, example = "request_code_test"
//					) @RequestParam String request_code
//			) throws Exception {
//		
//		log.debug("run");
//		
//		model.addAttribute("no-wrap", sEtfS.etf9001(sRequest));
//		
//		return "jsonView";
//	}
//	
//	@RequestMapping(value = {"/rest/etf/etf9002"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
//	public String etf9002(
//			Model model
//			, @ApiIgnore SRequest sRequest
//			) throws Exception {
//		
//		log.debug("run");
//		
//		model.addAttribute("no-wrap", sEtfS.etf9002(sRequest));
//		
//		return "jsonView";
//	}
	
}
