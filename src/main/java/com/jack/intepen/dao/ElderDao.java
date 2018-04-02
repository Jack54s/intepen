package com.jack.intepen.dao;

import com.jack.intepen.entity.Elder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 11407 on 30/030.
 */
@Repository
public interface ElderDao {

    List<Elder> queryElder();
    Elder queryElderById(int id);
    int insertElder(Elder elder);
    int updateElder(Elder elder);
    int deleteElder(int id);
}
