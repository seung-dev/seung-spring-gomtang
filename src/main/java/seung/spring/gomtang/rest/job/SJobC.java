package seung.spring.gomtang.rest.job;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import seung.spring.gomtang.rest.job.service.SJobS;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "JOB REST SERVICE", value = "SJobC")
@Slf4j
@Controller
public class SJobC {

	@Resource(name = "sJobS")
	private SJobS sJobS;
	
	@ApiOperation(response = SResponse.class, value = "JOB EXECUTE - KIWOOM sr33333", notes = "ETF")
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
	@RequestMapping(value = {"/rest/job/sr33333"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String sr33333(
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
		
		model.addAttribute("no-wrap", sJobS.sr33333(sRequest));
		
		return "jsonView";
	}
	
	@ApiOperation(response = SResponse.class, value = "JOB EXECUTE - KIWOOM sr66666", notes = "ETF")
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
	@RequestMapping(value = {"/rest/job/sr66666"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String sr66666(
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
		
		model.addAttribute("no-wrap", sJobS.sr66666(sRequest));
		
		return "jsonView";
	}
	
	@ApiOperation(response = SResponse.class, value = "JOB EXECUTE - KIWOOM sr99999", notes = "ETF")
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
	@RequestMapping(value = {"/rest/job/sr99999"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String sr99999(
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
		
		model.addAttribute("no-wrap", sJobS.sr99999(sRequest));
		
		return "jsonView";
	}
	
	@ApiOperation(response = SResponse.class, value = "JOB EXECUTE - KIWOOM kw10000", notes = "ETF")
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
	@RequestMapping(value = {"/rest/job/kw10000"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String kw10000(
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
		
		model.addAttribute("no-wrap", sJobS.kw10000(sRequest));
		
		return "jsonView";
	}
	
	@ApiOperation(response = SResponse.class, value = "JOB EXECUTE - KIWOOM tr10001", notes = "ETF")
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
	@RequestMapping(value = {"/rest/job/tr10001"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String tr10001(
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
		
		model.addAttribute("no-wrap", sJobS.tr10001(sRequest));
		
		return "jsonView";
	}
	
	@ApiOperation(response = SResponse.class, value = "JOB EXECUTE - KIWOOM tr10001", notes = "ETF")
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
	@RequestMapping(value = {"/rest/job/tr10081"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String tr10081(
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
		
		model.addAttribute("no-wrap", sJobS.tr10081(sRequest));
		
		return "jsonView";
	}
	
	@ApiOperation(response = SResponse.class, value = "JOB EXECUTE - KIWOOM tr40005", notes = "ETF")
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
	@RequestMapping(value = {"/rest/job/tr40005"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String tr40005(
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
		
		model.addAttribute("no-wrap", sJobS.tr40005(sRequest));
		
		return "jsonView";
	}
	
	@ApiOperation(response = SResponse.class, value = "JOB EXECUTE - NAVER n0101", notes = "ETF")
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
	@RequestMapping(value = {"/rest/job/n0101"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String n0101(
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
		
		model.addAttribute("no-wrap", sJobS.n0101(sRequest));
		
		return "jsonView";
	}
	
	@ApiOperation(response = SResponse.class, value = "JOB EXECUTE - NAVER n0102", notes = "ETF")
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
	@RequestMapping(value = {"/rest/job/n0102"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public String n0102(
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
		
		model.addAttribute("no-wrap", sJobS.n0102(sRequest));
		
		return "jsonView";
	}
	
}
