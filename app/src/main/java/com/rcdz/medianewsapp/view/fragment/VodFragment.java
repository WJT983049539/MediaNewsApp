package com.rcdz.medianewsapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.adapter.DemandAdapter;
import com.rcdz.medianewsapp.model.bean.DemandListBean;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetDemandList;
import com.rcdz.medianewsapp.view.activity.DemandDetailsActivity;
import com.rcdz.medianewsapp.view.customview.SpaceItemDecoration;
import com.rcdz.medianewsapp.view.pullscrllview.NRecyclerView;
import com.rcdz.medianewsapp.view.pullscrllview.interfaces.LoadingListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作用:精品点播
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/19 10:36
 */
public class VodFragment extends Fragment implements GetDemandList {
    public List<DemandListBean.DemandInfo> dataList = new ArrayList<DemandListBean.DemandInfo>();
    @BindView(R.id.demand_list)
    NRecyclerView demandList;
    private String channelSectionId;
    private int mPage = 1;
    private DemandAdapter demandAdapter;
    public VodFragment(){}
    public VodFragment(int channelSectionId2) {
        this.channelSectionId = String.valueOf(channelSectionId2);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.demand, container, false);
        ButterKnife.bind(this, view);
        //先清空
        dataList.clear();
        initData();
        return view;
    }

    /**
     * 获取数据
     */
    private void initData() {
        dataList.clear();
        demandAdapter=new DemandAdapter(dataList,getContext());
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);

        demandList.addItemDecoration(new SpaceItemDecoration());
        demandList.setLayoutManager(gridLayoutManager);
        demandList.setAdapter(demandAdapter);
        NewNetWorkPersenter newNetWorkPersenter = new NewNetWorkPersenter(getContext());
        newNetWorkPersenter.GetDemandList(String.valueOf(mPage), channelSectionId, this);
        demandAdapter.setOnItemClick(new DemandAdapter.OnItemClick() {
            @Override
            public void onitemclik(int position) { // todo 详细播放页
                Intent intent=new Intent(getActivity(), DemandDetailsActivity.class);
                intent.putExtra("demandId",String.valueOf(dataList.get(position).getId()));
                intent.putExtra("title",dataList.get(position).getName());
                intent.putExtra("litletitle",dataList.get(position).getKeyword());
                intent.putExtra("content",dataList.get(position).getMark());
                intent.putExtra("channelSectionId",channelSectionId);
                startActivity(intent);
            }
        });

        demandList.setLoadingListener(new LoadingListener() {
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
        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(getActivity());
        newNetWorkPersenter.GetDemandList(String.valueOf(mPage),channelSectionId, this);
    }

    @Override
    public void getDemandList(DemandListBean demandListBean) {
        demandList.refreshComplete();//刷新成功
        int total=demandListBean.getTotal();
        dataList.addAll(demandListBean.getRows());

        if(dataList.size() == total){
            demandList.setNoMore(true);//没有更多了
        }else{
            demandList.loadMoreComplete();//加载成功
        }
        demandAdapter.notifyDataSetChanged();
    }
}
