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
import com.rcdz.medianewsapp.model.bean.DemandListBean;
import com.rcdz.medianewsapp.tools.AppConfig;

import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/19 16:09
 */
public class DemandAdapter extends RecyclerView.Adapter<DemandAdapter.ViewHolder> {
    private List<DemandListBean.DemandInfo> dataList;
    private   Context context;
    private RequestOptions options = new RequestOptions().placeholder(R.mipmap.default_image).error(R.mipmap.default_image).centerCrop();
    public interface OnItemClick {
        void onitemclik(int position);
    }

    private OnItemClick onItemClick = null;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
    public DemandAdapter(List<DemandListBean.DemandInfo> dataList, Context context) {
        this.dataList=dataList;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_demand,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(dataList.get(position).getImageUrl()!=null){
            Glide.with(context).load(AppConfig.BASE_PICTURE_URL+dataList.get(position).getImageUrl()).apply(options).into(holder.demand_img);
        }
        holder.demand_title.setText(dataList.get(position).getName());
        holder.demand_decript.setText(dataList.get(position).getMark());
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
        private ImageView demand_img;
        private TextView demand_title;
        private TextView demand_decript;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            demand_img=itemView.findViewById(R.id.demand_img);
            demand_title=itemView.findViewById(R.id.demand_title);
            demand_decript=itemView.findViewById(R.id.demand_decript);


        }
    }
}
