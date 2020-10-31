package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作用:积分
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/22 15:33
 */
public class JiFenLogBean implements Serializable {

    /**
     * code : 200
     * message : null
     * status : 0
     * msg : null
     * total : 1
     * rows : [{"Id":19,"Type":"1","Score":0,"AddScore":1,"CurrentScore":1,"Source":"观看视频,增加积分1","UserId":16,"CreateDate":"2020-10-22 15:38:46"}]
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
    private List<JiFenLog> rows;

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

    public List<JiFenLog> getRows() {
        return rows;
    }

    public void setRows(List<JiFenLog> rows) {
        this.rows = rows;
    }

    public static class JiFenLog implements Serializable{
        /**
         * Id : 19
         * Type : 1
         * Score : 0
         * AddScore : 1
         * CurrentScore : 1
         * Source : 观看视频,增加积分1
         * UserId : 16
         * CreateDate : 2020-10-22 15:38:46
         */

        private int Id;
        private String Type;
        private int Score;
        private int AddScore;
        private int CurrentScore;
        private String Source;
        private int UserId;
        private String CreateDate;

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public int getScore() {
            return Score;
        }

        public void setScore(int Score) {
            this.Score = Score;
        }

        public int getAddScore() {
            return AddScore;
        }

        public void setAddScore(int AddScore) {
            this.AddScore = AddScore;
        }

        public int getCurrentScore() {
            return CurrentScore;
        }

        public void setCurrentScore(int CurrentScore) {
            this.CurrentScore = CurrentScore;
        }

        public String getSource() {
            return Source;
        }

        public void setSource(String Source) {
            this.Source = Source;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public String getCreateDate() {
            return CreateDate;
        }

        public void setCreateDate(String CreateDate) {
            this.CreateDate = CreateDate;
        }
    }
}
