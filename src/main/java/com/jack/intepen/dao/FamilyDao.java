package com.jack.intepen.dao;

import com.jack.intepen.entity.Family;
import org.springframework.stereotype.Repository;

/**
 * Created by 11407 on 3/003.
 */
@Repository
public interface FamilyDao {

    int insertFamily(Family family);
    int updateFamily(Family family);
    int deleteFamily(int id);
    Family queryFamilyById(int id);
    Family queryFamilyByAccount(String account);
}
