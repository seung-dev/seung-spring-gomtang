package seung.spring.boot.conf.web;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import seung.java.kimchi.util.SCode;
import seung.spring.boot.conf.web.util.SReflect;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;

@Slf4j
@RestController
public class SController {

	@RequestMapping(value = {"/reflect"}, method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse reflect(
			SRequest sRequest
			, @Valid @RequestBody SReflect sReflect
			) throws Exception {
		
		log.debug("run");
		
		return SResponse.builder()
				.request_code(sReflect.getRequest_code())
				.error_code(SCode.SUCCESS)
				.request_time(sRequest.getRequest_time())
				.response_time(new Date().getTime())
				.request(sReflect)
				.build()
				;
	}
	
}
