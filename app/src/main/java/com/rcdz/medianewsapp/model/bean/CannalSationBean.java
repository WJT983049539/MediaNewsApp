package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作用: 频道栏目List
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/19 10:15
 */
public class CannalSationBean implements Serializable {


    /**
     * code : 200
     * message : null
     * status : 0
     * msg : null
     * total : 9
     * rows : [{"Id":12,"Name":"高清频道","Description":"高清频道","OpenShard":1,"OpenComment":0,"OpenLikes":1,"CreateID":1,"Creator":"超级管理员","CreateDate":"2020-02-02 00:00:00"},{"Id":11,"Name":"本地频道","Description":"本地频道","OpenShard":1,"OpenComment":0,"OpenLikes":1,"CreateID":1,"Creator":"超级管理员","CreateDate":"2020-02-02 00:00:00"},{"Id":10,"Name":"精品回看","Description":"精品回看","OpenShard":1,"OpenComment":0,"OpenLikes":1,"CreateID":1,"Creator":"超级管理员","CreateDate":"2020-02-02 00:00:00"},{"Id":9,"Name":"专题报道","Description":"专题报道","OpenShard":1,"OpenComment":0,"OpenLikes":1,"CreateID":1,"Creator":"超级管理员","CreateDate":"2020-02-02 00:00:00"},{"Id":8,"Name":"闻喜新闻","Description":"闻喜新闻","OpenShard":1,"OpenComment":0,"OpenLikes":1,"CreateID":1,"Creator":"超级管理员","CreateDate":"2020-02-02 00:00:00"},{"Id":7,"Name":"纪录片","Description":"纪录片","OpenShard":1,"OpenComment":0,"OpenLikes":1,"CreateID":1,"Creator":"超级管理员","CreateDate":"2020-02-02 00:00:00"},{"Id":6,"Name":"独家V视","Description":"独家V视","OpenShard":1,"OpenComment":0,"OpenLikes":1,"CreateID":1,"Creator":"超级管理员","CreateDate":"2020-02-02 00:00:00"},{"Id":5,"Name":"精品点播","Description":"精品点播","OpenShard":1,"OpenComment":0,"OpenLikes":1,"CreateID":1,"Creator":"超级管理员","CreateDate":"2020-02-02 00:00:00"},{"Id":1,"Name":"TV直播","Description":"TV直播","OpenShard":1,"OpenComment":0,"OpenLikes":1,"CreateID":1,"Creator":"超级管理员","CreateDate":"2020-02-02 00:00:00"}]
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
    private List<CannelSation> rows;

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

    public List<CannelSation> getRows() {
        return rows;
    }

    public void setRows(List<CannelSation> rows) {
        this.rows = rows;
    }

    public static class CannelSation implements Serializable{
        /**
         * Id : 12
         * Name : 高清频道
         * Description : 高清频道
         * OpenShard : 1
         * OpenComment : 0
         * OpenLikes : 1
         * CreateID : 1
         * Creator : 超级管理员
         * CreateDate : 2020-02-02 00:00:00
         */

        private int Id;
        private String Name;
        private String Description;
        private int OpenShard;
        private int OpenComment;
        private int OpenLikes;
        private int CreateID;
        private String Creator;
        private String CreateDate;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public int getOpenShard() {
            return OpenShard;
        }

        public void setOpenShard(int OpenShard) {
            this.OpenShard = OpenShard;
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
