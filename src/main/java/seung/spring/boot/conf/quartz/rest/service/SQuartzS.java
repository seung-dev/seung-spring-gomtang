package seung.spring.boot.conf.quartz.rest.service;

import seung.spring.boot.conf.web.util.SRequest;
import seung.spring.boot.conf.web.util.SResponse;

public interface SQuartzS {

	/**
	 * <pre>
	 * select job records
	 * </pre>
	 * 
	 * @param {@link SRequest}
	 * @return {@link SResponse}
	 */
	public SResponse quartz0101(SRequest sRequest);
	
	/**
	 * <pre>
	 * insert simple job
	 * </pre>
	 * 
	 * @param {@link SRequest}
	 * @return {@link SResponse}
	 */
	public SResponse quartz0111(SRequest sRequest);
	
	/**
	 * <pre>
	 * add cron job
	 * </pre>
	 * 
	 * @param {@link SRequest}
	 * @return {@link SResponse}
	 */
	public SResponse quartz0112(SRequest sRequest);
	
	/**
	 * <pre>
	 * update simple job
	 * </pre>
	 * 
	 * @param {@link SRequest}
	 * @return {@link SResponse}
	 */
	public SResponse quartz0121(SRequest sRequest);
	
	/**
	 * <pre>
	 * update cron job
	 * </pre>
	 * 
	 * @param {@link SRequest}
	 * @return {@link SResponse}
	 */
	public SResponse quartz0122(SRequest sRequest);
	
	/**
	 * <pre>
	 * delete job
	 * </pre>
	 * 
	 * @param {@link SRequest}
	 * @return {@link SResponse}
	 */
	public SResponse quartz0131(SRequest sRequest);
	
	/**
	 * <pre>
	 * run job
	 * </pre>
	 * 
	 * @param {@link SRequest}
	 * @return {@link SResponse}
	 */
	public SResponse quartz0141(SRequest sRequest);
	
	/**
	 * <pre>
	 * run job one time
	 * </pre>
	 * 
	 * @param {@link SRequest}
	 * @return {@link SResponse}
	 */
	public SResponse quartz0142(SRequest sRequest);
	
	/**
	 * <pre>
	 * pause job
	 * </pre>
	 * 
	 * @param {@link SRequest}
	 * @return {@link SResponse}
	 */
	public SResponse quartz0143(SRequest sRequest);
	
}
