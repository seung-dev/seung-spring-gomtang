package seung.spring.gomtang.rest.ki.service;

import java.util.Map;

import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;
import seung.spring.gomtang.rest.ki.util.Ki0201;

public interface SKiS {

	public SResponse ki0201(SRequest sRequest, Ki0201 ki0201);
	
	public Map<String, ?> ki0190(SRequest sRequest, Ki0201 ki0201);
	
}
