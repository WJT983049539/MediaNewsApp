package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作用:版块信息
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/14 10:10
 */
public class SetionBean implements Serializable {

    /**
     * status : true
     * code : 200
     * message : 查询成功!
     * data : [{"id":1,"name":"今日闻喜",HasChilds:true},{"id":2,"name":"推荐"},{"id":3,"name":"智慧党建"},{"id":4,"name":"桐乡文化"}]
     */

    private boolean status;
    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 今日闻喜
         *
         */

        private int id;
        private String name;
        private String HasChilds; //1有子栏目 null 为没子栏目
        private String Logo;
        public String getHasChilds() {
            return HasChilds;
        }

        public void setHasChilds(String hasChilds) {
            HasChilds = hasChilds;
        }





        public String getLogo() {
            return Logo;
        }

        public void setLogo(String logo) {
            Logo = logo;
        }




        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
