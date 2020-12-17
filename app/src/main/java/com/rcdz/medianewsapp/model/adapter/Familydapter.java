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
import com.rcdz.medianewsapp.model.bean.FaimilyListInfiBean;
import com.rcdz.medianewsapp.tools.AppConfig;
import com.rcdz.medianewsapp.tools.GlideUtil;

import java.util.List;

import static com.rcdz.medianewsapp.view.activity.MyFaililyActivity.isDelete;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/23 9:39
 */
public class Familydapter extends RecyclerView.Adapter<Familydapter.ViewHolder> {
    private  List<FaimilyListInfiBean.FaimilyInfo> list;
    private Context context;
    private RequestOptions options = new RequestOptions().placeholder(R.mipmap.peop).error(R.mipmap.peop).circleCrop();
    public interface OnItemClick {
        //flag 标志是否是符号, fuhao 标志减号或加号
        void onitemclik(int position,boolean flag,int fuhao);
    }
    private OnItemClick onItemClick = null;
    public interface OnClickListenerDelete {
     void onitemDeleteClick(String name,int type,int userId);
    }
    public OnClickListenerDelete onClickListenerDelete=null;
    public void setOnClickListenerDelete(OnClickListenerDelete onClickListenerDelete){
        this.onClickListenerDelete=onClickListenerDelete;
    }
    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
    public Familydapter(Context context, List<FaimilyListInfiBean.FaimilyInfo> list){
        this.context=context;
        this.list=list;
        FaimilyListInfiBean.FaimilyInfo faimilyInfo=new FaimilyListInfiBean.FaimilyInfo();
        faimilyInfo.setRealse(false);
        list.add(faimilyInfo);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.faimily_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(list.get(position).getRealse()){ //是真实数据
            if(isDelete){  //是否打开删除按钮
                holder.dele_peop.setVisibility(View.VISIBLE);
                holder.dele_peop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(onClickListenerDelete!=null){
                            onClickListenerDelete.onitemDeleteClick(list.get(position).getUserTrueName(),list.get(position).getIsFamilyCreator(),list.get(position).getUserId());
                        }
                    }
                });

            }else {
                holder.dele_peop.setVisibility(View.GONE);
            }
            String headimg=AppConfig.BASE_PICTURE_URL+list.get(position).getHeadImageUrl();
            Glide.with(context).load(headimg).apply(options).into(holder.family_item_head);
            holder.family_item_name.setVisibility(View.VISIBLE);
            holder.family_item_name.setText(list.get(position).getUserTrueName()+"");
            if(onItemClick!=null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(onItemClick!=null){
                            onItemClick.onitemclik(position,true,-1);
                        }
                    }
                });
            }
        }else{ //符号
            holder.dele_peop.setVisibility(View.GONE);
            if(list.get(position).getFuHao()==1){ //加号
                Glide.with(context).load(R.mipmap.jiahao).apply(options).into(holder.family_item_head);
                holder.family_item_name.setVisibility(View.GONE);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(onItemClick!=null){
                            onItemClick.onitemclik(position,false,1);
                        }
                    }
                });
            }else if(list.get(position).getFuHao()==2){ //减号
                    Glide.with(context).load(R.mipmap.jianhao2).apply(options).into(holder.family_item_head);
                holder.family_item_name.setVisibility(View.GONE);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(onItemClick!=null){
                            onItemClick.onitemclik(position,false,2);
                        }
                    }
                });
            }

        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView family_item_head;
        ImageView dele_peop;
        TextView family_item_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            family_item_head= itemView.findViewById(R.id.family_item_head);
            dele_peop= itemView.findViewById(R.id.dele_peop);
            family_item_name=itemView.findViewById(R.id.family_item_name);
        }
    }
}
