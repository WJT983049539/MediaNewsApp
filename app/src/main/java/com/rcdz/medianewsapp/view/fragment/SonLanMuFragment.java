package com.rcdz.medianewsapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.adapter.LanmuAdapter;
import com.rcdz.medianewsapp.model.bean.NewsListBean;
import com.rcdz.medianewsapp.model.bean.SonLanmuBean;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetSonCluln;
import com.rcdz.medianewsapp.tools.GlobalToast;
import com.rcdz.medianewsapp.view.activity.LanmuActivity;
import com.rcdz.medianewsapp.view.activity.ShowNewsActivity;
import com.rcdz.medianewsapp.view.pullscrllview.NRecyclerView;
import com.rcdz.medianewsapp.view.pullscrllview.interfaces.LoadingListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作用: 子栏目
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/12/18 18:14
 */
public class SonLanMuFragment extends Fragment implements GetSonCluln {
    @BindView(R.id.lanm_list)
    NRecyclerView lanmList;
    private List<SonLanmuBean.SonLanmuInfo> list=new ArrayList<>();
    private  int mPage = 1;
    private String PlateID;
    private String PlateName;
    private   LanmuAdapter lanmuAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        PlateID = getArguments().getString("PlateID");
        PlateName=getArguments().getString("PlateName");
        View view = inflater.inflate(R.layout.lanmufragment, container, false);
        ButterKnife.bind(this, view);  //fragment 绑定 带两个参数
        initView();
        return view;
    }

    private void initView() {
        list.clear();
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lanmList.setLayoutManager(linearLayoutManager);
        lanmuAdapter=new LanmuAdapter(getActivity(),list,R.layout.sonitemlanmu);
        lanmList.setAdapter(lanmuAdapter);
        lanmuAdapter.setOnItemClicklisten(new LanmuAdapter.OnItemClicklisten() {
            @Override
            public void onitemClickListen(int pid,String HasChilds,String name,String logo) {
                //判断是否柚子栏目
                if(("1").equals(HasChilds)){ //柚子栏目
                    Intent intent=new Intent(getActivity(), LanmuActivity.class);
                    intent.putExtra("pid",pid);
                    intent.putExtra("name",name);
                    intent.putExtra("logo",logo);
                    getActivity().startActivity(intent);
                }else{ //无子栏目,直接显示新闻列表
                    Intent intent=new Intent(getActivity(), ShowNewsActivity.class);
                    intent.putExtra("PlateID",pid);
                    intent.putExtra("PlateName",name);
                    getActivity().startActivity(intent);
                }

            }
        });
        initNewsList(mPage);
        lanmList.setLoadingListener(new LoadingListener() {
            //下拉刷新
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        mPage = 1;
                        list.clear();
                        initNewsList(mPage);
                    }
                }, 1000);
            }

            //上拉加载
            @Override
            public void onLoadMore() {
                ++mPage;
                initNewsList(mPage);
            }
        });
    }
    //获取子栏目列表
    private void initNewsList(int mPage) {
        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(getActivity());
        newNetWorkPersenter.GetSonClumn(SonLanMuFragment.this,PlateID,mPage,"30");
    }

    @Override
    public void getSonClumn(SonLanmuBean sonLanmuBean) {
        if( sonLanmuBean.getCode()==200){
            list.addAll(sonLanmuBean.getRows());
            lanmList.refreshComplete();//刷新成功
            lanmuAdapter.notifyDataSetChanged();
            if (list.size() >= Integer.valueOf(sonLanmuBean.getTotal())) {
                lanmList.setNoMore(true);//没有更多了
            } else {
                lanmList.loadMoreComplete();//加载成功
            }
        }else{
            GlobalToast.show4("获取子栏目失败", Toast.LENGTH_LONG);
        }
    }
}
