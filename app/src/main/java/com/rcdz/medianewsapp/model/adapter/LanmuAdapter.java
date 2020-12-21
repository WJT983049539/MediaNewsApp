package com.rcdz.medianewsapp.model.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.SonLanmuBean;
import com.rcdz.medianewsapp.tools.AppConfig;
import com.rcdz.medianewsapp.tools.GlideUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/12/19 8:51
 */
//todo 字栏目适配器
public class LanmuAdapter extends CommonRecyclerAdapter<SonLanmuBean.SonLanmuInfo>{
    public interface OnItemClicklisten {
        void onitemClickListen(int pid,String HasChilds,String name,String logo);
    }
    private OnItemClicklisten onItemClicklisten;
    public void setOnItemClicklisten(OnItemClicklisten onItemClicklisten){
        this.onItemClicklisten=onItemClicklisten;

    }

    public LanmuAdapter(Context context, List<SonLanmuBean.SonLanmuInfo> data, int layoutId) {
        super(context, data, layoutId);

    }

    @Override
    public void convert(CommonViewHolder holder, Context context, SonLanmuBean.SonLanmuInfo item) {
        ImageView imageView=holder.getView(R.id.lanmuimage);
        String url= AppConfig.BASE_PICTURE_URL+item.getLogo();
        GlideUtil.load(context,url,imageView,GlideUtil.getOption());
        holder.setText(R.id.title,item.getName());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("MM-dd");
        try {
            Date date=simpleDateFormat.parse(item.getCreateDate());
            String time=simpleDateFormat2.format(date);
            holder.setText(R.id.time,time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClicklisten!=null){
                    onItemClicklisten.onitemClickListen(item.getId(), item.getHasChilds(),item.getName(),item.getLogo());
                }
            }
        });

    }

}
