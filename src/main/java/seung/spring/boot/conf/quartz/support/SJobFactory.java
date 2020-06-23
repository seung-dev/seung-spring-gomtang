package seung.spring.boot.conf.quartz.support;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

public class SJobFactory  extends SpringBeanJobFactory implements ApplicationContextAware {

	private AutowireCapableBeanFactory autowireCapableBeanFactory;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
	}
	
	@Override
	protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
		Object jobInstance = super.createJobInstance(bundle);
		autowireCapableBeanFactory.autowireBean(jobInstance);
		return jobInstance;
	}
	
}
