package seung.spring.gomtang.job;

/**
 * <pre>
 * job initialization
 * </pre>
 */
//@Slf4j
//@Component("sJobI")
public class SJobI {

//	@Resource(name = "sMapperI")
//	private SMapperI sMapperI;
//	
//	@Resource(name = "sQuartzH")
//	private SQuartzH sQuartzH;
//	
//	public void initJob() {
//		
//		log.info("run");
//		
//		List<SLinkedHashMap> cronJobs = new ArrayList<>();
////		cronJobs.add(
////				new SLinkedHashMap()
////				.add("group", "fin")
////				.add("name", "etfA")
////				.add("dscr", "ETF DATA 수집 - KIWOOM")
////				.add("clss", "seung.spring.gomtang.job.fin.SJobEtfA")
////				.add("dateFrom", "")
////				.add("dateTo", "")
////				.add("cronExpr", "0 10 16,18 ? * MON-FRI *")
////				);
////		cronJobs.add(
////				new SLinkedHashMap()
////				.add("group", "fin")
////				.add("name", "etfB")
////				.add("dscr", "ETF DATA 수집 - NAVER, DART")
////				.add("clss", "seung.spring.gomtang.job.fin.SJobEtfB")
////				.add("dateFrom", "")
////				.add("dateTo", "")
////				.add("cronExpr", "0 0 4 ? * MON-FRI *")
////				);
//		
//		for(SLinkedHashMap job : cronJobs) {
//			
//			try {
//				
//				if(0 == sMapperI.selectList("quartz0102", job).size()) {
//					sQuartzH.addCron(
//							job.getString("group", "")
//							, job.getString("name", "")
//							, job.getString("dscr", "")
//							, job.getString("clss", "")
//							, job.getString("dateFrom", "")
//							, job.getString("dateTo", "")
//							, job.getString("cronExpr", "")
//							);
//				}
//				
//			} catch (ClassNotFoundException e) {
//				log.error("Failed to add cron job.", e);
//			} catch (SchedulerException e) {
//				log.error("Failed to add cron job.", e);
//			} catch (SKimchiException e) {
//				log.error("Failed to add cron job.", e);
//			}
//			
//		}// end of cron jobs
//		
//	}
	
}
