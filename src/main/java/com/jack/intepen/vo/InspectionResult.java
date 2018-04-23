package com.jack.intepen.vo;

import java.sql.Date;

/**
 * Created by 11407 on 23/023.
 */
public class InspectionResult {
//id: 110,
// name: '吴xx',
// sex: '男',
// age: 233,
// idCard: 1111111111,
// birthday: '2000-01-01',
// dataTime: this.today,
// temperature: '36',
// bloodPressure: '70/110',
// illness: 1,
// docName: '小明',
// record: 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaa',
// tel: 13032885319
    private Integer elderId;
    private String name;
    private String sex;
    private String idCard;
    private Date birthday;
    private Date date;
    private Integer temperature;
    private String bloodPressure;
    private Integer illnessId;
    private String nurseName;
    private String record;
    private String tel;

    public Integer getElderId() {
        return elderId;
    }

    public void setElderId(Integer elderId) {
        this.elderId = elderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public Integer getIllnessId() {
        return illnessId;
    }

    public void setIllnessId(Integer illnessId) {
        this.illnessId = illnessId;
    }

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
