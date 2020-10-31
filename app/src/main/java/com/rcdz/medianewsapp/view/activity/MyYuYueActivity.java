package com.rcdz.medianewsapp.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.adapter.HistoryAdapter;
import com.rcdz.medianewsapp.model.adapter.YuYueAdapter;
import com.rcdz.medianewsapp.model.bean.YuYueInfoBean;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetYuyue;
import com.rcdz.medianewsapp.view.pullscrllview.NRecyclerView;
import com.rcdz.medianewsapp.view.pullscrllview.interfaces.LoadingListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/23 18:29
 */
public class MyYuYueActivity extends BaseActivity implements GetYuyue {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.yuyue_list)
    NRecyclerView yuyueList;
    @BindView(R.id.yuyuedate)
    TextView yuyuedate;
    int mPage=1;
    private List<YuYueInfoBean.YuYueInfo.ProgramListBean> list=new ArrayList<YuYueInfoBean.YuYueInfo.ProgramListBean>();
    private YuYueAdapter yuYueAdapter;
    @Override
    public String setNowActivityName() {
        return null;
    }

    @Override
    public int setLayout() {
        return R.layout.activity_yuyue;
    }

    @Override
    public void inintView() {
        ButterKnife.bind(this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyYuYueActivity.this.finish();
            }
        });

        ButterKnife.bind(this);
        yuYueAdapter= new YuYueAdapter(MyYuYueActivity.this,list,R.layout.item_yuyue);
        yuyueList.setLayoutManager(new LinearLayoutManager(this));
        yuyueList.setAdapter(yuYueAdapter);
        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(this);
        newNetWorkPersenter.GetYuyueList(this);
    }


    @Override
    public void inintData() {

    }


    @Override
    public void getyuyue(YuYueInfoBean yuYueInfoBean) {
        if(yuYueInfoBean!=null&&yuYueInfoBean.getData()!=null){
            yuyuedate.setText(yuYueInfoBean.getData().getTitle());
            list.addAll(yuYueInfoBean.getData().getProgramList());
            yuYueAdapter.notifyDataSetChanged();
        }

    }
}
