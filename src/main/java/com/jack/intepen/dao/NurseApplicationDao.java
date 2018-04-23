package com.jack.intepen.dao;

import com.jack.intepen.entity.NurseApplication;

import java.util.List;

/**
 * Created by 11407 on 13/013.
 */
public interface NurseApplicationDao {

    int insertNurseApplication(NurseApplication nurseApplication);
    int deleteNurseApplication(int id);
    int updateNurseApplication(NurseApplication nurseApplication);
    List<NurseApplication> queryNurseApplication();
}
