package com.rcdz.medianewsapp.persenter.interfaces;

import com.rcdz.medianewsapp.model.bean.NewsListBean;
import com.rcdz.medianewsapp.model.bean.NoSetionsBean;
import com.rcdz.medianewsapp.model.bean.SetionBean;

/**
 * 作用:获得用户取消的版块
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/13 17:09
 */
public interface GetNoSationList {
    void getNoSationList(NoSetionsBean noSetionsBean);
}
