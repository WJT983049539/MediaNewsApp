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
     * data : {"user_Id":16,"userName":"ccaaa","userTrueName":"eer","address":"address","phoneNo":"15935938255","email":null,"remark":"remake","gender":0,"roleName":null,"headImageUrl":null,"createDate":"2020-10-13 08:48:43"}
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

    public static class DataBean {

        @Override
        public String toString() {
            return "DataBean{" +
                    "user_Id=" + user_Id +
                    ", userName='" + userName + '\'' +
                    ", userTrueName='" + userTrueName + '\'' +
                    ", address='" + address + '\'' +
                    ", phoneNo='" + phoneNo + '\'' +
                    ", email=" + email +
                    ", remark='" + remark + '\'' +
                    ", gender=" + gender +
                    ", roleName=" + roleName +
                    ", headImageUrl=" + headImageUrl +
                    ", createDate='" + createDate + '\'' +
                    '}';
        }

        /**
         * user_Id : 16
         * userName : ccaaa
         * userTrueName : eer
         * address : address
         * phoneNo : 15935938255
         * email : null
         * remark : remake
         * gender : 0
         * roleName : null
         * headImageUrl : null
         * createDate : 2020-10-13 08:48:43
         */

        private int user_Id;
        private String userName;
        private String userTrueName;
        private String address;
        private String phoneNo;
        private Object email;
        private String remark;
        private int gender;
        private Object roleName;
        private Object headImageUrl;
        private String createDate;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public Object getRoleName() {
            return roleName;
        }

        public void setRoleName(Object roleName) {
            this.roleName = roleName;
        }

        public Object getHeadImageUrl() {
            return headImageUrl;
        }

        public void setHeadImageUrl(Object headImageUrl) {
            this.headImageUrl = headImageUrl;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
