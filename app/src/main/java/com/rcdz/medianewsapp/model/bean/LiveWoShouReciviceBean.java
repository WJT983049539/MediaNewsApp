package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;

/**
 * 作用: 握手成功收到的消息
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/11/1 14:15
 */
public class LiveWoShouReciviceBean implements Serializable {

    /**
     * Message : [{"Img":"http://www.xx.com/users/1.jpg"}]
     * UserCount : 1
     * MessageType : 1
     * RoomId : 1015
     * Img : http://www.xx.com/users/1.jpg
     */

    private String Message;
    private int UserCount;
    private int MessageType;
    private int RoomId;
    private String Img;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public int getUserCount() {
        return UserCount;
    }

    public void setUserCount(int UserCount) {
        this.UserCount = UserCount;
    }

    public int getMessageType() {
        return MessageType;
    }

    public void setMessageType(int MessageType) {
        this.MessageType = MessageType;
    }

    public int getRoomId() {
        return RoomId;
    }

    public void setRoomId(int RoomId) {
        this.RoomId = RoomId;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String Img) {
        this.Img = Img;
    }
}
