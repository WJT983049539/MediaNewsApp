package com.rcdz.medianewsapp.model.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.view.activity.SuggestActivity;

import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/31 15:32
 */
public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder>{

    private  SuggestActivity suggestActivity;
    private List<String> picture;
    public PictureAdapter(SuggestActivity suggestActivity, List<String> picture) {
        this.suggestActivity=suggestActivity;
        this.picture=picture;
    }
    public interface OnItemClick {
        void onitemclik(int position);
    }
    private OnItemClick onItemClick=null;
    public void setOnItemClick(OnItemClick onItemClick){
        this.onItemClick=onItemClick;
    }


    public interface PictureOnItemClick {
        void pponitemclik(int position);
    }
    private PictureOnItemClick pponItemClick=null;
    public void setppOnItemClick(PictureOnItemClick onItemClick){
        this.pponItemClick=onItemClick;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_suggest,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.delete.setVisibility(View.VISIBLE);
       if(position<3&&position==picture.size()-1){ //说明只有一张图片
           Glide.with(suggestActivity).load(R.mipmap.jia).into(holder.suggest_item_img);
           holder.delete.setVisibility(View.GONE);
           holder.suggest_item_img.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if(onItemClick!=null){
                       onItemClick.onitemclik(position);
                   }
               }
           });
       } else{
           Glide.with(suggestActivity).load(picture.get(position)).into(holder.suggest_item_img);
           holder.delete.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if(pponItemClick!=null){
                       pponItemClick.pponitemclik(position);
                   }
               }
           });
       }
    }


    @Override
    public int getItemCount() {
        return picture.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView suggest_item_img;
        ImageView delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            suggest_item_img=itemView.findViewById(R.id.suggest_item_img);
            delete=itemView.findViewById(R.id.delete);
        }
    }
}
