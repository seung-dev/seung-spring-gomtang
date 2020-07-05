package seung.spring.gomtang.rest.etf;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
import lombok.extern.slf4j.Slf4j;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;
import seung.spring.gomtang.rest.etf.service.SEtfS;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "ETF REST SERVICE", value = "SEtfC")
@Slf4j
@Controller
public class SEtfC {

	@Resource(name = "sEtfS")
	private SEtfS sEtfS;
	
	@ApiOperation(response = SResponse.class, value = "ETF API SCHEMA", notes = "ETF")
	@ApiResponses(value = {
			@ApiResponse(
					code = 200
					, message = ""
					, examples = @Example(value = {
							@ExampleProperty(
									mediaType = "application/json"
									, value = ""
									)})
					)
	})
	@CrossOrigin("*")
	@RequestMapping(value = {"/rest/etf/etf0000"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String etf0000(
			Model model
			, @ApiIgnore SRequest sRequest
			, @ApiParam(
					value = "request code"
					, type = "string"
					, required = true
					, example = "request_code_test"
					) @RequestParam String request_code
			) throws Exception {
		
		log.debug("run");
		
		model.addAttribute("no-wrap", sEtfS.etf0000(sRequest));
		
		return "jsonView";
	}
	
	@ApiOperation(response = SResponse.class, value = "ETF API OPTIONS", notes = "ETF")
	@ApiResponses(value = {
			@ApiResponse(
					code = 200
					, message = ""
					, examples = @Example(value = {
							@ExampleProperty(
									mediaType = "application/json"
									, value = ""
									)})
					)
	})
	@CrossOrigin("*")
	@RequestMapping(value = {"/rest/etf/etf0001"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String etf0001(
			Model model
			, @ApiIgnore SRequest sRequest
			, @ApiParam(
					value = "request code"
					, type = "string"
					, required = true
					, example = "request_code_test"
					) @RequestParam String request_code
			) throws Exception {
		
		log.debug("run");
		
		model.addAttribute("no-wrap", sEtfS.etf0001(sRequest));
		
		return "jsonView";
	}
	
	@ApiOperation(response = SResponse.class, value = "ETF OVERVIEW", notes = "ETF")
	@ApiResponses(value = {
			@ApiResponse(
					code = 200
					, message = ""
					, examples = @Example(value = {
							@ExampleProperty(
									mediaType = "application/json"
									, value = ""
									)})
					)
	})
	@CrossOrigin("*")
	@RequestMapping(value = {"/rest/etf/etf0101"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String etf0101(
			Model model
			, @ApiIgnore SRequest sRequest
			, @ApiParam(
					value = "request code"
					, type = "string"
					, required = true
					, example = "request_code_test"
					) @RequestParam String request_code
			) throws Exception {
		
		log.debug("run");
		
		model.addAttribute("no-wrap", sEtfS.etf0101(sRequest));
		
		return "jsonView";
	}
	
	@ApiOperation(response = SResponse.class, value = "ETF TRADING DATE", notes = "ETF")
	@ApiResponses(value = {
			@ApiResponse(
					code = 200
					, message = ""
					, examples = @Example(value = {
							@ExampleProperty(
									mediaType = "application/json"
									, value = ""
									)})
					)
	})
	@CrossOrigin("*")
	@RequestMapping(value = {"/rest/etf/etf0102"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String etf0102(
			Model model
			, @ApiIgnore SRequest sRequest
			, @ApiParam(
					value = "request code"
					, type = "string"
					, required = true
					, example = "request_code_test"
					) @RequestParam String request_code
			) throws Exception {
		
		log.debug("run");
		
		model.addAttribute("no-wrap", sEtfS.etf0102(sRequest));
		
		return "jsonView";
	}
	
	@ApiOperation(response = SResponse.class, value = "ETF ITEM LIST", notes = "ETF")
	@ApiResponses(value = {
			@ApiResponse(
					code = 200
					, message = ""
					, examples = @Example(value = {
							@ExampleProperty(
									mediaType = "application/json"
									, value = ""
									)})
					)
	})
	@CrossOrigin("*")
	@RequestMapping(value = {"/rest/etf/etf0103"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String etf0103(
			Model model
			, @ApiIgnore SRequest sRequest
			, @ApiParam(
					value = "request code"
					, type = "string"
					, required = true
					, example = "request_code_test"
					) @RequestParam String request_code
			) throws Exception {
		
		log.debug("run");
		
		model.addAttribute("no-wrap", sEtfS.etf0103(sRequest));
		
		return "jsonView";
	}
	
	@ApiOperation(response = SResponse.class, value = "ETF HISTORY", notes = "ETF")
	@ApiResponses(value = {
			@ApiResponse(
					code = 200
					, message = ""
					, examples = @Example(value = {
							@ExampleProperty(
									mediaType = "application/json"
									, value = ""
									)})
					)
	})
	@CrossOrigin("*")
	@RequestMapping(value = {"/rest/etf/etf0111"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String etf0111(
			Model model
			, @ApiIgnore SRequest sRequest
			, @ApiParam(
					value = "request code"
					, type = "string"
					, required = true
					, example = "request_code_test"
					) @RequestParam String request_code
			) throws Exception {
		
		log.debug("run");
		
		model.addAttribute("no-wrap", sEtfS.etf0111(sRequest));
		
		return "jsonView";
	}
	
	@ApiOperation(response = SResponse.class, value = "ETF DETAILS - SOURCE: NAVER", notes = "ETF")
	@ApiResponses(value = {
			@ApiResponse(
					code = 200
					, message = ""
					, examples = @Example(value = {
							@ExampleProperty(
									mediaType = "application/json"
									, value = ""
									)})
					)
	})
	@CrossOrigin("*")
	@RequestMapping(value = {"/rest/etf/etf0112"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String etf0112(
			Model model
			, @ApiIgnore SRequest sRequest
			, @ApiParam(
					value = "request code"
					, type = "string"
					, required = true
					, example = "request_code_test"
					) @RequestParam String request_code
			, @ApiParam(
					value = "item_code"
					, type = "string"
					, required = true
					, example = "069500"
					) @RequestParam String item_code
			) throws Exception {
		
		log.debug("run");
		
		model.addAttribute("no-wrap", sEtfS.etf0112(sRequest));
		
		return "jsonView";
	}
	
	@ApiOperation(response = SResponse.class, value = "ETF CU구성목록 - SOURCE: NAVER", notes = "ETF")
	@ApiResponses(value = {
			@ApiResponse(
					code = 200
					, message = ""
					, examples = @Example(value = {
							@ExampleProperty(
									mediaType = "application/json"
									, value = ""
									)})
					)
	})
	@CrossOrigin("*")
	@RequestMapping(value = {"/rest/etf/etf0113"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String etf0113(
			Model model
			, @ApiIgnore SRequest sRequest
			, @ApiParam(
					value = "request code"
					, type = "string"
					, required = true
					, example = "request_code_test"
					) @RequestParam String request_code
			, @ApiParam(
					value = "item_code"
					, type = "string"
					, required = true
					, example = "069500"
					) @RequestParam String item_code
			) throws Exception {
		
		log.debug("run");
		
		model.addAttribute("no-wrap", sEtfS.etf0113(sRequest));
		
		return "jsonView";
	}
	
	@ApiOperation(response = SResponse.class, value = "ETF API SCHEMA", notes = "ETF")
	@ApiResponses(value = {
			@ApiResponse(
					code = 200
					, message = ""
					, examples = @Example(value = {
							@ExampleProperty(
									mediaType = "application/json"
									, value = ""
									)})
					)
	})
	@RequestMapping(value = {"/rest/etf/etf9001"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String etf9001(
			Model model
			, @ApiIgnore SRequest sRequest
			, @ApiParam(
					value = "request code"
					, type = "string"
					, required = true
					, example = "request_code_test"
					) @RequestParam String request_code
			, @ApiParam(
					value = "recent trading date"
					, type = "string"
					, required = true
					, example = "20200601"
					) @RequestParam String trdd_last
			, @ApiParam(
					value = "run directory"
					, type = "string"
					, required = true
					, example = "a"
					) @RequestParam String dir_run
			) throws Exception {
		
		log.debug("run");
		
		model.addAttribute("no-wrap", sEtfS.etf9001(sRequest));
		
		return "jsonView";
	}
	
}
