package com.jack.intepen.entity;

import java.sql.Date;

/**
 * Created by 11407 on 12/012.
 */
public class DoctorAdvice {

    private Integer id;
    private Integer elderId;
    private Date startDate;
    private Date endDate;
    private String medicine;
    private String time;
    private String dosage;
    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getElderId() {
        return elderId;
    }

    public void setElderId(Integer elderId) {
        this.elderId = elderId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
