package com.rcdz.medianewsapp.model.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.TvCannelBean;
import com.rcdz.medianewsapp.tools.AppConfig;
import com.rcdz.medianewsapp.tools.GsonUtil;

import java.util.List;

/**
 * 作用:Tv直播节目
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/19 11:26
 */
public class LiveAdapter extends RecyclerView.Adapter<LiveAdapter.ViewHolder> {
    private List<TvCannelBean.TvCanneInfo> list;
    private Context context;
    public LiveAdapter(List<TvCannelBean.TvCanneInfo> list,Context context) {
        this.list=list;
        this.context=context;
    }
    public interface OnItemClick {
        void onitemclik(int position,Object object);
    }

    private OnItemClick onItemClick = null;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.tv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_titletext.setText(list.get(position).getName());
        holder.tv_descript.setText(list.get(position).getDescription());
        Object o=list.get(position).getImageUrl();
        if(o!=null){
            Glide.with(context).load(AppConfig.BASE_URL+o).into(holder.tv_img);
        }else{
            Glide.with(context).load(R.mipmap.default_image).into(holder.tv_img);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClick!=null){
                    onItemClick.onitemclik(position,list.get(position).getStreamUrl());
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView tv_img;
        TextView tv_titletext;
        TextView tv_descript;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_img= itemView.findViewById(R.id.tv_img);
            tv_titletext= itemView.findViewById(R.id.tv_titletext);
            tv_descript= itemView.findViewById(R.id.tv_descript);
        }
    }
}
