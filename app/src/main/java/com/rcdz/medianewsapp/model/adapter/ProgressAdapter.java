package com.rcdz.medianewsapp.model.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.YuYueProgresListInfoBean;
import com.rcdz.medianewsapp.tools.AppConfig;
import com.rcdz.medianewsapp.view.activity.VideoPlayerActivity;
import com.rcdz.medianewsapp.view.customview.CustomButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/26 13:00
 */
public class ProgressAdapter extends CommonRecyclerAdapter<YuYueProgresListInfoBean.YuYueProgresInfo>{
    public ProgressAdapter(Context context, List<YuYueProgresListInfoBean.YuYueProgresInfo> data, int layoutId) {
        super(context, data, layoutId);
    }
    public interface OnItemClick {
        void onitemclik(String type,YuYueProgresListInfoBean.YuYueProgresInfo item);
    }

    public interface LivingRefresh{
        void goRefresh(Long jgtime);
    }
    //刷新接口
    private LivingRefresh livingRefresh=null;
    private OnItemClick onItemClick = null;

    public void setLivingRefresh(LivingRefresh livingRefresh){ this.livingRefresh=livingRefresh;}
    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
    @Override
    public void convert(CommonViewHolder holder, Context context, YuYueProgresListInfoBean.YuYueProgresInfo item) {
        CustomButton ff=holder.getView(R.id.progress_button_title);

        Activity activity= (Activity) context;
        Resources resource = (Resources) activity.getBaseContext().getResources();
        ColorStateList csl = (ColorStateList) resource.getColorStateList(R.color.my_color);
        holder.setText(R.id.progress_date,item.getStartTime());
        holder.setText(R.id.progress_title,item.getName());
        //有三种状态 首先看节目结束时间是否超过了现在的时间 （超过现在的时候 回看）
        //没有超过现在的时间   就是直播中    （1.已经超过开始时间 直播中）（还没到开始时间  预约）    Button ff=holder.getView(R.id.progress_button_title);
        // ff.setPressed(true);
        //有预约
        item.getStartTime();
            if(item.getUse()){  //是否可预约
                if(item.getFlag()!=null){
                    //取消预约
                    ff.setFlag(false);
                    ff.setTextColor(mContext.getResources().getColorStateList(R.color.my_color));
                    ff.setText("取消预约");
                    ff.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(onItemClick!=null){
                                onItemClick.onitemclik("3",item);
                            }
                        }
                    });


                }else{
                    ff.setFlag(false);
                    ff.setTextColor(mContext.getResources().getColorStateList(R.color.my_color2));
                    ff.setText("预约");

                    ff.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(onItemClick!=null){
                                onItemClick.onitemclik("2",item);
                            }
                        }
                    });


                }

            }else{
                ff.setFlag(false);
                ff.setTextColor(mContext.getResources().getColorStateList(R.color.my_color2));
                ff.setText("回看");
                ff.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(onItemClick!=null){
                            onItemClick.onitemclik("1",item);
                        }
                    }
                });




            }
        if(item.getLive()){
            try {
            Glide.with(context).load(R.mipmap.living).into((ImageView) holder.getView(R.id.progress_statu));
            ff.setFlag(true);
            ff.setTextColor(mContext.getResources().getColorStateList(R.color.colorWhite));
            ff.setText("直播中");
            String end= item.getEndTime();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yy-MM-dd");
            SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            String time=simpleDateFormat.format(new Date());
            String times=time+" "+end;
                Date date2=simpleDateFormat2.parse(times);
                Long jgtime= date2.getTime()-new Date().getTime();
                if(livingRefresh!=null){
                    livingRefresh.goRefresh(jgtime);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


        }else{
            Glide.with(context).load(R.mipmap.noliving).into((ImageView) holder.getView(R.id.progress_statu));
        }

        if(ff.getFlag()){
            ff.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.yuyuebutton_h));

        }else{
            ff.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.yuyuebutton));
        }
    }
}
