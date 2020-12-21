package com.rcdz.medianewsapp.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.HomeClumnInfo;
import com.rcdz.medianewsapp.model.bean.SetionBean;
import com.rcdz.medianewsapp.model.bean.SonLanmuBean;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetHomeClumnInfoList;
import com.rcdz.medianewsapp.persenter.interfaces.GetSonCluln;
import com.rcdz.medianewsapp.tools.AppConfig;
import com.rcdz.medianewsapp.tools.GlideUtil;
import com.rcdz.medianewsapp.tools.SetList;
import com.rcdz.medianewsapp.tools.SharedPreferenceTools;
import com.rcdz.medianewsapp.view.fragment.MainNewsFragment;
import com.rcdz.medianewsapp.view.fragment.NewsAdoptFragment;
import com.rcdz.medianewsapp.view.fragment.NewsRecommendFragment;
import com.rcdz.medianewsapp.view.fragment.SonLanMuFragment;
import com.rcdz.medianewsapp.view.pullscrllview.NRecyclerView;
import com.rcdz.medianewsapp.view.pullscrllview.interfaces.LoadingListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用:子栏目列表 *
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/12/19 15:48
 */
public class LanmuActivity extends BaseActivity2 implements GetSonCluln {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.lanmtablayout)
    TabLayout lanmtablayout;
    @BindView(R.id.lanmviewpager)
    ViewPager lanmviewpager;
    @BindView(R.id.cover)
    ImageView cover;
    private Boolean loginstatu;
    private int pid;
    private String name="";
    private String logo="";
    private SetList<SonLanmuBean.SonLanmuInfo> newlist=new SetList<>(); //栏目集合
    private LnmuNewsPagerAdapter lnmuNewsPagerAdapter;
    @Override
    public String setNowActivityName() {
        return "";
    }

    @Override
    public int setLayout() {
        return R.layout.activity_lanmu;
    }

    @Override
    public void inintView() {
        ButterKnife.bind(this);

        pid=getIntent().getIntExtra("pid",-1);
        name=getIntent().getStringExtra("name");
        logo=getIntent().getStringExtra("logo");
        toolbarTitle.setText(name);
        String url= AppConfig.BASE_PICTURE_URL+logo;
        GlideUtil.load(LanmuActivity.this,url,cover,GlideUtil.getOption());
        loginstatu = (boolean) SharedPreferenceTools.getValueofSP(this,"loginStru",false);
        // todo 请求新闻title
        NewNetWorkPersenter  newsNetWorkPersenter = new NewNetWorkPersenter(this);
        newsNetWorkPersenter.GetSonClumn(this, String.valueOf(pid),1,"100"); //查询子栏目
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LanmuActivity.this.finish();
            }
        });

    }

    @Override
    public void inintData() {

    }


    @OnClick(R.id.img_back)
    public void onViewClicked() {
        this.finish();
    }


    @Override
    public void getSonClumn(SonLanmuBean sonLanmuBean) {
        newlist.clear();
        newlist.addAll(sonLanmuBean.getRows());
        lnmuNewsPagerAdapter= new LnmuNewsPagerAdapter(getSupportFragmentManager());
        lanmviewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(lanmtablayout));
        lanmviewpager.setAdapter(lnmuNewsPagerAdapter);
        lanmviewpager.setOffscreenPageLimit(5);//缓存5个页面
        lanmtablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        lanmtablayout.setupWithViewPager(lanmviewpager);
    }
    private class LnmuNewsPagerAdapter extends FragmentPagerAdapter {
        public LnmuNewsPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
            // 那新闻
            Fragment fragment=null;
            fragment = new NewsAdoptFragment();
            Bundle bundle = new Bundle();
            bundle.putString("PlateID", String.valueOf(newlist.get(position).getId()));
            bundle.putString("PlateName",newlist.get(position).getName());
            fragment.setArguments(bundle);
            return fragment;
        }
        @Override
        public String getPageTitle(int position) {
            return newlist.get(position).getName();
        }


        @Override
        public int getCount() {
            return  newlist.size();
        }
    }
}
