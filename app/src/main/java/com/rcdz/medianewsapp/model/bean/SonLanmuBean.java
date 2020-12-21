package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/12/19 14:01
 */
public class SonLanmuBean implements Serializable {

    /**
     * code : 200
     * message : null
     * status : 0
     * msg : null
     * total : 1
     * rows : [{"Name":"其他","Id":20,"OpenShard":0,"OrderNum":1608259285816,"OpenComment":0,"Enable":0,"PId":19,"OpenLikes":0,"CreateID":3,"Creator":"测试用户2","CreateDate":"2020-12-18 10:41:25","Logo":"Upload/Files/Global_Sections/3b9e89f62dfe48cbb10fe98c5c17fbe6/small/微信图片_20201217134007.jpg"}]
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
    private List<SonLanmuInfo> rows;

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

    public List<SonLanmuInfo> getRows() {
        return rows;
    }

    public void setRows(List<SonLanmuInfo> rows) {
        this.rows = rows;
    }

    public static class SonLanmuInfo implements Serializable {
        /**
         * Name : 其他
         * Id : 20
         * OpenShard : 0
         * OrderNum : 1608259285816
         * OpenComment : 0
         * Enable : 0
         * PId : 19
         * OpenLikes : 0
         * CreateID : 3
         * Creator : 测试用户2
         * CreateDate : 2020-12-18 10:41:25
         * Logo : Upload/Files/Global_Sections/3b9e89f62dfe48cbb10fe98c5c17fbe6/small/微信图片_20201217134007.jpg
         *  "HasChilds": null 1
         */

        private String Name;
        private int Id;
        private int OpenShard;
        private long OrderNum;
        private int OpenComment;
        private int Enable;
        private int PId;
        private int OpenLikes;
        private int CreateID;
        private String Creator;
        private String CreateDate;
        private String Logo;
        private String HasChilds;
        public String getHasChilds() {
            return HasChilds;
        }

        public void setHasChilds(String hasChilds) {
            HasChilds = hasChilds;
        }



        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getOpenShard() {
            return OpenShard;
        }

        public void setOpenShard(int OpenShard) {
            this.OpenShard = OpenShard;
        }

        public long getOrderNum() {
            return OrderNum;
        }

        public void setOrderNum(long OrderNum) {
            this.OrderNum = OrderNum;
        }

        public int getOpenComment() {
            return OpenComment;
        }

        public void setOpenComment(int OpenComment) {
            this.OpenComment = OpenComment;
        }

        public int getEnable() {
            return Enable;
        }

        public void setEnable(int Enable) {
            this.Enable = Enable;
        }

        public int getPId() {
            return PId;
        }

        public void setPId(int PId) {
            this.PId = PId;
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

        public String getLogo() {
            return Logo;
        }

        public void setLogo(String Logo) {
            this.Logo = Logo;
        }

        @Override
        public String toString() {
            return "SonLanmuInfo{" +
                    "Name='" + Name + '\'' +
                    ", Id=" + Id +
                    ", OpenShard=" + OpenShard +
                    ", OrderNum=" + OrderNum +
                    ", OpenComment=" + OpenComment +
                    ", Enable=" + Enable +
                    ", PId=" + PId +
                    ", OpenLikes=" + OpenLikes +
                    ", CreateID=" + CreateID +
                    ", Creator='" + Creator + '\'' +
                    ", CreateDate='" + CreateDate + '\'' +
                    ", Logo='" + Logo + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SonLanmuBean{" +
                "code=" + code +
                ", message=" + message +
                ", status=" + status +
                ", msg=" + msg +
                ", total=" + total +
                ", summary=" + summary +
                ", extra=" + extra +
                ", rows=" + rows +
                '}';
    }
}
