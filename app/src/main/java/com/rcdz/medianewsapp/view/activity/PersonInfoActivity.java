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
public class PersonInfoActivity extends BaseActivity implements GetUserInfo {
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
        newNetWorkPersenter.GetUserInfo("",this);
    }


    @Override
    public void getUserInfo(UserInfoBean userInfoBean) {
        RequestOptions options = new RequestOptions().error(R.mipmap.peop).centerCrop();
        if(userInfoBean!=null&&userInfoBean.getData()!=null){
            Log.i("test",userInfoBean.getData().toString());
            Glide.with(this).load(AppConfig.BASE_PICTURE_URL+ userInfoBean.getData().getHeadImageUrl()).apply(options).into(pHead);//头像
            if(userInfoBean.getData().getUserName()!=null){
                pNick.setText(userInfoBean.getData().getUserName());
            }
            if(userInfoBean.getData().getAddress()!=null){
                pAddress.setText(userInfoBean.getData().getAddress().toString());
            }
            if(userInfoBean.getData().getRemark()!=null){
                pRemake.setText(userInfoBean.getData().getRemark().toString());
            }
            if(userInfoBean.getData().getPhoneNo()!=null){
                pPhone.setText(userInfoBean.getData().getPhoneNo());
            }
            if(Integer.valueOf(userInfoBean.getData().getGender())==0){
                pPhone.setText("男");
            }
            if(Integer.valueOf(userInfoBean.getData().getGender())==1){
                pPhone.setText("女");
            }

        }
    }
}
