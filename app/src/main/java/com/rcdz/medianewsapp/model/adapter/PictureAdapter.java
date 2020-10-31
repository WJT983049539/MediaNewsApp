package com.rcdz.medianewsapp.model.adapter;

import android.content.Context;

import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/31 15:32
 */
public class PictureAdapter extends CommonRecyclerAdapter<String>{
    public PictureAdapter(Context context, List<String> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(CommonViewHolder holder, Context context, String item) {

    }
}
