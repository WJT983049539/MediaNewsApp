package com.rcdz.medianewsapp.persenter.interfaces;

import com.rcdz.medianewsapp.model.bean.SetionBean;

import java.util.List;

/**
 * 作用: 显示版块信息
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/14 9:25
 */
public interface GetUserSetion {
    void getUserSetion(List<SetionBean.DataBean> setionBeans);
}
