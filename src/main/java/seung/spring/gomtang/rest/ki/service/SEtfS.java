package seung.spring.gomtang.rest.ki.service;

import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;
import seung.spring.gomtang.rest.ki.util.Etf0101;
import seung.spring.gomtang.rest.ki.util.Etf0102;
import seung.spring.gomtang.rest.ki.util.Etf0103;
import seung.spring.gomtang.rest.ki.util.Etf0111;
import seung.spring.gomtang.rest.ki.util.Etf0112;
import seung.spring.gomtang.rest.ki.util.Etf0113;

public interface SEtfS {

//	public SResponse etf0000(SRequest sRequest);
//	
//	public SResponse etf0001(SRequest sRequest);
	
	public SResponse etf0101(SRequest sRequest, Etf0101 etf0101);
	
	public SResponse etf0102(SRequest sRequest, Etf0102 etf0102);
	
	public SResponse etf0103(SRequest sRequest, Etf0103 etf0103);
	
	public SResponse etf0111(SRequest sRequest, Etf0111 etf0111);
	
	public SResponse etf0112(SRequest sRequest, Etf0112 etf0112);
	
	public SResponse etf0113(SRequest sRequest, Etf0113 etf0113);
	
//	public SResponse etf9001(SRequest sRequest);
//	
//	public SResponse etf9002(SRequest sRequest);
	
}
