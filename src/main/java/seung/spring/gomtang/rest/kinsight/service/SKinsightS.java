package seung.spring.gomtang.rest.kinsight.service;

import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;
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

public interface SKinsightS {

	SResponse ki0001(SRequest sRequest, Ki0001 ki0001);
	
	SResponse ki0100(SRequest sRequest, Ki0100 ki0100);
	SResponse ki0110(SRequest sRequest, Ki0110 ki0110);
	SResponse ki0120(SRequest sRequest, Ki0120 ki0120);
	SResponse ki0121(SRequest sRequest, Ki0121 ki0121);
	
	SResponse ki0200(SRequest sRequest, Ki0200 ki0200);
	SResponse ki0210(SRequest sRequest, Ki0210 ki0210);
	
	SResponse ki0300(SRequest sRequest, Ki0300 ki0300);
	SResponse ki0310(SRequest sRequest, Ki0310 ki0310);
	
	SResponse ki0420(SRequest sRequest, Ki0420 ki0420);
	SResponse ki0440(SRequest sRequest, Ki0440 ki0440);
	SResponse ki0450(SRequest sRequest, Ki0450 ki0450);
	
}
