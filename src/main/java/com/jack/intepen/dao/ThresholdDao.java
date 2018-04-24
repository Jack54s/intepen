package com.jack.intepen.dao;

import com.jack.intepen.entity.Threshold;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 11407 on 24/024.
 */
@Repository
public interface ThresholdDao {

    int insertThreshold(Threshold threshold);
    int deleteThreshold(int id);
    int updateThreshold(Threshold threshold);
    List<Threshold> queryAllThreshold();
    Threshold queryThresholdById(int id);
    Threshold queryThresholdByItem(String item);
}
