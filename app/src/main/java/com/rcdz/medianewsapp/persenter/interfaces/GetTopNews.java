package com.rcdz.medianewsapp.persenter.interfaces;

import com.rcdz.medianewsapp.model.bean.NewsListBean;
import com.rcdz.medianewsapp.model.bean.TopNewsInfo;

/**
 * 作用:得到置顶新闻
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/26 18:36
 */
public interface GetTopNews {
    void getTopNews(TopNewsInfo topNewsInfo);
}
