package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/17 18:21
 */
public class LeaveMegBean implements Serializable {
    private MainData mainData;

    public MainData getMainData() {
        return mainData;
    }

    public void setMainData(MainData mainData) {
        this.mainData = mainData;
    }

    public static class MainData implements Serializable{
        private String Subject;
        private String Contents;
        private String Images;
        private String PhoneNo;
        private int OrganizationId;
        private String OrganizationName;
        private String Type;
        private int IsReply;
        private int IsBlackList;
        private String State;

        public String getSubject() {
            return Subject;
        }

        public void setSubject(String subject) {
            Subject = subject;
        }

        public String getContents() {
            return Contents;
        }

        public void setContents(String contents) {
            Contents = contents;
        }

        public String getImages() {
            return Images;
        }

        public void setImages(String images) {
            Images = images;
        }

        public String getPhoneNo() {
            return PhoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            PhoneNo = phoneNo;
        }

        public int getOrganizationId() {
            return OrganizationId;
        }

        public void setOrganizationId(int organizationId) {
            OrganizationId = organizationId;
        }

        public String getOrganizationName() {
            return OrganizationName;
        }

        public void setOrganizationName(String organizationName) {
            OrganizationName = organizationName;
        }

        public String getType() {
            return Type;
        }

        public void setType(String type) {
            Type = type;
        }

        public int getIsReply() {
            return IsReply;
        }

        public void setIsReply(int isReply) {
            IsReply = isReply;
        }

        public int getIsBlackList() {
            return IsBlackList;
        }

        public void setIsBlackList(int isBlackList) {
            IsBlackList = isBlackList;
        }

        public String getState() {
            return State;
        }

        public void setState(String state) {
            State = state;
        }
    }


}
