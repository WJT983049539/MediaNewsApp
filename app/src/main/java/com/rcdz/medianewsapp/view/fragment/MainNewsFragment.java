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
import android.widget.Toast;

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
import com.rcdz.medianewsapp.tools.SharedPreferenceTools;
import com.rcdz.medianewsapp.view.activity.LoginActivity;
import com.rcdz.medianewsapp.view.activity.MainActivity;
import com.rcdz.medianewsapp.view.activity.NewsSearchActivity;
import com.rcdz.medianewsapp.view.activity.SelectCannerListActivity;
import com.rcdz.medianewsapp.view.activity.SettingActivity;
import com.rcdz.medianewsapp.view.activity.WelcomeActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

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
    private boolean loginstatu=false;

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
        loginstatu = (boolean) SharedPreferenceTools.getValueofSP(getActivity(),"loginStru",false);
        // todo 请求新闻title
        newsNetWorkPersenter = new NewNetWorkPersenter(getActivity());
        if(loginstatu){
            newsNetWorkPersenter.GetUserSetion(this);  //得到全部plate
        }else{
            newsNetWorkPersenter.GetUserSetionasNoLogin(this);
        }
        newsNetWorkPersenter.GetSensitiveKeyword();
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * 得到新闻列表
     *
     * @param news
     */
    @Override
    public void getAllNewsList(NewsListBean news) {

    }


    @OnClick({R.id.searchBtn, R.id.loginBtn,R.id.lanmumenu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.searchBtn://搜索
                startActivity(new Intent(getContext(), NewsSearchActivity.class));
                break;
            case R.id.loginBtn://个人中心
                if(loginstatu){
                    MainActivity mainActivity= (MainActivity) getActivity();
                    mainActivity.setPositon(4);
                }else{
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                }

                break;
            case R.id.lanmumenu://栏目编辑
                    if(setionBean==null||setionBean.getData().size()==0){
                        GlobalToast.show4("栏目信息不存在，请查看有无栏目",5000);
                    }else{
                        ACache aCache=ACache.get(getActivity());
                        aCache.put("setionBean",setionBean);
                        Intent intent=new Intent(getActivity(), SelectCannerListActivity.class);
                        startActivity(intent);
                    }
                break;
        }
    }

    /**
     * 得到用户的title,显示主页各种新闻页面
     */
    @Override
    public void getUserSetion(List<SetionBean.DataBean> setionBeans) {
        newlist.clear();
        setionBean=new SetionBean();
        setionBean.setCode(200);
        setionBean.setData(setionBeans);
        setionBean.setMessage("ok");

        for(SetionBean.DataBean titleBean:setionBeans){
            if(titleBean.getName().equals("推荐")){
                newlist.add(0,titleBean);
            }else{
                newlist.add(titleBean);
            }
        }

        myPagerAdapter= new MyNewsPagerAdapter(getFragmentManager());
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        viewpager.setAdapter(myPagerAdapter);
        viewpager.setOffscreenPageLimit(5);//缓存5个页面
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tablayout.setupWithViewPager(viewpager);
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
                    bundle.putString("PlateName",newlist.get(position).getName());
                    fragment.setArguments(bundle);
                }else {
                    //todo 子栏目
                    if("1".equals(newlist.get(position).getHasChilds())){    //有子栏目
                        fragment = new SonLanMuFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("PlateID", String.valueOf(newlist.get(position).getId()));
                        bundle.putString("PlateName",newlist.get(position).getName());
                        fragment.setArguments(bundle);
                    }else{
                        fragment = new NewsAdoptFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("PlateID", String.valueOf(newlist.get(position).getId()));
                        bundle.putString("PlateName",newlist.get(position).getName());
                        fragment.setArguments(bundle);
                    }

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
        if(loginstatu){
            newsNetWorkPersenter.GetUserSetion(this);//得到全部plate
        }else{
            newsNetWorkPersenter.GetUserSetionasNoLogin(this);
        }

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }

    }
}
