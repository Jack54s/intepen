package com.jack.intepen.entity;

import java.sql.Date;

/**
 * Created by 11407 on 23/023.
 */
public class PatientStatistic {

    private Integer id;
    private Integer illnessId;
    private Integer numberOfPatient;
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIllnessId() {
        return illnessId;
    }

    public void setIllnessId(Integer illnessId) {
        this.illnessId = illnessId;
    }

    public Integer getNumberOfPatient() {
        return numberOfPatient;
    }

    public void setNumberOfPatient(Integer numberOfPatient) {
        this.numberOfPatient = numberOfPatient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
