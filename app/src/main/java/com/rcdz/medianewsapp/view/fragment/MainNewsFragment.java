package com.rcdz.medianewsapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.MessageEvent3;
import com.rcdz.medianewsapp.model.bean.NewsListBean;
import com.rcdz.medianewsapp.model.bean.SetionBean;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetAllNewsList;
import com.rcdz.medianewsapp.persenter.interfaces.GetUserSetion;
import com.rcdz.medianewsapp.tools.ACache;
import com.rcdz.medianewsapp.tools.GlobalToast;
import com.rcdz.medianewsapp.tools.SetList;
import com.rcdz.medianewsapp.view.activity.NewsSearchActivity;
import com.rcdz.medianewsapp.view.activity.SelectCannerListActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作用: 主页
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/13 15:42
 */
public class MainNewsFragment extends Fragment implements GetAllNewsList, GetUserSetion {
    private  SetList<SetionBean.DataBean> newlist=new SetList<>(); //栏目集合
    private SetionBean setionBean ;
    @BindView(R.id.searchBtn)
    TextView searchBtn;
    @BindView(R.id.loginBtn)
    ImageView loginBtn;
    @BindView(R.id.modular_home_head)
    RelativeLayout modularHomeHead;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.lanmumenu)
    TextView lanmumenu;
    @BindView(R.id.titlelist)
    LinearLayout titlelist;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private  MyNewsPagerAdapter myPagerAdapter;

    public MainNewsFragment() {
    }

    public static Fragment getInstance() {
        return new MainNewsFragment();
    }

    NewNetWorkPersenter newsNetWorkPersenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_news, container, false);
        ButterKnife.bind(this, view);
        newlist.clear();
        EventBus.getDefault().register(this);//注册evenbus
        // todo 请求新闻title
        newsNetWorkPersenter = new NewNetWorkPersenter(getActivity());
        newsNetWorkPersenter.GetUserSetion(this);//得到全部plate
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        NewNetWorkPersenter newNetWorkPersenter = new NewNetWorkPersenter(getActivity());
//        newNetWorkPersenter.GetNewsList(MainNewsFragment.this);
    }

    /**
     * 得到新闻列表
     *
     * @param news
     */
    @Override
    public void getAllNewsList(NewsListBean news) {

    }

    /**
     * 得到用户的title,显示主页各种新闻页面
     */
    @Override
    public void getUserSetion(SetionBean setionBean2) {
        newlist.clear();
        setionBean=setionBean2;
        for(SetionBean.DataBean titleBean:setionBean2.getData()){
            newlist.add(titleBean);
        }


        myPagerAdapter= new MyNewsPagerAdapter(getFragmentManager());
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        viewpager.setAdapter(myPagerAdapter);
        viewpager.setOffscreenPageLimit(5);//缓存5个页面
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tablayout.setupWithViewPager(viewpager);

    }

    @OnClick({R.id.searchBtn, R.id.loginBtn,R.id.lanmumenu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.searchBtn://搜索
                startActivity(new Intent(getContext(), NewsSearchActivity.class));
                break;
            case R.id.loginBtn://个人中心
                break;
            case R.id.lanmumenu://栏目编辑
                if(setionBean==null||setionBean.getData().size()==0){
                    GlobalToast.show("栏目信息不存在，请查看有无栏目",5000);
                }else{
                    ACache aCache=ACache.get(getActivity());
                    aCache.put("setionBean",setionBean);
                    Intent intent=new Intent(getActivity(), SelectCannerListActivity.class);
                    startActivity(intent);
                }

                break;
        }
    }

    private class MyNewsPagerAdapter extends FragmentPagerAdapter {
        public MyNewsPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }
        @NonNull
        @Override
        public Fragment getItem(int position) {
                //这里是第一个数据是推荐新闻，第二个是普通新闻
                Fragment fragment=null;
                if(newlist.get(position).getName().equals("推荐")){
                    fragment= new NewsRecommendFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("PlateID", String.valueOf(newlist.get(position).getId()));
                    fragment.setArguments(bundle);
                }else {
                    fragment = new NewsAdoptFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("PlateID", String.valueOf(newlist.get(position).getId()));
                    fragment.setArguments(bundle);


                }

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

    //收到事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent3 messageEvent){
        //收到用户的模块然后去显示
        SetList<SetionBean.DataBean> List= messageEvent.getUserList();
        NewNetWorkPersenter newsNetWorkPersenter=new NewNetWorkPersenter(getActivity());
       String  SectionName="";
        for(int i=0;i< List.size();i++){
            SectionName+=List.get(i).getId()+",";
        }
        SectionName= SectionName.substring(0,SectionName.length()-1);

        newsNetWorkPersenter.UpdateUserSections(SectionName);//修改版块
        newsNetWorkPersenter.GetUserSetion(this);//得到全部plate
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }

    }
}
