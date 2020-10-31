package com.rcdz.medianewsapp.model.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.HistoryListInfoBean;
import com.rcdz.medianewsapp.tools.AppConfig;
import com.rcdz.medianewsapp.view.activity.MyHistoryActivity;

import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/23 18:51
 */
public class HistoryAdapter extends CommonRecyclerAdapter<HistoryListInfoBean.HistoryInfo>{
    private RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_image).error(R.mipmap.default_image).centerCrop();
    public HistoryAdapter(Context context, List<HistoryListInfoBean.HistoryInfo> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(CommonViewHolder holder, Context context, HistoryListInfoBean.HistoryInfo obj) {
        Glide.with(context).load(AppConfig.BASE_PICTURE_URL+obj.getImageUrl()).apply(options).into((ImageView) holder.getView(R.id.history_img));
        holder.setText(R.id.history_title,obj.getLongTitle());
        holder.setText(R.id.history_date,obj.getCreator());
    }
}
