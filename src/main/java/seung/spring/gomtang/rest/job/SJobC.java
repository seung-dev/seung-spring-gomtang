package seung.spring.gomtang.rest.job;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import seung.spring.boot.conf.web.util.SReflect;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;
import seung.spring.gomtang.rest.job.service.SJobS;

@Slf4j
@RestController
public class SJobC {

	@Resource(name = "sJobS")
	private SJobS sJobS;
	
	@RequestMapping(value = {"/rest/job/sr33333"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse sr33333(
			SRequest sRequest
			, @Valid @RequestBody SReflect sReflect
			) throws Exception {
		
		log.debug("run");
		
		return sJobS.sr33333(sRequest, sReflect);
	}
	
	@RequestMapping(value = {"/rest/job/sr66666"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse sr66666(
			SRequest sRequest
			, @Valid @RequestBody SReflect sReflect
			) throws Exception {
		
		log.debug("run");
		
		return sJobS.sr66666(sRequest, sReflect);
	}
	
	@RequestMapping(value = {"/rest/job/sr99999"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse sr99999(
			SRequest sRequest
			, @Valid @RequestBody SReflect sReflect
			) throws Exception {
		
		log.debug("run");
		
		return sJobS.sr99999(sRequest, sReflect);
	}
	
	@RequestMapping(value = {"/rest/job/kw10000"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse kw10000(
			SRequest sRequest
			, @Valid @RequestBody SReflect sReflect
			) throws Exception {
		
		log.debug("run");
		
		return sJobS.kw10000(sRequest, sReflect);
	}
	
	@RequestMapping(value = {"/rest/job/tr10001"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse tr10001(
			SRequest sRequest
			, @Valid @RequestBody SReflect sReflect
			) throws Exception {
		
		log.debug("run");
		
		return sJobS.tr10001(sRequest, sReflect);
	}
	
	@RequestMapping(value = {"/rest/job/tr10081"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse tr10081(
			SRequest sRequest
			, @Valid @RequestBody SReflect sReflect
			) throws Exception {
		
		log.debug("run");
		
		return sJobS.tr10081(sRequest, sReflect);
	}
	
	@RequestMapping(value = {"/rest/job/tr40005"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse tr40005(
			SRequest sRequest
			, @Valid @RequestBody SReflect sReflect
			) throws Exception {
		
		log.debug("run");
		
		return sJobS.tr40005(sRequest, sReflect);
	}
	
	@RequestMapping(value = {"/rest/job/n0101"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse n0101(
			SRequest sRequest
			, @Valid @RequestBody SReflect sReflect
			) throws Exception {
		
		log.debug("run");
		
		return sJobS.n0101(sRequest, sReflect);
	}
	
	@RequestMapping(value = {"/rest/job/n0102"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse n0102(
			SRequest sRequest
			, @Valid @RequestBody SReflect sReflect
			) throws Exception {
		
		log.debug("run");
		
		return sJobS.n0102(sRequest, sReflect);
	}
	
}
