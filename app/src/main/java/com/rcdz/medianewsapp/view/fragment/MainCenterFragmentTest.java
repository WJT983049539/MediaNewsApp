package com.rcdz.medianewsapp.view.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.UserInfoBean;
import com.rcdz.medianewsapp.persenter.CommApi;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetSignStatus;
import com.rcdz.medianewsapp.persenter.interfaces.GetUserInfo;
import com.rcdz.medianewsapp.tools.ACache;
import com.rcdz.medianewsapp.tools.AppConfig;
import com.rcdz.medianewsapp.tools.DataCleanManager;
import com.rcdz.medianewsapp.tools.GlobalToast;
import com.rcdz.medianewsapp.view.activity.CommentActivity;
import com.rcdz.medianewsapp.view.activity.LiveRoomActivity;
import com.rcdz.medianewsapp.view.activity.MyCollectActivity;
import com.rcdz.medianewsapp.view.activity.MyFaililyActivity;
import com.rcdz.medianewsapp.view.activity.MyHistoryActivity;
import com.rcdz.medianewsapp.view.activity.MyJifenActivity;
import com.rcdz.medianewsapp.view.activity.MyYuYueActivity;
import com.rcdz.medianewsapp.view.activity.PersonalInformationActivity;
import com.rcdz.medianewsapp.view.activity.SettingActivity;
import com.rcdz.medianewsapp.view.activity.SuggestActivity;
//import com.rcdz.medianewsapp.view.activity.MyJifenActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/13 15:45
 */
