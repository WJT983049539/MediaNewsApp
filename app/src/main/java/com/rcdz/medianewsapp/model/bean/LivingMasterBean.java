package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/21 11:38
 */
public  class LivingMasterBean implements Serializable {

    /**
     * status : true
     * code : 200
     * message : null
     * data : {"user_Id":1,"userName":"admin","userTrueName":"超级管理员","headImageUrl":""}
     */

    private boolean status;
    private int code;
    private Object message;
    private DataBean data;

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

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * user_Id : 1
         * userName : admin
         * userTrueName : 超级管理员
         * headImageUrl :
         */

        private int user_Id;
        private String userName;
        private String userTrueName;
        private Object headImageUrl;

        public int getUser_Id() {
            return user_Id;
        }

        public void setUser_Id(int user_Id) {
            this.user_Id = user_Id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserTrueName() {
            return userTrueName;
        }

        public void setUserTrueName(String userTrueName) {
            this.userTrueName = userTrueName;
        }

        public Object getHeadImageUrl() {
            return headImageUrl;
        }

        public void setHeadImageUrl(String headImageUrl) {
            this.headImageUrl = headImageUrl;
        }
    }
}
