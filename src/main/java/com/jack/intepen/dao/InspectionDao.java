package com.jack.intepen.dao;

import com.jack.intepen.entity.Inspection;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * Created by 11407 on 23/023.
 */
@Repository
public interface InspectionDao {

    int insertInspection(Inspection inspection);
    int deleteInspection(int id);
    int updateInspection(Inspection inspection);
    List<Inspection> queryInspectionByElderId(Integer elderId);
    List<Inspection> queryInspectionByDate(Date date);
    Inspection queryInspectionByElderIdAndDate(@Param("elderId")Integer elderId, @Param("date") Date date);
}
