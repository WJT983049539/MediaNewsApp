package com.rcdz.medianewsapp.model.bean;

import android.app.Service;

import java.io.Serializable;
import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/27 10:41
 */

public class TopVideoNewBean implements Serializable {

    /**
     * status : true
     * code : 200
     * message : 获取置顶新闻成功
     * data : [{"id":"e3a80aa1-65b9-4f31-9b1a-8ddbb8f56271","title":"华裔投票拜登：若特朗普连任，就搬去其他国家","longTitle":"华裔投票拜登：若特朗普连任，就搬去其他国家","styleType":null,"coverUrl":"Upload/Files/Video/aef569b5f845437a86fb2df3d0147293/small/logo.png","imageUrl":null,"creator":"测试用户1","publishDate":"2020-11-06 10:00:00","publishDateString":"4小时前","targetId":93,"sectionId":2,"locationSection":"1","activityType":-1,"commentCount":1,"type":2,"contentFontFize":null,"titleFontSize":null},{"id":"77baf13c-899c-4272-842f-7185ed3354fb","title":"城市美景","longTitle":"城市美景","styleType":null,"coverUrl":"Upload/Files/Video/8882f2594f4649c7b6d435a37a521b4e/small/RkyLeDxJB10VMW.jpg","imageUrl":null,"creator":"测试用户2","publishDate":"2020-11-03 17:23:32","publishDateString":"2天前","targetId":86,"sectionId":1,"locationSection":"1","activityType":-1,"commentCount":0,"type":2,"contentFontFize":null,"titleFontSize":null},{"id":"4f705ca7-4196-4f20-a119-a3f19343ec2d","title":"城市美景","longTitle":"城市美景","styleType":null,"coverUrl":"Upload/Files/Video/8882f2594f4649c7b6d435a37a521b4e/small/RkyLeDxJB10VMW.jpg","imageUrl":null,"creator":"测试用户2","publishDate":"2020-11-03 17:23:32","publishDateString":"2天前","targetId":86,"sectionId":2,"locationSection":"1","activityType":-1,"commentCount":0,"type":2,"contentFontFize":null,"titleFontSize":null}]
     */

    private boolean status;
    private int code;
    private String message;
    private List<TopVideoNew> data;

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

    public List<TopVideoNew> getData() {
        return data;
    }

    public void setData(List<TopVideoNew> data) {
        this.data = data;
    }

    public static class TopVideoNew implements Serializable {
        /**
         * id : e3a80aa1-65b9-4f31-9b1a-8ddbb8f56271
         * title : 华裔投票拜登：若特朗普连任，就搬去其他国家
         * longTitle : 华裔投票拜登：若特朗普连任，就搬去其他国家
         * styleType : null
         * coverUrl : Upload/Files/Video/aef569b5f845437a86fb2df3d0147293/small/logo.png
         * imageUrl : null
         * creator : 测试用户1
         * publishDate : 2020-11-06 10:00:00
         * publishDateString : 4小时前
         * targetId : 93
         * sectionId : 2
         * locationSection : 1
         * activityType : -1
         * commentCount : 1
         * type : 2
         * contentFontFize : null
         * titleFontSize : null
         */

        private String id;
        private String title;
        private String longTitle;
        private Object styleType;
        private String coverUrl;
        private Object imageUrl;
        private String creator;
        private String publishDate;
        private String publishDateString;
        private int targetId;
        private int sectionId;
        private String locationSection;
        private int activityType;
        private int commentCount;
        private int type;
        private Object contentFontFize;
        private Object titleFontSize;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLongTitle() {
            return longTitle;
        }

        public void setLongTitle(String longTitle) {
            this.longTitle = longTitle;
        }

        public Object getStyleType() {
            return styleType;
        }

        public void setStyleType(Object styleType) {
            this.styleType = styleType;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public Object getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(Object imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public String getPublishDateString() {
            return publishDateString;
        }

        public void setPublishDateString(String publishDateString) {
            this.publishDateString = publishDateString;
        }

        public int getTargetId() {
            return targetId;
        }

        public void setTargetId(int targetId) {
            this.targetId = targetId;
        }

        public int getSectionId() {
            return sectionId;
        }

        public void setSectionId(int sectionId) {
            this.sectionId = sectionId;
        }

        public String getLocationSection() {
            return locationSection;
        }

        public void setLocationSection(String locationSection) {
            this.locationSection = locationSection;
        }

        public int getActivityType() {
            return activityType;
        }

        public void setActivityType(int activityType) {
            this.activityType = activityType;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getContentFontFize() {
            return contentFontFize;
        }

        public void setContentFontFize(Object contentFontFize) {
            this.contentFontFize = contentFontFize;
        }

        public Object getTitleFontSize() {
            return titleFontSize;
        }

        public void setTitleFontSize(Object titleFontSize) {
            this.titleFontSize = titleFontSize;
        }
    }
}
