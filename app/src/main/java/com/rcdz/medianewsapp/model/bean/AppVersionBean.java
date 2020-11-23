package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/11/3 14:57
 */
public class AppVersionBean implements Serializable {
    /**
     * status : true
     * code : 200
     * message : 查询成功!
     * data : {"id":1,"version":"12345689","description":"测试版本","updateDescription":"测试","isMaster":1,"isForce":0,"appType":0,"os":0,"createID":4,"creator":"测试用户3","createDate":"2020-11-01 17:08:43"}
     */

    private boolean status;
    private int code;
    private String message;
    private AppVersionInfo data;

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

    public AppVersionInfo getData() {
        return data;
    }

    public void setData(AppVersionInfo data) {
        this.data = data;
    }

    public static class AppVersionInfo implements Serializable {
        /**
         * id : 1
         * version : 12345689
         * description : 测试版本
         * updateDescription : 测试
         * isMaster : 1
         * isForce : 0
         * appType : 0
         * os : 0
         * createID : 4
         * creator : 测试用户3
         * createDate : 2020-11-01 17:08:43
         */

        private int id;
        private String version;
        private String description;
        private String updateDescription;
        private int isMaster;
        private int isForce;
        private int appType;
        private int os;
        private int createID;
        private String creator;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        private String url;
        private String createDate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUpdateDescription() {
            return updateDescription;
        }

        public void setUpdateDescription(String updateDescription) {
            this.updateDescription = updateDescription;
        }

        public int getIsMaster() {
            return isMaster;
        }

        public void setIsMaster(int isMaster) {
            this.isMaster = isMaster;
        }

        public int getIsForce() {
            return isForce;
        }

        public void setIsForce(int isForce) {
            this.isForce = isForce;
        }

        public int getAppType() {
            return appType;
        }

        public void setAppType(int appType) {
            this.appType = appType;
        }

        public int getOs() {
            return os;
        }

        public void setOs(int os) {
            this.os = os;
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
    }
}
