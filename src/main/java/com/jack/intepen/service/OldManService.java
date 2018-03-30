package com.jack.intepen.service;

import com.jack.intepen.dao.OldManDao;
import com.jack.intepen.entity.OldMan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 11407 on 30/030.
 */
@Service
public class OldManService {

    @Autowired
    private OldManDao oldManDao;

    public List<OldMan> getOldManList(){
        return oldManDao.queryOldMan();
    }

    public OldMan getOldManById(int id){
        return oldManDao.queryOldManById(id);
    }

    @Transactional
    public boolean addOldMan(OldMan oldMan){
        if(oldMan.getName() != null && !"".equals(oldMan.getName())){
            try{
                int effectNum = oldManDao.insertOldMan(oldMan);
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

    public boolean modifyOldMan(OldMan oldMan){
        if(oldMan.getId() != null && !"".equals(oldMan.getId())){
            try{
                int effectNum = oldManDao.updateOldMan(oldMan);
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
    public boolean deleteOldMan(int id){
        if(id > 0){
            try{
                int effectNum = oldManDao.deleteOldMan(id);
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
