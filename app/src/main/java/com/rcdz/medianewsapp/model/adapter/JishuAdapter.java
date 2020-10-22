package com.rcdz.medianewsapp.model.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.DemandEpisodeBean;

import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/20 15:00
 */
public class JishuAdapter extends RecyclerView.Adapter<JishuAdapter.ViewHolder> {
    private List<DemandEpisodeBean.DemandEpisodeInfo> list;
    public JishuAdapter(List<DemandEpisodeBean.DemandEpisodeInfo> list) {
        this.list=list;
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
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.num,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_num.setText(String.valueOf(position+1));
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
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_num= itemView.findViewById(R.id.tv_num);
        }
    }
}
