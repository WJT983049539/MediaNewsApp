package com.rcdz.medianewsapp.model.bean;

/**
 * 作用:接收到的直播消息 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/11/1 13:30
 */
public class ReceiveMessage {

    /**
     * Message : { "UserName":"张三", "SendDate":"2020/11/1 12:02:58", "Message":"你好，你是谁？234234543123441234" }
     * UserCount : 5
     * MessageType : 0
     * RoomId : 1015
     * Img : http://www.a.com/users/123123.png
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
