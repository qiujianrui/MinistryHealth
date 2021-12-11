package com.yhjx.ministryhealth.bean;

public class DrugNameBean {

    /**
     * params : {}
     * id : 68
     * name : 佐匹克隆片
     */

    private ParamsBean params;
    private int id;
    private String name;

    public ParamsBean getParams() {
        return params;
    }

    public void setParams(ParamsBean params) {
        this.params = params;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class ParamsBean {
    }
}
