package com.jack.intepen.service;

import com.jack.intepen.dao.ElderDao;
import com.jack.intepen.entity.Elder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 11407 on 30/030.
 */
@Service
public class ElderService {

    @Autowired
    private ElderDao elderDao;

    public List<Elder> getElderList(){
        return elderDao.queryElder();
    }

    public Elder getElderById(int id){
        return elderDao.queryElderById(id);
    }

    @Transactional
    public boolean addElder(Elder elder){
        if(elder.getName() != null && !"".equals(elder.getName())){
            try{
                int effectNum = elderDao.insertElder(elder);
                if(effectNum > 0 ){
                    return true;
                }
                else{
                    throw new RuntimeException("插入老人信息失败！");
                }
            }
            catch (Exception e){
                throw new RuntimeException("插入老人信息失败：" + e.getMessage());
            }
        }
        else{
            throw new RuntimeException("老人姓名不能为空！");
        }
    }

    public boolean modifyElder(Elder elder){
        if(elder.getId() != null && !"".equals(elder.getId())){
            try{
                int effectNum = elderDao.updateElder(elder);
                if(effectNum > 0 ){
                    return true;
                }
                else{
                    throw new RuntimeException("更新老人信息失败！");
                }
            }
            catch (Exception e){
                throw new RuntimeException("更新老人信息失败：" + e.getMessage());
            }
        }
        else{
            throw new RuntimeException("老人ID不能为空！");
        }

    }

    public boolean deleteElder(int id){
        if(id > 0){
            try{
                int effectNum = elderDao.deleteElder(id);
                if(effectNum > 0 ){
                    return true;
                }
                else{
                    throw new RuntimeException("删除老人信息失败！");
                }
            }
            catch (Exception e){
                throw new RuntimeException("删除老人信息失败：" + e.getMessage());
            }
        }
        else{
            throw new RuntimeException("老人ID不能为空！");
        }
    }
}
