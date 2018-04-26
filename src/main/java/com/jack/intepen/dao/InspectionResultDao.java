package com.jack.intepen.dao;

import com.jack.intepen.vo.InspectionResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * Created by 11407 on 26/026.
 */
@Repository
public interface InspectionResultDao {

    List<InspectionResult> queryInspectionResultByElderId(Integer elderId);
    List<InspectionResult> queryInspectionResultByElderName(String elderName);
    List<InspectionResult> queryInspectionResultByDate(Date date);
    List<InspectionResult> queryInspectionResultByElderNameAndDateZone(@Param("elderName")String elderName, @Param("dateA") Date date, @Param("dateB") Date dateB);
    List<InspectionResult> queryInspectionResultByDateZone(@Param("dateA") Date dateA, @Param("dateB") Date dateB);
}
