package com.jack.intepen.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 11407 on 12/012.
 */
@Repository
public interface ElderFamilyDao {

    List<Integer> queryElderIdByFamilyId(int familyId);
    List<Integer> queryFamilyIdByElderId(int elderId);
    int insertElderFamilyRelation(@Param("elderId") int elderId, @Param("familyId") int familyId);
    int deleteElderFamilyByFamilyId(int familyId);
    int deleteElderFamilyByElderId(int elderId);
    int deleteElderFamily(@Param("elderId") Integer elderId,
                          @Param("familyId") Integer familyId);
}
