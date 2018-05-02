package com.jack.intepen.service;

import com.jack.intepen.dao.PatientStatisticDao;
import com.jack.intepen.entity.PatientStatistic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * Created by 11407 on 26/026.
 */
@Service
public class PatientStatisticService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PatientStatisticDao patientStatisticDao;

    public List<PatientStatistic> getPatientStatisticsByDate(Date date){
        return patientStatisticDao.queryPatientStatisticByDate(date);
    }

    public List<PatientStatistic> getPatientStatisticsToday(){

        java.util.Date day = new java.util.Date();
        Date today = new Date(day.getTime());
        return getPatientStatisticsByDate(today);
    }

    public List<PatientStatistic> getPatientStatisticsByDateZone(Date dateA, Date dateB){
        return patientStatisticDao.queryPatientStatisticByDateZone(dateA, dateB);
    }
}
