package seung.spring.boot.conf.quartz;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import lombok.extern.slf4j.Slf4j;
import seung.spring.boot.conf.SProperties;
import seung.spring.boot.conf.quartz.support.SJobFactory;

@Slf4j
@Configuration
public class SConfQuartz {

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(
            ApplicationContext applicationContext
            , SProperties sProperties
            , @Qualifier("dataSourceI") DataSource dataSourceI
            ) {
        
        log.debug("run");
        
        SJobFactory sJobFactory = new SJobFactory();
        sJobFactory.setApplicationContext(applicationContext);
        
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setQuartzProperties(sProperties.getQuartz());
        schedulerFactoryBean.setDataSource(dataSourceI);
        schedulerFactoryBean.setJobFactory(sJobFactory);
        
        return schedulerFactoryBean;
        
    }
    
}
