package com.rcdz.medianewsapp.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.view.fragment.Login_PhoneFragment;
import com.rcdz.medianewsapp.view.fragment.Login_PwdFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/12 10:43
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.showlin)
    LinearLayout showlin;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.tv_agreement)
    TextView tvAgreement;
    @BindView(R.id.login_phone)
    TextView loginPhone;
    @BindView(R.id.login_pwd)
    TextView loginPwd;
    @BindView(R.id.tg)
    TextView tg;

    @Override
    public String setNowActivityName() {
        return null;
    }

    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void inintView() {
        ButterKnife.bind(this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.showlin, new Login_PhoneFragment(), "login_phone");
        fragmentTransaction.commit();
    }

    @Override
    public void inintData() {

    }


    @OnClick({R.id.register, R.id.tv_agreement, R.id.login_phone, R.id.login_pwd,R.id.tg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register:
                openActivity(RegisterActivity.class);
                break;
            case R.id.tv_agreement:
                startActivity(new Intent(this,ShowXieYiBookActivity.class));
                break;
            case R.id.login_phone: //手机号码登录
                loginPhone.setTextColor(Color.parseColor("#000000"));
                loginPwd.setTextColor(Color.parseColor("#686868"));

                FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.showlin, new Login_PhoneFragment(), "login_phone");
                fragmentTransaction2.commit();
                break;
            case R.id.login_pwd:   //手机密码登录
                loginPhone.setTextColor(Color.parseColor("#686868"));
                loginPwd.setTextColor(Color.parseColor("#000000"));
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.showlin, new Login_PwdFragment(), "login_pwd");
                fragmentTransaction.commit();

                break;
            case  R.id.tg:
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}
