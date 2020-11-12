package com.rcdz.medianewsapp.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.PliveLeaveInfo;
import com.rcdz.medianewsapp.tools.AppConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/17 11:09
 */
public class AppealHotAdapter extends RecyclerView.Adapter<AppealHotAdapter.ViewHolder> {
    private ArrayList<PliveLeaveInfo.LeaveMessageInfo> dataList;
    private RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_image).error(R.mipmap.default_image).centerCrop();
    private Context context;

    public interface OnItemClick {
        void onitemclik(int position);
    }

    private OnItemClick onItemClick = null;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
    public AppealHotAdapter(ArrayList<PliveLeaveInfo.LeaveMessageInfo> dataList, Context context) {
        this.dataList=dataList;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_appealhot,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(AppConfig.BASE_URL +dataList.get(position).getImages()).apply(options).into(holder.img_hotimg);
        holder.tv_hotname.setText(dataList.get(position).getCreator());
        holder.tv_content.setText(dataList.get(position).getContents());
        holder.tv_subject.setText(dataList.get(position).getSubject());
        holder.tv_suggest.setText(dataList.get(position).getType());
        holder.tv_OrganizationName.setText(dataList.get(position).getOrganizationName());
        if(dataList.get(position).getCreateDate()!=null){
            String date=dataList.get(position).getCreateDate();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("YYYY-MM-dd");
            SimpleDateFormat simpleDateFormat3=new SimpleDateFormat("HH:mm:ss");
            try {
              Date data=  simpleDateFormat.parse(date);
                String data2=simpleDateFormat2.format(data);
                String data3=simpleDateFormat3.format(data);
                holder.tv_date.setText(data2);
                holder.tv_time.setText(data3);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        if(dataList.get(position).getIsReply()==0){ //未回复
            Glide.with(context).load(R.mipmap.dhf).apply(options).into(holder.img_statu);
        }else if(dataList.get(position).getIsReply()==1){
            Glide.with(context).load(R.mipmap.yhf).apply(options).into(holder.img_statu);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClick!=null){
                    onItemClick.onitemclik(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_hotuser;
        ImageView img_statu;
        TextView tv_hotname;
        ImageView img_hotimg;
        TextView tv_subject;
        TextView tv_content;
        TextView tv_suggest;
        TextView tv_date;
        TextView tv_time;
        TextView tv_OrganizationName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_hotuser=itemView.findViewById(R.id.img_hotuser);
            img_statu=itemView.findViewById(R.id.img_statu);
            tv_hotname=itemView.findViewById(R.id.tv_hotname);
            img_hotimg=itemView.findViewById(R.id.img_hotimg);
            tv_subject=itemView.findViewById(R.id.tv_subject);
            tv_content=itemView.findViewById(R.id.tv_content);
            tv_suggest=itemView.findViewById(R.id.tv_suggest);
            tv_date=itemView.findViewById(R.id.tv_cz);
            tv_time=itemView.findViewById(R.id.tv_time);
            tv_OrganizationName=itemView.findViewById(R.id.tv_OrganizationName);



        }
    }
}
