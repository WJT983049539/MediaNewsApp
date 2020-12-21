package com.rcdz.medianewsapp.view.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.qw.soul.permission.SoulPermission;
import com.qw.soul.permission.bean.Permission;
import com.qw.soul.permission.bean.Permissions;
import com.qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.qw.soul.permission.callbcak.GoAppDetailCallBack;
import com.rcdz.medianewsapp.MAppaction;
import com.rcdz.medianewsapp.R;
import com.rcdz.medianewsapp.model.adapter.MainViewPageAdapter;
import com.rcdz.medianewsapp.persenter.NewNetWorkPersenter;
import com.rcdz.medianewsapp.view.customview.NoSlideViewPage;
import com.rcdz.medianewsapp.view.customview.OutLoginDialog2;
import com.rcdz.medianewsapp.view.fragment.MainAllFragment;
import com.rcdz.medianewsapp.view.fragment.MainCenterFragmentTest;
import com.rcdz.medianewsapp.view.fragment.MainNewsFragment;
import com.rcdz.medianewsapp.view.fragment.MainPoliticsFragment;
import com.rcdz.medianewsapp.view.fragment.MainTVFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{
    @BindView(R.id.main_viewpager)
    NoSlideViewPage mainViewpager;
    @BindView(R.id.main_tab_one)
    RadioButton mainTabOne;
    @BindView(R.id.main_tab_two)
    RadioButton mainTabTwo;
    @BindView(R.id.main_tab_three)
    RadioButton mainTabThree;
    @BindView(R.id.main_tab_four)
    RadioButton mainTabFour;
    @BindView(R.id.main_tab_five)
    RadioButton mainTabFive;
    @BindView(R.id.main_tab_rg)
    RadioGroup mainTabRg;
    private MainViewPageAdapter mainViewPageAdapter;
    private List<Fragment> fragments = new ArrayList<>();
    @Override
    public String setNowActivityName() {
        return "mainActivity";
    }

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void inintView() {
        ButterKnife.bind(this);
//        NewNetWorkPersenter newNetWorkPersenter=new NewNetWorkPersenter(this);
//        newNetWorkPersenter.GetUserInfo("",null);
        /**
         * 初始化页面控件
         */
        mainTabRg.setOnCheckedChangeListener(this);
        fragments.add(MainNewsFragment.getInstance());  //首页
        fragments.add(MainTVFragment.getInstance());  //频道
        fragments.add(MainAllFragment.getInstance());  //智慧圈
        fragments.add(MainPoliticsFragment.getInstance());  //民生
        fragments.add(MainCenterFragmentTest.getInstance());  //个人中心
        mainViewPageAdapter = new MainViewPageAdapter(getSupportFragmentManager(), fragments);
        mainViewpager.setOffscreenPageLimit(5); //缓存4个页面
        mainViewpager.setAdapter(mainViewPageAdapter);
    }

    @Override
    public void inintData() {

    }
    public void setPositon2(int positon){ //民生
       Fragment f= fragments.get(3);
        if(f instanceof MainPoliticsFragment){
            MainPoliticsFragment ff= (MainPoliticsFragment) f;
            ff.test(positon);
        }
        mainTabRg.check( R.id.main_tab_four);
    }
    public void setPositon(int positon){
        switch (positon){
            case 0:
                mainTabRg.check( R.id.main_tab_one);
                break;
            case 1:
                mainTabRg.check( R.id.main_tab_two);
                break;
            case 2:
                mainTabRg.check( R.id.main_tab_three);
                break;
            case 3:
                mainTabRg.check( R.id.main_tab_four);
                break;
            case 4:
                mainTabRg.check( R.id.main_tab_five);
                break;
        }

    }



    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.main_tab_one:
                mainViewpager.setCurrentItem(0);
                break;
            case R.id.main_tab_two:
                mainViewpager.setCurrentItem(1);
                break;
            case R.id.main_tab_three:
                mainViewpager.setCurrentItem(2);
                break;
            case R.id.main_tab_four:
                mainViewpager.setCurrentItem(3);
                break;
            case R.id.main_tab_five:
                mainViewpager.setCurrentItem(4);
                break;
            default:
                mainViewpager.setCurrentItem(0);
                break;
        }
    }

    /**
     * 两次返回退出
     */
    private long mExitTime;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // //添加动画
        ////                        TranslateAnimation animation=new TranslateAnimation(dialogview.getWidth(),0,0,0);
        ////                        animation.setDuration(2000);
        ////                        dialogview1.setAnimation(animation);
        ////                        animation.start();
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

//            if ((System.currentTimeMillis() - mExitTime) > 2000) {
//                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
//                mExitTime = System.currentTimeMillis();
//            } else {
//                MAppaction.exit();
//            }

            OutLoginDialog2 outLoginDialog2=new OutLoginDialog2(this, new OutLoginDialog2.DialogConfirmClick() {
                @Override
                public void ConfirmClick() {
                    MainActivity.this.finish();
                }

                @Override
                public void noClick() {

                }
            });
            outLoginDialog2.show();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
