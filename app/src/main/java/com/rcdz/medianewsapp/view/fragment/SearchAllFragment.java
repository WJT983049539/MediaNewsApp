package com.rcdz.medianewsapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.adapter.NewsAdapter;
import com.rcdz.medianewsapp.model.bean.MuhuNewBean;
import com.rcdz.medianewsapp.model.bean.NewsListBean;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetAllNewsList;
import com.rcdz.medianewsapp.tools.SharedPreferenceTools;
import com.rcdz.medianewsapp.view.activity.NewsDetailActivity;
import com.rcdz.medianewsapp.view.pullscrllview.NRecyclerView;
import com.rcdz.medianewsapp.view.pullscrllview.interfaces.LoadingListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作用:查找全部的新闻
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/15 17:24
 */
public class SearchAllFragment extends Fragment implements GetAllNewsList {
    private String sousuoContent;
    @BindView(R.id.android_news_list)
    NRecyclerView androidNewsList;
    private  boolean loginStru=false;
    int mPage = 1;
    private NewsAdapter dataAdapter;
    public ArrayList<NewsListBean.NewsInfo> newsItemList = new ArrayList<NewsListBean.NewsInfo>();
    private HashMap<Integer, NewsListBean.NewsInfo> NewTitlelist2 = new HashMap<>(); //健存tarid 值存具体内容
    public final static String TAG="SearchAllFragment";
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    public SearchAllFragment(String sousuoContent) {
        this.sousuoContent=sousuoContent;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.android_news_list, container, false);
        ButterKnife.bind(this, mRootView);  //fragment 绑定 带两个参数
        loginStru= (boolean) SharedPreferenceTools.getValueofSP(getActivity(),"loginStru",false);
        initView();
        newsItemList.clear();
        NewTitlelist2.clear();
        return mRootView;
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        androidNewsList.setLayoutManager(layoutManager);
        dataAdapter = new NewsAdapter(newsItemList, getActivity());
        androidNewsList.setAdapter(dataAdapter);
        androidNewsList.refreshComplete();//刷新成功
        dataAdapter.notifyDataSetChanged();//notifyDataSetChanged()可以在修改适配器绑定的数组后，不用重新刷新Activity，通知Activity更新ListView
        initNewsList(mPage);
        androidNewsList.setLoadingListener(new LoadingListener() {
            //下拉刷新
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        mPage = 1;
                        newsItemList.clear();
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
        dataAdapter.setOnItemClickListener(new NewsAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if(loginStru){ //记录足迹
                    int type=newsItemList.get(position).getType();
                    if(type==1){ //文章
                        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(getActivity());
                        newNetWorkPersenter.AddHistoryforNews(String.valueOf(newsItemList.get(position).getType()),String.valueOf(newsItemList.get(position).getTargetId()), String.valueOf(newsItemList.get(position).getSectionId()), String.valueOf(type));
                    }else if(type==2){ //视频
                        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(getActivity());
                        newNetWorkPersenter.AddHistoryforNews(String.valueOf(newsItemList.get(position).getType()),String.valueOf(newsItemList.get(position).getTargetId()),String.valueOf(newsItemList.get(position).getSectionId()), "-1");
                    }else if(type==3){ //图集
                        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(getActivity());
                        newNetWorkPersenter.AddHistoryforNews(String.valueOf(newsItemList.get(position).getType()),String.valueOf(newsItemList.get(position).getTargetId()),String.valueOf(newsItemList.get(position).getSectionId()), "-1");
                    }
                }
                //跳转到详情页
//                Intent intent =new Intent(getActivity(), NewsDetailActivity.class);
//                intent.putExtra("id",newsItemList.get(position).getTargetId());
//                intent.putExtra("plateId",newsItemList.get(position).getSectionId());
//                intent.putExtra("platName",PlateName);
//                intent.putExtra("ActivityType",newsItemList.get(position).getActivityType());
//                intent.putExtra("Type",newsItemList.get(position).getType());
//                getActivity().startActivity(intent);
            }
        });
    }

    private void initNewsList(int mPage1) {
        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(getActivity());
        newNetWorkPersenter.NewSearch(sousuoContent,String.valueOf(mPage1),true, SearchAllFragment.this);
    }

    @Override
    public void getAllNewsList(NewsListBean news) {
        NewTitlelist2.clear();
        androidNewsList.refreshComplete();//刷新成功
        List<NewsListBean.NewsInfo> newInfos= news.getRows();
        for (int i = 0; i < newInfos.size(); i++) {
            NewTitlelist2.put(newInfos.get(i).getTargetId(),newInfos.get(i));
       }
        List<NewsListBean.NewsInfo> valuesList = new ArrayList<NewsListBean.NewsInfo>(NewTitlelist2.values()); //去掉重复新闻
        newsItemList.addAll(valuesList);
        dataAdapter.notifyDataSetChanged();
        if (newsItemList.size() >= 100) { //只能显示100条
            androidNewsList.setNoMore(true);//没有更多了
        } else {
            androidNewsList.loadMoreComplete();//加载成功
        }
        Log.i(TAG,"得到新闻");
    }
}
