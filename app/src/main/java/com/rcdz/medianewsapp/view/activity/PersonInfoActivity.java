package com.rcdz.medianewsapp.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.LivingMasterBean;
import com.rcdz.medianewsapp.model.bean.UserInfoBean;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetLivingMInfo;
import com.rcdz.medianewsapp.persenter.interfaces.GetUserInfo;
import com.rcdz.medianewsapp.tools.AppConfig;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/23 14:13
 */
public class PersonInfoActivity extends BaseActivity implements GetLivingMInfo {
    @BindView(R.id.p_back)
    ImageView pBack;
    @BindView(R.id.p_head)
    ImageView pHead;
    @BindView(R.id.p_nick)
    TextView pNick;
    @BindView(R.id.p_remake)
    TextView pRemake;
    @BindView(R.id.p_sex)
    TextView pSex;
    @BindView(R.id.sex_women)
    TextView sexWomen;
    @BindView(R.id.p_phone)
    TextView pPhone;
    @BindView(R.id.p_address)
    TextView pAddress;
    private String userId = "";

    @Override
    public String setNowActivityName() {
        return null;
    }

    @Override
    public int setLayout() {
        return R.layout.personinfo;
    }

    @Override
    public void inintView() {
        ButterKnife.bind(this);
        userId = String.valueOf(getIntent().getIntExtra("userId", -1));
        pBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonInfoActivity.this.finish();
            }
        });
    }

    @Override
    public void inintData() {
        NewNetWorkPersenter newNetWorkPersenter = new NewNetWorkPersenter(this);
        newNetWorkPersenter.GetLivingMasterInfo(userId, this);
    }


    @Override
    public void getinfo(LivingMasterBean livingMasterBean) {
        RequestOptions options = new RequestOptions().error(R.mipmap.peop).centerCrop();
        if(livingMasterBean!=null&&livingMasterBean.getData()!=null){
            Log.i("test",livingMasterBean.getData().toString());
            Glide.with(this).load(AppConfig.BASE_PICTURE_URL+ livingMasterBean.getData().getHeadImageUrl()).apply(options).into(pHead);//头像
            if(livingMasterBean.getData().getUserName()!=null){
                pNick.setText(livingMasterBean.getData().getUserName());
            }
            if(livingMasterBean.getData().getAddress()!=null){
                pAddress.setText(livingMasterBean.getData().getAddress().toString());
            }
            if(livingMasterBean.getData().getRemark()!=null){
                pRemake.setText(livingMasterBean.getData().getRemark().toString());
            }
            if(livingMasterBean.getData().getPhoneNo()!=null){
                pPhone.setText(livingMasterBean.getData().getPhoneNo());
            }
                if(Integer.valueOf(livingMasterBean.getData().getPhoneNo())==0){
                    pPhone.setText("男");
                }
                if(Integer.valueOf(livingMasterBean.getData().getPhoneNo())==1){
                    pPhone.setText("女");
                }

        }
    }
}
