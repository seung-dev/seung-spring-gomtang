package seung.spring.gomtang.rest.kinsight;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;
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
import seung.spring.gomtang.rest.kinsight.util.Ki0420;
import seung.spring.gomtang.rest.kinsight.util.Ki0440;
import seung.spring.gomtang.rest.kinsight.util.Ki0450;

@RestController
@Slf4j
public class SKinsightC {

	@Resource(name = "sKinsightS")
	private SKinsightS sKinsightS;
	
	@RequestMapping(value = {"/rest/ki/ki0001"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse ki0001(
			SRequest sRequest
			, @Valid @RequestBody Ki0001 ki0001
			) throws Exception {
		
		log.debug("run");
		
		return sKinsightS.ki0001(sRequest, ki0001);
	}
	
	@RequestMapping(value = {"/rest/ki/ki0100"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse ki0100(
			SRequest sRequest
			, @Valid @RequestBody Ki0100 ki0100
			) throws Exception {
		
		log.debug("run");
		
		return sKinsightS.ki0100(sRequest, ki0100);
	}
	
	@RequestMapping(value = {"/rest/ki/ki0110"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse ki0110(
			SRequest sRequest
			, @Valid @RequestBody Ki0110 ki0110
			) throws Exception {
		
		log.debug("run");
		
		return sKinsightS.ki0110(sRequest, ki0110);
	}
	
	@RequestMapping(value = {"/rest/ki/ki0120"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse ki0120(
			SRequest sRequest
			, @Valid @RequestBody Ki0120 ki0120
			) throws Exception {
		
		log.debug("run");
		
		return sKinsightS.ki0120(sRequest, ki0120);
	}
	
	@RequestMapping(value = {"/rest/ki/ki0121"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse ki0120(
			SRequest sRequest
			, @Valid @RequestBody Ki0121 ki0121
			) throws Exception {
		
		log.debug("run");
		
		return sKinsightS.ki0121(sRequest, ki0121);
	}
	
	@RequestMapping(value = {"/rest/ki/ki0200"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse ki0200(
			SRequest sRequest
			, @Valid @RequestBody Ki0200 ki0200
			) throws Exception {
		
		log.debug("run");
		
		return sKinsightS.ki0200(sRequest, ki0200);
	}
	
	@RequestMapping(value = {"/rest/ki/ki0210"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse ki0210(
			SRequest sRequest
			, @Valid @RequestBody Ki0210 ki0210
			) throws Exception {
		
		log.debug("run");
		
		return sKinsightS.ki0210(sRequest, ki0210);
	}
	
	@RequestMapping(value = {"/rest/ki/ki0300"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse ki0300(
			SRequest sRequest
			, @Valid @RequestBody Ki0300 ki0300
			) throws Exception {
		
		log.debug("run");
		
		return sKinsightS.ki0300(sRequest, ki0300);
	}
	
	@RequestMapping(value = {"/rest/ki/ki0310"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse ki0310(
			SRequest sRequest
			, @Valid @RequestBody Ki0310 ki0310
			) throws Exception {
		
		log.debug("run");
		
		return sKinsightS.ki0310(sRequest, ki0310);
	}
	
	@RequestMapping(value = {"/rest/ki/ki0420"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse ki0420(
			SRequest sRequest
			, @Valid @RequestBody Ki0420 ki0420
			) throws Exception {
		
		log.debug("run");
		
		return sKinsightS.ki0420(sRequest, ki0420);
	}
	
	@RequestMapping(value = {"/rest/ki/ki0440"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse ki0440(
			SRequest sRequest
			, @Valid @RequestBody Ki0440 ki0440
			) throws Exception {
		
		log.debug("run");
		
		return sKinsightS.ki0440(sRequest, ki0440);
	}
	
	@RequestMapping(value = {"/rest/ki/ki0450"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse ki0450(
			SRequest sRequest
			, @Valid @RequestBody Ki0450 ki0450
			) throws Exception {
		
		log.debug("run");
		
		return sKinsightS.ki0450(sRequest, ki0450);
	}
	
}
