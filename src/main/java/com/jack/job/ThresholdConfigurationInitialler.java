package com.jack.job;

import com.jack.config.ThresholdConfiguration;
import com.jack.intepen.dao.ThresholdDao;
import com.jack.intepen.entity.Threshold;
import com.jack.intepen.util.SpringBeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Created by 11407 on 24/024.
 */
@Component
public class ThresholdConfigurationInitialler{

    private Logger logger = LoggerFactory.getLogger(ThresholdConfigurationInitialler.class);

    private ThresholdDao thresholdDao = SpringBeanUtil.getBean(ThresholdDao.class);

    @Autowired
    private ThresholdConfiguration thresholdConfiguration;

    private List<Threshold> thresholdList = thresholdDao.queryAllThreshold();

    public void initialThresholdConfig(){

        for(Threshold threshold : thresholdList){
            thresholdConfiguration.thresholdMap.put(threshold.getItem(), threshold.getThreshold());
        }
    }
    //
//    @Autowired
//    private ThresholdDao thresholdDao;
//
//    @Autowired
//    private ThresholdConfiguration thresholdConfiguration;
//
//    List<Threshold> thresholdList = thresholdDao.queryAllThreshold();
    //logger.info("------------thresholdList:{}-----------");
}
