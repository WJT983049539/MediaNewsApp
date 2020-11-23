package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作用: 新闻列表
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/13 19:33
 */
public class NewsListBean implements Serializable {


    /**
     * code : 200
     * message : null
     * status : 0
     * msg : null
     * total : 5
     * rows : [{"Id":"766f51a4-b4fb-4885-b4e7-1badb2ce2246","Title":"1111","LongTitle":"11111","StyleType":null,"CoverUrl":"Upload/Files/Video/08a37dc9c3a84954a306da11c1553449/small/01200000169026136208529565374.jpg","ImageUrl":null,"Creator":"测试用户1","PublishDate":"2020-10-13 15:10:00","PublishDateString":"17小时前","SectionId":2,"LocationSection":"2","CommentCount":0,"Type":1,"ContentFontFize":"12","TitleFontSize":"15"},{"Id":"6110eff5-be94-4437-8df7-13c8c66ff005","Title":"撒旦法撒旦法","LongTitle":"sad发电房","StyleType":"06","CoverUrl":"Upload/Files/Article/51955165984149e9a7a4a70913ee88f6/small/05.jpg,Upload/Files/Article/8ca7feef04374f1989cb05c45fb66101/small/07.jpg","ImageUrl":null,"Creator":"超级管理员","PublishDate":"2020-10-13 10:00:00","PublishDateString":"22小时前","SectionId":1,"LocationSection":null,"CommentCount":0,"Type":1,"ContentFontFize":null,"TitleFontSize":null},{"Id":"a0ada17a-91d9-419d-b4f6-c62798d1b4db","Title":"第三方大厦","LongTitle":"手打阿萨德","StyleType":"02","CoverUrl":"Upload/Files/Article/5d32bc064c91408b9fad8549ad3bc61a/small/06.jpg","ImageUrl":null,"Creator":"测试用户1","PublishDate":"2020-03-02 20:10:00","PublishDateString":"7个月前","SectionId":1,"LocationSection":"1","CommentCount":1,"Type":1,"ContentFontFize":null,"TitleFontSize":null},{"Id":"21706422-1f5b-473d-85eb-6750995e2f2d","Title":"视频测试","LongTitle":"视频测试视频测试视频测试视频测试","StyleType":"04","CoverUrl":"Upload/Files/Video/fd6adf1943d248e4b5b0cde11a08dd19/small/06.jpg","ImageUrl":null,"Creator":"测试用户1","PublishDate":"2020-02-02 00:00:00","PublishDateString":"8个月前","SectionId":3,"LocationSection":null,"CommentCount":0,"Type":2,"ContentFontFize":"12","TitleFontSize":"14"},{"Id":"73855180-7a78-4613-a409-bbc0abd77500","Title":"第三方大厦","LongTitle":"手打阿萨德","StyleType":"02","CoverUrl":"Upload/Files/Article/5d32bc064c91408b9fad8549ad3bc61a/small/06.jpg","ImageUrl":null,"Creator":"测试用户1","PublishDate":"2020-01-01 09:10:00","PublishDateString":"9个月前","SectionId":2,"LocationSection":"1","CommentCount":1,"Type":1,"ContentFontFize":"12","TitleFontSize":"15"}]
     * summary : null
     * extra : null
     */

    private int code;
    private Object message;
    private int status;
    private Object msg;
    private int total;
    private Object summary;
    private Object extra;
    private List<NewsInfo> rows;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getSummary() {
        return summary;
    }

    public void setSummary(Object summary) {
        this.summary = summary;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    public List<NewsInfo> getRows() {
        return rows;
    }

    public void setRows(List<NewsInfo> rows) {
        this.rows = rows;
    }

    public static class NewsInfo implements Serializable {
        /**
         * Id : 766f51a4-b4fb-4885-b4e7-1badb2ce2246
         * Title : 1111
         * LongTitle : 11111
         * StyleType : null
         * CoverUrl : Upload/Files/Video/08a37dc9c3a84954a306da11c1553449/small/01200000169026136208529565374.jpg
         * ImageUrl : null
         * Creator : 测试用户1
         * PublishDate : 2020-10-13 15:10:00
         * PublishDateString : 17小时前
         * SectionId : 2
         * LocationSection : 2
         * CommentCount : 0
         * Type : 1
         * ContentFontFize : 12
         * TitleFontSize : 15
         */

        private String Id;
        private String Title;
        private String LongTitle;
        private String StyleType;
        private String CoverUrl;
        private Object ImageUrl;
        private String Creator;
        private String PublishDate;
        private String PublishDateString;
        private int SectionId;

        public String getSectionName() {
            return SectionName;
        }

        public void setSectionName(String sectionName) {
            SectionName = sectionName;
        }

        private String SectionName;

        public int getTargetId() {
            return TargetId;
        }

        public void setTargetId(int targetId) {
            TargetId = targetId;
        }

        private int TargetId;
        private String LocationSection;

        public int getActivityType() {
            return ActivityType;
        }

        public void setActivityType(int activityType) {
            ActivityType = activityType;
        }

        private int ActivityType;
        private int CommentCount;
        private int Type;
        private String ContentFontFize;
        private String TitleFontSize;

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getLongTitle() {
            return LongTitle;
        }

        public void setLongTitle(String LongTitle) {
            this.LongTitle = LongTitle;
        }

        public String getStyleType() {
            return StyleType;
        }

        public void setStyleType(String StyleType) {
            this.StyleType = StyleType;
        }

        public String getCoverUrl() {
            return CoverUrl;
        }

        public void setCoverUrl(String CoverUrl) {
            this.CoverUrl = CoverUrl;
        }

        public Object getImageUrl() {
            return ImageUrl;
        }

        public void setImageUrl(Object ImageUrl) {
            this.ImageUrl = ImageUrl;
        }

        public String getCreator() {
            return Creator;
        }

        public void setCreator(String Creator) {
            this.Creator = Creator;
        }

        public String getPublishDate() {
            return PublishDate;
        }

        public void setPublishDate(String PublishDate) {
            this.PublishDate = PublishDate;
        }

        public String getPublishDateString() {
            return PublishDateString;
        }

        public void setPublishDateString(String PublishDateString) {
            this.PublishDateString = PublishDateString;
        }

        public int getSectionId() {
            return SectionId;
        }

        public void setSectionId(int SectionId) {
            this.SectionId = SectionId;
        }

        public String getLocationSection() {
            return LocationSection;
        }

        public void setLocationSection(String LocationSection) {
            this.LocationSection = LocationSection;
        }

        public int getCommentCount() {
            return CommentCount;
        }

        public void setCommentCount(int CommentCount) {
            this.CommentCount = CommentCount;
        }

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public String getContentFontFize() {
            return ContentFontFize;
        }

        public void setContentFontFize(String ContentFontFize) {
            this.ContentFontFize = ContentFontFize;
        }

        public String getTitleFontSize() {
            return TitleFontSize;
        }

        public void setTitleFontSize(String TitleFontSize) {
            this.TitleFontSize = TitleFontSize;
        }
    }
}
