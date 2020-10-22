package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作用: 点播集数详情
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/19 17:24
 */
public class DemandEpisodeBean implements Serializable {

    /**
     * status : true
     * code : 200
     * message : 查询成功!
     * data : [{"id":5,"videoDemandId":1,"number":2,"imageUrl":"3","videoUrl":"Upload/Files/VideoDemand_Source/1/2.flv","source":"324","createDate":"2020-05-02 00:00:00"},{"id":1,"videoDemandId":1,"number":1,"imageUrl":"111","videoUrl":"Upload/Files/VideoDemand_Source/1/1.mp4","source":"11","createDate":"2020-02-02 00:00:00"}]
     */

    private boolean status;
    private int code;
    private String message;
    private List<DemandEpisodeInfo> data;

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

    public List<DemandEpisodeInfo> getData() {
        return data;
    }

    public void setData(List<DemandEpisodeInfo> data) {
        this.data = data;
    }

    public static class DemandEpisodeInfo implements Serializable {
        /**
         * id : 5
         * videoDemandId : 1
         * number : 2
         * imageUrl : 3
         * videoUrl : Upload/Files/VideoDemand_Source/1/2.flv
         * source : 324
         * createDate : 2020-05-02 00:00:00
         */

        private int id;
        private int videoDemandId;
        private int number;
        private String imageUrl;
        private String videoUrl;
        private String source;
        private String createDate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getVideoDemandId() {
            return videoDemandId;
        }

        public void setVideoDemandId(int videoDemandId) {
            this.videoDemandId = videoDemandId;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
