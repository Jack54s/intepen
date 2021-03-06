package com.jack.intepen.service;

import com.jack.intepen.dao.ElderDao;
import com.jack.intepen.dao.ElderFamilyDao;
import com.jack.intepen.dao.MedicalRecordDao;
import com.jack.intepen.entity.Elder;
import com.jack.intepen.entity.MedicalRecord;
import com.jack.intepen.vo.ElderProfile;
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

    @Autowired
    private MedicalRecordDao medicalRecordDao;

    @Autowired
    private ElderFamilyDao elderFamilyDao;

    public List<ElderProfile> getElderList(){
        return elderDao.queryElder();
    }

    public ElderProfile getElderById(int id){
        return elderDao.queryElderById(id);
    }

    public List<ElderProfile> getElderByName(String name) { return elderDao.queryElderByName(name); }

    public ElderProfile getElderByIdCard(String idCard) { return elderDao.queryElderByIdCard(idCard); }

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
                elderDao.deleteElder(id);
            }
            catch (Exception e){
                throw new RuntimeException("删除老人信息失败：" + e.getMessage());
            }
        }
        else{
            throw new RuntimeException("老人ID不能为空！");
        }
        return true;
    }

    public List<ElderProfile> getUndistributedElder(){ return elderDao.queryUndistributedElder(); }

    public boolean distributeNurse(int id, int nurseId){
        if(id > 0 && nurseId > 0){
            try{
                int effectNum = elderDao.distributeNurse(id, nurseId);
                if(effectNum > 0 ){
                    return true;
                }
                else{
                    throw new RuntimeException("分配护工失败！");
                }
            }
            catch (Exception e){
                throw new RuntimeException("分配护工失败：" + e.getMessage());
            }
        }
        else{
            throw new RuntimeException("人员ID错误！");
        }
    }

    public List<MedicalRecord> getMedicalRecords(int elderId){
        return medicalRecordDao.queryMedicalRecordByElderId(elderId);
    }

    @Transactional
    public Boolean relateFamily(Integer elderId, List<Integer> familyIds){

        elderFamilyDao.deleteElderFamilyByElderId(elderId);

        for (Integer familyId : familyIds){
            if(elderFamilyDao.insertElderFamilyRelation(elderId, familyId) != 1){
                return false;
            }
        }
        return true;
    }
}
