package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/21 14:15
 */
public class litlemessagebean implements Serializable {

    /**
     * UserName : 张三
     * SendDate : 2020/11/1 14:06:35
     * Message : 213
     */

    private String UserName;
    private String SendDate;
    private String Message;
    private String UserId="";
    private int  Type=0; //0直播中 2关闭直播间
    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }



    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getSendDate() {
        return SendDate;
    }

    public void setSendDate(String SendDate) {
        this.SendDate = SendDate;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }
}

