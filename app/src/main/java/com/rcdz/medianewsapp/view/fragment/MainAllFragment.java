package com.rcdz.medianewsapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.view.activity.ModelNetWebActivity;

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
    @BindView(R.id.textView19)
    TextView textView19;
    @BindView(R.id.tv_gzh)
    TextView tvGzh;
    @BindView(R.id.tv_jgdw)
    TextView tvJgdw;
    @BindView(R.id.tv_xzsp)
    TextView tvXzsp;
    @BindView(R.id.tv_xswz)
    TextView tvXswz;
    @BindView(R.id.tv_zhdj)
    TextView tvZhdj;
    @BindView(R.id.tv_kds)
    TextView tvKds;
    @BindView(R.id.tv_tgb)
    TextView tvTgb;
    @BindView(R.id.tv_dbz)
    TextView tvDbz;
    @BindView(R.id.tv_swz)
    TextView tvSwz;
    @BindView(R.id.tv_cgj)
    TextView tvCgj;
    @BindView(R.id.tv_ggzxc)
    TextView tvGgzxc;
    @BindView(R.id.tv_cstcw)
    TextView tvCstcw;
    @BindView(R.id.tv_cwz)
    TextView tvCwz;
    @BindView(R.id.tv_cky)
    TextView tvCky;
    @BindView(R.id.tv_hcp)
    TextView tvHcp;
    @BindView(R.id.tv_clk)
    TextView tvClk;
    @BindView(R.id.tv_csb)
    TextView tvCsb;
    @BindView(R.id.tv_jdf)
    TextView tvJdf;
    @BindView(R.id.tv_jsf)
    TextView tvJsf;
    @BindView(R.id.tv_rqf)
    TextView tvRqf;
    @BindView(R.id.tv_jhf)
    TextView tvJhf;
    @BindView(R.id.tv_dsf)
    TextView tvDsf;
    @BindView(R.id.tv_tckd)
    TextView tvTckd;
    @BindView(R.id.tv_gcsc)
    TextView tvGcsc;
    @BindView(R.id.tv_tcfc)
    TextView tvTcfc;
    @BindView(R.id.tv_tczp)
    TextView tvTczp;
    @BindView(R.id.tv_jzfw)
    TextView tvJzfw;
    @BindView(R.id.tv_fwzx)
    TextView tvFwzx;
    @BindView(R.id.tv_fwpt)
    TextView tvFwpt;
    @BindView(R.id.tv_lssws)
    TextView tvLssws;
    @BindView(R.id.tv_ylcx)
    TextView tvYlcx;
    @BindView(R.id.tv_ydcx)
    TextView tvYdcx;
    @BindView(R.id.tv_yygh)
    TextView tvYygh;
    @BindView(R.id.tv_zhyb)
    TextView tvZhyb;
    @BindView(R.id.tv_zkcx)
    TextView tvZkcx;
    @BindView(R.id.tv_ggcx)
    TextView tvGgcx;
    @BindView(R.id.tv_xxcx)
    TextView tvXxcx;
    @BindView(R.id.tv_pcscx)
    TextView tvPcscx;
    @BindView(R.id.tv_bszn)
    TextView tvBszn;
    @BindView(R.id.tv_sfyz)
    TextView tvSfyz;

    public MainAllFragment() {
    }

    public static Fragment getInstance() {
        return new MainAllFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.technologys, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick({R.id.textView19, R.id.tv_gzh, R.id.tv_jgdw, R.id.tv_xzsp, R.id.tv_xswz, R.id.tv_zhdj, R.id.tv_kds, R.id.tv_tgb, R.id.tv_dbz, R.id.tv_swz, R.id.tv_cgj, R.id.tv_ggzxc, R.id.tv_cstcw, R.id.tv_cwz, R.id.tv_cky, R.id.tv_hcp, R.id.tv_clk, R.id.tv_csb, R.id.tv_jdf, R.id.tv_jsf, R.id.tv_rqf, R.id.tv_jhf, R.id.tv_dsf, R.id.tv_tckd, R.id.tv_gcsc, R.id.tv_tcfc, R.id.tv_tczp, R.id.tv_jzfw, R.id.tv_fwzx, R.id.tv_fwpt, R.id.tv_lssws, R.id.tv_ylcx, R.id.tv_ydcx, R.id.tv_yygh, R.id.tv_zhyb, R.id.tv_zkcx, R.id.tv_ggcx, R.id.tv_xxcx, R.id.tv_pcscx, R.id.tv_bszn, R.id.tv_sfyz})
    public void onViewClicked(View view) {
        Intent intent=new Intent(getActivity(), ModelNetWebActivity.class);
        switch (view.getId()) {
            case R.id.textView19:
                break;
            case R.id.tv_gzh://公众号
                break;
            case R.id.tv_jgdw://机构单位
                break;
            case R.id.tv_xzsp://行政审批
                break;
            case R.id.tv_xswz://线上问政
                break;
            case R.id.tv_zhdj://智慧党建
                break;
            case R.id.tv_kds://看电视
                break;
            case R.id.tv_tgb://听广播
                intent.putExtra("type","1122");
                getActivity().startActivity(intent);
                break;
            case R.id.tv_dbz://读报纸
                intent.putExtra("type","1123");
                 getActivity().startActivity(intent);
                break;
            case R.id.tv_swz://上网站
                intent.putExtra("type","1124");
                 getActivity().startActivity(intent);
                break;
            case R.id.tv_cgj://查公交
                intent.putExtra("type","1131");
                 getActivity().startActivity(intent);
                break;
            case R.id.tv_ggzxc://公共自行车
                intent.putExtra("type","1132");
                 getActivity().startActivity(intent);
                break;
            case R.id.tv_cstcw://城市停车位
                intent.putExtra("type","1133");
                 getActivity().startActivity(intent);
                break;
            case R.id.tv_cwz://查违章
                intent.putExtra("type","1134");
                 getActivity().startActivity(intent);
                break;
            case R.id.tv_cky://查客运
                intent.putExtra("type","1135");
                 getActivity().startActivity(intent);
                break;
            case R.id.tv_hcp://火车票
                intent.putExtra("type","1136");
                 getActivity().startActivity(intent);
                break;
            case R.id.tv_clk://查路况
                intent.putExtra("type","1137");
                 getActivity().startActivity(intent);
                break;
            case R.id.tv_csb://查社保
                intent.putExtra("type","1141");
                 getActivity().startActivity(intent);
                break;
            case R.id.tv_jdf://交电费
                intent.putExtra("type","1142");
                 getActivity().startActivity(intent);
                break;
            case R.id.tv_jsf://交水费
                intent.putExtra("type","1143");
                 getActivity().startActivity(intent);
                break;
            case R.id.tv_rqf://燃气费
                intent.putExtra("type","1144");
                 getActivity().startActivity(intent);
                break;
            case R.id.tv_jhf://交话费
                intent.putExtra("type","1145");
                 getActivity().startActivity(intent);
                break;
            case R.id.tv_dsf://电视费
                intent.putExtra("type","1146");
                 getActivity().startActivity(intent);
                break;
            case R.id.tv_tckd://同城快递
                intent.putExtra("type","1151");
                 getActivity().startActivity(intent);
                break;
            case R.id.tv_gcsc://同城商超
//                intent.putExtra("type","1152");
//                 getActivity().startActivity(intent);
                break;
            case R.id.tv_tcfc://同城房产
                intent.putExtra("type","1153");
                 getActivity().startActivity(intent);
                break;
            case R.id.tv_tczp://同城招聘
                intent.putExtra("type","1154");
                 getActivity().startActivity(intent);
                break;
            case R.id.tv_jzfw://家政服务
                intent.putExtra("type","1155");
                 getActivity().startActivity(intent);
                break;
            case R.id.tv_fwzx://法务咨询
                intent.putExtra("type","1161");
                 getActivity().startActivity(intent);
                break;
            case R.id.tv_fwpt://法网平台
                intent.putExtra("type","1162");
                 getActivity().startActivity(intent);
                break;
            case R.id.tv_lssws://律师事务所
                intent.putExtra("type","1163");
                 getActivity().startActivity(intent);
                break;
            case R.id.tv_ylcx://医疗查询
                intent.putExtra("type","1171");
                 getActivity().startActivity(intent);
                break;
            case R.id.tv_ydcx://药店查询
                intent.putExtra("type","1172");
                 getActivity().startActivity(intent);
                break;
            case R.id.tv_yygh://预约挂号
                intent.putExtra("type","1173");
                 getActivity().startActivity(intent);
                break;
            case R.id.tv_zhyb://智慧医保
                break;
            case R.id.tv_zkcx://中考查询

                break;
            case R.id.tv_ggcx://高考查询
                break;
            case R.id.tv_xxcx://学校查询
                break;
            case R.id.tv_pcscx://派出所查询
                intent.putExtra("type","1178");
                 getActivity().startActivity(intent);
                break;
            case R.id.tv_bszn://办事指南
                break;
            case R.id.tv_sfyz://身份验证
                break;
        }
    }
}
