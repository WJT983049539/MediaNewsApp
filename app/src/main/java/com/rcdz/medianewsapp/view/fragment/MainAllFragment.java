package com.rcdz.medianewsapp.view.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.tools.GlobalToast;
import com.rcdz.medianewsapp.view.activity.MainActivity;
import com.rcdz.medianewsapp.view.activity.ModelNetWebActivity;
import com.rcdz.medianewsapp.view.customview.GzhDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/13 15:44
 */
public class MainAllFragment extends Fragment {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.linearLayout1)
    TextView linearLayout1;
    @BindView(R.id.tv_xzsp)
    LinearLayout tvXzsp;
    @BindView(R.id.tv_xswz)
    LinearLayout tvXswz;
    @BindView(R.id.tv_jgdw)
    LinearLayout tvJgdw;
    @BindView(R.id.tv_gzh)
    LinearLayout tvGzh;
    @BindView(R.id.tv_zhdj)
    LinearLayout tvZhdj;
    @BindView(R.id.lin_tv)
    LinearLayout linTv;
    @BindView(R.id.tv_tgb)
    LinearLayout tvTgb;
    @BindView(R.id.tv_dbz)
    LinearLayout tvDbz;
    @BindView(R.id.tv_swz)
    LinearLayout tvSwz;
    @BindView(R.id.tv_cgj)
    LinearLayout tvCgj;
    @BindView(R.id.tv_ggzxc)
    LinearLayout tvGgzxc;
    @BindView(R.id.tv_cstcw)
    LinearLayout tvCstcw;
    @BindView(R.id.tv_cwz)
    LinearLayout tvCwz;
    @BindView(R.id.tv_cky)
    LinearLayout tvCky;
    @BindView(R.id.tv_hcp)
    LinearLayout tvHcp;
    @BindView(R.id.tv_clk)
    LinearLayout tvClk;
    @BindView(R.id.tv_csb)
    LinearLayout tvCsb;
    @BindView(R.id.tv_jdf)
    LinearLayout tvJdf;
    @BindView(R.id.tv_jsf)
    LinearLayout tvJsf;
    @BindView(R.id.tv_rqf)
    LinearLayout tvRqf;
    @BindView(R.id.tv_jhf)
    LinearLayout tvJhf;
    @BindView(R.id.tv_dsf)
    LinearLayout tvDsf;
    @BindView(R.id.tv_tckd)
    LinearLayout tvTckd;
    @BindView(R.id.tv_gcsc)
    LinearLayout tvGcsc;
    @BindView(R.id.tv_tcfc)
    LinearLayout tvTcfc;
    @BindView(R.id.tv_tczp)
    LinearLayout tvTczp;
//    @BindView(R.id.tv_jzfw2)
//    LinearLayout tvJzfw;
    @BindView(R.id.tv_fwzx)
    LinearLayout tvFwzx;
    @BindView(R.id.tv_fwpt)
    LinearLayout tvFwpt;
    @BindView(R.id.tv_lssws)
    LinearLayout tvLssws;
    @BindView(R.id.tv_yycx)
    LinearLayout tv_yycx;
    @BindView(R.id.tv_yygh)
    TextView tvYygh;
    @BindView(R.id.yygh)
    LinearLayout linearLayout30;
    @BindView(R.id.tv_zhyb)
    LinearLayout tvZhyb;
    @BindView(R.id.tv_ggcx)
    LinearLayout tvGgcx;
    @BindView(R.id.tv_xxcx)
    LinearLayout tvXxcx;
    @BindView(R.id.tv_pcscx)
    LinearLayout tvPcscx;
    @BindView(R.id.tv_bszn)
    LinearLayout tvBszn;
    @BindView(R.id.tv_sfyz)
    LinearLayout tvSfyz;
    @BindView(R.id.lin_ydcx)
    LinearLayout lin_ydcx;

    public MainAllFragment() {
    }

    public static Fragment getInstance() {
        return new MainAllFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.technologys2, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @OnClick({R.id.toolbar_title, R.id.toolbar, R.id.lin_ydcx,R.id.tv_yycx,R.id.tv_xzsp, R.id.tv_xswz, R.id.tv_jgdw, R.id.tv_gzh,  R.id.lin_tv, R.id.tv_tgb, R.id.tv_dbz, R.id.tv_swz, R.id.tv_cgj, R.id.tv_ggzxc, R.id.tv_cstcw, R.id.tv_cwz, R.id.tv_cky, R.id.tv_hcp, R.id.tv_clk, R.id.tv_csb, R.id.tv_jdf, R.id.tv_jsf, R.id.tv_rqf, R.id.tv_jhf, R.id.tv_dsf, R.id.tv_tckd, R.id.tv_gcsc, R.id.tv_tcfc, R.id.tv_tczp, R.id.tv_fwzx, R.id.tv_fwpt, R.id.tv_lssws,  R.id.tv_yygh, R.id.yygh, R.id.tv_zhyb,  R.id.tv_ggcx, R.id.tv_xxcx, R.id.tv_pcscx, R.id.tv_bszn, R.id.tv_sfyz})
    public void onViewClicked(View view) {
        Intent intent = new Intent(getActivity(), ModelNetWebActivity.class);
        switch (view.getId()) {
            case R.id.toolbar_title:
                break;
            case R.id.toolbar:
                break;
            case R.id.tv_xzsp://闻喜政务
                intent.putExtra("type", "1197");
                getActivity().startActivity(intent);
                break;
            case R.id.tv_xswz://线上问政
                MainActivity mainActivity3 = (MainActivity) getActivity();
                mainActivity3.setPositon2(0);
                break;
            case R.id.tv_jgdw://机构单位
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.setPositon2(1);
                break;
            case R.id.tv_gzh://公众号
//                GlobalToast.show4("暂未开放",Toast.LENGTH_LONG);
                GzhDialog gzhDialog=new GzhDialog(getActivity());
                gzhDialog.setOnDialogListen(new GzhDialog.Confirm() {
                    @Override
                    public void cannal() {
                        gzhDialog.cancel();
                    }
                });
                gzhDialog.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                gzhDialog.getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
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
                        gzhDialog.getWindow().getDecorView().setSystemUiVisibility(uiOptions);
                    }
                });
                gzhDialog.show();
                break;
            case R.id.tv_zhdj://智慧党建
