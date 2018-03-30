package com.jack.intepen.dao;

import com.jack.intepen.entity.OldMan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 11407 on 30/030.
 */
@Repository
public interface OldManDao {

    List<OldMan> queryOldMan();
    OldMan queryOldManById(int id);
    int insertOldMan(OldMan oldMan);
    int updateOldMan(OldMan oldMan);
    int deleteOldMan(int id);
}
