package com.jack.intepen.dao;

import com.jack.intepen.entity.PatientStatistic;

import java.sql.Date;

/**
 * Created by 11407 on 23/023.
 */
public interface PatientStatisticDao {

    int insertPatientStatistic(PatientStatistic patientStatistic);
    int deletePatientStatistic(int id);
    int updatePatientStatistic(PatientStatistic patientStatistic);
    PatientStatistic queryPatientStatisticByDate(Date date);
}
