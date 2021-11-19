package com.yhjx.ministryhealth.bean;

public class MsgListBean {

    /**
     * messageTitle : 问卷通知
     * messageDate : 2021-11-09
     * messageReceiver : 3
     * messageContent : 哈哈
     * messageSender : 深圳市精神卫生中心
     * type : 1
     * id : 1
     */

    private String messageTitle;
    private String messageDate;
    private String messageReceiver;
    private String messageContent;
    private String messageSender;
    private String type;
    private String id;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
