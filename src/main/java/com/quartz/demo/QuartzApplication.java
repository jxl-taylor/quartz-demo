package com.quartz.demo;

import com.quartz.demo.service.QuertzBuilder;

public class QuartzApplication {

	public static void main(String[] args) {
		QuertzBuilder quartz= new QuertzBuilder();

		quartz.buildQuartz();
	}
}
