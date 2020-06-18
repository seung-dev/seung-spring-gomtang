package seung.spring.boot.conf.quartz;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import seung.java.kimchi.SDate;
import seung.java.kimchi.exception.SKimchiException;
import seung.java.kimchi.util.SLinkedHashMap;

@Component(value = "sQuartzH")
public class SQuartzH {

    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;
    
    /**
     * @param group
     * @param name
     * @throws SchedulerException
     */
    @SuppressWarnings("unchecked")
    public SLinkedHashMap detail(
            String group
            , String name
            ) throws SchedulerException {
        
        SLinkedHashMap jobDetail = new SLinkedHashMap();
        
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(name, group);
        List<Trigger> triggersOfJob = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
        if(triggersOfJob.isEmpty()) {
            throw new SchedulerException("Triggers of job is empty.");
        }
        Trigger trigger = triggersOfJob.get(0);
        jobDetail.put("start_time", SDate.getDateString("yyyy-MM-dd HH:mm:ss", trigger.getStartTime()));
        jobDetail.put("next_fire_time", SDate.getDateString("yyyy-MM-dd HH:mm:ss", trigger.getNextFireTime()));
        jobDetail.put("previous_fire_time", trigger.getPreviousFireTime() == null ? "" : SDate.getDateString("yyyy-MM-dd HH:mm:ss", trigger.getPreviousFireTime()));
        jobDetail.put("trigger_state", scheduler.getTriggerState(trigger.getKey()).name());
        
        return jobDetail;
    }
    
    /**
     * @param group
     * @param name
     * @param dscr
     * @param clss
     * @param dateFrom
     * @param dateTo
     * @param intv
     * @return {@link TriggerState#name()}
     * @throws SchedulerException
     * @throws ClassNotFoundException
     * @throws SKimchiException
     */
    @SuppressWarnings("unchecked")
    public String addSimple(
            String group
            , String name
            , String dscr
            , String clss
            , String dateFrom
            , String dateTo
            , long intv
            ) throws SchedulerException, ClassNotFoundException, SKimchiException {
        
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(name, group);
        
        if(scheduler.checkExists(jobKey)) {
            throw new SchedulerException("Job already exist.");
        }
        
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("job_group", group);
        jobDataMap.put("job_name", name);
        jobDataMap.put("dscr", dscr);
        jobDataMap.put("clss", clss);
        jobDataMap.put("date_from", dateFrom);
        jobDataMap.put("date_to", dateTo);
        jobDataMap.put("intv", intv);
        
        JobDetail jobDetail = JobBuilder.newJob()
                .storeDurably(true)
                .withIdentity(name, group)
                .withDescription(dscr)
                .ofType((Class<? extends QuartzJobBean>) Class.forName(clss))
                .setJobData(jobDataMap)
                .build()
                ;
        
        Date startAt = null;
        if(dateFrom == null || "".equals(dateFrom)) {
            startAt = new Date();
        } else {
            startAt = SDate.toDate(dateFrom, "yyyy-MM-dd HH:mm:ss");
        }
        
        Date endAt = null;
        if(dateTo == null || "".equals(dateTo)) {
            endAt = SDate.toDate("2099-12-31 23:59:59", "yyyy-MM-dd HH:mm:ss");
        } else {
            endAt = SDate.toDate(dateTo, "yyyy-MM-dd HH:mm:ss");
        }
        
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(name, group)
                .startAt(startAt)
                .endAt(endAt)
                .withSchedule(
                        SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInMilliseconds(intv)
                        .withMisfireHandlingInstructionIgnoreMisfires()
                        )
                .build()
                ;
        
        scheduler.scheduleJob(jobDetail, trigger);
        
        return scheduler.getTriggerState(trigger.getKey()).name();
    }
    
