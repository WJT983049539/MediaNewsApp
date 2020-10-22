package com.rcdz.medianewsapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.rcdz.medianewsapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作用:
 *
 * @author:create by wjt
 * 邮箱 983049539@qq.com
 * time 2020/10/13 15:45
 */
public class MainPoliticsFragment extends Fragment {
    private String[] titles = { "诉求热点", "部门机构", "我的留言"};
    @BindView(R.id.tablayout3)
    TabLayout tablayout;
    @BindView(R.id.viewpager3)
    ViewPager viewPager;

    public MainPoliticsFragment() {
    }

    public static Fragment getInstance() {
        return new MainPoliticsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_politics, container, false);
        ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    private void initView(View view) {
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getFragmentManager());
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        viewPager.setAdapter(myPagerAdapter);
        tablayout.setupWithViewPager(viewPager);
    }


    class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            Fragment hotFragment=null;
            switch (position) {
                case 0:
                    hotFragment = new AppealHotFragment(); //民生诉求留言
                    break;
                  case 1:
                    hotFragment = new OrganizationListFragment(); //部门机构
                    break;
                case 2:
                    hotFragment = new LeavingMessageFragment();//我的留言
                    break;
                default: hotFragment = new AppealHotFragment();

            }
            return hotFragment;
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
