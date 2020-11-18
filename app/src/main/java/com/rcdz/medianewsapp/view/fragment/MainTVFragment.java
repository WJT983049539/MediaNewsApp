package com.rcdz.medianewsapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.bean.CannalSationBean;
import com.rcdz.medianewsapp.model.bean.SetionBean;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.persenter.interfaces.GetCannelSetion;
import com.rcdz.medianewsapp.persenter.interfaces.GetUserSetion;
import com.rcdz.medianewsapp.tools.SetList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作用:频道
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/13 15:44
 */
public class MainTVFragment extends Fragment implements GetCannelSetion {
    private SetList<CannalSationBean.CannelSation> list=new SetList<CannalSationBean.CannelSation>(); //栏目集合
    @BindView(R.id.tablayout2)
    TabLayout tablayout;
    @BindView(R.id.viewpager2)
    ViewPager viewpager;
    @BindView(R.id.toolbar_title)
    TextView title;
    private MyNewsPagerAdapter myPagerAdapter;
    public  MainTVFragment() {
    }

    public static Fragment getInstance() {
        return new MainTVFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_tv, container, false);
        ButterKnife.bind(this, view);
        title.setText("频道");
        initData();
        return view;
    }

    private void initData() {
        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(getContext());
        newNetWorkPersenter.GetChannelSations(this); //获取频道列表
    }


    @Override
    public void getCannel(CannalSationBean CannalSationBean) {
       list.clear();
        for(com.rcdz.medianewsapp.model.bean.CannalSationBean.CannelSation titleBean:CannalSationBean.getRows()){
            list.add(titleBean);
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
            int ChannelSectionId= list.get(position).getId();
            Fragment fragment = null;
            if(list.get(position).getName().equals("TV直播")){  //现在tv频道里面暂时只有3个频道
                fragment= new ChannelListFragment();
//                Bundle bundle=new Bundle();
//                bundle.putString("PlantId", String.valueOf(list.get(position).getId()));
//                bundle.putString("PlantName", list.get(position).getName());
//                fragment.setArguments(bundle);
            }else if(list.get(position).getName().equals("精品点播")){
//                Bundle bundle=new Bundle();
//                bundle.putString("PlantId", String.valueOf(list.get(position).getId()));
//                bundle.putString("PlantName", list.get(position).getName());
//                fragment.setArguments(bundle);
                fragment= new VodFragment(ChannelSectionId);
            }else if(list.get(position).getName().equals("直播间")){
                fragment= new LivingFragment();
            }else{ //剩下的通用fragment
                fragment= new VodFragment(ChannelSectionId);
            }
            return fragment;
        }
        @Override
        public String getPageTitle(int position) {

            return list.get(position).getName();
        }


        @Override
        public int getCount() {
            return  list.size();
        }
    }
}
