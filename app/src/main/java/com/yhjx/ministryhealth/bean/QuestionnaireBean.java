package com.yhjx.ministryhealth.bean;

import java.util.List;

public class QuestionnaireBean {

    /**
     * dateCreate : 2021-11-10
     * paperData : 收到的
     * paperDataListVos : [{"title":"大萨达","type":"0","paperDataVos":[{"optional":"大萨达","isSelected":"false","count":null},{"optional":"打","isSelected":"false","count":null},{"optional":"打","isSelected":"false","count":null},{"optional":"双打","isSelected":"false","count":null}]},{"title":"撒大大","type":"1","paperDataVos":[{"optional":"打打","isSelected":"false","count":null},{"optional":"大萨达","isSelected":"false","count":null},{"optional":"打","isSelected":"false","count":null},{"optional":"打撒","isSelected":"false","count":null}]},{"title":"大大大","type":"2","paperDataVos":[{"optional":"","isSelected":"false","count":null}]}]
     */

    private String dateCreate;
    private String paperData;
    private List<PaperDataListVosBean> paperDataListVos;

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getPaperData() {
        return paperData;
    }

    public void setPaperData(String paperData) {
        this.paperData = paperData;
    }

    public List<PaperDataListVosBean> getPaperDataListVos() {
        return paperDataListVos;
    }

    public void setPaperDataListVos(List<PaperDataListVosBean> paperDataListVos) {
        this.paperDataListVos = paperDataListVos;
    }


}
