package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/21 9:28
 */
public class LiveCoverInfo implements Serializable {

    /**
     * status : true
     * code : 200
     * message : 成功!
     * data : [{"id":1010,"url":"Upload/Files/LiveRoom/OneFrames/room_1010/cover.jpg"}]
     */

    private boolean status;
    private int code;
    private String message;
    private List<CoverInfo> data;

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

    public List<CoverInfo> getData() {
        return data;
    }

    public void setData(List<CoverInfo> data) {
        this.data = data;
    }

    public static class CoverInfo implements Serializable{
        /**
         * id : 1010
         * url : Upload/Files/LiveRoom/OneFrames/room_1010/cover.jpg
         */

        private int id;
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
