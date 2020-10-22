package com.rcdz.medianewsapp.model.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.LiveBean;
import com.rcdz.medianewsapp.tools.AppConfig;
import com.rcdz.medianewsapp.tools.GlideUtil;
import com.shehuan.niv.NiceImageView;

import java.util.List;

/**
 * 直播间适配器
 * @author:create by
 * 邮箱 983049539@qq.com
 */
public class LiveingAdapter extends RecyclerView.Adapter<LiveingAdapter.HolderView>{
    private List<LiveBean.LiveInfo> data;
    private Context context;

    public LiveingAdapter(List<LiveBean.LiveInfo> livelists, Context context) {
        this.data=livelists;
        this.context=context;
    }

    public interface OnItemClick {
        void onitemclik(int position);
    }
    private OnItemClick onItemClick=null;
    public void setOnItemClick(OnItemClick onItemClick){
        this.onItemClick=onItemClick;
    }


    @NonNull
    @Override
    public HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_live2,parent,false);
        return new HolderView(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull HolderView holder, int position) {
        holder.live_title.setText(data.get(position).getName());
        GlideUtil.load(context, AppConfig.BASE_LIVE_URL +data.get(position).getUrl()+"?j"+ Math.random(),holder.live_Preview,GlideUtil.getOption());
        int status=data.get(position).getLiveState();
        if(status==2){
            holder.live_status.setImageResource(R.mipmap.liveing);
        }else {
            holder.live_status.setImageResource(R.mipmap.nolive);
        }
        holder.living_name.setText(data.get(position).getName().toString());
        GlideUtil.load(context, AppConfig.BASE_LIVE_URL +data.get(position).getHeadImageUrl()+"?j"+ Math.random(),holder.living_head,GlideUtil.getOption());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClick!=null){
                    onItemClick.onitemclik(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class HolderView extends RecyclerView.ViewHolder {
        private NiceImageView live_Preview;
        private TextView live_title;
        private TextView living_name;
        private TextView living_people;
        private ImageView live_status;
        private ImageView living_head;
        public HolderView(@NonNull View itemView) {
            super(itemView);
            live_title=itemView.findViewById(R.id.live_title);
            live_Preview=itemView.findViewById(R.id.live_Preview);
            live_status=itemView.findViewById(R.id.live_status);
            living_people=itemView.findViewById(R.id.living_people);
            living_head=itemView.findViewById(R.id.living_head);
            living_name=itemView.findViewById(R.id.living_name);

        }
    }
}