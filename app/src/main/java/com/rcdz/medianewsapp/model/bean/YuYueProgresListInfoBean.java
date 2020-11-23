package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作用: 某天的节目单带预约状态
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/26 11:44
 */
public class YuYueProgresListInfoBean implements Serializable {

    /**
     * status : true
     * code : 200
     * message : 节目列表查询成功
     * data : [{"id":1,"channelId":1,"programTime":"2020-10-23 00:00:00","startTime":"16:05:00","endTime":"16:20:00","name":"蓝猫淘气三千问","programUrl":"Upload/Files/VideoDemand_Source/1/2.flv ","createID":0,"creator":null,"createDate":"0001-01-01 00:00:00","flag":null}]
     */

    private boolean status;
    private int code;
    private String message;
    private List<YuYueProgresInfo> data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<YuYueProgresInfo> getData() {
        return data;
    }

    public void setData(List<YuYueProgresInfo> data) {
        this.data = data;
    }

    public static class YuYueProgresInfo implements Serializable {
        /**
         * id : 1
         * channelId : 1
         * programTime : 2020-10-23 00:00:00
         * startTime : 16:05:00
         * endTime : 16:20:00
         * name : 蓝猫淘气三千问
         * programUrl : Upload/Files/VideoDemand_Source/1/2.flv
         * createID : 0
         * creator : null
         * createDate : 0001-01-01 00:00:00
         * flag : null
         "scheduleId":null,
         "isLive":false,
         "isUse":false
         */

        private int id;
        private int channelId;
        private String programTime;
        private String startTime;
        private String endTime;
        private String name;
        private String programUrl;
        private int createID;
        private Object creator;
        private String createDate;
        private Object flag;
        private Boolean isLive;
        private Boolean isUse;
        private String scheduleId;
        public String getScheduleId() {
            return scheduleId;
        }

        public void setScheduleId(String scheduleId) {
            this.scheduleId = scheduleId;
        }



        public Boolean getLive() {
            return isLive;
        }

        public void setLive(Boolean live) {
            isLive = live;
        }

        public Boolean getUse() {
            return isUse;
        }

        public void setUse(Boolean use) {
            isUse = use;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getChannelId() {
            return channelId;
        }

        public void setChannelId(int channelId) {
            this.channelId = channelId;
        }

        public String getProgramTime() {
            return programTime;
        }

        public void setProgramTime(String programTime) {
            this.programTime = programTime;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProgramUrl() {
            return programUrl;
        }

        public void setProgramUrl(String programUrl) {
            this.programUrl = programUrl;
        }

        public int getCreateID() {
            return createID;
        }

        public void setCreateID(int createID) {
            this.createID = createID;
        }

        public Object getCreator() {
            return creator;
        }

        public void setCreator(Object creator) {
            this.creator = creator;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public Object getFlag() {
            return flag;
        }

        public void setFlag(Object flag) {
            this.flag = flag;
        }
    }
}
