package com.jack;

import com.jack.config.ThresholdConfiguration;
import com.jack.job.SmartWatchServer;
import com.jack.job.ThresholdConfigurationInitialler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by 11407 on 23/023.
 */
public class ApplicationStartListener implements ApplicationListener<ApplicationStartedEvent> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ThresholdConfigurationInitialler thresholdConfigurationInitialler;

    @Autowired
    private ThresholdConfiguration thresholdConfiguration;


    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationEvent) {

        logger.info("--------------ThresholdConfiguration is initialed...--------------");

        thresholdConfigurationInitialler.initialThresholdConfig();

        logger.info("------------thresholdMap:{}-------------", thresholdConfiguration.thresholdMap.toString());
    }
}
