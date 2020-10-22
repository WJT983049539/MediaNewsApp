package com.rcdz.medianewsapp.model.bean;


import com.rcdz.medianewsapp.tools.SetList;

/**
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 */
public class MessageEvent3 {
    private SetList<SetionBean.DataBean> UserList;

    public SetList<SetionBean.DataBean> getUserList() {
        return UserList;
    }

    public void setUserList(SetList<SetionBean.DataBean> userList) {
        UserList = userList;
    }
}
