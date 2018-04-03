package com.jack.intepen.dao;

import com.jack.intepen.entity.Nurse;

import java.util.List;

/**
 * Created by 11407 on 3/003.
 */
public interface NurseDao {

    int insertNurse(Nurse nurse);
    int deleteNurse(int id);
    int updateNurse(Nurse nurse);
    Nurse queryNurseById(int id);
    Nurse queryNurseByAccount(String account);
    List<Nurse> queryNurse();
}
