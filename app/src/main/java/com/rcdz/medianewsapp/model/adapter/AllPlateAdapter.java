package com.rcdz.medianewsapp.model.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.SetionBean;
import com.rcdz.medianewsapp.tools.SetList;

/**
 * @author:create by
 * 邮箱 983049539@qq.com
 */
public class AllPlateAdapter extends RecyclerView.Adapter<AllPlateAdapter.ViewHolder> {
    private SetList<SetionBean.DataBean> userList;
    public AllPlateAdapter(SetList<SetionBean.DataBean> userList) {
        this.userList=userList;
    }
    public interface OnItemClick {
        void onitemclik(int position);
    }
    private OnItemClick onItemClick = null;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AllPlateAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.myplate2,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.plate_button.setText("+"+userList.get(position).getName());
            holder.plate_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { //删除点击事件
                    if (onItemClick != null) {
                        onItemClick.onitemclik(position);
                    }
                }
            });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView plate_button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            plate_button=itemView.findViewById(R.id.noplate_button);
        }
    }
}

