package com.yhjx.ministryhealth.bean;

import java.util.List;

public class IndexBean {

    /**
     * hotTel : 0244200242420
     * indexDateListVos : [{"isTakeRemind":"0","isVisitRemind":"1","englishWeekDate":"S","chineseDate":"15"},{"isTakeRemind":"1","isVisitRemind":"0","englishWeekDate":"M","chineseDate":"16"},{"isTakeRemind":"0","isVisitRemind":"1","englishWeekDate":"T","chineseDate":"17"},{"isTakeRemind":"1","isVisitRemind":"0","englishWeekDate":"W","chineseDate":"18"},{"isTakeRemind":"0","isVisitRemind":"1","englishWeekDate":"T","chineseDate":"19"},{"isTakeRemind":"1","isVisitRemind":"0","englishWeekDate":"F","chineseDate":"20"},{"isTakeRemind":"0","isVisitRemind":"1","englishWeekDate":"S","chineseDate":"21"}]
     * indexMsgVo : {"messageCount":"8","messageTitle":"复诊通知","messageDate":"2021-8-8","messageReceiver":"亲爱的张三","messageContent":"xxx.xxx.x.x","messageSender":"深圳市精神卫生中心"}
     */

    private String hotTel;
    private IndexMsgVoBean indexMsgVo;
    private List<IndexDateListVosBean> indexDateListVos;

    public String getHotTel() {
        return hotTel;
    }

    public void setHotTel(String hotTel) {
        this.hotTel = hotTel;
    }

    public IndexMsgVoBean getIndexMsgVo() {
        return indexMsgVo;
    }

    public void setIndexMsgVo(IndexMsgVoBean indexMsgVo) {
        this.indexMsgVo = indexMsgVo;
    }

    public List<IndexDateListVosBean> getIndexDateListVos() {
        return indexDateListVos;
    }

    public void setIndexDateListVos(List<IndexDateListVosBean> indexDateListVos) {
        this.indexDateListVos = indexDateListVos;
    }

    public static class IndexMsgVoBean {
        /**
         * messageCount : 8
         * messageTitle : 复诊通知
         * messageDate : 2021-8-8
         * messageReceiver : 亲爱的张三
         * messageContent : xxx.xxx.x.x
         * messageSender : 深圳市精神卫生中心
         */

        private String messageCount;
        private String messageTitle;
        private String messageDate;
        private String messageReceiver;
        private String messageContent;
        private String messageSender;

        public String getMessageCount() {
            return messageCount;
        }

        public void setMessageCount(String messageCount) {
            this.messageCount = messageCount;
        }

        public String getMessageTitle() {
            return messageTitle;
        }

        public void setMessageTitle(String messageTitle) {
            this.messageTitle = messageTitle;
        }

        public String getMessageDate() {
            return messageDate;
        }

        public void setMessageDate(String messageDate) {
            this.messageDate = messageDate;
        }

        public String getMessageReceiver() {
            return messageReceiver;
        }

        public void setMessageReceiver(String messageReceiver) {
            this.messageReceiver = messageReceiver;
        }

        public String getMessageContent() {
            return messageContent;
        }

        public void setMessageContent(String messageContent) {
            this.messageContent = messageContent;
        }

        public String getMessageSender() {
            return messageSender;
        }

        public void setMessageSender(String messageSender) {
            this.messageSender = messageSender;
        }
    }

    public static class IndexDateListVosBean {
        /**
         * isTakeRemind : 0
         * isVisitRemind : 1
         * englishWeekDate : S
         * chineseDate : 15
         */

        private String isTakeRemind;
        private String isVisitRemind;
        private String englishWeekDate;
        private String chineseDate;

        public String getIsTakeRemind() {
            return isTakeRemind;
        }

        public void setIsTakeRemind(String isTakeRemind) {
            this.isTakeRemind = isTakeRemind;
        }

        public String getIsVisitRemind() {
            return isVisitRemind;
        }

        public void setIsVisitRemind(String isVisitRemind) {
            this.isVisitRemind = isVisitRemind;
        }

        public String getEnglishWeekDate() {
            return englishWeekDate;
        }

        public void setEnglishWeekDate(String englishWeekDate) {
            this.englishWeekDate = englishWeekDate;
        }

        public String getChineseDate() {
            return chineseDate;
        }

        public void setChineseDate(String chineseDate) {
            this.chineseDate = chineseDate;
        }
    }
}
