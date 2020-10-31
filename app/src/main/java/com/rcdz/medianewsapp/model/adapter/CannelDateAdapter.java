package com.rcdz.medianewsapp.model.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.CannelProgeressDateListBean;

import java.util.List;

import static com.rcdz.medianewsapp.view.activity.VideoPlayerActivity.SelectedDatelist;

/**
 * 作用: 频道日期
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/26 11:13
 */
public class CannelDateAdapter extends RecyclerView.Adapter<CannelDateAdapter.ViewHolder> {
    private List<CannelProgeressDateListBean.CannelDataInfo> datelist;
    public CannelDateAdapter(List<CannelProgeressDateListBean.CannelDataInfo> datelist) {
        this.datelist=datelist;
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
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.canneldate_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.canneldate.setTextColor(Color.parseColor("#020202"));

        if(SelectedDatelist.size()>0){
            String date=SelectedDatelist.get(0);
            if(datelist.get(position).getDate().equals(date)){
                holder.canneldate.setTextColor(Color.parseColor("#D4001D"));
            }
        }


        holder.canneldate.setText(datelist.get(position).getTitle());
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
        return datelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView canneldate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            canneldate=itemView.findViewById(R.id.canneldate);
        }
    }
}