public class MainCenterFragmentTest extends Fragment implements GetSignStatus, GetUserInfo {
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.tv_sign)
    TextView tvSign;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_remake)
    TextView tvRemake;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.imageView17)
    ImageView imageView17;
    @BindView(R.id.lin_message)
    LinearLayout linMessage;
    @BindView(R.id.lin_collect)
    LinearLayout linCollect;
    @BindView(R.id.lin_history)
    LinearLayout linHistory;
    @BindView(R.id.lin_suggest)
    LinearLayout linSuggest;
    @BindView(R.id.lin_jifen)
    LinearLayout linJifen;
    @BindView(R.id.lin_yuyue)
    LinearLayout linYuyue;
    @BindView(R.id.lin_version)
    LinearLayout linVersion;
    @BindView(R.id.lin_set)
    LinearLayout linSet;
    @BindView(R.id.lin_clearcache)
    LinearLayout linClearcache;
    @BindView(R.id.lin_faimlily)
    LinearLayout linFaimlily;
    @BindView(R.id.textView23)
    TextView textView23;
    @BindView(R.id.lin_share)
    LinearLayout linShare;
    @BindView(R.id.lin_guanyu)
    LinearLayout linGuanyu;
    private String str_Version;
    private Boolean SignStatus;
    private UserInfoBean userInfoBean;
    private RequestOptions options = new RequestOptions().error(R.mipmap.peop).centerCrop();
    public MainCenterFragmentTest() {
    }

    public static Fragment getInstance() {
        return new MainCenterFragmentTest();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newcenter3, container, false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    private void initView() {
        getSignStatus();
        ACache aCache=ACache.get(getActivity());
        userInfoBean = (UserInfoBean) aCache.getAsObject("userinfo");
        if(userInfoBean!=null&&userInfoBean.getData()!=null){
            if(userInfoBean!=null&&userInfoBean.getData()!=null&&userInfoBean.getData().getHeadImageUrl()!=null){
                String headimg= (String) userInfoBean.getData().getHeadImageUrl();
                Glide.with(getActivity()).load(AppConfig.BASE_PICTURE_URL+ headimg).apply(options).into(imgHead);
            }else {
                Glide.with(getActivity()).load(R.mipmap.peop).apply(options).into(imgHead);
            }

            if(userInfoBean!=null&&userInfoBean.getData()!=null&&userInfoBean.getData().getUserTrueName()!=null){
                String trueName=userInfoBean.getData().getUserTrueName();
                tvName.setText(trueName);
            }
            Object remark=userInfoBean.getData().getRemark();
            if(remark!=null){
                tvRemake.setText(remark.toString());
            }else{
                tvRemake.setText("无签名");
            }
        }else{
            NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(getActivity());
            newNetWorkPersenter.GetUserInfo("",this);
        }


    }

    private void getSignStatus() {
        Map<String,String> areaMap = new HashMap<String,String>();
        CommApi.postNoParams("api/Sys_UserSignIn/IsSignIn").execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if(response.body()!=null){
                    Log.i("test","签到状态-->"+response.body());
                    try {
                        JSONObject jsonObject=new JSONObject(response.body());
                        int code=jsonObject.getInt("code");
                        if(code==200){
                            SignStatus = jsonObject.getBoolean("data");
                            if(SignStatus){
                                tvSign.setBackgroundResource(R.mipmap.sign_true);
                            }else{
                                tvSign.setBackgroundResource(R.mipmap.sign_false);
                            }

                        }else{
                            GlobalToast.show("获取签到状态失败！",Toast.LENGTH_LONG);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(Response response) {
                super.onError(response);
                Log.i("test","签到状态失败-->"+response.message());
                GlobalToast.show("获取签到状态失败！",Toast.LENGTH_LONG);
            }
        });
    }

    @OnClick({R.id.img_head, R.id.tv_sign, R.id.constraintLayout, R.id.linearLayout, R.id.imageView17, R.id.lin_message, R.id.lin_collect, R.id.lin_history, R.id.lin_suggest, R.id.lin_jifen, R.id.lin_yuyue, R.id.lin_version, R.id.lin_set, R.id.lin_clearcache, R.id.lin_faimlily, R.id.lin_share, R.id.lin_guanyu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_head:
                Intent intent = new Intent(getActivity(), PersonalInformationActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_sign:
                //去签到
                if(SignStatus){
                    GlobalToast.show("已经签到",Toast.LENGTH_LONG);
                }else{
                    NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(getActivity());
                    newNetWorkPersenter.AddSignJifen("7",this);
                }

                break;
            case R.id.constraintLayout:
                break;
            case R.id.linearLayout:
                break;
            case R.id.imageView17:
                break;
            case R.id.lin_message:
                Intent intent8 = new Intent(getActivity(), CommentActivity.class);
                startActivity(intent8);
                break;
            case R.id.lin_collect:
                Intent intent3 = new Intent(getActivity(), MyCollectActivity.class);
                startActivity(intent3);
                break;
            case R.id.lin_history:
                Intent intent5 = new Intent(getActivity(), MyHistoryActivity.class);
                startActivity(intent5);
                break;
            case R.id.lin_suggest://todo 意见反馈
                Intent intent7 = new Intent(getActivity(), SuggestActivity.class);
                startActivity(intent7);
                break;
            case R.id.lin_jifen:
                    Intent intent2 = new Intent(getActivity(), MyJifenActivity.class);
                    startActivity(intent2);
                break;
            case R.id.lin_yuyue:
                Intent intent6 = new Intent(getActivity(), MyYuYueActivity.class);
                startActivity(intent6);
                break;
            case R.id.lin_version:
                GlobalToast.show("已是最新版本",Toast.LENGTH_LONG);
                break;
            case R.id.lin_set:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.lin_clearcache:
                DataCleanManager.clearAllCache(getActivity());
                GlobalToast.show("清除成功",Toast.LENGTH_LONG);
                break;
            case R.id.lin_faimlily:
                Intent intent4 = new Intent(getActivity(), MyFaililyActivity.class);
                startActivity(intent4);
                break;
            case R.id.lin_share:
                break;
            case R.id.lin_guanyu:
                getVersionMsg();//获取版本信息
                new AlertDialog.Builder(getActivity()).setTitle("关于我们").setMessage("公众号:\n山西卓至飞高电子科技有限公司\n\n" + str_Version)
                        .setPositiveButton("确定", null)
                        .show();
                break;
        }
    }

    private void getVersionMsg() {
        try {
            String pkName = getActivity().getPackageName();
            String versionName =getActivity().getPackageManager().getPackageInfo(pkName, 0).versionName;
            int versionCode = getActivity().getPackageManager().getPackageInfo(pkName, 0).versionCode;
            str_Version += ("版本号:" + versionName);
            if (AppConfig.CS) {//判断是否是测试状态
                str_Version += ("\n内部版本号:" + versionCode);
            }
        } catch (Exception e) {
            GlobalToast.show("获取版本号错误",Toast.LENGTH_SHORT);
            str_Version = "获取版本号错误";
        }
    }
    //添加积分成功
    @Override
    public void ShowSignStatus(Boolean status) {
        if(status){
            tvSign.setBackgroundResource(R.mipmap.sign_true);
        }else{
            tvSign.setBackgroundResource(R.mipmap.sign_false);
        }
        getSignStatus();
    }

    @Override
    public void getUserInfo(UserInfoBean userInfoBean) {
        ACache aCache=ACache.get(getActivity());
        aCache.put("userinfo",userInfoBean);
        if(userInfoBean!=null&&userInfoBean.getData()!=null&&userInfoBean.getData().getHeadImageUrl()!=null){
            String headimg= (String) userInfoBean.getData().getHeadImageUrl();
            Glide.with(getActivity()).load(AppConfig.BASE_PICTURE_URL+ headimg).apply(options).into(imgHead);
        }else {
            Glide.with(getActivity()).load(R.mipmap.peop).apply(options).into(imgHead);
        }

        if(userInfoBean!=null&&userInfoBean.getData()!=null&&userInfoBean.getData().getUserTrueName()!=null){
            String trueName=userInfoBean.getData().getUserTrueName();
            tvName.setText(trueName);
        }
        Object remark=userInfoBean.getData().getRemark();
        if(remark!=null){
            tvRemake.setText(remark.toString());
        }else{
            tvRemake.setText("无签名");
        }
    }
}
