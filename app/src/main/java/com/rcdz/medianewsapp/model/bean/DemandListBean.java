package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/19 16:01
 */
public class DemandListBean implements Serializable {

    /**
     * code : 200
     * message : null
     * status : 0
     * msg : null
     * total : 1
     * rows : [{"Id":1,"Name":"cs","Mark":"cs","ChineseName":"cs","ParallelName":"cs","Keyword":"cs","State":"拟稿","AuditUserId":null,"Auditor":null,"AuditDate":null,"Description":null,"ImageUrl":"Upload/Files/VideoDemand/a988793f8b2649b5bc86a2275ff4aa54/small/微信图片_20200731160948.png","City":"cs","Language":"cs","Year":"2020-10-16","ShowTime":"2020-11-06 10:00:00","Source":"03","PublishMode":0,"OnlineTime":null,"OfflineTime":null,"CreateID":3,"Creator":"测试用户2","CreateDate":"2020-10-13 18:02:37","PublishDate":"2020-02-02 00:00:00","ChannelSectionId":1}]
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
    private List<DemandInfo> rows;

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

    public List<DemandInfo> getRows() {
        return rows;
    }

    public void setRows(List<DemandInfo> rows) {
        this.rows = rows;
    }

    public static class DemandInfo implements Serializable {
        /**
         * Id : 1
         * Name : cs
         * Mark : cs
         * ChineseName : cs
         * ParallelName : cs
         * Keyword : cs
         * State : 拟稿
         * AuditUserId : null
         * Auditor : null
         * AuditDate : null
         * Description : null
         * ImageUrl : Upload/Files/VideoDemand/a988793f8b2649b5bc86a2275ff4aa54/small/微信图片_20200731160948.png
         * City : cs
         * Language : cs
         * Year : 2020-10-16
         * ShowTime : 2020-11-06 10:00:00
         * Source : 03
         * PublishMode : 0
         * OnlineTime : null
         * OfflineTime : null
         * CreateID : 3
         * Creator : 测试用户2
         * CreateDate : 2020-10-13 18:02:37
         * PublishDate : 2020-02-02 00:00:00
         * ChannelSectionId : 1
         */

        private int Id;
        private String Name;
        private String Mark;
        private String ChineseName;
        private String ParallelName;
        private String Keyword;
        private String State;
        private Object AuditUserId;
        private Object Auditor;
        private Object AuditDate;
        private Object Description;
        private Object ImageUrl;
        private String City;
        private String Language;
        private String Year;
        private String ShowTime;
        private String Source;
        private int PublishMode;
        private Object OnlineTime;
        private Object OfflineTime;
        private int CreateID;
        private String Creator;
        private String CreateDate;
        private String PublishDate;
        private int ChannelSectionId;

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

        public String getMark() {
            return Mark;
        }

        public void setMark(String Mark) {
            this.Mark = Mark;
        }

        public String getChineseName() {
            return ChineseName;
        }

        public void setChineseName(String ChineseName) {
            this.ChineseName = ChineseName;
        }

        public String getParallelName() {
            return ParallelName;
        }

        public void setParallelName(String ParallelName) {
            this.ParallelName = ParallelName;
        }

        public String getKeyword() {
            return Keyword;
        }

        public void setKeyword(String Keyword) {
            this.Keyword = Keyword;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public Object getAuditUserId() {
            return AuditUserId;
        }

        public void setAuditUserId(Object AuditUserId) {
            this.AuditUserId = AuditUserId;
        }

        public Object getAuditor() {
            return Auditor;
        }

        public void setAuditor(Object Auditor) {
            this.Auditor = Auditor;
        }

        public Object getAuditDate() {
            return AuditDate;
        }

        public void setAuditDate(Object AuditDate) {
            this.AuditDate = AuditDate;
        }

        public Object getDescription() {
            return Description;
        }

        public void setDescription(Object Description) {
            this.Description = Description;
        }

        public Object getImageUrl() {
            return ImageUrl;
        }

        public void setImageUrl(String ImageUrl) {
            this.ImageUrl = ImageUrl;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public String getLanguage() {
            return Language;
        }

        public void setLanguage(String Language) {
            this.Language = Language;
        }

        public String getYear() {
            return Year;
        }

        public void setYear(String Year) {
            this.Year = Year;
        }

        public String getShowTime() {
            return ShowTime;
        }

        public void setShowTime(String ShowTime) {
            this.ShowTime = ShowTime;
        }

        public String getSource() {
            return Source;
        }

        public void setSource(String Source) {
            this.Source = Source;
        }

        public int getPublishMode() {
            return PublishMode;
        }

        public void setPublishMode(int PublishMode) {
            this.PublishMode = PublishMode;
        }

        public Object getOnlineTime() {
            return OnlineTime;
        }

        public void setOnlineTime(Object OnlineTime) {
            this.OnlineTime = OnlineTime;
        }

        public Object getOfflineTime() {
            return OfflineTime;
        }

        public void setOfflineTime(Object OfflineTime) {
            this.OfflineTime = OfflineTime;
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

        public String getPublishDate() {
            return PublishDate;
        }

        public void setPublishDate(String PublishDate) {
            this.PublishDate = PublishDate;
        }

        public int getChannelSectionId() {
            return ChannelSectionId;
        }

        public void setChannelSectionId(int ChannelSectionId) {
            this.ChannelSectionId = ChannelSectionId;
        }
    }
}
