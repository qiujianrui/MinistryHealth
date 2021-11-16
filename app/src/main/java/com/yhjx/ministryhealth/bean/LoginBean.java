package com.yhjx.ministryhealth.bean;

public class LoginBean {

    /**
     * name : 111123211321
     * personImg : http://120.79.134.115/img/touxiang.png
     * phone : 17388768837
     * token : 2fb153c41f4b9ceddbaf7df3a7ddcbe4
     * patientId : 123123123
     * pwd : null
     * newPassword : null
     * surePassword : null
     */

    private String name;
    private String personImg;
    private String phone;
    private String token;
    private String patientId;
    private Object pwd;
    private Object newPassword;
    private Object surePassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonImg() {
        return personImg;
    }

    public void setPersonImg(String personImg) {
        this.personImg = personImg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Object getPwd() {
        return pwd;
    }

    public void setPwd(Object pwd) {
        this.pwd = pwd;
    }

    public Object getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(Object newPassword) {
        this.newPassword = newPassword;
    }

    public Object getSurePassword() {
        return surePassword;
    }

    public void setSurePassword(Object surePassword) {
        this.surePassword = surePassword;
    }
}
