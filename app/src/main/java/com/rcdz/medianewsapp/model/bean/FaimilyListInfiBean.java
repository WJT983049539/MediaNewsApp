package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/23 9:01
 */
public class FaimilyListInfiBean implements Serializable {


    /**
     * status : true
     * code : 200
     * message : 查询成功!
     * data : [{"createDate":"0001-01-01 00:00:00","familyId":-1,"joinType":0,"userId":16,"headImageUrl":null,"userTrueName":"测试72"}]
     */

    private boolean status;
    private int code;
    private String message;
    private List<FaimilyInfo> data;

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

    public List<FaimilyInfo> getData() {
        return data;
    }

    public void setData(List<FaimilyInfo> data) {
        this.data = data;
    }

    public static class FaimilyInfo implements Serializable {
        /**
         * createDate : 0001-01-01 00:00:00
         * familyId : -1
         * joinType : 0
         * userId : 16
         * headImageUrl : null
         * userTrueName : 测试72
         */

        private String createDate;
        private int familyId;
        private int joinType;
        private int userId;
        private Object headImageUrl;
        private String userTrueName;

        public Boolean getRealse() {
            return isRealse;
        }

        public void setRealse(Boolean realse) {
            isRealse = realse;
        }

        private Boolean isRealse=true; //是否为真实数据

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getFamilyId() {
            return familyId;
        }

        public void setFamilyId(int familyId) {
            this.familyId = familyId;
        }

        public int getJoinType() {
            return joinType;
        }

        public void setJoinType(int joinType) {
            this.joinType = joinType;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public Object getHeadImageUrl() {
            return headImageUrl;
        }

        public void setHeadImageUrl(Object headImageUrl) {
            this.headImageUrl = headImageUrl;
        }

        public String getUserTrueName() {
            return userTrueName;
        }

        public void setUserTrueName(String userTrueName) {
            this.userTrueName = userTrueName;
        }
    }
}
