package com.jack.intepen.dao;

import com.jack.intepen.entity.Elder;
import com.jack.intepen.entity.Nurse;
import com.jack.intepen.vo.NurseProfile;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 11407 on 3/003.
 */
@Repository
public interface NurseDao {

    int insertNurse(Nurse nurse);
    int deleteNurse(int id);
    int updateNurseProfile(NurseProfile nurseProfile);
    Nurse queryNurseById(int id);
    Nurse queryNurseByAccount(String account);
    List<Nurse> queryNurseByName(String Name);
    List<Nurse> queryNurse();
    List<Elder> queryElderByNurse(int nurseId);
    NurseProfile queryNurseProfileById(int id);
    NurseProfile queryNurseProfileByAccount(String account);
    List<NurseProfile> queryNurseProfileByName(String Name);
}
