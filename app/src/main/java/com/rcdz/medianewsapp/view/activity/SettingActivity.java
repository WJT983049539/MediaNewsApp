package com.rcdz.medianewsapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.UserInfoBean;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.tools.ACache;
import com.rcdz.medianewsapp.tools.Constant;
import com.rcdz.medianewsapp.tools.SharedPreferenceTools;
import com.rcdz.medianewsapp.view.customview.OutLoginDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/23 20:07
 */
public class SettingActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.updatepsd)
    ConstraintLayout updatepsd;
    @BindView(R.id.outlogin)
    ConstraintLayout outlogin;

    @Override
    public String setNowActivityName() {
        return null;
    }

    @Override
    public int setLayout() {
        return R.layout.set;
    }

    @Override
    public void inintView() {
        ButterKnife.bind(this);
    }

    @Override
    public void inintData() {

    }


    @OnClick({R.id.back, R.id.updatepsd, R.id.outlogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.updatepsd:
                Intent intent=new Intent(this,updatePsdActivity.class);
                startActivity(intent);
                break;
            case R.id.outlogin:
                OutLoginDialog outLoginDialog=new OutLoginDialog(SettingActivity.this, new OutLoginDialog.DialogConfirmClick() {
                    @Override
                    public void ConfirmClick() {
                        SharedPreferenceTools.putValuetoSP(SettingActivity.this,"loginStru",false);//登录状态保存到共享参数
                        ACache aCache=ACache.get(SettingActivity.this);
                        UserInfoBean userInfoBean=null;
                        aCache.put("userinfo",userInfoBean);
                        SharedPreferenceTools.putValuetoSP(SettingActivity.this,"user","");//不是第一次登录了
                        SharedPreferenceTools.putValuetoSP(SettingActivity.this,"token","");//保存到共享参数
                        Constant.token="";
//                        Intent intent=new Intent(SettingActivity.this,LoginActivity.class);
//                        startActivity(intent);
                        SettingActivity.this.finish();

                    }

                    @Override
                    public void noClick() {

                    }
                });
                outLoginDialog.show();
                break;
        }
    }
}
