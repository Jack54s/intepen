package com.jack.intepen.dao;

import com.jack.intepen.entity.PhysicalExaminationData;

/**
 * Created by 11407 on 13/013.
 */
public interface PhysicalExaminationDataDao {

    int insertPhysicalExaminationData(PhysicalExaminationData physicalExaminationData);
    int deletePhysicalExaminationData(int id);
    int updatePhysicalExaminationData(PhysicalExaminationData physicalExaminationData);
    PhysicalExaminationData queryPhysicalExaminationDataByElderId(int elderId);
}
