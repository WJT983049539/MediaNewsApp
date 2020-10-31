package com.rcdz.medianewsapp.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.adapter.NewsAdapter;
import com.rcdz.medianewsapp.model.adapter.ZhiDingAdapter;
import com.rcdz.medianewsapp.model.bean.BannerInfoBean;
import com.rcdz.medianewsapp.model.bean.NewsListBean;
import com.rcdz.medianewsapp.model.bean.TopNewsInfo;
import com.rcdz.medianewsapp.model.bean.TopVideoNewBean;
import com.rcdz.medianewsapp.model.bean.TvCannelBean;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetAllNewsList;
import com.rcdz.medianewsapp.persenter.interfaces.GetBanner;
import com.rcdz.medianewsapp.persenter.interfaces.GetCannelInfo;
import com.rcdz.medianewsapp.persenter.interfaces.GetTopNews;
import com.rcdz.medianewsapp.persenter.interfaces.GetTopVideoNews;
import com.rcdz.medianewsapp.tools.AppConfig;
import com.rcdz.medianewsapp.tools.GlideUtil;
import com.rcdz.medianewsapp.tools.GlobalToast;
import com.rcdz.medianewsapp.view.pullscrllview.NPullScrollView;
import com.rcdz.medianewsapp.view.pullscrllview.NRecyclerView;
import com.rcdz.medianewsapp.view.pullscrllview.interfaces.LoadingListener;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作用: 推荐
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/14 11:14
 */
public class NewsRecommendFragment extends Fragment implements GetAllNewsList , GetCannelInfo, GetBanner , GetTopNews, GetTopVideoNews {
    public final static String TAG="NewsRecommendFragment";
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.topvideonew_img)
    ImageView topvideonew_img;
    @BindView(R.id.topvideonew_status)
    ImageView topvideonew_status;
    @BindView(R.id.topvideonew_title)
    TextView topvideonew_title;
    @BindView(R.id.linearLayout_home_main)
    LinearLayout linearLayoutHomeMain;
    @BindView(R.id.android_news_list)
    NRecyclerView androidNewsList;
    @BindView(R.id.mScrollView)
    NPullScrollView mScrollView;
    @BindView(R.id.zhiding_rc)
    RecyclerView zhiding_rc; //置顶新闻
    @BindView(R.id.lin_zhiding)
    LinearLayout lin_zhiding; //置顶新闻
    public NewsAdapter dataAdapter;
    public boolean isSee=false;
    private List<TvCannelBean.TvCanneInfo> canneInfos=new ArrayList<>();


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


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) { //不可见
            isSee=false;
        } else { //当前可见
            isSee=true;
        }
    }

    //获取主页推荐新闻
    private void initView() {
        getbanner();//获取轮播图
        canneInfos.clear();


        getCannelInfo();

        getZhiDingNews();

        initNesListView(mPage);
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

    private void getZhiDingNews() {
        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(getActivity());
        newNetWorkPersenter.GetTopVideoNews(this);
        newNetWorkPersenter.GetTopNews(this);
    }

    private void getbanner() {
        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(getActivity());
        newNetWorkPersenter.GetNewsBanner(this);
    }

    private void getCannelInfo() {
        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(getActivity());
        newNetWorkPersenter.GetTvLiveList("1",this);
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
    //得到频道列表
    @Override
    public void getCannelInfo(TvCannelBean tvCannelBean) {
        if(tvCannelBean!=null&&tvCannelBean.getRows().size()!=0){
            canneInfos.addAll(tvCannelBean.getRows());
            FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
            TuiJianShowCannelFragment tuiJianShowCannelFragment=new TuiJianShowCannelFragment(canneInfos);
            fragmentTransaction.replace(R.id.cannleview,tuiJianShowCannelFragment);
            fragmentTransaction.commit();

        }else{

            FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
            TuiJianShowCannelFragment2 tuiJianShowCannelFragment2=new TuiJianShowCannelFragment2();
            fragmentTransaction.replace(R.id.cannleview,tuiJianShowCannelFragment2);
            fragmentTransaction.commit();
            GlobalToast.show("获取频道列表失败", Toast.LENGTH_LONG);
        }
    }

    @Override
    public void getbanner(BannerInfoBean bannerInfoBean) {
        List<BannerInfoBean.BannerInfo> list = bannerInfoBean.getRows();
        banner.setAdapter(new BannerImageAdapter<BannerInfoBean.BannerInfo>(list) {
            @Override
            public void onBindView(BannerImageHolder holder, BannerInfoBean.BannerInfo data, int position, int size) {
                holder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                RequestOptions options = new RequestOptions()
                        .placeholder(R.mipmap.default_image)
                        .error(R.mipmap.default_image)
                        .skipMemoryCache(false)//禁用内存缓存
                        .diskCacheStrategy(DiskCacheStrategy.ALL);
                Glide.with(holder.itemView)
                        .load(AppConfig.BASE_PICTURE_URL + data.getImageUrl())
                        .apply(options)
                        .into(holder.imageView);
            }
            }).addBannerLifecycleObserver(NewsRecommendFragment.this)   //添加生命周期观察者
                .setIndicator(new CircleIndicator(getActivity()));


        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object data, int position) {
                BannerInfoBean.BannerInfo data1= (BannerInfoBean.BannerInfo) data;
                    //todo 跳转到详情
                String bilder = data1.getLink();
//                NewsNetWorkPersenter newsNetWorkPersenter = new NewsNetWorkPersenter(getActivity());
//                newsNetWorkPersenter.GetNewDetaileInfo(bilder, NewsRecommendFragment.this);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isSee=false;
    }

    @Override
    public void onPause() {
        super.onPause();
        isSee=false;
    }

    //视频置顶新闻
    @Override
    public void getTopNews(TopVideoNewBean topVideoNewBean) {
        if(topVideoNewBean!=null&&topVideoNewBean.getData()!=null&&topVideoNewBean.getData().size()!=0){
           TopVideoNewBean.TopVideoNew topVideoNew= topVideoNewBean.getData().get(0);
            GlideUtil.load(getActivity(),AppConfig.BASE_PICTURE_URL+topVideoNew.getCoverUrl(),topvideonew_img,GlideUtil.getOption());
            if(topVideoNew.getTitle()!=null){
                topvideonew_title.setText(topVideoNew.getTitle());
            }
        }
    }

    @Override
    public void getTopNews(TopNewsInfo topNewsInfo) {
        lin_zhiding.setVisibility(View.VISIBLE);
        if(topNewsInfo!=null){
            ZhiDingAdapter zhiDingAdapter=new ZhiDingAdapter(getActivity(),topNewsInfo.getData(),R.layout.item_zhiding);
            zhiding_rc.setLayoutManager(new LinearLayoutManager(getActivity()));
            zhiding_rc.setAdapter(zhiDingAdapter);
        }else {
            lin_zhiding.setVisibility(View.GONE);
            GlobalToast.show("置顶新闻为空",Toast.LENGTH_LONG);
        }

    }
}
