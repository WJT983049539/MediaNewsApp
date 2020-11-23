package com.rcdz.medianewsapp.model.bean;

import java.io.Serializable;

/**
 * 直播间评论信息
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 */
public class BarrageInfobean implements Serializable {
    String info;
    String headimage;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getHeadimage() {
        return headimage;
    }

    public void setHeadimage(String headimage) {
        this.headimage = headimage;
    }
}
