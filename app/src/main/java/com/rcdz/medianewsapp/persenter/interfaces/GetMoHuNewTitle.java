package com.rcdz.medianewsapp.persenter.interfaces;

import com.rcdz.medianewsapp.model.bean.MuhuNewBean;

import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/15 14:55
 */
public interface GetMoHuNewTitle {
    void getMohuNewTitle(List<MuhuNewBean.RowsBean> list);
}
