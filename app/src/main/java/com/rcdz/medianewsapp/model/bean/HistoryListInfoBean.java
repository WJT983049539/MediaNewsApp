package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/23 18:32
 */
public class HistoryListInfoBean implements Serializable {

    /**
     * code : 200
     * message : null
     * status : 0
     * msg : null
     * total : 1
     * rows : [{"Id":2,"GlobalSectionId":1,"SectionName":"今日闻喜","Type":3,"TargetId":3,"Title":"问啊","LongTitle":"阿松大","Url":"啊实打实的","ImageUrl":"啊实打实","Duration":"20","SourceType":"精品点播","CreateID":16,"Creator":"8个月前","CreateDate":"2020-02-02 10:00:00"}]
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
    private List<HistoryInfo> rows;

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

    public List<HistoryInfo> getRows() {
        return rows;
    }

    public void setRows(List<HistoryInfo> rows) {
        this.rows = rows;
    }

    public static class HistoryInfo implements Serializable {
        /**
         * Id : 2
         * GlobalSectionId : 1
         * SectionName : 今日闻喜
         * Type : 3
         * TargetId : 3
         * Title : 问啊
         * LongTitle : 阿松大
         * Url : 啊实打实的
         * ImageUrl : 啊实打实
         * Duration : 20
         * SourceType : 精品点播
         * CreateID : 16
         * Creator : 8个月前
         * CreateDate : 2020-02-02 10:00:00
         */

        private int Id;
        private int GlobalSectionId;
        private String SectionName;
        private int Type;
        private int TargetId;
        private String Title;
        private String LongTitle;
        private String Url;
        private String ImageUrl;
        private String Duration;
        private String SourceType;
        private int CreateID;
        private String Creator;
        private String CreateDate;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getGlobalSectionId() {
            return GlobalSectionId;
        }

        public void setGlobalSectionId(int GlobalSectionId) {
            this.GlobalSectionId = GlobalSectionId;
        }

        public String getSectionName() {
            return SectionName;
        }

        public void setSectionName(String SectionName) {
            this.SectionName = SectionName;
        }

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public int getTargetId() {
            return TargetId;
        }

        public void setTargetId(int TargetId) {
            this.TargetId = TargetId;
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

        public String getUrl() {
            return Url;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }

        public String getImageUrl() {
            return ImageUrl;
        }

        public void setImageUrl(String ImageUrl) {
            this.ImageUrl = ImageUrl;
        }

        public String getDuration() {
            return Duration;
        }

        public void setDuration(String Duration) {
            this.Duration = Duration;
        }

        public String getSourceType() {
            return SourceType;
        }

        public void setSourceType(String SourceType) {
            this.SourceType = SourceType;
        }

        public int getCreateID() {
            return CreateID;
        }

        public void setCreateID(int CreateID) {
            this.CreateID = CreateID;
        }

        public String getCreator() {
            return Creator;
        }

        public void setCreator(String Creator) {
            this.Creator = Creator;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }
    }
}
