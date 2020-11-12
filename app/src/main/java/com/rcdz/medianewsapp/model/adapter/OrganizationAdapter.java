package com.rcdz.medianewsapp.model.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.DepartmnetInfoBean;
import com.rcdz.medianewsapp.tools.AppConfig;
import com.rcdz.medianewsapp.tools.GlideUtil;

import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/17 17:06
 */
public class OrganizationAdapter extends RecyclerView.Adapter<OrganizationAdapter.ViewHolder> {
    private List<DepartmnetInfoBean.DepartmnetInfo> dataList;
    private Activity activity;
    public OrganizationAdapter(List<DepartmnetInfoBean.DepartmnetInfo> dataList, Activity activity) {
        this.dataList=dataList;
        this.activity=activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.organization,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(dataList.get(position).getName()!=null){
            holder.tv_bumen.setText(dataList.get(position).getName());
        }
        holder.tv_allmun.setText("留言主体数量："+dataList.get(position).getFeedbackNum());
        holder.tv_backnum.setText("已回复数量："+dataList.get(position).getFeedbackReplynum());

        if(dataList.get(position).getContactsPhone()!=null){
            holder.tv_phone.setText(dataList.get(position).getContactsPhone().toString());
        }

        Glide.with(activity).load(AppConfig.BASE_PICTURE_URL+dataList.get(position).getLogo()).apply(GlideUtil.getOption()).into(holder.img_img);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_phone;
        private TextView tv_bumen;
        private TextView tv_allmun;
        private TextView tv_backnum;
        private ImageView img_img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_phone=itemView.findViewById(R.id.tv_phone);
            tv_bumen=itemView.findViewById(R.id.tv_bumen);
            tv_allmun=itemView.findViewById(R.id.tv_allmun);
            tv_backnum=itemView.findViewById(R.id.tv_backnum);
            img_img=itemView.findViewById(R.id.img_img);

        }
    }
}
