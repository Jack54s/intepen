package com.jack.intepen.dao;

import com.jack.intepen.entity.MedicalRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 11407 on 13/013.
 */
@Repository
public interface MedicalRecordDao {

    int insertMedicalRecord(MedicalRecord medicalRecord);
    int deleteMedicalRecord(int id);
    int updateMedicalRecord(MedicalRecord medicalRecord);
    List<MedicalRecord> queryMedicalRecordByElderId(int elderId);
}
