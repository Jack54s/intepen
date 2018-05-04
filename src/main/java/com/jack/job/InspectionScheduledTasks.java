package com.jack.job;

import com.jack.intepen.dao.ElderDao;
import com.jack.intepen.dao.InspectionDao;
import com.jack.intepen.entity.Elder;
import com.jack.intepen.entity.Inspection;
import com.jack.intepen.vo.ElderProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

/**
 * Created by 11407 on 4/004.
 */
@Component
public class InspectionScheduledTasks {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ElderDao elderDao;

    @Autowired
    private InspectionDao inspectionDao;

    @Scheduled(cron = "1 0 0 * * *")
    public void createInspectionToday(){

        logger.info("-----------Start creating today's Inspection...---------");

        List<ElderProfile> elders = elderDao.queryElder();

        Inspection inspection = new Inspection();
        for(ElderProfile elder : elders){

            inspection.setElderId(elder.getId());
            java.util.Date day = new java.util.Date();
            Date today = new Date(day.getTime());
            inspection.setDate(today);

            inspectionDao.insertInspection(inspection);
        }
    }
}
