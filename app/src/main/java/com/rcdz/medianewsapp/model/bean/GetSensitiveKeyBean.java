package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/12/21 16:14
 */
public class GetSensitiveKeyBean implements Serializable {

    /**
     * code : 200
     * message : null
     * status : 0
     * msg : null
     * total : 3
     * rows : [{"Id":9,"Keyword":"网吧","CreateID":3,"Creator":"测试用户2","CreateDate":"2020-12-03 17:50:11","ModifyID":null,"Modifier":null,"ModifyDate":null},{"Id":6,"Keyword":"哈哈哈对对对","CreateID":2,"Creator":"NUL2L","CreateDate":"2020-02-02 00:00:00","ModifyID":2,"Modifier":"测试用户1","ModifyDate":"2020-10-30 10:44:43"},{"Id":2,"Keyword":"星号","CreateID":2,"Creator":"测试用户1","CreateDate":"2020-10-30 09:14:47","ModifyID":null,"Modifier":null,"ModifyDate":null}]
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
    private List<Word> rows;

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

    public List<Word> getRows() {
        return rows;
    }

    public void setRows(List<Word> rows) {
        this.rows = rows;
    }

    public static class Word implements Serializable {
        /**
         * Id : 9
         * Keyword : 网吧
         * CreateID : 3
         * Creator : 测试用户2
         * CreateDate : 2020-12-03 17:50:11
         * ModifyID : null
         * Modifier : null
         * ModifyDate : null
         */

        private int Id;
        private String Keyword;
        private int CreateID;
        private String Creator;
        private String CreateDate;
        private Object ModifyID;
        private Object Modifier;
        private Object ModifyDate;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getKeyword() {
            return Keyword;
        }

        public void setKeyword(String Keyword) {
            this.Keyword = Keyword;
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

        public Object getModifyID() {
            return ModifyID;
        }

        public void setModifyID(Object ModifyID) {
            this.ModifyID = ModifyID;
        }

        public Object getModifier() {
            return Modifier;
        }

        public void setModifier(Object Modifier) {
            this.Modifier = Modifier;
        }

        public Object getModifyDate() {
            return ModifyDate;
        }

        public void setModifyDate(Object ModifyDate) {
            this.ModifyDate = ModifyDate;
        }
    }
}
