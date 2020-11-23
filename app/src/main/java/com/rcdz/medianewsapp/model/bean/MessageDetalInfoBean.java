package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/11/4 11:08
 */
public class MessageDetalInfoBean implements Serializable {

    /**
     * status : true
     * code : 200
     * message : 查询成功!
     * data : {"id":37,"subject":"64646+","contents":"ceshi ","images":"Upload/Files/Livelihood_Feedback/2bf663251fdd42528b535fbc949a794d/small/0.41669806662035924.jpg","phoneNo":"6546","organizationId":45,"organizationName":"职能机构","type":"投诉","userTrueName":null,"auditUserId":null,"auditDate":null,"description":null,"isReply":0,"replyUserId":null,"replyContents":null,"replyDate":null,"time":null,"createID":16,"creator":"eer","createDate":"2020-11-03 19:59:26","isBlackList":0,"state":"0"}
     */

    private boolean status;
    private int code;
    private String message;
    private MessageDetalInfo data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageDetalInfo getData() {
        return data;
    }

    public void setData(MessageDetalInfo data) {
        this.data = data;
    }

    public static class MessageDetalInfo implements Serializable {
        /**
         * id : 37
         * subject : 64646+
         * contents : ceshi
         * images : Upload/Files/Livelihood_Feedback/2bf663251fdd42528b535fbc949a794d/small/0.41669806662035924.jpg
         * phoneNo : 6546
         * organizationId : 45
         * organizationName : 职能机构
         * type : 投诉
         * userTrueName : null
         * auditUserId : null
         * auditDate : null
         * description : null
         * isReply : 0
         * replyUserId : null
         * replyContents : null
         * replyDate : null
         * time : null
         * createID : 16
         * creator : eer
         * createDate : 2020-11-03 19:59:26
         * isBlackList : 0
         * state : 0
         */

        private int id;
        private String subject;
        private String contents;
        private String images;
        private String phoneNo;
        private int organizationId;
        private String organizationName;
        private String type;
        private Object userTrueName;
        private Object auditUserId;
        private Object auditDate;
        private Object description;
        private int isReply;
        private Object replyUserId;
        private Object replyContents;
        private Object replyDate;
        private Object time;
        private int createID;
        private String creator;
        private String createDate;
        private int isBlackList;
        private String state;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getContents() {
            return contents;
        }

        public void setContents(String contents) {
            this.contents = contents;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public int getOrganizationId() {
            return organizationId;
        }

        public void setOrganizationId(int organizationId) {
            this.organizationId = organizationId;
        }

        public String getOrganizationName() {
            return organizationName;
        }

        public void setOrganizationName(String organizationName) {
            this.organizationName = organizationName;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getUserTrueName() {
            return userTrueName;
        }

        public void setUserTrueName(Object userTrueName) {
            this.userTrueName = userTrueName;
        }

        public Object getAuditUserId() {
            return auditUserId;
        }

        public void setAuditUserId(Object auditUserId) {
            this.auditUserId = auditUserId;
        }

        public Object getAuditDate() {
            return auditDate;
        }

        public void setAuditDate(Object auditDate) {
            this.auditDate = auditDate;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public int getIsReply() {
            return isReply;
        }

        public void setIsReply(int isReply) {
            this.isReply = isReply;
        }

        public Object getReplyUserId() {
            return replyUserId;
        }

        public void setReplyUserId(Object replyUserId) {
            this.replyUserId = replyUserId;
        }

        public Object getReplyContents() {
            return replyContents;
        }

        public void setReplyContents(Object replyContents) {
            this.replyContents = replyContents;
        }

        public Object getReplyDate() {
            return replyDate;
        }

        public void setReplyDate(Object replyDate) {
            this.replyDate = replyDate;
        }

        public Object getTime() {
            return time;
        }

        public void setTime(Object time) {
            this.time = time;
        }

        public int getCreateID() {
            return createID;
        }

        public void setCreateID(int createID) {
            this.createID = createID;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getIsBlackList() {
            return isBlackList;
        }

        public void setIsBlackList(int isBlackList) {
            this.isBlackList = isBlackList;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
