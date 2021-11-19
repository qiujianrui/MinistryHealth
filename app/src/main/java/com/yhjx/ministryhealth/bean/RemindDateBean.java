package com.yhjx.ministryhealth.bean;

import java.util.List;

public class RemindDateBean {

    private List<String> takeRemindTimeList;
    private List<String> visitRemindTimeList;

    public List<String> getTakeRemindTimeList() {
        return takeRemindTimeList;
    }

    public void setTakeRemindTimeList(List<String> takeRemindTimeList) {
        this.takeRemindTimeList = takeRemindTimeList;
    }

    public List<String> getVisitRemindTimeList() {
        return visitRemindTimeList;
    }

    public void setVisitRemindTimeList(List<String> visitRemindTimeList) {
        this.visitRemindTimeList = visitRemindTimeList;
    }
}
