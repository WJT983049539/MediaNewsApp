package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/11/1 14:08
 */
public class LiveWoshouBean implements Serializable {

    /**
     * Auth : {"Password":"qwer1234.0"}
     * Data : {"Message":null,"RoomId":1015,"UserId":100,"Img":"http://www.xx.com/users/1.jpg","MessageType":1}
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

    public static class AuthBean implements Serializable{
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

    public static class DataBean implements Serializable{
        /**
         * Message : null
         * RoomId : 1015
         * UserId : 100
         * Img : http://www.xx.com/users/1.jpg
         * MessageType : 1
         */

        private Object Message;
        private int RoomId;
        private int UserId;
        private String Img;
        private int MessageType;

        public Object getMessage() {
            return Message;
        }

        public void setMessage(Object Message) {
            this.Message = Message;
        }

        public int getRoomId() {
            return RoomId;
        }

        public void setRoomId(int RoomId) {
            this.RoomId = RoomId;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public String getImg() {
            return Img;
        }

        public void setImg(String Img) {
            this.Img = Img;
        }

        public int getMessageType() {
            return MessageType;
        }

        public void setMessageType(int MessageType) {
            this.MessageType = MessageType;
        }
    }
}
