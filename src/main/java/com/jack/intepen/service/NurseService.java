package com.jack.intepen.service;

import com.jack.intepen.dao.NurseDao;
import com.jack.intepen.entity.Nurse;
import com.jack.intepen.service.UserInterface.SysUserService;
import com.jack.intepen.util.EncryptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 11407 on 3/003.
 */
public class NurseService implements SysUserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private NurseDao nurseDao;

    public Nurse getNurseByAccount(String account){
        return nurseDao.queryNurseByAccount(account);
    }

    @Transactional
    public boolean addNurse(Nurse nurse){

        logger.info("--------------------------addNurse----------------------");

        String salt = EncryptionUtils.getSalt(128);
        String password = EncryptionUtils.SHA512Encode(nurse.getPassword(), salt);
        nurse.setPassword(password);
        nurse.setSalt(salt);

        if(nurse.getAccount() != null && !"".equals(nurse.getAccount())){
            try{
                int effectNum = nurseDao.insertNurse(nurse);
                if(effectNum > 0 ){
                    return true;
                }
                else{
                    throw new RuntimeException("插入护工信息失败！");
                }
            }
            catch (Exception e){
                throw new RuntimeException("插入护工信息失败：" + e.getMessage());
            }
        }
        else{
            throw new RuntimeException("护工账号不能为空！");
        }
    }

    public boolean modifyNurse(Nurse nurse){

        logger.info("--------------------------modifyNurse----------------------");

        if(nurse.getId() != null && !"".equals(nurse.getId())){
            try{
                int effectNum = nurseDao.updateNurse(nurse);
                if(effectNum > 0 ){
                    return true;
                }
                else{
                    throw new RuntimeException("更新护工信息失败！");
                }
            }
            catch (Exception e){
                throw new RuntimeException("更新护工信息失败：" + e.getMessage());
            }
        }
        else{
            throw new RuntimeException("护工ID不能为空！");
        }

    }

    public boolean deleteNurse(int id){

        logger.info("--------------------------deleteNurse----------------------");

        if(id > 0){
            try{
                int effectNum = nurseDao.deleteNurse(id);
                if(effectNum > 0 ){
                    return true;
                }
                else{
                    throw new RuntimeException("删除护工信息失败！");
                }
            }
            catch (Exception e){
                throw new RuntimeException("删除护工信息失败：" + e.getMessage());
            }
        }
        else{
            throw new RuntimeException("护工ID不能为空！");
        }
    }
}
