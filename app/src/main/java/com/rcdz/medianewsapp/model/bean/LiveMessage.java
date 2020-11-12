package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;

/**
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 */
public class LiveMessage implements Serializable {


    /**
     * Auth : {"Password":"qwer1234.0"}
     * Data : {"Message":"{\"UserName\":\"张三\",\"SendDate\":\"2020/11/1 14:06:35\",\"Message\":\"213\"}","RoomId":1015,"UserId":100,"Img":"http://www.a.com/users/123123.png","MessageType":0}
     */

    private AuthBean Auth;
    private SendMessageBean Data;

    public AuthBean getAuth() {
        return Auth;
    }

    public void setAuth(AuthBean Auth) {
        this.Auth = Auth;
    }

    public SendMessageBean getData() {
        return Data;
    }

    public void setData(SendMessageBean Data) {
        this.Data = Data;
    }

    public static class AuthBean {
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

    public static class SendMessageBean implements Serializable {
        /**
         * Message : {"UserName":"张三","SendDate":"2020/11/1 14:06:35","Message":"213"}
         * RoomId : 1015
         * UserId : 100
         * Img : http://www.a.com/users/123123.png
         * MessageType : 0
         */

        private String Message;
        private int RoomId;
        private int UserId;
        private String Img;
        private int MessageType;

        public String getMessage() {
            return Message;
        }

        public void setMessage(String Message) {
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
