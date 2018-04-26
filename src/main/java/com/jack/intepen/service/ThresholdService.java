package com.jack.intepen.service;

import com.jack.intepen.dao.ThresholdDao;
import com.jack.intepen.entity.Threshold;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 11407 on 24/024.
 */
@Service
public class ThresholdService {

    @Autowired
    ThresholdDao thresholdDao;

    public List<Threshold> getAllThreshold(){
        return thresholdDao.queryAllThreshold();
    }

    public Threshold getThresholdById(int id){
        return thresholdDao.queryThresholdById(id);
    }

    public Threshold getThresholdByItem(String item){
        return thresholdDao.queryThresholdByItem(item);
    }

    @Transactional
    public boolean addThreshold(Threshold threshold){
        if(threshold.getItem() != null && !"".equals(threshold.getItem())){
            try{
                int effectNum = thresholdDao.insertThreshold(threshold);
                if(effectNum > 0 ){
                    return true;
                }
                else{
                    throw new RuntimeException("插入阈值失败！");
                }
            }
            catch (Exception e){
                throw new RuntimeException("插入阈值失败：" + e.getMessage());
            }
        }
        else{
            throw new RuntimeException("阈值类别不能为空！");
        }
    }

    public boolean modifyThreshold(Threshold threshold){
        if(threshold.getId() != null && !"".equals(threshold.getId())){
            try{
                int effectNum = thresholdDao.updateThreshold(threshold);
                if(effectNum > 0 ){
                    return true;
                }
                else{
                    throw new RuntimeException("受影响行数为0");
                }
            }
            catch (Exception e){
                throw new RuntimeException("更新阈值失败：" + e.getMessage());
            }
        }
        else{
            throw new RuntimeException("阈值ID不能为空！");
        }
    }

    public boolean removeThreshold(int id){
        if(id > 0){
            try{
                thresholdDao.deleteThreshold(id);
            }
            catch (Exception e){
                throw new RuntimeException("删除阈值失败：" + e.getMessage());
            }
        }
        else{
            throw new RuntimeException("阈值ID不能为空！");
        }
        return true;
    }
}
