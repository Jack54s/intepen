package com.jack.intepen.dao;

import com.jack.intepen.entity.Inspection;

import java.sql.Date;
import java.util.List;

/**
 * Created by 11407 on 23/023.
 */
public interface InspectionDao {

    int insertInspection(Inspection inspection);
    int deleteInspection(int id);
    int updateInspection(Inspection inspection);
    List<Inspection> queryInspectionByElderId(Integer elderId);
    List<Inspection> queryInspectionByDate(Date date);
}
