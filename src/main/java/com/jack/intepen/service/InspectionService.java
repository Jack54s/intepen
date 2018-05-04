package com.jack.intepen.service;

import com.jack.intepen.dao.InspectionDao;
import com.jack.intepen.dao.InspectionResultDao;
import com.jack.intepen.entity.Inspection;
import com.jack.intepen.vo.InspectionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * Created by 11407 on 26/026.
 */
@Service
public class InspectionService {

    @Autowired
    private InspectionResultDao inspectionResultDao;

    @Autowired
    private InspectionDao inspectionDao;

    public List<InspectionResult> getInspectionByElderNameAndDateZone(String elderName, Date dateA, Date dateB){
        return inspectionResultDao.queryInspectionResultByElderNameAndDateZone(elderName, dateA, dateB);
    }

    public List<InspectionResult> getInspectionResultByElderId(Integer elderId){
        return inspectionResultDao.queryInspectionResultByElderId(elderId);
    }

    public List<InspectionResult> getInspectionResultByElderName(String elderName){
        return inspectionResultDao.queryInspectionResultByElderName(elderName);
    }

    public List<InspectionResult> getInspectionResultByDate(Date date){
        return inspectionResultDao.queryInspectionResultByDate(date);
    }

    public List<InspectionResult> getTodayInspectionResult(){
        java.util.Date day = new java.util.Date();

        Date today = new Date(day.getTime());
        return inspectionResultDao.queryInspectionResultByDate(today);
    }

    public List<InspectionResult> getInspectionResultByDateZone(Date dateA, Date dateB){
        return inspectionResultDao.queryInspectionResultByDateZone(dateA, dateB);
    }

    public Boolean addInspection(Inspection inspection){

        try{
            int effectNum = inspectionDao.insertInspection(inspection);
            if(effectNum > 0 ){
                return true;
            }
            else{
                throw new RuntimeException("插入巡查信息失败！");
            }
        }
        catch (Exception e){
            throw new RuntimeException("插入巡查信息失败：" + e.getMessage());
        }
    }

    public Boolean modifyInspection(Inspection inspection){

        if(inspection.getId() != null && !"".equals(inspection.getId())){
            try{
                inspectionDao.updateInspection(inspection);
            }
            catch (Exception e){
                throw new RuntimeException("更新巡查信息失败：" + e.getMessage());
            }
        }
        else{
            throw new RuntimeException("巡查ID不能为空！");
        }
        return true;
    }
}
