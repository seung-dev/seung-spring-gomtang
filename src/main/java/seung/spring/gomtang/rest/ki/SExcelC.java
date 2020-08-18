package seung.spring.gomtang.rest.ki;

import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;
import seung.spring.gomtang.rest.ki.service.SKiS;
import seung.spring.gomtang.rest.ki.util.Ki0201;

@Controller
@Slf4j
public class SExcelC {

	@Resource(name = "sKiS")
	private SKiS sKiS;
	
	@RequestMapping(value = {"/rest/ki/ki0190"}, method = {RequestMethod.POST}, produces = "application/json; charset=UTF-8")
	public ModelAndView ki0190(
			SRequest sRequest
			, Model model
			, @Valid @RequestBody Ki0201 ki0201
			) throws Exception {
		
		log.debug("run");
		
		return new ModelAndView("sXlsxView", sKiS.ki0190(sRequest, ki0201));
	}
	
}
