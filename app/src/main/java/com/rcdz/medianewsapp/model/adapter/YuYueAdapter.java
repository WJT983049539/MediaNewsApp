package com.rcdz.medianewsapp.model.adapter;

import android.content.Context;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.HistoryListInfoBean;
import com.rcdz.medianewsapp.model.bean.YuYueInfoBean;
import com.rcdz.medianewsapp.view.activity.MyYuYueActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/23 19:14
 */
public class YuYueAdapter extends CommonRecyclerAdapter<YuYueInfoBean.YuYueInfo.ProgramListBean>{

    public YuYueAdapter(Context context, List<YuYueInfoBean.YuYueInfo.ProgramListBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(CommonViewHolder holder, Context context, YuYueInfoBean.YuYueInfo.ProgramListBean item) {
        holder.setText(R.id.yuyu_title,item.getName());
        holder.setText(R.id.yuyu_date,item.getStartTime());
    }
}
