package seung.spring.boot.conf.quartz;

import java.text.ParseException;
import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Component;

@Component(value = "sQuartzFactory")
public class SQuartzFactory {

    public JobDetail jobDetail(
            ApplicationContext applicationContext
            , String group
            , String name
            , Class<? extends QuartzJobBean> jobClass
            , boolean durability
            , JobDataMap jobDataMap
            ) {
        
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        
        factoryBean.setApplicationContext(applicationContext);
        factoryBean.setJobClass(jobClass);
        factoryBean.setDurability(durability);
        factoryBean.setGroup(group);
        factoryBean.setName(name);
        factoryBean.setJobDataMap(jobDataMap);
        factoryBean.afterPropertiesSet();
        
        return factoryBean.getObject();
    }
    
    /**
     * <pre>
     * SimpleTrigger.MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY = -1
     * SimpleTrigger.MISFIRE_INSTRUCTION_SMART_POLICY = 0
     * SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW = 1
     * SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_EXISTING_REPEAT_COUNT = 2
     * SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NOW_WITH_REMAINING_REPEAT_COUNT = 3
     * SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT = 4
     * SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_EXISTING_COUNT = 5
     * </pre>
     * @param group
     * @param name
     * @param startTime
     * @param repeatCount
     * @param repeatInterval
     * @param misfireInstruction
     * @return {@link SimpleTrigger}
     */
    public SimpleTrigger simpleTrigger(
            String group
            , String name
            , Date startTime
            , long startDelay
            , int repeatCount
            , long repeatInterval
            , int misfireInstruction
            ) {
        
        SimpleTriggerFactoryBean simpleTriggerFactoryBean = new SimpleTriggerFactoryBean();
        
        simpleTriggerFactoryBean.setGroup(group);
        simpleTriggerFactoryBean.setName(name);
        simpleTriggerFactoryBean.setStartTime(startTime);
        simpleTriggerFactoryBean.setStartDelay(startDelay);
        simpleTriggerFactoryBean.setRepeatCount(repeatCount);
        simpleTriggerFactoryBean.setMisfireInstruction(misfireInstruction);
        simpleTriggerFactoryBean.setRepeatInterval(repeatInterval);
        simpleTriggerFactoryBean.afterPropertiesSet();
        
        return simpleTriggerFactoryBean.getObject();
    }
    
    
    /**
     * <pre>
     * CronTrigger.MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY = -1
     * CronTrigger.MISFIRE_INSTRUCTION_SMART_POLICY = 0
     * CronTrigger.MISFIRE_INSTRUCTION_FIRE_ONCE_NOW = 1
     * CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING = 2
     * CronTrigger.DEFAULT_PRIORITY = 5
     * </pre>
     * @param group
     * @param name
     * @param cronExpression
     * @param startTime
     * @param misfireInstruction
     * @return {@link CronTrigger}
     */
    public CronTrigger cronTrigger(
            String group
            , String name
            , Date startTime
            , long startDelay
            , String cronExpression
            , int misfireInstruction
            ) throws ParseException {
        
        
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        
        cronTriggerFactoryBean.setGroup(group);
        cronTriggerFactoryBean.setName(name);
        cronTriggerFactoryBean.setStartTime(startTime);
        cronTriggerFactoryBean.setStartDelay(startDelay);
        cronTriggerFactoryBean.setCronExpression(cronExpression);
        cronTriggerFactoryBean.setMisfireInstruction(misfireInstruction);
        cronTriggerFactoryBean.afterPropertiesSet();
        
        return cronTriggerFactoryBean.getObject();
    }
    
}
