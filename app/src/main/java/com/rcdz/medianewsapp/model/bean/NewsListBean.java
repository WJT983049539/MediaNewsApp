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
     * total : 3
     * rows : [{"Id":"948bb66f-59e2-44ea-89eb-89416664ab4e","Title":"文章1","LongTitle":"文章1","StyleType":"06","CoverUrl":"Upload/Files/My_ImageRepository/041776dfcb2547a7b7e8b380f700517f/轮播图2_看图王.jpg","ImageUrl":"","Creator":"测试姓名","PublishDate":"2020-12-16 16:39:15","SectionId":2,"LocationSection":"1","CommentCount":0,"Type":1,"ContentFontFize":"14","TitleFontSize":"16","TargetId":2226,"PublishDateString":"18小时前","ActivityType":-1,"SectionName":"推荐","OpenComment":0,"OpenLikes":0,"OpenShard":1,"Source":"","CountPage":0,"CountLikes":0,"OrderNum":1608107568467,"Arrtibutes":"观看次数,来源"},{"Id":"0db943c5-16e2-4a07-892a-e46ac3702283","Title":"图集1","LongTitle":"图集1","StyleType":"06","CoverUrl":"Upload/Files/My_ImageRepository/041776dfcb2547a7b7e8b380f700517f/轮播图2_看图王.jpg","ImageUrl":null,"Creator":"测试姓名","PublishDate":"2020-12-16 16:38:33","SectionId":2,"LocationSection":"1","CommentCount":0,"Type":3,"ContentFontFize":null,"TitleFontSize":null,"TargetId":101,"PublishDateString":"18小时前","ActivityType":-1,"SectionName":"推荐","OpenComment":0,"OpenLikes":0,"OpenShard":1,"Source":"","CountPage":0,"CountLikes":0,"OrderNum":null,"Arrtibutes":"观看次数,来源"},{"Id":"b9d227c6-1e21-463b-917f-398bebaba844","Title":"文章3","LongTitle":"文章3","StyleType":"06","CoverUrl":"Upload/Files/My_ImageRepository/041776dfcb2547a7b7e8b380f700517f/轮播图2_看图王.jpg","ImageUrl":"","Creator":"测试姓名","PublishDate":"2020-12-16 16:39:30","SectionId":2,"LocationSection":"2","CommentCount":0,"Type":1,"ContentFontFize":"14","TitleFontSize":"16","TargetId":2228,"PublishDateString":"18小时前","ActivityType":-1,"SectionName":"推荐","OpenComment":0,"OpenLikes":0,"OpenShard":1,"Source":"","CountPage":0,"CountLikes":0,"OrderNum":1608107636261,"Arrtibutes":"观看次数,来源"}]
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

    public static class NewsInfo implements Serializable{
        /**
         * Id : 948bb66f-59e2-44ea-89eb-89416664ab4e
         * Title : 文章1
         * LongTitle : 文章1
         * StyleType : 06
         * CoverUrl : Upload/Files/My_ImageRepository/041776dfcb2547a7b7e8b380f700517f/轮播图2_看图王.jpg
         * ImageUrl :
         * Creator : 测试姓名
         * PublishDate : 2020-12-16 16:39:15
         * SectionId : 2
         * LocationSection : 1
         * CommentCount : 0
         * Type : 1
         * ContentFontFize : 14
         * TitleFontSize : 16
         * TargetId : 2226
         * PublishDateString : 18小时前
         * ActivityType : -1
         * SectionName : 推荐
         * OpenComment : 0
         * OpenLikes : 0
         * OpenShard : 1
         * Source :
         * CountPage : 0
         * CountLikes : 0
         * OrderNum : 1608107568467
         * Arrtibutes : 观看次数,来源
         */

        private String Id;
        private String Title;
        private String LongTitle;
        private String StyleType;
        private String CoverUrl;
        private String ImageUrl;
        private String Creator;
        private String PublishDate;
        private int SectionId;
        private String LocationSection;
        private int CommentCount;
        private int Type;
        private String ContentFontFize;
        private String TitleFontSize;
        private int TargetId;
        private String PublishDateString;
        private int ActivityType;
        private String SectionName;
        private int OpenComment;
        private int OpenLikes;
        private int OpenShard;
        private String Source;
        private int CountPage;
        private int CountLikes;
        private long OrderNum;
        private String Arrtibutes;

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

        public String getImageUrl() {
            return ImageUrl;
        }

        public void setImageUrl(String ImageUrl) {
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

        public int getTargetId() {
            return TargetId;
        }

        public void setTargetId(int TargetId) {
            this.TargetId = TargetId;
        }

        public String getPublishDateString() {
            return PublishDateString;
        }

        public void setPublishDateString(String PublishDateString) {
            this.PublishDateString = PublishDateString;
        }

        public int getActivityType() {
            return ActivityType;
        }

        public void setActivityType(int ActivityType) {
            this.ActivityType = ActivityType;
        }

        public String getSectionName() {
            return SectionName;
        }

        public void setSectionName(String SectionName) {
            this.SectionName = SectionName;
        }

        public int getOpenComment() {
            return OpenComment;
        }

        public void setOpenComment(int OpenComment) {
            this.OpenComment = OpenComment;
        }

        public int getOpenLikes() {
            return OpenLikes;
        }

        public void setOpenLikes(int OpenLikes) {
            this.OpenLikes = OpenLikes;
        }

        public int getOpenShard() {
            return OpenShard;
        }

        public void setOpenShard(int OpenShard) {
            this.OpenShard = OpenShard;
        }

        public String getSource() {
            return Source;
        }

        public void setSource(String Source) {
            this.Source = Source;
        }

        public int getCountPage() {
            return CountPage;
        }

        public void setCountPage(int CountPage) {
            this.CountPage = CountPage;
        }

        public int getCountLikes() {
            return CountLikes;
        }

        public void setCountLikes(int CountLikes) {
            this.CountLikes = CountLikes;
        }

        public long getOrderNum() {
            return OrderNum;
        }

        public void setOrderNum(long OrderNum) {
            this.OrderNum = OrderNum;
        }

        public String getArrtibutes() {
            return Arrtibutes;
        }

        public void setArrtibutes(String Arrtibutes) {
            this.Arrtibutes = Arrtibutes;
        }
    }
}
