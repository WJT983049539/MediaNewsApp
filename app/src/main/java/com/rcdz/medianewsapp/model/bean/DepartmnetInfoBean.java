package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作用:机构信息
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/17 16:54
 */
public class DepartmnetInfoBean implements Serializable {

    /**
     * status : true
     * code : 200
     * message : 查询成功!
     * data : [{"feedbackReplynum":0,"feedbackNum":1,"contactsPhone":null,"name":"A"},{"feedbackReplynum":2,"feedbackNum":3,"contactsPhone":null,"name":"B"},{"feedbackReplynum":0,"feedbackNum":4,"contactsPhone":null,"name":"c"},{"feedbackReplynum":0,"feedbackNum":0,"contactsPhone":null,"name":"D"}]
     */

    private boolean status;
    private int code;
    private String message;
    private List<DepartmnetInfo> data;

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

    public List<DepartmnetInfo> getData() {
        return data;
    }

    public void setData(List<DepartmnetInfo> data) {
        this.data = data;
    }

    public static class DepartmnetInfo implements Serializable {
        /**
         * feedbackReplynum : 0
         * feedbackNum : 1
         * contactsPhone : null
         * name : A
         */

        private int feedbackReplynum;
        private int feedbackNum;
        private Object contactsPhone;
        private String name;

        public int getFeedbackReplynum() {
            return feedbackReplynum;
        }

        public void setFeedbackReplynum(int feedbackReplynum) {
            this.feedbackReplynum = feedbackReplynum;
        }

        public int getFeedbackNum() {
            return feedbackNum;
        }

        public void setFeedbackNum(int feedbackNum) {
            this.feedbackNum = feedbackNum;
        }

        public Object getContactsPhone() {
            return contactsPhone;
        }

        public void setContactsPhone(Object contactsPhone) {
            this.contactsPhone = contactsPhone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
