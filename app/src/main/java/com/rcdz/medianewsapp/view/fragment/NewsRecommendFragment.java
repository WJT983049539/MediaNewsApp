package com.rcdz.medianewsapp.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.adapter.NewsAdapter;
import com.rcdz.medianewsapp.model.bean.NewsListBean;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetAllNewsList;
import com.rcdz.medianewsapp.view.pullscrllview.NPullScrollView;
import com.rcdz.medianewsapp.view.pullscrllview.NRecyclerView;
import com.rcdz.medianewsapp.view.pullscrllview.interfaces.LoadingListener;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作用: 推荐
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/14 11:14
 */
public class NewsRecommendFragment extends Fragment implements GetAllNewsList {
    public final static String TAG="NewsRecommendFragment";
    @BindView(R.id.roll_view_pager)
    Banner rollViewPager;
    @BindView(R.id.linearLayout_home_main)
    LinearLayout linearLayoutHomeMain;
    @BindView(R.id.android_news_list)
    NRecyclerView androidNewsList;
    @BindView(R.id.mScrollView)
    NPullScrollView mScrollView;
    public NewsAdapter dataAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private String PlateID;
    int mPage = 1;
    public ArrayList<NewsListBean.NewsInfo> newsItemList = new ArrayList<NewsListBean.NewsInfo>();
    //onCreateView()：每次创建、绘制该Fragment的View组件时回调该方法，Fragment将会显示该方法返回的View组件。
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mRootView = inflater.inflate(R.layout.fragment_news_recommend, container, false);
        ButterKnife.bind(this, mRootView);  //fragment 绑定 带两个参数
        PlateID = getArguments().getString("PlateID");
        initView();
        newsItemList.clear();
        return mRootView;
    }
    //获取主页推荐新闻
    private void initView() {
//        getbmp();//获取轮播图

        androidNewsList.setLoadingMoreEnabled(false);//启用上拉加载
        androidNewsList.setPullRefreshEnabled(false);//禁用下拉刷新
        mScrollView.setLoadingMoreEnabled(true);//启用上拉加载
        mScrollView.refreshWithPull();//下拉效果
        mScrollView.setLoadingListener(new LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPage = 1;
                        newsItemList.clear();
                        initNesListView(mPage);
                        mScrollView.refreshComplete();
                    }
                }, 100);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ++mPage;
                        initNesListView(mPage);
                    }
                }, 100);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        androidNewsList.setLayoutManager(layoutManager);
        dataAdapter = new NewsAdapter(newsItemList, getActivity());
        androidNewsList.setAdapter(dataAdapter);
    }

    private void initNesListView(int mPage) {
        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(getActivity());
        newNetWorkPersenter.GetNewsList(NewsRecommendFragment.this,PlateID,String.valueOf(mPage));
    }

    /**
     * 得到新闻
     * @param news
     */
    @Override
    public void getAllNewsList(NewsListBean news) {
        //添加新闻
        List<NewsListBean.NewsInfo> newInfos= news.getRows();
        for (int i = 0; i < newInfos.size(); i++) {
            newsItemList.add(newInfos.get(i));
        }
        dataAdapter.notifyDataSetChanged();
        if (newsItemList.size() >= Integer.valueOf(news.getTotal())) {
            mScrollView.setNoMore(true);//没有更多了
        } else {
            mScrollView.loadMoreComplete();//加载成功
        }
        Log.i(TAG,"得到新闻");
    }
}
