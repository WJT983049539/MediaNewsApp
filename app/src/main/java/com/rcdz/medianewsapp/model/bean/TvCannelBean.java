package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/19 11:14
 */
public class TvCannelBean implements Serializable {

    /**
     * code : 200
     * message : null
     * status : 0
     * msg : null
     * total : 7
     * rows : [{"Id":7,"Name":"CCTV-3","StreamUrl":"http://ivi.bupt.edu.cn/hls/cctv3hd.m3u8","CreateID":1,"Creator":"超级管理员","CreateDate":"2020-09-23 10:00:00","LogoNo":null,"TagId":null,"Description":"CCTV3在线直播,CCTV-3是一个集综艺性","Platform":null,"Type":null,"ChannelSectionId":null,"Permission":null,"IsTopic":null,"IsLookingBack":0,"IsMoveTime":null,"ImageUrl":null,"State":null,"AuditUserId":null,"AuditDate":null},{"Id":6,"Name":"CCTV-10","StreamUrl":"http://ivi.bupt.edu.cn/hls/cctv10hd.m3u8","CreateID":1,"Creator":"超级管理员","CreateDate":"2020-09-23 10:00:00","LogoNo":null,"TagId":null,"Description":"科学频道","Platform":null,"Type":null,"ChannelSectionId":null,"Permission":null,"IsTopic":null,"IsLookingBack":0,"IsMoveTime":null,"ImageUrl":null,"State":null,"AuditUserId":null,"AuditDate":null},{"Id":5,"Name":"CCTV-6","StreamUrl":"http://39.134.66.66/PLTV/88888888/224/3221225488/index.m3u8","CreateID":1,"Creator":"超级管理员","CreateDate":"2020-09-23 10:00:00","LogoNo":null,"TagId":null,"Description":"电影频道","Platform":null,"Type":null,"ChannelSectionId":null,"Permission":null,"IsTopic":null,"IsLookingBack":0,"IsMoveTime":null,"ImageUrl":null,"State":null,"AuditUserId":null,"AuditDate":null},{"Id":4,"Name":"CCTV-2","StreamUrl":"http://112.50.243.8/PLTV/88888888/224/3221225923/1.m3u8","CreateID":1,"Creator":"超级管理员","CreateDate":"2020-09-23 10:00:00","LogoNo":null,"TagId":null,"Description":"财经频道","Platform":null,"Type":null,"ChannelSectionId":null,"Permission":null,"IsTopic":null,"IsLookingBack":0,"IsMoveTime":null,"ImageUrl":null,"State":null,"AuditUserId":null,"AuditDate":null},{"Id":3,"Name":"CCTV-1","StreamUrl":"http://112.50.243.8/PLTV/88888888/224/3221225922/1.m3u8","CreateID":1,"Creator":"超级管理员","CreateDate":"2020-09-23 10:00:00","LogoNo":null,"TagId":null,"Description":"综合频道","Platform":null,"Type":null,"ChannelSectionId":null,"Permission":null,"IsTopic":null,"IsLookingBack":0,"IsMoveTime":null,"ImageUrl":null,"State":null,"AuditUserId":null,"AuditDate":null},{"Id":2,"Name":"CCTV-9","StreamUrl":"http://39.134.66.66/PLTV/88888888/224/3221225488/index.m3u8","CreateID":1,"Creator":"超级管理员","CreateDate":"2020-09-23 10:00:00","LogoNo":null,"TagId":null,"Description":"英语频道","Platform":null,"Type":null,"ChannelSectionId":null,"Permission":null,"IsTopic":null,"IsLookingBack":0,"IsMoveTime":null,"ImageUrl":null,"State":null,"AuditUserId":null,"AuditDate":null},{"Id":1,"Name":"CCTV-4","StreamUrl":"http://ivi.bupt.edu.cn/hls/cctv4hd.m3u8","CreateID":1,"Creator":"超级管理员","CreateDate":"2020-09-23 10:00:00","LogoNo":null,"TagId":null,"Description":"国际频道","Platform":null,"Type":null,"ChannelSectionId":null,"Permission":null,"IsTopic":null,"IsLookingBack":0,"IsMoveTime":null,"ImageUrl":null,"State":null,"AuditUserId":null,"AuditDate":null}]
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
    private List<TvCanneInfo> rows;

    @Override
    public String toString() {
        return "TvCannelBean{" +
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

    public List<TvCanneInfo> getRows() {
        return rows;
    }

    public void setRows(List<TvCanneInfo> rows) {
        this.rows = rows;
    }

    public static class TvCanneInfo implements Serializable{
        /**
         * Id : 7
         * Name : CCTV-3
         * StreamUrl : http://ivi.bupt.edu.cn/hls/cctv3hd.m3u8
         * CreateID : 1
         * Creator : 超级管理员
         * CreateDate : 2020-09-23 10:00:00
         * LogoNo : null
         * TagId : null
         * Description : CCTV3在线直播,CCTV-3是一个集综艺性
         * Platform : null
         * Type : null
         * ChannelSectionId : null
         * Permission : null
         * IsTopic : null
         * IsLookingBack : 0
         * IsMoveTime : null
         * ImageUrl : null
         * State : null
         * AuditUserId : null
         * AuditDate : null
         */

