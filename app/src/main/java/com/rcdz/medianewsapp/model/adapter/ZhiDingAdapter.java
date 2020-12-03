package com.rcdz.medianewsapp.model.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.TopNewsInfo;
import com.rcdz.medianewsapp.view.activity.NewsDetailActivity;

import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/27 10:51
 */
public class ZhiDingAdapter extends CommonRecyclerAdapter<TopNewsInfo.TopNews>{


    public ZhiDingAdapter(Context context, List<TopNewsInfo.TopNews> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(CommonViewHolder holder, Context context, TopNewsInfo.TopNews item) {
        holder.setText(R.id.long_title,item.getLongTitle());
        holder.setText(R.id.zuzhi,item.getCreator());
        holder.setText(R.id.comment,item.getCommentCount()+"评论");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent =new Intent(mContext, NewsDetailActivity.class);
                    intent.putExtra("id",item.getTargetId());
                    intent.putExtra("plateId",item.getSectionId());
                     intent.putExtra("imageUrl","");
                    intent.putExtra("title",item.getTitle());
                    intent.putExtra("platName","推荐");
                    intent.putExtra("ActivityType",item.getActivityType());
                    intent.putExtra("Type",item.getType());
                    mContext.startActivity(intent);
            }
        });
    }
}
