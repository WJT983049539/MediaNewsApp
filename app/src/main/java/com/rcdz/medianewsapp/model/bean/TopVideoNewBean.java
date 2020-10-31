package com.rcdz.medianewsapp.model.bean;

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
     * data : [{"id":"eee07256-43a0-4df2-a323-d9a4918e584c","title":"视频测试","longTitle":"视频测试视频测试视频测试视频测试","styleType":"04","coverUrl":"Upload/Files/Video/fd6adf1943d248e4b5b0cde11a08dd19/small/06.jpg","imageUrl":null,"creator":"测试用户1","publishDate":"2020-02-02 00:00:00","publishDateString":"8个月前","sectionId":3,"locationSection":"1","commentCount":0,"type":2,"contentFontFize":"12","titleFontSize":"14"}]
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
         * id : eee07256-43a0-4df2-a323-d9a4918e584c
         * title : 视频测试
         * longTitle : 视频测试视频测试视频测试视频测试
         * styleType : 04
         * coverUrl : Upload/Files/Video/fd6adf1943d248e4b5b0cde11a08dd19/small/06.jpg
         * imageUrl : null
         * creator : 测试用户1
         * publishDate : 2020-02-02 00:00:00
         * publishDateString : 8个月前
         * sectionId : 3
         * locationSection : 1
         * commentCount : 0
         * type : 2
         * contentFontFize : 12
         * titleFontSize : 14
         */

        private String id;
        private String title;
        private String longTitle;
        private String styleType;
        private String coverUrl;
        private Object imageUrl;
        private String creator;
        private String publishDate;
        private String publishDateString;
        private int sectionId;
        private String locationSection;
        private int commentCount;
        private int type;
        private String contentFontFize;
        private String titleFontSize;

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

        public String getStyleType() {
            return styleType;
        }

        public void setStyleType(String styleType) {
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

        public String getContentFontFize() {
            return contentFontFize;
        }

        public void setContentFontFize(String contentFontFize) {
            this.contentFontFize = contentFontFize;
        }

        public String getTitleFontSize() {
            return titleFontSize;
        }

        public void setTitleFontSize(String titleFontSize) {
            this.titleFontSize = titleFontSize;
        }
    }
}
