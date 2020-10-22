package com.rcdz.medianewsapp.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.BarrageInfobean;
import com.rcdz.medianewsapp.tools.GlideUtil;

import java.util.List;

/**
 * @author:create by
 * 邮箱 983049539@qq.com
 */
public class LiveBarrageAdapter extends RecyclerView.Adapter<LiveBarrageAdapter.MyHolder> {
    private List<BarrageInfobean> list;
    private Context activity;
    public LiveBarrageAdapter(List<BarrageInfobean> list, Context activity) {
    this.list=list;
    this.activity=activity;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barrage,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.item_tv_barrage.setText(list.get(position).getInfo());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView item_tv_barrage;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            item_tv_barrage= itemView.findViewById(R.id.item_tv_barrage);
        }
    }
}
