package com.yhjx.ministryhealth.bean;

public class ChatMsgBean {

    /**
     * doctor : 深圳市精神卫生中心
     * doctorImg : http://120.79.134.115/img/touxiang.png
     * patientImg : http://120.79.134.115/img/touxiang.png
     * dateSend : 2021-11-09
     * dataMsg : 哈哈
     * msgType : 1
     */

    private String doctor;
    private String doctorImg;
    private String patientImg;
    private String dateSend;
    private String dataMsg;
    private String msgType;

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDoctorImg() {
        return doctorImg;
    }

    public void setDoctorImg(String doctorImg) {
        this.doctorImg = doctorImg;
    }

    public String getPatientImg() {
        return patientImg;
    }

    public void setPatientImg(String patientImg) {
        this.patientImg = patientImg;
    }

    public String getDateSend() {
        return dateSend;
    }

    public void setDateSend(String dateSend) {
        this.dateSend = dateSend;
    }

    public String getDataMsg() {
        return dataMsg;
    }

    public void setDataMsg(String dataMsg) {
        this.dataMsg = dataMsg;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
}
