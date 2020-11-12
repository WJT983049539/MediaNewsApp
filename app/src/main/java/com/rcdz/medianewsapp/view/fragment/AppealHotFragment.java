package com.rcdz.medianewsapp.view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.adapter.AppealHotAdapter;
import com.rcdz.medianewsapp.model.bean.PliveLeaveInfo;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetPliveLeaveMsgInfo;
import com.rcdz.medianewsapp.tools.GlobalToast;
import com.rcdz.medianewsapp.tools.GsonUtil;
import com.rcdz.medianewsapp.view.pullscrllview.NRecyclerView;
import com.rcdz.medianewsapp.view.pullscrllview.interfaces.LoadingListener;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作用: 民生热点诉求列表
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/17 10:41
 */
public class AppealHotFragment  extends Fragment implements GetPliveLeaveMsgInfo {
    View mRootView;
    @BindView(R.id.hotspotList)
    NRecyclerView mRecyclerView;
    private AppealHotAdapter appealHotAdapter;
    private Activity mContext;
    public ArrayList<PliveLeaveInfo.LeaveMessageInfo> dataList = new ArrayList<PliveLeaveInfo.LeaveMessageInfo>();
    private int mPage = 1;
    public AppealHotFragment() {
    }

    public static AppealHotFragment newInstance(String param1, String param2) {
        AppealHotFragment fragment = new AppealHotFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_appealhot_list, container, false);
            ButterKnife.bind(this, mRootView);
            mContext = getActivity();
            //先清空
            dataList.clear();
            initView();
        }
        return mRootView;
    }
    private  void initView(){
        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(getActivity());
        newNetWorkPersenter.GetPLiveLeaveInfo(String.valueOf(mPage),"0",this);
        appealHotAdapter=new AppealHotAdapter(dataList,getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(appealHotAdapter);
        mRecyclerView.setLoadingListener(new LoadingListener() {
            //下拉刷新
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        mPage = 1;
                        dataList.clear();
                        initListData(mPage);
                    }
                }, 1000);
            }
            //上拉加载
            @Override
            public void onLoadMore() {
                ++mPage;
                initListData(mPage);
            }
        });
    }

    private void initListData(int mPage) {
        mRecyclerView.refreshComplete();//刷新成功
        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(getActivity());
        newNetWorkPersenter.GetPLiveLeaveInfo(String.valueOf(mPage),"0",this);

    }

    @Override
    public void getPliveLeaveMsgInfo(PliveLeaveInfo pliveLeaveInfo) {
        mRecyclerView.refreshComplete();//刷新成功

        int total=pliveLeaveInfo.getTotal();
        dataList.addAll(pliveLeaveInfo.getRows());

        if(dataList.size() == total){
            mRecyclerView.setNoMore(true);//没有更多了
        }else{
            mRecyclerView.loadMoreComplete();//加载成功
        }
        appealHotAdapter.notifyDataSetChanged();

    }
}

