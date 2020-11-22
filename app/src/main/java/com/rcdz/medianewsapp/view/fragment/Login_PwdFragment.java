package com.rcdz.medianewsapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.LoginBean;
import com.rcdz.medianewsapp.model.bean.UserInfoBean;
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
import com.rcdz.medianewsapp.view.activity.RetrievePsdActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用:密码登录
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/12 11:39
 */
public class Login_PwdFragment extends Fragment implements IshowLogin, GetUserInfo {

    @BindView(R.id.login_psd_pwd)
    EditText loginPsdPwd;
    @BindView(R.id.login_phone_user)
    EditText loginPsdUser;
    @BindView(R.id.forgetpsd)
    TextView forgetpsd;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.button_login)
    Button buttonLogin;
    private String user;
    private String psd;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login_psd, container, false);
        ButterKnife.bind(this, view);
        initView();


        return view;
    }

    public void initView() {
        String user= (String) SharedPreferenceTools.getValueofSP(getActivity(),"user","");
        loginPsdUser.setText(user);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @OnClick({R.id.login_psd_pwd, R.id.login_phone_user, R.id.forgetpsd, R.id.button_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_psd_pwd:
                break;
            case R.id.login_phone_user:
                break;
            case R.id.forgetpsd: //忘记密码
                Intent intent=new Intent(getActivity(), RetrievePsdActivity.class);
                startActivity(intent);
                break;
            case R.id.button_login:

                if(loginPsdUser.getText()==null||loginPsdUser.getText().equals("")){
                  GlobalToast.show4("用户名不能为空",Toast.LENGTH_LONG);
                  return;
                }else{
                    user=loginPsdUser.getText().toString();
                }
                if(loginPsdPwd.getText()==null||loginPsdPwd.getText().equals("")){
                    GlobalToast.show4("密码不能为空",Toast.LENGTH_LONG);
                    return;
                }else{
                    psd=loginPsdPwd.getText().toString();
                }
                NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(getActivity());
                newNetWorkPersenter.AppLogin(user,psd,Login_PwdFragment.this);

                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void ishowLogin(LoginBean loginBean) { //登录成功
        //{"status":true,"code":310,"message":"登陆成功","data":{"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiIxNiIsImlhdCI6IjE2MDQ2MjgwOTkiLCJuYmYiOiIxNjA0NjI4MDk5IiwiZXhwIjoiMTYwNTIzMjg5OSIsImlzcyI6IkZ1c2lvbk1lZGlhLmNvcmUub3duZXIiLCJhdWQiOiJGdXNpb25NZWRpYS5jb3JlIn0.jMAaJX2OJfBeyfeS486nGmcwLB2z7HObCSp2FGmnUzk",
        // "userName":"我他发可",
        // "img":"Upload/Files/Sys_User/07610eac938141fe8259e166b96d5a09/small/temp.jpg",
        // "user":"ccaaa"}}
        if(loginBean.getCode()==310){
            if(loginBean.getData().getToken()!=null){
                Log.i("Token",loginBean.getData().getToken());
                String token=loginBean.getData().getToken();
                String user=loginBean.getData().getUser();
                if(token==null||token.equals("")){
                    GlobalToast.show("Token获取失败",2000);
                    Log.i("Token","Token获取失败");
                }else{
                    SharedPreferenceTools.putValuetoSP(getActivity(),"token",token);//保存到共享参数
                    SharedPreferenceTools.putValuetoSP(getActivity(),"loginStru",true);//登录状态保存到共享参数
                    SharedPreferenceTools.putValuetoSP(getActivity(),"isFirstStart",false);//不是第一次登录了
                    SharedPreferenceTools.putValuetoSP(getActivity(),"user",user);//不是第一次登录了

                    Constant.token=token;//保存到临时变量里面
                    ACache aCache=ACache.get(getActivity());
                    aCache.put("loginInfo",loginBean);//储存到缓存 ，，用户变更需要改变
                    //获取个人信息
                    NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(getActivity());
                    newNetWorkPersenter.GetUserInfo("",this);

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
        new Thread(new Runnable() {
            @Override
            public void run() {
                ACache aCache=ACache.get(getActivity());
                aCache.put("userinfo",userInfoBean);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
                        getActivity().finish();
                    }
                });

            }
        }).start();
    }
}
