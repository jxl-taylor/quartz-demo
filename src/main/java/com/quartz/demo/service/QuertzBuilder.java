package com.quartz.demo.service;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuertzBuilder {
	private static Logger logger= Logger.getLogger(QuertzBuilder.class);
	
	public void buildQuartz() {
		try {
			JobDetail userJobDetails=JobBuilder.newJob(UserQuartzJob.class).
					withIdentity( "user","employees")
					.build();
			Trigger userTrigger=TriggerBuilder.newTrigger().withIdentity("cronTrigger1", "group1")
								.withSchedule(CronScheduleBuilder.cronSchedule("0 0/2 * * * ?")).build();

			Scheduler userScheduler=new StdSchedulerFactory().getScheduler();
			userScheduler.start();
			userScheduler.scheduleJob(userJobDetails, userTrigger);

			// 暂停执行任务
//			userScheduler.pauseJob(userJobDetails.getKey());
//
//			// 继续执行任务
//			userScheduler.resumeJob(userJobDetails.getKey());
//
//			// 继续执行任务
//			userScheduler.addJob(userJobDetails, true);


		} catch (SchedulerException e) {
			logger.error(e.toString());
		}
		
		
	}

}
