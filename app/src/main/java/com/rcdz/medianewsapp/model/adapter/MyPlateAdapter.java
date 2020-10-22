package com.rcdz.medianewsapp.model.adapter;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/16 10:58
 */

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
import com.rcdz.medianewsapp.view.activity.SelectCannerListActivity;

/**
 * 选择版块用户版块适配器
 * @author:create by
 * 邮箱 983049539@qq.com
 */
public class MyPlateAdapter extends RecyclerView.Adapter<MyPlateAdapter.ViewHolder> {
    private SetList<SetionBean.DataBean> userList;
    public MyPlateAdapter(SetList<SetionBean.DataBean> userList) {
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
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.myplate,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.plate_button.setText(userList.get(position).getName());

        if(userList.get(position).getName().equals("推荐")){
            //推荐不允许删除
            holder.plate_delete.setVisibility(View.GONE);
        }else{
            holder.plate_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { //删除点击事件
                    if (onItemClick != null) {
                        onItemClick.onitemclik(position);
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView plate_button;
        ImageView plate_delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            plate_button=itemView.findViewById(R.id.plate_button);
            plate_delete=itemView.findViewById(R.id.plate_delete);
        }
    }
}
