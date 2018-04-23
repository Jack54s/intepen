package com.jack.intepen.dao;

import com.jack.intepen.entity.Illness;

import java.util.List;

/**
 * Created by 11407 on 23/023.
 */
public interface IllnessDao {

    int insertIllness(Illness illness);
    int deleteIllness(Illness illness);
    int updateIllness(Illness illness);
    List<Illness> queryIllness();
    Illness queryIllnessById(int id);
}
