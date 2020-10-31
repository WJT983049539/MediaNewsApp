package com.rcdz.medianewsapp.model.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.JiFenLogBean;

import java.util.List;

/**
 * @author:create by
 * 邮箱 983049539@qq.com
 */
public class JifenAdapter extends RecyclerView.Adapter<JifenAdapter.ViewHolder> {
    private List<JiFenLogBean.JiFenLog> logList;
    public JifenAdapter(List<JiFenLogBean.JiFenLog> logList) {
        this.logList=logList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_jiden_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.jjifentype.setText(logList.get(i).getSource());
        viewHolder.jifen_integral.setText("+"+logList.get(i).getAddScore());
        viewHolder.jifen_date.setText(logList.get(i).getCreateDate());
    }

    @Override
    public int getItemCount() {
        return logList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView jjifentype;
        TextView jifen_integral;
        TextView jifen_date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             jjifentype=itemView.findViewById(R.id.tv_cz);
             jifen_integral=itemView.findViewById(R.id.jifen_integral);
             jifen_date=itemView.findViewById(R.id.tv_date);
        }
    }
}
