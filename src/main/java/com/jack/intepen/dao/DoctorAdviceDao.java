package com.jack.intepen.dao;

import com.jack.intepen.entity.DoctorAdvice;

import java.util.List;

/**
 * Created by 11407 on 13/013.
 */
public interface DoctorAdviceDao {

    int insertDoctorAdvice(DoctorAdvice doctorAdvice);
    int deleteDoctorAdvice(int id);
    int updateDoctorAdvice(DoctorAdvice doctorAdvice);
    List<DoctorAdvice> queryDoctorAdviceByElderId(int elderId);
}
