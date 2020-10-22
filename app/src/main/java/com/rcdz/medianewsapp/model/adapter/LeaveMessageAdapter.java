package com.rcdz.medianewsapp.model.adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.PliveLeaveInfo;

import org.w3c.dom.Text;

import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/19 9:04
 */
public class LeaveMessageAdapter extends RecyclerView.Adapter<LeaveMessageAdapter.ViewHolder> {
    private List<PliveLeaveInfo.LeaveMessageInfo> dataList;
    public LeaveMessageAdapter(List<PliveLeaveInfo.LeaveMessageInfo> dataList) {
        this.dataList=dataList;
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
    public LeaveMessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.leavemessage_adapter,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaveMessageAdapter.ViewHolder holder, int position) {
        holder.tv_leave_content.setText("测试");
        holder.tv_leave_date.setText("测试");
        holder.tv_leave_title.setText("测试");
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
        ImageView leave_image;
        TextView tv_leave_title;
        TextView tv_leave_content;
        TextView tv_leave_date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            leave_image=itemView.findViewById(R.id.leave_image);
            tv_leave_title= itemView.findViewById(R.id.tv_leave_title);
            tv_leave_content= itemView.findViewById(R.id.tv_leave_content);
            tv_leave_date=itemView.findViewById(R.id.tv_leave_date);
        }
    }
}
