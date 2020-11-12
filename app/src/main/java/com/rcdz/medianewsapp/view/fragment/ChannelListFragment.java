package com.rcdz.medianewsapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.adapter.LiveAdapter;
import com.rcdz.medianewsapp.model.bean.TvCannelBean;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetCannelInfo;
import com.rcdz.medianewsapp.tools.Constant;
import com.rcdz.medianewsapp.view.activity.VideoPlayerActivity;
import com.rcdz.medianewsapp.view.pullscrllview.NRecyclerView;
import com.rcdz.medianewsapp.view.pullscrllview.interfaces.LoadingListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 作用:TV频道列表
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/19 11:00
 */
public class ChannelListFragment extends androidx.fragment.app.Fragment implements GetCannelInfo {
    private  View view;
    int mpage=1;
    NRecyclerView live_list;
    LiveAdapter liveAdapter;
    private List<TvCannelBean.TvCanneInfo>  list =new ArrayList<>();
//    String PlantId;
//    String PlantName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.tv_list,container,false);
        live_list=view.findViewById(R.id.live_list);
//        Bundle bundle = getArguments();
//        PlantId=bundle.getString("PlantId");
//        PlantName=bundle.getString("PlantName");
        ininData();
        return view;
    }

    private void ininData() {
        list.clear();
        liveAdapter=new LiveAdapter(list,getContext());
        live_list.setLayoutManager(new LinearLayoutManager(getContext()));
        live_list.setAdapter(liveAdapter);
        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(getActivity());
        newNetWorkPersenter.GetTvLiveList(String.valueOf(mpage),this);
        liveAdapter.setOnItemClick(new LiveAdapter.OnItemClick() {
            @Override
            public void onitemclik(int position, Object object) {
                if(object!=null){
                    Intent intent = new Intent(getContext(), VideoPlayerActivity.class);
                    intent.putExtra("id",String.valueOf(list.get(position).getId()));
                    intent.putExtra("name",String.valueOf(list.get(position).getName()));
                    intent.putExtra("url",String.valueOf(list.get(position).getStreamUrl()));
                    if(list.get(position).getImageUrl()!=null){
                        intent.putExtra("image",list.get(position).getImageUrl().toString());
                    }else{
                        intent.putExtra("image","");
                    }
                    Constant.isQuanping=false;
                    startActivity(intent);
                }
            }
        });

        live_list.setLoadingListener(new LoadingListener() {
            //下拉刷新
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        mpage = 1;
                        list.clear();
                        initListData(mpage);
                    }
                }, 1000);
            }
            //上拉加载
            @Override
            public void onLoadMore() {
                ++mpage;
                initListData(mpage);
            }
        });
    }

    private void initListData(int mpage) {
        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(getActivity());
        newNetWorkPersenter.GetTvLiveList(String.valueOf(mpage),this);
    }

    @Override
    public void getCannelInfo(TvCannelBean tvCannelBean) {
        live_list.refreshComplete();//刷新成功
        int total=tvCannelBean.getTotal();
        list.addAll(tvCannelBean.getRows());
        liveAdapter.notifyDataSetChanged();
        if(list.size() == total){
            live_list.setNoMore(true);//没有更多了
        }else{
            live_list.loadMoreComplete();//加载成功
        }

    }
}
