package com.rcdz.medianewsapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.BaseBean;
import com.rcdz.medianewsapp.model.bean.LoginBean;
import com.rcdz.medianewsapp.model.bean.UserInfoBean;
import com.rcdz.medianewsapp.persenter.interfaces.GetPhoneCode;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetUserInfo;
import com.rcdz.medianewsapp.persenter.interfaces.IshowLogin;
import com.rcdz.medianewsapp.tools.ACache;
import com.rcdz.medianewsapp.tools.Comment;
import com.rcdz.medianewsapp.tools.Constant;
import com.rcdz.medianewsapp.tools.GlobalToast;
import com.rcdz.medianewsapp.tools.PhoneFormatCheckUtils;
import com.rcdz.medianewsapp.tools.SharedPreferenceTools;
import com.rcdz.medianewsapp.view.activity.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/12 12:04
 */
public class Login_PhoneFragment extends Fragment implements GetPhoneCode, IshowLogin , GetUserInfo {
    @BindView(R.id.login_psd_pwd)
    EditText loginPsdPwd;
    @BindView(R.id.login_phone_user)
    EditText loginPhoneUser;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.button_login_phone)
    Button buttonLoginPhone;
    @BindView(R.id.smscode)
    TextView smscode;
    NewNetWorkPersenter newNetWorkPersenter;
    int vc_time = 60;
    Handler mHandler_vc;
    private String phone;
    private String smsCode;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login_phone, container, false);
        ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    private void initView(View view) {
         newNetWorkPersenter=new NewNetWorkPersenter(getActivity());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @OnClick({R.id.login_psd_pwd, R.id.login_phone_user, R.id.button_login_phone, R.id.smscode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_psd_pwd:


                break;
            case R.id.login_phone_user:
                break;
            case R.id.button_login_phone://登录
                if(loginPhoneUser.getText()!=null&&PhoneFormatCheckUtils.isPhoneLegal(loginPhoneUser.getText().toString())){
                    phone=loginPhoneUser.getText().toString();
                }
                if(loginPsdPwd.getText()!=null&& Comment.isNumeric(loginPsdPwd.getText().toString())){
                    smsCode=loginPsdPwd.getText().toString();
                }
                newNetWorkPersenter.AppLoginForMes(phone,smsCode,Login_PhoneFragment.this);

                break;
                //获取验证码
            case R.id.smscode:

                if(loginPhoneUser.getText()!=null){
                    String phone1=loginPhoneUser.getText().toString();
                    if(PhoneFormatCheckUtils.isChinaPhoneLegal(phone1)){
                        newNetWorkPersenter.GetPhoneCode(phone1,Login_PhoneFragment.this);

                    }else{
                        GlobalToast.show("请输入正确手机号码",5000);
                    }
                }


                break;
        }
    }
    //得到验证码
    @Override
    public void getPhoneCode(BaseBean value) {
        if(value.getCode()==200){
            GlobalToast.show(value.getMessage(),5000);
            changeSmsCodeStyle();  //发送成功
        }
    }

    private void changeSmsCodeStyle(){
        //转换获取验证码按钮样式（60s不可重新获取）
        mHandler_vc = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        // 完成主界面更新,拿到数据
                        vc_time--;
                        smscode.setText("重新获取(" + vc_time + "s)");
                        if (vc_time <= 0) {
                            // getSmsCode.setBackgroundColor(0xffff983d);//0xFF626262   0xFFfc3c17
                            smscode.setBackgroundResource(R.drawable.com_button_bg);
                            smscode.setText("获取验证码");
                            smscode.setEnabled(true);
                        }
                        break;
                }
            }
        };
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        // 耗时操作，完成之后发送消息给Handler，完成UI更新；
                        mHandler_vc.sendEmptyMessage(0);
                        if (vc_time <= 0) {
                            break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    //登录成功
    @Override
    public void ishowLogin(LoginBean loginBean) {
        if(loginBean.getCode()==310){
            if(loginBean.getData().getToken()!=null){
                Log.i("Token",loginBean.getData().getToken());
                String token=loginBean.getData().getToken();
                if(token==null||token.equals("")){
                    GlobalToast.show("Token获取失败",2000);
                    Log.i("Token","Token获取失败");
                }else{
                    NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(getActivity());
                    newNetWorkPersenter.GetUserInfo("",this);
                    SharedPreferenceTools.putValuetoSP(getActivity(),"token",token);//保存到共享参数
                    SharedPreferenceTools.putValuetoSP(getActivity(),"loginStru",true);//登录状态保存到共享参数
                    SharedPreferenceTools.putValuetoSP(getActivity(),"isFirstStart",false);//不是第一次登录了
                    Constant.token=token;//保存到临时变量里面
                    ACache aCache=ACache.get(getActivity());
                    aCache.put("loginInfo",loginBean);//储存到缓存 ，，用户变更需要改变
                    startActivity(new Intent(getActivity(), MainActivity.class));
                    //获取个人信息

                }
            }else{
                GlobalToast.show("登录成功,但token为空",2000);
            }
        }else{
            GlobalToast.show(loginBean.getMessage(),2000);
        }
    }

    @Override
    public void getUserInfo(UserInfoBean userInfoBean) {
        ACache aCache=ACache.get(getActivity());
        aCache.put("userinfo",userInfoBean);
    }
}
