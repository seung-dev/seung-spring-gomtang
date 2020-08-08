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
import seung.spring.gomtang.rest.ki.service.SKiS;
import seung.spring.gomtang.rest.ki.util.Ki0201;

@RestController
@Slf4j
public class SKiC {

	@Resource(name = "sKiS")
	private SKiS sKiS;
	
	@RequestMapping(value = {"/rest/ki/ki0201"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public SResponse ki0201(
			SRequest sRequest
			, @Valid @RequestBody Ki0201 ki0201
			) throws Exception {
		
		log.debug("run");
		
		return sKiS.ki0201(sRequest, ki0201);
	}
	
}
