package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/26 10:42
 */
public class CannelProgeressDateListBean implements Serializable {

    /**
     * status : true
     * code : 200
     * message : 查询成功!
     * data : [{"title":"昨天","date":"2020-10-25"},{"title":"10月24日","date":"2020-10-24"},{"title":"10月23日","date":"2020-10-23"},{"title":"9月27日","date":"2020-09-27"}]
     */

    private boolean status;
    private int code;
    private String message;
    private List<CannelDataInfo> data;

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

    public List<CannelDataInfo> getData() {
        return data;
    }

    public void setData(List<CannelDataInfo> data) {
        this.data = data;
    }

    public static class CannelDataInfo implements Serializable {
        /**
         * title : 昨天
         * date : 2020-10-25
         */

        private String title;
        private String date;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
