package com.jack.intepen.entity;

import java.sql.Date;

/**
 * Created by 11407 on 12/012.
 */
public class MedicalRecord {

    private Integer id;
    private String hospital;
    private Date admissionDate;
    private Date recordDate;
    private String historyOfPresentIllness;
    private String historyOfPastIllness;
    private String historyOfPersonalIllness;
    private String historyOfFamilyIllness;
    private Integer elderId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getHistoryOfPresentIllness() {
        return historyOfPresentIllness;
    }

    public void setHistoryOfPresentIllness(String historyOfPresentIllness) {
        this.historyOfPresentIllness = historyOfPresentIllness;
    }

    public String getHistoryOfPastIllness() {
        return historyOfPastIllness;
    }

    public void setHistoryOfPastIllness(String historyOfPastIllness) {
        this.historyOfPastIllness = historyOfPastIllness;
    }

    public String getHistoryOfPersonalIllness() {
        return historyOfPersonalIllness;
    }

    public void setHistoryOfPersonalIllness(String historyOfPersonalIllness) {
        this.historyOfPersonalIllness = historyOfPersonalIllness;
    }

    public String getHistoryOfFamilyIllness() {
        return historyOfFamilyIllness;
    }

    public void setHistoryOfFamilyIllness(String historyOfFamilyIllness) {
        this.historyOfFamilyIllness = historyOfFamilyIllness;
    }

    public Integer getElderId() {
        return elderId;
    }

    public void setElderId(Integer elderId) {
        this.elderId = elderId;
    }
}
