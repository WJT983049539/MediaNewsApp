package com.rcdz.medianewsapp.model.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.HomeClumnInfo;
import com.rcdz.medianewsapp.tools.AppConfig;

import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/12/18 11:17
 */
public class HomeClumnAdapter extends CommonRecyclerAdapter<HomeClumnInfo.DataBean.AppSectionsBean>{

    public interface ClumnOnClicklisten {
        void onclick(int  pid,String name,String logo);
    }
    public ClumnOnClicklisten clumnOnClicklisten;
    public void setClumnClick(ClumnOnClicklisten clumnOnClicklisten){
        this.clumnOnClicklisten=clumnOnClicklisten;
    }
    RequestOptions requestOptions= new RequestOptions().placeholder(R.mipmap.default_image).error(R.mipmap.default_image).centerInside();
    public HomeClumnAdapter(Context context,List<HomeClumnInfo.DataBean.AppSectionsBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(CommonViewHolder holder, Context context, HomeClumnInfo.DataBean.AppSectionsBean item) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clumnOnClicklisten!=null){
                    clumnOnClicklisten.onclick(item.getSectionId(),item.getSectionName(),item.getLogo());
                }
            }
        });
        holder.setText(R.id.tv_clumn_n,item.getSectionName());
        Glide.with(context).load(AppConfig.BASE_PICTURE_URL+item.getLogo()).apply(requestOptions).into((ImageView) holder.getView(R.id.image_clumn));
    }
}
