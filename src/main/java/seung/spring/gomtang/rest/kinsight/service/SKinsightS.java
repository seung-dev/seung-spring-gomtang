package seung.spring.gomtang.rest.kinsight.service;

import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;
import seung.spring.gomtang.rest.kinsight.util.Ki0100;
import seung.spring.gomtang.rest.kinsight.util.Ki0110;
import seung.spring.gomtang.rest.kinsight.util.Ki0120;
import seung.spring.gomtang.rest.kinsight.util.Ki0300;
import seung.spring.gomtang.rest.kinsight.util.Ki0310;

public interface SKinsightS {

	SResponse ki0100(SRequest sRequest, Ki0100 ki0100);
	SResponse ki0110(SRequest sRequest, Ki0110 ki0110);
	SResponse ki0120(SRequest sRequest, Ki0120 ki0120);
	
	SResponse ki0300(SRequest sRequest, Ki0300 ki0300);
	SResponse ki0310(SRequest sRequest, Ki0310 ki0310);
	
}
