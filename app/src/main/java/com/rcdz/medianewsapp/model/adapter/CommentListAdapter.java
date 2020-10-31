package com.rcdz.medianewsapp.model.adapter;

import android.content.Context;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.CommentInfoBean;
import com.rcdz.medianewsapp.tools.AppConfig;
import com.rcdz.medianewsapp.tools.GlideUtil;
import com.shehuan.niv.NiceImageView;

import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/27 13:15
 */
public class CommentListAdapter extends CommonRecyclerAdapter<CommentInfoBean.CommentInfo>{
    public CommentListAdapter(Context context, List<CommentInfoBean.CommentInfo> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(CommonViewHolder holder, Context context, CommentInfoBean.CommentInfo item) {
        GlideUtil.load(context, AppConfig.BASE_PICTURE_URL+item.getHeadImageUrl(),(NiceImageView)holder.getView(R.id.comment_img),GlideUtil.getOption());
        holder.setText(R.id.comment_name,item.getCreator());
        holder.setText(R.id.comment_content,item.getContents());
        holder.setText(R.id.comment_time,item.getCreateDate());

    }
}
