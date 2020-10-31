package com.rcdz.medianewsapp.model.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.CommentInfoBean;
import com.rcdz.medianewsapp.tools.AppConfig;
import com.rcdz.medianewsapp.view.activity.CommentActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/23 20:26
 */
public class CommentAdapter extends CommonRecyclerAdapter<CommentInfoBean.CommentInfo>{
private RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_image).error(R.mipmap.default_image).centerCrop();
public CommentAdapter(Context context, List<CommentInfoBean.CommentInfo> data, int layoutId) {
        super(context, data, layoutId);
        }

@Override
public void convert(CommonViewHolder holder, Context context, CommentInfoBean.CommentInfo obj) {
        Glide.with(context).load(AppConfig.BASE_PICTURE_URL+obj.getHeadImageUrl()).apply(options).into((ImageView) holder.getView(R.id.com_img));
        holder.setText(R.id.com_username,obj.getCreator());
        holder.setText(R.id.com_date,obj.getCreateDate());
        holder.setText(R.id.comment_content,obj.getContents());
        holder.setText(R.id.comment_title,obj.getLongTitle());
        }
        }