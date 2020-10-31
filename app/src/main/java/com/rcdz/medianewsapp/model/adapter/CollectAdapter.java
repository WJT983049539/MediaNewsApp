package com.rcdz.medianewsapp.model.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.CollectListInfoBean;
import com.rcdz.medianewsapp.tools.AppConfig;
import com.rcdz.medianewsapp.view.activity.MyCollectActivity;
import com.rcdz.medianewsapp.view.customview.SmoothCheckBox;

import java.util.List;

import static com.rcdz.medianewsapp.view.activity.MyCollectActivity.isSelected;

/**
 * 作用:收藏适配器 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/23 16:16
 */
public class CollectAdapter extends CommonRecyclerAdapter<CollectListInfoBean.CollectInfo>{
    private RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_image).error(R.mipmap.default_image).centerCrop();
    public CollectAdapter(Context context, List<CollectListInfoBean.CollectInfo> data, int layoutId) {
        super(context, data, layoutId);
    }
    //todo 点击进入详情页面 还未做
    @Override
    public void convert(CommonViewHolder holder, Context context, CollectListInfoBean.CollectInfo item) {
        Glide.with(context).load(AppConfig.BASE_PICTURE_URL+item.getImageUrl()).apply(options).into((ImageView) holder.getView(R.id.collect_img));
        holder.setText(R.id.collect_title,item.getLongTitle());
        holder.setText(R.id.collect_date,item.getCreator());
        if(isSelected){
            holder.setViewVisibility(R.id.selsct_statu, View.VISIBLE);
            holder.setViewVisibility(R.id.selsct_statu, View.VISIBLE);
            SmoothCheckBox smoothCheckBox=  holder.getView(R.id.airticle_checkbox);
            smoothCheckBox.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(SmoothCheckBox smoothCheckBox, boolean isChecked) {
                    MyCollectActivity.selectCollectMap.put(item.getId(),isChecked);
                }
            });
        }else{
            holder.setViewVisibility(R.id.selsct_statu, View.GONE);
        }
    }

}
