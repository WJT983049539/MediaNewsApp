package com.rcdz.medianewsapp.view.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
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
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.UserInfoBean;
import com.rcdz.medianewsapp.tools.ACache;
import com.rcdz.medianewsapp.tools.AppConfig;
import com.rcdz.medianewsapp.tools.GlobalToast;
import com.rcdz.medianewsapp.view.activity.LiveRoomActivity;
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
public class MainCenterFragmentTest extends Fragment {
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
        ButterKnife.bind(view);
        initView();
        return view;
    }

    private void initView() {
        ACache aCache=ACache.get(getActivity());
        userInfoBean= (UserInfoBean) aCache.getAsObject("userinfo");
        Glide.with(getActivity()).load(AppConfig.BASE_PICTURE_URL+ userInfoBean.getData().getHeadImageUrl()).apply(options).into(imgHead);
        String trueName=userInfoBean.getData().getUserTrueName();
        if(userInfoBean.getData()!=null&&userInfoBean.getData().getUserTrueName()!=null){
            tvName.setText(trueName);
        }
        Object remark=userInfoBean.getData().getRemark();
        if(remark!=null){
            tvRemake.setText(remark.toString());
        }else{
            tvRemake.setText("无签名");
        }

    }

    @OnClick({R.id.img_head, R.id.tv_sign, R.id.constraintLayout, R.id.linearLayout, R.id.imageView17, R.id.lin_message, R.id.lin_collect, R.id.lin_history, R.id.lin_suggest, R.id.lin_jifen, R.id.lin_yuyue, R.id.lin_version, R.id.lin_set, R.id.lin_clearcache, R.id.lin_faimlily, R.id.lin_share, R.id.lin_guanyu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_head:
                break;
            case R.id.tv_sign:
                break;
            case R.id.constraintLayout:
                break;
            case R.id.linearLayout:
                break;
            case R.id.imageView17:
                break;
            case R.id.lin_message:
                break;
            case R.id.lin_collect:
                break;
            case R.id.lin_history:
                break;
            case R.id.lin_suggest:
                break;
            case R.id.lin_jifen:
                break;
            case R.id.lin_yuyue:
                break;
            case R.id.lin_version:
                break;
            case R.id.lin_set:
                break;
            case R.id.lin_clearcache:
                break;
            case R.id.lin_faimlily:
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
}
