package com.quartz.demo.service;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.quartz.demo.dao.UserOperation;
import com.quartz.demo.model.User;

public class UserQuartzJob implements Job {
	private static Logger logger=Logger.getLogger(UserQuartzJob.class);
	private UserOperation op= new UserOperation();
	
	private User setDummyUser() {
		User user = new User();
		user.setId(1);
		user.setName("fathyelshemy");
		user.setAge("25");
		return user;
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("======================================start of job=======================================");
		User user=setDummyUser();
		op.addUser(user);
		logger.info(user.toString()+ new Date());
		op.usersViewer();
		System.out.println("======================================end of job=======================================");
	}

}
