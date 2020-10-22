package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;

/**
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 */
public class LiveReciveMessage implements Serializable {


    /**
     * Message :
     * UserCount : 2
     * MessageType : 0
     * RoomId : 1010
     */

    private String Message;
    private int UserCount;
    private int MessageType;
    private int RoomId;

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
}
