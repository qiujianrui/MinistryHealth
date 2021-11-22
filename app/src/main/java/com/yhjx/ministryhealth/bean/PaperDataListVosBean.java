package com.yhjx.ministryhealth.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class PaperDataListVosBean implements MultiItemEntity {
    /**
     * title : 大萨达
     * type : 0
     * paperDataVos : [{"optional":"大萨达","isSelected":"false","count":null},{"optional":"打","isSelected":"false","count":null},{"optional":"打","isSelected":"false","count":null},{"optional":"双打","isSelected":"false","count":null}]
     */

    private String title;
    private String type;
    private List<PaperDataVosBean> paperDataVos;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PaperDataVosBean> getPaperDataVos() {
        return paperDataVos;
    }

    public void setPaperDataVos(List<PaperDataVosBean> paperDataVos) {
        this.paperDataVos = paperDataVos;
    }

    @Override
    public int getItemType() {
        return Integer.parseInt(type);
    }

    public static class PaperDataVosBean {
        /**
         * optional : 大萨达
         * isSelected : false
         * count : null
         */

        private String optional;
        private String isSelected;
        private Object count;

        public String getOptional() {
            return optional;
        }

        public void setOptional(String optional) {
            this.optional = optional;
        }

        public String getIsSelected() {
            return isSelected;
        }

        public void setIsSelected(String isSelected) {
            this.isSelected = isSelected;
        }

        public Object getCount() {
            return count;
        }

        public void setCount(Object count) {
            this.count = count;
        }
    }
}
