package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;

/**
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 */
public class LiveMessage implements Serializable {

    /**
     * Auth : {"Password":"qwer1234.0"}
     * Data : {"Message":"你好","RoomId":1,"UserId":100}
     */

    private AuthBean Auth;
    private DataBean Data;

    public AuthBean getAuth() {
        return Auth;
    }

    public void setAuth(AuthBean Auth) {
        this.Auth = Auth;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class AuthBean  implements Serializable {
        /**
         * Password : qwer1234.0
         */

        private String Password;

        public String getPassword() {
            return Password;
        }

        public void setPassword(String Password) {
            this.Password = Password;
        }
    }

    public static class DataBean implements Serializable {
        /**
         * Message : 你好
         * RoomId : 1
         * UserId : 100
         */

        private String Message;
        private int RoomId;
        private int UserId;
        private int MessageType;

        public String getImg() {
            return Img;
        }

        public void setImg(String img) {
            Img = img;
        }

        private String Img;

        public String getMessage() {
            return Message;
        }

        public void setMessage(String message) {
            Message = message;
        }

        public int getRoomId() {
            return RoomId;
        }

        public void setRoomId(int roomId) {
            RoomId = roomId;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int userId) {
            UserId = userId;
        }

        public int getMessageType() {
            return MessageType;
        }

        public void setMessageType(int messageType) {
            MessageType = messageType;
        }
    }

}
