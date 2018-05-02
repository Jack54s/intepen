package com.jack.intepen.dao;

import com.jack.intepen.entity.Elder;
import com.jack.intepen.vo.ElderProfile;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 11407 on 30/030.
 */
@Repository
public interface ElderDao {

    List<ElderProfile> queryElder();
    ElderProfile queryElderById(int id);
    List<ElderProfile> queryElderByName(String name);
    ElderProfile queryElderByIdCard(String idCard);
    List<ElderProfile> queryUndistributedElder();
    int insertElder(Elder elder);
    int updateElder(Elder elder);
    int deleteElder(int id);
    int distributeNurse(@Param("id") int id, @Param("nurseId") int nurseId);
}
