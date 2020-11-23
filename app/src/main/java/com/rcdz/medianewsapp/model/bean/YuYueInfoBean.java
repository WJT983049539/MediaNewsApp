package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/23 18:26
 */
public class YuYueInfoBean implements Serializable {

    /**
     * status : true
     * code : 200
     * message : 查询成功!
     * data : {"title":"10月23日 星期五","programList":[{"startTime":"21:00:00","endTime":"22:20:00","name":"午间新闻"},{"startTime":"16:05:00","endTime":"16:20:00","name":"蓝猫淘气三千问"}]}
     */

    private boolean status;
    private int code;
    private String message;
    private YuYueInfo data;

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

    public YuYueInfo getData() {
        return data;
    }

    public void setData(YuYueInfo data) {
        this.data = data;
    }

    public static class YuYueInfo implements Serializable {
        /**
         * title : 10月23日 星期五
         * programList : [{"startTime":"21:00:00","endTime":"22:20:00","name":"午间新闻"},{"startTime":"16:05:00","endTime":"16:20:00","name":"蓝猫淘气三千问"}]
         */

        private String title;
        private List<ProgramListBean> programList;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ProgramListBean> getProgramList() {
            return programList;
        }

        public void setProgramList(List<ProgramListBean> programList) {
            this.programList = programList;
        }

        public static class ProgramListBean {
            /**
             * startTime : 21:00:00
             * endTime : 22:20:00
             * name : 午间新闻
             */

            private String startTime;
            private String endTime;
            private String name;

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
        }
    }
}
