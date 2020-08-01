package seung.spring.boot.conf.quartz.rest;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import seung.spring.boot.conf.quartz.rest.service.SQuartzS;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;

@RestController
@Slf4j
public class SQuartzC {

//	@Resource(name = "sQuartzS")
//	private SQuartzS sQuartzS;
//	
//	@RequestMapping(value = {"/rest/quartz/quartz0101"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
//	public String quartz0101(
//			Model model
//			, @ApiIgnore SRequest sRequest
//			, @ApiParam(
//					value = "request code"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "request_code_test"
//					) @RequestParam String request_code
//			) throws Exception {
//		
//		log.debug("run");
//		
//		model.addAttribute("no-wrap", sQuartzS.quartz0101(sRequest));
//		
//		return "jsonView";
//	}
//	
//	@ApiOperation(response = SResponse.class, value = "JOB(SIMPLE) ADD", notes = "QUARTZ")
//	@ApiResponses(value = {
//			@ApiResponse(
//					code = 200
//					, message = ""
//					, examples = @Example(value = {
//							@ExampleProperty(
//									mediaType = "application/json"
//									, value = ""
//									)})
//					)
//	})
//	@RequestMapping(value = {"/rest/quartz/quartz0111"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
//	public String quartz0111(
//			Model model
//			, @ApiIgnore SRequest sRequest
//			, @ApiParam(
//					value = "request code"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "request_code_test"
//					) @RequestParam String request_code
//			, @ApiParam(
//					value = "job group"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "job_group_test"
//					) @RequestParam String job_group
//			, @ApiParam(
//					value = "job name"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "job_name_test1"
//					) @RequestParam String job_name
//			, @ApiParam(
//					value = "job description"
//					, type = "string"
//					, allowEmptyValue = true
//					, allowMultiple = false
//					, example = "test simple job"
//					) @RequestParam String dscr
//			, @ApiParam(
//					value = "job class name"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = ""
//					) @RequestParam String clss
//			, @ApiParam(
//					value = "start at (format: yyyy-MM-dd HH:mm:ss)"
//					, type = "string"
//					, allowEmptyValue = true
//					, allowMultiple = false
//					, example = ""
//					) @RequestParam String date_from
//			, @ApiParam(
//					value = "end at (format: yyyy-MM-dd HH:mm:ss)"
//					, type = "string"
//					, allowEmptyValue = true
//					, allowMultiple = false
//					, example = ""
//					) @RequestParam String date_to
//			, @ApiParam(
//					value = "interval in milliseconds"
//					, type = "long"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "60000"
//					) @RequestParam long intv
//			) throws Exception {
//		
//		log.debug("run");
//		
//		model.addAttribute("no-wrap", sQuartzS.quartz0111(sRequest));
//		
//		return "jsonView";
//	}
//	
//	@ApiOperation(response = SResponse.class, value = "JOB(CRON) ADD", notes = "QUARTZ")
//	@ApiResponses(value = {
//			@ApiResponse(
//					code = 200
//					, message = ""
//					, examples = @Example(value = {
//							@ExampleProperty(
//									mediaType = "application/json"
//									, value = ""
//									)})
//					)
//	})
//	@RequestMapping(value = {"/rest/quartz/quartz0112"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
//	public String quartz0112(
//			Model model
//			, @ApiIgnore SRequest sRequest
//			, @ApiParam(
//					value = "request code"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "request_code_test"
//					) @RequestParam String request_code
//			, @ApiParam(
//					value = "job group"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "job_group_test"
//					) @RequestParam String job_group
//			, @ApiParam(
//					value = "job name"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "job_name_test1"
//					) @RequestParam String job_name
//			, @ApiParam(
//					value = "job description"
//					, type = "string"
//					, allowEmptyValue = true
//					, allowMultiple = false
//					, example = "test simple job"
//					) @RequestParam String dscr
//			, @ApiParam(
//					value = "job class name"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = ""
//					) @RequestParam String clss
//			, @ApiParam(
//					value = "start at (format: yyyy-MM-dd HH:mm:ss)"
//					, type = "string"
//					, allowEmptyValue = true
//					, allowMultiple = false
//					, example = ""
//					) @RequestParam String date_from
//			, @ApiParam(
//					value = "end at (format: yyyy-MM-dd HH:mm:ss)"
//					, type = "string"
//					, allowEmptyValue = true
//					, allowMultiple = false
//					, example = ""
//					) @RequestParam String date_to
//			, @ApiParam(
//					value = "cron expression"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "0,10,20,30,40,50 * * ? * * *"
//					) @RequestParam String cron_expr
//			) throws Exception {
//		
//		log.debug("run");
//		
//		model.addAttribute("no-wrap", sQuartzS.quartz0112(sRequest));
//		
//		return "jsonView";
//	}
//	
//	@ApiOperation(response = SResponse.class, value = "JOB(SIMPLE) EDIT", notes = "QUARTZ")
//	@ApiResponses(value = {
//			@ApiResponse(
//					code = 200
//					, message = ""
//					, examples = @Example(value = {
//							@ExampleProperty(
//									mediaType = "application/json"
//									, value = ""
//									)})
//					)
//	})
//	@RequestMapping(value = {"/rest/quartz/quartz0121"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
//	public String quartz0121(
//			Model model
//			, @ApiIgnore SRequest sRequest
//			, @ApiParam(
//					value = "request code"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "request_code_test"
//					) @RequestParam String request_code
//			, @ApiParam(
//					value = "job group"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "job_group_test"
//					) @RequestParam String job_group
//			, @ApiParam(
//					value = "job name"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "job_name_test1"
//					) @RequestParam String job_name
//			, @ApiParam(
//					value = "start at (format: yyyy-MM-dd HH:mm:ss)"
//					, type = "string"
//					, allowEmptyValue = true
//					, allowMultiple = false
//					, example = ""
//					) @RequestParam String date_from
//			, @ApiParam(
//					value = "end at (format: yyyy-MM-dd HH:mm:ss)"
//					, type = "string"
//					, allowEmptyValue = true
//					, allowMultiple = false
//					, example = ""
//					) @RequestParam String date_to
//			, @ApiParam(
//					value = "interval in milliseconds"
//					, type = "long"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "60000"
//					) @RequestParam long intv
//			) throws Exception {
//		
//		log.debug("run");
//		
//		model.addAttribute("no-wrap", sQuartzS.quartz0121(sRequest));
//		
//		return "jsonView";
//	}
//	
//	@ApiOperation(response = SResponse.class, value = "JOB(CRON) EDIT", notes = "QUARTZ")
//	@ApiResponses(value = {
//			@ApiResponse(
//					code = 200
//					, message = ""
//					, examples = @Example(value = {
//							@ExampleProperty(
//									mediaType = "application/json"
//									, value = ""
//									)})
//					)
//	})
//	@RequestMapping(value = {"/rest/quartz/quartz0122"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
//	public String quartz0122(
//			Model model
//			, @ApiIgnore SRequest sRequest
//			, @ApiParam(
//					value = "request code"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "request_code_test"
//					) @RequestParam String request_code
//			, @ApiParam(
//					value = "job group"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "job_group_test"
//					) @RequestParam String job_group
//			, @ApiParam(
//					value = "job name"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "job_name_test2"
//					) @RequestParam String job_name
//			, @ApiParam(
//					value = "start at (format: yyyy-MM-dd HH:mm:ss)"
//					, type = "string"
//					, allowEmptyValue = true
//					, allowMultiple = false
//					, example = ""
//					) @RequestParam String date_from
//			, @ApiParam(
//					value = "end at (format: yyyy-MM-dd HH:mm:ss)"
//					, type = "string"
//					, allowEmptyValue = true
//					, allowMultiple = false
//					, example = ""
//					) @RequestParam String date_to
//			, @ApiParam(
//					value = "cron expression"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "0,10,20,30,40,50 * * ? * * *"
//					) @RequestParam String cron_expr
//			) throws Exception {
//		
//		log.debug("run");
//		
//		model.addAttribute("no-wrap", sQuartzS.quartz0122(sRequest));
//		
//		return "jsonView";
//	}
//	
//	@ApiOperation(response = SResponse.class, value = "JOB DELETE", notes = "QUARTZ")
//	@ApiResponses(value = {
//			@ApiResponse(
//					code = 200
//					, message = ""
//					, examples = @Example(value = {
//							@ExampleProperty(
//									mediaType = "application/json"
//									, value = ""
//									)})
//					)
//	})
//	@RequestMapping(value = {"/rest/quartz/quartz0131"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
//	public String quartz0131(
//			Model model
//			, @ApiIgnore SRequest sRequest
//			, @ApiParam(
//					value = "request code"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "request_code_test"
//					) @RequestParam String request_code
//			, @ApiParam(
//					value = "job group"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "job_group_test"
//					) @RequestParam String job_group
//			, @ApiParam(
//					value = "job name"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "job_name_test0"
//					) @RequestParam String job_name
//			) throws Exception {
//		
//		log.debug("run");
//		
//		model.addAttribute("no-wrap", sQuartzS.quartz0131(sRequest));
//		
//		return "jsonView";
//	}
//	
//	@ApiOperation(response = SResponse.class, value = "JOB RUN", notes = "QUARTZ")
//	@ApiResponses(value = {
//			@ApiResponse(
//					code = 200
//					, message = ""
//					, examples = @Example(value = {
//							@ExampleProperty(
//									mediaType = "application/json"
//									, value = ""
//									)})
//					)
//	})
//	@RequestMapping(value = {"/rest/quartz/quartz0141"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
//	public String quartz0141(
//			Model model
//			, @ApiIgnore SRequest sRequest
//			, @ApiParam(
//					value = "request code"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "request_code_test"
//					) @RequestParam String request_code
//			, @ApiParam(
//					value = "job group"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "job_group_test"
//					) @RequestParam String job_group
//			, @ApiParam(
//					value = "job name"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "job_name_test0"
//					) @RequestParam String job_name
//			) throws Exception {
//		
//		log.debug("run");
//		
//		model.addAttribute("no-wrap", sQuartzS.quartz0141(sRequest));
//		
//		return "jsonView";
//	}
//	
//	@ApiOperation(response = SResponse.class, value = "JOB RUN ONE TIME", notes = "QUARTZ")
//	@ApiResponses(value = {
//			@ApiResponse(
//					code = 200
//					, message = ""
//					, examples = @Example(value = {
//							@ExampleProperty(
//									mediaType = "application/json"
//									, value = ""
//									)})
//					)
//	})
//	@RequestMapping(value = {"/rest/quartz/quartz0142"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
//	public String quartz0142(
//			Model model
//			, @ApiIgnore SRequest sRequest
//			, @ApiParam(
//					value = "request code"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "request_code_test"
//					) @RequestParam String request_code
//			, @ApiParam(
//					value = "job group"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "job_group_test"
//					) @RequestParam String job_group
//			, @ApiParam(
//					value = "job name"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "job_name_test0"
//					) @RequestParam String job_name
//			) throws Exception {
//		
//		log.debug("run");
//		
//		model.addAttribute("no-wrap", sQuartzS.quartz0142(sRequest));
//		
//		return "jsonView";
//	}
//	
//	@ApiOperation(response = SResponse.class, value = "JOB PAUSE", notes = "QUARTZ")
//	@ApiResponses(value = {
//			@ApiResponse(
//					code = 200
//					, message = ""
//					, examples = @Example(value = {
//							@ExampleProperty(
//									mediaType = "application/json"
//									, value = ""
//									)})
//					)
//	})
//	@RequestMapping(value = {"/rest/quartz/quartz0143"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
//	public String quartz0143(
//			Model model
//			, @ApiIgnore SRequest sRequest
//			, @ApiParam(
//					value = "request code"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "request_code_test"
//					) @RequestParam String request_code
//			, @ApiParam(
//					value = "job group"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "job_group_test"
//					) @RequestParam String job_group
//			, @ApiParam(
//					value = "job name"
//					, type = "string"
//					, allowEmptyValue = false
//					, allowMultiple = false
//					, example = "job_name_test0"
//					) @RequestParam String job_name
//			) throws Exception {
//		
//		log.debug("run");
//		
//		model.addAttribute("no-wrap", sQuartzS.quartz0143(sRequest));
//		
//		return "jsonView";
//	}
	
}
