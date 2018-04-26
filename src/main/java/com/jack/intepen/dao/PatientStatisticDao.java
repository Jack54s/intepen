package com.jack.intepen.dao;

import com.jack.intepen.entity.PatientStatistic;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

/**
 * Created by 11407 on 23/023.
 */
public interface PatientStatisticDao {

    int insertPatientStatistic(PatientStatistic patientStatistic);
    int deletePatientStatistic(int id);
    int updatePatientStatistic(PatientStatistic patientStatistic);
    List<PatientStatistic> queryPatientStatisticByDate(Date date);
    List<PatientStatistic> queryPatientStatisticByDateZone(@Param("dateA") Date dateA, @Param("dateB") Date dateB);
}
