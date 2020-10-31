package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/27 10:02
 */
public class BannerInfoBean implements Serializable {

    /**
     * code : 200
     * message : null
     * status : 0
     * msg : null
     * total : 3
     * rows : [{"Id":1,"Orders":333,"Link":"aaaaaawer","ImageUrl":"Upload/Files/Watermark/logo.png","CreateID":1,"Creator":"admin","CreateDate":"2020-10-15 00:00:00"},{"Id":3,"Orders":3,"Link":"ccccccccccccc","ImageUrl":"Upload/Files/Watermark/logo.png","CreateID":1,"Creator":"admin","CreateDate":"2020-10-15 00:00:00"},{"Id":4,"Orders":1,"Link":"sadfasdf","ImageUrl":"Upload/Files/RepeatImages/77b89136285c48d4b3100729666570fc/small/2345截图20201009120030.png","CreateID":1,"Creator":"超级管理员","CreateDate":"2020-10-22 16:22:04"}]
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
    private List<BannerInfo> rows;

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

    public List<BannerInfo> getRows() {
        return rows;
    }

    public void setRows(List<BannerInfo> rows) {
        this.rows = rows;
    }

    public static class BannerInfo implements Serializable {
        /**
         * Id : 1
         * Orders : 333
         * Link : aaaaaawer
         * ImageUrl : Upload/Files/Watermark/logo.png
         * CreateID : 1
         * Creator : admin
         * CreateDate : 2020-10-15 00:00:00
         */

        private int Id;
        private int Orders;
        private String Link;
        private String ImageUrl;
        private int CreateID;
        private String Creator;
        private String CreateDate;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getOrders() {
            return Orders;
        }

        public void setOrders(int Orders) {
            this.Orders = Orders;
        }

        public String getLink() {
            return Link;
        }

        public void setLink(String Link) {
            this.Link = Link;
        }

        public String getImageUrl() {
            return ImageUrl;
        }

        public void setImageUrl(String ImageUrl) {
            this.ImageUrl = ImageUrl;
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
