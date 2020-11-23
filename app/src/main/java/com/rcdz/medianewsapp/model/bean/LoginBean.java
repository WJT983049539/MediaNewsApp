package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;

/**
 * 作用:登录成功信息
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/13 9:42
 */
public class LoginBean  implements Serializable {

    /**
     * status : true
     * code : 310
     * message : 登陆成功
     * data : {"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiIxNiIsImlhdCI6IjE2MDI1NTMyODYiLCJuYmYiOiIxNjAyNTUzMjg2IiwiZXhwIjoiMTYwMzE1ODA4NiIsImlzcyI6IkZ1c2lvbk1lZGlhLmNvcmUub3duZXIiLCJhdWQiOiJGdXNpb25NZWRpYS5jb3JlIn0.ncjJ4I2dC4gu2nYoEXiraGdcpvNHOoLDPD1golcYc_o",
     * "userName":null,
     * "img":null}
     */

    private boolean status;
    private int code;
    private String message;

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

    private DataBean data;
    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiIxNiIsImlhdCI6IjE2MDI1NTMyODYiLCJuYmYiOiIxNjAyNTUzMjg2IiwiZXhwIjoiMTYwMzE1ODA4NiIsImlzcyI6IkZ1c2lvbk1lZGlhLmNvcmUub3duZXIiLCJhdWQiOiJGdXNpb25NZWRpYS5jb3JlIn0.ncjJ4I2dC4gu2nYoEXiraGdcpvNHOoLDPD1golcYc_o
         * userName : null
         * img : null
         */

        private String token;
        private Object userName;
        private Object img;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        private String user;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Object getUserName() {
            return userName;
        }

        public void setUserName(Object userName) {
            this.userName = userName;
        }

        public Object getImg() {
            return img;
        }

        public void setImg(Object img) {
            this.img = img;
        }
    }
}
