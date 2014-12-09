/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.batch.service;

import com.java4us.batch.component.AsyncJavaWorker;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author turgay
 */
public class JavaSchedulerJob extends QuartzJobBean implements StatefulJob {

    private static Logger LOGGER = LoggerFactory.getLogger("JavaSchedulerJob");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    private AsyncJavaWorker asyncJavaWorker;

    @SuppressWarnings("unused")
	@Override
    protected void executeInternal(JobExecutionContext ctx)
            throws JobExecutionException {

        // The job data map is available through the JobExecutionContext 
        // (passed to you at execution time)
        JobDataMap jobDataMap = ctx.getJobDetail().getJobDataMap();

        try {
            // Retrieve the last date when the job was run
            Date lastDateRun = ctx.getPreviousFireTime();

            // Job was run previously
            if (lastDateRun != null) {
                LOGGER.debug("Last date run: " + sdf.format(lastDateRun));

                // Retrieve the number of times this job has been attempted
                int refireCount = ctx.getRefireCount();

                if (refireCount > 0) {
                    LOGGER.debug("Total attempts: " + refireCount);
                }
            } else {
                // Job is run for the first time
                LOGGER.debug("Job is run for the first time");
            }

            // Do the actual work
            LOGGER.debug("Delegating work to worker");
            asyncJavaWorker.work();

            // Retrieve the next date when the job will be run
            String nextDateRun = sdf.format(ctx.getNextFireTime());

            LOGGER.debug("Next date run: " + nextDateRun);

        } catch (Exception e) {
            LOGGER.error("Unexpected exception", e);
            throw new JobExecutionException("Unexpected exception", e, true);
        }
    }


    public void setAsyncJavaWorker(AsyncJavaWorker asyncJavaWorker) {
        this.asyncJavaWorker = asyncJavaWorker;
    }
    
    

}