    /**
     * @param group
     * @param name
     * @param dscr
     * @param clss
     * @param dateFrom
     * @param dateTo
     * @param cronExpr
     * @return {@link TriggerState#name()}
     * @throws SchedulerException
     * @throws ClassNotFoundException
     * @throws SKimchiException
     */
    @SuppressWarnings("unchecked")
    public String addCron(
            String group
            , String name
            , String dscr
            , String clss
            , String dateFrom
            , String dateTo
            , String cronExpr
            ) throws SchedulerException, ClassNotFoundException, SKimchiException {
        
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(name, group);
        
        if(scheduler.checkExists(jobKey)) {
            throw new SchedulerException("Job already exist.");
        }
        
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("job_group", group);
        jobDataMap.put("job_name", name);
        jobDataMap.put("dscr", dscr);
        jobDataMap.put("clss", clss);
        jobDataMap.put("date_from", dateFrom);
        jobDataMap.put("date_to", dateTo);
        jobDataMap.put("cron_expr", cronExpr);
        
        JobDetail jobDetail = JobBuilder.newJob()
                .storeDurably(true)
                .withIdentity(name, group)
                .withDescription(dscr)
                .ofType((Class<? extends QuartzJobBean>) Class.forName(clss))
                .setJobData(jobDataMap)
                .build()
                ;
        
        Date startAt = null;
        if(dateFrom == null || "".equals(dateFrom)) {
            startAt = new Date();
        } else {
            startAt = SDate.toDate(dateFrom, "yyyy-MM-dd HH:mm:ss");
        }
        
        Date endAt = null;
        if(dateTo == null || "".equals(dateTo)) {
            endAt = SDate.toDate("2099-12-31 23:59:59", "yyyy-MM-dd HH:mm:ss");
        } else {
            endAt = SDate.toDate(dateTo, "yyyy-MM-dd HH:mm:ss");
        }
        
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(name, group)
                .startAt(startAt)
                .endAt(endAt)
                .withSchedule(
                        CronScheduleBuilder
                            .cronSchedule(cronExpr)
                            .inTimeZone(TimeZone.getDefault())
                            .withMisfireHandlingInstructionDoNothing()
                        )
                .build()
                ;
        
        scheduler.scheduleJob(jobDetail, trigger);
        
        return scheduler.getTriggerState(trigger.getKey()).name();
    }
    
    /**
     * @param group
     * @param name
     * @param dateFrom
     * @param dateTo
     * @param intv
     * @return {@link TriggerState#name()}
     * @throws SchedulerException
     * @throws SKimchiException
     */
    @SuppressWarnings("unchecked")
    public String editSimple(
            String group
            , String name
            , String dateFrom
            , String dateTo
            , long intv
            ) throws SchedulerException, SKimchiException {
        
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(name, group);
        List<Trigger> triggersOfJob = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
        
        if(triggersOfJob.isEmpty()) {
            throw new SchedulerException("Triggers of job is empty.");
        }
        
        TriggerKey triggerKey = triggersOfJob.get(0).getKey();
        TriggerState triggerState = scheduler.getTriggerState(triggerKey);
        
        if(!triggerState.equals(TriggerState.PAUSED)) {
            throw new SchedulerException(String.format("Trigger state is %s.", triggerState.name()));
        }
        
        Date startAt = null;
        if(dateFrom == null || "".equals(dateFrom)) {
            startAt = new Date();
        } else {
            startAt = SDate.toDate(dateFrom, "yyyy-MM-dd HH:mm:ss");
        }
        
        Date endAt = null;
        if(dateTo == null || "".equals(dateTo)) {
            endAt = SDate.toDate("2099-12-31 23:59:59", "yyyy-MM-dd HH:mm:ss");
        } else {
            endAt = SDate.toDate(dateTo, "yyyy-MM-dd HH:mm:ss");
        }
        
        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger()
                .withIdentity(name, group)
                .startAt(startAt)
                .endAt(endAt)
                .withSchedule(
                        SimpleScheduleBuilder
                            .simpleSchedule()
                            .withIntervalInMilliseconds(intv)
                            .withMisfireHandlingInstructionIgnoreMisfires()
                        )
                .build()
                ;
        
        scheduler.rescheduleJob(triggerKey, simpleTrigger);
        
        return scheduler.getTriggerState(simpleTrigger.getKey()).name();
    }
    
    /**
     * @param group
     * @param name
     * @param dateFrom
     * @param dateTo
     * @param cronExpr
     * @return {@link TriggerState#name()}
     * @throws SchedulerException
     * @throws SKimchiException
     */
    @SuppressWarnings("unchecked")
    public String editCron(
            String group
            , String name
            , String dateFrom
            , String dateTo
            , String cronExpr
            ) throws SchedulerException, SKimchiException {
        
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(name, group);
        List<Trigger> triggersOfJob = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
        
        if(triggersOfJob.isEmpty()) {
            throw new SchedulerException("Triggers of job is empty.");
        }
        
        TriggerKey triggerKey = triggersOfJob.get(0).getKey();
        TriggerState triggerState = scheduler.getTriggerState(triggerKey);
        
        if(!triggerState.equals(TriggerState.PAUSED)) {
            throw new SchedulerException(String.format("Trigger state is %s.", triggerState.name()));
        }
        
        Date startAt = null;
        if(dateFrom == null || "".equals(dateFrom)) {
            startAt = new Date();
        } else {
            startAt = SDate.toDate(dateFrom, "yyyy-MM-dd HH:mm:ss");
        }
        
        Date endAt = null;
        if(dateTo == null || "".equals(dateTo)) {
            endAt = SDate.toDate("2099-12-31 23:59:59", "yyyy-MM-dd HH:mm:ss");
        } else {
            endAt = SDate.toDate(dateTo, "yyyy-MM-dd HH:mm:ss");
        }
        
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity(name, group)
                .startAt(startAt)
                .endAt(endAt)
                .withSchedule(
                        CronScheduleBuilder
                            .cronSchedule(cronExpr)
                            .inTimeZone(TimeZone.getDefault())
                            .withMisfireHandlingInstructionDoNothing()
                        )
                .build()
                ;
        
        scheduler.rescheduleJob(triggerKey, cronTrigger);
        
        return scheduler.getTriggerState(cronTrigger.getKey()).name();
    }
    
