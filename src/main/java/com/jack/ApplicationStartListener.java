package com.jack;

import com.jack.job.SmartWatchServer;
import com.jack.job.ThresholdConfigurationInitialler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by 11407 on 23/023.
 */
public class ApplicationStartListener implements ApplicationListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ThresholdConfigurationInitialler thresholdConfigurationInitialler;


    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

        logger.info("--------------ThresholdConfiguration is initialed...--------------");

        thresholdConfigurationInitialler.initialThresholdConfig();
    }
}
