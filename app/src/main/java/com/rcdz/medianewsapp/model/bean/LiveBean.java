package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作用:直播间列表
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/20 16:35
 */
public class LiveBean implements Serializable {


    /**
     * code : 200
     * message : null
     * status : 0
     * msg : null
     * total : 3
     * rows : [{"Id":1012,"EncryptType":0,"Type":1,"LiveState":2,"RtmpUrl":"rtmp://192.168.1.220:1935/private/room_1012","Title":null,"Name":"私有直播间1","Url":"Upload/Files/LiveRoom/OneFrames/room_1012/cover.jpg","HLSUrl":"http://192.168.1.220:8080/private/room_1012.m3u8","UserTrueName":"超级管理员","HeadImageUrl":"","State":"3"},{"Id":1013,"EncryptType":1,"Type":0,"LiveState":2,"RtmpUrl":"rtmp://192.168.1.220:1935/live/room_1013\r\n","Title":null,"Name":"公共直播间1","Url":"Upload/Files/LiveRoom/OneFrames/room_1013/cover.jpg","HLSUrl":"http://192.168.1.220:8080/live/room_1013.m3u8","UserTrueName":null,"HeadImageUrl":null,"State":"3"},{"Id":1010,"EncryptType":1,"Type":0,"LiveState":0,"RtmpUrl":"rtmp://192.168.1.220:1935/live/room_1010","Title":"测试101112","Name":"公共直播间2","Url":"Upload/Files/LiveRoom/OneFrames/room_1010/cover.jpg","HLSUrl":"http://192.168.1.220:8080/live/room_1010.m3u8","UserTrueName":null,"HeadImageUrl":null,"State":"3"}]
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
    private List<LiveInfo> rows;

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

    public List<LiveInfo> getRows() {
        return rows;
    }

    public void setRows(List<LiveInfo> rows) {
        this.rows = rows;
    }

    public static class LiveInfo implements Serializable{
        /**
         * Id : 1012
         * EncryptType : 0
         * Type : 1 0共有 1私有
         * LiveState : 2
         * RtmpUrl : rtmp://192.168.1.220:1935/private/room_1012
         * Title : null
         * Name : 私有直播间1
         * Url : Upload/Files/LiveRoom/OneFrames/room_1012/cover.jpg
         * HLSUrl : http://192.168.1.220:8080/private/room_1012.m3u8
         * UserTrueName : 超级管理员
         * HeadImageUrl :
         * State : 3
         */

        private int Id;
        private int CreateID;
        private int EncryptType;
        private int Type;
        private int LiveState;
        private String RtmpUrl;
        private Object Title;
        private String Name;
        private String Url;
        private String HLSUrl;
        private String UserTrueName;
        private String HeadImageUrl;
        private String State;

        public int getCreateID() {
            return CreateID;
        }

        public void setCreateID(int createID) {
            CreateID = createID;
        }
        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public int getEncryptType() {
            return EncryptType;
        }

        public void setEncryptType(int EncryptType) {
            this.EncryptType = EncryptType;
        }

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public int getLiveState() {
            return LiveState;
        }

        public void setLiveState(int LiveState) {
            this.LiveState = LiveState;
        }

        public String getRtmpUrl() {
            return RtmpUrl;
        }

        public void setRtmpUrl(String RtmpUrl) {
            this.RtmpUrl = RtmpUrl;
        }

        public Object getTitle() {
            return Title;
        }

        public void setTitle(Object Title) {
            this.Title = Title;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }

        public String getHLSUrl() {
            return HLSUrl;
        }

        public void setHLSUrl(String HLSUrl) {
            this.HLSUrl = HLSUrl;
        }

        public String getUserTrueName() {
            return UserTrueName;
        }

        public void setUserTrueName(String UserTrueName) {
            this.UserTrueName = UserTrueName;
        }

        public String getHeadImageUrl() {
            return HeadImageUrl;
        }

        public void setHeadImageUrl(String HeadImageUrl) {
            this.HeadImageUrl = HeadImageUrl;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }
    }
}