//                intent.putExtra("type", "1198");
//                getActivity().startActivity(intent);
                break;
            case R.id.lin_tv://看电视
                intent.putExtra("type", "1156");
                getActivity().startActivity(intent);
                break;
            case R.id.tv_tgb://听广播
                intent.putExtra("type", "1122");
                getActivity().startActivity(intent);
                break;
            case R.id.tv_dbz://读报纸
                intent.putExtra("type", "1123");
                getActivity().startActivity(intent);
                break;
            case R.id.tv_swz://上网站
                intent.putExtra("type", "1124");
                getActivity().startActivity(intent);
                break;
            case R.id.tv_cgj://查公交
                intent.putExtra("type", "1131");
                getActivity().startActivity(intent);
                break;
            case R.id.tv_ggzxc://公共自行车
                intent.putExtra("type", "1132");
                getActivity().startActivity(intent);
                break;
            case R.id.tv_cstcw://城市停车位
                intent.putExtra("type", "1133");
                getActivity().startActivity(intent);
                break;
            case R.id.tv_cwz://查违章
                intent.putExtra("type", "1134");
                getActivity().startActivity(intent);
                break;
            case R.id.tv_cky://查客运
                intent.putExtra("type", "1135");
                getActivity().startActivity(intent);
                break;
            case R.id.tv_hcp://火车票
                intent.putExtra("type", "1136");
                getActivity().startActivity(intent);
                break;
            case R.id.tv_clk://查路况
                intent.putExtra("type", "1137");
                getActivity().startActivity(intent);
                break;
            case R.id.tv_csb://查社保
                intent.putExtra("type", "1141");
                getActivity().startActivity(intent);
                break;
            case R.id.tv_jdf://交电费
                intent.putExtra("type", "1142");
                getActivity().startActivity(intent);
                break;
            case R.id.tv_jsf://交水费
                intent.putExtra("type", "1143");
                getActivity().startActivity(intent);
                break;
            case R.id.tv_rqf://燃气费
                intent.putExtra("type", "1144");
                getActivity().startActivity(intent);
                break;
            case R.id.tv_jhf://交话费
                intent.putExtra("type", "1145");
                getActivity().startActivity(intent);
                break;
            case R.id.tv_dsf://电视费
                intent.putExtra("type", "1146");
                getActivity().startActivity(intent);
                break;
            case R.id.tv_tckd://同城快递
                intent.putExtra("type", "1151");
                getActivity().startActivity(intent);
                break;
            case R.id.tv_gcsc://家政服务
                intent.putExtra("type", "1155");
                getActivity().startActivity(intent);
//                GlobalToast.show4("暂未开放",Toast.LENGTH_LONG);
                break;
            case R.id.tv_tcfc://同城房产
                intent.putExtra("type", "1153");
                getActivity().startActivity(intent);
                break;
            case R.id.tv_tczp://同城招聘
                intent.putExtra("type", "1154");
                getActivity().startActivity(intent);
                break;
//            case R.id.tv_jzfw2://家政服务
//                intent.putExtra("type", "1155");
//                getActivity().startActivity(intent);
//                break;
            case R.id.tv_fwzx://法务咨询
                intent.putExtra("type", "1161");
                getActivity().startActivity(intent);
                break;
            case R.id.tv_fwpt://法网平台
                intent.putExtra("type", "1162");
                getActivity().startActivity(intent);
                break;
            case R.id.tv_lssws://律师事务所
                intent.putExtra("type", "1163");
                getActivity().startActivity(intent);
                break;
            case R.id.tv_yycx://医疗查询
                intent.putExtra("type", "1171");
                getActivity().startActivity(intent);
                break;
            case R.id.lin_ydcx://药店查询
                intent.putExtra("type", "1172");
                getActivity().startActivity(intent);
                break;
            case R.id.yygh://预约挂号
                intent.putExtra("type", "1173");
                getActivity().startActivity(intent);
                break;
            case R.id.tv_zhyb://智慧医保
                GlobalToast.show4("暂不开放", Toast.LENGTH_LONG);
                break;
//            case R.id.tv_zkcx://中考查询
//                GlobalToast.show4("暂不开放", Toast.LENGTH_LONG);
//                break;
            case R.id.tv_ggcx://高考查询改成学科资源
                intent.putExtra("type", "1199");
                getActivity().startActivity(intent);
                break;
            case R.id.tv_xxcx://学校查询

                intent.putExtra("type", "1140");
                getActivity().startActivity(intent);
                break;
            case R.id.tv_pcscx://派出所查询
                intent.putExtra("type", "1178");
                getActivity().startActivity(intent);
                break;
            case R.id.tv_bszn://办事指南
                GlobalToast.show4("暂不开放", Toast.LENGTH_LONG);
                break;
            case R.id.tv_sfyz://身份验证
                GlobalToast.show4("暂不开放", Toast.LENGTH_LONG);
                break;
        }
        }
}