        private int Id;
        private String Name;
        private String StreamUrl;
        private int CreateID;
        private String Creator;
        private String CreateDate;
        private Object LogoNo;
        private Object TagId;
        private String Description;
        private Object Platform;
        private Object Type;
        private Object ChannelSectionId;
        private Object Permission;
        private Object IsTopic;
        private int IsLookingBack;
        private Object IsMoveTime;
        private Object ImageUrl;
        private Object State;
        private Object AuditUserId;
        private Object AuditDate;

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

        public String getStreamUrl() {
            return StreamUrl;
        }

        public void setStreamUrl(String StreamUrl) {
            this.StreamUrl = StreamUrl;
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

        public Object getLogoNo() {
            return LogoNo;
        }

        public void setLogoNo(Object LogoNo) {
            this.LogoNo = LogoNo;
        }

        public Object getTagId() {
            return TagId;
        }

        public void setTagId(Object TagId) {
            this.TagId = TagId;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public Object getPlatform() {
            return Platform;
        }

        public void setPlatform(Object Platform) {
            this.Platform = Platform;
        }

        public Object getType() {
            return Type;
        }

        public void setType(Object Type) {
            this.Type = Type;
        }

        public Object getChannelSectionId() {
            return ChannelSectionId;
        }

        public void setChannelSectionId(Object ChannelSectionId) {
            this.ChannelSectionId = ChannelSectionId;
        }

        public Object getPermission() {
            return Permission;
        }

        public void setPermission(Object Permission) {
            this.Permission = Permission;
        }

        public Object getIsTopic() {
            return IsTopic;
        }

        public void setIsTopic(Object IsTopic) {
            this.IsTopic = IsTopic;
        }

        public int getIsLookingBack() {
            return IsLookingBack;
        }

        public void setIsLookingBack(int IsLookingBack) {
            this.IsLookingBack = IsLookingBack;
        }

        public Object getIsMoveTime() {
            return IsMoveTime;
        }

        public void setIsMoveTime(Object IsMoveTime) {
            this.IsMoveTime = IsMoveTime;
        }

        public Object getImageUrl() {
            return ImageUrl;
        }

        public void setImageUrl(Object ImageUrl) {
            this.ImageUrl = ImageUrl;
        }

        public Object getState() {
            return State;
        }

        public void setState(Object State) {
            this.State = State;
        }

        public Object getAuditUserId() {
            return AuditUserId;
        }

        public void setAuditUserId(Object AuditUserId) {
            this.AuditUserId = AuditUserId;
        }

        public Object getAuditDate() {
            return AuditDate;
        }

        public void setAuditDate(Object AuditDate) {
            this.AuditDate = AuditDate;
        }

        @Override
        public String toString() {
            return "TvCanneInfo{" +
                    "Id=" + Id +
                    ", Name='" + Name + '\'' +
                    ", StreamUrl='" + StreamUrl + '\'' +
                    ", CreateID=" + CreateID +
                    ", Creator='" + Creator + '\'' +
                    ", CreateDate='" + CreateDate + '\'' +
                    ", LogoNo=" + LogoNo +
                    ", TagId=" + TagId +
                    ", Description='" + Description + '\'' +
                    ", Platform=" + Platform +
                    ", Type=" + Type +
                    ", ChannelSectionId=" + ChannelSectionId +
                    ", Permission=" + Permission +
                    ", IsTopic=" + IsTopic +
                    ", IsLookingBack=" + IsLookingBack +
                    ", IsMoveTime=" + IsMoveTime +
                    ", ImageUrl=" + ImageUrl +
                    ", State=" + State +
                    ", AuditUserId=" + AuditUserId +
                    ", AuditDate=" + AuditDate +
                    '}';
        }
    }
}
