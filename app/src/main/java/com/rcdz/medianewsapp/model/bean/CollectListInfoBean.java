package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/23 16:12
 */
public class CollectListInfoBean implements Serializable {

    /**
     * code : 200
     * message : null
     * status : 0
     * msg : null
     * total : 2
     * rows : [{"Id":4,"Type":1,"TargetId":1,"SourceType":"你好哈哈","CreateID":16,"Creator":"eer","CreateDate":"2020-10-23 14:11:21","Title":"eeeeee","LongTitle":"你好哈哈","Url":"你好哈哈","ImageUrl":"你好哈哈","Duration":"你好哈哈"},{"Id":3,"Type":1,"TargetId":1,"SourceType":"你好哈哈","CreateID":16,"Creator":"eer","CreateDate":"2020-10-23 14:11:21","Title":"你好哈哈","LongTitle":"你好哈哈","Url":"你好哈哈","ImageUrl":"你好哈哈","Duration":"你好哈哈"}]
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
    private List<CollectInfo> rows;

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

    public List<CollectInfo> getRows() {
        return rows;
    }

    public void setRows(List<CollectInfo> rows) {
        this.rows = rows;
    }

    public static class CollectInfo implements Serializable {
        /**
         * Id : 4
         * Type : 1
         * TargetId : 1
         * SourceType : 你好哈哈
         * CreateID : 16
         * Creator : eer
         * CreateDate : 2020-10-23 14:11:21
         * Title : eeeeee
         * LongTitle : 你好哈哈
         * Url : 你好哈哈
         * ImageUrl : 你好哈哈
         * Duration : 你好哈哈
         */

        private int Id;
        private int Type;
        private int TargetId;
        private String SourceType;
        private int CreateID;
        private String Creator;
        private String CreateDate;
        private String Title;
        private String LongTitle;
        private String Url;
        private String ImageUrl;
        private String Duration;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
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
    }
}
