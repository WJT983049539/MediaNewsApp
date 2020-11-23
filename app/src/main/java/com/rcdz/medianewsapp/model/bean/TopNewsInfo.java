package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/27 10:47
 */
public class TopNewsInfo implements Serializable {
    /**
     * status : true
     * code : 200
     * message : 获取置顶新闻成功
     * data : [{"id":"b2b2889d-05f9-4848-8af8-6083a1c39dd4","title":"华裔投票拜登：若特朗普连任，就搬去其他国家","longTitle":"华裔投票拜登：若特朗普连任，就搬去其他国家","styleType":null,"coverUrl":"Upload/Files/Video/aef569b5f845437a86fb2df3d0147293/small/logo.png","imageUrl":null,"creator":"测试用户1","publishDate":"2020-11-06 10:00:00","publishDateString":"4小时前","targetId":93,"sectionId":2,"locationSection":"1","activityType":-1,"commentCount":1,"type":2,"contentFontFize":null,"titleFontSize":null},{"id":"61b23a92-dcfb-4e70-ba93-2cb3bd9497bb","title":"测试图集","longTitle":"测试图集测试图集测试图集测试图集测试图集测试图集","styleType":"06","coverUrl":"Upload/Files/ImageSet/716f37ddfa2f414b89d73d0aed190940/small/logo.png","imageUrl":null,"creator":"测试用户1","publishDate":"2020-11-06 09:52:06","publishDateString":"4小时前","targetId":62,"sectionId":2,"locationSection":"1","activityType":-1,"commentCount":0,"type":3,"contentFontFize":null,"titleFontSize":null},{"id":"25e9fed3-1a4a-4c70-b340-d82275e42aea","title":"测试图集2","longTitle":"测试图集2","styleType":"06","coverUrl":"Upload/Files/ImageSet/512822e07c7c45f2b0f183a78575e8c1/small/logo.png","imageUrl":null,"creator":"测试用户1","publishDate":"2020-11-06 09:51:07","publishDateString":"4小时前","targetId":63,"sectionId":3,"locationSection":"1","activityType":-1,"commentCount":0,"type":3,"contentFontFize":null,"titleFontSize":null}]
     */

    private boolean status;
    private int code;
    private String message;
    private List<TopNews> data;

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

    public List< TopNews> getData() {
        return data;
    }

    public void setData(List< TopNews> data) {
        this.data = data;
    }

    public static class TopNews implements Serializable {
        /**
         * id : b2b2889d-05f9-4848-8af8-6083a1c39dd4
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
