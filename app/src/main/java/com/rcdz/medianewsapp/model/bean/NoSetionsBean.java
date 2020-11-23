package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作用: 用户取消的栏目
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/16 16:20
 */
public class NoSetionsBean implements Serializable {

    /**
     * code : 200
     * message : null
     * status : 0
     * msg : null
     * total : 1
     * rows : [{"Id":204,"SectionId":8,"SectionName":"大事记","CreateID":16,"Creator":"测试7","CreateDate":"2020-10-16 16:19:12"}]
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
    private List<RowsBean> rows;

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

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Serializable{
        /**
         * Id : 204
         * SectionId : 8
         * SectionName : 大事记
         * CreateID : 16
         * Creator : 测试7
         * CreateDate : 2020-10-16 16:19:12
         */

        private int Id;
        private int SectionId;
        private String SectionName;
        private int CreateID;
        private String Creator;
        private String CreateDate;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getSectionId() {
            return SectionId;
        }

        public void setSectionId(int SectionId) {
            this.SectionId = SectionId;
        }

        public String getSectionName() {
            return SectionName;
        }

        public void setSectionName(String SectionName) {
            this.SectionName = SectionName;
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