    /**
     * @param group
     * @param name
     * @return 0: fail, 1: success
     * @throws SchedulerException
     */
    @SuppressWarnings("unchecked")
    public int delete(
            String group
            , String name
            ) throws SchedulerException {
        
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(name, group);
        List<Trigger> triggersOfJob = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
        
        if(triggersOfJob.isEmpty()) {
            throw new SchedulerException("Triggers of job is empty.");
        }
        
//        TriggerKey triggerKey = triggersOfJob.get(0).getKey();
//        TriggerState triggerState = scheduler.getTriggerState(triggerKey);
//        
//        if(!triggerState.equals(TriggerState.PAUSED)) {
//            throw new SchedulerException(String.format("Trigger state is %s.", triggerState.name()));
//        }
        
        return scheduler.deleteJob(jobKey) ? 1 : 0;
    }
    
    /**
     * @param group
     * @param name
     * @return {@link TriggerState#name()}
     * @throws SchedulerException
     */
    @SuppressWarnings("unchecked")
    public String run(
            String group
            , String name
            ) throws SchedulerException {
        
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(name, group);
        List<Trigger> triggersOfJob = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
        
        if(triggersOfJob.isEmpty()) {
            throw new SchedulerException("Triggers of job is empty.");
        }
        
        TriggerKey triggerKey = triggersOfJob.get(0).getKey();
        TriggerState triggerState = scheduler.getTriggerState(triggerKey);
        
        if(!triggerState.equals(TriggerState.PAUSED) && !triggerState.equals(TriggerState.ERROR)) {
            throw new SchedulerException(String.format("Trigger state is %s.", triggerState.name()));
        }
        
        scheduler.resumeJob(jobKey);
        
        return scheduler.getTriggerState(triggerKey).name();
    }
    
    /**
     * @param group
     * @param name
     * @return {@link TriggerState#name()}
     * @throws SchedulerException
     */
    @SuppressWarnings("unchecked")
    public String trigger(
            String group
            , String name
            ) throws SchedulerException {
        
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(name, group);
        List<Trigger> triggersOfJob = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
        
        if(triggersOfJob.isEmpty()) {
            throw new SchedulerException("Triggers of job is empty.");
        }
        
        TriggerKey triggerKey = triggersOfJob.get(0).getKey();
        TriggerState triggerState = scheduler.getTriggerState(triggerKey);
        
        if(
                triggerState.equals(TriggerState.PAUSED)
                || triggerState.equals(TriggerState.NORMAL)
                || triggerState.equals(TriggerState.COMPLETE)
                || triggerState.equals(TriggerState.ERROR)
                ) {
            scheduler.triggerJob(jobKey);
        } else {
            throw new SchedulerException(String.format("Trigger state is %s.", triggerState.name()));
        }
        
        return scheduler.getTriggerState(triggerKey).name();
    }
    
    /**
     * @param group
     * @param name
     * @return {@link TriggerState#name()}
     * @throws SchedulerException
     */
    @SuppressWarnings("unchecked")
    public String pause(
            String group
            , String name
            ) throws SchedulerException {
        
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(name, group);
        List<Trigger> triggersOfJob = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
        
        if(triggersOfJob.isEmpty()) {
            throw new SchedulerException("Triggers of job is empty.");
        }
        
        TriggerKey triggerKey = triggersOfJob.get(0).getKey();
        TriggerState triggerState = scheduler.getTriggerState(triggerKey);
        
        if(!triggerState.equals(TriggerState.NORMAL)) {
            throw new SchedulerException(String.format("Trigger state is %s.", triggerState.name()));
        }
        
        scheduler.pauseJob(jobKey);
        
        return scheduler.getTriggerState(triggerKey).name();
    }
    
}
