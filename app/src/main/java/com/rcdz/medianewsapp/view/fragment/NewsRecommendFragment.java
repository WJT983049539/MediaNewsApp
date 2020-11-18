package com.rcdz.medianewsapp.view.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.qw.soul.permission.SoulPermission;
import com.qw.soul.permission.bean.Permission;
import com.qw.soul.permission.bean.Permissions;
import com.qw.soul.permission.callbcak.CheckRequestPermissionsListener;
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
import com.rcdz.medianewsapp.tools.SharedPreferenceTools;
import com.rcdz.medianewsapp.view.activity.MainActivity;
import com.rcdz.medianewsapp.view.activity.NewTimeWebViewActivity;
import com.rcdz.medianewsapp.view.activity.NewsDetailActivity;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用: 推荐
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/14 11:14
 */
public class NewsRecommendFragment extends Fragment implements GetAllNewsList, GetCannelInfo, GetBanner, GetTopNews, GetTopVideoNews {
    public final static String TAG = "NewsRecommendFragment";
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.newtime)
    LinearLayout newtime;
    @BindView(R.id.zhengpawer)
    LinearLayout zhengpawer;
    @BindView(R.id.topvideonew_img)
    ImageView topvideonew_img;
    @BindView(R.id.topvideo)
    RelativeLayout topvideo;
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
    public boolean isSee = false;
    @BindView(R.id.lin_xswz)
    LinearLayout linXswz;
    @BindView(R.id.lin_gzh)
    LinearLayout linGzh;
    @BindView(R.id.lin_jgdw)
    LinearLayout linJgdw;
    @BindView(R.id.lin_xzsp)
    LinearLayout linXzsp;
    @BindView(R.id.lin_more)
    LinearLayout linMore;
    @BindView(R.id.cannleview)
    LinearLayout cannleview;
    private List<TvCannelBean.TvCanneInfo> canneInfos = new ArrayList<>();
    private boolean loginStru = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private String PlateID;
    private String PlateName;
    int mPage = 1;
    public ArrayList<NewsListBean.NewsInfo> newsItemList = new ArrayList<NewsListBean.NewsInfo>();

    //onCreateView()：每次创建、绘制该Fragment的View组件时回调该方法，Fragment将会显示该方法返回的View组件。
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mRootView = inflater.inflate(R.layout.fragment_news_recommend, container, false);
        ButterKnife.bind(this, mRootView);  //fragment 绑定 带两个参数
        PlateID = getArguments().getString("PlateID");
        PlateName = getArguments().getString("PlateName");
        loginStru = (boolean) SharedPreferenceTools.getValueofSP(getActivity(), "loginStru", false);
        initView();
        newsItemList.clear();
        return mRootView;
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) { //不可见
            isSee = false;
        } else { //当前可见
            isSee = true;
        }
    }

    //获取主页推荐新闻
    private void initView() {

        //新闻明时代
        newtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NewTimeWebViewActivity.class));

            }
        });
        //正能量
        zhengpawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WAKE_LOCK,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_WIFI_STATE,
                        Manifest.permission.CAMERA
                ), new CheckRequestPermissionsListener() {
                    @Override
                    public void onAllPermissionOk(Permission[] allPermissions) {
                        startActivity(new Intent(getActivity(), ZpowerActivity.class));
                    }

                    @Override
                    public void onPermissionDenied(Permission[] refusedPermissions) {

                    }
                });


            }
        });

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
                        getbanner();//获取轮播图
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
        dataAdapter.setOnItemClickListener(new NewsAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //跳转到详情页
                if (loginStru) { //记录足迹

                    int type = newsItemList.get(position).getType();
                    if (type == 1) { //文章
                        NewNetWorkPersenter newNetWorkPersenter = new NewNetWorkPersenter(getActivity());
                        newNetWorkPersenter.AddHistoryforNews(String.valueOf(newsItemList.get(position).getType()), String.valueOf(newsItemList.get(position).getTargetId()), PlateID, String.valueOf(type));
                    } else if (type == 2) { //视频
                        NewNetWorkPersenter newNetWorkPersenter = new NewNetWorkPersenter(getActivity());
                        newNetWorkPersenter.AddHistoryforNews(String.valueOf(newsItemList.get(position).getType()), String.valueOf(newsItemList.get(position).getTargetId()), PlateID, "-1");
                    } else if (type == 3) { //图集
                        NewNetWorkPersenter newNetWorkPersenter = new NewNetWorkPersenter(getActivity());
                        newNetWorkPersenter.AddHistoryforNews(String.valueOf(newsItemList.get(position).getType()), String.valueOf(newsItemList.get(position).getTargetId()), PlateID, "-1");
                    }
                }
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("id", newsItemList.get(position).getTargetId());
                intent.putExtra("plateId", PlateID);
                intent.putExtra("platName", PlateName);
                intent.putExtra("ActivityType", newsItemList.get(position).getActivityType());
                intent.putExtra("Type", newsItemList.get(position).getType());
                getActivity().startActivity(intent);
            }
        });
    }

    private void getZhiDingNews() {
        NewNetWorkPersenter newNetWorkPersenter = new NewNetWorkPersenter(getActivity());
        newNetWorkPersenter.GetTopVideoNews(this);
        newNetWorkPersenter.GetTopNews(this);
    }

    private void getbanner() {
        NewNetWorkPersenter newNetWorkPersenter = new NewNetWorkPersenter(getActivity());
        newNetWorkPersenter.GetNewsBanner(this);
    }

    private void getCannelInfo() {
        NewNetWorkPersenter newNetWorkPersenter = new NewNetWorkPersenter(getActivity());
        newNetWorkPersenter.GetTvLiveList("1", this);
    }

    private void initNesListView(int mPage) {
        NewNetWorkPersenter newNetWorkPersenter = new NewNetWorkPersenter(getActivity());
        newNetWorkPersenter.GetNewsList(NewsRecommendFragment.this, PlateID, String.valueOf(mPage));
    }

    /**
     * 得到新闻
     *
     * @param news
     */
    @Override
    public void getAllNewsList(NewsListBean news) {
        //添加新闻
        newsItemList.addAll(news.getRows());
        dataAdapter.notifyDataSetChanged();
        if (newsItemList.size() >= Integer.valueOf(news.getTotal())) {
            mScrollView.setNoMore(true);//没有更多了
        } else {
            mScrollView.loadMoreComplete();//加载成功
        }
        Log.i(TAG, "得到新闻");
    }

    //得到频道列表
    @Override
    public void getCannelInfo(TvCannelBean tvCannelBean) {
        if (tvCannelBean != null && tvCannelBean.getRows().size() != 0) {
            canneInfos.addAll(tvCannelBean.getRows());
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            TuiJianShowCannelFragment tuiJianShowCannelFragment = new TuiJianShowCannelFragment(canneInfos);
            fragmentTransaction.replace(R.id.cannleview, tuiJianShowCannelFragment);
            fragmentTransaction.commit();

        } else {

            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            TuiJianShowCannelFragment2 tuiJianShowCannelFragment2 = new TuiJianShowCannelFragment2();
            fragmentTransaction.replace(R.id.cannleview, tuiJianShowCannelFragment2);
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

                String usrl = data.getImageUrl();

                String asda = usrl.replace("/small", "");
                Glide.with(holder.itemView)
                        .load(AppConfig.BASE_PICTURE_URL + asda)
                        .apply(options)
                        .into(holder.imageView);
            }
        }).addBannerLifecycleObserver(NewsRecommendFragment.this)   //添加生命周期观察者
                .setIndicator(new CircleIndicator(getActivity()));


        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(Object data, int position) {
                BannerInfoBean.BannerInfo data1 = (BannerInfoBean.BannerInfo) data;
                //todo 跳转到详情
                String Link = data1.getLink();
                Intent intent = new Intent(getActivity(), BannnerDetailActivity.class);
                intent.putExtra("url", Link);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isSee = false;
    }

    @Override
    public void onPause() {
        super.onPause();
        isSee = false;
    }

    //视频置顶新闻
    @Override
    public void getTopNews(TopVideoNewBean topVideoNewBean) {
        if (topVideoNewBean != null && topVideoNewBean.getData() != null && topVideoNewBean.getData().size() != 0) {
            TopVideoNewBean.TopVideoNew topVideoNew = topVideoNewBean.getData().get(0);
            String imag = topVideoNew.getCoverUrl();
            String imageurl = imag.split(",")[0];
//            String image=imag.replace("/small","");
            GlideUtil.load(getActivity(), AppConfig.BASE_PICTURE_URL + imageurl, topvideonew_img, GlideUtil.getOption());
            if (topVideoNew.getTitle() != null) {
                topvideonew_title.setText(topVideoNew.getTitle());
            }

            topvideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                    intent.putExtra("id", topVideoNew.getTargetId());
                    intent.putExtra("plateId", topVideoNew.getSectionId());
                    intent.putExtra("platName", PlateName);
                    intent.putExtra("ActivityType", topVideoNew.getActivityType());
                    intent.putExtra("Type", topVideoNew.getType());
                    getActivity().startActivity(intent);
                }
            });
        }
    }

    @Override
    public void getTopNews(TopNewsInfo topNewsInfo) {
        lin_zhiding.setVisibility(View.VISIBLE);
        if (topNewsInfo != null) {
            ZhiDingAdapter zhiDingAdapter = new ZhiDingAdapter(getActivity(), topNewsInfo.getData(), R.layout.item_zhiding);
            zhiding_rc.setLayoutManager(new LinearLayoutManager(getActivity()));
            zhiding_rc.setAdapter(zhiDingAdapter);
        } else {
            lin_zhiding.setVisibility(View.GONE);
            GlobalToast.show("置顶新闻为空", Toast.LENGTH_LONG);
        }

    }

    @OnClick({R.id.lin_xswz, R.id.lin_gzh, R.id.lin_jgdw, R.id.lin_xzsp, R.id.lin_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_xswz:
                MainActivity mainActivity= (MainActivity) getActivity();
                mainActivity.setPositon(3);
                break;
            case R.id.lin_gzh: //公众号

                break;
            case R.id.lin_jgdw:
                MainActivity mainActivity2= (MainActivity) getActivity();
                mainActivity2.setPositon(3);
                break;
            case R.id.lin_xzsp:
                MainActivity mainActivity3= (MainActivity) getActivity();
                mainActivity3.setPositon(3);
                break;
            case R.id.lin_more:
                MainActivity mainActivity4= (MainActivity) getActivity();
                mainActivity4.setPositon(2);
                break;
        }
    }
}
