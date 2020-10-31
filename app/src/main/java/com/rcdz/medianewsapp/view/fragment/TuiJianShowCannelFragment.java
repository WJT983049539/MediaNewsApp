package com.rcdz.medianewsapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.TvCannelBean;
import com.rcdz.medianewsapp.tools.AppConfig;
import com.rcdz.medianewsapp.tools.GlideUtil;

import java.util.List;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/26 19:56
 */
public class TuiJianShowCannelFragment extends Fragment {
    private List<TvCannelBean.TvCanneInfo> canneInfos;
    private ViewFlipper viewflipper;
    public TuiJianShowCannelFragment(List<TvCannelBean.TvCanneInfo> canneInfos){
        this.canneInfos=canneInfos;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view= inflater.inflate(R.layout.mainfragment3,container,false);
       inintView(view);
        return view;
    }

    private void inintView(View view1) {
        viewflipper=view1.findViewById(R.id.viewflipper);
        viewflipper.clearFocus();

        for (int i=0;i<canneInfos.size();i++){

          View  view = LayoutInflater.from(getActivity()).inflate(R.layout.mainfragment,null);
            TextView content = view.findViewById(R.id.content);
            if(canneInfos.get(i).getDescription()!=null){
                content.setText(canneInfos.get(i).getDescription());
            }else{
                content.setText("无");
            }
            TextView title = view.findViewById(R.id.title);
            if(canneInfos.get(i).getName()!=null){
                title.setText(canneInfos.get(i).getName());
            }else{
                title.setText("无");
            }
            TextView comentnum = view.findViewById(R.id.comentnum);
            if(canneInfos.get(i).getCreator()!=null){
                comentnum.setText(canneInfos.get(i).getCreator());
            }else{
                comentnum.setText("无");
            }
            ImageView image = view.findViewById(R.id.image);
            if(canneInfos.get(i).getImageUrl()!=null){
                GlideUtil.load(getActivity(),AppConfig.BASE_PICTURE_URL+canneInfos.get(i).getImageUrl(),image,GlideUtil.getOption());
            }else{
                Glide.with(getActivity()).load(R.mipmap.default_image).apply(GlideUtil.getOption()).into(image);
            }

            viewflipper.addView(view);
        }

        viewflipper.setInAnimation(getActivity(),R.anim.come_in);
        viewflipper.setOutAnimation(getActivity(),R.anim.come_out);
        viewflipper.setFlipInterval(5000);
        viewflipper.setAutoStart(true);
        viewflipper.isAutoStart();
    }
}
