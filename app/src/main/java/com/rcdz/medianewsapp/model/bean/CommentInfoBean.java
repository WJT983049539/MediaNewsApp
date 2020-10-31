package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/23 18:23
 */
public class CommentInfoBean implements Serializable {


    /**
     * code : 200
     * message : null
     * status : 0
     * msg : null
     * total : 1
     * rows : [{"Id":8,"HeadImageUrl":"Upload/Files/a.jpg","Creator":"啊啊啊","Title":"啊啊啊","LongTitle":"啊啊啊","Contents":"啊啊啊","TargetId":1017,"GlobalSectionId":1,"Type":1,"CreateID":16,"CreateDate":"2020-02-02 00:00:00"}]
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
    private List<CommentInfo> rows;

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

    public List<CommentInfo> getRows() {
        return rows;
    }

    public void setRows(List<CommentInfo> rows) {
        this.rows = rows;
    }

    public static class CommentInfo implements Serializable {
        /**
         * Id : 8
         * HeadImageUrl : Upload/Files/a.jpg
         * Creator : 啊啊啊
         * Title : 啊啊啊
         * LongTitle : 啊啊啊
         * Contents : 啊啊啊
         * TargetId : 1017
         * GlobalSectionId : 1
         * Type : 1
         * CreateID : 16
         * CreateDate : 2020-02-02 00:00:00
         */

        private int Id;
        private String HeadImageUrl;
        private String Creator;
        private String Title;
        private String LongTitle;
        private String Contents;
        private int TargetId;
        private int GlobalSectionId;
        private int Type;
        private int CreateID;
        private String CreateDate;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getHeadImageUrl() {
            return HeadImageUrl;
        }

        public void setHeadImageUrl(String HeadImageUrl) {
            this.HeadImageUrl = HeadImageUrl;
        }

        public String getCreator() {
            return Creator;
        }

        public void setCreator(String Creator) {
            this.Creator = Creator;
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

        public String getContents() {
            return Contents;
        }

        public void setContents(String Contents) {
            this.Contents = Contents;
        }

        public int getTargetId() {
            return TargetId;
        }

        public void setTargetId(int TargetId) {
            this.TargetId = TargetId;
        }

        public int getGlobalSectionId() {
            return GlobalSectionId;
        }

        public void setGlobalSectionId(int GlobalSectionId) {
            this.GlobalSectionId = GlobalSectionId;
        }

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public int getCreateID() {
            return CreateID;
        }

        public void setCreateID(int CreateID) {
            this.CreateID = CreateID;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }
    }
}
