package com.rcdz.medianewsapp.view.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
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
//                        SharedPreferenceTools.putValuetoSP(SettingActivity.this,"user","");//不是第一次登录了
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
                //隐藏键盘
                outLoginDialog.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                outLoginDialog.getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int visibility) {
                        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                                //布局位于状态栏下方
                                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                                //全屏
                                View.SYSTEM_UI_FLAG_FULLSCREEN |
                                //隐藏导航栏
                                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                        if (Build.VERSION.SDK_INT >= 19) {
                            uiOptions |= 0x00001000;
                        } else {
                            uiOptions |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
                        }
                        outLoginDialog.getWindow().getDecorView().setSystemUiVisibility(uiOptions);
                    }
                });
                outLoginDialog.show();
                break;
        }
    }
}
